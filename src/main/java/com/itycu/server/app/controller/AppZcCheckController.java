package com.itycu.server.app.controller;


import com.itycu.server.app.dto.pandian.ZcCheckListDTO;
import com.itycu.server.controller.ZcCheckController;
import com.itycu.server.dao.*;
import com.itycu.server.model.Dept;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.ZcCheck;
import com.itycu.server.model.ZcCheckItem;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.ZcCheckService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/zcCheck")
@Api(tags = "App盘点数据相关")
public class AppZcCheckController {
    private static Logger logger = LoggerFactory.getLogger(AppZcCheckController.class);

    @Autowired
    private ZcCheckDao zcCheckDao;

    @Autowired
    private ZcCheckItemDao zcCheckItemDao;

    @Autowired
    private ZcCheckService zcCheckService;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    UserDao userDao;

    @Autowired
    private ZcInfoDao zcInfoDao;


    @PostMapping("/getZcCheckList")
    @ApiOperation(value = "App端获取盘点列表")
    public Map<String, Object> getZcCheckList(@RequestBody ZcCheckListDTO zcCheckListDTO) {
        Integer page = zcCheckListDTO.getOffset();
        Integer limit = zcCheckListDTO.getLimit();
        Map<String, Object> param = new HashMap<>();
        Integer type = null;
        String typeStr = zcCheckListDTO.getType();
        if (StringUtils.isEmpty(typeStr)) {
            type = 3;
        } else {
            type = Integer.valueOf(typeStr);
        }
        SysUser sysUser = UserUtil.getLoginUser();
        long deptId = sysUser.getDeptid();
        int checked = 0;
        logger.info("获取档期登录用户的部门id:{}", deptId);
        int count = 0;
        Map<String, Object> map = new HashMap();
        List<com.itycu.server.model.ZcCheck> managerIdList = new ArrayList<>();
        //获取去区分不同的部门
        Dept dept = deptDao.getById(deptId);
        if (null != dept) {
            logger.info("获取的盘点数据是 type======>{}", type);
            if (type == 3 || null == type) {
                param.put("profit", null);
            } else {
                param.put("profit", type);
            }
            param.put("del", "0");
            param.put("createBy", UserUtil.getLoginUser().getId());
            param.put("deptId", deptId);
            param.put("pid", dept.getPid());
            param.put("deptType", dept.getC03());
            if (("cwb").equals(dept.getC03())) {
                param.put("statusList", Arrays.asList(0, 1));// 盘点状态不等于2
                //财务登录当前账号
                logger.info("当前登录账号类型财务部门是======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(param, page * limit - limit, limit);
            } else if ("zhb".equals(dept.getC03()) || "kjb".equals(dept.getC03())
                    || "yyb".equals(dept.getC03()) || "bwb".equals(dept.getC03())) {
                //四个部门的登录当前账
                param.put("statusList", Arrays.asList(0, 1));//  盘点状态不等于2
                logger.info("当前登录账号搜四个管理部门中之一:======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(param, page * limit - limit, limit);
            } else {
                //其他部门或者是支行登录
                param.put("statusList", Arrays.asList(0, 1));//  盘点状态不等于2
                logger.info("当前登录账号类型是其他账号:======>>{}", dept.getDeptname());
                logger.info("当前登录账号参数类型:======>>{}", param);
                managerIdList = queryManagerDeptIds(param, page * limit - limit, limit);
            }
            count = zcCheckDao.queryCountManagerDeptIds(param);
            logger.info("获得的查询总数是==={}", count);
        }
        createZcCheckTableInfo(managerIdList);
        if (!CollectionUtils.isEmpty(managerIdList)) {
            for (ZcCheck zcCheck : managerIdList) {
                long id = zcCheck.getId();
                int zcCheckItemNum = 0;
                List<ZcCheckItem> items = zcCheckItemDao.queryAllZcCheckItem(id);
                if (!CollectionUtils.isEmpty(items)) {
                    for (ZcCheckItem zcCheckItem : items) {
                        //盘点中
                        if (zcCheckItem.getResult().equals("0")) {
                            zcCheckItemNum++;
                        }
                    }
                }
                zcCheck.setZcCheckItemNum(zcCheckItemNum);
            }
        }
        map.put("data", managerIdList);
        map.put("count", count);
        map.put("code", "0");
        map.put("message", "");
        return map;
    }


    private List<com.itycu.server.model.ZcCheck> queryManagerDeptIds(Map<String, Object> map, int page, int limit) {
        List<com.itycu.server.model.ZcCheck> list = zcCheckDao.queryManagerDeptIds(map, page, limit);
        return list;
    }



    private void createZcCheckTableInfo(List<ZcCheck> managerIdList) {
        if (CollectionUtils.isEmpty(managerIdList)) {
            return;
        }
        for (ZcCheck zcCheck : managerIdList) {
            long id = zcCheck.getId();
            ZcCheck zc = zcCheckDao.getById(id);
            Dept dept = deptDao.getById(Long.parseLong(zcCheck.getCheckDeptId()));
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            zcCheck.setCheck_num( dept.getJx() + "-PD" + year + "-"+appendName(5, String.valueOf(zc.getBh())));
            List<ZcCheckItem> zcCheckItemList = zcCheckItemDao.queryCheckFailItem(id);
            if (null != zcCheckItemList) {
                zcCheck.setError(zcCheckItemList.size());
                zcCheck.setNormal(zc.getTotal() - zcCheckItemList.size());
            } else {
                zcCheck.setError(0);
                zcCheck.setNormal(zc.getTotal());
            }
            if (null != zc.getCheckUserId()) {
                String userName = zcCheckDao.queryPandianUserName(zcCheck.getId(),zc.getCheckUserId());
                if (StringUtils.isEmpty(userName)) {
                    zcCheck.setCheckUserName("");
                } else {
                    zcCheck.setCheckUserName(userName);
                }
            } else {
                zcCheck.setCheckUserName("");
            }
        }
    }


    private String appendName(int target, String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        int length = source.length();
        if (length >= target) {
            return source;
        } else {
            StringBuffer sb = new StringBuffer();
            int size = target - length;
            for (int i = 0; i < size; i++) {
                sb.append("0");
            }
            return sb.toString() + source;
        }
    }
}
