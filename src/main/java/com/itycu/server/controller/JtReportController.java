package com.itycu.server.controller;


import com.itycu.server.dao.JtreportDao;
import com.itycu.server.model.JtReport;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jtReport")
public class JtReportController {

    @Autowired
    private JtreportDao jtreportDao;

    @GetMapping("/listall")
    @ApiOperation(value = "获取县级资产量")
    public Map getListPieDept(@PathVariable Long id) {
        Map map = new HashMap();
        List<JtReport> list = jtreportDao.list(id);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

}
