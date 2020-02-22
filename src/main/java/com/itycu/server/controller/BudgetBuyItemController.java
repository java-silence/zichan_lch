package com.itycu.server.controller;

import com.itycu.server.dao.BudgetBuyItemDao;
import com.itycu.server.model.BudgetBuyItem;
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
 * 购买预算子项
 */
@RestController
@RequestMapping("/budgetBuyItems")
public class BudgetBuyItemController {

    @Autowired
    private BudgetBuyItemDao budgetBuyItemDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public BudgetBuyItem save(@RequestBody BudgetBuyItem budgetBuyItem) {
        budgetBuyItemDao.save(budgetBuyItem);
        return budgetBuyItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public BudgetBuyItem get(@PathVariable Long id) {
        return budgetBuyItemDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public BudgetBuyItem update(@RequestBody BudgetBuyItem budgetBuyItem) {
        budgetBuyItemDao.update(budgetBuyItem);
        return budgetBuyItem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public BudgetBuyItem audit(@PathVariable Long id) {
        BudgetBuyItem budgetBuyItem = budgetBuyItemDao.getById(id);
        budgetBuyItem.setStatus(1);
        budgetBuyItemDao.update(budgetBuyItem);
        return budgetBuyItem;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public BudgetBuyItem unaudit(@PathVariable Long id) {
        BudgetBuyItem budgetBuyItem = budgetBuyItemDao.getById(id);
        budgetBuyItem.setStatus(0);
        budgetBuyItemDao.update(budgetBuyItem);
        return budgetBuyItem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return budgetBuyItemDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<BudgetBuyItem> list(PageTableRequest request) {
                return budgetBuyItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
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
        int count = budgetBuyItemDao.count(request.getParams());
        List list = budgetBuyItemDao.list(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        budgetBuyItemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<BudgetBuyItem> listAll() {
        List<BudgetBuyItem> list = budgetBuyItemDao.listAll();
        return list;
    }

}
