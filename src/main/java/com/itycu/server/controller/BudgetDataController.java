package com.itycu.server.controller;

import com.itycu.server.model.BudgetData;
import com.itycu.server.model.BudgetDataItem;
import com.itycu.server.model.Dept;
import com.itycu.server.model.SysUser;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.BudgetDataService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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





    @GetMapping("/budgetRecordList")
    @ApiOperation(value = "获取预算申请的记录列表")
    public Map queryBudgetRecordList(PageTableRequest request,HttpServletRequest httpServletRequest) {
        Map<String,Object> map = new HashMap();
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
        SysUser sysUser = UserUtil.getLoginUser();
        map.put("userId",sysUser.getId());
        int count = budgetDataService.countBudgetRecord(request.getParams());
        List<Map<String,Object>> list = budgetDataService.queryBudgetRecordList(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","查询成功");
        return map;
    }



    @GetMapping("/budgetItemRecordListById")
    @ApiOperation(value = "获取预算申请的记录列表总的id查找所有的预算子项列表")
    public Map budgetItemRecordListById(PageTableRequest request,
                                        HttpServletRequest httpServletRequest) {
        Map<String,Object> map = new HashMap();
        Integer page = Integer.valueOf((String)request.getParams().get("page"));
        Integer limit = Integer.MAX_VALUE;
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
        map.put("id",(String)request.getParams().get("zcBuyId"));
        List<Map<String,Object>> list = budgetDataService.budgetItemRecordListById(map, page*limit-limit, limit);
        map.put("data",list);
        map.put("code","0");
        map.put("msg","查询成功");
        return map;
    }

}