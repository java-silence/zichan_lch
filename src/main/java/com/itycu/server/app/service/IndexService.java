package com.itycu.server.app.service;

import com.itycu.server.app.model.AppIndexDeptDataInfo;
import com.itycu.server.app.vo.todovo.AppTodoVO;
import com.itycu.server.model.Todo;

import java.util.List;

public interface IndexService {


    List<AppIndexDeptDataInfo> getAllManagerDeptList(long id);


    List<AppIndexDeptDataInfo> getAllBranchDeptList(long id);


    List<Todo> queryAllTodoList(long userId);

    List<AppTodoVO> appQueryAllTodoList(Long userId);

    List<AppTodoVO> appQueryAllTodoList(Long userId,Integer status);
}
