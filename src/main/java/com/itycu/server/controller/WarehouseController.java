package com.itycu.server.controller;

import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dao.WarehouseDao;
import com.itycu.server.model.Warehouse;
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
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseDao warehouseDao;

    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Warehouse save(@RequestBody Warehouse warehouse) {
        warehouse.setCreateby(UserUtil.getLoginUser().getId());
        warehouseDao.save(warehouse);
        return warehouse;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Warehouse get(@PathVariable Long id) {
        return warehouseDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Warehouse update(@RequestBody Warehouse warehouse) {
        warehouse.setUpdateby(UserUtil.getLoginUser().getId());
        warehouseDao.update(warehouse);
        return warehouse;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Warehouse audit(@PathVariable Long id) {
        Warehouse warehouse = warehouseDao.getById(id);

        warehouse.setAuditby(UserUtil.getLoginUser().getId());
        warehouse.setAuditTime(new Date());
        warehouse.setStatus("1");
        warehouseDao.update(warehouse);
        return warehouse;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Warehouse unaudit(@PathVariable Long id) {
        Warehouse warehouse = warehouseDao.getById(id);

        warehouse.setAuditby(null);
        warehouse.setAuditTime(null);
        warehouse.setStatus("0");
        warehouseDao.update(warehouse);
        return warehouse;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return warehouseDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Warehouse> list(PageTableRequest request) {
                return warehouseDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Warehouse> listAll() {
        List<Warehouse> list = warehouseDao.listAll();
        return list;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        warehouseDao.delete(id);
    }

    @GetMapping("/listsbwh")
    @ApiOperation(value = "列出设备仓库")
    public List<Warehouse> listsbwh() {
        List<Warehouse> list = warehouseDao.listsbwh();
//    public List<Warehouse> listsbwh(@RequestParam("biztype") int biztype) {
//        List<Warehouse> list = warehouseDao.listsbwh(biztype);
        return list;
    }

    @GetMapping("/listbpwh")
    @ApiOperation(value = "列出备品备件仓库")
    public List<Warehouse> listbpwh() {
        List<Warehouse> list = warehouseDao.listbpwh();
        return list;
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {

//        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:warehouse:queryall") == 0){
//            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
//        }

        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = warehouseDao.count(request.getParams());

        List list = warehouseDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }


    @GetMapping("/listbytype")
    @ApiOperation(value = "根据类型列出仓库")
    public List<Warehouse> listbytype(@RequestParam("biztype") String biztype) {
        if(biztype.equals("bj")){
            biztype="备件库";
        }else if(biztype.equals("yl")){
            biztype="原料库";
        }else if(biztype.equals("cp")){
            biztype="产品库";
        }else if(biztype.equals("sm")){
            biztype="商贸库";
        }
        List<Warehouse> list = warehouseDao.listbytype(biztype);
        return list;
    }

    @GetMapping("/listbytypedept")
    @ApiOperation(value = "根据类型和部门列出仓库")
    public List<Warehouse> listbytypedept(@RequestParam("biztype") String biztype) {
        if(biztype.equals("bj")){
            biztype="备件库";
        }else if(biztype.equals("yl")){
            biztype="原料库";
        }else if(biztype.equals("cp")){
            biztype="产品库";
        }
        Long deptid = UserUtil.getLoginUser().getDeptid();

        List<Warehouse> list = warehouseDao.listbytypedept(biztype,deptid);
        return list;
    }
}
