package com.itycu.server.controller;

import com.google.common.collect.Lists;
import com.itycu.server.dao.ChdaDao;
import com.itycu.server.dao.ChflDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.model.Chda;
import com.itycu.server.model.Chfl;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chdas")
public class ChdaController {

    @Autowired
    private ChdaDao chdaDao;

    @Autowired
    private ChflDao chflDao;

    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Chda save(@RequestBody Chda chda) {
        ishasInvnameCpgg(chda);
        chda.setCreateby(UserUtil.getLoginUser().getId());
        chda.setDel("0");
        chdaDao.save(chda);
        return chda;
    }

    //判断品名和规格是否已经存在
    public void ishasInvnameCpgg(Chda chda){
          if (chda.getInvname() != null && !"".equals(chda.getInvname())){
              String invname = chda.getInvname().trim();
              if (chda.getInvstd() == null)  chda.setInvstd("");
              String cpgg = chda.getInvstd().trim();
              List<Chda> chdas = chdaDao.listbyNameCpgg(invname, cpgg);
              if (chdas.size() > 0){
                  throw new NullPointerException("已经存在存货名称："+invname+"，规格："+cpgg+"的存货档案，请核对数据");
              }
          }else{
              throw new NullPointerException("请输入存货名称");
          }
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Chda get(@PathVariable Long id) {
        return chdaDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Chda update(@RequestBody Chda chda) {
        chda.setUpdateby(UserUtil.getLoginUser().getId());
        chdaDao.update(chda);
        return chda;
    }

   /* @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Chda audit(@PathVariable Long id) {
        Chda chda = chdaDao.getById(id);

        chda.setAuditby(UserUtil.getLoginUser().getId());
        chda.setAuditTime(new Date());
        chda.setStatus(1);
        chdaDao.update(chda);
        return chda;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Chda unaudit(@PathVariable Long id) {
        Chda chda = chdaDao.getById(id);

        chda.setAuditby(null);
        chda.setAuditTime(null);
        chda.setStatus(0);
        chdaDao.update(chda);
        return chda;
    }*/


    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return chdaDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Chda> list(PageTableRequest request) {
                return chdaDao.list(request.getParams(), request.getOffset(), request.getLimit());

            }
        }).handle(request);
    }

    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {

        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:inv:querydept") > 0){
            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
        }

        if (request.getParams().get("chfl") != null && !"".equals(request.getParams().get("chfl"))){
            List<Chfl> chfls = chflDao.listAll();
            Long chfl = Long.valueOf(request.getParams().get("chfl").toString());
            List<Long> chflList = new ArrayList<>();
            chflList.add(chfl);
            findAllChfl(chfls,chfl,chflList);
            if (!CollectionUtils.isEmpty(chflList)){
                request.getParams().put("chflList", chflList);
            }
        }
        Map map = new HashMap();

        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));

        int count = chdaDao.count(request.getParams());

        List list = chdaDao.list(request.getParams(), page * limit - limit, limit);

        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("msg", "");

        return map;
    }

    //找到存货分类下所有存货档案
    void findAllChfl(List<Chfl> chfls, Long pid,List<Long> chflList){
        List<Chfl> chfl1 = findChfl(chfls, pid);
        if (!CollectionUtils.isEmpty(chfl1)){
            for (Chfl chfl:chfl1){
                chflList.add(chfl.getId());
                findAllChfl(chfls,chfl.getId(),chflList);
            }
        }else{
            return;
        }
    }

    List<Chfl> findChfl(List<Chfl> chfls, Long id){
        List<Chfl> collect = new ArrayList<>();
        if (collect != null){
            collect = chfls.stream().filter(c -> c.getPid().equals(id)).collect(Collectors.toList());
        }
        return collect;
    }

    @GetMapping("/eleTree")
    @ApiOperation(value = "eleTree部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public Map eleTree() {
        Map map = new HashMap();
        List<Chfl> treeAll = chflDao.listChfl();
//        System.out.println(treeAll);
        Chfl chfl1 = new Chfl();
        chfl1.setId(null);
        chfl1.setCname("全部");
        chfl1.setPid(0l);
        treeAll.add(0,chfl1);


        List<Chfl> list = Lists.newArrayList();
        treeAll.stream().filter(chfl -> chfl.getPid().equals(new Long(0)))
                .forEach(chfl -> setChild(list,treeAll,chfl,""));

//        System.out.println(list);

        map.put("data",list);
        map.put("code",0);
        return map;
    }

    @GetMapping("/chflsm")
    @ApiOperation(value = "商贸存货分类树，不含原料及废品")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public Map chflsm() {
        Map map = new HashMap();
        List<Chfl> treeAll = chflDao.listChflsm();
//        System.out.println(treeAll);
//        Chfl chfl1 = new Chfl();
//        chfl1.setId(null);
//        chfl1.setCname("全部");
//        chfl1.setPid(0l);
//        treeAll.add(0,chfl1);


        List<Chfl> list = Lists.newArrayList();
        treeAll.stream().filter(chfl -> chfl.getPid().equals(new Long(0)))
                .forEach(chfl -> setChild(list,treeAll,chfl,""));

//        System.out.println(list);

        map.put("data",list);
        map.put("code",0);
        return map;
    }

    public Map setChild(List list, List<Chfl> treeAll, Chfl cclass,String ppath){
        Map map = new HashMap();
        map.put("name",cclass.getCname());
        map.put("id",cclass.getId());
        String mypath;
        if(cclass.getId() == null){
            mypath="";
        }else{
            if(ppath==""){
                mypath = cclass.getId().toString();
            }else{
                mypath = ppath + "-" +cclass.getId().toString();
            }
        }

        map.put("ppath", mypath);

        List<Chfl> deptList = treeAll.stream().filter(d -> d.getPid().equals(cclass.getId()))
                .collect(Collectors.toList());

        if (deptList.size() != 0){
            List childList = new ArrayList();

            deptList.stream().forEach(d -> {
                childList.add(setChild(list,treeAll,d,mypath));
            });
            map.put("children",childList);
        }

        if (new Long(0).equals(cclass.getPid()))  list.add(map);
        return map;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        chdaDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Chda> listAll() {
        List<Chda> list = chdaDao.listAll();
        return list;
    }

    @PostMapping("/export")
    @ApiOperation(value = "导出存货档案")
    public void export(PageTableRequest request, HttpServletResponse response) {
        List<Chda> chdas = chdaDao.listByCondition(request.getParams());
        String fileName = "存货档案表";
        if (!CollectionUtils.isEmpty(chdas)) {
            String[] headers = new String[]{"存货编码","存货名称","规格型号","单位","存货分类","零售价","批发价","配货价","长度","米重","件重"};

            List<Object[]> datas = new ArrayList<>(chdas.size());
            for (Chda chda : chdas) {
                Object[] objects = new Object[]{chda.getInvcode(),chda.getInvname(),
                        chda.getInvstd(),chda.getUnit1(),chda.getInvcname(),chda.getIprice(),
                        chda.getViprice(),chda.getIcost(),chda.getIweight(),chda.getC01(),chda.getC02()};
                datas.add(objects);
            }

            ExcelUtil.excelExport(
                    fileName, headers,
                    datas, response);
        }else{
            throw new NullPointerException("没有满足条件的数据。");
        }
    }

    @GetMapping("invnameByChfl/{invcid}")
    @ApiOperation(value = "根据invcid获取一个存货名称")
    public String invnameByChfl(@PathVariable Long invcid) {
        return chdaDao.invnameByChfl(invcid);
    }
}
