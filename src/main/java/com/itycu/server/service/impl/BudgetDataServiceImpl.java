package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.model.*;
import com.itycu.server.service.BudgetDataService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


  private static Logger logger = LoggerFactory.getLogger(BudgetDataServiceImpl.class);

  @Resource
  private BudgetDataDao budgetDataDao;

  @Autowired
  private TodoDao todoDao;


  private String budgetURL = "budget/auditBudget.html";

  @Autowired
  FlowDao flowDao;

  @Autowired
  DeptDao deptDao;

  @Autowired
  UserDao userDao;

  @Autowired
  private FlowstepDao flowstepDao;

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
      Long flowId = null;
      Flow flow = getFlowByName();
      if (null != flow) {
        logger.info("获得的FlowId==={}", flow.getId());
        flowId = flow.getId();
        budgetData.setFlowid(flowId.intValue());
      }

      //创建预算编号
      String budgetDataId = createNum();
      //设置基本的信息插入到budget_data表中
      budgetData.setStatus(1);//状态审核中
      budgetData.setUserId(UserUtil.getLoginUser().getId().intValue());
      int applyDeptId = budgetData.getApplyDeptId();
      String applyDeptName = budgetData.getApplyDeptName();
      String glDeptId = budgetData.getGlDeptId();
      String glDeptName = budgetData.getGlDeptName();
      budgetData.setBudgetDataId(budgetDataId);
      int result = budgetDataDao.saveBudgetDataInfo(budgetData);
      if (result > 0) {
        //将预算审核的各项列表数据插入到数据库中
        for (BudgetDataItem budgetDataItem : list) {
          budgetDataItem.setBudgetDataId(budgetDataId);
          budgetDataItem.setBudgetKind(String.valueOf(budgetData.getBudgetKind()));
          budgetDataItem.setApplyDeptId(applyDeptId);
          budgetDataItem.setApplyDeptName(applyDeptName);
          budgetDataItem.setGlDeptId(Integer.parseInt(glDeptId));
          budgetDataItem.setGlDeptName(glDeptName);
          //设置为待审核状态
          budgetDataItem.setBudgetC01("0");
          budgetDataItem.setBudgetType(budgetDataItem.getBudgetType().substring(0, budgetDataItem.getBudgetType().indexOf(" ")));
        }
      }
      //创建待办事项到数据库中
      int id = budgetData.getId();
      //是否是使用部门首次创建todo
      boolean init = true;
      boolean aggree = false;
      createToDoInfo(applyDeptId, applyDeptName, glDeptId, flowId, id, budgetData.getBudgetKind(), init, aggree);
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

  @Override
  public List<Map<String, Object>> getBudgetItemDetailListByTodoId(Map<String, Object> map, int offset, int limit) {
    return budgetDataDao.getBudgetItemDetailListByTodoId(map, offset, limit);
  }

  @Override
  public BudgetData getTodoInitData(Map<String, Object> map) {
    return budgetDataDao.getTodoInitData(map);
  }

  @Override
  public List<Map<String, Object>> getTodoCheckList(Map<String, Object> map) {
    return budgetDataDao.getTodoCheckList(map);
  }

  @Override
  public int passCheck(String todoId, String c03) {
    //是否是使用部门首次创建todo
    boolean init = false;
    boolean aggree = false;
    logger.info("获得的用户身份是===>{}", c03);
    Todo todo = todoDao.getById(Long.parseLong(todoId));
    if (null != todo) {
      //查找财务部的id
      String glDeptId = budgetDataDao.queryGldeptManagerDeptById("cwb");
      long bizid = todo.getBizid();
      //财务部的用户身份审核
      if ("cwb".equals(c03)) {
        //20表示业务类型数据预算审核的数据
        if ("20".equals(todo.getBizid())) {
          String glStatus = null;
          //审核通过的状态
          String cWStatus = "1";
          updateBudgetItemCheckStatus(bizid, glStatus, cWStatus);
          SysUser sysUser = UserUtil.getLoginUser();
          BudgetData budgetData = budgetDataDao.queryBudgetDataById(todo.getBizid().intValue());
          createToDoInfo(sysUser.getId().intValue(),
              sysUser.getNickname(),
              glDeptId,
              todo.getFlowid(),
              todo.getBizid().intValue(),
              budgetData.getBudgetKind(), init,aggree);
        }
        //其他部门的审核
      } else if ("zhb".equals(c03)
          || "kjb".equals(c03)
          || "yyb".equals(c03)
          || "bwb".equals(c03)) {
        //20表示业务类型数据预算审核的数据
        if ("20".equals(todo.getBizid())) {
          //管理部门审核状态
          String glStatus = "1";
          // 财务待审核状态
          String cWStatus = "0";
          //更新预算子单数据的状态
          updateBudgetItemCheckStatus(bizid, glStatus, cWStatus);
          SysUser sysUser = UserUtil.getLoginUser();
          BudgetData budgetData = budgetDataDao.queryBudgetDataById(todo.getBizid().intValue());
          createToDoInfo(sysUser.getId().intValue(),
              sysUser.getNickname(),
              glDeptId,
              todo.getFlowid(),
              todo.getBizid().intValue(),
              budgetData.getBudgetKind(),
              init,aggree);
        }
      } else {
        return 0;
      }
    }
    return 0;
  }


  private void updateBudgetItemCheckStatus(long bizid, String glStatus, String cWStatus) {
    budgetDataDao.updateBudegetItemCheckStatus(bizid, glStatus, cWStatus);
  }


  /**
   * 开启预算流程的数据
   *
   * @return 包含流程数据的map
   */
  private int createToDoInfo(int applyDeptId, String applyDeptName, String glDeptId,
                              Long flowId, int bizid, int kind, boolean init, boolean aggree) {
    logger.info("applyDeptId==>{},applyDeptName=={}," +
                "glDeptId=={},flowId=={},bizid=={}," +
                "kind=={},kind=={},aggree=={}",
                 applyDeptId,applyDeptName,glDeptId,flowId,bizid,kind,kind,aggree);
    try {
      Todo todoInfo = new Todo();
      todoInfo.setStatus("0");
      todoInfo.setNeirong("");
      todoInfo.setType(0);//审核状态
      //设置审核人的id
      todoInfo.setAuditby(findUserByDeptId(String.valueOf(glDeptId)));
      //设置发送人的id
      todoInfo.setSendby(findUserByDeptId(String.valueOf(applyDeptId)));
      if (kind == 1) {
        todoInfo.setBiaoti("【" + applyDeptName + "】月度预算申请");
        todoInfo.setNeirong(applyDeptName + "月度预算申请");
      } else {
        todoInfo.setBiaoti("【" + applyDeptName + "】年度预算申请");
        todoInfo.setNeirong(applyDeptName + "年度预算申请");
      }
      //表示首次有使用部门创建
      if (init) {
        //表示审核意见是同意
        todoInfo.setC02("同意");
        //表示审核状态通过审核
        todoInfo.setC03("1");
        //表示业务流程开始的地方
        todoInfo.setTodoIds("start");
        //2表示有管理部门的审核步骤id
        todoInfo.setStepid(queryFlowStepIdByFlowId(flowId, 1));
      } else {
        //审核状态是同意
        if (aggree) {
          //表示审核意见是同意
          todoInfo.setC02("同意");
          //表示审核状态通过审核
          todoInfo.setC03("1");
        } else {
          //表示审核意见是同意
          todoInfo.setC02("不同意");
          //表示审核状态通过审核
          todoInfo.setC03("0");
        }
      }
      //设置业务类型的为20L，预算审核的流程类型都是20L
      todoInfo.setBiztype("20");
      todoInfo.setFlowid(flowId);
      todoInfo.setUrl(budgetURL);
      todoInfo.setCreateTime(new Date());
      todoInfo.setUpdateTime(new Date());
      //设置业务单据的部门
      todoInfo.setBizdeptid(new Long(applyDeptId));
      todoInfo.setBizid(new Long(bizid));
      //单据创始人
      todoInfo.setBizcreateby(UserUtil.getLoginUser().getId());
       return   todoDao.save(todoInfo);
    } catch (Exception e) {
      logger.error("【预算审核流程】插入数据错误==>>>" + e.getMessage());
      return 0;
    }
  }


  /**
   * 根据部门的ID查找用户的ID
   *
   * @param applyDeptId
   * @return
   */
  private Long findUserByDeptId(String applyDeptId) {
    SysUser sysUser = userDao.getByDeptId(applyDeptId);
    return null == sysUser ? null : sysUser.getId();
  }


  private Long queryFlowStepIdByFlowId(long flowId, long stepid) {
    Flowstep flowstep = flowstepDao.getByStpeId(flowId, stepid);
    return flowstep.getId();
  }


  /**
   * 根据名称查找流程的名称。
   *
   * @return
   */
  private Flow getFlowByName() {
    Flow flow = flowDao.findByName("预算审核流程");
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