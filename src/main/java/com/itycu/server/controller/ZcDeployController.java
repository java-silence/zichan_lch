package com.itycu.server.controller;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.*;
import com.itycu.server.dto.ZcBfCheckDto;
import com.itycu.server.dto.ZcDeployCheckDto;
import com.itycu.server.dto.ZcDeployDto;
import com.itycu.server.model.Dept;
import com.itycu.server.model.ZcCheck;
import com.itycu.server.model.ZcDeploy;
import com.itycu.server.model.ZcDeployItem;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.service.ZcDeployService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/zcDeploys")
public class ZcDeployController {


    private static Logger logger = LoggerFactory.getLogger(ZcDeployController.class);

    public static final String actionUrl = "zcdeploy/auditZcDeploy.html";

    @Autowired
    private ZcDeployDao zcDeployDao;

    @Autowired
    private ZcDeployItemDao zcDeployItemDao;

    @Autowired
    private ZcDeployService zcDeployService;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private DeptDao deptDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Map save(@RequestBody ZcDeployDto zcDeployDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Long id = zcDeployDto.getId();
            if (!ObjectUtils.isEmpty(id)) {
                // 修改
                zcDeployService.updateZcDeploy(zcDeployDto);
            } else {
                zcDeployService.save(zcDeployDto);
            }
            map.put("code", "0");
            map.put("msg", "启动成功");
            return map;
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @LogAnnotation
    @PostMapping("/check")
    @ApiOperation(value = "资产调配审核")
    @Transactional
    public Map check(@RequestBody ZcDeployCheckDto zcDeployCheckDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String status = zcDeployService.check(zcDeployCheckDto);
            map.put("code", "0");
            map.put("msg", "审核成功");
            map.put("status", status);
            return map;
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Map get(@PathVariable Long id) {

        long deptid = UserUtil.getLoginUser().getDeptid();
        Dept currentDept = deptDao.getById(deptid);
        Dept pdept = deptDao.getById(currentDept.getPid());
        Map map = new HashMap<>();
        ZcDeploy zcDeploy = zcDeployDao.getById(id);
        // 申请信息
        Map<String, Object> shenqing = new HashMap<>();
        HashMap<String, Object> zcDeployDetail = zcDeployDao.getZcDeployDetail(id);
        shenqing.put("updateTime", zcDeployDetail.get("createTime"));
        shenqing.put("username", zcDeployDetail.get("username"));
        shenqing.put("nickname", zcDeployDetail.get("nickname"));
        shenqing.put("deptname", zcDeployDetail.get("uDeptName"));
        List<Map<String, Object>> diaochu = todoDao.findShenheList(id, actionUrl, "1");
        List<Map<String, Object>> diaoru = todoDao.findShenheList(id, actionUrl, "2");
        List<Map<String, Object>> shenhe = todoDao.findShenheList(id, actionUrl, "3");
        map.put("zcDeploy", zcDeploy);
        map.put("shenqing", shenqing);
        map.put("diaoru", diaoru);
        map.put("diaochu", diaochu);
        map.put("shenhe", shenhe);
        map.put("pdeptName", pdept.getDeptname());
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcDeploy update(@RequestBody ZcDeploy zcDeploy) {
        zcDeployDao.update(zcDeploy);
        return zcDeploy;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcDeploy audit(@PathVariable Long id) {
        ZcDeploy zcDeploy = zcDeployDao.getById(id);
        zcDeploy.setStatus(1);
        zcDeployDao.update(zcDeploy);
        return zcDeploy;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcDeploy unaudit(@PathVariable Long id) {
        ZcDeploy zcDeploy = zcDeployDao.getById(id);
        zcDeploy.setStatus(0);
        zcDeployDao.update(zcDeploy);
        return zcDeploy;
    }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request, HttpServletRequest httpServletRequest) {

        request.getParams().put("applyUserId", UserUtil.getLoginUser().getId());
        Map map = new HashMap();
        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
        // todo 此处需要添加权限
        int count = zcDeployDao.count(request.getParams());
        List<Map<String, Object>> list = zcDeployDao.listZcDeploy(request.getParams(), page * limit - limit, limit);
        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcDeployDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcDeploy> list(PageTableRequest request) {
                return zcDeployDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {


        Map map = new HashMap();

        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));

        int count = zcDeployDao.count(request.getParams());

        List list = zcDeployDao.list(request.getParams(), page * limit - limit, limit);

        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("msg", "");

        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        // 删除子表
        zcDeployItemDao.deleteByZcDeployId(id);
        zcDeployDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcDeploy> listAll() {
        List<ZcDeploy> list = zcDeployDao.listAll();
        return list;
    }

    @GetMapping("/getZcDeployDetail")
    @ApiOperation(value = "根据id获取")
    public Map getZcDeployDetail(@RequestParam(value = "deployId", required = false) Long deployId) {
        Map map = new HashMap();
        HashMap<String, Object> data = zcDeployDao.getZcDeployDetail(deployId);
        map.put("data", data);
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }

    /**
     * 调配流程启动
     *
     * @param zcDeployId
     */
    @GetMapping("/startDeployProcess/{id}")
    public Map startDeployProcess(@PathVariable("id") String zcDeployId) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            zcDeployService.startProcess(zcDeployId);
            map.put("code", "0");
            map.put("msg", "启动成功");
            return map;
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @GetMapping("/listZcDeployRecord")
    @ApiOperation(value = "列表")
    public Map listZcDeployRecord(PageTableRequest request, HttpServletRequest httpServletRequest) {

        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:dpfinish:apply") > 0) {
            //request.getParams().put("applyUserId", UserUtil.getLoginUser().getId());
        }
        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:dpfinish:sy") > 0) {
            //request.getParams().put("syNewAndOldDeptId", UserUtil.getLoginUser().getDeptid());
        }
        // 管理部门权限查看
        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:dpfinish:gl") > 0) {
            //request.getParams().put("applyUserId", "");
            //request.getParams().put("syNewAndOldDeptId", "");
            //request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
        }
        Map map = new HashMap();
        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
        int count = zcDeployDao.countListZcDeployRecord(request.getParams());
        List<Map<String, Object>> list = zcDeployDao.listZcDeployRecord(request.getParams(), page * limit - limit, limit);
        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }


    @PostMapping("/export")
    @ApiOperation(value = "列表")
    public void exportData(PageTableRequest request, HttpServletRequest httpServletRequest, HttpServletResponse response) {

        Integer page = 1;
        Integer limit = Integer.MAX_VALUE;
        DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
        List<Map<String, Object>> list = zcDeployDao.listZcDeployRecord(request.getParams(), page * limit - limit, limit);


        String fileName = "调配记录";
        String[] headers = new String[]{
                "资产追溯码", "资产编码", "资产名称", "调配单号",
                "调配时间", "管理部门", "调入部门", "调出部门",
                "规格", "型号", "存放地点", "巡检周期",
                "保养周期", "资产来源", "原价值", "累计折旧",
                "本年折旧", "净值", "减值准备", "净额",
                "净残值率", "净残值", "开始使用日期", "剩余月数",
                "已计提数"
        };
        List<Object[]> datas = new ArrayList<>(list.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map zcInfo : list) {
            String applyTime = (String) zcInfo.get("applyTime");
            String useTime = "";
            if (zcInfo.get("startUseTime") != null && !StringUtils.isEmpty(zcInfo.get("startUseTime"))) {
                useTime = s.format((Date) zcInfo.get("startUseTime"));
            }
            Object[] objects = new Object[]{
                    zcInfo.get("epcid"), zcInfo.get("deployNum"), zcInfo.get("zcName"), zcInfo.get("zcCodenum"),
                    applyTime, zcInfo.get("gldeptname"), zcInfo.get("frontDeptname"), zcInfo.get("backDeptname"),
                    zcInfo.get("specification"), zcInfo.get("model"), zcInfo.get("storeAddress"), zcInfo.get("inspectTime"),
                    zcInfo.get("warrantyperiod"), zcInfo.get("zcFrom"), zcInfo.get("originalValue"), zcInfo.get("ljZhejiu"),
                    zcInfo.get("bnZhejiu"), zcInfo.get("netvalue"), zcInfo.get("jzzb"), zcInfo.get("net"),
                    zcInfo.get("netResidualRate"), zcInfo.get("netResidualValue"), useTime, zcInfo.get("usemonths"),
                    zcInfo.get("haveCount")
            };
            datas.add(objects);
        }
        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
        logger.info("资产档案导出成功");
    }

}
