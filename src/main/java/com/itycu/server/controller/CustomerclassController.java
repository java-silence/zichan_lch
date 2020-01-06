package com.itycu.server.controller;

import java.util.*;

import com.google.common.collect.Lists;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.model.Chfl;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
import com.itycu.server.dao.CustomerclassDao;
import com.itycu.server.model.Customerclass;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customerclasss")
public class CustomerclassController {

    @Autowired
    private CustomerclassDao customerclassDao;
    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Customerclass save(@RequestBody Customerclass customerclass) {
        customerclass.setCreateby(UserUtil.getLoginUser().getId());
        customerclass.setDeptid(UserUtil.getLoginUser().getDeptid());
        if (customerclass.getPid() == null) {

            customerclass.setPid( new Long(0));
        }
        if (new Long(4l).compareTo(customerclass.getPid()) == 0){
            customerclass.setC01("线路");
        }
        customerclassDao.save(customerclass);
        return customerclass;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Customerclass get(@PathVariable Long id) {
        return customerclassDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Customerclass update(@RequestBody Customerclass customerclass) {
        if (new Long(4l).compareTo(customerclass.getPid()) == 0){
            customerclass.setC01("线路");
        }else{
            customerclass.setC01("");
        }
        customerclass.setUpdateby(UserUtil.getLoginUser().getId());
        customerclassDao.update(customerclass);
        return customerclass;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Customerclass audit(@PathVariable Long id) {
        Customerclass customerclass = customerclassDao.getById(id);

        customerclass.setAuditby(UserUtil.getLoginUser().getId());
        customerclass.setAuditTime(new Date());
        customerclass.setStatus("1");
        customerclassDao.update(customerclass);
        return customerclass;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Customerclass unaudit(@PathVariable Long id) {
        Customerclass customerclass = customerclassDao.getById(id);

        customerclass.setAuditby(null);
        customerclass.setAuditTime(null);
        customerclass.setStatus("0");
        customerclassDao.update(customerclass);
        return customerclass;
    }

    @GetMapping(value = "/listcustomerclass",params = "type")
    @ApiOperation(value = "列出所有存货分类")
    public List<Customerclass> listcustomerclass(String type) {

        List<Customerclass> customerclass ;

        if(type.equals("topcustomerclass")){
            customerclass = customerclassDao.listTopcustomerclass(0L);
        }else if(type.equals("deptcustomerclass")){
            customerclass = customerclassDao.listByDeptid(UserUtil.getLoginUser().getDeptid());
        }else{
            customerclass = customerclassDao.listcustomerclass();
        }

        return customerclass;
    }


    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return customerclassDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Customerclass> list(PageTableRequest request) {
                return customerclassDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = customerclassDao.count(request.getParams());

            List list = customerclassDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }


    @GetMapping("/treelist")
    @ApiOperation(value = "部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public List<Customerclass> treeList() {

        List<Customerclass> treeAll = new ArrayList<>();
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:customerClass:querydept") > 0){
            treeAll = customerclassDao.listByDeptid(UserUtil.getLoginUser().getDeptid());
        }else{
            treeAll = customerclassDao.listAll();
        }

        List<Customerclass> list = Lists.newArrayList();
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
    private void settreeList(Long pId, List<Customerclass> treeAll, List<Customerclass> list) {
        for (Customerclass per : treeAll) {
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
        customerclassDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Customerclass> listAll() {
        List<Customerclass> list = customerclassDao.listAll();
        return list;
    }

    @GetMapping("/listXianlu")
    @ApiOperation(value = "列出所有线路")
    public List<Customerclass> listXianlu() {
        List<Customerclass> list = customerclassDao.listXianlu();
        return list;
    }
}
