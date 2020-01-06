package com.itycu.server.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dto.ZcRepairItemDto;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.ZcRepairItemDao;
import com.itycu.server.model.ZcRepairItem;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/zcRepairItems")
public class ZcRepairItemController {

    @Autowired
    private ZcRepairItemDao zcRepairItemDao;
    @Autowired
    private PermissionDao permissionDao;

    //@Autowired
    //private ZcRepairItemService zcRepairItemService;

    @PostMapping
    @ApiOperation(value = "保存")
    public ZcRepairItem save(@RequestBody ZcRepairItem zcRepairItem) {
        zcRepairItem.setCreateBy(UserUtil.getLoginUser().getId());
        zcRepairItemDao.save(zcRepairItem);
        return zcRepairItem;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcRepairItem get(@PathVariable Long id) {
        return zcRepairItemDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ZcRepairItem update(@RequestBody ZcRepairItem zcRepairItem) {
        zcRepairItem.setUpdateBy(UserUtil.getLoginUser().getId());
        zcRepairItemDao.update(zcRepairItem);
        return zcRepairItem;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZcRepairItem audit(@PathVariable Long id) {
        ZcRepairItem zcRepairItem = zcRepairItemDao.getById(id);

//        zcRepairItem.setAuditby(UserUtil.getLoginUser().getId());
//        zcRepairItem.setAuditTime(new Date());
//        zcRepairItem.setStatus("1");
        zcRepairItemDao.update(zcRepairItem);
        return zcRepairItem;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZcRepairItem unaudit(@PathVariable Long id) {
        ZcRepairItem zcRepairItem = zcRepairItemDao.getById(id);

//        zcRepairItem.setAuditby(null);
//        zcRepairItem.setAuditTime(null);
//        zcRepairItem.setStatus("0");
        zcRepairItemDao.update(zcRepairItem);
        return zcRepairItem;
    }

//    @GetMapping
//    @ApiOperation(value = "列表")
//    public PageTableResponse list(PageTableRequest request) {
//        return new PageTableHandler(new CountHandler() {
//
//            @Override
//            public int count(PageTableRequest request) {
//                return zcRepairItemDao.count(request.getParams());
//            }
//        }, new ListHandler() {
//
//            @Override
//            public List<ZcRepairItem> list(PageTableRequest request) {
//                return zcRepairItemDao.list(request.getParams(), request.getOffset(), request.getLimit());
//            }
//        }).handle(request);
//    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {
            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcRepair:querydept") > 0){
                request.getParams().put("applyDeptId", UserUtil.getLoginUser().getDeptid());
            }

            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = zcRepairItemDao.count(request.getParams());

            List list = zcRepairItemDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zcRepairItemDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcRepairItem> listAll() {
        List<ZcRepairItem> list = zcRepairItemDao.listAll();
        return list;
    }

//    @PutMapping("/todo")
//    @ApiOperation(value = "审批")
//    @Transactional
//    public TodoDto todo(@RequestBody TodoDto todo) {
//        return zcRepairItemService.todo(todo);
//    }
    /**
     * 根据报修ID查询
     * @param zcReId
     * @return
     */
    @GetMapping("/listByZcReId")
    @ApiOperation(value = "根据报修主表找到子表数据")
    public Map listByZcReId(@RequestParam(value = "zcReId",required = false) Long zcReId) {
        Map map = new HashMap();
        //List<ZcBfItem> list = zcBfItemDao.listByZcBfId(zcBfId);
        List<ZcRepairItemDto> list = new ArrayList<>();
        if ( zcReId != null ) {
            list = zcRepairItemDao.listByZcReId(zcReId);
        }
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @GetMapping("/listByZcReIdAndGldept")
    @ApiOperation(value = "根据报修主表找到子表数据")
    public Map listByZcReIdAndGldept(@RequestParam(value = "zcReId",required = false) Long zcReId) {
        Map map = new HashMap();
        //List<ZcBfItem> list = zcBfItemDao.listByZcBfId(zcBfId);
        List<ZcRepairItemDto> list = new ArrayList<>();
        Long glDeptId = UserUtil.getLoginUser().getDeptid();
        if ( zcReId != null && glDeptId != null) {
            list = zcRepairItemDao.listByZcReIdAndGldept(zcReId,glDeptId);
        }
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @GetMapping("/listByZcReIdNew")
    @ApiOperation(value = "根据报修主表找到子表数据")
    public Map listByZcReIdNew(@RequestParam(value = "todoId",required = false) Long todoId) {
        Map map = new HashMap();
        List<ZcRepairItemDto> list = zcRepairItemDao.listDetailByFlowTodoIdNew(todoId);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","成功");
        return map;
    }

    @LogAnnotation
    @PostMapping("/export")
    @ApiOperation(value = "导出报修记录")
    public void export(PageTableRequest request, HttpServletResponse response) {
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcRepair:querydept") > 0){
            request.getParams().put("applyDeptId", UserUtil.getLoginUser().getDeptid());
        }
        List<ZcRepairItemDto> zcRepairItemDtos = zcRepairItemDao.listByCondition(request.getParams());
        String fileName = "报修记录";
//        if (!CollectionUtils.isEmpty(equipmentList)) {
        String[] headers = new String[]{"资产追溯码","资产编码","资产名称","报修开始时间","报修结束时间","报修结果","管理部门","报修原因",
                "服务商名称","地址","联系人","电话","送修地址"};

        List<Object[]> datas = new ArrayList<>(zcRepairItemDtos.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ZcRepairItemDto zcRepairItemDto : zcRepairItemDtos) {
            String repairStartTime = "";
            String repairEndTime = "";
            String qrStatus = "";
            if(zcRepairItemDto.getRepairStartTime() != null){
                repairStartTime = s1.format(zcRepairItemDto.getRepairStartTime());
            }
            if(zcRepairItemDto.getRepairEndTime() != null){
                repairEndTime = s1.format(zcRepairItemDto.getRepairEndTime());
            }
            if (zcRepairItemDto.getQrStatus() != null){
                if (new Integer(0).equals(zcRepairItemDto.getQrStatus())){
                    qrStatus = "不合格";
                }else if(new Integer(1).equals(zcRepairItemDto.getQrStatus())){
                    qrStatus = "合格";
                }
            }
            Object[] objects = new Object[]{zcRepairItemDto.getEpcid(),zcRepairItemDto.getZcCodenum(),zcRepairItemDto.getZcName(),
                    repairStartTime,repairEndTime,qrStatus
                    ,zcRepairItemDto.getGlDeptName(),zcRepairItemDto.getRepairDes(),zcRepairItemDto.getOutCompany(),
                    zcRepairItemDto.getOutAddress(),zcRepairItemDto.getOutUsername(),zcRepairItemDto.getOutPhone()
                    ,zcRepairItemDto.getRepairAddress()
            };
            datas.add(objects);
        }

        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
//        }
    }
}
