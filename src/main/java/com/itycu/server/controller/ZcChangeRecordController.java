package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dto.ZcChangeRecordDto;
import com.itycu.server.service.ZcChangeRecordService;
import com.itycu.server.utils.DynamicConditionUtil;
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
import com.itycu.server.dao.ZcChangeRecordDao;
import com.itycu.server.model.ZcChangeRecord;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/zcChangeRecords")
public class ZcChangeRecordController {

    @Autowired
    private ZcChangeRecordDao zcChangeRecordDao;

    @Autowired
    private ZcChangeRecordService zcChangeRecordService;

    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcChangeRecord save(@RequestBody ZcChangeRecord zcChangeRecord) {
        zcChangeRecord.setCreateBy(UserUtil.getLoginUser().getId());
        zcChangeRecordDao.save(zcChangeRecord);
        return zcChangeRecord;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcChangeRecord get(@PathVariable Long id) {
        return zcChangeRecordDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcChangeRecord update(@RequestBody ZcChangeRecord zcChangeRecord) {
        zcChangeRecord.setUpdateBy(UserUtil.getLoginUser().getId());
        zcChangeRecordDao.update(zcChangeRecord);
        return zcChangeRecord;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcChangeRecord audit(@PathVariable Long id) {
        ZcChangeRecord zcChangeRecord = zcChangeRecordDao.getById(id);

//        zcChangeRecord.setAuditby(UserUtil.getLoginUser().getId());
//        zcChangeRecord.setAuditTime(new Date());
//        zcChangeRecord.setStatus("1");
        zcChangeRecordDao.update(zcChangeRecord);
        return zcChangeRecord;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcChangeRecord unaudit(@PathVariable Long id) {
        ZcChangeRecord zcChangeRecord = zcChangeRecordDao.getById(id);

//        zcChangeRecord.setAuditby(null);
//        zcChangeRecord.setAuditTime(null);
//        zcChangeRecord.setStatus("0");
        zcChangeRecordDao.update(zcChangeRecord);
        return zcChangeRecord;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zcChangeRecordDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZcChangeRecordDto> list(PageTableRequest request) {
                return zcChangeRecordDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request, HttpServletRequest httpServletRequest) {
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcInfo:querysydept") > 0){
                request.getParams().put("syDeptId", UserUtil.getLoginUser().getDeptid());
            }
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcInfo:querygldept") > 0){
                request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
            }

            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
            DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
            int count = zcChangeRecordDao.count(request.getParams());

            List list = zcChangeRecordDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @GetMapping("/groupList")
    @ApiOperation(value = "列表")
    public Map groupList(PageTableRequest request, HttpServletRequest httpServletRequest) {
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcInfo:querysydept") > 0){
            request.getParams().put("syDeptId", UserUtil.getLoginUser().getDeptid());
        }
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcInfo:querygldept") > 0){
            request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
        }

        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
        int count = zcChangeRecordDao.groupCount(request.getParams());

        List list = zcChangeRecordDao.groupList(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcChangeRecordDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcChangeRecord> listAll() {
        List<ZcChangeRecord> list = zcChangeRecordDao.listAll();
        return list;
    }

//    @PutMapping("/todo")
//    @ApiOperation(value = "审批")
//    @Transactional
//    public TodoDto todo(@RequestBody TodoDto todo) {
//        return zcChangeRecordService.todo(todo);
//    }

    @LogAnnotation
    @PostMapping("/export")
    @ApiOperation(value = "导出变更记录")
    public void export(PageTableRequest request, HttpServletResponse response) {
        zcChangeRecordService.export(request, response);
    }
}
