package com.itycu.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dto.ZcInspectRecordDto;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.ZcInspectRecordDao;
import com.itycu.server.model.ZcInspectRecord;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zcInspectRecords")
public class ZcInspectRecordController {

    @Autowired
    private ZcInspectRecordDao zcInspectRecordDao;

    //@Autowired
    //private ZcInspectRecordService zcInspectRecordService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcInspectRecord save(@RequestBody ZcInspectRecord zcInspectRecord) {
        zcInspectRecord.setCreateBy(UserUtil.getLoginUser().getId());
        zcInspectRecordDao.save(zcInspectRecord);
        return zcInspectRecord;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcInspectRecord get(@PathVariable Long id) {
        return zcInspectRecordDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcInspectRecord update(@RequestBody ZcInspectRecord zcInspectRecord) {
//        zcInspectRecord.setUpdateby(UserUtil.getLoginUser().getId());
        zcInspectRecordDao.update(zcInspectRecord);
        return zcInspectRecord;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcInspectRecord audit(@PathVariable Long id) {
        ZcInspectRecord zcInspectRecord = zcInspectRecordDao.getById(id);

//        zcInspectRecord.setAuditby(UserUtil.getLoginUser().getId());
//        zcInspectRecord.setAuditTime(new Date());
//        zcInspectRecord.setStatus("1");
        zcInspectRecordDao.update(zcInspectRecord);
        return zcInspectRecord;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcInspectRecord unaudit(@PathVariable Long id) {
        ZcInspectRecord zcInspectRecord = zcInspectRecordDao.getById(id);

//        zcInspectRecord.setAuditby(null);
//        zcInspectRecord.setAuditTime(null);
//        zcInspectRecord.setStatus("0");
        zcInspectRecordDao.update(zcInspectRecord);
        return zcInspectRecord;
    }


        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {


            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = zcInspectRecordDao.count(request.getParams());

            List list = zcInspectRecordDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcInspectRecordDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcInspectRecord> listAll() {
        List<ZcInspectRecord> list = zcInspectRecordDao.listAll();
        return list;
    }

//    @PutMapping("/todo")
//    @ApiOperation(value = "审批")
//    @Transactional
//    public TodoDto todo(@RequestBody TodoDto todo) {
//        return zcInspectRecordService.todo(todo);
//    }

    @GetMapping("/listByZcInReId")
    @ApiOperation(value = "根据巡检主表找到子表数据")
    public Map listByZcInReId(@RequestParam(value = "zcInReId",required = false) Long zcInReId) {
        Map map = new HashMap();
        List<ZcInspectRecordDto> list = zcInspectRecordDao.listByZcInReId(zcInReId);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }
}
