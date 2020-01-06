package com.itycu.server.controller;

import com.itycu.server.dao.EquipmentDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dao.ZxBorrowDao;
import com.itycu.server.dao.ZxBorrowsDao;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.dto.ZxBorrowVO;
import com.itycu.server.model.Equipment;
import com.itycu.server.model.ZxBorrow;
import com.itycu.server.model.ZxBorrows;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.service.TodoService;
import com.itycu.server.service.ZxBorrowService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.internal.util.logging.formatter.CollectionOfClassesObjectFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zxBorrows")
public class ZxBorrowController {

    @Autowired
    private ZxBorrowDao zxBorrowDao;

    @Autowired
    private ZxBorrowsDao zxBorrowsDao;

    @Autowired
    private ZxBorrowService zxBorrowService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    @Transactional
    public ZxBorrow save(@RequestBody ZxBorrowVO zxBorrow) {
//        zxBorrowService.save(zxBorrow);
        zxBorrow.setCreateby(UserUtil.getLoginUser().getId());
        zxBorrow.setDeptid(UserUtil.getLoginUser().getDeptid());
        zxBorrow.setStatus("0");
        List<Long> eqpIds = zxBorrow.getEqpIds();
        if (eqpIds == null || eqpIds.size() == 0)  throw new NullPointerException("请选择借用的设备");
        zxBorrowDao.save(zxBorrow);
        List<ZxBorrows> zxBorrowsList = new ArrayList<>();
        for (Long eqpid : eqpIds){
              ZxBorrows zxBorrows = new ZxBorrows();
              zxBorrows.setEqpid(eqpid);
              zxBorrowsList.add(zxBorrows);
        }
        if (!CollectionUtils.isEmpty(zxBorrowsList)){
            zxBorrowsDao.saves(zxBorrowsList,zxBorrow.getId());
        }
//        audit(zxBorrow.getId());
        return zxBorrow;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZxBorrow get(@PathVariable Long id) {
        return zxBorrowDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    @Transactional
    public ZxBorrow update(@RequestBody ZxBorrowVO zxBorrow) {
        zxBorrow.setUpdateby(UserUtil.getLoginUser().getId());
        zxBorrowDao.update(zxBorrow);
        List<Long> eqpIds = zxBorrow.getEqpIds();
        if (eqpIds == null || eqpIds.size() == 0)  throw new NullPointerException("请选择借用的设备");
        List<ZxBorrows> zxBorrowsList = new ArrayList<>();
        for (Long eqpid : eqpIds){
            ZxBorrows zxBorrows = new ZxBorrows();
            zxBorrows.setEqpid(eqpid);
            zxBorrowsList.add(zxBorrows);
        }
        if (!CollectionUtils.isEmpty(zxBorrowsList)){
            zxBorrowsDao.saves(zxBorrowsList,zxBorrow.getId());
        }
//        Equipment equipment =equipmentDao.getById(zxBorrow.getEqpid());
//        equipment.setStatus("正常");
//        equipmentDao.update(equipment);
        return zxBorrow;
    }

    @PutMapping("/todo")
    @ApiOperation(value = "修改")
    @Transactional
    public TodoDto todo(@RequestBody TodoDto todoDto) {
        return zxBorrowService.todo(todoDto);
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public ZxBorrow audit(@PathVariable Long id) {
        ZxBorrow zxBorrow = zxBorrowDao.getById(id);

        zxBorrow.setAuditby(UserUtil.getLoginUser().getId());
        zxBorrow.setAuditTime(new Date());
        zxBorrow.setStatus("1");
        zxBorrowDao.update(zxBorrow);

        Equipment equipment =equipmentDao.getById(zxBorrow.getEqpid());
        equipment.setStatus("借用");
        equipmentDao.update(equipment);

        return zxBorrow;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public ZxBorrow unaudit(@PathVariable Long id) {
        ZxBorrow zxBorrow = zxBorrowDao.getById(id);

        zxBorrow.setAuditby(null);
        zxBorrow.setAuditTime(null);
        zxBorrow.setStatus("0");
        zxBorrowDao.update(zxBorrow);
        return zxBorrow;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return zxBorrowDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<ZxBorrow> list(PageTableRequest request) {
                return zxBorrowDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {

            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:borrow:queryall") == 0){
                request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
            }
            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = zxBorrowDao.count(request.getParams());

            List list = zxBorrowDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        zxBorrowDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZxBorrow> listAll() {
        List<ZxBorrow> list = zxBorrowDao.listAll();
        return list;
    }

    @GetMapping("/printBorrow")
    @ApiOperation(value = "根据id获取")
    public List<ZxBorrow> printBorrow(Long id) {
        List<ZxBorrow> zxBorrowList = new ArrayList<>();
        ZxBorrowVO byId = zxBorrowDao.getById(id);
        List<ZxBorrows> byzxBorrowid = zxBorrowsDao.getByzxBorrowid(byId.getId());
        Map<String, Map<String,List<ZxBorrows>>> collect = byzxBorrowid.stream().collect(
                Collectors.groupingBy(ZxBorrows::getCname,Collectors.groupingBy(ZxBorrows::getEtype)));//根据设备名称型号分组
        collect.forEach((key,value)->{
                value.forEach((key2,value2)->{
                    ZxBorrowVO zxBorrowVO = new ZxBorrowVO();
                    BeanUtils.copyProperties(byId,zxBorrowVO);
                    zxBorrowVO.setZxBorrowsList(value2);
                    value2.forEach((value3)->{
                        zxBorrowVO.setEqpname(value3.getCname());
                        zxBorrowVO.setEtype(value3.getEtype());
                    });
                    zxBorrowList.add(zxBorrowVO);
                });
            });
        return zxBorrowList;
    }
}
