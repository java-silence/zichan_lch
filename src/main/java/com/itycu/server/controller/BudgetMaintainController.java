package com.itycu.server.controller;

import com.itycu.server.dao.BudgetMaintainDao;
import com.itycu.server.model.BudgetMaintain;
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
 * 维保预算
 */
@RestController
@RequestMapping("/budgetMaintains")
public class BudgetMaintainController {

    @Autowired
    private BudgetMaintainDao budgetMaintainDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public BudgetMaintain save(@RequestBody BudgetMaintain budgetMaintain) {
        budgetMaintainDao.save(budgetMaintain);
        return budgetMaintain;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public BudgetMaintain get(@PathVariable Long id) {
        return budgetMaintainDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public BudgetMaintain update(@RequestBody BudgetMaintain budgetMaintain) {
        budgetMaintainDao.update(budgetMaintain);
        return budgetMaintain;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public BudgetMaintain audit(@PathVariable Long id) {
        BudgetMaintain budgetMaintain = budgetMaintainDao.getById(id);
        budgetMaintain.setStatus(1);
        budgetMaintainDao.update(budgetMaintain);
        return budgetMaintain;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public BudgetMaintain unaudit(@PathVariable Long id) {
        BudgetMaintain budgetMaintain = budgetMaintainDao.getById(id);
        budgetMaintain.setStatus(0);
        budgetMaintainDao.update(budgetMaintain);
        return budgetMaintain;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return budgetMaintainDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<BudgetMaintain> list(PageTableRequest request) {
                return budgetMaintainDao.list(request.getParams(), request.getOffset(), request.getLimit());
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
        int count = budgetMaintainDao.count(request.getParams());
        List list = budgetMaintainDao.list(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        budgetMaintainDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<BudgetMaintain> listAll() {
        List<BudgetMaintain> list = budgetMaintainDao.listAll();
        return list;
    }

}
