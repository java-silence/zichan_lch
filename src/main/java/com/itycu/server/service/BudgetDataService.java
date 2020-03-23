package com.itycu.server.service;

import com.itycu.server.model.BudgetData;
import com.itycu.server.model.BudgetDataItem;

import java.util.List;
import java.util.Map;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)表服务接口
 *
 * @author makejava
 * @since 2020-03-18 09:05:51
 */
public interface BudgetDataService {

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  BudgetDataItem queryById(Integer id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  List<BudgetDataItem> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param budgetDataItem 实例对象
   * @return 实例对象
   */
  BudgetDataItem insert(BudgetDataItem budgetDataItem);

  /**
   * 修改数据
   *
   * @param budgetDataItem 实例对象
   * @return 实例对象
   */
  BudgetDataItem update(BudgetDataItem budgetDataItem);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);


  int saveBudgetDataInfo(BudgetData budgetData);


  /**
   * 计算总共有多少的预算记录
   *
   * @param map
   * @return
   */
  int countBudgetRecord(Map<String, Object> map);


  /**
   * 查询预算的记录列表
   *
   * @param map    查询参数
   * @param offset 起始页
   * @param limit
   * @return
   */
  List<Map<String, Object>> queryBudgetRecordList(Map<String, Object> map, int offset, int limit);


  /**
   * 获取预算记录的列表
   *
   * @param map
   * @param offset
   * @param limit
   * @return
   */
  List<Map<String, Object>> budgetItemRecordListById(Map<String, Object> map, int offset, int limit);


  List<Map<String, Object>> getBudgetItemDetailListByTodoId(Map<String, Object> map, int offset, int limit);


  /**
   * 获取todo页面的初始化数据
   *
   * @param map
   * @return
   */
  BudgetData getTodoInitData(Map<String, Object> map);


  /**
   * 获取审核的动态数据
   * @param map
   * @return
   */
  List<Map<String,Object>>  getTodoCheckList(Map<String, Object> map);



  int passCheck(String todoId,String c03);

}