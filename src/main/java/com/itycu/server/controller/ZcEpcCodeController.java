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
import com.itycu.server.dao.ZcEpcCodeDao;
import com.itycu.server.model.ZcEpcCode;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zcEpcCodes")
public class ZcEpcCodeController {

    @Autowired
    private ZcEpcCodeDao zcEpcCodeDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcEpcCode save(@RequestBody ZcEpcCode zcEpcCode) {
        zcEpcCodeDao.save(zcEpcCode);
        return zcEpcCode;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcEpcCode get(@PathVariable Long id) {
        return zcEpcCodeDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcEpcCode update(@RequestBody ZcEpcCode zcEpcCode) {
        zcEpcCodeDao.update(zcEpcCode);
        return zcEpcCode;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcEpcCode audit(@PathVariable Long id) {
        ZcEpcCode zcEpcCode = zcEpcCodeDao.getById(id);
        zcEpcCodeDao.update(zcEpcCode);
        return zcEpcCode;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcEpcCode unaudit(@PathVariable Long id) {
        ZcEpcCode zcEpcCode = zcEpcCodeDao.getById(id);
        zcEpcCodeDao.update(zcEpcCode);
        return zcEpcCode;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {
            @Override
            public int count(PageTableRequest request) {
                return zcEpcCodeDao.count(request.getParams());
            }
        }, new ListHandler() {
            @Override
            public List<ZcEpcCode> list(PageTableRequest request) {
                return zcEpcCodeDao.list(request.getParams(), request.getOffset(), request.getLimit());
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
        int count = zcEpcCodeDao.count(request.getParams());
        List list = zcEpcCodeDao.list(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcEpcCodeDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcEpcCode> listAll() {
        List<ZcEpcCode> list = zcEpcCodeDao.listAll();
        return list;
    }

}
