package com.itycu.server.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dao.VendorclassDao;
import com.itycu.server.model.Chfl;
import com.itycu.server.model.Vendorclass;
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
import com.itycu.server.dao.VendorDao;
import com.itycu.server.model.Vendor;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorDao vendorDao;

    @Autowired
    private VendorclassDao vendorclassDao;

    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Vendor save(@RequestBody Vendor vendor) {
        vendor.setCreateby(UserUtil.getLoginUser().getId());
        vendor.setDeptid(UserUtil.getLoginUser().getDeptid());
        vendorDao.save(vendor);
        return vendor;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Vendor get(@PathVariable Long id) {
        return vendorDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Vendor update(@RequestBody Vendor vendor) {
        vendor.setUpdateby(UserUtil.getLoginUser().getId());
        vendorDao.update(vendor);
        return vendor;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Vendor audit(@PathVariable Long id) {
        Vendor vendor = vendorDao.getById(id);

        vendor.setAuditby(UserUtil.getLoginUser().getId());
        vendor.setAuditTime(new Date());
        vendor.setStatus("1");
        vendorDao.update(vendor);
        return vendor;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Vendor unaudit(@PathVariable Long id) {
        Vendor vendor = vendorDao.getById(id);

        vendor.setAuditby(null);
        vendor.setAuditTime(null);
        vendor.setStatus("0");
        vendorDao.update(vendor);
        return vendor;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return vendorDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Vendor> list(PageTableRequest request) {
                return vendorDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {

            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:vendor:querydept") > 0){
                request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
            }
            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = vendorDao.count(request.getParams());

            List list = vendorDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }


    @GetMapping("/eleTree")
    @ApiOperation(value = "eleTree部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public Map eleTree() {
        Map map = new HashMap();
        List<Vendorclass> treeAll = getVendorClass();
//        System.out.println(treeAll);
        Vendorclass vendorclass1 = new Vendorclass();
        vendorclass1.setId(null);
        vendorclass1.setCname("全部");
        vendorclass1.setPid(0l);
        treeAll.add(0,vendorclass1);


        List<Vendorclass> list = Lists.newArrayList();
        treeAll.stream().filter(vendorclass -> vendorclass.getPid().equals(new Long(0)))
                .forEach(vendorclass -> setChild(list,treeAll,vendorclass));

//        System.out.println(list);

        map.put("data",list);
        map.put("code",0);
        return map;
    }

    public List<Vendorclass> getVendorClass(){
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:vendorClass:querydept") > 0){
            return vendorclassDao.listByDeptid(UserUtil.getLoginUser().getDeptid());
        }else{
            return vendorclassDao.listvendorclass();
        }
    }

    public void setChild(List treeAll, List<Vendorclass> list, Vendorclass vendorclass){
        Map map = new HashMap();
        map.put("name",vendorclass.getCname());
        map.put("id",vendorclass.getId());

        List<Vendorclass> vendorclassList = list.stream().filter(d -> d.getPid().equals(vendorclass.getId()))
                .collect(Collectors.toList());

        if (vendorclassList.size() != 0){
            List l1 = new ArrayList();

            vendorclassList.stream().forEach(d -> {
                Map map1 = new HashMap();
                map1.put("name",d.getCname());
                map1.put("id",d.getId());
                l1.add(map1);
            });
            map.put("children",l1);
        }

        treeAll.add(map);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        vendorDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Vendor> listAll() {
        List<Vendor> list = vendorDao.listAll();
        return list;
    }

    @GetMapping("/listctype")
    @ApiOperation(value = "列出所有数据")
    public List<Vendor> listctype() {
        List<Vendor> list = new ArrayList<>();
        Long deptid = UserUtil.getLoginUser().getDeptid();
        if(new Long(6).equals(deptid)){
            list = vendorDao.listctype("sm");
        }else{
            list = vendorDao.listctype("cj");
        }
        return list;
    }
}
