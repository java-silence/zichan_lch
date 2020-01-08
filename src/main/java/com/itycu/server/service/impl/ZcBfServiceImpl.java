package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.dto.*;
import com.itycu.server.model.*;
import com.itycu.server.service.ZcBfService;
import com.itycu.server.utils.DateUtil;
import com.itycu.server.utils.StrUtil;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZcBfServiceImpl implements ZcBfService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    public static final String bfActionUrl = "zcbf/auditZcBf.html";
    public static final String cwbfActionUrl = "zcbf/cwauditZcBf.html";
    @Autowired
    private ZcBfDao zcBfDao;
    @Autowired
    private ZcBfItemDao zcBfItemDao;
    @Autowired
    private FlowDao flowDao;
    @Autowired
    private FlowstepDao flowstepDao;
    @Autowired
    private FlowDeptUserDao flowDeptUserDao;
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
    private ZcBfCwitemDao zcBfCwitemDao;
    @Autowired
    private NoticeDao noticeDao;

    @Override
    @Transactional
    public Map save(ZcBfDto zcBfDto) {

        HashMap<String, Object> map = new HashMap<>();

        // 登录人信息
        LoginUser user = UserUtil.getLoginUser();
        Dept child = deptDao.getById(user.getDeptid());
        Dept parent = deptDao.getById(child.getPid());
        String jx = parent.getJx();
        // 查询数量
        int count1 = zcBfDao.countByDeptThisYear(parent.getDeptcode());
        String bfNum = jx + "-BF"+DateUtil.getCurrentYear()+"-"+StrUtil.getStaticNum(count1+1,5);
        Date createTime = zcBfDto.getCreateTime();
        Integer count = zcBfDao.countRecord(zcBfDto.getCreateTime(),user.getDeptid());
//        if (count > 0) {
//            map.put("code","1");
//            map.put("msg","该部门本年度已提交报废,不能再次提交!");
//            return map;
//        }
        // 查询报废流程
        Flow flow = flowDao.findByName("资产报废流程");
        Long flowId = flow.getId();
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);
        // 报废主单
        ZcBf zcBf = (ZcBf)zcBfDto;
        List<ZcBfItem> zcBfItemList = zcBfDto.getZcBfItemList();
        List<String> list = zcBfItemList.stream().map(e -> String.valueOf(e.getId())).collect(Collectors.toList());
        if (list.size()>0){
            String ids = String.join(",", list);
            zcBf.setZcIds(ids);
        }
        zcBf.setCreateBy(user.getId());
        zcBf.setUpdateBy(user.getId());
        zcBf.setApplyUserId(user.getId());
        zcBf.setFlowid(flowId);
        zcBf.setStatus(0);
        zcBf.setApplyDeptId(user.getDeptid());
        zcBf.setDel(0);
        zcBf.setBfNum(bfNum);
        zcBf.setDeptCode(child.getDeptcode());
        zcBf.setStepid(flowsteps.get(0).getId());
        zcBfDao.save(zcBf);
        if (!CollectionUtils.isEmpty(zcBfItemList)){
            zcBfItemDao.saves(zcBfItemList,zcBf.getId(),user.getId());
        }
        log.debug("新增报废申请单{}", zcBf.getCreateBy());
        // 类别判断
        if ("1".equals(zcBfDto.getType())) {
            // 启动流程
            this.startProcess(String.valueOf(zcBf.getId()));
        }
        map.put("code","0");
        map.put("msg","提交成功");
        return map;
    }

    @Transactional
    @Override
    public void startProcess(String bfId) {

        // 报废主流程
        ZcBf zcBf = zcBfDao.getById(Long.parseLong(bfId));
        if (zcBf.getStatus() !=0 ){
            log.info("【流程已经启动】");
            throw new RuntimeException("流程已经启动");
        }
        Long flowId = zcBf.getFlowid();
        // 查询报废流程
        Flow flow = flowDao.getById(flowId);
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);
        // 报废子表
        List<ZcBfItem> zcBfItems = zcBfItemDao.listByZcBfId(zcBf.getId());
        // 获取所有的管理部门
        ArrayList<Long> deptIdList = getGlDeptIdList(zcBfItems);
        if (deptIdList.size()<=0){
            log.info("【报废资产无管理部门】");
            throw new RuntimeException("报废资产无管理部门");
        }
        // 根据报废资产部门ID查询节点人员信息
        Long nextNodeId = getNextNodeId(1,zcBf.getStepid(), flowsteps);
        // 当前节点角色
        Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcBf.getFlowid(), nextNodeId);
        // 查询用户
        //List<FlowDeptUser> flowDeptUsers = flowDeptUserDao.listAllFlowDeptUser(deptIdList,flowId,nextNodeId);
        // 申请人信息
        //SysUserDto user = userDao.getById(zcBf.getApplyUserId());
        LoginUser loginUser = UserUtil.getLoginUser();
        // 查询所有的部门信息,获取父节点部门信息
        HashMap<Long, String> deptMap = getDeptMap();
        List<Dept> deptList = deptDao.listDeptSAndParent(deptIdList);
        //List<Long> parentDeptIdList = getParentDeptList(deptList);
        //TreeSet<Long> userIds = new TreeSet<>();
        ArrayList<Long> zcItemIds = new ArrayList<>();
        ArrayList<Long> zcids = new ArrayList<>();
        // 循环管理部门
        for (Long did : deptIdList) {
            Dept dept = deptDao.getById(did);
            String deptcode = dept.getDeptcode();
            Long currentId = dept.getId();
            // 根据部门角色信息查询人员信息
            // List<HashMap<String,Object>> userList = userDao.findByRoleIdAndDeptCode(memidByFlowStep,deptcode);
            SysUser user = userDao.getUserByRoleDept(memidByFlowStep, currentId);
            if ( user == null ){
                throw new RuntimeException("报废资产无审核人员");
            }
            // 获取第一个人
            //HashMap<String, Object> checkUser = userList.get(0);
            //String id = String.valueOf(checkUser.get("id"));
            Long id = user.getId();

            // 查询该用户,报废资产的待办任务
            Todo todo = todoDao.getNewTodoNoBfid(id,0,0,bfActionUrl);
            Long flowTodoId = null;
            if (todo == null){
                // 最后一位此处 无用
                flowTodoId = saveFlowTodo(id, loginUser, 0,zcBf, getFlowstepById(nextNodeId, flowsteps),1,zcBf.getId(),null);
            }else{
                flowTodoId = todo.getId();
            }
            for (ZcBfItem zcBfItem : zcBfItems) {
                //Long syDeptId = zcBfItem.getSyDeptId();
                //String childDeptcode = deptMap.get(syDeptId);
                //if (childDeptcode.startsWith(deptcode) && !zcItemIds.contains(zcBfItem.getId())){
                if ( currentId == zcBfItem.getGlDeptId() && !zcItemIds.contains(zcBfItem.getId())){
                    // 属于当前部门信息
                    FlowTodoItem flowTodoItem = new FlowTodoItem();
                    flowTodoItem.setAuditby(id);
                    flowTodoItem.setSendby(zcBfItem.getApplyUserId());
                    flowTodoItem.setFlowTodoId(flowTodoId);
                    flowTodoItem.setFlowItemId(zcBfItem.getId());
                    flowTodoItem.setStatus(0);
                    int ressult = flowTodoItemDao.save(flowTodoItem);
                    zcItemIds.add(zcBfItem.getId());
                    zcids.add(zcBfItem.getZcId());
                }
            }
        }
        // 跟新资产的状态,审核部门审核 2:审核部门
        zcBfItemDao.updateStatus(zcBf.getId(),2);
        // 更新报废的状态
        Map<String, Object> params = new HashMap<>();
        params.put("zcBfId",zcBf.getId());
        params.put("status",1);
        params.put("stepid",flowsteps.get(1).getId());
        zcBfDao.updateStatus(params);
        // 更新资产信息
        zcInfoDao.updateStatusList(3,zcids);
    }

    @Override
    @Transactional
    public void checkNew(ZcBfCheckDto zcBfCheckDto) {

        // 审核部审核和再次提交的
        LoginUser loginUser = UserUtil.getLoginUser();
        Dept dept = deptDao.getById(loginUser.getDeptid());
        List<FlowTodoItem> flowTodoItems = zcBfCheckDto.getFlowTodoItems();
        Long bfzcid = zcBfCheckDto.getBfzcid();
        ZcBf zcBf = zcBfDao.getById(bfzcid);
        Long itemStatus = zcBfCheckDto.getItemStatus();
        Long stepid = zcBf.getStepid();
        Long flowid = zcBf.getFlowid();
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowid);
        Flowstep flowstep = getFlowstepById(stepid,flowsteps);
        //-------------------------------------------------------------
        FlowTodoItem flowTodoItem1 = flowTodoItems.get(0);
        Long todoId = flowTodoItem1.getFlowTodoId();
        Todo flowTodo = todoDao.getById(todoId);
        // 1,修改报废子项分类别
        List<FlowTodoItem> agreeTodoItems = new ArrayList<>();        // 1审核为同意   2财务为报废  3再次提交的
        List<Long> agreeToDoItemIds = new ArrayList<>();
        List<Long> agreeBfItemIds = new ArrayList<>();
        List<FlowTodoItem> refuseTodoItems = new ArrayList<>();       // 1审核为拒绝    2财务为正常  3再次提交删除的
        List<Long> refuseToDoItemIds = new ArrayList<>();
        List<Long> refuseBfItemIds = new ArrayList<>();
        List<FlowTodoItem> backTodoItems = new ArrayList<>();
        List<Long> backToDoItemIds = new ArrayList<>();
        List<Long> backBfItemIds = new ArrayList<>();

        // 审核部处理的
        for (FlowTodoItem flowTodoItem : flowTodoItems) {
            // 更新报废子项信息
            Long flowItemId = flowTodoItem.getFlowItemId();
            String identifyContent = flowTodoItem.getIdentifyContent();
            zcBfItemDao.updateItem(flowItemId,identifyContent);
            if ( flowTodoItem.getStatus() == 1 && flowTodoItem.getShbStatus() == null ){
                agreeTodoItems.add(flowTodoItem);
                agreeToDoItemIds.add(flowTodoItem.getId());
                agreeBfItemIds.add(flowTodoItem.getFlowItemId());
            }else if ( flowTodoItem.getStatus() == 2 && flowTodoItem.getShbStatus() == null ) {
                refuseTodoItems.add(flowTodoItem);
                refuseToDoItemIds.add(flowTodoItem.getId());
                refuseBfItemIds.add(flowTodoItem.getFlowItemId());
            }else if ( flowTodoItem.getStatus() == 3 && flowTodoItem.getShbStatus() == null ) {
                backTodoItems.add(flowTodoItem);
                backToDoItemIds.add(flowTodoItem.getId());
                backBfItemIds.add(flowTodoItem.getFlowItemId());
            }
        }
        // 再次提交的
        if (zcBfCheckDto.getAgainSubmit() != null && zcBfCheckDto.getAgainSubmit() == 3) {
            for (FlowTodoItem flowTodoItem : flowTodoItems) {
                if ( flowTodoItem.getStatus() == 1 ){
                    agreeTodoItems.add(flowTodoItem);
                    agreeToDoItemIds.add(flowTodoItem.getId());
                    agreeBfItemIds.add(flowTodoItem.getFlowItemId());
                }else if ( flowTodoItem.getStatus() == 2 && flowTodoItem.getShbStatus() == null ) {
                    refuseTodoItems.add(flowTodoItem);
                    refuseToDoItemIds.add(flowTodoItem.getId());
                    refuseBfItemIds.add(flowTodoItem.getFlowItemId());
                }else if ( flowTodoItem.getStatus() == 2 && flowTodoItem.getShbStatus() == 3 ) {
                    refuseTodoItems.add(flowTodoItem);
                    refuseToDoItemIds.add(flowTodoItem.getId());
                    refuseBfItemIds.add(flowTodoItem.getFlowItemId());
                }
            }
        }

        // 处理同意的
        if (agreeTodoItems.size()>0){
            if ( itemStatus == 3 ) {
                // 更新todoItem
                flowTodoItemDao.updateListStatus(1,agreeToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("cwb",1,agreeBfItemIds,loginUser.getNickname(),dept.getDeptname());
            }else if ( itemStatus == 2 && zcBfCheckDto.getAgainSubmit() == null ) {
                // 更新todoItem
                flowTodoItemDao.updateListStatus(1,agreeToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("shb",1,agreeBfItemIds,loginUser.getNickname(),dept.getDeptname());
            }
            if (zcBfCheckDto.getAgainSubmit() != null && zcBfCheckDto.getAgainSubmit() == 3) {
                FlowTodoItem flowTodoItem = agreeTodoItems.get(0);
                Long flowTodoId = flowTodoItem.getFlowTodoId();
                Todo todo = todoDao.getById(flowTodoId);
                Long sendby = todo.getSendby();
                //Long aLong = saveFlowTodo(sendby, UserUtil.getLoginUser(), zcBf, flowsteps.get(1));
                for (FlowTodoItem agreeTodoItem : agreeTodoItems) {
                    // 属于当前部门信息
                    Long flowItemId = agreeTodoItem.getFlowItemId();

                    HashMap<String, Object> params = new HashMap<>();
                    params.put("flow_item_id",flowItemId);
                    params.put("status",3);
                    FlowTodoItem flowTodoItem2 = flowTodoItemDao.getByParams(params);
                    flowTodoItem2.setStatus(0);
                    flowTodoItem2.setUpdateTime(new Date());
                    flowTodoItemDao.update(flowTodoItem2);
                    // todo 发送消息通知
                    //FlowTodoItem item = new FlowTodoItem();
                    //item.setFlowTodoId(aLong);
                    //item.setFlowItemId(agreeTodoItem.getFlowItemId());
                    //item.setStatus(0);
                    //int ressult = flowTodoItemDao.save(item);
                    // 更新报废子项审核部状态
                    zcBfItemDao.updateShStatusNull(agreeTodoItem.getFlowItemId());
                }
            }
        }
        // 审核部处理拒绝的   财务部处理正常的
        if (refuseTodoItems.size()>0){
            if ( itemStatus == 3 ){
                // 更新todoItem
                flowTodoItemDao.updateListStatus(3,refuseToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("cwb",2,refuseBfItemIds,loginUser.getNickname(),dept.getDeptname());
            }else if ( itemStatus == 2 ){
                // 更新todoItem
                flowTodoItemDao.updateListStatus(3,refuseToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("jujue",1,refuseBfItemIds,loginUser.getNickname(),dept.getDeptname());
                // 更新资产信息
                List<ZcBfItem> zcBfItemList = zcBfItemDao.listByIds(refuseBfItemIds);
                List<Long> zcids = zcBfItemList.stream().map(e -> e.getZcId()).collect(Collectors.toList());
                // 批量更新资产信息为正常状态
                zcInfoDao.updateStatusList(1,zcids);
            }
        }
        // 处理驳回的
        if (backTodoItems.size()>0){
            // 更改状态 指定任务 更改状态
            flowTodoItemDao.updateListStatus(3,backToDoItemIds);
            // 驳回的资产中多个发起部门的信息
            // Long currentFlowid = saveFlowTodo(flowTodo.getSendby(), (SysUser)loginUser, zcBf, flowstep);
            for (FlowTodoItem backTodoItem : backTodoItems) {
                // 获取发起人信息
                Long sendby = backTodoItem.getSendby();
                // 查询再次提交的
                Long currentFlowid = null;
                Todo newTodo = todoDao.getNewTodo(sendby, 1, 0, bfActionUrl, bfzcid);
                if (newTodo == null) {
                    currentFlowid = saveFlowTodo(sendby, (SysUser)loginUser, 1,zcBf, flowstep,1,flowTodo.getBizid(),null);
                }else {
                    currentFlowid = newTodo.getId();
                }
                // 属于当前部门信息
                FlowTodoItem flowTodoItem = new FlowTodoItem();
                flowTodoItem.setAuditby(backTodoItem.getSendby());
                flowTodoItem.setSendby(loginUser.getId());
                flowTodoItem.setFlowTodoId(currentFlowid);
                flowTodoItem.setFlowItemId(backTodoItem.getFlowItemId());
                flowTodoItem.setStatus(0);
                int ressult = flowTodoItemDao.save(flowTodoItem);
            }
            // 更新报废子项
            zcBfItemDao.updateListStatus("shb",3,backBfItemIds,loginUser.getNickname(),dept.getDeptname());
        }
        // 判断是不是再次提交的
        if (flowTodo.getType() == 1) {
            flowTodo.setNeirong(zcBfCheckDto.getNeirong());
            // 待办中两种状态 0:待办理 1:已办理
            flowTodo.setStatus("1");
            todoDao.update(flowTodo);
        }
    }

    @Override
    public void submitToCw(ZcBfCheckDto zcBfCheckDto) {
        Long id = zcBfCheckDto.getId();
        Todo todo = todoDao.getById(id);
        Long flowid = todo.getFlowid();
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowid);
        Flowstep flowstep = flowsteps.get(1);
        Long stepid = flowstep.getId();
        // 当前登录用户信息
        LoginUser loginUser = UserUtil.getLoginUser();
        long deptid = loginUser.getDeptid();
        Dept sydept = deptDao.getById(deptid);
        Dept parentDept = deptDao.getById(sydept.getPid());
        String deptcode = parentDept.getDeptcode();
        Long nextNodeId = getNextNodeId(1, stepid, flowsteps);
        Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(flowid, nextNodeId);
        List<HashMap<String,Object>> userList = userDao.findByRoleIdAndDeptCode(memidByFlowStep,deptcode);
        HashMap<String, Object> user = userList.get(0);
        // 财务审核人信息
        String userId = String.valueOf(user.get("id"));
        Flowstep flowstep1 = getFlowstepById(nextNodeId, flowsteps);
        // 获取报废资产信息
        List<FlowTodoItem> flowTodoItems = zcBfCheckDto.getFlowTodoItems();
        ArrayList<Long> list = new ArrayList<>();
        TreeSet<Long> ids = new TreeSet<>();
        for (FlowTodoItem flowTodoItem : flowTodoItems) {
            ids.add(flowTodoItem.getFlowItemId());
        }
        list.addAll(ids);
        // 查询财务人员本年是否有未完成的待办任务
        Todo cwTodo = todoDao.getNewTodoNoBfid(Long.parseLong(userId),0,0,cwbfActionUrl);
        Long aLong = null;
        if (cwTodo == null){
            ZcBf zcBf = new ZcBf();
            zcBf.setFlowid(flowid);
            aLong = saveFlowTodo(Long.parseLong(userId), loginUser, 0,zcBf, flowstep1,2,todo.getBizid(),String.valueOf(id));
        }else {
            aLong = cwTodo.getId();
            // 更新财务的待办列表
            String todoIds = cwTodo.getTodoIds();
            todoIds = todoIds+","+String.valueOf(id);
            cwTodo.setTodoIds(todoIds);
            int update = todoDao.update(cwTodo);
        }
        for (Long zcBfItemId : list) {
            // 属于当前部门信息
            FlowTodoItem flowTodoItem = new FlowTodoItem();
            flowTodoItem.setFlowTodoId(aLong);
            flowTodoItem.setFlowItemId(zcBfItemId);
            flowTodoItem.setStatus(0);
            int ressult = flowTodoItemDao.save(flowTodoItem);
        }
        // 更改报废子项的状态
        zcBfItemDao.updateListStatus("status",3,list,loginUser.getNickname(),sydept.getDeptname());
        // 更改待办的任务状态
        todo.setStatus("1");
        todo.setUpdateTime(new Date());
        todoDao.update(todo);
    }

    @Override
    public Long cwcheck(ZcBfCheckDto zcBfCheckDto) {

        LoginUser loginUser = UserUtil.getLoginUser();
        Dept dept = deptDao.getById(loginUser.getDeptid());
        // 当前人的待办ID
        Long todoid = zcBfCheckDto.getId();
        Todo todo = todoDao.getById(todoid);
        // 处理报废信息(报废的和正常的)
        List<FlowTodoItem> flowTodoItems = zcBfCheckDto.getFlowTodoItems();
        List<Long> zcbfItemIds = flowTodoItems.stream().map(e -> e.getFlowItemId()).collect(Collectors.toList());
        List<Long> flowtodoids = flowTodoItems.stream().map(e -> e.getId()).collect(Collectors.toList());
        // 资产报废子项集合
        List<ZcBfItem> zcBfItemList = zcBfItemDao.listByIds(zcbfItemIds);
        // 处理提交信息,报废主单信息
        ArrayList<Long> bfzcList = new ArrayList<>();
        ArrayList<Long> bfitemList = new ArrayList<>();
        ArrayList<Long> zczcList = new ArrayList<>();
        ArrayList<Long> zcitemList = new ArrayList<>();
        TreeSet<Long> bfids = new TreeSet<>();
        ArrayList<Long> zcbfids = new ArrayList<>();
        for (ZcBfItem zcBfItem : zcBfItemList) {
            // 报废资产ID集合
            bfids.add(zcBfItem.getZcBfId());
            for (FlowTodoItem flowTodoItem : flowTodoItems) {
                if ( flowTodoItem.getStatus() == 1 && flowTodoItem.getFlowItemId() == zcBfItem.getId() ) {
                    bfzcList.add(zcBfItem.getZcId());
                    bfitemList.add(zcBfItem.getId());
                    break;
                }
                if ( flowTodoItem.getStatus() == 2 && flowTodoItem.getFlowItemId() == zcBfItem.getId() ) {
                    zczcList.add(zcBfItem.getZcId());
                    zcitemList.add(zcBfItem.getId());
                    break;
                }
            }
        }
        // 给发起人插入通知消息
        saveNoticeList(zcBfItemList);
        // 生成报废单号
        Dept child = deptDao.getById(loginUser.getDeptid());
        Dept parent = deptDao.getById(child.getPid());
        String jx = parent.getJx();
        int count1 = zcBfCwitemDao.countThisYear();
        String bfNum = jx + "-BF"+DateUtil.getCurrentYear()+"-"+StrUtil.getStaticNum(count1+1,5);
        ZcBfCwitem zcBfCwitem = new ZcBfCwitem();
        zcBfCwitem.setCodeNum(bfNum);
        zcBfCwitem.setTodoId(todoid);
        zcBfCwitem.setBfId(todo.getBizid());
        zcBfCwitemDao.save(zcBfCwitem);
        // 报废-更新财务item信息  更新资产信息
        if (bfitemList.size()>0){
            zcBfItemDao.updateListStatus("cwb",1,bfitemList,loginUser.getNickname(),dept.getDeptname());
            zcBfItemDao.updateZcBfCwitem(zcBfCwitem.getId(),bfitemList);
            zcInfoDao.updateBfStatusList(7,bfzcList);
        }
        // 报废-更新财务item信息  更新资产信息
        if (zcitemList.size()>0){
            zcBfItemDao.updateListStatus("cwb",2,zcitemList,loginUser.getNickname(),dept.getDeptname());
        }
        // 更新报废主单信息
        zcbfids.addAll(bfids);
        zcBfDao.updateStatusList(2,zcbfids);
        // 当前人的待办信息已完成
        todo.setStatus("1");
        todo.setNeirong(zcBfCheckDto.getNeirong());
        todo.setUpdateTime(new Date());
        todoDao.update(todo);
        // 待办子项信息
        flowTodoItemDao.updateListStatus(1,flowtodoids);
        return zcBfCwitem.getId();
    }

    // 给申请人发送消息
    private void saveNoticeList(List<ZcBfItem> zcBfItemList) {
        for (ZcBfItem zcBfItem : zcBfItemList) {
            String content = "你与"+DateUtil.format(zcBfItem.getCreateTime())+"申请的【资产处置】已经审核完成，请知悉！";
            Notice notice = new Notice();
            notice.setUserId(zcBfItem.getApplyUserId());
            notice.setTitle("【资产处置】");
            notice.setStatus(0);
            notice.setUpdateTime(null);
            notice.setContent(content);
            noticeDao.save(notice);
        }
    }

    @Override
    @Transactional
    public void check(ZcBfCheckDto zcBfCheckDto) {

        LoginUser loginUser = UserUtil.getLoginUser();
        Dept dept = deptDao.getById(loginUser.getDeptid());
        List<FlowTodoItem> flowTodoItems = zcBfCheckDto.getFlowTodoItems();
        Long bfzcid = zcBfCheckDto.getBfzcid();
        ZcBf zcBf = zcBfDao.getById(bfzcid);
        Long itemStatus = zcBfCheckDto.getItemStatus();
        Long stepid = zcBf.getStepid();
        Long flowid = zcBf.getFlowid();
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowid);
        Flowstep flowstep = getFlowstepById(stepid,flowsteps);
        //-------------------------------------------------------------
        FlowTodoItem flowTodoItem1 = flowTodoItems.get(0);
        Long todoId = flowTodoItem1.getFlowTodoId();
        Todo flowTodo = todoDao.getById(todoId);
        // 1,修改报废子项分类别
        List<FlowTodoItem> agreeTodoItems = new ArrayList<>();        // 1审核为同意   2财务为报废  3再次提交的
        List<Long> agreeToDoItemIds = new ArrayList<>();
        List<Long> agreeBfItemIds = new ArrayList<>();
        List<FlowTodoItem> refuseTodoItems = new ArrayList<>();       // 1审核为拒绝    2财务为正常  3再次提交删除的
        List<Long> refuseToDoItemIds = new ArrayList<>();
        List<Long> refuseBfItemIds = new ArrayList<>();
        List<FlowTodoItem> backTodoItems = new ArrayList<>();
        List<Long> backToDoItemIds = new ArrayList<>();
        List<Long> backBfItemIds = new ArrayList<>();
        for (FlowTodoItem flowTodoItem : flowTodoItems) {
            if ( flowTodoItem.getStatus() == 1 ){
                agreeTodoItems.add(flowTodoItem);
                agreeToDoItemIds.add(flowTodoItem.getId());
                agreeBfItemIds.add(flowTodoItem.getFlowItemId());
            }else if ( flowTodoItem.getStatus() == 2 ) {
                refuseTodoItems.add(flowTodoItem);
                refuseToDoItemIds.add(flowTodoItem.getId());
                refuseBfItemIds.add(flowTodoItem.getFlowItemId());
            }else if ( flowTodoItem.getStatus() == 3 ) {
                backTodoItems.add(flowTodoItem);
                backToDoItemIds.add(flowTodoItem.getId());
                backBfItemIds.add(flowTodoItem.getFlowItemId());
            }
        }
        // 处理同意的
        if (agreeTodoItems.size()>0){
            if ( itemStatus == 3 ) {
                // 更新todoItem
                flowTodoItemDao.updateListStatus(1,agreeToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("cwb",1,agreeBfItemIds,loginUser.getNickname(),dept.getDeptname());
            }else if ( itemStatus == 2 && zcBfCheckDto.getAgainSubmit() == null ) {
                // 更新todoItem
                flowTodoItemDao.updateListStatus(1,agreeToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("shb",1,agreeBfItemIds,loginUser.getNickname(),dept.getDeptname());
            }
            if (zcBfCheckDto.getAgainSubmit() != null && zcBfCheckDto.getAgainSubmit() == 3) {
                FlowTodoItem flowTodoItem = agreeTodoItems.get(0);
                Long flowTodoId = flowTodoItem.getFlowTodoId();
                Todo todo = todoDao.getById(flowTodoId);
                Long sendby = todo.getSendby();
                Long aLong = saveFlowTodo(sendby, UserUtil.getLoginUser(), 1,zcBf, flowsteps.get(1),1,flowTodo.getBizid(),null);
                for (FlowTodoItem agreeTodoItem : agreeTodoItems) {
                    // 属于当前部门信息
                    FlowTodoItem item = new FlowTodoItem();
                    item.setFlowTodoId(aLong);
                    item.setFlowItemId(agreeTodoItem.getFlowItemId());
                    item.setStatus(0);
                    int ressult = flowTodoItemDao.save(item);
                    // 更新报废子项审核部状态
                    zcBfItemDao.updateShStatusNull(agreeTodoItem.getFlowItemId());
                }
            }
        }
        // 审核部处理拒绝的   财务部处理正常的
        if (refuseTodoItems.size()>0){
            if ( itemStatus == 3 ){
                // 更新todoItem
                flowTodoItemDao.updateListStatus(3,refuseToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("cwb",2,refuseBfItemIds,loginUser.getNickname(),dept.getDeptname());
            }else if ( itemStatus == 2 ){
                // 更新todoItem
                flowTodoItemDao.updateListStatus(3,refuseToDoItemIds);
                // 更新报废item
                zcBfItemDao.updateListStatus("jujue",1,refuseBfItemIds,loginUser.getNickname(),dept.getDeptname());
            }

        }
        // 处理驳回的
        if (backTodoItems.size()>0){
            // 更改状态 指定任务 更改状态
            flowTodoItemDao.updateListStatus(3,backToDoItemIds);
            // 保存待办主信息
            Long currentFlowid = saveFlowTodo(flowTodo.getSendby(), (SysUser)loginUser, 1,zcBf, flowstep,1,flowTodo.getBizid(),null);
            for (FlowTodoItem backTodoItem : backTodoItems) {
                // 属于当前部门信息
                FlowTodoItem flowTodoItem = new FlowTodoItem();
                flowTodoItem.setFlowTodoId(currentFlowid);
                flowTodoItem.setFlowItemId(backTodoItem.getFlowItemId());
                flowTodoItem.setStatus(0);
                int ressult = flowTodoItemDao.save(flowTodoItem);
            }
            // 更新报废子项
            zcBfItemDao.updateListStatus("shb",3,backBfItemIds,loginUser.getNickname(),dept.getDeptname());
        }
        // 2,修改当TODO前状态
        flowTodo.setNeirong(zcBfCheckDto.getNeirong());
        // 待办中两种状态 0:待办理 1:已办理
        flowTodo.setStatus("1");
        todoDao.update(flowTodo);
        // 判断当前节点任务是否完成,完成后指派到下一级
        boolean flag = checkStepFinished(zcBf);
        if (flag) {
            // 流程最后为财务部审核
            Flowstep lastFlowstep = flowsteps.get(flowsteps.size() - 1);
            if ( stepid == lastFlowstep.getId() ){
                // 流程已经处理完毕
                Map<String, Object> params = new HashMap<>();
                params.put("status",2);
                params.put("zcBfId",bfzcid);
                zcBfDao.updateStatus(params);
                // 更新资产信息
                updateZcInfoStatus(zcBf);
            }
            if ( stepid == flowsteps.get(1).getId() ) {
                // 查询报废子表
                List<ZcBfItem> zcBfItems = zcBfItemDao.listByZcBfId(bfzcid);
                if (zcBfItems.size()==0){
                    // 审核部全部拒绝
                    // 流程已经处理完毕
                    Map<String, Object> params = new HashMap<>();
                    params.put("status",2);
                    params.put("zcBfId",bfzcid);
                    zcBfDao.updateStatus(params);
                    // 更新资产信息
                    updateZcInfoStatus(zcBf);
                    return;
                }
                // 获取使用部门ID
                Dept sydept = deptDao.getById(zcBfItems.get(0).getSyDeptId());
                Dept parentDept = deptDao.getById(sydept.getPid());
                String deptcode = parentDept.getDeptcode();
                // 当前节点角色信息
                // 获取下一级step
                Long nextNodeId = getNextNodeId(1, stepid, flowsteps);
                Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcBf.getFlowid(), nextNodeId);
                List<HashMap<String,Object>> userList = userDao.findByRoleIdAndDeptCode(memidByFlowStep,deptcode);
                HashMap<String, Object> user = userList.get(0);
                String userId = String.valueOf(user.get("id"));
                Flowstep flowstep1 = getFlowstepById(nextNodeId, flowsteps);
                // 插入待办信息
                SysUserDto byId = userDao.getById(zcBf.getApplyUserId());
                Long aLong = saveFlowTodo(Long.parseLong(userId), userDao.getById(zcBf.getApplyUserId()), 0,zcBf, flowstep1,2,bfzcid,null);
                for (ZcBfItem zcBfItem : zcBfItems) {
                    Long syDeptId = zcBfItem.getSyDeptId();
                    // 属于当前部门信息
                    FlowTodoItem flowTodoItem = new FlowTodoItem();
                    flowTodoItem.setFlowTodoId(aLong);
                    flowTodoItem.setFlowItemId(zcBfItem.getId());
                    flowTodoItem.setStatus(0);
                    int ressult = flowTodoItemDao.save(flowTodoItem);
                }
                // 更改报废子项的状态
                zcBfItemDao.updateStatus(bfzcid,3);
                // 更新报废的状态
                Map<String, Object> params = new HashMap<>();
                params.put("zcBfId",zcBf.getId());
                params.put("stepid",nextNodeId);
                zcBfDao.updateStatus(params);
            }
        }
    }

    @Override
    public Map updateZcBf(ZcBfDto zcBfDto) {
        Map map = new HashMap<>();
        // 修改主项
        ZcBf zcBf = (ZcBf)zcBfDto;
        Long zcBfId = zcBf.getId();
        // 删除子项
        zcBfItemDao.deleteByZcBfId(zcBfId);
        List<ZcBfItem> zcBfItemList = zcBfDto.getZcBfItemList();
        List<String> list = zcBfItemList.stream().map(e -> String.valueOf(e.getId())).collect(Collectors.toList());
        if (list.size()>0){
            String ids = String.join(",", list);
            zcBf.setZcIds(ids);
        }
        ZcBf oldZcbf = zcBfDao.getById(zcBf.getId());
        Long applyUserId = oldZcbf.getApplyUserId();
        List<String> list1 = Arrays.asList("bfCategory", "bfDes", "bz");
        BeanUtils.copyProperties(zcBf,oldZcbf,list1.toArray(new String[list1.size()]));
        zcBfDao.update(oldZcbf);
        // 插入子项
        if (!CollectionUtils.isEmpty(zcBfItemList)){
            zcBfItemDao.saves(zcBfItemList,zcBf.getId(),applyUserId);
            log.debug("新增报废申请单{}", zcBf.getCreateBy());
            // 类别判断
            if ("1".equals(zcBfDto.getType())) {
                // 启动流程
                this.startProcess(String.valueOf(zcBf.getId()));
            }
        }
        map.put("code","1");
        map.put("msg","保存成功");
        return map;
    }

    /**
     * 更新资产信息
     * @param zcBf
     */
    private void updateZcInfoStatus(ZcBf zcBf) {
        List<ZcBfItem> zcBfItems = zcBfItemDao.listFinishedByZcBfId(zcBf.getId());
        for (ZcBfItem zcBfItem : zcBfItems) {
            Integer del = zcBfItem.getDel();
            String cwbStatus = zcBfItem.getCwbStatus();
            Long zcId = zcBfItem.getZcId();
            ZcInfo zcInfo = new ZcInfo();
            zcInfo.setCreateTime(null);
            zcInfo.setId(zcId);
            if (del == 1){
                zcInfo.setUseStatus(1);
            }
            if ("1".equals(cwbStatus)){
                zcInfo.setBf("1");
                zcInfo.setUseStatus(7);
            }
            int update = zcInfoDao.update(zcInfo);
        }
    }

    /**
     * 判断当前节点是否完成
     * @param zcBf
     * @return
     */
    private boolean checkStepFinished(ZcBf zcBf) {
        boolean flag = false;
        Long stepid = zcBf.getStepid();
        if ( stepid == 51 ){
            Map<String, Object> params = new HashMap<>();
            int count = zcBfItemDao.countCheck("shb",zcBf.getId());
            if (count==0){
                flag = true;
            }
        }
        if ( stepid == 52 ){
            Map<String, Object> params = new HashMap<>();
            int count = zcBfItemDao.countCheck("cwb",zcBf.getId());
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
     * @param zcBf 报废主信息
     * @param flowstep 流程步骤
     * @return
     */
    public Long saveFlowTodo( Long auditUserId,SysUser user, Integer type,ZcBf zcBf,Flowstep flowstep,Integer memo,Long bizid,String id ){
        Todo todo = new Todo();
        todo.setType(type);
        todo.setAuditby(auditUserId);
        todo.setSendby(user.getId());
        todo.setBiaoti("【"+user.getNickname()+"】申请资产报废");
        todo.setNeirong(zcBf.getBz());
        todo.setBizid(zcBf.getId());
        todo.setBizid(bizid);
        todo.setBizcreateby(user.getId());
        todo.setBizdeptid(zcBf.getApplyDeptId());
        todo.setStatus("0");
        todo.setFlowid(zcBf.getFlowid());
        todo.setStepid(flowstep.getId());
        todo.setMemo(String.valueOf(memo));
        todo.setTodoIds(id);
        todo.setCreateTime(new Date());
        todo.setUpdateTime(new Date());
        // 流程审核跳转页面
        todo.setUrl(flowstep.getFlowact());
        int save = todoDao.save(todo);
        return todo.getId();
    }

    /**
     * 根据报废资子项查询所有的管理部门
     * @param zcBfItems
     * @return
     */
    public ArrayList<Long> getGlDeptIdList( List<ZcBfItem> zcBfItems){
        ArrayList<Long> list = new ArrayList<>();
        TreeSet<Long> zcids = new TreeSet<>();
        for (ZcBfItem zcBfItem : zcBfItems) {
            if ( !(ObjectUtils.isEmpty(zcBfItem.getGlDeptId())) ){
                zcids.add(zcBfItem.getGlDeptId());
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
