package com.itycu.server.app.controller;


import com.itycu.server.app.dto.chuzhi.AppDealDataDTO;
import com.itycu.server.app.dto.chuzhi.AppDealListDTO;
import com.itycu.server.app.dto.chuzhi.AppDealListRecordDetailDTO;
import com.itycu.server.app.dto.chuzhi.AppInsertDataDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.chuzhi.DealZcInfoVO;
import com.itycu.server.app.vo.fenye.PageVO;
import com.itycu.server.dao.*;
import com.itycu.server.dto.ZcBfCheckDto;
import com.itycu.server.dto.ZcBfDto;
import com.itycu.server.model.Dept;
import com.itycu.server.model.FlowTodoItem;
import com.itycu.server.model.Todo;
import com.itycu.server.model.ZcBfItem;
import com.itycu.server.service.ZcBfService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/deal")
@Api(tags = "App处置管理")
public class AppDealController {


    private static Logger logger = LoggerFactory.getLogger(AppDealController.class);

    @Autowired
    private ZcInfoDao zcInfoDao;

    @Autowired
    private ZcBfService zcBfService;

    @Autowired
    private ZcBfDao zcBfDao;

    @Autowired
    private ZcBfItemDao zcBfItemDao;

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private FlowTodoItemDao flowTodoItemDao;

    @Autowired
    private DeptDao deptDao;


    @PostMapping(value = "/bfList")
    @ApiOperation(value = "获取资产报废的列表", notes = "资产报废列表")
    public Map<String, Object> getZcBFList(@RequestBody AppDealListDTO appDealListDTO) {
        try {
            int limit = appDealListDTO.getLimit();
            int offset = appDealListDTO.getOffset();
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("del", 0);
            paramsMap.put("syDeptId", UserUtil.getLoginUser().getDeptid());
            paramsMap.put("keyword", appDealListDTO.getKeyword());
            paramsMap.put("status", 1);
            List<DealZcInfoVO> dealZcInfoVOList = zcBfDao.getZcBFList(paramsMap, offset * limit - limit, limit);
            map.put("data", dealZcInfoVOList);
            map.put("message", "操作成功");
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return FailMap.createFailMap();
        }
    }


    /**
     * @param appInsertDataDTO
     * @return
     */
    @PostMapping(value = "/insertBfData")
    @ApiOperation(value = "添加报废记录申请", notes = "添加报废记录申请")
    public Map<String, Object> insertBfData(@RequestBody AppInsertDataDTO appInsertDataDTO) {
        try {
            if (CollectionUtils.isEmpty(appInsertDataDTO.getZcBfItemList())) {
                return FailMap.createFailMapMsg("列表参数为空");
            }
            Map<String, Object> map = new HashMap<>();
            ZcBfDto zcBfDto = new ZcBfDto();
            zcBfDto.setType(appInsertDataDTO.getType() + "");
            zcBfDto.setBfDes(appInsertDataDTO.getBfDes());
            zcBfDto.setBfCategory(appInsertDataDTO.getBfCategory());
            List<ZcBfItem> zcBfItemList = new ArrayList<>();
            List<DealZcInfoVO> infoVOList = appInsertDataDTO.getZcBfItemList();
            infoVOList.forEach(k -> {
                ZcBfItem zcBfItem = new ZcBfItem();
                zcBfItem.setBz(k.getBz());
                zcBfItem.setDel(0);
                zcBfItem.setZcId(k.getId());
                zcBfItem.setSyDeptId(k.getSyDeptId());
                zcBfItem.setGlDeptId(k.getGlDeptId());
                zcBfItem.setEpcid(k.getEpcid());
                zcBfItem.setZcName(k.getZcName());
                zcBfItem.setFileName(k.getFileName());
                zcBfItem.setFileUrl(k.getImageUrl());

                zcBfItemList.add(zcBfItem);
            });
            zcBfDto.setZcBfItemList(zcBfItemList);
            zcBfService.save(zcBfDto);
            map.put("data", "");
            map.put("message", "操作成功");
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("添加报废记录申请错误{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }


    /**
     * @param pageVO
     * @return
     */
    @PostMapping(value = "/getBFRecordList")
    @ApiOperation(value = "获取报废的记录列表", notes = "获取报废的记录列表")
    public Map<String, Object> getBFRecordList(@RequestBody PageVO pageVO) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", "gl");
        params.put("glDeptId", UserUtil.getLoginUser().getDeptid());
        Map<String, Object> map = new HashMap<>();
        Integer page = pageVO.getOffset();
        Integer limit = pageVO.getLimit();
        List<Map<String, Object>> list = zcBfDao.listZcbf(params, page * limit - limit, limit);
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }


    /**
     * @param
     * @return
     */
    @PostMapping(value = "/getBFRecordItemList")
    @ApiOperation(value = "获取报废的记录列表下面的详情数据列表", notes = "获取报废的记录列表下面的详情数据列表")
    public Map<String, Object> getBFRecordItemList(@RequestBody AppDealListRecordDetailDTO appDealListRecordDetailDTO) {
        try {
            Map<String, Object> map = new HashMap();
            List<Map<String, Object>> list = new ArrayList<>();
            if (0 != appDealListRecordDetailDTO.getZcBfId()) {
                list = zcBfItemDao.listDetailByZcBfId(appDealListRecordDetailDTO.getZcBfId());
            }
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "成功");
            return map;
        } catch (Exception e) {
            logger.error("获取报废的记录列表下面的详情数据列表错误{}", e.getMessage());
            return FailMap.createFailMapMsg("数据列表错误");
        }
    }

    /**
     * 审核部门
     * @param appDealDataDTO
     * @return
     */
    @PostMapping("/bfCheckMainInfo")
    @ApiOperation(value = "资产处置审核主信息",notes = "资产处置审核主信息")
    public Map bfCheckMainInfo(@RequestBody AppDealDataDTO appDealDataDTO) {
        Map<String,Object> map = new HashMap();
        try {
            HashMap<String, Object> data = zcBfDao.getZcBfDetail(appDealDataDTO.getBizid());
            // 用户部门ID
            long deptid = UserUtil.getLoginUser().getDeptid();
            Dept dept = deptDao.getById(deptid);
            // 部门提交数量
            if ("3".equals(dept.getZhfhgl())) {
                //List<Dept> deptList = deptDao.listByIdAndChildIds(dept.getPid());
                List<Dept> deptList = deptDao.listByPid(dept.getPid());
                // 当前用户所在的商行全部部门
                int size = deptList.size();
                // 查询当前审核部门本年度已经有的报废提交
                int number = zcBfItemDao.countYearSubmit(deptid);
                data.put("deptSize", size);
                data.put("haveSubmit", number);
                data.put("remain", size - number);
                // 已有的是否全部完成
                List<FlowTodoItem> flowTodoItems = flowTodoItemDao.listByToDoId(appDealDataDTO.getTodoid());
                ArrayList<Long> list = new ArrayList<>();
                ArrayList<Long> users = new ArrayList<>();
                TreeSet<Long> ids = new TreeSet<>();
                TreeSet<Long> applyUsers = new TreeSet<>();
                for (FlowTodoItem flowTodoItem : flowTodoItems) {
                    ids.add(flowTodoItem.getFlowItemId());
                    applyUsers.add(flowTodoItem.getSendby());
                }
                list.addAll(ids);
                users.addAll(applyUsers);
                List<String> deptNames = deptDao.findDeptByUsers(users);
                if (deptNames.size()>0) {
                    String deptName = StringUtils.join(deptNames, "，");
                    data.put("applyDeptName",deptName);
                }
                // 统计审核部门是否全部完成
                int result = zcBfItemDao.countByIds(list);
                if (result > 0) {
                    data.put("result", 1);
                } else {
                    data.put("result", 0);
                }
            }
            // 查询待办的信息
            Todo todo = todoDao.getById(appDealDataDTO.getTodoid());
            // 查询子项
            HashMap<String, Object> params = new HashMap<>();
            params.put("flowTodoId", todo.getId());
            params.put("status", "0");
            int count = flowTodoItemDao.count(params);
            data.put("type", todo.getType());
            data.put("num", count);
            map.put("data",data);
            map.put("code","0");
            map.put("message","成功");
        } catch (Exception e) {
            logger.info("资产处置审核主信息{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }


    /**
     * 资产处置审核列表信息
     * @return
     */
    @PostMapping("/bfCheckItemList")
    @ApiOperation(value = "资产处置审核列表信息",notes = "资产处置审核列表信息")
    public Map bfCheckItemList(@RequestBody AppDealDataDTO appDealDataDTO) {
        Map<String,Object> map = new HashMap();
        try {
            List<Map<String,Object>> list = null;
            list = zcBfItemDao.listDetailByFlowTodoIdNew(appDealDataDTO.getTodoid());
            if (!ObjectUtils.isEmpty(appDealDataDTO.getType())) {
                list = zcBfItemDao.listDetailByFlowTodoIdNewByType(appDealDataDTO.getTodoid(),appDealDataDTO.getType());
            }
            map.put("data",list);
            map.put("code","0");
            map.put("msg","成功");
            map.put("code","0");
            map.put("message","成功");
        } catch (Exception e) {
            logger.info("资产处置审核列表信息{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }


    /**
     * 资产处置审核-审核部审核
     * @return
     */
    @PostMapping("/shbCheck")
    @ApiOperation(value = "资产处置审核-审核部审核",notes = "资产处置审核-审核部审核")
    public Map shbCheck(@RequestBody AppDealDataDTO appDealDataDTO) {
        Map<String,Object> map = new HashMap();
        try {
            ZcBfCheckDto zcBfCheckDto = new ZcBfCheckDto();
            zcBfCheckDto.setBfzcid(appDealDataDTO.getBizid());
            zcBfCheckDto.setItemStatus(appDealDataDTO.getItemStatus());
            zcBfCheckDto.setAgainSubmit(appDealDataDTO.getAgainSubmit());
            zcBfCheckDto.setNeirong(appDealDataDTO.getNeirong());
            zcBfCheckDto.setFlowTodoItems(appDealDataDTO.getFlowTodoItems());

            zcBfService.checkNew(zcBfCheckDto);
            map.put("code","0");
            map.put("message","请求成功");
            map.put("data",null);
        } catch (Exception e) {
            logger.info("资产处置审核{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }


    /**
     * 资产处置审核-提交到财务
     * @return
     */
    @PostMapping("/submitCw")
    @ApiOperation(value = "资产处置审核-提交到财务",notes = "资产处置审核-提交到财务")
    public Map submitCw(@RequestBody AppDealDataDTO appDealDataDTO) {
        Map<String,Object> map = new HashMap();
        try {
            ZcBfCheckDto zcBfCheckDto = new ZcBfCheckDto();
            zcBfCheckDto.setId(appDealDataDTO.getTodoid());
            zcBfCheckDto.setFlowTodoItems(appDealDataDTO.getFlowTodoItems());

            zcBfService.submitToCw(zcBfCheckDto);
            map.put("code","0");
            map.put("message","请求成功");
            map.put("data",null);
        } catch (Exception e) {
            logger.info("资产处置审核{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }


    /**
     * 资产处置审核-财务审核
     * @return
     */
    @PostMapping("/cwCheck")
    @ApiOperation(value = "资产处置审核-财务审核",notes = "资产处置审核-财务审核")
    public Map cwCheck(@RequestBody AppDealDataDTO appDealDataDTO) {
        Map<String,Object> map = new HashMap();
        try {
            ZcBfCheckDto zcBfCheckDto = new ZcBfCheckDto();
            zcBfCheckDto.setId(appDealDataDTO.getTodoid());
            zcBfCheckDto.setNeirong(appDealDataDTO.getNeirong());
            zcBfCheckDto.setFlowTodoItems(appDealDataDTO.getFlowTodoItems());

            zcBfService.cwcheck(zcBfCheckDto);
            map.put("code","0");
            map.put("message","请求成功");
            map.put("data",null);
        } catch (Exception e) {
            logger.info("资产处置审核{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }

}
