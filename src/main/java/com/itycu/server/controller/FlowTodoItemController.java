package com.itycu.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.itycu.server.dao.FlowTodoItemDao;
import com.itycu.server.model.FlowTodoItem;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/flowTodoItems")
public class FlowTodoItemController {

    @Autowired
    private FlowTodoItemDao flowTodoItemDao;

    //@Autowired
    //private FlowTodoItemService flowTodoItemService;

    @PostMapping
    @ApiOperation(value = "保存")
    public FlowTodoItem save(@RequestBody FlowTodoItem flowTodoItem) {
        //flowTodoItem.setCreateby(UserUtil.getLoginUser().getId());
        flowTodoItemDao.save(flowTodoItem);
        return flowTodoItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public FlowTodoItem get(@PathVariable Long id) {
        return flowTodoItemDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public FlowTodoItem update(@RequestBody FlowTodoItem flowTodoItem) {
        //flowTodoItem.setUpdateby(UserUtil.getLoginUser().getId());
        flowTodoItemDao.update(flowTodoItem);
        return flowTodoItem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public FlowTodoItem audit(@PathVariable Long id) {
        FlowTodoItem flowTodoItem = flowTodoItemDao.getById(id);

        //flowTodoItem.setAuditby(UserUtil.getLoginUser().getId());
        //flowTodoItem.setAuditTime(new Date());
        flowTodoItem.setStatus(1);
        flowTodoItemDao.update(flowTodoItem);
        return flowTodoItem;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public FlowTodoItem unaudit(@PathVariable Long id) {
        FlowTodoItem flowTodoItem = flowTodoItemDao.getById(id);

        //flowTodoItem.setAuditby(null);
        //flowTodoItem.setAuditTime(null);
        //flowTodoItem.setStatus(0);
        flowTodoItemDao.update(flowTodoItem);
        return flowTodoItem;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return flowTodoItemDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<FlowTodoItem> list(PageTableRequest request) {
                return flowTodoItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = flowTodoItemDao.count(request.getParams());

            List list = flowTodoItemDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        flowTodoItemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<FlowTodoItem> listAll() {
        List<FlowTodoItem> list = flowTodoItemDao.listAll();
        return list;
    }

}
