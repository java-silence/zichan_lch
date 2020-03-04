package com.itycu.server.controller;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.ZcRepairDao;
import com.itycu.server.dto.ZcRepairDto;
import com.itycu.server.model.Dept;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.ZcRepair;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.ZcRepairService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zcRepairs")
public class ZcRepairController {

    @Autowired
    private ZcRepairDao zcRepairDao;

    @Autowired
    private ZcRepairService zcRepairService;

    @Autowired
    private DeptDao deptDao;


    /**
     * 资产维修列表(添加时搜索)
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/zcInfoList")
    @ApiOperation(value = "列表")
    public Map zcInfoList(PageTableRequest request, HttpServletRequest httpServletRequest) {
        Map map = new HashMap();
        // 封装查询条件
        request.getParams().put("del","0");
        request.getParams().put("useStatus","1");
        SysUser sysUser = UserUtil.getLoginUser();
        Dept dept = deptDao.getById(sysUser.getDeptid());
        String zhfhgl = dept.getZhfhgl();
        String c03 = dept.getC03();
        if ( "cwb".equalsIgnoreCase(c03) ) {
            // 财务部  & 使用部门
            request.getParams().put("deptType","sy");
            if (!ObjectUtils.isEmpty(request.getParams().get("firstSearch"))) {
                // 初始搜索
                request.getParams().put("syDeptId", sysUser.getDeptid());
            }
        }else if ( "1".equalsIgnoreCase(zhfhgl) || "2".equalsIgnoreCase(zhfhgl) ) {
            // 使用部门
            request.getParams().put("deptType","sy");
            // 初始搜索
            request.getParams().put("syDeptId", sysUser.getDeptid());
        }else if ( "3".equalsIgnoreCase(zhfhgl) ) {
            // 管理部门
            request.getParams().put("deptType","gl");
            request.getParams().put("glDeptId", sysUser.getDeptid());
            if (!ObjectUtils.isEmpty(request.getParams().get("firstSearch"))) {
                // 初始搜索
                request.getParams().put("syDeptId", sysUser.getDeptid());
            }
        }
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
        int count = zcRepairDao.zcRepairCount(request.getParams());
        List list = zcRepairDao.zcRepairCList(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存")
    @PreAuthorize("hasAuthority('sys:zcRepair:add')")
    @Transactional
    public ZcRepairDto save(@RequestBody ZcRepairDto zcRepair) {
        return zcRepairService.save(zcRepair);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcRepairDto get(@PathVariable Long id) {
        return zcRepairDao.getById(id);
    }

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAuthority('sys:zcRepair:edit')")
    @Transactional
    public ZcRepairDto update(@RequestBody ZcRepairDto zcRepair) {
        return zcRepairService.update(zcRepair);
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcRepair audit(@PathVariable Long id) {
        ZcRepair zcRepair = zcRepairDao.getById(id);
        zcRepairDao.update(zcRepair);
        return zcRepair;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcRepair unaudit(@PathVariable Long id) {
        ZcRepair zcRepair = zcRepairDao.getById(id);
        zcRepairDao.update(zcRepair);
        return zcRepair;
    }


    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    public Map list2(PageTableRequest request) {
        //if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcRepair:querydept") > 0){
        //    request.getParams().put("applyDeptId", UserUtil.getLoginUser().getDeptid());
        //}
        Map map = new HashMap();
        request.getParams().put("del","0");
        SysUser sysUser = UserUtil.getLoginUser();
        Dept dept = deptDao.getById(sysUser.getDeptid());
        String zhfhgl = dept.getZhfhgl();
        String c03 = dept.getC03();
        if ( "cwb".equalsIgnoreCase(c03) ) {
            // 财务部
        }else if ( "1".equalsIgnoreCase(zhfhgl) || "2".equalsIgnoreCase(zhfhgl) ) {
            // 使用部门
            request.getParams().put("syDeptId", sysUser.getDeptid());
        }else if ( "3".equalsIgnoreCase(zhfhgl) ) {
            // 管理部门
            request.getParams().put("glDeptId", sysUser.getDeptid());
            request.getParams().put("syDeptId", sysUser.getDeptid());
        }
        try {
            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
            int count = zcRepairDao.count(request.getParams());
            List list = zcRepairDao.list(request.getParams(), page*limit-limit, limit);
            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","成功");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            map.put("code","1");
            map.put("msg","失败");
        }
        return map;
    }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAuthority('sys:zcRepair:del')")
    public void delete(@PathVariable Long id) {
        zcRepairService.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcRepair> listAll() {
        List<ZcRepair> list = zcRepairDao.listAll();
        return list;
    }

//    @PutMapping("/todo")
//    @ApiOperation(value = "审批")
//    @Transactional
//    public TodoDto todo(@RequestBody TodoDto todo) {
//        return zcRepairService.todo(todo);
//    }

    @LogAnnotation
    @PostMapping("/check")
    @ApiOperation(value = "资产报修审核")
    @Transactional
    public ZcRepairDto check(@RequestBody ZcRepairDto zcRepairDto) {
        return zcRepairService.check(zcRepairDto);
    }
}
