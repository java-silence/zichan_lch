package com.itycu.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dao.FlowDao;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.model.Flow;
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
@RequestMapping("/flows")
public class FlowController {

    @Autowired
    private FlowDao flowDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Flow save(@RequestBody Flow flow) {
        flowDao.save(flow);

        return flow;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Flow get(@PathVariable Long id) {
        return flowDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Flow update(@RequestBody Flow flow) {
        flowDao.update(flow);

        return flow;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return flowDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Flow> list(PageTableRequest request) {
                return flowDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request) {
        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = flowDao.count(request.getParams());

        List list = flowDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        flowDao.delete(id);
    }
}
