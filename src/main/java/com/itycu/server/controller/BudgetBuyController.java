package com.itycu.server.controller;

import com.itycu.server.dao.BudgetBuyDao;
import com.itycu.server.model.BudgetBuy;
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
 * 购买预算
 */
@RestController
@RequestMapping("/budgetBuys")
public class BudgetBuyController {

    @Autowired
    private BudgetBuyDao budgetBuyDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public BudgetBuy save(@RequestBody BudgetBuy budgetBuy) {
        budgetBuyDao.save(budgetBuy);
        return budgetBuy;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public BudgetBuy get(@PathVariable Long id) {
        return budgetBuyDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public BudgetBuy update(@RequestBody BudgetBuy budgetBuy) {
        budgetBuyDao.update(budgetBuy);
        return budgetBuy;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public BudgetBuy audit(@PathVariable Long id) {
        BudgetBuy budgetBuy = budgetBuyDao.getById(id);
        budgetBuy.setStatus(1);
        budgetBuyDao.update(budgetBuy);
        return budgetBuy;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public BudgetBuy unaudit(@PathVariable Long id) {
        BudgetBuy budgetBuy = budgetBuyDao.getById(id);
        budgetBuy.setStatus(0);
        budgetBuyDao.update(budgetBuy);
        return budgetBuy;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return budgetBuyDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<BudgetBuy> list(PageTableRequest request) {
                return budgetBuyDao.list(request.getParams(), request.getOffset(), request.getLimit());
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
        int count = budgetBuyDao.count(request.getParams());
        List list = budgetBuyDao.list(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","请求成功");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        budgetBuyDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<BudgetBuy> listAll() {
        List<BudgetBuy> list = budgetBuyDao.listAll();
        return list;
    }

}
