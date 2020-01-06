package com.itycu.server.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.service.BeiyongjinService;
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
import com.itycu.server.dao.BeiyongjinDao;
import com.itycu.server.model.Beiyongjin;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/beiyongjins")
public class BeiyongjinController {

    @Autowired
    private BeiyongjinDao beiyongjinDao;

    @Autowired
    private BeiyongjinService beiyongjinService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Beiyongjin save(@RequestBody Beiyongjin beiyongjin) {
        return beiyongjinService.save(beiyongjin);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Beiyongjin get(@PathVariable Long id) {
        return beiyongjinDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    @Transactional
    public Beiyongjin update(@RequestBody Beiyongjin beiyongjin) {
        return beiyongjinService.update(beiyongjin);
    }

//    @PutMapping("/audit/{id}")
//    @ApiOperation(value = "审核")
//    public Beiyongjin audit(@PathVariable Long id) {
//        Beiyongjin beiyongjin = beiyongjinDao.getById(id);
//
//        beiyongjin.setAuditby(UserUtil.getLoginUser().getId());
//        beiyongjin.setAuditTime(new Date());
//        beiyongjin.setStatus("1");
//        beiyongjinDao.update(beiyongjin);
//        return beiyongjin;
//    }

    @PostMapping("/audit")
    @ApiOperation(value = "审核")
    public void audit(@RequestBody List<Beiyongjin> beiyongjins) {
        for (Beiyongjin beiyongjin : beiyongjins){
            beiyongjin.setAuditby(UserUtil.getLoginUser().getId());
            beiyongjin.setAuditTime(new Date());
            beiyongjin.setStatus("3");
            beiyongjinDao.update(beiyongjin);
        }
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Beiyongjin unaudit(@PathVariable Long id) {
        Beiyongjin beiyongjin = beiyongjinDao.getById(id);

        beiyongjin.setAuditby(null);
        beiyongjin.setAuditTime(null);
        beiyongjin.setStatus("0");
        beiyongjinDao.update(beiyongjin);
        return beiyongjin;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return beiyongjinDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Beiyongjin> list(PageTableRequest request) {
                return beiyongjinDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = beiyongjinDao.count(request.getParams());

            List list = beiyongjinDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        beiyongjinDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Beiyongjin> listAll() {
        List<Beiyongjin> list = beiyongjinDao.listAll();
        return list;
    }

    @GetMapping("/getyue")
    @ApiOperation(value = "获取余额")
    public BigDecimal getYue() {
        return beiyongjinDao.getYue();
    }



//    @PutMapping("/todo")
//    @ApiOperation(value = "审批")
//    @Transactional
//    public TodoDto todo(@RequestBody TodoDto todo) {
//        return beiyongjinService.todo(todo);
//    }

}
