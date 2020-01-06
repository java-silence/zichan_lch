package com.itycu.server.controller;

import com.itycu.server.dao.ZxBorrowsDao;
import com.itycu.server.model.Equipment;
import com.itycu.server.model.ZxBorrows;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zxBorrowss")
public class ZxBorrowsController {
    @Autowired
    private ZxBorrowsDao zxBorrowsDao;

    @GetMapping("/listByzxBorrowId")
    @ApiOperation(value = "列出所有数据")
    public Map listByStocktranId(Long zxBorrowId) {
        Map map = new HashMap();

        List<ZxBorrows> list = zxBorrowsDao.getByzxBorrowid(zxBorrowId);

        map.put("data",list);
        map.put("code","0");
        map.put("msg","");

        return map;
    }

    @GetMapping(params = "pid")
    public List<Equipment> roles(Long pid) {
        return zxBorrowsDao.listbypid(pid);
    }
}
