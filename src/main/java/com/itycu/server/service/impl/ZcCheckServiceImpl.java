package com.itycu.server.service.impl;

import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.ZcCheckDao;
import com.itycu.server.dao.ZcCheckItemDao;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.*;
import com.itycu.server.service.ZcCheckService;
import com.itycu.server.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class ZcCheckServiceImpl implements ZcCheckService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZcInfoDao zcInfoDao;
    @Autowired
    protected ZcCheckItemDao zcCheckItemDao;

    @Autowired
    protected ZcCheckDao zcCheckDao;
    @Autowired
    private DeptDao deptDao;

    @Override
    public void saves(ZcCheck zcCheck) {

//        try {
//            //获取盘点部门
//            Long CheckDeptId = zcCheck.getCheckDeptId();
//
//            //根据部门id和当前登录管理部门人员的id 查询资产id 列表
//            //判断当前登录人是否管理部门
//            Dept modelDept = deptDao.getById(UserUtil.getLoginUser().getDeptid());
//            String zhfhgl = modelDept.getZhfhgl();
//            if (zhfhgl.equals("0")||zhfhgl.equals("2")) {
//                throw new NullPointerException("当前登录用户不是管理部门，无法创建盘点单");
//            }
//
//            //将资产id插入到zc_check_item表中
//            Map map = new HashMap();
//            map.put("syDeptId", CheckDeptId);
//            map.put("glDeptId", UserUtil.getLoginUser().getDeptid());
//            map.put("del", 0);//查询没有删除的数据
//            map.put("bf", 0);//查询没有报废的数据
//            List<ZcInfoDto> list_zcInfo =zcInfoDao.listByCondition(map);
//            if (list_zcInfo.size()==0){
//                throw new NullPointerException("此部门下没有当前管理的资产");
//            }
//            List<ZcCheckItem> deptList = new ArrayList<>();
//            //保存主表
//            zcCheck.setCreateBy(UserUtil.getLoginUser().getId());
//            zcCheck.setDel(0);
//            zcCheck.setStatus(0);
//            zcCheck.setTotal(list_zcInfo.size());
//            zcCheckDao.save(zcCheck);
//
//            for (int i = 0; i < list_zcInfo.size(); i++) {
//                ZcCheckItem model = new ZcCheckItem();
//                model.setZcCheckId(zcCheck.getId());
//                model.setZcId(list_zcInfo.get(i).getId());
//                model.setDel(0);
//                deptList.add(model);
//            }
//
//            zcCheckItemDao.saves(deptList);
//        } catch (Exception e) {
//            log.error("资产盘点单创建失败", e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        }
//        log.info("资产盘点单创建{}", UserUtil.getLoginUser().getId());
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertZcTask(ZcCheck zcCheck) {
        SysUser sysUser = UserUtil.getLoginUser();
        String checkDeptId = zcCheck.getCheckDeptId();
        if (StringUtils.isEmpty(checkDeptId)) {
            log.info("盘点部门的数据id没有选择");
            return 0;
        }

        int insertResult = 0;
        String[] ids = checkDeptId.split(",");
        List<String> idList = Arrays.asList(ids);
        Map<String, Object> map = new HashMap();

        map.put("del", 0);//查询没有删除的数据
        map.put("bf", 0);//查询没有报废的数据
        if (null != sysUser) {
            List<ZcCheckItem> zcCheckItemList = new ArrayList<>();
            long deptid = sysUser.getDeptid();
            Dept dept = deptDao.getById(deptid);
            String deptType = dept.getC03();
            if ("cwb".equals(deptType)) {
                //财务部门创建盘点订单
                for (int i = 0; i < idList.size(); i++) {
                    map.put("syDeptId", idList.get(i));

                    map.put("pid", dept.getPid());
                    log.info("sysDeptId===={},pid===={}", idList.get(i), deptid);
                    List<ZcInfoDto> zcInfoList = zcInfoDao.queryCwbZcInfoList(map);
                    if (!CollectionUtils.isEmpty(zcInfoList)) {
                        int size = zcInfoList.size();
                        zcCheck.setCheckDeptId(String.valueOf(idList.get(i)));
                        saveZcCheck(zcCheck, size);
                        for (int j = 0; j < zcInfoList.size(); j++) {
                            ZcCheckItem model = createZcCheckItem(zcCheck, zcInfoList.get(j).getId());
                            zcCheckItemList.add(model);
                            model.setReCheck(0);
                            zcCheckItemDao.saveZcItem(model);
                        }
                        // zcCheckItemDao.saves(zcCheckItemList);
                        insertResult++;
                        updateCheckBH(zcCheck.getId(), idList.get(i));
                    }
                }
                return insertResult;
            } else if ("bwb".equals(deptType)
                    || "zhb".equals(deptType)
                    || "yyb".equals(deptType)
                    || "kjb".equals(deptType)) {
                //运营部门 综合办公室 电子科技部门  保卫部创建盘点订单
                for (int i = 0; i < idList.size(); i++) {
                    map.put("syDeptId", idList.get(i));
                    // map.put("glDeptId", deptid);
                    map.put("deptType", deptType);
                    log.info("syDeptId===={},glDeptId===={}", idList.get(i), deptid);
                    List<ZcInfoDto> zcInfoList = zcInfoDao.queryOtherZcInfoList(map);
                    if (!CollectionUtils.isEmpty(zcInfoList)) {
                        zcCheck.setCheckDeptId(String.valueOf(idList.get(i)));
                        saveZcCheck(zcCheck, zcInfoList.size());
                        for (int j = 0; j < zcInfoList.size(); j++) {
                            ZcCheckItem model = createZcCheckItem(zcCheck, zcInfoList.get(j).getId());
                            zcCheckItemList.add(model);
                            model.setReCheck(0);
                            zcCheckItemDao.saveZcItem(model);
                        }
                        insertResult++;
                        updateCheckBH(zcCheck.getId(), idList.get(i));
                    }
                }
                return insertResult;
            } else {
                log.info("创建部门是使用部门");
                for (int i = 0; i < idList.size(); i++) {
                    map.put("syDeptId", idList.get(i));
                    map.put("deptType", null);
                    List<ZcInfoDto> zcInfoList = zcInfoDao.querySyDeptZcList(map);
                    if (!CollectionUtils.isEmpty(zcInfoList)) {
                        zcCheck.setCheckDeptId(String.valueOf(idList.get(i)));
                        saveZcCheck(zcCheck, zcInfoList.size());
                        for (int j = 0; j < zcInfoList.size(); j++) {
                            ZcCheckItem model = createZcCheckItem(zcCheck, zcInfoList.get(j).getId());
                            zcCheckItemList.add(model);
                            model.setReCheck(0);
                            zcCheckItemDao.saveZcItem(model);
                        }
                        insertResult++;
                        updateCheckBH(zcCheck.getId(), idList.get(i));
                    }
                }
                return insertResult;
            }
        }
        return 0;
    }

    @Override
    public int checkHasCreatedCount(long createBy, long deptId) {
        return checkHasCreatedByCreateByAndDept(createBy,deptId);
    }

    @Override
    public Map pdeSaveCheck(String deptId,int profit) {
        long userId = UserUtil.getLoginUser().getId();
        int resultCount = checkHasCreatedByCreateByAndDept2(userId, Long.parseLong(deptId),profit);
        Map<String, Object> map = new HashMap<>();
        if (resultCount > 0) {
            map.put("code", "500");
            map.put("message", "该部门已经创建过盘点单了");
            return map;
        }
        List<ZcCheckItem> zcCheckItemList = new ArrayList<>();
        map.put("syDeptId", deptId);
        map.put("del", 0);
        map.put("bf", 0);
        map.put("deptType", null);
        List<ZcInfoDto> zcInfoList = zcInfoDao.querySyDeptZcList(map);
        if (!CollectionUtils.isEmpty(zcInfoList)) {
            ZcCheck zcCheck = new ZcCheck();
            //设置为实物盘点
            zcCheck.setProfit(profit);
            zcCheck.setReCheck(0);
            zcCheck.setCheckTime(new Date());
            zcCheck.setCheckDeptId(String.valueOf(deptId));
            saveZcCheck(zcCheck, zcInfoList.size());
            long zcCheckId = zcCheck.getId();
            log.info("zcCheckId========>>{}", zcCheckId);
            for (int j = 0; j < zcInfoList.size(); j++) {
                ZcCheckItem model = createZcCheckItem(zcCheck, zcInfoList.get(j).getId());
                zcCheckItemList.add(model);
                model.setReCheck(0);
                zcCheckItemDao.saveZcItem(model);
            }
            int updateResult = updateCheckBH(zcCheck.getId(), deptId);
            if (updateResult > 0) {
                List<ZcCheckItem> zcCheckItemList1 = zcCheckItemDao.queryAllZcCheckItem(zcCheckId);
                log.info("zcCheckItemList1========>>{}", zcCheckItemList1);
                zcCheck.setCheckItemList(zcCheckItemList1);
                zcCheck.setCreateUserName(getCreateUserName());
                zcCheck.setDeptName(getDeptName(deptId));
                map.put("data", zcCheck);
                //map.put("itemList",zcCheckItemList);
                map.put("code", "0");
                map.put("message", "成功");

            } else {
                map.put("code", "500");
                map.put("message", "失败");
            }
        }
        return map;
    }


    private int checkHasCreatedByCreateByAndDept(long createBy, long zcCheckDeptId) {
        int checkCount = zcCheckItemDao.checkHasCreatedByCreateByAndDept(createBy, zcCheckDeptId);
        return checkCount;
    }

    private int checkHasCreatedByCreateByAndDept2(long createBy, long zcCheckDeptId,int profit) {
        int checkCount = zcCheckItemDao.checkHasCreatedByCreateByAndDept2(createBy, zcCheckDeptId,profit);
        return checkCount;
    }

    /**
     * 查询今年各部门今年最大的盘点单号
     *
     * @return
     */
    private int updateCheckBH(long id, String checkId) { //创建编号
        log.info("zcCheckId====>{},checkDeptId===={}", id, checkId);
        int max = 0;
        String jx = deptDao.getJxById(Long.parseLong(checkId));
        Integer maxBh = deptDao.queryMaxBh(jx);
        if (null == maxBh) {
            max = 1;
        } else {
            max = maxBh + 1;
        }
        return zcCheckDao.updateZcCheckBh(id, max);
    }

    /**
     * 插入盘点的主表数据
     *
     * @param zcCheck 盘点数据
     * @param size    字表数据长度
     */
    private void saveZcCheck(ZcCheck zcCheck, int size) {
        zcCheck.setCheckUserId(UserUtil.getLoginUser().getId());
        zcCheck.setCreateBy(UserUtil.getLoginUser().getId());
        zcCheck.setDel(0);
        zcCheck.setStatus(0);
        zcCheck.setTotal(size);
        zcCheckDao.save(zcCheck);
    }


    private ZcCheckItem createZcCheckItem(ZcCheck zcCheck, Long id) {
        ZcCheckItem model = new ZcCheckItem();
        model.setZcCheckId(zcCheck.getId());
        model.setZcId(id);
        model.setDel(0);
        return model;
    }


    private String getDeptName(String deptid) {
        Dept dept = deptDao.getById(Long.parseLong(deptid));
        return dept.getDeptname();
    }


    private String getCreateUserName() {
        String userName = UserUtil.getLoginUser().getNickname();
        return userName;
    }

}
