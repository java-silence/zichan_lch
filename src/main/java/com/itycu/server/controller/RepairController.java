package com.itycu.server.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.itycu.server.dao.*;
import com.itycu.server.dto.RepairVO;
import com.itycu.server.dto.RepairsVO;
import com.itycu.server.dto.StockoutsVO;
import com.itycu.server.model.*;
import com.itycu.server.service.RepairService;
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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/repairs")
public class RepairController {

    @Autowired
    private RepairDao repairDao;

    @Autowired
    private RepairService repairService;

    @Autowired
    private RepairsDao repairsDao;

    @Autowired
    private WarehouseDao warehouseDao;
    @Autowired
    private StockoutDao stockoutDao;
    @Autowired
    private StockoutsDao stockoutsDao;

    @Autowired
    private PermissionDao permissionDao;

//    @PostMapping
//    @ApiOperation(value = "保存")
//    public Repair save(@RequestBody Repair repair) {
//        repair.setCreateby(UserUtil.getLoginUser().getId());
//        repairDao.save(repair);
//        return repair;
//    }
@PostMapping
@ApiOperation(value = "保存")
public Repair save(@RequestBody RepairVO repairVO) {
    return repairService.save(repairVO);
}

//    @PostMapping
//    @ApiOperation(value = "保存")
//    @Transactional
//    public Repair save(@RequestBody RepairVO repairVO) {
//        Repair repair = (Repair)repairVO;
//        repair.setCreateby(UserUtil.getLoginUser().getId());
//        repairDao.save(repair);
//
//
//        List<Repairs> repairsList = repairVO.getRepairsList();
//        if (repairsList.size()!=0){
//            repairsDao.saves(repairsList,repair.getId());
//        }
//
//        return repair;
//    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Repair get(@PathVariable Long id) {
        return repairDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Repair update(@RequestBody RepairVO repairVO) {
        return repairService.update(repairVO);
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Repair audit(@PathVariable Long id) {

        Repair repair = repairDao.getById(id);
//        repairService.SendTodo(id);
        return repair;
    }

    @GetMapping("/repairsStockout/{id}")
    @ApiOperation(value = "维修出库")
    @Transactional
    public Repair repairsStockout(@PathVariable Long id) {

        Repair repair = repairDao.getById(id);
        repair.setMaterial("1");
        repairDao.update(repair);

        Warehouse warehouse = warehouseDao.listByDept(repair.getDeptid()); //出库
        Stockout stockout = new Stockout();
        stockout.setDdate(new Date());
        stockout.setBusstype("2");
        stockout.setWhid(warehouse.getId());
        stockout.setDeptid(repair.getDeptid());
        stockout.setWhuserid(UserUtil.getLoginUser().getId());
        stockout.setStatus("1");
        stockoutDao.save(stockout);
        
        return repair;
    }

    Currstock findCurrstock(List<Currstock> currStocks, Long invid){
        List<Currstock> currstockList = currStocks.stream().filter(c -> c.getInvid().equals(invid)).collect(Collectors.toList());
        if (currstockList.size()==0){
            return null;
        }
        return currstockList.get(0);
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Repair unaudit(@PathVariable Long id) {
        Repair repair = repairDao.getById(id);

        repair.setAuditby(null);
        repair.setAuditTime(null);
        repair.setStatus("0");
        repairDao.update(repair);
        return repair;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {return repairDao.count(request.getParams());}
        }, new ListHandler() {

            @Override
            public List<RepairVO> list(PageTableRequest request) {
                return repairDao.list(request.getParams(),request.getOffset(),request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        repairDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Repair> listAll() {
        List<Repair> list = repairDao.listAll();
        return list;
    }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request) {

        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:repair:queryall") == 0){
            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
        }

        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = repairDao.count(request.getParams());

        List list = repairDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }


    @GetMapping("/auditList")
    @ApiOperation(value = "列表")
    public Map auditList(PageTableRequest request) {

        request.getParams().put("todoauditby", UserUtil.getLoginUser().getId());
        //request.getParams().put("todostatus","0");

        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = repairDao.todocount(request.getParams());

        List list = repairDao.todolist(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @GetMapping("/todo/{id}")
    @ApiOperation(value = "根据id获取")
    public RepairVO gettodo(@PathVariable Long id) {
        return repairDao.gettodoById(id);
    }

    @GetMapping("/viewRepair/{id}")
    @ApiOperation(value = "根据id获取")
    public RepairVO viewRepair(@PathVariable Long id) {
        return repairDao.gettodoByBizid(id);
    }

}
