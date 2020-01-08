package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.dto.SysUserDto;
import com.itycu.server.dto.ZcDeployCheckDto;
import com.itycu.server.dto.ZcDeployDto;
import com.itycu.server.model.*;
import com.itycu.server.service.ZcDeployService;
import com.itycu.server.utils.DateUtil;
import com.itycu.server.utils.StrUtil;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lch
 * @create 2019-12-03 21:06
 */
@Service
public class ZcDeployServiceImpl implements ZcDeployService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZcDeployDao zcDeployDao;
    @Autowired
    private ZcDeployItemDao zcDeployItemDao;
    @Autowired
    private FlowDao flowDao;
    @Autowired
    private FlowstepDao flowstepDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TodoDao todoDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private FlowmemberDao flowmemberDao;
    @Autowired
    private FlowTodoItemDao flowTodoItemDao;
    @Autowired
    private ZcInfoDao zcInfoDao;
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public ZcDeployDto save(ZcDeployDto zcDeployDto) {

        // 登录人信息
        LoginUser user = UserUtil.getLoginUser();
        Dept child = deptDao.getById(user.getDeptid());
        Dept parent = deptDao.getById(child.getPid());
        String jx = parent.getJx();
        // 查询数量
        int count1 = zcDeployDao.countByDeptThisYear(parent.getDeptcode());
        String deployNum = jx + "-TP"+DateUtil.getCurrentYear()+"-"+StrUtil.getStaticNum(count1+1,5);
        // 查询调配流程
        Flow flow = flowDao.findByName("资产调配流程");
        Long flowId = flow.getId();
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);
        // 调配主单
        ZcDeploy zcDeploy = (ZcDeploy)zcDeployDto;
        List<ZcDeployItem> zcDeployItemList = zcDeployDto.getZcDeployItems();
        List<String> list = zcDeployItemList.stream().map(e -> String.valueOf(e.getId())).collect(Collectors.toList());
        if (list.size()>0){
            String ids = String.join(",", list);
            zcDeploy.setZcIds(ids);
        }
        zcDeploy.setCreateBy(user.getId());
        zcDeploy.setUpdateBy(user.getId());
        zcDeploy.setApplyUserId(user.getId());
        zcDeploy.setFlowid(flowId);
        zcDeploy.setStatus(0);
        zcDeploy.setApplyDeptId(user.getDeptid());
        zcDeploy.setDel(0);
        zcDeploy.setDeployNum(deployNum);
        zcDeploy.setDeptCode(child.getDeptcode());
        zcDeploy.setStepid(flowsteps.get(0).getId());
        zcDeployDao.save(zcDeploy);
        if (!CollectionUtils.isEmpty(zcDeployItemList)){
            zcDeployItemDao.saves(zcDeployItemList,zcDeploy.getId(),user.getId(),zcDeploy.getBackDeptId());
        }
        log.debug("新增调配申请单{}", zcDeploy.getCreateBy());
        // 类别判断
        if ("1".equals(zcDeployDto.getType())) {
            // 启动流程
            this.startProcess(String.valueOf(zcDeploy.getId()));
        }
        return zcDeployDto;
    }

    @Override
    public void startProcess(String deployId) {

        // 调配主单
        ZcDeploy zcDeploy = zcDeployDao.getById(Long.parseLong(deployId));
        if (zcDeploy.getStatus() !=0 ){
            log.info("【流程已经启动】");
            throw new RuntimeException("流程已经启动");
        }
        Long flowId = zcDeploy.getFlowid();
        // 查询流程
        //Flow flow = flowDao.getById(flowId);
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);
        // 调配子表
        List<ZcDeployItem> zcDeployItems = zcDeployItemDao.listByZcDeployId(zcDeploy.getId());
        // 获取所有的调入部门
        ArrayList<Long> syDeptIdList = getDeptIdList(zcDeployItems,0);
        if (syDeptIdList.size()<=0){
            log.info("【调配资产无使用部门】");
            throw new RuntimeException("调配资产无使用部门");
        }
        // 获取上下节点 0:上一个 1:下一个
        Long nextNodeId = getNextNodeId(1,zcDeploy.getStepid(), flowsteps);
        // 调配调出角色
        Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcDeploy.getFlowid(), nextNodeId);
        // 当前登录用户
        LoginUser loginUser = UserUtil.getLoginUser();
        // 调配子项ID集合
        ArrayList<Long> zcItemIds = new ArrayList<>();
        // 资产集合
        ArrayList<Long> zcids = new ArrayList<>();
        // 循环资产使用部门
        for (Long did : syDeptIdList) {
            Long currentId = did;
            // 根据部门角色信息查询人员信息(第一个)
            SysUser user = userDao.getUserByRoleDept(memidByFlowStep, currentId);
            if ( user == null ){
                throw new RuntimeException("调配资产无审核人员");
            }
            Long id = user.getId();
            // 插入待办任务信息
            Long flowTodoId = saveFlowTodo(id, loginUser, zcDeploy, getFlowstepById(nextNodeId, flowsteps),1);
            for (ZcDeployItem zcDeployItem : zcDeployItems) {
                if ( currentId == zcDeployItem.getSyDeptId() && !zcItemIds.contains(zcDeployItem.getId())){
                    // 属于当前部门信息
                    FlowTodoItem flowTodoItem = new FlowTodoItem();
                    flowTodoItem.setFlowTodoId(flowTodoId);
                    flowTodoItem.setFlowItemId(zcDeployItem.getId());
                    flowTodoItem.setStatus(0);
                    int ressult = flowTodoItemDao.save(flowTodoItem);
                    zcItemIds.add(zcDeployItem.getId());
                    zcids.add(zcDeployItem.getZcId());
                }
            }
        }
        // 跟新调配子项状态 2:调出 3:调入 4:财务
        zcDeployItemDao.updateStatus(zcDeploy.getId(),2);
        // 更新调配主单的状态
        Map<String, Object> params = new HashMap<>();
        params.put("zcDeployId",zcDeploy.getId());
        params.put("status",1);
        params.put("stepid",flowsteps.get(1).getId());
        params.put("updateTime",new Date());
        zcDeployDao.updateStatus(params);
        // 更新资产信息
        // zcInfoDao.updateStatusList(3,zcids);
    }

    @Override
    public String check(ZcDeployCheckDto zcDeployCheckDto) {

        LoginUser loginUser = UserUtil.getLoginUser();
        Long deployId = zcDeployCheckDto.getDeployId();
        Long todoId = zcDeployCheckDto.getTodoId();
        // 调配主表信息
        ZcDeploy zcDeploy = zcDeployDao.getById(deployId);
        // 流程ID
        Long flowid = zcDeploy.getFlowid();
        Long stepid = zcDeploy.getStepid();
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowid);
        Flowstep flowstep = getFlowstepById(stepid,flowsteps);
        //-------------------------------------------------------------
        Todo flowTodo = todoDao.getById(todoId);
        List<FlowTodoItem> flowTodoItems =  flowTodoItemDao.listByToDoId(todoId);
        List<ZcDeployItem> zcDeployItems = zcDeployItemDao.listByZcDeployId(deployId);
        ArrayList<Long> todoItemIds = new ArrayList<>();
        ArrayList<Long> deployItemIds = new ArrayList<>();
        for (FlowTodoItem flowTodoItem : flowTodoItems) {
            todoItemIds.add(flowTodoItem.getId());
            deployItemIds.add(flowTodoItem.getFlowItemId());
        }

        // 处理同意的
        Long itemStatus = zcDeployCheckDto.getItemStatus();
        if (flowTodoItems.size()>0){
            if ( itemStatus == 2 ) {
                flowTodoItemDao.updateListStatus(1,todoItemIds);
                zcDeployItemDao.updateListStatus("dc",1,deployItemIds,loginUser.getId(),loginUser.getNickname());
            }else if ( itemStatus == 3 ) {
                flowTodoItemDao.updateListStatus(1,todoItemIds);
                zcDeployItemDao.updateListStatus("dr",1,deployItemIds,loginUser.getId(),loginUser.getNickname());
            }else if ( itemStatus == 4 ) {
                flowTodoItemDao.updateListStatus(1,todoItemIds);
                zcDeployItemDao.updateListStatus("cwb",1,deployItemIds,loginUser.getId(),loginUser.getNickname());
            }
        }
        // 2,修改当TODO前状态
        flowTodo.setNeirong(zcDeployCheckDto.getNeirong());
        // 待办中两种状态 0:待办理 1:已办理
        flowTodo.setStatus("1");
        todoDao.update(flowTodo);
        // 判断当前节点任务是否完成,完成后指派到下一级
        boolean flag = checkStepFinished(zcDeploy);
        if (flag) {
            // 流程最后为财务部审核
            Flowstep lastFlowstep = flowsteps.get(flowsteps.size() - 1);
            if ( stepid == lastFlowstep.getId() ){
                // 流程已经处理完毕
                Map<String, Object> params = new HashMap<>();
                params.put("status",2);
                params.put("zcDeployId",deployId);
                zcDeployDao.updateStatus(params);
                // 给发起人发送通知消息
                String content = "你与"+DateUtil.format(zcDeploy.getCreateTime())+"申请的【资产调配】已经审核完成，请知悉！";
                Notice notice = new Notice();
                notice.setUserId(zcDeploy.getApplyUserId());
                notice.setTitle("【资产调配】");
                notice.setStatus(0);
                notice.setUpdateTime(null);
                notice.setContent(content);
                noticeDao.save(notice);
                // 更新资产信息
                updateZcInfoStatus(zcDeploy);
                return String.valueOf(zcDeployCheckDto.getItemStatus());
            } else if ( stepid == flowsteps.get(1).getId() ) {
                // 给所有的调配子项赋值调入部门,没有则设置默认的部门信息
                for (ZcDeployItem zcDeployItem : zcDeployItems) {
                    if (ObjectUtils.isEmpty(zcDeployItem.getBackDeptId())) {
                        zcDeployItem.setBackDeptId(zcDeploy.getBackDeptId());
                    }
                }
                //获取所有的调出部门ID
                ArrayList<Long> dcDeptIdList = getDeptIdList(zcDeployItems, 1);
                if (dcDeptIdList.size()<=0){
                    log.info("【调配资产无调出部门】");
                    throw new RuntimeException("调配资产无调出部门");
                }
                // 获取上下节点 0:上一个 1:下一个
                Long nextNodeId = getNextNodeId(1,zcDeploy.getStepid(), flowsteps);
                // 调配调出角色
                Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcDeploy.getFlowid(), nextNodeId);
                // 调配子项ID集合
                ArrayList<Long> zcItemIds = new ArrayList<>();
                // 资产集合
                ArrayList<Long> zcids = new ArrayList<>();
                // 循环资产使用部门
                for (Long did : dcDeptIdList) {
                    Long currentId = did;
                    // 根据部门角色信息查询人员信息(第一个)
                    SysUser user = userDao.getUserByRoleDept(memidByFlowStep, currentId);
                    if ( user == null ){
                        throw new RuntimeException("调配资产无审核人员");
                    }
                    Long id = user.getId();
                    // 插入待办任务信息
                    Long flowTodoId = saveFlowTodo(id, loginUser, zcDeploy, getFlowstepById(nextNodeId, flowsteps),2);
                    for (ZcDeployItem zcDeployItem : zcDeployItems) {
                        if ( currentId == zcDeployItem.getBackDeptId() && !zcItemIds.contains(zcDeployItem.getId())){
                            // 属于当前部门信息
                            FlowTodoItem flowTodoItem = new FlowTodoItem();
                            flowTodoItem.setFlowTodoId(flowTodoId);
                            flowTodoItem.setFlowItemId(zcDeployItem.getId());
                            flowTodoItem.setStatus(0);
                            int ressult = flowTodoItemDao.save(flowTodoItem);
                            zcItemIds.add(zcDeployItem.getId());
                            zcids.add(zcDeployItem.getZcId());
                        }
                    }
                }
                // 跟新调配子项状态 2:调出 3:调入 4:财务
                zcDeployItemDao.updateStatus(zcDeploy.getId(),3);
                // 更新调配主单的状态
                Map<String, Object> params = new HashMap<>();
                params.put("stepid",nextNodeId);
                params.put("updateTime",new Date());
                params.put("zcDeployId",zcDeploy.getId());
                zcDeployDao.updateStatus(params);
            } else if ( stepid == flowsteps.get(2).getId() ) {
                // 给财务插入待办信息 给调入插入待办信息
                Long backDeptId = loginUser.getDeptid();
                // 获取当前用户的上级部门
                Dept byId = deptDao.getById(backDeptId);
                Dept parentDept = deptDao.getById(byId.getPid());
                String deptcode = parentDept.getDeptcode();
                // 下一个节点ID
                Long nextNodeId = getNextNodeId(1, stepid, flowsteps);
                // 调出角色
                Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcDeploy.getFlowid(), nextNodeId);
                // 根据部门角色信息查询人员信息(第一个)
                List<HashMap<String,Object>> userList = userDao.findByRoleIdAndDeptCode(memidByFlowStep,deptcode);
                if ( userList.size() == 0 ){
                    throw new RuntimeException("调配资产无审核人员");
                }
                HashMap<String, Object> user = userList.get(0);
                String userId = String.valueOf(user.get("id"));
                // 插入待办任务信息
                Long flowTodoId = saveFlowTodo(Long.parseLong(userId), loginUser, zcDeploy, getFlowstepById(nextNodeId, flowsteps),3);
                for (ZcDeployItem zcDeployItem : zcDeployItems) {
                    // 属于当前部门信息
                    FlowTodoItem flowTodoItem = new FlowTodoItem();
                    flowTodoItem.setFlowTodoId(flowTodoId);
                    flowTodoItem.setFlowItemId(zcDeployItem.getId());
                    flowTodoItem.setStatus(0);
                    int result = flowTodoItemDao.save(flowTodoItem);
                }
                // 子项状态为2  主表步骤改为下一步
                zcDeployItemDao.updateStatus(deployId,4);
                // 更新报废的状态
                Map<String, Object> params = new HashMap<>();
                params.put("zcDeployId",zcDeploy.getId());
                params.put("stepid",nextNodeId);
                zcDeployDao.updateStatus(params);
            }
        }
        return String.valueOf(zcDeployCheckDto.getItemStatus());
    }

    @Override
    public void updateZcDeploy(ZcDeployDto zcDeployDto) {

        // 修改主项
        ZcDeploy zcDeploy = (ZcDeploy)zcDeployDto;
        Long zcDeployId = zcDeploy.getId();
        // 删除子项
        zcDeployItemDao.deleteByZcDeployId(zcDeployId);
        List<ZcDeployItem> zcDeployItemList = zcDeployDto.getZcDeployItems();
        List<String> list = zcDeployItemList.stream().map(e -> String.valueOf(e.getId())).collect(Collectors.toList());
        if (list.size()>0){
            String ids = String.join(",", list);
            zcDeploy.setZcIds(ids);
        }
        ZcDeploy oldZcDeploy = zcDeployDao.getById(zcDeploy.getId());
        Long applyUserId = oldZcDeploy.getApplyUserId();
        List<String> list1 = Arrays.asList("backDeptId", "description","zcIds");
        BeanUtils.copyProperties(zcDeploy,oldZcDeploy,list1.toArray(new String[list1.size()]));
        zcDeployDao.update(oldZcDeploy);
        // 插入子项
        if (!CollectionUtils.isEmpty(zcDeployItemList)){
            zcDeployItemDao.saves(zcDeployItemList,zcDeploy.getId(),applyUserId,zcDeploy.getBackDeptId());
            log.debug("新增报废申请单{}", zcDeploy.getCreateBy());
            // 类别判断
            if ("1".equals(zcDeployDto.getType())) {
                // 启动流程
                this.startProcess(String.valueOf(zcDeploy.getId()));
            }
        }
    }

    /**
     * 更新资产信息
     * @param zcDeploy
     */
    private void updateZcInfoStatus(ZcDeploy zcDeploy) {

        List<ZcDeployItem> zcDeployItems = zcDeployItemDao.listByZcDeployId(zcDeploy.getId());
        for (ZcDeployItem zcDeployItem : zcDeployItems) {
            ZcInfo zcInfo = new ZcInfo();
            zcInfo.setId(zcDeployItem.getZcId());
            zcInfo.setSyDeptId(zcDeployItem.getBackDeptId());
            zcInfo.setUseStatus(1);
            zcInfo.setUpdateTime(new Date());
            int update = zcInfoDao.update(zcInfo);
        }
    }

    /**
     * 判断当前节点是否完成
     * @param zcDeploy
     * @return
     */
    private boolean checkStepFinished(ZcDeploy zcDeploy) {
        boolean flag = false;
        Long stepid = zcDeploy.getStepid();
        if ( stepid == 54 ){
            Map<String, Object> params = new HashMap<>();
            int count = zcDeployItemDao.countCheck("dc",zcDeploy.getId());
            if (count==0){
                flag = true;
            }
        }
        if ( stepid == 55 ){
            Map<String, Object> params = new HashMap<>();
            int count = zcDeployItemDao.countCheck("dr",zcDeploy.getId());
            if (count==0){
                flag = true;
            }
        }
        if ( stepid == 56 ){
            Map<String, Object> params = new HashMap<>();
            int count = zcDeployItemDao.countCheck("cwb",zcDeploy.getId());
            if (count==0){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 插入待办信息
     * @param auditUserId 审核人
     * @param user 登录人
     * @param zcDeploy 报废主信息
     * @param flowstep 流程步骤
     * @return
     */
    public Long saveFlowTodo( Long auditUserId,SysUser user, ZcDeploy zcDeploy,Flowstep flowstep,Integer memo ){
        Todo todo = new Todo();
        todo.setAuditby(auditUserId);
        todo.setSendby(user.getId());
        todo.setBiaoti("【"+user.getNickname()+"】申请资产调配");
        todo.setNeirong(zcDeploy.getDescription());
        todo.setBizid(zcDeploy.getId());
        todo.setBizcreateby(user.getId());
        todo.setBizdeptid(zcDeploy.getApplyDeptId());
        todo.setStatus("0");
        todo.setFlowid(zcDeploy.getFlowid());
        todo.setStepid(flowstep.getId());
        todo.setMemo(String.valueOf(memo));
        todo.setCreateTime(new Date());
        todo.setUpdateTime(new Date());
        // 流程审核跳转页面
        todo.setUrl(flowstep.getFlowact());
        int save = todoDao.save(todo);
        return todo.getId();
    }

    /**
     * 根据子项获取所有的调入或者调出部门
     * @param zcDeployItems
     * @param type 0:调出 1:调入
     * @return
     */
    public ArrayList<Long> getDeptIdList( List<ZcDeployItem> zcDeployItems,Integer type){
        ArrayList<Long> list = new ArrayList<>();
        TreeSet<Long> zcids = new TreeSet<>();
        for (ZcDeployItem zcDeployItem : zcDeployItems) {
            if (0 == type) {
                if ( !(ObjectUtils.isEmpty(zcDeployItem.getSyDeptId())) ){
                    zcids.add(zcDeployItem.getSyDeptId());
                }
            }else if (1 == type) {
                if ( !(ObjectUtils.isEmpty(zcDeployItem.getBackDeptId())) ){
                    zcids.add(zcDeployItem.getBackDeptId());
                }
            }

        }
        list.addAll(zcids);
        return list;
    }

    /**
     * 获取不重复父ID的部门信息
     * @param deptList
     * @return
     */
    public ArrayList<Long> getParentDeptList( List<Dept> deptList ){
        ArrayList<Long> list = new ArrayList<>();
        TreeSet<Long> zcids = new TreeSet<>();
        for (Dept dept : deptList) {
            if ( dept.getPid() != 0 ){
                zcids.add(dept.getPid());
            }else {
                zcids.add(dept.getId());
            }
        }
        list.addAll(zcids);
        return list;
    }

    /**
     * 获取下一节点ID
     * @param type 0:上一个  1:下一个
     * @param currentId
     * @param flowsteps
     * @return
     */
    public Long getNextNodeId( Integer type, Long currentId,List<Flowstep> flowsteps ){

        LinkedList<Long> ids = new LinkedList<>();
        for (Flowstep flowstep : flowsteps) {
            ids.add(flowstep.getId());
        }
        if (0 == type){
            return ids.get(ids.indexOf(currentId)-1);
        }else if (1 == type){
            return ids.get(ids.indexOf(currentId)+1);
        }
        return 0l;
    }

    /**
     * 根据Id获取节点信息
     * @param currentId
     * @param flowsteps
     * @return
     */
    public Flowstep getFlowstepById( Long currentId,List<Flowstep> flowsteps ){
        Flowstep flowstep = null;
        for (Flowstep flowstep1 : flowsteps) {
            if ( currentId == flowstep1.getId() ) {
                flowstep = flowstep1;
                break;
            }
        }
        return flowstep;
    }

    /**
     * 判断流程是否结束
     * @param currentId
     * @param flowsteps
     * @return
     */
    public boolean checkFinish( Long currentId,List<Flowstep> flowsteps ){
        boolean flag = false;
        if (currentId.equals(flowsteps.get(flowsteps.size()-1).getId())){
            flag = true;
        }
        return flag;
    }

    /**
     * 将部门信息 key: id value: deptcode
     * @return
     */
    public HashMap<Long,String> getDeptMap(){
        HashMap<Long,String> map = new HashMap<>();
        List<Dept> deptList = deptDao.listDepts();
        for (Dept dept : deptList) {
            map.put(dept.getId(),dept.getDeptcode());
        }
        return map;
    }
}
