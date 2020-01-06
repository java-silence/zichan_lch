package com.itycu.server.controller;

import java.util.List;

import com.itycu.server.dao.CmsNewsDao;
import com.itycu.server.model.CmsNews;
import com.itycu.server.utils.UserUtil;
import com.itycu.server.page.table.PageTableResponse;
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
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cmsNewss")
public class CmsNewsController {

    @Autowired
    private CmsNewsDao cmsNewsDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public CmsNews save(@RequestBody CmsNews cmsNews) {
        cmsNews.setCreateby(UserUtil.getLoginUser().getId());
        cmsNewsDao.save(cmsNews);

        return cmsNews;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public CmsNews get(@PathVariable Long id) {
        return cmsNewsDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public CmsNews update(@RequestBody CmsNews cmsNews) {
        cmsNews.setUpdateby(UserUtil.getLoginUser().getId());
        cmsNewsDao.update(cmsNews);

        return cmsNews;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return cmsNewsDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<CmsNews> list(PageTableRequest request) {
                return cmsNewsDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        cmsNewsDao.delete(id);
    }
}
