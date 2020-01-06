package com.itycu.server.controller;

import com.itycu.server.dao.InvDao;
import com.itycu.server.dao.InvclassDao;
import com.itycu.server.dto.CurrstockVO;
import com.itycu.server.model.Inv;
import com.itycu.server.model.Invclass;
import com.itycu.server.model.SelectOption;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invs")
public class InvController {

    @Autowired
    private InvDao invDao;
    @Autowired
    private InvclassDao invclassDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Inv save(@RequestBody Inv inv) {
        inv.setCreateby(UserUtil.getLoginUser().getId());
        invDao.save(inv);
        return inv;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Inv get(@PathVariable Long id) {
        return invDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Inv update(@RequestBody Inv inv) {
        inv.setUpdateby(UserUtil.getLoginUser().getId());
        invDao.update(inv);
        return inv;
    }

    @PutMapping("/batchUpdate")
    @ApiOperation(value = "批量修改")
    public Inv batchUpdate(@RequestBody Inv inv) {
        inv.setUpdateby(UserUtil.getLoginUser().getId());
        invDao.batchUpdate(inv);
        return inv;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Inv audit(@PathVariable Long id) {
        Inv inv = invDao.getById(id);

        inv.setAuditby(UserUtil.getLoginUser().getId());
        inv.setAuditTime(new Date());
        inv.setStatus("1");
        invDao.update(inv);
        return inv;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Inv unaudit(@PathVariable Long id) {
        Inv inv = invDao.getById(id);

        inv.setAuditby(null);
        inv.setAuditTime(null);
        inv.setStatus("0");
        invDao.update(inv);
        return inv;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return invDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Inv> list(PageTableRequest request) {
                return invDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        invDao.delete(id);
    }

    @GetMapping("/optionsbyinvcid/{invcid}")
    @ApiOperation(value = "某一分类下所有物料给select options")
    public List<SelectOption> optionsbyinvcid(@PathVariable Long invcid) {
        List<SelectOption> list = invDao.optionsbyinvcid(invcid);
        return list;
    }
    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Inv> listAll() {
        List<Inv> list = invDao.listAll();
        return list;
    }
    @GetMapping("/getByXtid")
    @ApiOperation(value = "根据系统id列出所有数据")
    public List<Inv> getByXtid(Long xtid) {
        List<Inv> list = invDao.getByXtid(xtid);
        return list;
    }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
    public Map layuiList(PageTableRequest request) {
        Map map = new HashMap();

        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

        Long deptid = UserUtil.getLoginUser().getDeptid();
        if(new Long(6).equals(deptid)){
            request.getParams().put("flid", 5);
        }
        int count = invDao.count(request.getParams());
        List list = invDao.list(request.getParams(), page*limit-limit, limit);

        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @GetMapping("/getinvGroupInvname/{invcid}")
    @ApiOperation(value = "某一分类下所有物料给select options")
    public List<SelectOption> getinvGroupInvname(@PathVariable Long invcid) {
        List<Invclass> invclassList = invclassDao.listAll();
        List<Long> invcidList = new ArrayList<>();
        invcidList.add(invcid);
        findInvcidClass(invclassList,invcid,invcidList);
        List<SelectOption> list = invDao.getinvGroupInvnameByInvcid(invcidList);
        return list;
    }

    List<Invclass> findInvclassByPid(List<Invclass> invclassList, Long pid){
        List<Invclass> collect = new ArrayList<>();
        if (collect != null){
            collect = invclassList.stream().filter(i -> i.getPid().equals(pid)).collect(Collectors.toList());
        }
        return collect;
    }

    //找到线路下的客户分类
    void findInvcidClass(List<Invclass> invclassList, Long pid,List<Long> invcidList){
        List<Invclass> invcidClasses = findInvclassByPid(invclassList, pid);
        if (!CollectionUtils.isEmpty(invcidClasses)){
            for (Invclass invclass:invcidClasses){
                invcidList.add(invclass.getId());
                findInvcidClass(invclassList,invclass.getId(),invcidList);
            }
        }else{
            return;
        }
    }

    @GetMapping("/getinvbyppath/{ppath}")
    @ApiOperation(value = "某一分类下所有物料给select options")
    public List<SelectOption> getinvbyppath(@PathVariable String ppath) {
        List<SelectOption> list = invDao.getinvbyppath(ppath);
        return list;
    }

    @GetMapping("/getInvByNameCpgg")
    @ApiOperation(value = "根据invname和cpgg")
    public Inv getInvByNameCpgg(String invname,String cpgg) {
        Inv inv = null;
        List<Inv> invByNameCpgg = invDao.getInvByNameCpgg(invname, cpgg);
        if (invByNameCpgg.size() == 1){
            inv = invByNameCpgg.get(0);
        }else if(invByNameCpgg.size() > 1){
            throw new NullPointerException("基础档案-存货档案有多个"+invname+"，规格"+cpgg+"的存货，请核对数据");
        }
        return inv;
    }

    @GetMapping("/listcpgg")
    @ApiOperation(value = "列出某个品名所有规格")
    public Map listcpgg(@RequestParam("invname") String invname, @RequestParam("cpgg")String cpgg) {
        Map map = new HashMap();
        List<String> list = invDao.listcpgg(invname,cpgg);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @GetMapping("/listCpggByInvname")
    @ApiOperation(value = "列出某个品名所有规格")
    public Map listCpggByInvname(String invname) {
        Map map = new HashMap();
        List<String> list = invDao.listCpggByInvname(invname);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @GetMapping("/listInvname")
    @ApiOperation(value = "列出品名")
    public Map listInvname(String invname,String ppath) {
        Map map = new HashMap();
        List<String> list = invDao.listInvname(invname,ppath);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","");
        return map;
    }
}
