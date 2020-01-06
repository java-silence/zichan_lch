package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dto.ZcRepairDto;
import com.itycu.server.service.ZcRepairService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.ZcRepairDao;
import com.itycu.server.model.ZcRepair;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zcRepairs")
public class ZcRepairController {

    @Autowired
    private ZcRepairDao zcRepairDao;

    @Autowired
    private ZcRepairService zcRepairService;

    @Autowired
    private PermissionDao permissionDao;

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

//        zcRepair.setAuditby(UserUtil.getLoginUser().getId());
//        zcRepair.setAuditTime(new Date());
//        zcRepair.setStatus("1");
        zcRepairDao.update(zcRepair);
        return zcRepair;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcRepair unaudit(@PathVariable Long id) {
        ZcRepair zcRepair = zcRepairDao.getById(id);

//        zcRepair.setAuditby(null);
//        zcRepair.setAuditTime(null);
//        zcRepair.setStatus("0");
        zcRepairDao.update(zcRepair);
        return zcRepair;
    }


        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcRepair:querydept") > 0){
                request.getParams().put("applyDeptId", UserUtil.getLoginUser().getDeptid());
            }

            Map map = new HashMap();
            request.getParams().put("del","0");
            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = zcRepairDao.count(request.getParams());

            List list = zcRepairDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

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
