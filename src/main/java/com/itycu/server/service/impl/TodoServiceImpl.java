package com.itycu.server.service.impl;

import com.itycu.server.dao.*;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.Flowmember;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.Todo;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Hezhilin on 2018/3/11 0011.
 */
@Service
public class TodoServiceImpl implements TodoService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private TodoDao todoDao;

    @Autowired
    private FlowmemberDao flowmemberDao;

    @Autowired
    private FlowstepDao flowstepDao;

//    @Autowired
//    private RepairDao repairDao;

    @Autowired
    private PushServiceImpl pushService;


    @Autowired
    private UserDao userDao;

//审批
    @Override
    @Transactional
    public Todo update(TodoDto todo) {
//        todoDao.getById(todo.getId()).getFlowid();
        Todo todo1 = todoDao.getById(todo.getId());
        todo.setFlowid(todo1.getFlowid());
        todo.setStepid(todo1.getStepid());
        todo.setBiaoti(todo1.getBiaoti());
        todo.setBiztable(todo1.getBiztable());
        if(todo.getSend() == 1){
            todo.setStatus("1");
        }
         todoDao.update(todo);
        //添加下一个审批人待办
        if(todo.getSend() == 1){
            todoDao.bizNextStep(todo.getBiztable(),todo.getBizid(),todo.getStepid());
            if(todo.getBack() == null) todo.setBack("0");
            if(todo.getBack().equals("1")){        //驳回处理
                todo = sendTodo(todo);
                //repairDao.toNextStep(todo.getBizid());     //返写申请中的stepid +1
//                todoDao.bizNextStep(todo.getBiztable(),todo.getBizid(),todo.getStepid() -1);
            }else {
                if (todo.getStepid()+1 <= flowstepDao.getMaxStepbyFlowId(todo.getFlowid()) ){
                    if(flowstepDao.getByStpeId(todo.getFlowid(),todo.getStepid()).getTofinish().equals("0")){   //未结束的流程
                        sendTodo(todo);
                        //repairDao.toNextStep(todo.getBizid());     //返写申请中的stepid +1
                    }
                }
                //推送消息
                pushService.PushMsg( "跟踪消息", "【跟踪】"+todo1.getBiaoti(), todo1.getSendby(), todo1.getUrl(), todo1.getId(),todo1.getBizid());
            }
        }

        //保存附件
        saveFiles(todo.getBizid(),todo1.getFlowid().toString(),todo.getFileIds());

        log.debug("审批待办事宜{}", todo.getBiaoti());

        return todo;
    }

    //保存附件
    private void saveFiles(long todoId, String biztype, List<String> fileIds){
        if(fileIds != null && fileIds.size()>0 ){
            todoDao.saveFiles(todoId,biztype,fileIds );
        }
    }

    //新增下一审批人待办
    @Override
    public TodoDto sendTodo(TodoDto todo){
        TodoDto todoNext = new TodoDto();
        Todo todoOld = todoDao.getById(todo.getId());   //由于todo为前台提交数据不全，所以从库里再取一次

        if(todoOld != null){
            if(todo.getBizdeptid()== null){
                todo.setBizdeptid(todoOld.getBizdeptid());
            }
            if(todo.getBizcreateby()== null){
                todo.setBizcreateby(todoOld.getBizcreateby());
            }
            if(todo.getBiztable()== null){
                todo.setBiztable(todoOld.getBiztable());
            }
            if(todo.getAuditby() == null){
                todo.setAuditby(todoOld.getAuditby());
            }
        }

        Long audityBy ; //=flowmemberDao.getMemidByFlowStep(todo.getFlowid(),todo.getStepid()+1);

        Long nextStep;

        if(todo.getBack() == null) todo.setBack("0");

        //驳回
        if(todo.getBack().equals("1") ){
            nextStep = todo.getStepid()-1;
            audityBy = todoOld.getSendby();
        }else{
            if (todo.getJumpto()!=null) {
                nextStep = todo.getJumpto();
            }else{
                nextStep = todo.getStepid()+1;
            }

            Flowmember flowmember = flowmemberDao.getByFlowStep(todo.getFlowid(),nextStep);
            audityBy = flowmember.getMemid();
            //成员类型 1:用户,2:岗位,3:部门,4:上级岗位,5:本人,6上一流程指定,7业务所在部门岗位,8同上一流程
            if(new Integer(6).equals(flowmember.getMemtype())){
                audityBy = todo.getUserid();    //取上一流程中指定的用户ID
            }else if(new Integer(7).equals(flowmember.getMemtype())){    //取业务表中部门，然后该部门指定岗位或角色的用户
                Long deptid = todo.getBizdeptid();// repairDao.getById(todo.getBizid()).getDeptid();
                SysUser userByRoleDept = userDao.getUserByRoleDept(flowmember.getMemid(), deptid);
                audityBy = userDao.getUserByRoleDept(flowmember.getMemid(),deptid).getId();
            }else if(new Integer(5).equals(flowmember.getMemtype())){        //发起流程的本人
                audityBy = todo.getBizcreateby();   // repairDao.getById(todo.getBizid()).getCreateby();
            }else if(new Integer(8).equals(flowmember.getMemtype())){       //同上一流程
                audityBy = todo.getAuditby();//  todo中sendby为空，重取一次 todo.getSendby();
            }
        }
        todoNext.setAuditby(audityBy);
        todoNext.setBiaoti(todo.getBiaoti());
        todoNext.setSendby(UserUtil.getLoginUser().getId());
        todoNext.setBizid(todo.getBizid());
        todoNext.setFlowid(todo.getFlowid());
        todoNext.setStepid(nextStep);
        todoNext.setStatus("0");

        todoNext.setUrl(flowstepDao.getByStpeId(todo.getFlowid(),nextStep).getFlowact());

        todoNext.setBizcreateby(todo.getBizcreateby());
        todoNext.setBizdeptid(todo.getBizdeptid());
        todoNext.setBiztable(todo.getBiztable());

        todoDao.save(todoNext);

        //推送消息
        pushService.PushMsg( "审批消息", todoNext.getBiaoti(), audityBy, todoNext.getUrl(), todoNext.getId(),todoNext.getBizid());

        return  todoNext;
    }
}
