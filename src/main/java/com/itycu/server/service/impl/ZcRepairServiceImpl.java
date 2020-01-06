package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.dto.SysUserDto;
import com.itycu.server.dto.ZcRepairDto;
import com.itycu.server.dto.ZcRepairItemDto;
import com.itycu.server.model.*;
import com.itycu.server.service.ZcRepairService;
import com.itycu.server.utils.DateUtil;
import com.itycu.server.utils.StrUtil;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ZcRepairServiceImpl implements ZcRepairService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private ZcRepairDao zcRepairDao;
    @Autowired
    private ZcRepairItemDao zcRepairItemDao;
    @Autowired
    private FlowDao flowDao;
    @Autowired
    private FlowstepDao flowstepDao;
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
    private NoticeDao noticeDao;

    @Override
    public ZcRepairDto save(ZcRepairDto zcRepair) {
        // 查询报修流程
        Flow flow = flowDao.findByName("资产报修流程");
        Long flowId = flow.getId();
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);

        List<ZcRepairItem> zcRepairItemList = zcRepair.getZcRepairItemList();
        List<String> list = zcRepairItemList.stream().map(e -> String.valueOf(e.getId())).collect(Collectors.toList());
        if (list.size()>0){
            String ids = String.join(",", list);
            zcRepair.setZcIds(ids);
        }
        Dept child = deptDao.getById(UserUtil.getLoginUser().getDeptid());
        Dept parent = deptDao.getById(child.getPid());
        String jx = parent.getJx();
        // 查询数量
        int count1 = zcRepairDao.countByDeptThisYear(parent.getDeptcode());
        String bxNum = jx + "-BX"+ DateUtil.getCurrentYear()+"-"+ StrUtil.getStaticNum(count1+1,5);

        zcRepair.setCreateBy(UserUtil.getLoginUser().getId());
        zcRepair.setDel(0);
        zcRepair.setApplyUserId(UserUtil.getLoginUser().getId());
        zcRepair.setApplyDeptId(UserUtil.getLoginUser().getDeptid());
        zcRepair.setFlowid(flowId);
        zcRepair.setStepid(flowsteps.get(0).getId());
        zcRepair.setStatus(0);
        zcRepair.setCode(bxNum);
        zcRepair.setDeptCode(child.getDeptcode());
        zcRepairDao.save(zcRepair);

        if (!CollectionUtils.isEmpty(zcRepairItemList)){
            zcRepairItemDao.saves(zcRepairItemList,zcRepair.getId());
        }

        // 类别判断
        if ("1".equals(zcRepair.getType())) {
            // 启动流程
            this.startProcess(String.valueOf(zcRepair.getId()));
        }
        log.info("新增报修单{}", zcRepair.getCreateBy());
        return zcRepair;
    }

    @Override
    public ZcRepairDto update(ZcRepairDto zcRepair) {
        zcRepair.setUpdateBy(UserUtil.getLoginUser().getId());
        zcRepairDao.update(zcRepair);
        return zcRepair;
    }

    @Override
    public void delete(Long id) {
        ZcRepair zcRepair = new ZcRepair();
        zcRepair.setId(id);
        zcRepair.setUpdateBy(UserUtil.getLoginUser().getId());
        zcRepair.setDel(1);
        zcRepairDao.update(zcRepair);
    }

    public void startProcess(String bxId) {

        // 报修主流程
        ZcRepair zcRepair = zcRepairDao.getById(Long.parseLong(bxId));
        if (zcRepair.getStatus() !=0 ){
            log.info("【流程已经启动】");
            throw new RuntimeException("流程已经启动");
        }
        Long flowId = zcRepair.getFlowid();
        // 查询报修流程
        Flow flow = flowDao.getById(flowId);
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);
        // 报修子表
        List<ZcRepairItemDto> zcRepairItems = zcRepairItemDao.listByZcReId(zcRepair.getId());
        // 获取所有的管理部门
        ArrayList<Long> deptIdList = getGlDeptIdList(zcRepairItems);
        if (deptIdList.size()<=0){
            log.info("【报修资产无管理部门】");
            throw new RuntimeException("报修资产无管理部门");
        }
        // 根据报修资产部门ID查询节点人员信息
        Long nextNodeId = getNextNodeId(1,zcRepair.getStepid(), flowsteps);
        // 当前节点角色
        Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcRepair.getFlowid(), nextNodeId);
        // 查询用户
        //List<FlowDeptUser> flowDeptUsers = flowDeptUserDao.listAllFlowDeptUser(deptIdList,flowId,nextNodeId);
        // 申请人信息
        //SysUserDto user = userDao.getById(zcBf.getApplyUserId());
        LoginUser loginUser = UserUtil.getLoginUser();
        // 查询所有的部门信息,获取父节点部门信息
//        HashMap<Long, String> deptMap = getDeptMap();
//        List<Dept> deptList = deptDao.listDeptSAndParent(deptIdList);
//        List<Long> parentDeptIdList = getParentDeptList(deptList);
//        TreeSet<Long> userIds = new TreeSet<>();
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
                throw new RuntimeException("报修资产无审核人员");
            }
            // 获取第一个人
            //HashMap<String, Object> checkUser = userList.get(0);
            //String id = String.valueOf(checkUser.get("id"));
            Long id = user.getId();
            // 插入待办任务信息
            Long flowTodoId = saveFlowTodo(id, loginUser.getId(), zcRepair, getFlowstepById(nextNodeId, flowsteps),userDao.getById(zcRepair.getApplyUserId()));
            for (ZcRepairItem zcRepairItem : zcRepairItems) {
                Long syDeptId = zcRepairItem.getApplyDeptId();
                //String childDeptcode = deptMap.get(syDeptId);
                //if (childDeptcode.startsWith(deptcode) && !zcItemIds.contains(zcBfItem.getId())){
                if ( currentId == zcRepairItem.getGlDeptId() && !zcItemIds.contains(zcRepairItem.getId())){
                    // 属于当前部门信息
                    FlowTodoItem flowTodoItem = new FlowTodoItem();
                    flowTodoItem.setFlowTodoId(flowTodoId);
                    flowTodoItem.setFlowItemId(zcRepairItem.getId());
                    flowTodoItem.setStatus(0);
                    int ressult = flowTodoItemDao.save(flowTodoItem);
                    zcItemIds.add(zcRepairItem.getId());
                    zcids.add(zcRepairItem.getZcId());
                }
            }
        }
        // 跟新资产的状态,审核部门审核 2:审核部门
        Map<String, Object> paramsItem = new HashMap<>();
        paramsItem.put("zcReId",zcRepair.getId());
        paramsItem.put("status",2);
        zcRepairItemDao.updateStatus(paramsItem);
        // 更新报修的状态
        Map<String, Object> params = new HashMap<>();
        params.put("zcReId",zcRepair.getId());
        params.put("status",1);
        params.put("stepid",flowsteps.get(1).getId());
        zcRepairDao.updateStatus(params);
        // 更新资产信息
        zcInfoDao.updateStatusList(3,zcids);
    }

    /**
     * 插入待办信息
     * @param auditUserId 审核人
     * @param zcRepair 报修主信息
     * @param flowstep 流程步骤
     * @return
     */
    public Long saveFlowTodo( Long auditUserId,Long sendUserId, ZcRepair zcRepair,Flowstep flowstep,SysUser ApplyDeptUser){
        Todo todo = new Todo();
        todo.setAuditby(auditUserId);
        todo.setSendby(sendUserId);
        todo.setBiaoti("【"+ApplyDeptUser.getNickname()+"】申请资产报修");
        todo.setNeirong(zcRepair.getBz());
        todo.setBizid(zcRepair.getId());
        todo.setBizcreateby(sendUserId);
        todo.setBizdeptid(zcRepair.getApplyDeptId());
        todo.setStatus("0");
        todo.setFlowid(zcRepair.getFlowid());
        todo.setStepid(flowstep.getId());
        // 流程审核跳转页面
        todo.setUrl(flowstep.getFlowact());
        int save = todoDao.save(todo);
        return todo.getId();
    }

    @Override
    public ZcRepairDto check(ZcRepairDto zcRepairDto) {

        LoginUser loginUser = UserUtil.getLoginUser();
        List<FlowTodoItem> flowTodoItems = zcRepairDto.getFlowTodoItems();
        List<ZcRepairItem> zcRepairItems = zcRepairDto.getZcRepairItemList();
        if (!CollectionUtils.isEmpty(zcRepairItems)){  //更新报修子表
              for (ZcRepairItem zcRepairItem:zcRepairItems){
                  zcRepairItemDao.update(zcRepairItem);
              }
        }

        Long bfzcid = zcRepairDto.getBfzcid();
        ZcRepair zcRepair = zcRepairDao.getById(bfzcid);
        Long itemStatus = zcRepairDto.getItemStatus();
        Long stepid = zcRepair.getStepid();
        Long flowid = zcRepair.getFlowid();
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowid);
        Flowstep flowstep = getFlowstepById(stepid,flowsteps);
        //-------------------------------------------------------------
        FlowTodoItem flowTodoItem1 = flowTodoItems.get(0);
        Long todoId = flowTodoItem1.getFlowTodoId();
        Todo flowTodo = todoDao.getById(todoId);
        // 修改当TODO前状态
        flowTodo.setNeirong(zcRepairDto.getNeirong());
        // 待办中两种状态 0:待办理 1:已办理
        flowTodo.setStatus("1");
        todoDao.update(flowTodo);
//        // 判断当前节点任务是否完成,完成后指派到下一级
//        boolean flag = checkStepFinished(zcRepair);
//        if (flag) {
            // 流程最后为使用部门确认
            Flowstep lastFlowstep = flowsteps.get(flowsteps.size() - 1);
            if ( stepid == lastFlowstep.getId() ){
                // 流程已经处理完毕
                Map<String, Object> params = new HashMap<>();
                params.put("status",2);
                params.put("zcReId",bfzcid);
                params.put("confirmTime",new Date());
                params.put("confirmBy",UserUtil.getLoginUser().getId());
                params.put("confirmDept", UserUtil.getLoginUser().getDeptid());
                zcRepairDao.updateStatus(params);

                // 更改报修子项的状态
                Map<String, Object> paramsItem = new HashMap<>();
                paramsItem.put("zcReId",zcRepair.getId());
                paramsItem.put("status",4);
                zcRepairItemDao.updateStatus(paramsItem);
                // 更新资产信息
//                updateZcInfoStatus(zcBf);

                //给财务发送消息
                Dept dept = deptDao.deptByJx("YQ","cwb");
                if (dept != null && dept.getId() != null){
                     SysUserDto sysUserDto = userDao.getUserByDeptTop1(dept.getId());
                     if (sysUserDto != null){
                         Notice notice = new Notice();
                         notice.setTitle(flowTodo.getBiaoti());
                         notice.setContent("报修流程审核完成，请及时查阅");
                         notice.setStatus(0);
                         notice.setUserId(sysUserDto.getId());
                         notice.setUpdateTime(null);
                         noticeDao.save1(notice);
                     }
                }
            }
            if ( stepid == flowsteps.get(1).getId() ) {
                // 查询报修子表
                List<ZcRepairItemDto> zcRepairItemList = zcRepairItemDao.listByZcReId(bfzcid);
                if (zcRepairItemList.size()==0){
                    // 审核部全部拒绝
                    // 流程已经处理完毕
                    Map<String, Object> params = new HashMap<>();
                    params.put("status",2);
                    params.put("zcReId",bfzcid);
                    params.put("confirmTime",new Date());
                    params.put("confirmBy",UserUtil.getLoginUser().getId());
                    params.put("confirmDept", UserUtil.getLoginUser().getDeptid());
                    zcRepairDao.updateStatus(params);
                    // 更新资产信息
//                    updateZcInfoStatus(zcBf);
                    return zcRepairDto;
                }
                // 获取使用部门ID
                Dept sydept = deptDao.getById(zcRepairItemList.get(0).getApplyDeptId());
                Dept parentDept = deptDao.getById(sydept.getPid());
                String deptcode = parentDept.getDeptcode();
                // 当前节点角色信息
                // 获取下一级step
                Long nextNodeId = getNextNodeId(1, stepid, flowsteps);
                Flowstep flowstep1 = getFlowstepById(nextNodeId, flowsteps);
                // 插入待办信息
                SysUserDto byId = userDao.getById(zcRepair.getApplyUserId());

                if (!CollectionUtils.isEmpty(zcRepairItems)){  //更新报修子表
                    for (ZcRepairItem zcRepairItem:zcRepairItems){
                        // 更改报修子项的状态
                        Map<String, Object> paramsItem = new HashMap<>();
                        paramsItem.put("zcReId",zcRepair.getId());
                        paramsItem.put("status",3);
                        paramsItem.put("auditTime",new Date());
                        paramsItem.put("id",zcRepairItem.getId());
                        paramsItem.put("auditBy",UserUtil.getLoginUser().getId());
                        zcRepairItemDao.updateStatus(paramsItem);
                    }
                }

                List<ZcRepairItemDto> zcRepairItemList1 = zcRepairItemDao.listByZcReId(bfzcid);
                if (!CollectionUtils.isEmpty(zcRepairItemList1)){
                    boolean isAllAudit = true;
                    for (ZcRepairItemDto zcRepairItemDto:zcRepairItemList1){
                        if (new Integer(2).equals(zcRepairItemDto.getStatus())){
                            isAllAudit = false;
                        }
                    }
                    if (isAllAudit){
                        Long aLong = saveFlowTodo(zcRepair.getApplyUserId(), loginUser.getId(), zcRepair, flowstep1,userDao.getById(zcRepair.getApplyUserId()));
                        for (ZcRepairItem zcRepairItem : zcRepairItemList) {
                            Long syDeptId = zcRepairItem.getApplyDeptId();
                            // 属于当前部门信息
                            FlowTodoItem flowTodoItem = new FlowTodoItem();
                            flowTodoItem.setFlowTodoId(aLong);
                            flowTodoItem.setFlowItemId(zcRepairItem.getId());
                            flowTodoItem.setStatus(0);
                            int ressult = flowTodoItemDao.save(flowTodoItem);
                        }

                        // 更新报修的状态
                        Map<String, Object> params = new HashMap<>();
                        params.put("zcReId",zcRepair.getId());
                        params.put("stepid",nextNodeId);
                        zcRepairDao.updateStatus(params);
                    }
                }

            }
//        }
        return zcRepairDto;
    }

    /**
     * 根据报修子项查询所有的管理部门
     * @param zcRepairItems
     * @return
     */
    public ArrayList<Long> getGlDeptIdList( List<ZcRepairItemDto> zcRepairItems){
        ArrayList<Long> list = new ArrayList<>();
        TreeSet<Long> zcids = new TreeSet<>();
        for (ZcRepairItemDto zcRepairItem : zcRepairItems) {
            if ( !(ObjectUtils.isEmpty(zcRepairItem.getGlDeptId())) ){
                zcids.add(zcRepairItem.getGlDeptId());
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
}
