package com.itycu.server.controller;

import com.itycu.server.dao.SblbDao;
import com.itycu.server.model.Sblb;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sblbs")
public class SblbController {

    @Autowired
    private SblbDao sblbDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Sblb save(@RequestBody Sblb sblb) {
        sblb.setCreateby(UserUtil.getLoginUser().getId());
        sblbDao.save(sblb);
        return sblb;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Sblb get(@PathVariable Long id) {
        return sblbDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Sblb update(@RequestBody Sblb sblb) {
        sblb.setUpdateby(UserUtil.getLoginUser().getId());
        sblbDao.update(sblb);
        return sblb;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Sblb audit(@PathVariable Long id) {
        Sblb sblb = sblbDao.getById(id);

        sblb.setAuditby(UserUtil.getLoginUser().getId());
        sblb.setAuditTime(new Date());
        sblb.setStatus("1");
        sblbDao.update(sblb);
        return sblb;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Sblb unaudit(@PathVariable Long id) {
        Sblb sblb = sblbDao.getById(id);

        sblb.setAuditby(null);
        sblb.setAuditTime(null);
        sblb.setStatus("0");
        sblbDao.update(sblb);
        return sblb;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return sblbDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Sblb> list(PageTableRequest request) {
                return sblbDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

        int count = sblbDao.count(request.getParams());

        List list = sblbDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        sblbDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Sblb> listAll() {
        List<Sblb> list = sblbDao.listAll();
        return list;
    }

    @GetMapping("/listbyxtid")
    @ApiOperation(value = "根据部门列出所有用户")
    public List<Sblb> listByxtid(Long xtid) {
        List<Sblb> users = sblbDao.listByXtid(xtid);
        return users;
    }
}
