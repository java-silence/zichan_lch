package com.itycu.server.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.*;
import com.itycu.server.dto.ZcBfCheckDto;
import com.itycu.server.dto.ZcBfDto;
import com.itycu.server.model.*;
import com.itycu.server.service.ZcBfService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.itycu.server.page.table.PageTableRequest;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/zcBfs")
public class ZcBfController {

    private static Logger logger = LoggerFactory.getLogger(ZcBfController.class);

    public static final String bfActionUrl = "zcbf/auditZcBf.html";
    public static final String cwbfActionUrl = "zcbf/cwauditZcBf.html";

    @Autowired
    private ZcBfDao zcBfDao;

    @Autowired
    private ZcBfService zcBfService;

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private FlowTodoItemDao flowTodoItemDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private ZcBfItemDao zcBfItemDao;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存报废申请单")
    @Transactional
    public Map save(@RequestBody ZcBfDto zcBfDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Long id = zcBfDto.getId();
            if (!ObjectUtils.isEmpty(id)) {
                // 修改
                return zcBfService.updateZcBf(zcBfDto);
            }
            return zcBfService.save(zcBfDto);
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @LogAnnotation
    @PostMapping("/check")
    @ApiOperation(value = "资产报废审核")
    @Transactional
    public Map check(@RequestBody ZcBfCheckDto zcBfCheckDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            zcBfService.checkNew(zcBfCheckDto);
            map.put("code", "0");
            map.put("msg", "审核成功");
            return map;
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @LogAnnotation
    @PostMapping("/cwcheck")
    @ApiOperation(value = "资产报废审核")
    @Transactional
    public Map cwcheck(@RequestBody ZcBfCheckDto zcBfCheckDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            Long id = zcBfService.cwcheck(zcBfCheckDto);
            map.put("zcbfCwItemId", id);
            map.put("code", "0");
            map.put("msg", "审核成功");
            return map;
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @LogAnnotation
    @PostMapping("/submitToCw")
    @ApiOperation(value = "资产报废审核")
    @Transactional
    public Map submitToCw(@RequestBody ZcBfCheckDto zcBfCheckDto) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            zcBfService.submitToCw(zcBfCheckDto);
            map.put("code", "0");
            map.put("msg", "提交成功");
            return map;
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcBf get(@PathVariable Long id) {
        return zcBfDao.getById(id);
    }

    @GetMapping("/print")
    @ApiOperation(value = "根据id获取")
    public Map print(@RequestParam(value = "id", required = false) Long id) {
        Map map = new HashMap<>();
        long deptid = UserUtil.getLoginUser().getDeptid();
        Dept currentDept = deptDao.getById(deptid);
        Dept pdept = deptDao.getById(currentDept.getPid());
        Map<String, Object> shenqing = new HashMap<>();
        HashMap<String, Object> bf = zcBfDao.getZcBfDetail(id);
        shenqing.put("updateTime", bf.get("createTime"));
        shenqing.put("username", bf.get("username"));
        shenqing.put("nickname", bf.get("nickname"));
        shenqing.put("deptname", bf.get("deptname"));
        List<Map<String, Object>> shenpi = todoDao.findShenheList(id, bfActionUrl, "1");
        List<Map<String, Object>> shenhe = todoDao.findShenheList(id, cwbfActionUrl, "2");
        map.put("shenqing", shenqing);
        map.put("shenpi", shenpi);
        map.put("shenhe", shenhe);
        map.put("pdeptName", pdept.getDeptname());
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }

    /**
     * 财务获取提交和未提交的部门信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getSubmitDept")
    @ApiOperation(value = "根据id获取")
    public Map getSubmitDept(@RequestParam(value = "id", required = false) Long id) {

        Todo todo = todoDao.getById(id);
        Date updateTime = todo.getUpdateTime();
        Map map = new HashMap();
        // 用户部门ID
        long deptid = UserUtil.getLoginUser().getDeptid();
        Dept dept = deptDao.getById(deptid);
        // 该商行下所有的部门
        List<Dept> deptList = deptDao.listByPid(dept.getPid());
        List<Long> deptids = deptList.stream().map(e -> e.getId()).collect(Collectors.toList());
        // 全部提交的使用部门
        List<ZcBfItem> zcBfItemList = zcBfItemDao.listSyDeptThisYear(deptids, updateTime);
        List<Long> haveids = zcBfItemList.stream().map(e -> e.getSyDeptId()).collect(Collectors.toList());
        // 未提交部门
        List<Dept> no = new ArrayList<>();
        // 已提交部门
        List<Dept> have = new ArrayList<>();
        for (Dept dept1 : deptList) {
            Long did = dept1.getId();
            if (haveids.contains(did)) {
                have.add(dept1);
            } else {
                no.add(dept1);
            }
        }
        List<String> nonames = no.stream().map(e -> e.getDeptname()).collect(Collectors.toList());
        List<String> havenames = have.stream().map(e -> e.getDeptname()).collect(Collectors.toList());
        String nonames1 = String.join("，", nonames);
        String havenames2 = String.join("，", havenames);
        map.put("have", have);
        map.put("no", no);
        map.put("nonames", nonames);
        map.put("havenames", havenames);
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }

    @GetMapping("/getZcBfDetail")
    @ApiOperation(value = "根据id获取")
    public Map getZcBfDetail(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "bfzcid", required = false) Long bfzcid) {

        Map map = new HashMap();
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
            map.put("deptSize", size);
            map.put("haveSubmit", number);
            map.put("remain", size - number);
            // 已有的是否全部完成
            List<FlowTodoItem> flowTodoItems = flowTodoItemDao.listByToDoId(id);
            ArrayList<Long> list = new ArrayList<>();
            TreeSet<Long> ids = new TreeSet<>();
            for (FlowTodoItem flowTodoItem : flowTodoItems) {
                ids.add(flowTodoItem.getFlowItemId());
            }
            list.addAll(ids);
            // 统计审核部门是否全部完成
            int result = zcBfItemDao.countByIds(list);
            if (result > 0) {
                map.put("result", 1);
            } else {
                map.put("result", 0);
            }
        }
        // 查询待办的信息
        Todo todo = todoDao.getById(id);
        // 查询子项
        HashMap<String, Object> params = new HashMap<>();
        params.put("flowTodoId", todo.getId());
        params.put("status", "0");
        int count = flowTodoItemDao.count(params);
        HashMap<String, Object> data = zcBfDao.getZcBfDetail(bfzcid);
        data.put("type", todo.getType());
        data.put("num", count);
        map.put("data", data);
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcBf update(@RequestBody ZcBf zcBf) {
        zcBf.setCreateBy(UserUtil.getLoginUser().getId());
        zcBfDao.update(zcBf);
        return zcBf;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcBf audit(@PathVariable Long id) {
        ZcBf zcBf = zcBfDao.getById(id);
        zcBfDao.update(zcBf);
        return zcBf;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcBf unaudit(@PathVariable Long id) {
        ZcBf zcBf = zcBfDao.getById(id);
        zcBfDao.update(zcBf);
        return zcBf;
    }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request, HttpServletRequest httpServletRequest) {

        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:bfcheck:apply") > 0) {
            request.getParams().put("applyUserId", UserUtil.getLoginUser().getId());
            request.getParams().put("type", "user");
        }
        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:bfcheck:sh") > 0) {
            request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
            request.getParams().put("type", "gl");
        }
        Map map = new HashMap();
        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request, httpServletRequest);

        // todo 此处需要添加权限

        int count = zcBfDao.count(request.getParams());
        List<Map<String, Object>> list = zcBfDao.listZcbf(request.getParams(), page * limit - limit, limit);
        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcBfDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcBf> listAll() {
        List<ZcBf> list = zcBfDao.listAll();
        return list;
    }

    /**
     * 报废流程启动
     *
     * @param bfId
     */
    @GetMapping("/startBfProcess/{id}")
    public Map startBfProcess(@PathVariable("id") String bfId) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            zcBfService.startProcess(bfId);
            map.put("code", "0");
            map.put("msg", "启动成功");
            return map;
        } catch (Exception e) {
            map.put("code", "1");
            map.put("msg", e.getMessage());
            return map;
        }
    }

    @GetMapping("/listZcBfRecord")
    @ApiOperation(value = "列表")
//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map listZcBfRecord(PageTableRequest request, HttpServletRequest httpServletRequest) {
        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:bffinish:apply") > 0) {
            //request.getParams().put("applyUserId", UserUtil.getLoginUser().getId());
        }
        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:bffinish:gl") > 0) {
            //request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
        }
        Map map = new HashMap();
        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request, httpServletRequest);

        int count = zcBfDao.countListZcBfRecord(request.getParams());
        List<Map<String, Object>> list = zcBfDao.listZcBfRecord(request.getParams(), page * limit - limit, limit);

        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }


    @PostMapping("/listZcBfRecord/export")
    @ApiOperation(value = "报废记录数据导出")
    public void listZcBfRecordExport(PageTableRequest request, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        Integer page = 1;
        Integer limit = Integer.MAX_VALUE;
        DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
        List<Map<String, Object>> list = zcBfDao.listZcBfRecord(request.getParams(), page * limit - limit, limit);
        String fileName = "报废记录";
        String[] headers = new String[]{
                "资产追溯码", "资产编码",  "处置单号","资产名称","资产分类",
                "原价值", "净值",
                "报废申请日期", "报废完成日期", "报废描述", "报废原因",
                "规格", "型号", "使用部门", "管理部门", "鉴定意见",
                "巡检周期", "保养周期", "资产来源",
                "累计折旧", "本年折旧", "减值准备",
                "净额", "净残值率", "净残值", "开始使用日期",
                "剩余月数", "已提计数"
        };
        List<Object[]> datas = new ArrayList<>(list.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Map<String, Object> zcInfo : list) {
            Object[] objects = new Object[]{
                    zcInfo.get("epcid"), zcInfo.get("zcCodenum"), zcInfo.get("codeNum"), zcInfo.get("zcName"),zcInfo.get("name"),
                    zcInfo.get("originalValue"), zcInfo.get("netvalue"), zcInfo.get("applyTime"), zcInfo.get("finishTime"),
                    zcInfo.get("bfdes"), zcInfo.get("bfReason"), zcInfo.get("specification"), zcInfo.get("model"),
                    zcInfo.get("sydeptname"), zcInfo.get("gldeptname"), zcInfo.get("identifyContent"), zcInfo.get("storeAddress"),
                    zcInfo.get("inspectTime"), zcInfo.get("warrantyperiod"), zcInfo.get("zcFrom"), zcInfo.get("ljZhejiu"),
                    zcInfo.get("bnZhejiu"), zcInfo.get("jzzb"), zcInfo.get("net"), zcInfo.get("netResidualRate"),
                    zcInfo.get("netResidualValue"), zcInfo.get("startUseTime"), zcInfo.get("usemonths"), zcInfo.get("haveCount")
            };
            datas.add(objects);
        }
        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
        logger.info("资产档案导出成功");
    }
}
