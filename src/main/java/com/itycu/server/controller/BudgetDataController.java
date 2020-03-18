package com.itycu.server.controller;

import com.itycu.server.model.BudgetData;
import com.itycu.server.model.BudgetDataItem;
import com.itycu.server.service.BudgetDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)表控制层
 *
 * @author makejava
 * @since 2020-03-18 09:05:58
 */
@Api(tags = "预算数据操作")
@RestController
@RequestMapping("/budgetData")
public class BudgetDataController {
    /**
     * 服务对象
     */
    @Resource
    private BudgetDataService budgetDataService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BudgetDataItem selectOne(Integer id) {
        return this.budgetDataService.queryById(id);
    }


    @PostMapping("/save")
    @ApiOperation(value = "保存预算数据信息")
    public Map saveBudgetDataInfo(@RequestBody BudgetData budgetData) {
        Map<String, String> map = new HashMap();
        int result =  budgetDataService.saveBudgetDataInfo(budgetData);
        if(result>0){
            map.put("data", "");
            map.put("code", "200");
            map.put("msg", "操作成功");
            return map;
        }else{
            map.put("count", "");
            map.put("code", "0");
            map.put("msg", "操作失败");
            return map;
        }
    }
}