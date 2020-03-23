package com.itycu.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.itycu.server.app.vo.todovo.AppTodoVO;
import com.itycu.server.model.*;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.BudgetDataService;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.UserUtil;
import com.zaxxer.hikari.util.SuspendResumeLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
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

  @Autowired
  private TodoService todoService;

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
    int result = budgetDataService.saveBudgetDataInfo(budgetData);
    if (result > 0) {
      map.put("data", "");
      map.put("code", "200");
      map.put("msg", "操作成功");
      return map;
    } else {
      map.put("count", "");
      map.put("code", "0");
      map.put("msg", "操作失败");
      return map;
    }
  }


  @GetMapping("/budgetRecordList")
  @ApiOperation(value = "获取预算申请的记录列表")
  public Map queryBudgetRecordList(PageTableRequest request, HttpServletRequest httpServletRequest) {
    Map<String, Object> map = new HashMap();
    Integer page = Integer.valueOf((String) request.getParams().get("offset"));
    Integer limit = Integer.valueOf((String) request.getParams().get("limit"));
    DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
    SysUser sysUser = UserUtil.getLoginUser();
    map.put("userId", sysUser.getId());
    int count = budgetDataService.countBudgetRecord(request.getParams());
    List<Map<String, Object>> list = budgetDataService.queryBudgetRecordList(request.getParams(), page * limit - limit, limit);
    map.put("data", list);
    map.put("count", count);
    map.put("code", "0");
    map.put("msg", "查询成功");
    return map;
  }


  @GetMapping("/budgetItemRecordListById")
  @ApiOperation(value = "获取预算申请的记录列表总的id查找所有的预算子项列表")
  public Map getBudgetItemRecordListById(PageTableRequest request,
                                         HttpServletRequest httpServletRequest) {
    Map<String, Object> map = new HashMap();
    Integer page = Integer.valueOf((String) request.getParams().get("page"));
    Integer limit = Integer.MAX_VALUE;
    DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
    map.put("id", (String) request.getParams().get("zcBuyId"));
    List<Map<String, Object>> list = budgetDataService.budgetItemRecordListById(map, page * limit - limit, limit);
    map.put("data", list);
    map.put("code", "0");
    map.put("msg", "查询成功");
    return map;
  }


  @GetMapping("/getBudgetItemDetailListByTodoId")
  @ApiOperation(value = "获取审核页面的预算审核数据子列表")
  public Map getBudgetItemDetailListByTodoId(PageTableRequest request,
                                             HttpServletRequest httpServletRequest) {
    Map<String, Object> map = new HashMap<String, Object>();
    Integer page = Integer.valueOf((String) request.getParams().get("page"));
    Integer limit = Integer.MAX_VALUE;
    DynamicConditionUtil.dynamicCondition(request, httpServletRequest);
    map.put("id", httpServletRequest.getParameter("todoId"));
    List<Map<String, Object>> list = budgetDataService.getBudgetItemDetailListByTodoId(map, page * limit - limit, limit);
    map.put("data", list);
    map.put("code", "0");
    map.put("msg", "查询成功");
    return map;
  }


  @GetMapping("/initData")
  @ApiOperation(value = "待办页面数据的初始化")
  public Map<String, Object> getTodoInitData(PageTableRequest request,
                                             HttpServletRequest httpServletRequest) {
    Map<String, Object> map = new HashMap<String, Object>();
    JSONObject jsonObject = new JSONObject();
    map.put("id", httpServletRequest.getParameter("todoId"));
    BudgetData data = budgetDataService.getTodoInitData(map);
    map.put("data", data);
    map.put("code", "0");
    map.put("msg", "查询成功");
    return map;
  }


  @GetMapping("/checkRecord")
  @ApiOperation(value = "获取页面的审批动态数据")
  public Map<String, Object> getTodoCheckList(PageTableRequest request,
                                              HttpServletRequest httpServletRequest) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("bizid", httpServletRequest.getParameter("bizid"));
    map.put("flowid", httpServletRequest.getParameter("flowid"));
    List<Map<String, Object>> data = budgetDataService.getTodoCheckList(map);
    map.put("data", data);
    map.put("code", "0");
    map.put("msg", "查询成功");
    return map;
  }


  @PostMapping("/passCheck")
  @ApiOperation(value = "预算审核通过审核")
  public Map<String, Object> passCheck(
                                       HttpServletRequest httpServletRequest) {
    Map<String, Object> map = new HashMap<String, Object>();
    String todoId = httpServletRequest.getParameter("todoId");
    SysUser sysUser = UserUtil.getLoginUser();
    if (null != sysUser) {

        int result = budgetDataService.passCheck(todoId,sysUser.getC03());
        if (result > 0) {
            map.put("data", null);
            map.put("code", "0");
            map.put("msg", "操作成功");
        }
         else {
        map.put("data", null);
        map.put("code", "500");
        map.put("msg", "操作失败");
      }
      return map;
    }
    map.put("data", null);
    map.put("code", "500");
    map.put("msg", "操作失败");
    return map;
  }


  @PostMapping("/notPassCheck")
  @ApiOperation(value = "预算审核不通过")
  public Map<String, Object> notPassCheck(PageTableRequest request,
                                          HttpServletRequest httpServletRequest) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("todoId", httpServletRequest.getParameter("todoId"));
    SysUser sysUser = UserUtil.getLoginUser();
    if (null != sysUser) {
      //财务部的用户身份审核
      if ("cwb".equals(sysUser.getC03())) {


        //其他部门的审核
      } else if ("zhb".equals(sysUser.getC03())
          || "kjb".equals(sysUser.getC03())
          || "yyb".equals(sysUser.getC03())
          || "bwb".equals(sysUser.getC03())) {

      } else {
        map.put("data", null);
        map.put("code", "0");
        map.put("msg", "操作失败");
      }
      return map;
    }
    map.put("data", null);
    map.put("code", "0");
    map.put("msg", "操作失败");
    return map;
  }
}