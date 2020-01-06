package com.itycu.server.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.service.ZcCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.dao.ZcCategoryDao;
import com.itycu.server.model.ZcCategory;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/zcCategorys")
public class ZcCategoryController {

    @Autowired
    private ZcCategoryDao zcCategoryDao;

    @Autowired
    private ZcCategoryService zcCategoryService;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存资产分类")
    @PreAuthorize("hasAuthority('sys:zcCategory:add')")
    public ZcCategory save(@RequestBody ZcCategory zcCategory) {
        return zcCategoryService.save(zcCategory);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcCategory get(@PathVariable Long id) {
        return zcCategoryDao.getById(id);
    }

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改资产分类")
    @PreAuthorize("hasAuthority('sys:zcCategory:edit')")
    public ZcCategory update(@RequestBody ZcCategory zcCategory) {
        return zcCategoryService.update(zcCategory);
    }

        @GetMapping("/layuiList")
        @ApiOperation(value = "列表")
        public Map layuiList(PageTableRequest request) {


            Map map = new HashMap();

            Integer page = Integer.valueOf((String)request.getParams().get("offset"));
            Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

            int count = zcCategoryDao.count(request.getParams());

            List list = zcCategoryDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除资产分类")
    @PreAuthorize("hasAuthority('sys:zcCategory:del')")
    public void delete(@PathVariable Long id) {
        zcCategoryService.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcCategory> listAll() {
        List<ZcCategory> list = zcCategoryDao.listAll();
        return list;
    }

    @GetMapping("/treelist")
    @ApiOperation(value = "资产分类树列表")
    @PreAuthorize("hasAuthority('sys:zcCategory:query')")
    public List<ZcCategory> treeList() {
        List<ZcCategory> treeAll = zcCategoryDao.listAll();

        List<ZcCategory> list = Lists.newArrayList();
        settreeList(0L, treeAll, list);
        return list;
    }

    /**
     * 资产分类树列表
     *
     * @param pId
     * @param treeAll
     * @param list
     */
    private void settreeList(Long pId, List<ZcCategory> treeAll, List<ZcCategory> list) {
        for (ZcCategory zcCategory : treeAll) {
            if (zcCategory.getPid().equals(pId)) {
                list.add(zcCategory);
                if (treeAll.stream().filter(p -> p.getPid().equals(zcCategory.getId())).findAny() != null) {
                    settreeList(zcCategory.getId(), treeAll, list);
                }
            }
        }
    }

    @GetMapping("/eleTree")
    @ApiOperation(value = "eleTree资产分类树列表")
    public Map eleTree() {
        Map map = new HashMap();
        List<ZcCategory> treeAll = zcCategoryDao.listAll();
//        ZcCategory zcCategoryAll = new ZcCategory();
//        zcCategoryAll.setId(null);
//        zcCategoryAll.setName("-请选择分类-");
//        zcCategoryAll.setPid(0l);
//        treeAll.add(0,zcCategoryAll);
        List<ZcCategory> list = Lists.newArrayList();
        treeAll.stream().filter(zcCategory -> zcCategory.getPid().equals(new Long(0)))
                .forEach(zcCategory -> setChild(list,treeAll,zcCategory));
        map.put("data",list);
        map.put("code",0);
        return map;
    }

    public Map setChild(List list,List<ZcCategory> treeAll,ZcCategory zcCategory){
        Map map = new HashMap();
        map.put("name",zcCategory.getName());
        map.put("catCode",zcCategory.getCatCode());
        map.put("id",zcCategory.getId());
        List<ZcCategory> zcCategoryList = treeAll.stream().filter(d -> d.getPid().equals(zcCategory.getId()))
                .collect(Collectors.toList());
        if (zcCategoryList.size() != 0){
            List childList = new ArrayList();
            zcCategoryList.stream().forEach(d -> {
                childList.add(setChild(list,treeAll,d));
            });
            map.put("children",childList);
        }
        if (new Long(0).equals(zcCategory.getPid()))  list.add(map);
        return map;
    }
}
