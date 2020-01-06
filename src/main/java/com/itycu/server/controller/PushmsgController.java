package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dao.PushmsgDao;
import com.itycu.server.model.Pushmsg;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.service.PushService;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pushmsgs")
public class PushmsgController {

    @Autowired
    private PushmsgDao pushmsgDao;
    @Autowired
    private PushService pushService;

    @PostMapping
    @ApiOperation(value = "保存")
    @Transactional
    public Pushmsg save(@RequestBody Pushmsg pushmsg) {
        pushmsg.setCreateby(UserUtil.getLoginUser().getId());
        pushService.PushMsg(pushmsg.getTitle(),pushmsg.getContent(),pushmsg.getUserid(),"",null,null);
//        pushmsgDao.save(pushmsg);
        return pushmsg;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Pushmsg get(@PathVariable Long id) {
        return pushmsgDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Pushmsg update(@RequestBody Pushmsg pushmsg) {
        pushmsg.setUpdateby(UserUtil.getLoginUser().getId());
        pushmsgDao.update(pushmsg);
        return pushmsg;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Pushmsg audit(@PathVariable Long id) {
        Pushmsg pushmsg = pushmsgDao.getById(id);

        pushmsg.setAuditby(UserUtil.getLoginUser().getId());
        pushmsg.setAuditTime(new Date());
        pushmsg.setStatus("1");
        pushmsgDao.update(pushmsg);
        return pushmsg;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Pushmsg unaudit(@PathVariable Long id) {
        Pushmsg pushmsg = pushmsgDao.getById(id);

        pushmsg.setAuditby(null);
        pushmsg.setAuditTime(null);
        pushmsg.setStatus("0");
        pushmsgDao.update(pushmsg);
        return pushmsg;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return pushmsgDao.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<Pushmsg> list(PageTableRequest request) {
                return pushmsgDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {

            request.getParams().put("userid", UserUtil.getLoginUser().getId());

            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = pushmsgDao.count(request.getParams());

            List list = pushmsgDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        pushmsgDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Pushmsg> listAll() {
        List<Pushmsg> list = pushmsgDao.listAll();
        return list;
    }
}
