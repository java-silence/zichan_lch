package com.itycu.server.controller;

import com.google.common.collect.Lists;
import com.itycu.server.dao.ChflDao;
import com.itycu.server.model.Chfl;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chfls")
public class ChflController {

    @Autowired
    private ChflDao chflDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Chfl save(@RequestBody Chfl chfl) {
        chfl.setCreateby(UserUtil.getLoginUser().getId());
        if (chfl.getPid() == null) {

            chfl.setPid( new Long(0));
        }
        chflDao.save(chfl);
        return chfl;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Chfl get(@PathVariable Long id) {
        return chflDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Chfl update(@RequestBody Chfl chfl) {
        chfl.setUpdateby(UserUtil.getLoginUser().getId());
        chflDao.update(chfl);
        return chfl;
    }

/*    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Chfl audit(@PathVariable Long id) {
        Chfl chfl = chflDao.getById(id);

        chfl.setAuditby(UserUtil.getLoginUser().getId());
        chfl.setAuditTime(new Date());
        chfl.setStatus(1);
        chflDao.update(chfl);
        return chfl;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Chfl unaudit(@PathVariable Long id) {
        Chfl chfl = chflDao.getById(id);

        chfl.setAuditby(null);
        chfl.setAuditTime(null);
        chfl.setStatus(0);
        chflDao.update(chfl);
        return chfl;
    }*/

    @GetMapping(value = "/listchfls",params = "type")
    @ApiOperation(value = "列出所有存货分类")
    public List<Chfl> listchfl(String type) {
        List<Chfl> chfls ;
        if(type.equals("topchfl")){
            chfls = chflDao.listTopchfls(0L);
        }else {
            chfls = chflDao.listChfl();
        }

        return chfls;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return chflDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Chfl> list(PageTableRequest request) {
                return chflDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = chflDao.count(request.getParams());

            List list = chflDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }




    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        chflDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Chfl> listAll() {
        List<Chfl> list = chflDao.listAll();
        return list;
    }


    @GetMapping("/treelist")
    @ApiOperation(value = "部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public List<Chfl> treeList() {

        List<Chfl> treeAll = chflDao.listAll();

        List<Chfl> list = Lists.newArrayList();
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
    private void settreeList(Long pId, List<Chfl> treeAll, List<Chfl> list) {
        for (Chfl per : treeAll) {
            if (per.getPid().equals(pId)) {
                list.add(per);
                if (treeAll.stream().filter(p -> p.getPid().equals(per.getId())).findAny() != null) {
                    settreeList(per.getId(), treeAll, list);
                }
            }
        }
    }


    /*@GetMapping("/listpidAndcname")
    @ApiOperation(value = "列出pid和cname")
    public Map listpidAndcname(){

        Map map = new HashMap();

        List<Chfl> listpid = chflDao.listpidAndcname();


        if (listpid.size()!= 0 ){

            List l1 = new ArrayList();

        listpid.stream().forEach(chfl -> {

           Map map1 = new HashMap<>();

              map1.put("pid",chfl.getPid());
              map1.put("cname",chfl.getCname());

              l1.add(map1);


        });

        map.put("data",l1);

            System.out.println(l1);

        }




        System.out.println(map);


        return map;

    }
*/
}
