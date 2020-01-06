package com.itycu.server.controller;

import java.util.List;

import com.itycu.server.page.table.PageTableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.PositionDao;
import com.itycu.server.model.Position;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionDao positionDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public Position save(@RequestBody Position position) {
        positionDao.save(position);

        return position;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Position get(@PathVariable Long id) {
        return positionDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Position update(@RequestBody Position position) {
        positionDao.update(position);

        return position;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return positionDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Position> list(PageTableRequest request) {
                return positionDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        positionDao.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<Position> listAll() {
        List<Position> list = positionDao.listAll();
        return list;
    }
}
