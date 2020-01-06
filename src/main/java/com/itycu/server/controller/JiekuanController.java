package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.itycu.server.dao.JiekuanDao;
import com.itycu.server.model.Jiekuan;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/jiekuans")
public class JiekuanController {

    @Autowired
    private JiekuanDao jiekuanDao;

    //@Autowired
    //private JiekuanService jiekuanService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Jiekuan save(@RequestBody Jiekuan jiekuan) {
        jiekuan.setCreateby(UserUtil.getLoginUser().getId());
        jiekuanDao.save(jiekuan);
        return jiekuan;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Jiekuan get(@PathVariable Long id) {
        return jiekuanDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Jiekuan update(@RequestBody Jiekuan jiekuan) {
        jiekuan.setUpdateby(UserUtil.getLoginUser().getId());
        jiekuanDao.update(jiekuan);
        return jiekuan;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Jiekuan audit(@PathVariable Long id) {
        Jiekuan jiekuan = jiekuanDao.getById(id);

        jiekuan.setAuditby(UserUtil.getLoginUser().getId());
        jiekuan.setAuditTime(new Date());
        jiekuan.setStatus("1");
        jiekuanDao.update(jiekuan);
        return jiekuan;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Jiekuan unaudit(@PathVariable Long id) {
        Jiekuan jiekuan = jiekuanDao.getById(id);

        jiekuan.setAuditby(null);
        jiekuan.setAuditTime(null);
        jiekuan.setStatus("0");
        jiekuanDao.update(jiekuan);
        return jiekuan;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return jiekuanDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Jiekuan> list(PageTableRequest request) {
                return jiekuanDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = jiekuanDao.count(request.getParams());

            List list = jiekuanDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        jiekuanDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Jiekuan> listAll() {
        List<Jiekuan> list = jiekuanDao.listAll();
        return list;
    }

//    @PutMapping("/todo")
//    @ApiOperation(value = "审批")
//    @Transactional
//    public TodoDto todo(@RequestBody TodoDto todo) {
//        return jiekuanService.todo(todo);
//    }

}
