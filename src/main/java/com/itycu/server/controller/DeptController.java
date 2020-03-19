package com.itycu.server.controller;

import com.google.common.collect.Lists;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.model.Dept;
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
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptDao deptDao;
    @Autowired
    private PermissionDao permissionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Dept save(@RequestBody Dept dept) {
        if (dept.getPid() == null) {
            dept.setPid(0L);
        }
        deptDao.save(dept);
        return dept;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Dept get(@PathVariable Long id) {
        return deptDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Dept update(@RequestBody Dept dept) {
        deptDao.update(dept);

        return dept;
    }

    @GetMapping
    @ApiOperation(value = "分页列表（非部门树）")
    public PageTableResponse list(PageTableRequest request) {
        request.getParams().put("ctype", "收费站");
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return deptDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Dept> list(PageTableRequest request) {
                return deptDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }


    @GetMapping("/list2")
    @ApiOperation(value = "列表")
    //    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request) {

        if (permissionDao.hasPermission(UserUtil.getLoginUser().getId(), "sys:dept:queryall") == 0) {
            request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
        }

        request.getParams().put("ctype", "收费站");
        Map map = new HashMap();

        Integer page = Integer.valueOf((String) request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String) request.getParams().get("limit"));

        int count = deptDao.count(request.getParams());

        List list = deptDao.list(request.getParams(), page * limit - limit, limit);

        map.put("data", list);
        map.put("count", count);
        map.put("code", "0");
        map.put("msg", "");

        return map;
    }

    @GetMapping(value = "/listdepts", params = "type")
    @ApiOperation(value = "列出所有部门")
    public List<Dept> listDepts(String type) {
        List<Dept> depts;
        if (type.equals("topdept")) {
            depts = deptDao.listTopDepts(0L);
        } else {
            depts = deptDao.listDepts();
        }
        return depts;
    }

    @GetMapping(value = "/listzhan", params = "type")
    @ApiOperation(value = "列出所有收费站，即ctype=收费站")
    public List<Dept> listzhan() {
        List<Dept> depts;
        depts = deptDao.listzhan();
        return depts;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        deptDao.delete(id);
    }


    @GetMapping("/treelist")
    @ApiOperation(value = "部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public List<Dept> treeList() {
        List<Dept> treeAll = deptDao.listDepts();
        List<Dept> list = Lists.newArrayList();
        settreeList(0L, treeAll, list);
        return list;
    }

    /**
     * 部门树列表
     *
     * @param pId
     * @param treeAll
     * @param list
     */
    private void settreeList(Long pId, List<Dept> treeAll, List<Dept> list) {
        for (Dept per : treeAll) {
            if (per.getPid().equals(pId)) {
                list.add(per);
                if (treeAll.stream().filter(p -> p.getPid().equals(per.getId())).findAny() != null) {
                    settreeList(per.getId(), treeAll, list);
                }
            }
        }
    }

    @GetMapping("/eleTreeSelf")
    @ApiOperation(value = "查询所在的管理部门")
    public Map eleTreeSelf() {
        Map map = new HashMap();
        List<Map> list = new LinkedList<>();
        long deptid = UserUtil.getLoginUser().getDeptid();
        Dept userDept = deptDao.getById(deptid);
        Long id = userDept.getPid();
        // 查询下级所有的管理部门
        List<Dept> depts = deptDao.listChildGlDept(id, 3l);
        for (Dept dept : depts) {
            Map map1 = new HashMap();
            map1.put("name", dept.getDeptname());
            map1.put("id", dept.getId());
            map1.put("deptcode", dept.getDeptcode());
            list.add(map1);
        }
        map.put("data", list);
        map.put("code", 0);
        return map;
    }

    /**
     * 列出垣曲县的管理部门
     * @return
     */
    @GetMapping("/eleTreeGlDept")
    @ApiOperation(value = "查询所在的管理部门")
    public Map eleTreeGlDept() {
        Map map = new HashMap();
        List<Map> list = new LinkedList<>();
        // 查询下级所有的管理部门
        List<Dept> depts = deptDao.listChildGlDept(11l, 3l);
        for (Dept dept : depts) {
            Map map1 = new HashMap();
            map1.put("name", dept.getDeptname());
            map1.put("id", dept.getId());
            map1.put("deptcode", dept.getDeptcode());
            list.add(map1);
        }
        map.put("data", list);
        map.put("code", 0);
        return map;
    }

    @GetMapping("/eleTree")
    @ApiOperation(value = "eleTree部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public Map eleTree() {
        Map map = new HashMap();
        List<Dept> treeAll = deptDao.listDepts();
        Dept deptAll = new Dept();
        deptAll.setId(null);
        deptAll.setDeptname("请选择部门");
        deptAll.setPid(0l);
        treeAll.add(0, deptAll);
        List<Dept> list = Lists.newArrayList();
        treeAll.stream().filter(dept -> dept.getPid().equals(new Long(0)))
                .forEach(dept -> setChild(list, treeAll, dept));
        map.put("data", list);
        map.put("code", 0);
        return map;
    }

    @GetMapping("/xmSelectTree")
    @ApiOperation(value = "eleTree部门树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public Map xmSelectTree(PageTableRequest request) {
        //根据当前登录人确定部门，
        //判断角色是管理部门角色还是使用部门角色
        //管理角色查询当前公司所有部门
        Map map = new HashMap();
        long deptid = UserUtil.getLoginUser().getDeptid();
        String deptCode = "%%";
        if (deptid != 0) {
            Dept modelDept = deptDao.getById(UserUtil.getLoginUser().getDeptid());
            String zhfhgl = modelDept.getZhfhgl();
            if (zhfhgl.equals("0") || zhfhgl.equals("1") || zhfhgl.equals("3") || "cwb".equals(modelDept.getC03())) {
                deptCode = modelDept.getDeptcode().substring(0, 2) + "%";
            }
        }
        request.getParams().put("deptcode", deptCode);
        List<Dept> treeAll = deptDao.listDeptxm(request.getParams());
        List<Dept> list = Lists.newArrayList();
        treeAll.stream().filter(dept -> dept.getPid().equals(new Long(0)))
                .forEach(dept -> setChild(list, treeAll, dept));
        map.put("data", list);
        map.put("code", 0);
        return map;
    }

    @GetMapping("/panDianDeptTree")
    @ApiOperation(value = "eleTree部门树列表")
    public Map<String, Object> getDeptTree(PageTableRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = Lists.newArrayList();
        long deptid = UserUtil.getLoginUser().getDeptid();
        if (deptid != 0) {
            Dept modelDept = deptDao.getById(UserUtil.getLoginUser().getDeptid());
            if ("cwb".equals(modelDept.getC03()) ||
                    "zhb".equals(modelDept.getC03()) ||
                    "yyb".equals(modelDept.getC03()) ||
                    "kjb".equals(modelDept.getC03()) ||
                    "bwb".equals(modelDept.getC03())
            ) {
                Dept parent = deptDao.queryDeptByPid(modelDept.getId());
                if(parent.getPid()==0){
                    List<Map<String,Object>> resultMapList = new ArrayList<>();
                    Map<String,Object> resultMap = new HashMap<>();
                    resultMap.put("name", parent.getDeptname());
                    resultMap.put("id", parent.getId());
                    resultMap.put("deptcode", parent.getDeptcode());
                    List<Dept> resultList = deptDao.queryAllSonDeptByPid(parent.getId());
                    List childList = new ArrayList();
                    for(Dept result: resultList){
                        Map<String,Object> objectMap = new HashMap<>();
                        objectMap.put("name", result.getDeptname());
                        objectMap.put("id", result.getId());
                        objectMap.put("deptcode", result.getDeptcode());
                        childList.add(objectMap);
                    }
                    resultMap.put("children",childList);
                    resultMapList.add(resultMap);
                    map.put("data", resultMapList);
                    map.put("code", 0);
                }
            } else {
                Dept parent = deptDao.queryDeptByPid(modelDept.getId());
                if(parent.getPid()==0){
                    List<Map<String,Object>> resultMapList = new ArrayList<>();
                    Map<String,Object> resultMap = new HashMap<>();
                    resultMap.put("name", parent.getDeptname());
                    resultMap.put("id", parent.getId());
                    resultMap.put("deptcode", parent.getDeptcode());
                    List<Dept> resultList = deptDao.queryAllSonDeptByPid(parent.getId());
                    List childList = new ArrayList();
                    for(Dept result: resultList){
                        if(result.getId()==(deptid)){
                            Map<String,Object> objectMap = new HashMap<>();
                            objectMap.put("name", result.getDeptname());
                            objectMap.put("id", result.getId());
                            objectMap.put("deptcode", result.getDeptcode());
                            childList.add(objectMap);
                        }
                    }
                    resultMap.put("children",childList);
                    resultMapList.add(resultMap);
                    map.put("data", resultMapList);
                    map.put("code", 0);
                }
            }
        }
        return map;
    }

    @GetMapping("/JtdeptTree")
    @ApiOperation(value = "集团获取部门树")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public Map JtdeptTree(PageTableRequest request) {
        Map map = new HashMap();
        long deptid = UserUtil.getLoginUser().getDeptid();
        String deptCode = "%%";
        if (deptid != 0) {
            Dept modelDept = deptDao.getById(UserUtil.getLoginUser().getDeptid());

            deptCode = modelDept.getDeptcode().substring(0, 2) + "%";
        }

        List<Dept> treeAll = deptDao.jtlistDept(deptCode);

        List<Dept> list = Lists.newArrayList();
        treeAll.stream().filter(dept -> dept.getPid().equals(new Long(0)))
                .forEach(dept -> setChild(list, treeAll, dept));
        map.put("data", list);
        map.put("code", 0);
        return map;
    }

    public Map setChild(List list, List<Dept> treeAll, Dept dept) {
        Map map = new HashMap();
        map.put("name", dept.getDeptname());
        map.put("id", dept.getId());
        map.put("deptcode", dept.getDeptcode());
        List<Dept> deptList = treeAll.stream().filter(d -> d.getPid().equals(dept.getId()))
                .collect(Collectors.toList());
        if (deptList.size() != 0) {
            List childList = new ArrayList();
            deptList.stream().forEach(d -> {
                childList.add(setChild(list, treeAll, d));
            });
            map.put("children", childList);
        }
        if (new Long(0).equals(dept.getPid())) list.add(map);
        return map;
    }

    @GetMapping("/getLoginUserDept")
    @ApiOperation(value = "查询登录用户部门信息")
    public Map getLoginUserDept() {
        Map map = new HashMap();
        long deptid = UserUtil.getLoginUser().getDeptid();
        Dept dept = deptDao.getById(deptid);
        map.put("deptName", dept.getDeptname());
        map.put("deptId", dept.getId());
        if (dept.getPid() != null) {
            Dept pDept = deptDao.getById(dept.getPid());
            if (pDept != null) {
                map.put("pDeptName", pDept.getDeptname());
                map.put("pDeptId", pDept.getId());
            }
        }
        return map;
    }

}
