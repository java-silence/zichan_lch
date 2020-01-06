package com.itycu.server.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dao.CgDingdansDao;
import com.itycu.server.dto.CgdingdanVO;
import com.itycu.server.service.CgDingdanService;
import com.itycu.server.utils.UserUtil;
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
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.dao.CgDingdanDao;
import com.itycu.server.model.CgDingdan;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cgDingdans")
public class CgDingdanController {

    @Autowired
    private CgDingdanDao cgDingdanDao;

    @Autowired
    private CgDingdansDao cgDingdansDao;

    @Autowired
    private CgDingdanService cgDingdanService;



    @PostMapping
    @ApiOperation(value = "保存")
        public CgDingdan save(@RequestBody CgdingdanVO cgDingdanVO) {

        cgDingdanService.save(cgDingdanVO);
        return cgDingdanVO;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public CgDingdan get(@PathVariable Long id) {
        CgDingdan byId = cgDingdanDao.getById(id);
        return byId;
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public CgdingdanVO update(@RequestBody CgdingdanVO cgdingdanVO) {
        return cgDingdanService.update(cgdingdanVO);
    }

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核")
    public CgDingdan audit(@PathVariable Long id) {
        CgDingdan cgDingdan = cgDingdanDao.getById(id);

        cgDingdan.setAuditby(UserUtil.getLoginUser().getId());
        cgDingdan.setAuditTime(new Date());
        cgDingdan.setStatus("1");
        cgDingdanDao.update(cgDingdan);
        return cgDingdan;
    }

    @PutMapping("/unaudit/{id}")
    @ApiOperation(value = "弃审")
    public CgDingdan unaudit(@PathVariable Long id) {
        CgDingdan cgDingdan = cgDingdanDao.getById(id);

        cgDingdan.setAuditby(null);
        cgDingdan.setAuditTime(null);
        cgDingdan.setStatus("0");
        cgDingdanDao.update(cgDingdan);
        return cgDingdan;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return cgDingdanDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<CgDingdan> list(PageTableRequest request) {
                return cgDingdanDao.list(request.getParams(), request.getOffset(), request.getLimit());
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

            int count = cgDingdanDao.count(request.getParams());

            List list = cgDingdanDao.list(request.getParams(), page*limit-limit, limit);

            map.put("data",list);
            map.put("count",count);
            map.put("code","0");
            map.put("msg","");

            return map;
        }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        cgDingdanDao.delete(id);
        cgDingdansDao.delbypid(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<CgDingdan> listAll() {
        List<CgDingdan> list = cgDingdanDao.listAll();
        return list;
    }
}
