package com.itycu.server.app.service.impl;

import com.itycu.server.app.model.AppIndexDeptDataInfo;
import com.itycu.server.app.service.IndexService;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.TodoDao;
import com.itycu.server.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    DeptDao deptDao;

    @Autowired
    TodoDao todoDao;

    @Override
    public List<AppIndexDeptDataInfo> getAllManagerDeptList(long id) {
        return deptDao.getAllManagerDeptList(id);
    }

    @Override
    public List<AppIndexDeptDataInfo> getAllBranchDeptList(long id) {
        List<AppIndexDeptDataInfo> appIndexDeptDataInfoList  = deptDao.getAllBranchDeptList(id);
        return appIndexDeptDataInfoList;
    }

    @Override
    public List<Todo> queryAllTodoList(long deptId) {
        return todoDao.queryAllTodoList(deptId);
    }
}
