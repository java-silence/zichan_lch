package com.itycu.server.controller;

import java.util.*;

import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.ZcDeployItemDao;
import com.itycu.server.model.ZcDeployItem;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zcDeployItems")
public class ZcDeployItemController {

    @Autowired
    private ZcDeployItemDao zcDeployItemDao;

    //@Autowired
    //private ZcDeployItemService zcDeployItemService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcDeployItem save(@RequestBody ZcDeployItem zcDeployItem) {
        zcDeployItemDao.save(zcDeployItem);
        return zcDeployItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcDeployItem get(@PathVariable Long id) {
        return zcDeployItemDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcDeployItem update(@RequestBody ZcDeployItem zcDeployItem) {
        zcDeployItemDao.update(zcDeployItem);
        return zcDeployItem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcDeployItem audit(@PathVariable Long id) {
        ZcDeployItem zcDeployItem = zcDeployItemDao.getById(id);
        zcDeployItem.setStatus(1);
        zcDeployItemDao.update(zcDeployItem);
        return zcDeployItem;
    }

    @GetMapping("/listByZcDeployId")
    @ApiOperation(value = "根据报废主表找到子表数据")
    public Map listByZcDeployId(@RequestParam(value = "zcDeployId",required = false) Long zcDeployId,
                                @RequestParam(value = "cw",required = false) String cw) {
        Map map = new HashMap();
        List<Map<String,Object>> list = new ArrayList<>();
        if ( zcDeployId != null ) {
            list = zcDeployItemDao.listDetailByZcDeployId(zcDeployId,cw);
        }
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcDeployItem unaudit(@PathVariable Long id) {
        ZcDeployItem zcDeployItem = zcDeployItemDao.getById(id);
        zcDeployItem.setStatus(0);
        zcDeployItemDao.update(zcDeployItem);
        return zcDeployItem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcDeployItemDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcDeployItem> list(PageTableRequest request) {
                return zcDeployItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {


        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = zcDeployItemDao.count(request.getParams());

        List list = zcDeployItemDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcDeployItemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcDeployItem> listAll() {
        List<ZcDeployItem> list = zcDeployItemDao.listAll();
        return list;
    }

    @GetMapping("/listByZcDeployIdNew")
    @ApiOperation(value = "根据调配主表找到子表数据及资产数据")
    public Map listByZcDeployIdNew(@RequestParam(value = "todoId",required = false) Long todoId) {
        Map map = new HashMap();
        List<Map<String,Object>> list = zcDeployItemDao.listDetailByFlowTodoIdNew(todoId);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

//    @GetMapping("/listByZcDeployId")
//    @ApiOperation(value = "根据ID查询调配列表")
//    public Map listByZcDeployId(@RequestParam(value = "deployId",required = false) Long deployId ){
//        Map map = new HashMap<>();
//        List<Map<String,Object>> list = zcDeployItemDao.listByZcDeployIdNew(deployId);
//        map.put("code",0);
//        map.put("msg",0);
//        map.put("data",list);
//        return map;
//    }

}
