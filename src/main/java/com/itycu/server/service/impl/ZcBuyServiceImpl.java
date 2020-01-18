package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.dto.SysUserDto;
import com.itycu.server.dto.ZcBuyCheckDto;
import com.itycu.server.dto.ZcBuyDto;
import com.itycu.server.model.*;
import com.itycu.server.service.ZcBuyService;
import com.itycu.server.utils.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * 资产购买
 * @author lch
 * @create 2019-12-04 10:25
 */
@Service
public class ZcBuyServiceImpl implements ZcBuyService {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZcBuyDao zcBuyDao;
    @Autowired
    private ZcBuyItemDao zcBuyItemDao;
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
    @Autowired
    private ZcEpcCodeDao zcEpcCodeDao;

    @Override
    public ZcBuyDto save(ZcBuyDto zcBuyDto) {

        // 登录人信息
        LoginUser user = UserUtil.getLoginUser();
        Dept child = deptDao.getById(user.getDeptid());
        Dept parent = deptDao.getById(child.getPid());
        String jx = parent.getJx();
        // 查询数量
        int count = zcBuyDao.countByDeptThisYear(parent.getDeptcode());
        String buyNum = jx + "-CG"+DateUtil.getCurrentYear()+"-"+StrUtil.getStaticNum(count+1,5);
        // 查询报废流程
        Flow flow = flowDao.findByName("资产购买流程");
        Long flowId = flow.getId();
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);
        // 报废主单
        ZcBuy zcBuy = (ZcBuy)zcBuyDto;
        List<ZcBuyItem> zcBuyItemList = zcBuyDto.getZcBuyItemList();
        zcBuy.setBuyNum(buyNum);
        zcBuy.setCreateBy(user.getId());
        zcBuy.setUpdateBy(user.getId());
        zcBuy.setApplyUserId(user.getId());
        zcBuy.setFlowid(flowId);
        zcBuy.setStatus(0);
        zcBuy.setSyDeptId(user.getDeptid());
        zcBuy.setDeptCode(child.getDeptcode());
        zcBuy.setDel(0);
        zcBuy.setApplyTime(new Date());
        zcBuy.setStepid(flowsteps.get(0).getId());
        zcBuyDao.save(zcBuy);
        if (!CollectionUtils.isEmpty(zcBuyItemList)){
            zcBuyItemDao.saves(zcBuyItemList,zcBuy.getId(),user.getId());
        }
        log.debug("新增购买申请单{}", user.getId());
        // 类别判断
        if ("1".equals(zcBuyDto.getType())) {
            // 启动流程
            this.startProcess(String.valueOf(zcBuy.getId()));
        }
        return zcBuyDto;
    }

    @Override
    public void startProcess(String buyId) {
        LoginUser loginUser = UserUtil.getLoginUser();
        // 购买主流程
        ZcBuy zcBuy = zcBuyDao.getById(Long.parseLong(buyId));
        if (zcBuy.getStatus() !=0 ){
            log.info("【流程已经启动】");
            throw new RuntimeException("流程已经启动");
        }
        Long flowId = zcBuy.getFlowid();
        // 查询购买流程
        Flow flow = flowDao.getById(flowId);
        // 查询子节点
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowId);
        // 报废子表
        List<ZcBuyItem> zcBuyItems = zcBuyItemDao.listByZcBuyId(zcBuy.getId());
        // 获取所有的管理部门
        ArrayList<Long> deptIdList = getGlDeptIdList(zcBuyItems);
        if (deptIdList.size()<=0){
            log.info("【报废资产无管理部门】");
            throw new RuntimeException("报废资产无管理部门");
        }
        // 给自己插入待办信息
        Long flowTodoId1 = saveFlowTodo(loginUser.getId(), loginUser, zcBuy, flowsteps.get(0),1,"1");
        for (ZcBuyItem zcBuyItem : zcBuyItems) {
            // 属于当前部门信息
            FlowTodoItem flowTodoItem = new FlowTodoItem();
            flowTodoItem.setFlowTodoId(flowTodoId1);
            flowTodoItem.setFlowItemId(zcBuyItem.getId());
            flowTodoItem.setStatus(0);
            int ressult = flowTodoItemDao.save(flowTodoItem);
        }
        // 根据报废资产部门ID查询节点人员信息
        Long nextNodeId = getNextNodeId(1,zcBuy.getStepid(), flowsteps);
        // 当前节点角色
        Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcBuy.getFlowid(), nextNodeId);
        // 查询用户
        //List<FlowDeptUser> flowDeptUsers = flowDeptUserDao.listAllFlowDeptUser(deptIdList,flowId,nextNodeId);
        // 申请人信息
        //SysUserDto user = userDao.getById(zcBf.getApplyUserId());
        // 查询所有的部门信息,获取父节点部门信息
        HashMap<Long, String> deptMap = getDeptMap();
        List<Dept> deptList = deptDao.listDeptSAndParent(deptIdList);
        //List<Long> parentDeptIdList = getParentDeptList(deptList);
        TreeSet<Long> userIds = new TreeSet<>();
        ArrayList<Long> zcItemIds = new ArrayList<>();
        //ArrayList<Long> zcids = new ArrayList<>();
        // 循环管理部门
        for (Long did : deptIdList) {
            Dept dept = deptDao.getById(did);
            String deptcode = dept.getDeptcode();
            Long currentId = dept.getId();
            // 根据部门角色信息查询人员信息
            // List<HashMap<String,Object>> userList = userDao.findByRoleIdAndDeptCode(memidByFlowStep,deptcode);
            SysUser user = userDao.getUserByRoleDept(memidByFlowStep, currentId);
            if ( user == null ){
                throw new RuntimeException("购买资产无审核人员");
            }
            // 获取第一个人
            //HashMap<String, Object> checkUser = userList.get(0);
            //String id = String.valueOf(checkUser.get("id"));
            Long id = user.getId();
            // 插入待办任务信息  最后参数为类型  1:流程启动  2:流程审核
            Long flowTodoId = saveFlowTodo(id, loginUser, zcBuy, getFlowstepById(nextNodeId, flowsteps),2,"0");
            for (ZcBuyItem zcBuyItem : zcBuyItems) {
                if ( currentId == zcBuyItem.getGlDeptId() && !zcItemIds.contains(zcBuyItem.getId())){
                    // 属于当前部门信息
                    FlowTodoItem flowTodoItem = new FlowTodoItem();
                    flowTodoItem.setFlowTodoId(flowTodoId);
                    flowTodoItem.setFlowItemId(zcBuyItem.getId());
                    flowTodoItem.setStatus(0);
                    int ressult = flowTodoItemDao.save(flowTodoItem);
                    zcItemIds.add(zcBuyItem.getId());
                }
            }
        }
        // 跟新资产的状态,审核部门审核 2:审核部门
        zcBuyItemDao.updateStatus(zcBuy.getId(),2);
        // 更新报废的状态
        Map<String, Object> params = new HashMap<>();
        params.put("zcBuyId",zcBuy.getId());
        params.put("status",1);
        params.put("stepid",flowsteps.get(1).getId());
        zcBuyDao.updateStatus(params);
    }

    @Override
    public String check(ZcBuyCheckDto zcBuyCheckDto) {

        LoginUser loginUser = UserUtil.getLoginUser();
        List<FlowTodoItem> flowTodoItems = zcBuyCheckDto.getFlowTodoItems();
        Long zcBuyId = zcBuyCheckDto.getZcBuyId();
        ZcBuy zcBuy = zcBuyDao.getById(zcBuyId);

        Long applyUserId = zcBuy.getApplyUserId();
        SysUserDto applyUser = userDao.getById(applyUserId);
        Dept applyDept = deptDao.getById(applyUser.getDeptid());
        Long itemStatus = zcBuyCheckDto.getItemStatus();
        Long stepid = zcBuy.getStepid();
        Long flowid = zcBuy.getFlowid();
        List<Flowstep> flowsteps = flowstepDao.listSteps(flowid);
        Flowstep flowstep = getFlowstepById(stepid,flowsteps);
        //-------------------------------------------------------------
        FlowTodoItem flowTodoItem1 = flowTodoItems.get(0);
        Long todoId = flowTodoItem1.getFlowTodoId();
        Todo flowTodo = todoDao.getById(todoId);
        // 1,修改购买子项分类别
        List<FlowTodoItem> agreeTodoItems = new ArrayList<>();        // 1审核为同意   2财务为同意  3再次提交的
        List<Long> agreeToDoItemIds = new ArrayList<>();
        List<Long> agreeBfItemIds = new ArrayList<>();

        List<FlowTodoItem> refuseTodoItems = new ArrayList<>();       // 1审核为拒绝    2财务为拒绝  3再次提交删除
        List<Long> refuseToDoItemIds = new ArrayList<>();
        List<Long> refuseBfItemIds = new ArrayList<>();

        List<FlowTodoItem> backTodoItems = new ArrayList<>();
        List<Long> backToDoItemIds = new ArrayList<>();
        List<Long> backBfItemIds = new ArrayList<>();

        for (FlowTodoItem flowTodoItem : flowTodoItems) {
            // 更新购买子项信息
            zcBuyItemDao.updateByflowTodoItem(flowTodoItem);
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
                // 更新更新购买Item
                zcBuyItemDao.updateListStatus("cwb",1,agreeBfItemIds);
            }else if ( itemStatus == 2 && zcBuyCheckDto.getAgainSubmit() == null ) {
                // 更新todoItem
                flowTodoItemDao.updateListStatus(1,agreeToDoItemIds);
                // 更新购买子项
                zcBuyItemDao.updateListStatus("shb",1,agreeBfItemIds);
            }
            if (zcBuyCheckDto.getAgainSubmit() != null && zcBuyCheckDto.getAgainSubmit() == 3) {
                FlowTodoItem flowTodoItem = agreeTodoItems.get(0);
                Long flowTodoId = flowTodoItem.getFlowTodoId();
                Todo todo = todoDao.getById(flowTodoId);
                Long sendby = todo.getSendby();
                Long aLong = saveFlowTodo(sendby, UserUtil.getLoginUser(), zcBuy, flowsteps.get(1),2,"0");
                for (FlowTodoItem agreeTodoItem : agreeTodoItems) {
                    // 属于当前部门信息
                    FlowTodoItem item = new FlowTodoItem();
                    item.setFlowTodoId(aLong);
                    item.setFlowItemId(agreeTodoItem.getFlowItemId());
                    item.setStatus(0);
                    int ressult = flowTodoItemDao.save(item);
                    // 更新报废子项审核部状态
                    zcBuyItemDao.updateShStatusNull(agreeTodoItem.getFlowItemId());
                }
            }
        }
        // 审核部处理拒绝的   财务部处理正常的
        if (refuseTodoItems.size()>0){
            if ( itemStatus == 3 ){
                // 财务拒绝
                // 更新todoItem
                flowTodoItemDao.updateListStatus(3,refuseToDoItemIds);
                // 更新报废item
                //zcBuyItemDao.updateListStatus("cwb",2,refuseBfItemIds);
                zcBuyItemDao.updateListStatus("jujue",1,refuseBfItemIds);
            }else if ( itemStatus == 2 ){
                // 更新todoItem
                flowTodoItemDao.updateListStatus(3,refuseToDoItemIds);
                // 更新报废item
                zcBuyItemDao.updateListStatus("jujue",1,refuseBfItemIds);
            }
            // 发送消息
            noticeDao.save(getRefuseNotice(applyDept, applyUser, refuseTodoItems, String.valueOf(applyUser.getId()), zcBuy.getApplyTime()));
        }
        // 处理驳回的
        if (backTodoItems.size()>0){
            // 更改状态 指定任务 更改状态
            flowTodoItemDao.updateListStatus(3,backToDoItemIds);
            // 保存待办主信息
            Long currentFlowid = saveFlowTodo(flowTodo.getSendby(), (SysUser)loginUser, zcBuy, flowstep,1,"0");
            for (FlowTodoItem backTodoItem : backTodoItems) {
                // 属于当前部门信息
                FlowTodoItem flowTodoItem = new FlowTodoItem();
                flowTodoItem.setFlowTodoId(currentFlowid);
                flowTodoItem.setFlowItemId(backTodoItem.getFlowItemId());
                flowTodoItem.setStatus(0);
                int ressult = flowTodoItemDao.save(flowTodoItem);
            }
            // 更新报废子项
            zcBuyItemDao.updateListStatus("shb",3,backBfItemIds);
        }
        // 2,修改当TODO前状态
        flowTodo.setNeirong(zcBuyCheckDto.getNeirong());
        // 待办中两种状态 0:待办理 1:已办理
        flowTodo.setStatus("1");
        todoDao.update(flowTodo);
        // 判断当前节点任务是否完成,完成后指派到下一级
        boolean flag = checkStepFinished(zcBuy);
        if (flag) {
            // 流程最后为财务部审核
            Flowstep lastFlowstep = flowsteps.get(flowsteps.size() - 1);
            if ( stepid == lastFlowstep.getId() ){
                // 流程已经处理完毕
                Map<String, Object> params = new HashMap<>();
                params.put("status",2);
                params.put("zcBuyId",zcBuyId);
                zcBuyDao.updateStatus(params);
                // 往资产表插入信息
                //saveWaitBuyZcInfo(zcBuy);
                // 生成ECPID
                generateEcpId(zcBuy);
            }
            if ( stepid == flowsteps.get(1).getId() ) {
                // 查询报废子表
                List<ZcBuyItem> zcBuyItems = zcBuyItemDao.listByZcBuyId(zcBuyId);
                if (zcBuyItems.size()==0){
                    // 审核部全部拒绝
                    // 流程已经处理完毕
                    Map<String, Object> params = new HashMap<>();
                    params.put("status",2);
                    params.put("zcBuyId",zcBuyId);
                    zcBuyDao.updateStatus(params);
                    return String.valueOf(zcBuyCheckDto.getItemStatus());
                }
                // 获取使用部门ID
                Dept sydept = deptDao.getById(zcBuyItems.get(0).getGlDeptId());
                Dept parentDept = deptDao.getById(sydept.getPid());
                String deptcode = parentDept.getDeptcode();
                // 当前节点角色信息
                // 获取下一级step
                Long nextNodeId = getNextNodeId(1, stepid, flowsteps);
                Long memidByFlowStep = flowmemberDao.getMemidByFlowStep(zcBuy.getFlowid(), nextNodeId);
                List<HashMap<String,Object>> userList = userDao.findByRoleIdAndDeptCode(memidByFlowStep,deptcode);
                HashMap<String, Object> user = userList.get(0);
                String userId = String.valueOf(user.get("id"));
                // 插入通知消息给财务
                noticeDao.save(getNotice(applyDept,applyUser,flowTodoItems,userId,zcBuy.getApplyTime()));
                Flowstep flowstep1 = getFlowstepById(nextNodeId, flowsteps);
                // 插入待办信息
                SysUserDto byId = userDao.getById(zcBuy.getApplyUserId());
                Long aLong = saveFlowTodo(Long.parseLong(userId), userDao.getById(zcBuy.getApplyUserId()), zcBuy, flowstep1,2,"0");
                for (ZcBuyItem zcBuyItem : zcBuyItems) {
                    // 属于当前部门信息
                    FlowTodoItem flowTodoItem = new FlowTodoItem();
                    flowTodoItem.setFlowTodoId(aLong);
                    flowTodoItem.setFlowItemId(zcBuyItem.getId());
                    flowTodoItem.setStatus(0);
                    int ressult = flowTodoItemDao.save(flowTodoItem);
                }
                // 更改报废子项的状态
                zcBuyItemDao.updateStatus(zcBuyId,3);
                // 更新报废的状态
                Map<String, Object> params = new HashMap<>();
                params.put("zcBuyId",zcBuy.getId());
                params.put("stepid",nextNodeId);
                zcBuyDao.updateStatus(params);
            }
        }
        return String.valueOf(zcBuyCheckDto.getItemStatus());
    }

    /**
     * 生成资产追溯码Ecpid
     * @param zcBuy
     */
    private void generateEcpId(ZcBuy zcBuy) {
        Long syDeptId = zcBuy.getSyDeptId();
        Dept dept = deptDao.getById(syDeptId);
        String suCode = dept.getSuCode();
        //int count = zcInfoDao.countByDeptId(syDeptId);
        Dept pDept = deptDao.getById(dept.getPid());
        // 查询追溯码最后的元素
        String lastCode = zcEpcCodeDao.findDeptLastCode(syDeptId,pDept.getDeptcode());
        String substring = "";
        if (lastCode == null) {
            substring = "0";
        }else {
            substring = lastCode.substring(suCode.length());
        }
        int count = Integer.parseInt(substring);
        int buyNum = zcBuyDao.countByZcBuyId(zcBuy.getId());
        if (buyNum>0) {
            ArrayList<String> ecpIdLlist = EcpIdUtil.getEcpIdLlist(count, buyNum, dept.getSuCode());
            ArrayList<ZcEpcCode> insertList = new ArrayList<>();
            for (int i = 0; i < ecpIdLlist.size(); i++) {
                ZcEpcCode zcEpcCode = new ZcEpcCode();
                zcEpcCode.setEpcid(ecpIdLlist.get(i));
                zcEpcCode.setDeptId(dept.getId());
                zcEpcCode.setEnable(1);
                zcEpcCode.setCreateTime(new Date());
                zcEpcCode.setUpdateTime(new Date());
                insertList.add(zcEpcCode);
            }
            zcEpcCodeDao.saves(insertList);
            String ids = String.join(",", ecpIdLlist);
            zcBuy.setBz(ids);
            zcBuy.setStatus(2);
            zcBuy.setUpdateTime(new Date());
            zcBuyDao.update(zcBuy);
        }
    }

    /**
     * 构建通知消息
     * @param applyDept
     * @param applyUser
     * @param flowTodoItems
     * @return
     */
    private Notice getNotice(Dept applyDept, SysUserDto applyUser, List<FlowTodoItem> flowTodoItems,String userId,Date date) {
        List<String> items = new ArrayList<>();
        for (FlowTodoItem flowTodoItem : flowTodoItems) {
            String name = flowTodoItem.getName();
            Integer num = flowTodoItem.getNum();
            items.add(name+":"+num);
        }
        // <p>资产购买申请。【申请部门】：AAA,【申请人】：BBB,【申请时间】：CCC</p>
        // <p>【资产信息】：DDD</p>
        String content = "<p>资产购买申请。【申请部门】："+applyDept.getDeptname()+",【申请人】："+applyUser.getNickname()+",【申请时间】："+DateUtil.format(date)+"</p>"+
                "<p>【资产信息】："+String.join("，",items)+"</p>";
        Notice notice = new Notice();
        notice.setUserId(Long.parseLong(userId));
        notice.setTitle("【资产购买】");
        notice.setStatus(0);
        notice.setUpdateTime(null);
        notice.setContent(content);
        return notice;
    }

    /**
     * 构建通知消息
     * @param applyDept
     * @param applyUser
     * @param flowTodoItems
     * @return
     */
    private Notice getRefuseNotice(Dept applyDept, SysUserDto applyUser, List<FlowTodoItem> flowTodoItems,String userId,Date date) {
        List<String> items = new ArrayList<>();
        for (FlowTodoItem flowTodoItem : flowTodoItems) {
            String name = flowTodoItem.getName();
            Integer num = flowTodoItem.getNum();
            items.add(name+":"+num);
        }
        // <p>资产购买申请。【申请部门】：AAA,【申请人】：BBB,【申请时间】：CCC</p>
        // <p>【资产信息】：DDD</p>
        String content = "<p>你申请的购买资产已被拒绝。【申请部门】："+applyDept.getDeptname()+",【申请人】："+applyUser.getNickname()+",【申请时间】："+DateUtil.format(date)+"</p>"+
                "<p>【资产信息】："+String.join("，",items)+"</p>";
        Notice notice = new Notice();
        notice.setUserId(Long.parseLong(userId));
        notice.setTitle("【资产购买】-【拒绝】");
        notice.setStatus(0);
        notice.setUpdateTime(null);
        notice.setContent(content);
        return notice;
    }

    /**
     * 将新购买资产插入资产信息表中
     * @param zcBuy
     */
    private void saveWaitBuyZcInfo(ZcBuy zcBuy) {
        // 资产使用部门
        Long syDeptId = zcBuy.getSyDeptId();
        Long zcBuyId = zcBuy.getId();
        Long applyUserId = zcBuy.getApplyUserId();
        Dept dept = deptDao.getById(syDeptId);
        String deptcode = dept.getDeptcode();
        // 查询子项
        List<ZcBuyItem> zcBuyItems = zcBuyItemDao.listPassByZcBuyId(zcBuyId);
        for (ZcBuyItem zcBuyItem : zcBuyItems) {
            ZcInfo zcInfo = new ZcInfo();
            zcInfo.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
            int intFlag = (int)((Math.random() * 9 + 1) * 100000);
            zcInfo.setZcCodenum(deptcode+intFlag);
            zcInfo.setZcName(zcBuyItem.getName());
            zcInfo.setModel(zcBuyItem.getModel());
            zcInfo.setOriginalValue(zcBuyItem.getPrice());
            zcInfo.setCname(zcBuyItem.getSupplierName());
            zcInfo.setSyDeptId(syDeptId);
            zcInfo.setGlDeptId(zcBuyItem.getGlDeptId());
            zcInfo.setUseStatus(8);
            // 使用人
            zcInfo.setUserId(applyUserId);
            zcInfoDao.save(zcInfo);
        }
    }

    @Override
    public void updateZcBuy(ZcBuyDto zcBuyDto) {

        // 修改主项
        ZcBuy zcBuy = (ZcBuy)zcBuyDto;
        Long zcBuyId = zcBuy.getId();
        // 删除子项
        zcBuyItemDao.deleteByZcBuyId(zcBuyId);
        List<ZcBuyItem> zcBuyItemList = zcBuyDto.getZcBuyItemList();
        ZcBuy oldZcbuy = zcBuyDao.getById(zcBuy.getId());
        Long applyUserId = oldZcbuy.getApplyUserId();
        List<String> list1 = Arrays.asList("companyName", "bz");
        BeanUtils.copyProperties(zcBuy,oldZcbuy,list1.toArray(new String[list1.size()]));
        zcBuyDao.update(oldZcbuy);
        // 插入子项
        if (!CollectionUtils.isEmpty(zcBuyItemList)){
            zcBuyItemDao.saves(zcBuyItemList,zcBuy.getId(),applyUserId);
            // 类别判断
            if ("1".equals(zcBuyDto.getType())) {
                // 启动流程
                this.startProcess(String.valueOf(zcBuy.getId()));
            }
        }
    }

    /**
     * 判断当前节点是否完成
     * @param zcBuy
     * @return
     */
    private boolean checkStepFinished(ZcBuy zcBuy) {
        boolean flag = false;
        Long stepid = zcBuy.getStepid();
        if ( stepid == 61 ){
            Map<String, Object> params = new HashMap<>();
            int count = zcBuyItemDao.countCheck("shb",zcBuy.getId());
            if (count==0){
                flag = true;
            }
        }
        if ( stepid == 62 ){
            Map<String, Object> params = new HashMap<>();
            int count = zcBuyItemDao.countCheck("cwb",zcBuy.getId());
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
     * @param zcBuy 报废主信息
     * @param flowstep 流程步骤
     * @return
     */
    public Long saveFlowTodo( Long auditUserId,SysUser user, ZcBuy zcBuy,Flowstep flowstep ,Integer memo,String status){
        Todo todo = new Todo();
        todo.setAuditby(auditUserId);
        todo.setSendby(user.getId());
        todo.setBiaoti("【"+user.getNickname()+"】申请资产购买");
        todo.setNeirong(zcBuy.getBz());
        todo.setBizid(zcBuy.getId());
        todo.setBizcreateby(user.getId());
        todo.setBizdeptid(zcBuy.getSyDeptId());
        todo.setStatus(status);
        todo.setFlowid(zcBuy.getFlowid());
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
     * 根据购买清单查询所有的管理部门
     * @param zcBuyItems
     * @return
     */
    public ArrayList<Long> getGlDeptIdList( List<ZcBuyItem> zcBuyItems){
        ArrayList<Long> list = new ArrayList<>();
        TreeSet<Long> depIds = new TreeSet<>();
        for (ZcBuyItem zcBuyItem : zcBuyItems) {
            if ( !(ObjectUtils.isEmpty(zcBuyItem.getGlDeptId())) ){
                depIds.add(zcBuyItem.getGlDeptId());
            }
        }
        list.addAll(depIds);
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
