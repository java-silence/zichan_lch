package com.itycu.server.app.controller;

import com.itycu.server.dao.DeptDao;
import com.itycu.server.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author lch
 * @create 2020-02-03 15:42
 */
@RestController
@RequestMapping("/dept")
public class AppDeptController {

    @Autowired
    private DeptDao deptDao;

    /**
     * 管理部门列表
     * @return
     */
    @GetMapping("/glDeptList")
    public Map<String, Object> glDeptList() {
        Map<String, Object> map = new HashMap<>();
        // 查询下级所有的管理部门
        List<Dept> deptList = deptDao.listChildGlDept(11l, 3l);
        List<Map> list = getList(deptList);
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", list);
        return map;
    }

    /**
     * 全部部门列表
     * @return
     */
    @GetMapping("/deptList")
    public Map<String, Object> deptList() {
        Map<String, Object> map = new HashMap<>();
        // 查询下级所有部门
        List<Dept> deptList = deptDao.listByPid(11l);
        List<Map> list = getList(deptList);
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", list);
        return map;
    }

    public List<Map> getList(List<Dept> deptList) {
        List<Map> list = new LinkedList<>();
        for (Dept dept : deptList) {
            Map mapData = new HashMap();
            mapData.put("id", dept.getId());
            mapData.put("name", dept.getDeptname());
            mapData.put("deptcode", dept.getDeptcode());
            list.add(mapData);
        }
        return list;
    }

}
