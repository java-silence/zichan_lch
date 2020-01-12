package com.itycu.server.app.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itycu.server.app.dto.pandian.*;
import com.itycu.server.app.vo.pandian.CheckItemReportVO;
import com.itycu.server.app.vo.pandian.CheckItemVO;
import com.itycu.server.app.vo.pandian.ZcCheckDetailReportVO;
import com.itycu.server.dao.*;
import com.itycu.server.dto.SysUserDto;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.*;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.ZcCheckService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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


    @PostMapping(value = "/save")
    @ApiOperation(value = "保存")
    @Transactional
    public Map save(@RequestBody ZcCheck zcCheck) {
        Map<String, Object> map = new HashMap();
        if (!StringUtils.isEmpty(zcCheck.getCheckDeptId())) {
            String[] ids = zcCheck.getCheckDeptId().split(",");
            for (int i = 0; i < ids.length; i++) {
                int createCount = zcCheckService.checkHasCreatedCount(UserUtil.getLoginUser().getId(), Long.parseLong(ids[i]));
                if (createCount > 0) {
                    map.put("message", "该盘点单已经创建过了");
                    map.put("code", "400");
                    return map;
                }
            }
        }
        int result = zcCheckService.insertZcTask(zcCheck);
        if (result > 0) {
            map.put("code", "0");
            map.put("message", "操作成功");
        } else {
            map.put("code", "500");
            map.put("message", "操作失败");
        }
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
            zcCheck.setCheck_num(dept.getJx() + "-PD" + year + "-" + appendName(5, String.valueOf(zc.getBh())));
            List<ZcCheckItem> zcCheckItemList = zcCheckItemDao.queryCheckFailItem(id);
            if (null != zcCheckItemList) {
                zcCheck.setError(zcCheckItemList.size());
                zcCheck.setNormal(zc.getTotal() - zcCheckItemList.size());
            } else {
                zcCheck.setError(0);
                zcCheck.setNormal(zc.getTotal());
            }
            if (null != zc.getCheckUserId()) {
                String userName = zcCheckDao.queryPandianUserName(zcCheck.getId(), zc.getCheckUserId());
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


    @PostMapping("/info")
    @ApiOperation(value = "获取盘点单的数据信息")
    @Transactional
    public Map<String, Object> zcCheckDanHaoInfo(@RequestBody ZxCheckListItemDTO zxCheckListItemDTO) {
        Map<String, Object> map = new HashMap<>();
        int offset = zxCheckListItemDTO.getOffset();
        int limit = zxCheckListItemDTO.getLimit();
        long id = zxCheckListItemDTO.getId();
        logger.info("获取订单的盘点id=====》{}", zxCheckListItemDTO.getId());
        ZcCheck zcCheck = zcCheckDao.getById(id);
        String jx = deptDao.getJxById(id);
        int count = zcCheck.getBh();
        String pdNum = "";
        if (count == 0) {
            pdNum = "00001";
        } else {
            String countStr = String.valueOf(count);
            pdNum = appendName(5, countStr);
        }
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String danHao = jx + "-PD" + year + "-" + pdNum;
        HashMap<String, Object> params = new HashMap<>();
        List<CheckItemVO> zcCheckItems = zcCheckItemDao.queryCheckItemListById(id, offset * limit - limit, limit);
        params.put("zcCheckItems", zcCheckItems);
        params.put("danhao", danHao);
        map.put("data", zcCheckItems);
        return map;
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/finishAssetsStatus")
    @ApiOperation(value = "完成后,修改资产状态", tags = "完成后,修改资产状态")
    public Map updateAssetsState(@RequestBody ZcCheckFinishedDTO zcCheckFinishedDTO) {
        //更新找到的资产状态。
        long id = zcCheckFinishedDTO.getId();
        Map map = new HashMap();
        try {
            ZcCheck zcCheck = zcCheckDao.getById(zcCheckFinishedDTO.getId());
            zcCheck.setStatus(2);
            long checkId = zcCheck.getId();
            if (zcCheck.getReCheck() == 0) {
                //盘点完成
                List<ZcCheckItem> items = zcCheckItemDao.queryAllZcCheckItem(checkId);
                int result = updateZcStatusAndCheckZcResult(zcCheck, items);
                if (result > 0) {
                    map.put("code", "0");
                    map.put("message", "成功");
                } else {
                    map.put("code", "500");
                    map.put("message", "失败");
                }
            } else {
                logger.info("提交的盘点单的id是==={}", id);
                //更新新的盘点数据
                List<ZcCheckItem> newCheckItemList = zcCheckItemDao.queryRecheckId(id);
                zcCheck.setDel(1);
                updateZcStatusAndCheckZcResult(zcCheck, newCheckItemList);

                //更新原始的盘点数据
                long oldCheckId = zcCheckDao.queryOldCheckId(id);
                List<ZcCheckItem> reCheckItemList = zcCheckItemDao.queryRecheckId(oldCheckId);
                ZcCheck oldZcCheck = zcCheckDao.getById(oldCheckId);
                oldZcCheck.setId(oldCheckId);
                oldZcCheck.setDel(0);
                updateZcStatusAndCheckZcResult(oldZcCheck, reCheckItemList);
                //删除原来的复盘数据
                int result = zcCheckItemDao.deleteZcItem(id);
                if (result > 0) {
                    map.put("code", "0");
                    map.put("message", "成功");
                } else {
                    map.put("code", "500");
                    map.put("message", "失败");
                }
            }
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
        return map;
    }


    private int updateZcStatusAndCheckZcResult(ZcCheck zcCheck, List<ZcCheckItem> items) {
        int errorCount = 0;
        int normalCount = 0;
        List<Long> errorItemList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(items)) {
            for (int i = 0; i < items.size(); i++) {
                if ("1".equals(items.get(i).getResult())) {
                    //盘点正常
                    normalCount++;
                } else {
                    //盘点异常
                    errorCount++;
                    errorItemList.add(items.get(i).getId());
                }
            }
        }
        if (errorCount > 0) {
            //盘点异常
            zcCheck.setResult(2);
            //更新字表中的状态是异常状态
            zcCheckDao.updateItemStatus(errorItemList);
        } else {
            //盘点正常
            zcCheck.setResult(1);
        }
        logger.info("盘点完成之后的更新数据是：======》》》" + zcCheck);
        return zcCheckDao.update(zcCheck);
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateZcItemStatus")
    @ApiOperation(value = "盘点资产数据接口", tags = "盘点资产数据接口")
    public Map updateAssetsState(@RequestBody ZcCheckDTO zcCheckDTO) {
        Map finalMap = new HashMap();
        //更新找到的资产状态。
        Map map = new HashMap();
        if (StringUtils.isEmpty(zcCheckDTO.getEpcid())) {
            map.put("code", "500");
            map.put("message", "盘点的数据为空");
            return map;
        }
        int zcCheckId = zcCheckDTO.getZcCheckId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("epcid", zcCheckDTO.getEpcid());
        JSONArray array = new JSONArray();
        array.add(jsonObject);
        logger.info("获得的Json数组是===>{}", array.toJSONString());
        logger.info("获得的JsonString数组是===>{}", array.toString());
        try {
            String epcid = zcCheckDTO.getEpcid();
            List<ZcCheckItem> zcCheckItemList = JSONObject.parseArray(array.toString(), ZcCheckItem.class);
            if (!CollectionUtils.isEmpty(zcCheckItemList)) {
                logger.info("盘点数据的列表是：===========》》{}", zcCheckItemList);
                Map<String, Object> resultMap = insertProfitCheckItem(zcCheckItemList, zcCheckDTO.getZcCheckId());
                //   List<ZcCheckItem> profitList = (List) resultMap.get("profitList");
                List<ZcCheckItem> resultList = (List) resultMap.get("inCheckItemList");
                List<ZcCheckItem> notInCheckItem = (List) resultMap.get("notInCheckItemList");
                /**
                 *  提交的EpcID 不在资产信息表中存在
                 *  map.put("emptyEpcIdList", emptyEpcIdList);
                 *
                 *  提交的数据EpcId 在资产信息表中存在
                 * map.put("notEmptyEpcIdList", notEmptyEpcIdList);
                 *
                 *  提交的盘点数据在当前盘点单下面
                 * map.put("inCheckItemList", inCheckItemList);
                 *
                 * 提交的盘点数据不在当前的盘点单下面
                 *  map.put("notInCheckItemList", notInCheckItemList);
                 */
                if (CollectionUtils.isEmpty(resultList)) {
                    logger.info("盘点的数据全部是为空==={}", zcCheckDTO.getZcCheckId());
                    map.put("code", "0");
                    map.put("message", "成功");
                    map.put("data", null);
                    return map;
                }
                ZcCheckItem zcCheckItem = resultList.get(0);
                //没有盘点数据
                if (0 == zcCheckItem.getReCheck()) {
                    int result = updateZcCheck(resultList, zcCheckItem);
                    if (result > 0) {
                        //  finalMap.put("notCheck", queryNotFullCheckItem(zcCheckId));
                        //   finalMap.put("panying", queryPanYingCheckItem(zcCheckId));
                        map.put("code", "0");
                        map.put("message", "成功");
                        map.put("data", null);
                    } else {
                        map.put("code", "500");
                        map.put("message", "操作失败");
                        map.put("data", null);
                    }
                    return map;
                } else {
                    //再次盘点
                    updateZcCheck(resultList, zcCheckItem);
                    long checkId = zcCheckItem.getZcCheckId();
                    long oldCheckId = zcCheckDao.queryOldCheckId(checkId);
                    logger.info("查询到的原始id=====>{}", oldCheckId);
                    int status = 1;
                    int result = 0;
                    //更新
                    for (int i = 0; i < resultList.size(); i++) {
                        long zcId = resultList.get(i).getZcId();
                        result = zcCheckItemDao.updateItemStatusByZid(oldCheckId, zcId, status);
                    }
                    if (result > 0) {
                        // finalMap.put("notCheck", queryNotFullCheckItem(zcCheckId));
                        // finalMap.put("panying", queryPanYingCheckItem(zcCheckId));
                        map.put("code", "0");
                        map.put("message", "成功");
                        map.put("data", null);
                    } else {
                        map.put("code", "500");
                        map.put("message", "操作失败");
                        map.put("data", null);
                    }
                    return map;
                }
            } else {
                map.put("code", "500");
                map.put("message", "盘点的数据为空");
                map.put("data", null);
            }
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
        return map;
    }


    private int updateZcCheck(List<ZcCheckItem> zcCheckItemList, ZcCheckItem zcCheckItem) {
        //新的表单的数据
        for (int i = 0; i < zcCheckItemList.size(); i++) {
            zcCheckItemDao.update(zcCheckItemList.get(i));
        }
        //将盘点的父表更新为盘点状态盘点中
        long zcCheckId = zcCheckItem.getZcCheckId();
        int status = 1; //盘点中的状态
        return zcCheckDao.updateZcStatus(zcCheckId, status);
    }


    /**
     * 过滤提交的数据中是否存在盘盈的数据，如果有直接插入到数据库中
     * 如果没有的话 就是将资产的列表的返回之后更新资产的状态
     */
    private Map<String, Object> insertProfitCheckItem(List<ZcCheckItem> zcCheckItemList, long zcCheckId) {
        Map<String, Object> map = new HashMap<>();
        logger.info("【zcCheckId================>】" + zcCheckId);
        if (0 == zcCheckId) {
            return null;
        }
        List<String> emptyEpcIdList = new ArrayList<>();
        List<String> notEmptyEpcIdList = new ArrayList<>();
        List<ZcCheckItem> inCheckItemList = new ArrayList<>();
        List<ZcInfoDto> notInCheckItemList = new ArrayList<>();
        zcCheckItemList.forEach(k -> {
            String epcId = k.getEpcid();
            ZcInfoDto zcInfo = zcCheckDao.getByEpcId(epcId);
            if (null != zcInfo) {
                notEmptyEpcIdList.add(epcId);
                long zcId = zcInfo.getId();
                //查询盘点的资产信息是否在当前的盘点记录的盘点单里面
                ZcCheckItem zcCheck = zcCheckItemDao.findZcItemInZcCheckSubList(zcId, zcCheckId);
                if (null != zcCheck) {
                    //设置为盘点中
                    zcCheck.setResult("1");
                    inCheckItemList.add(zcCheck);
                } else {
                    notInCheckItemList.add(zcInfo);
                }
            } else {
                emptyEpcIdList.add(epcId);
            }
        });
        map.put("emptyEpcIdList", emptyEpcIdList);
        map.put("notEmptyEpcIdList", notEmptyEpcIdList);
        map.put("inCheckItemList", inCheckItemList);
        map.put("notInCheckItemList", notInCheckItemList);
        logger.info("EpcId为空的数据 emptyEpcIdList =========>{}," +
                        "EpcId不为空数据 notEmptyEpcIdList===>{}, " +
                        "当前盘点单存在的数据 inCheckItemList===>{},不在当前盘点单中的数据 notInCheckItemList ===>{}",
                emptyEpcIdList, notEmptyEpcIdList, inCheckItemList, notInCheckItemList);
        /**
         * 盘盈数据处理方式
         */
        if (!CollectionUtils.isEmpty(notInCheckItemList)) {
            List<ZcCheckItem> result = new ArrayList<>();
            notInCheckItemList.forEach(k -> {
                ZcCheckItem zcCheckItem = new ZcCheckItem();
                //盘点完成
                zcCheckItem.setResult("1");
                zcCheckItem.setProfit(1);
                zcCheckItem.setFinishTime(new Date());
                zcCheckItem.setZcId(k.getId());
                zcCheckItem.setZcCheckId(zcCheckId);
                zcCheckItem.setDel(0);
                //没有再次复盘
                zcCheckItem.setReCheck(0);
                result.add(zcCheckItem);
            });
            int profit = 1;
            zcCheckDao.batchZcItem(result);
            zcCheckDao.updateZcCheck(zcCheckId, profit);
        }
        return map;
    }


    private List<ZcEpcCheckItem> queryNotFullCheckItem(long zcCheckId) {
        List<ZcEpcCheckItem> zcCheckItemList = zcCheckItemDao.queryNotFullCheckItem(zcCheckId);
        return zcCheckItemList;
    }


    private List<ZcEpcCheckItem> queryPanYingCheckItem(long zcCheckId) {
        List<ZcEpcCheckItem> zcCheckItemList = zcCheckItemDao.queryPanYingCheckItem(zcCheckId);
        return zcCheckItemList;
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/checkRecordList")
    @ApiOperation(value = "盘点记录列表", tags = "盘点记录列表")
    public Map<String, Object> getChecked(@RequestBody ZcCheckRecordListDTO zcCheckRecordListDTO) {
        Map<String, Object> paramMap = new HashMap<>();
        SysUser sysUser = UserUtil.getLoginUser();
        Integer limit = zcCheckRecordListDTO.getLimit();
        Integer page = zcCheckRecordListDTO.getOffset();
        long deptId = sysUser.getDeptid();
        logger.info("获取档期登录用户的部门id:{}", deptId);
        int count = 0;
        Map<String, Object> map = new HashMap();
        List<com.itycu.server.model.ZcCheck> managerIdList = new ArrayList<>();
        //获取去区分不同的部门
        Dept dept = deptDao.getById(deptId);
        if (null != dept) {
            paramMap.put("statusList", Arrays.asList(2));// 盘点状态等于2
            paramMap.put("del", "0");
            // request.getParams().put("createBy", UserUtil.getLoginUser().getId());
            paramMap.put("deptId", deptId);
            paramMap.put("pid", dept.getPid());
            paramMap.put("deptType", dept.getC03());
            //获取全部的盘点数据
            paramMap.put("profit", null);

            if (("cwb").equals(dept.getC03())) {
                //财务登录当前账号
                logger.info("当前登录账号类型财务部门是======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(paramMap, page * limit - limit, limit);
            } else if ("zhb".equals(dept.getC03()) || "kjb".equals(dept.getC03())
                    || "yyb".equals(dept.getC03()) || "bwb".equals(dept.getC03())) {
                //四个部门的登录当前账
                logger.info("当前登录账号搜四个管理部门中之一:======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(paramMap, page * limit - limit, limit);
            } else {
                //其他部门或者是支行登录
                logger.info("当前登录账号类型是其他账号:======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(paramMap, page * limit - limit, limit);
            }
            createZcCheckTableInfo(managerIdList);
            logger.info("获得的查询总数是==={}", count);
        }
        map.put("data", managerIdList);
        map.put("code", "0");
        map.put("message", "");
        return map;
    }


    @PostMapping("/checkItemList/detail")
    @ApiOperation(value = "获取盘点记录的列表数据", tags = "获取盘点记录的列表数据")
    public Map<String, Object> getDetailInfo(@RequestBody ZcCheckRecordDetailListDTO zcCheckRecordDetailListDTO) {
        Map<String, Object> map = new HashMap();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("del", "0");
        Integer page = zcCheckRecordDetailListDTO.getLimit();
        Integer limit = zcCheckRecordDetailListDTO.getOffset();
        List list = zcCheckItemDao.list(paramMap, page * limit - limit, limit);
        map.put("data", list);
        map.put("code", "0");
        map.put("message", "");
        return map;
    }


    @PostMapping("/checkReport")
    @ApiOperation(value = "获取盘点报表--基本信息", tags = "获取盘点报表--基本信息")
    public Map<String, Object> getCheckReportInfo(@RequestBody ZcCheckFinishedDTO zcCheckFinishedDTO) {
        Map<String, Object> map = new HashMap();
        ZcCheckDetailReportVO zcCheckDetailReportVO = zcCheckDao.getCheckReportInfoById(zcCheckFinishedDTO.getId());
        if (null != zcCheckDetailReportVO) {
            zcCheckDetailReportVO.setReCheckFlag(reCheckFlag(zcCheckDetailReportVO.getReCheck()));
            zcCheckDetailReportVO.setStatusFlag(statusFlag(zcCheckDetailReportVO.getStatus()));
            zcCheckDetailReportVO.setStrResult(resultFlag(zcCheckDetailReportVO.getResult()));
            zcCheckDetailReportVO.setNormal(zcCheckDetailReportVO.getTotal() - zcCheckDetailReportVO.getErrorNum());
            zcCheckDetailReportVO.setCheckNum(appendName(5, zcCheckDetailReportVO.getCheckNum()));
            Dept dept = deptDao.getById(new Long(zcCheckDetailReportVO.getCheckDeptId()));
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String checkBH = dept.getJx() + "-PD" + year + "-" + appendName(5, String.valueOf(zcCheckDetailReportVO.getCheckNum()));
            zcCheckDetailReportVO.setCheckNum(checkBH);
        }
        map.put("data", zcCheckDetailReportVO);
        map.put("code", "0");
        map.put("message", "");
        return map;
    }


    private String reCheckFlag(int reCheck) {
        String reCheckFlag = "";
        if (reCheck == 1) {
            reCheckFlag = "已经复盘";
        } else {
            reCheckFlag = "没有复盘";
        }
        return reCheckFlag;
    }


    private String statusFlag(int status) {
        String reCheckFlag = "";
        if (status == 1) {
            reCheckFlag = "盘点中";
        } else if (status == 2) {
            reCheckFlag = "盘点完成";
        } else {
            reCheckFlag = "盘点中";
        }
        return reCheckFlag;
    }


    private String resultFlag(int result) {
        String reCheckFlag = "";
        if (result == 1) {
            reCheckFlag = "盘点正常";
        } else if (result == 2) {
            reCheckFlag = "盘点异常";
        } else {
            reCheckFlag = "盘点中";
        }
        return reCheckFlag;
    }


    @PostMapping("/checkReport/profitList")
    @ApiOperation(value = "获取盘点报表信息--盘盈/盘亏列表", tags = "获取盘点报表信息--盘盈/盘亏列表")
    public Map<String, Object> getCheckReportInfoProfitLossList(@RequestBody CheckItemReportDTO checkItemReportDTO) {
        Map<String, Object> map = new HashMap();
        try {

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("del", "0");
            paramMap.put("type", checkItemReportDTO.getType());
            paramMap.put("zcCheckId", checkItemReportDTO.getZcCheckId());
            Integer limit = checkItemReportDTO.getLimit();
            Integer page = checkItemReportDTO.getOffset();
            List<CheckItemReportVO> list = zcCheckItemDao.getCheckReportInfoProfitLossList(paramMap, page * limit - limit, limit);
            map.put("data", list);
            map.put("code", "0");
            map.put("message", "成功");
            return map;
        } catch (Exception e) {
            logger.error("获取盘点报表信息失败===>{}", e.getMessage());
            map.put("data", null);
            map.put("code", "0");
            map.put("message", "失败");
            return map;
        }
    }
}
