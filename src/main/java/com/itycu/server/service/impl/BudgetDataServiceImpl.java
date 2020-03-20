package com.itycu.server.service.impl;

import com.itycu.server.dao.BudgetDataDao;
import com.itycu.server.dao.FlowDao;
import com.itycu.server.model.BudgetData;
import com.itycu.server.model.BudgetDataItem;
import com.itycu.server.model.Flow;
import com.itycu.server.service.BudgetDataService;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预算数据表(年度预算和月度预算)(BudgetDataItem)表服务实现类
 *
 * @author makejava
 * @since 2020-03-18 09:05:57
 */
@Service("budgetDataService")
public class BudgetDataServiceImpl implements BudgetDataService {
  @Resource
  private BudgetDataDao budgetDataDao;


  @Autowired
  FlowDao flowDao;

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public BudgetDataItem queryById(Integer id) {
    return this.budgetDataDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit  查询条数
   * @return 对象列表
   */
  @Override
  public List<BudgetDataItem> queryAllByLimit(int offset, int limit) {
    return this.budgetDataDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param budgetDataItem 实例对象
   * @return 实例对象
   */
  @Override
  public BudgetDataItem insert(BudgetDataItem budgetDataItem) {
    this.budgetDataDao.insert(budgetDataItem);
    return budgetDataItem;
  }

  /**
   * 修改数据
   *
   * @param budgetDataItem 实例对象
   * @return 实例对象
   */
  @Override
  public BudgetDataItem update(BudgetDataItem budgetDataItem) {
    this.budgetDataDao.update(budgetDataItem);
    return this.queryById(budgetDataItem.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   *
   *
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
    return this.budgetDataDao.deleteById(id) > 0;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int saveBudgetDataInfo(BudgetData budgetData) {
    if (null != budgetData) {
      List<BudgetDataItem> list = budgetData.getBudgetDataList();
      if (CollectionUtils.isEmpty(list)) {
        return 0;
      }

      //开启流程
      Flow flow = getFlowByName();
      if (null != flow) {
        budgetData.setFlowid(flow.getId().intValue());
      }

      //创建预算编号
      String budgetDataId = createNum();

      //设置基本的信息插入到budget_data表中
      budgetData.setStatus(1);//状态审核中
      budgetData.setUserId(UserUtil.getLoginUser().getId().intValue());
      budgetData.setApplyDeptId(list.get(0).getBudgetDeptId());
      budgetData.setApplyDeptName(list.get(0).getBudgetDeptName());
      budgetData.setGlDeptId(String.valueOf(list.get(0).getBudgetManagerDeptId()));
      budgetData.setGlDeptName(list.get(0).getBudgetManagerName());
      budgetData.setBudgetDataId(budgetDataId);
      int result = budgetDataDao.saveBudgetDataInfo(budgetData);


      if (result > 0) {
        for (BudgetDataItem budgetDataItem : list) {
          budgetDataItem.setBudgetDataId(budgetDataId);
          budgetDataItem.setBudgetKind(String.valueOf(budgetData.getBudgetKind()));
          budgetDataItem.setBudgetType(budgetDataItem.getBudgetType().substring(0, budgetDataItem.getBudgetType().indexOf(" ")));
        }
      }
      /**
       * TODO 需要插入流程id数据
       */
      //基本数据信息插入到budget_data_item表格中
      return budgetDataDao.saveBudgetDataItemInfo(list);
    }
    return 0;
  }

  @Override
  public int countBudgetRecord(Map<String, Object> map) {
    return budgetDataDao.countBudgetRecord(map);
  }

  @Override
  public List<Map<String, Object>> queryBudgetRecordList(Map<String, Object> map, int offset, int limit) {
    return budgetDataDao.queryBudgetRecordList(map, offset, limit);
  }

  @Override
  public List<Map<String, Object>> budgetItemRecordListById(Map<String, Object> map, int offset, int limit) {
    return budgetDataDao.budgetItemRecordListById(map, offset, limit);
  }


  /**
   * 开启预算流程的数据
   *
   * @return 包含流程数据的map
   */
  private Map<String, Object> startBudgetFlow() {
    return null;
  }


  private Flow getFlowByName() {
    Flow flow = flowDao.findByName("预算申请流程");
    return flow;
  }

  /**
   * 创建预算编号
   *
   * @return
   */
  private synchronized String createNum() {
    return String.valueOf(new Date().getTime());
  }

}