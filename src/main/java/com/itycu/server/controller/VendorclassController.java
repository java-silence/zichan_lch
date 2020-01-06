package com.itycu.server.controller;

import java.util.*;

import com.google.common.collect.Lists;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.model.Chfl;
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
import com.itycu.server.dao.VendorclassDao;
import com.itycu.server.model.Vendorclass;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vendorclasss")
public class VendorclassController {

    @Autowired
    private VendorclassDao vendorclassDao;
    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Vendorclass save(@RequestBody Vendorclass vendorclass) {
        vendorclass.setCreateby(UserUtil.getLoginUser().getId());
        vendorclass.setDeptid(UserUtil.getLoginUser().getDeptid());
        if (vendorclass.getPid() == null) {

            vendorclass.setPid( new Long(0));
        }
        vendorclassDao.save(vendorclass);
        return vendorclass;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Vendorclass get(@PathVariable Long id) {
        return vendorclassDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Vendorclass update(@RequestBody Vendorclass vendorclass) {
        vendorclass.setUpdateby(UserUtil.getLoginUser().getId());
        vendorclassDao.update(vendorclass);
        return vendorclass;
    }


    @GetMapping(value = "/listvendorclass",params = "type")
    @ApiOperation(value = "列出所有存货分类")
    public List<Vendorclass> listvendorclass(String type) {
        List<Vendorclass> vendorclasss ;
        if(type.equals("topchfl")){
            vendorclasss = vendorclassDao.listTopchfls(0L);
        }else {
            vendorclasss = vendorclassDao.listvendorclass();
        }

        return vendorclasss;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Vendorclass audit(@PathVariable Long id) {
        Vendorclass vendorclass = vendorclassDao.getById(id);

        vendorclass.setAuditby(UserUtil.getLoginUser().getId());
        vendorclass.setAuditTime(new Date());
        vendorclass.setStatus("1");
        vendorclassDao.update(vendorclass);
        return vendorclass;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Vendorclass unaudit(@PathVariable Long id) {
        Vendorclass vendorclass = vendorclassDao.getById(id);

        vendorclass.setAuditby(null);
        vendorclass.setAuditTime(null);
        vendorclass.setStatus("0");
        vendorclassDao.update(vendorclass);
        return vendorclass;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return vendorclassDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Vendorclass> list(PageTableRequest request) {
                return vendorclassDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = vendorclassDao.count(request.getParams());

            List list = vendorclassDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }



    @GetMapping("/treelist")
    @ApiOperation(value = "部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public List<Vendorclass> treeList() {

        List<Vendorclass> treeAll = new ArrayList<>();
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:vendorClass:querydept") > 0){
            treeAll = vendorclassDao.listByDeptid(UserUtil.getLoginUser().getDeptid());
        }else{
            treeAll = vendorclassDao.listAll();
        }

        List<Vendorclass> list = Lists.newArrayList();
        settreeList(0L, treeAll, list);
//        System.out.println(list);
        return list;
    }

    /**
     * 部门树列表
     *
     * @param pId
     * @param treeAll
     * @param list
     */
    private void settreeList(Long pId, List<Vendorclass> treeAll, List<Vendorclass> list) {
        for (Vendorclass per : treeAll) {
            if (per.getPid().equals(pId)) {
                list.add(per);
                if (treeAll.stream().filter(p -> p.getPid().equals(per.getId())).findAny() != null) {
                    settreeList(per.getId(), treeAll, list);
                }
            }
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        vendorclassDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Vendorclass> listAll() {
        List<Vendorclass> list = vendorclassDao.listAll();
        return list;
    }
}
