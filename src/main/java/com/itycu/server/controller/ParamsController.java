package com.itycu.server.controller;

import com.itycu.server.dao.ParamsDao;
import com.itycu.server.utils.UserUtil;
import com.itycu.server.model.Params;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/paramss")
public class ParamsController {

    @Autowired
    private ParamsDao paramsDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Params save(@RequestBody Params params) {
        params.setCreateby(UserUtil.getLoginUser().getId());
        paramsDao.save(params);
        return params;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Params get(@PathVariable Long id) {
        return paramsDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Params update(@RequestBody Params params) {
        params.setUpdateby(UserUtil.getLoginUser().getId());
        paramsDao.update(params);
        return params;
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public Params audit(@PathVariable Long id) {
        Params params = paramsDao.getById(id);

        params.setAuditby(UserUtil.getLoginUser().getId());
        params.setAuditTime(new Date());
        params.setStatus("1");
        paramsDao.update(params);
        return params;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public Params unaudit(@PathVariable Long id) {
        Params params = paramsDao.getById(id);

        params.setAuditby(null);
        params.setAuditTime(null);
        params.setStatus("0");
        paramsDao.update(params);
        return params;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return paramsDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Params> list(PageTableRequest request) {
                return paramsDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        paramsDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Params> listAll() {
        List<Params> list = paramsDao.listAll();
        return list;
    }

    @GetMapping("/treelist")
    @ApiOperation(value = "参数树列表")
    //@PreAuthorize("hasAuthority('sys:menu:query')")
    public List<Params> treeList() {
        List<Params> treeAll = paramsDao.listAll();

        List<Params> list = Lists.newArrayList();
        settreeList(0L, treeAll, list);
//        System.out.println(list);
        return list;
    }

    /**
     * 参数树列表
     *
     * @param pId
     * @param treeAll
     * @param list
     */
    private void settreeList(Long pId, List<Params> treeAll, List<Params> list) {
        for (Params per : treeAll) {
            if (per.getPid().equals(pId)) {
                list.add(per);
                if (treeAll.stream().filter(p -> p.getPid().equals(per.getId())).findAny() != null) {
                    settreeList(per.getId(), treeAll, list);
                }
            }
        }
    }

}
