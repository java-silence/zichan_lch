package com.itycu.server.service.impl;

import com.itycu.server.dao.FlowDocDao;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.model.FlowDoc;
import com.itycu.server.service.FlowDocService;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fanlinglong on 2019-02-02.
 */
@Service
public class FlowDocServiceImpl implements FlowDocService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private TodoService todoService;
    @Autowired
    private FlowDocDao flowDocDao;

    @Override
    public FlowDoc save(FlowDoc flowDoc) {
        flowDoc.setCreateby(UserUtil.getLoginUser().getId());
        flowDoc.setDeptid(UserUtil.getLoginUser().getDeptid());
        flowDocDao.save(flowDoc);
        TodoDto todo = new TodoDto();
        todo.setBiaoti("【" +  UserUtil.getLoginUser().getNickname() +"】升降机使用申请");
        todo.setSendby(flowDoc.getCreateby());
        todo.setBizid(flowDoc.getId());
        todo.setFlowid(flowDoc.getFlowid());
        todo.setStepid(1L);
        todo.setStatus("0");
        todo.setBizcreateby(flowDoc.getCreateby());
        todo.setBizdeptid(flowDoc.getDeptid());
        todo.setBiztable("flow_doc");

        todoService.sendTodo(todo);


        log.debug("新增升降机申请单{}", UserUtil.getLoginUser().getNickname() + flowDoc.getTitle());

        return flowDoc;
    }

    @Override
    public TodoDto todo(TodoDto todoDto) {
        todoService.update(todoDto);
        return todoDto;
    }
}
