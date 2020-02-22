package com.itycu.server.controller;

import com.itycu.server.dao.BudgetMaintainItemDao;
import com.itycu.server.model.BudgetMaintainItem;
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
 * 维保预算子项
 */
@RestController
@RequestMapping("/budgetMaintainItems")
public class BudgetMaintainItemController {

    @Autowired
    private BudgetMaintainItemDao budgetMaintainItemDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public BudgetMaintainItem save(@RequestBody BudgetMaintainItem budgetMaintainItem) {
        budgetMaintainItemDao.save(budgetMaintainItem);
        return budgetMaintainItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public BudgetMaintainItem get(@PathVariable Long id) {
        return budgetMaintainItemDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public BudgetMaintainItem update(@RequestBody BudgetMaintainItem budgetMaintainItem) {
        budgetMaintainItemDao.update(budgetMaintainItem);
        return budgetMaintainItem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public BudgetMaintainItem audit(@PathVariable Long id) {
        BudgetMaintainItem budgetMaintainItem = budgetMaintainItemDao.getById(id);
        budgetMaintainItem.setStatus(1);
        budgetMaintainItemDao.update(budgetMaintainItem);
        return budgetMaintainItem;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public BudgetMaintainItem unaudit(@PathVariable Long id) {
        BudgetMaintainItem budgetMaintainItem = budgetMaintainItemDao.getById(id);
        budgetMaintainItem.setStatus(0);
        budgetMaintainItemDao.update(budgetMaintainItem);
        return budgetMaintainItem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return budgetMaintainItemDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<BudgetMaintainItem> list(PageTableRequest request) {
                return budgetMaintainItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
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
        int count = budgetMaintainItemDao.count(request.getParams());
        List list = budgetMaintainItemDao.list(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        budgetMaintainItemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<BudgetMaintainItem> listAll() {
        List<BudgetMaintainItem> list = budgetMaintainItemDao.listAll();
        return list;
    }

}
