package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dto.TodoDto;
import com.itycu.server.service.FukuanshenqingService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.itycu.server.dao.FukuanshenqingDao;
import com.itycu.server.model.Fukuanshenqing;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fukuanshenqings")
public class FukuanshenqingController {

    @Autowired
    private FukuanshenqingDao fukuanshenqingDao;

    @Autowired
    private FukuanshenqingService fukuanshenqingService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Fukuanshenqing save(@RequestBody Fukuanshenqing fukuanshenqing) {
//        fukuanshenqing.setCreateby(UserUtil.getLoginUser().getId());
//        fukuanshenqingDao.save(fukuanshenqing);
        fukuanshenqingService.save(fukuanshenqing);
        return fukuanshenqing;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Fukuanshenqing get(@PathVariable Long id) {
        return fukuanshenqingDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Fukuanshenqing update(@RequestBody Fukuanshenqing fukuanshenqing) {
        fukuanshenqing.setUpdateby(UserUtil.getLoginUser().getId());
        fukuanshenqingDao.update(fukuanshenqing);
        return fukuanshenqing;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Fukuanshenqing audit(@PathVariable Long id) {
        Fukuanshenqing fukuanshenqing = fukuanshenqingDao.getById(id);

        fukuanshenqing.setAuditby(UserUtil.getLoginUser().getId());
        fukuanshenqing.setAuditTime(new Date());
        fukuanshenqing.setStatus("1");
        fukuanshenqingDao.update(fukuanshenqing);
        return fukuanshenqing;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Fukuanshenqing unaudit(@PathVariable Long id) {
        Fukuanshenqing fukuanshenqing = fukuanshenqingDao.getById(id);

        fukuanshenqing.setAuditby(null);
        fukuanshenqing.setAuditTime(null);
        fukuanshenqing.setStatus("0");
        fukuanshenqingDao.update(fukuanshenqing);
        return fukuanshenqing;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return fukuanshenqingDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Fukuanshenqing> list(PageTableRequest request) {
                return fukuanshenqingDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = fukuanshenqingDao.count(request.getParams());

            List list = fukuanshenqingDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        fukuanshenqingDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Fukuanshenqing> listAll() {
        List<Fukuanshenqing> list = fukuanshenqingDao.listAll();
        return list;
    }

    @PutMapping("/todo")
    @ApiOperation(value = "修改")
    @Transactional
    public TodoDto todo(@RequestBody TodoDto todo) {
        return fukuanshenqingService.todo(todo);
    }
}
