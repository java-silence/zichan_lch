package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dao.FlowDocDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.FlowDocService;
import com.itycu.server.utils.UserUtil;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.model.FlowDoc;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/flowDocs")
public class FlowDocController {

    @Autowired
    private FlowDocDao flowDocDao;

    @Autowired
    private FlowDocService flowDocService;

    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public FlowDoc save(@RequestBody FlowDoc flowDoc) {
        return flowDocService.save(flowDoc);
    }

    @PutMapping("/todo")
    @ApiOperation(value = "修改")
    @Transactional
    public TodoDto todo(@RequestBody TodoDto todoDto) {
        return flowDocService.todo(todoDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public FlowDoc get(@PathVariable Long id) {
        return flowDocDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public FlowDoc update(@RequestBody FlowDoc flowDoc) {
        flowDoc.setUpdateby(UserUtil.getLoginUser().getId());
        flowDocDao.update(flowDoc);
        return flowDoc;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public FlowDoc audit(@PathVariable Long id) {
        FlowDoc flowDoc = flowDocDao.getById(id);

        flowDoc.setAuditby(UserUtil.getLoginUser().getId());
        flowDoc.setAuditTime(new Date());
        flowDoc.setStatus(1);
        flowDocDao.update(flowDoc);
        return flowDoc;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public FlowDoc unaudit(@PathVariable Long id) {
        FlowDoc flowDoc = flowDocDao.getById(id);

        flowDoc.setAuditby(null);
        flowDoc.setAuditTime(null);
        flowDoc.setStatus(0);
        flowDocDao.update(flowDoc);
        return flowDoc;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return flowDocDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<FlowDoc> list(PageTableRequest request) {
                return flowDocDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:shengjiangji:querydept") >= 1){
                request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
            }

            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = flowDocDao.count(request.getParams());

            List list = flowDocDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        flowDocDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<FlowDoc> listAll() {
        List<FlowDoc> list = flowDocDao.listAll();
        return list;
    }
}
