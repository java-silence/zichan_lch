package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dto.ZcInspectDto;
import com.itycu.server.service.ZcInspectService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.itycu.server.dao.ZcInspectDao;
import com.itycu.server.model.ZcInspect;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/zcInspects")
public class ZcInspectController {

    @Autowired
    private ZcInspectDao zcInspectDao;

    @Autowired
    private ZcInspectService zcInspectService;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存巡检任务")
    @PreAuthorize("hasAuthority('sys:zcInspect:add')")
    @Transactional
    public ZcInspectDto save(@RequestBody ZcInspectDto zcInspect) {
        return zcInspectService.save(zcInspect);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcInspect get(@PathVariable Long id) {
        return zcInspectDao.getById(id);
    }

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改巡检任务")
    @PreAuthorize("hasAuthority('sys:zcInspect:edit')")
    @Transactional
    public ZcInspectDto update(@RequestBody ZcInspectDto zcInspect) {
        return zcInspectService.update(zcInspect);
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcInspect audit(@PathVariable Long id) {
        ZcInspect zcInspect = zcInspectDao.getById(id);

//        zcInspect.setAuditby(UserUtil.getLoginUser().getId());
//        zcInspect.setAuditTime(new Date());
//        zcInspect.setStatus("1");
        zcInspectDao.update(zcInspect);
        return zcInspect;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcInspect unaudit(@PathVariable Long id) {
        ZcInspect zcInspect = zcInspectDao.getById(id);

//        zcInspect.setAuditby(null);
//        zcInspect.setAuditTime(null);
//        zcInspect.setStatus("0");
        zcInspectDao.update(zcInspect);
        return zcInspect;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcInspectDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcInspect> list(PageTableRequest request) {
                return zcInspectDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {


            Map map = new HashMap();
            request.getParams().put("del","0");
            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = zcInspectDao.count(request.getParams());

            List list = zcInspectDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除巡检任务")
    @PreAuthorize("hasAuthority('sys:zcInspect:del')")
    public void delete(@PathVariable Long id) {
        zcInspectService.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcInspect> listAll() {
        List<ZcInspect> list = zcInspectDao.listAll();
        return list;
    }

    @PostMapping("/zcInspectTodo")
    @ApiOperation(value = "巡检选择异常生成待办")
    public ZcInspectDto zcInspectTodo(ZcInspectDto zcInspectDto) {
        return zcInspectService.zcInspectTodo(zcInspectDto);
    }

    @PostMapping("/zcInspectTjTodo")
    @ApiOperation(value = "巡检异常提交待办")
    public ZcInspectDto zcInspectTjTodo(ZcInspectDto zcInspectDto) {
        return zcInspectService.zcInspectTodo(zcInspectDto);
    }

//    @PutMapping("/todo")
//    @ApiOperation(value = "审批")
//    @Transactional
//    public TodoDto todo(@RequestBody TodoDto todo) {
//        return zcInspectService.todo(todo);
//    }
    @LogAnnotation
    @PostMapping("/export")
    @ApiOperation(value = "导出巡检记录")
    public void export(PageTableRequest request, HttpServletResponse response) {
        zcInspectService.export(request, response);
    }
}
