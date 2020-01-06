package com.itycu.server.controller;

import java.util.List;

import com.itycu.server.dao.FlowmemberDao;
import com.itycu.server.model.Flowmember;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/flowmembers")
public class FlowmemberController {

    @Autowired
    private FlowmemberDao flowmemberDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Flowmember save(@RequestBody Flowmember flowmember) {
        flowmemberDao.save(flowmember);

        return flowmember;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Flowmember get(@PathVariable Long id) {
        return flowmemberDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Flowmember update(@RequestBody Flowmember flowmember) {
        flowmemberDao.update(flowmember);

        return flowmember;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return flowmemberDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Flowmember> list(PageTableRequest request) {
                return flowmemberDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        flowmemberDao.delete(id);
    }
}
