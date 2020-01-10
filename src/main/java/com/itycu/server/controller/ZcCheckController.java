package com.itycu.server.controller;

import com.itycu.server.dao.*;
import com.itycu.server.dto.SysUserDto;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.*;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.service.ZcCheckService;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import com.zaxxer.hikari.util.SuspendResumeLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/zcChecks")
@Api(tags = "盘点父表操作数据")
public class ZcCheckController {

    private static Logger logger = LoggerFactory.getLogger(ZcCheckController.class);

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


    @PostMapping
    @ApiOperation(value = "保存")
    @Transactional
    public Map save(@RequestBody ZcCheck zcCheck) {
        Map<String, Object> map = new HashMap();
        if (!StringUtils.isEmpty(zcCheck.getCheckDeptId())) {
            String[] ids = zcCheck.getCheckDeptId().split(",");
            for (int i = 0; i < ids.length; i++) {
                int createCount = zcCheckService.checkHasCreatedCount(UserUtil.getLoginUser().getId(), Long.parseLong(ids[i]));
                if (createCount > 0) {
                    map.put("code", "500");
                    map.put("message", "该盘点单已经创建过了");
                    return map;
                }
            }
        }
        int result = zcCheckService.insertZcTask(zcCheck);
        if (result > 0) {
            map.put("code", "0");
            map.put("message", "添加成功");
        } else {
            map.put("code", "500");
            map.put("message", "添加失败");
        }
        return map;
    }


    @GetMapping("/pdeSaveCheck")
    @ApiOperation(value = "PDE保存")
    @Transactional
    public Map pdeSaveCheck(int profit) {
        SysUser sysUser = UserUtil.getLoginUser();
        long deptid = sysUser.getDeptid();
        logger.info("获取用户部门信息是=={},部门id=={}", sysUser, deptid);
        Map map = zcCheckService.pdeSaveCheck(String.valueOf(deptid), profit);
        logger.info("获取用最后数据信息是=={},", map);
        return map;
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcCheck get(@PathVariable Long id) {
        return zcCheckDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcCheck update(@RequestBody ZcCheck zcCheck) {

        int result = zcCheckDao.update(zcCheck);
        if (result > 0) {
            logger.info("更新成功");
        }
        return zcCheck;
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/updateAssetsState")
    @ApiOperation(value = "更新资产状态", tags = "更新资产状态")
    public Map updateAssetsState(Long id) {
        //更新找到的资产状态。

        Map map = new HashMap();
        try {
            ZcCheck zcCheck = zcCheckDao.getById(id);
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


    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcCheckDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcCheck> list(PageTableRequest request) {
                return zcCheckDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map<String, Object> list2(PageTableRequest request) {
        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));

        Integer type = null;
        String typeStr = (String) request.getParams().get("type");
        if (StringUtils.isEmpty(typeStr)) {
            type = 3;
        } else {
            type = Integer.valueOf(typeStr);
        }
        // Integer  type  = Integer.valueOf((String) request.getParams().get("type"));
        SysUser sysUser = UserUtil.getLoginUser();
        long deptId = sysUser.getDeptid();
        int checked = 0;
        logger.info("获取的请求参数是：" + request.getParams());
        logger.info("获取档期登录用户的部门id:{}", deptId);
        int count = 0;
        List list = new ArrayList();
        Map<String, Object> map = new HashMap();
        List<com.itycu.server.model.ZcCheck> managerIdList = new ArrayList<>();
        //获取去区分不同的部门
        Dept dept = deptDao.getById(deptId);
        if (null != dept) {
            logger.info("获取的盘点数据是 type======>{}", type);

            if (type == 3 || null == type) {
                request.getParams().put("profit", null);
            } else {
                request.getParams().put("profit", type);
            }

            request.getParams().put("del", "0");
            request.getParams().put("createBy", UserUtil.getLoginUser().getId());
            request.getParams().put("deptId", deptId);
            request.getParams().put("pid", dept.getPid());
            request.getParams().put("deptType", dept.getC03());
            if (("cwb").equals(dept.getC03())) {
                request.getParams().put("statusList", Arrays.asList(0, 1));// 盘点状态不等于2
                //财务登录当前账号
                logger.info("当前登录账号类型财务部门是======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            } else if ("zhb".equals(dept.getC03()) || "kjb".equals(dept.getC03())
                    || "yyb".equals(dept.getC03()) || "bwb".equals(dept.getC03())) {
                //四个部门的登录当前账
                request.getParams().put("statusList", Arrays.asList(0, 1));//  盘点状态不等于2
                logger.info("当前登录账号搜四个管理部门中之一:======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            } else {
                //其他部门或者是支行登录
                request.getParams().put("statusList", Arrays.asList(0, 1));//  盘点状态不等于2
                logger.info("当前登录账号类型是其他账号:======>>{}", dept.getDeptname());
                logger.info("当前登录账号参数类型:======>>{}", request.getParams());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            }
            count = zcCheckDao.queryCountManagerDeptIds(request.getParams());
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


    @GetMapping("/listLog")
    @ApiOperation(value = "已完成的盘点列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map listLog(PageTableRequest request) {
        logger.info("已完成盘点列表请求参数:{}", request.getParams());
        SysUser sysUser = UserUtil.getLoginUser();
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));
        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        String createById = (String) request.getParams().get("glDept");
        if (!StringUtils.isEmpty(createById)) {
            SysUserDto sysUser1 = userDao.getByDeptId(createById);
            if (null != sysUser1) {
                request.getParams().put("glDept", sysUser1.getId());
            }
        }
        long deptId = sysUser.getDeptid();
        int checked = 0;
        logger.info("获取的请求参数是：" + request.getParams());
        logger.info("获取档期登录用户的部门id:{}", deptId);
        int count = 0;
        List list = new ArrayList();
        Map<String, Object> map = new HashMap();
        List<com.itycu.server.model.ZcCheck> managerIdList = new ArrayList<>();
        //获取去区分不同的部门
        Dept dept = deptDao.getById(deptId);
        if (null != dept) {
            request.getParams().put("statusList", Arrays.asList(2));// 盘点状态等于2
            request.getParams().put("del", "0");
            // request.getParams().put("createBy", UserUtil.getLoginUser().getId());
            request.getParams().put("deptId", deptId);
            request.getParams().put("pid", dept.getPid());
            request.getParams().put("deptType", dept.getC03());

            //获取全部的盘点数据
            request.getParams().put("profit", null);
            if (("cwb").equals(dept.getC03())) {

                //财务登录当前账号
                logger.info("当前登录账号类型财务部门是======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            } else if ("zhb".equals(dept.getC03()) || "kjb".equals(dept.getC03())
                    || "yyb".equals(dept.getC03()) || "bwb".equals(dept.getC03())) {
                //四个部门的登录当前账
                logger.info("当前登录账号搜四个管理部门中之一:======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            } else {
                //其他部门或者是支行登录
                logger.info("当前登录账号类型是其他账号:======>>{}", dept.getDeptname());
                logger.info("当前登录账号参数类型:======>>{}", request.getParams());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            }
            createZcCheckTableInfo(managerIdList);
            count = zcCheckDao.queryCountManagerDeptIds(request.getParams());
            logger.info("获得的查询总数是==={}", count);
        }
        map.put("data", managerIdList);
        map.put("count", count);
        map.put("code", "0");
        map.put("message", "");
        return map;
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


    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取盘点单的数据信息")
    @Transactional
    public Map<String, Object> zcCheckDanHaoInfo(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        logger.info("获取订单的盘点id=====》{}", id);
        ZcCheck zcCheck = zcCheckDao.getById(id);
        String jx = deptDao.getJxById(id);
        String pdNum = "";
        int count = zcCheck.getBh();
        if (count == 0) {
            pdNum = "00001";
        } else {
            StringBuilder sb = new StringBuilder();
            String countStr = String.valueOf(count);
            int length = countStr.length();
            if (length == 5) {
                pdNum = countStr;
            } else if (length == 4) {
                pdNum = sb.append("0").toString() + countStr;
            } else if (length == 3) {
                pdNum = sb.append("00").toString() + countStr;
            } else if (length == 2) {
                pdNum = sb.append("000").toString() + countStr;
            } else if (length == 1) {
                pdNum = sb.append("0000").toString() + countStr;
            }
        }
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String danHao = jx + "-PD" + year + "-" + pdNum;
        map.put("danhao", danHao);
        map.put("startUser", getSysUserInfo(zcCheck.getCreateBy()));
        map.put("endUser", checkUser(id));
        map.put("startTime", simpleDateInfo(zcCheck.getCreateTime()));
        map.put("endTime", simpleDateInfo(zcCheck.getCreateTime()));
        map.put("list", "");
        int normal = getNormal(id);
        int total = getTotal(id);
        // 查询部门资产
        HashMap<String, Object> params = new HashMap<>();
        params.put("syDeptId", zcCheck.getCheckDeptId());
        List<ZcInfoDto> zcInfoDtos = zcInfoDao.listByCondition(params);

        // 本部门盘点总数
        map.put("total", zcInfoDtos.size());
        //本部门差异数量
        map.put("chayi", Math.abs(zcInfoDtos.size() - normal));
        // 本部门正常数量
        map.put("normal", normal);
        map.put("account", getAccountName(String.valueOf(zcCheck.getCheckDeptId())));
        int error = total - normal;
        map.put("error", error);
        // 盘盈数据
        int profitCount = getProfitCount(id);
        int trueNum = normal - profitCount;
        // 实际本部门盘点数量
        map.put("trueNum", trueNum);
        if (profitCount > 0) {
            //标记是否是实物盘点的数据
            map.put("profit", "Y");
        } else {
            map.put("profit", "N");
        }
        map.put("profitCount", profitCount);
        // 本部门差异
        int bchayi = zcInfoDtos.size() - trueNum;
        map.put("bchayi", bchayi);


        //获取不同的部门的差异数据量
        List<ZcItemDeptCountInfo> zcInfoDtoList = zcCheckDao.queryPanYinZcList(id);
        if (!CollectionUtils.isEmpty(zcInfoDtoList)) {
            zcInfoDtoList.forEach(k -> {
                k.setActCount(k.getDiffCount());
                k.setDeptTotalCount(0);
            });
            map.put("deptList", zcInfoDtoList);
        }
        return map;
    }

    private int getProfitCount(long id) {
        int profitCount = zcCheckItemDao.queryProfitCount(id);
        return profitCount;
    }

    private int getNormal(long id) {
        int normalItem = zcCheckDao.getNormalItem(id);
        return normalItem;
    }

    private int getTotal(long id) {
        int total = zcCheckDao.getTotalZcItem(id);
        return total;
    }

    private String checkUser(long id) {
        String userName = zcCheckDao.queryZcCheckOperator(id);
        return userName;
    }

    private String getAccountName(String id) {
        String account = zcCheckDao.getAccountName(id);
        return account;
    }

    private String simpleDateInfo(Date date) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = simpleDateFormat.format(date);
        return result;
    }

    private String getSysUserInfo(long id) {
        SysUser sysUser = userDao.getById(id);
        if (null == sysUser) {
            return "";
        } else {
            return sysUser.getNickname();
        }
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    @Transactional
    public void delete(@PathVariable Long id) {
        zcCheckItemDao.deleteParent(id);
        zcCheckDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcCheck> listAll() {
        List<ZcCheck> list = zcCheckDao.listAll();
        return list;
    }


    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/reCheck/{checkId}")
    @ApiOperation(value = "盘点有异常的数据进行复盘")
    public Map<String, Object> reCheck(@PathVariable("checkId") Long checkId) {
        Map<String, Object> map = new HashMap();
        if (null == checkId) {
            map.put("data", null);
            map.put("code", 0);
            map.put("message", "");
            return map;
        } else {
            ZcCheck zcCheck = zcCheckDao.getById(checkId);
            ZcCheck newZcCheck = new ZcCheck();
            List<ZcCheckItem> zcCheckItemList = zcCheckItemDao.queryCheckFailItem(checkId);
            if (!CollectionUtils.isEmpty(zcCheckItemList)) {
                newZcCheck.setCheckDeptId(zcCheck.getCheckDeptId());
                newZcCheck.setCheckTime(zcCheck.getCheckTime());
                newZcCheck.setBz(zcCheck.getBz());
                newZcCheck.setCreateBy(UserUtil.getLoginUser().getId());
                newZcCheck.setDel(0);
                newZcCheck.setStatus(0);
                newZcCheck.setTotal(zcCheckItemList.size());
                newZcCheck.setProfit(zcCheck.getProfit());
                newZcCheck.setCheckUserId(zcCheck.getCheckUserId());
                //再次盘点数据
                newZcCheck.setReCheck(1);
                zcCheckDao.save(newZcCheck);
                long id = newZcCheck.getId();
                logger.info("老数据id======{},复盘的新数据id====={}", checkId, id);
                zcCheckDao.insertReal(id, checkId);
                for (int i = 0; i < zcCheckItemList.size(); i++) {
                    zcCheckItemList.get(i).setResult("0");
                    //复盘
                    zcCheckItemList.get(i).setReCheck(1);
                    zcCheckItemDao.update(zcCheckItemList.get(i));
                    ZcCheckItem model = createZcCheckItem(newZcCheck, zcCheckItemList.get(i).getZcId());

                    zcCheckItemDao.saveZcItem(model);
                }
                //表示存在复盘数据
                zcCheck.setReCheck(1);
                //待盘点
                //zcCheck.setStatus(0);
                //待盘点
                //zcCheck.setResult(0);
                zcCheckDao.update(zcCheck);
                map.put("data", null);
                map.put("code", 0);
                map.put("recheck", "1");
                map.put("message", "操作成功");
                return map;
            } else {
                map.put("data", null);
                map.put("code", 500);
                map.put("message", "暂无数据");
                return map;
            }
        }
    }


    private ZcCheckItem createZcCheckItem(ZcCheck zcCheck, Long id) {
        ZcCheckItem model = new ZcCheckItem();
        model.setZcCheckId(zcCheck.getId());
        model.setZcId(id);
        model.setReCheck(1);
        model.setDel(0);
        return model;
    }


    @PostMapping("/export")
    @ApiOperation(value = "导出盘点记录")
    public void export(PageTableRequest request, HttpServletResponse response) {
        logger.info("导出盘点记录请求参数:{}", request.getParams());
        SysUser sysUser = UserUtil.getLoginUser();
        Integer limit = Integer.MAX_VALUE;
        Integer page = 1;
        String createById = (String) request.getParams().get("glDept");
        if (!StringUtils.isEmpty(createById)) {
            SysUserDto sysUser1 = userDao.getByDeptId(createById);
            if (null != sysUser1) {
                request.getParams().put("glDept", sysUser1.getId());
            }
        }
        long deptId = sysUser.getDeptid();
        logger.info("获取的请求参数是：" + request.getParams());
        logger.info("获取档期登录用户的部门id:{}", deptId);
        int count = 0;
        List<ZcCheck> managerIdList = new ArrayList<>();
        //获取去区分不同的部门
        Dept dept = deptDao.getById(deptId);
        if (null != dept) {
            request.getParams().put("statusList", Arrays.asList(2));// 盘点状态等于2
            request.getParams().put("del", "0");
            // request.getParams().put("createBy", UserUtil.getLoginUser().getId());
            request.getParams().put("deptId", deptId);
            request.getParams().put("pid", dept.getPid());
            request.getParams().put("deptType", dept.getC03());
            if (("cwb").equals(dept.getC03())) {
                //财务登录当前账号
                logger.info("当前登录账号类型财务部门是======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            } else if ("zhb".equals(dept.getC03()) || "kjb".equals(dept.getC03())
                    || "yyb".equals(dept.getC03()) || "bwb".equals(dept.getC03())) {
                //四个部门的登录当前账
                logger.info("当前登录账号搜四个管理部门中之一:======>>{}", dept.getDeptname());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            } else {
                //其他部门或者是支行登录
                logger.info("当前登录账号参数类型:======>>{}", request.getParams());
                managerIdList = queryManagerDeptIds(request.getParams(), page * limit - limit, limit);
            }

            exportPanDian(response, managerIdList);

        }

    }


    private void exportPanDian(HttpServletResponse response, List<ZcCheck> managerIdList) {
        String fileName = "盘点记录";
        String[] headers = new String[]{
                "盘点单号", "盘点部门", "盘点人", "盘点时间",
                "资产总数", "正常数量", "差异数量", "盘点状态",
                "盘点结果", "是否已复盘", "盘点类型", "创建人",
                "创建时间", "更新时间", "备注"};
        List<Object[]> datas = new ArrayList<>(managerIdList.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ZcCheck zcInfo : managerIdList) {
            String checkTime = "";
            String createTime = "";
            String updateTime = "";
            String pdStatus = "";
            String pdResult = "";
            String reCheck = "";
            String pdProfit = "";

            long id = zcInfo.getId();
            ZcCheck zc = zcCheckDao.getById(id);
            Dept dept = deptDao.getById(Long.parseLong(zcInfo.getCheckDeptId()));
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String checkUserName = dept.getJx() + "-PD" + year + "-" + appendName(5, String.valueOf(zc.getBh()));
            if (zcInfo.getCheckTime() != null) {
                checkTime = s.format(zcInfo.getCheckTime());
            }
            if (zcInfo.getCreateTime() != null) {
                createTime = s1.format(zcInfo.getCreateTime());
            }
            if (zcInfo.getUpdateTime() != null) {
                updateTime = s1.format(zcInfo.getUpdateTime());
            }
            if (zcInfo.getStatus() == 0) {
                pdStatus = "待盘点";
            } else if (zcInfo.getStatus() == 1) {
                pdStatus = "盘点中";
            } else {
                pdStatus = "盘点完成";
            }
            if (zcInfo.getResult() == 1) {
                pdResult = "盘点正常";
            } else if (zcInfo.getResult() == 2) {
                pdResult = "盘点异常";
            }
            if (zcInfo.getReCheck() == 0) {
                reCheck = "否";
            } else if (zcInfo.getReCheck() == 1) {
                reCheck = "是";
            }
            if (zcInfo.getProfit() == 0) {
                pdProfit = "账务盘点";
            } else if (zcInfo.getProfit() == 1) {
                pdProfit = "实物盘点";
            }

            String userName = "";
            if (null != zc.getCheckUserId()) {
                userName = zcCheckDao.queryPandianUserName(zcInfo.getId(), zc.getCheckUserId());
            }
            int errorNum = 0;
            int normalNum = 0;
            List<ZcCheckItem> zcCheckItemList = zcCheckItemDao.queryCheckFailItem(id);
            if (null != zcCheckItemList) {
                errorNum = (zcCheckItemList.size());
                normalNum = (zc.getTotal() - zcCheckItemList.size());
            } else {
                errorNum = (0);
                normalNum = (zc.getTotal());
            }
            Object[] objects = new Object[]{
                    checkUserName, zcInfo.getCheckDeptName(), userName, checkTime,
                    zcInfo.getTotal(), normalNum, errorNum, pdStatus,
                    pdResult, reCheck, pdProfit, zcInfo.getCreator(), createTime, updateTime, zcInfo.getBz()
            };
            datas.add(objects);
        }
        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
        logger.info("资产档案导出成功");
    }


}
