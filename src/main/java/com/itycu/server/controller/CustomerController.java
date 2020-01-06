package com.itycu.server.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.itycu.server.dao.CustomerclassDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.model.Chfl;
import com.itycu.server.model.Customerclass;
import com.itycu.server.service.CustomerService;
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
import com.itycu.server.dao.CustomerDao;
import com.itycu.server.model.Customer;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CustomerclassDao customerclassDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private CustomerService customerService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Customer save(@RequestBody Customer customer) {
        customer.setCreateby(UserUtil.getLoginUser().getId());
        customer.setDeptid(UserUtil.getLoginUser().getDeptid());

        customer.setXianluid(getXianlu(customer.getPpath()));

//        Customerclass customerclass = getXianlu(customer.getPpath());
//        if(customerclass !=null){
//            customer.setXianluid(customerclass.getId());
//        }


        customerDao.save(customer);
        return customer;
    }

    private  Long getXianlu(String ppath){
        //SELECT t_customer.*, SUBSTRING(ppath,3),left(SUBSTRING(ppath,3), INSTR(SUBSTRING(ppath,3),'-')-1) from t_customer where ppath like '4-%'
        String xianlu = null;
        Long xianluid=null;
        Customerclass customerclass=null;
        //根据ppath提取线路信息
        if(ppath.startsWith("4-")){
            xianlu = ppath.replace("4-","");
            if(xianlu.indexOf("-")>=0){
                xianlu = xianlu.substring(0,xianlu.indexOf("-"));
//                customerclass = customerclassDao.getById(Long.valueOf(xianlu));
            }
        }
        if(xianlu== null){
            xianluid=null;
        }else {
            xianluid= Long.valueOf(xianlu);
        }

        return  xianluid;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Customer get(@PathVariable Long id) {
        return customerDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Customer update(@RequestBody Customer customer) {
        customer.setUpdateby(UserUtil.getLoginUser().getId());

//        Customerclass customerclass = getXianlu(customer.getPpath());
        customer.setXianluid(getXianlu(customer.getPpath()));
        customerDao.update(customer);
        return customer;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Customer audit(@PathVariable Long id) {
        Customer customer = customerDao.getById(id);

        customer.setAuditby(UserUtil.getLoginUser().getId());
        customer.setAuditTime(new Date());
        customer.setStatus("1");
        customerDao.update(customer);
        return customer;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Customer unaudit(@PathVariable Long id) {
        Customer customer = customerDao.getById(id);

        customer.setAuditby(null);
        customer.setAuditTime(null);
        customer.setStatus("0");
        customerDao.update(customer);
        return customer;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return customerDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Customer> list(PageTableRequest request) {
                return customerDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

        @GetMapping("/list2")
        @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
        public Map list2(PageTableRequest request) {

            if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:customer:querydept") > 0){
                request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
            }
            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = customerDao.count(request.getParams());

            List list = customerDao.list(request.getParams(), page*limit-limit, limit);

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

        List<Customerclass> treeAll = getCustomerClass();

//        System.out.println(treeAll);
        Customerclass Customerclass1 = new Customerclass();
        Customerclass1.setId(null);
        Customerclass1.setCname("全部");
        Customerclass1.setPid(0l);
        treeAll.add(0,Customerclass1);


        List<Customerclass> list = Lists.newArrayList();
        treeAll.stream().filter(customerclass -> customerclass.getPid().equals(new Long(0)))
                .forEach(Customerclass ->{
                    setChild(list,treeAll,Customerclass,"");
                } );

//        System.out.println(list);

        map.put("data",list);
        map.put("code",0);
        return map;
    }

    public List<Customerclass> getCustomerClass(){
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:customerClass:querydept") > 0){
            return customerclassDao.listByDeptid(UserUtil.getLoginUser().getDeptid());
        }else{
            return customerclassDao.listcustomerclass();
        }
    }

    public Map setChild(List list, List<Customerclass> treeAll, Customerclass customerclass,String ppath){
        Map map = new HashMap();
        map.put("name",customerclass.getCname());
        map.put("id",customerclass.getId());
        String mypath;
        if(customerclass.getId() == null){
            mypath="";
        }else{
            if(ppath==""){
                mypath = customerclass.getId().toString();
            }else{
                mypath = ppath + "-" +customerclass.getId().toString();
            }
        }

        map.put("ppath", mypath);

        List<Customerclass> deptList = treeAll.stream().filter(d -> d.getPid().equals(customerclass.getId()))
                .collect(Collectors.toList());

        if (deptList.size() != 0){
            List childList = new ArrayList();

            deptList.stream().forEach(d -> {
                childList.add(setChild(list,treeAll,d,mypath));
            });
            map.put("children",childList);
        }

        if (new Long(0).equals(customerclass.getPid()))  list.add(map);
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        customerDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Customer> listAll() {
        List<Customer> list;
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:customer:querydept") > 0){
            list = customerDao.listbydept(UserUtil.getLoginUser().getDeptid());
        }else{
            list = customerDao.listAll();
        }
        return list;
    }

    @GetMapping("/listctype")
    @ApiOperation(value = "列出所有数据")
    public List<Customer> listctype() {
        List<Customer> list = new ArrayList<>();
        Long deptid = UserUtil.getLoginUser().getDeptid();
        if(new Long(6).equals(deptid)){
            list = customerDao.listctype("sm");
        }else{
            list = customerDao.listctype("cj");
        }
        return list;
    }

    @PostMapping("/cusImport")
    @ApiOperation(value = "客户导入")
    @Transactional
    public Map cusImport(MultipartFile file) throws IOException {
        return customerService.cusImport(file);
    }
}
