package com.itycu.server.controller;

import com.itycu.server.dao.BudgetBasicDao;
import com.itycu.server.model.BudgetBasic;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础预算控制
 */
@RestController
@RequestMapping("/budgetBasics")
public class BudgetBasicController {

    @Autowired
    private BudgetBasicDao budgetBasicDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public BudgetBasic save(@RequestBody BudgetBasic budgetBasic) {
        budgetBasicDao.save(budgetBasic);
        return budgetBasic;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public BudgetBasic get(@PathVariable Long id) {
        return budgetBasicDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public BudgetBasic update(@RequestBody BudgetBasic budgetBasic) {
        budgetBasicDao.update(budgetBasic);
        return budgetBasic;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public BudgetBasic audit(@PathVariable Long id) {
        BudgetBasic budgetBasic = budgetBasicDao.getById(id);
        budgetBasic.setStatus(1);
        budgetBasicDao.update(budgetBasic);
        return budgetBasic;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public BudgetBasic unaudit(@PathVariable Long id) {
        BudgetBasic budgetBasic = budgetBasicDao.getById(id);
        budgetBasic.setStatus(0);
        budgetBasicDao.update(budgetBasic);
        return budgetBasic;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return budgetBasicDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<BudgetBasic> list(PageTableRequest request) {
                return budgetBasicDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    //@PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {
        Map map = new HashMap();
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        int count = budgetBasicDao.count(request.getParams());
        List list = budgetBasicDao.list(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        budgetBasicDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<BudgetBasic> listAll() {
        List<BudgetBasic> list = budgetBasicDao.listAll();
        return list;
    }

}
