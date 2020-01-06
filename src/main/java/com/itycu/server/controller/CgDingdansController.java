package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dto.CgdingdanVO;
import com.itycu.server.model.CgDingdan;
import com.itycu.server.utils.UserUtil;
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
import com.itycu.server.dao.CgDingdansDao;
import com.itycu.server.model.CgDingdans;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cgDingdanss")
public class CgDingdansController {

    @Autowired
    private CgDingdansDao cgDingdansDao ;

    @PostMapping
    @ApiOperation(value = "保存")
    public CgDingdans save(@RequestBody CgDingdans cgDingdans) {
//        CgDingdan cgDingdan = (CgDingdan)cgdingdanVO;
        cgDingdansDao.save(cgDingdans);


        return cgDingdans;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public CgDingdans get(@PathVariable Long id) {
        return cgDingdansDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public CgDingdans update(@RequestBody CgDingdans cgDingdans) {

        cgDingdansDao.update(cgDingdans);
        return cgDingdans;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public CgDingdans audit(@PathVariable Long id) {
        CgDingdans cgDingdans = cgDingdansDao.getById(id);


        cgDingdans.setStatus("1");
        cgDingdansDao.update(cgDingdans);
        return cgDingdans;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public CgDingdans unaudit(@PathVariable Long id) {
        CgDingdans cgDingdans = cgDingdansDao.getById(id);


        cgDingdans.setStatus("0");
        cgDingdansDao.update(cgDingdans);
        return cgDingdans;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return cgDingdansDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<CgDingdans> list(PageTableRequest request) {
                return cgDingdansDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = cgDingdansDao.count(request.getParams());

            List list = cgDingdansDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;

        }

    @GetMapping("/listBypid")
    @ApiOperation(value = "根据主表id,列出所有子表数据")
    public Map listBypid(Long pid) {
        Map map = new HashMap();
        List<CgDingdans> list = cgDingdansDao.getBypid(pid);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        cgDingdansDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<CgDingdans> listAll() {
        List<CgDingdans> list = cgDingdansDao.listAll();
        return list;
    }
}