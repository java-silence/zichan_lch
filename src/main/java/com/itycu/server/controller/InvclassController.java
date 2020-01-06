package com.itycu.server.controller;

import com.google.common.collect.Lists;
import com.itycu.server.dao.InvclassDao;
import com.itycu.server.model.Invclass;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invclasss")
public class InvclassController {

    @Autowired
    private InvclassDao invclassDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Invclass save(@RequestBody Invclass invclass) {
        invclass.setCreateby(UserUtil.getLoginUser().getId());
        invclassDao.save(invclass);
        return invclass;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Invclass get(@PathVariable Long id) {
        return invclassDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Invclass update(@RequestBody Invclass invclass) {
        invclass.setUpdateby(UserUtil.getLoginUser().getId());
        invclassDao.update(invclass);
        return invclass;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Invclass audit(@PathVariable Long id) {
        Invclass invclass = invclassDao.getById(id);

        invclass.setAuditby(UserUtil.getLoginUser().getId());
        invclass.setAuditTime(new Date());
        invclass.setStatus("1");
        invclassDao.update(invclass);
        return invclass;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Invclass unaudit(@PathVariable Long id) {
        Invclass invclass = invclassDao.getById(id);

        invclass.setAuditby(null);
        invclass.setAuditTime(null);
        invclass.setStatus("0");
        invclassDao.update(invclass);
        return invclass;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return invclassDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Invclass> list(PageTableRequest request) {
                return invclassDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        invclassDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Invclass> listAll() {
        List<Invclass> list = invclassDao.listAll();
        return list;
    }

    @GetMapping("/eleTree")
    @ApiOperation(value = "eleTree部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public Map eleTree() {
        Map map = new HashMap();
        List<Invclass> treeAll = listInv();
        Invclass invclassAll = new Invclass();
        invclassAll.setId(null);
        invclassAll.setCname("全部");
        invclassAll.setPid(0l);
        treeAll.add(0,invclassAll);

        List<Invclass> list = Lists.newArrayList();
        treeAll.stream().filter(invclass -> invclass.getPid().equals(new Long(0)))
                .forEach(invclass -> setChild(list,treeAll,invclass));
//        System.out.println(list);

        map.put("data",list);
        map.put("code",0);
        return map;
    }

    public List<Invclass> listInv(){
        List<Invclass> list = new ArrayList<>();
        Long deptid = UserUtil.getLoginUser().getDeptid();
        if(new Long(6).equals(deptid)){
            list = invclassDao.listIdOrPid(new Long(5));
        }else{
            list = invclassDao.listAll();
        }
        return  list;
    }

    public Map setChild(List list,List<Invclass> treeAll,Invclass invclass){
        Map map = new HashMap();
        map.put("name",invclass.getCname());
        map.put("id",invclass.getId());

        List<Invclass> invclassList = treeAll.stream().filter(i -> i.getPid().equals(invclass.getId()))
                .collect(Collectors.toList());

        if (invclassList.size() != 0){
            List childList = new ArrayList();

            invclassList.stream().forEach(d -> {
                childList.add(setChild(list,treeAll,d));
            });
            map.put("children",childList);
        }

        if (new Long(0).equals(invclass.getPid()))  list.add(map);
        return map;
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {
        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        int count = invclassDao.count(request.getParams());

        List list = invclassDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @GetMapping("/listByPid")
    @ApiOperation(value = "列出所有数据")
    public List<Invclass> listByPid(Long pid) {
        List<Invclass> list = invclassDao.listByPid(pid);
        return list;
    }
}
