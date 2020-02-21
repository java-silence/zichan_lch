package com.itycu.server.app.controller;

import com.itycu.server.app.util.FailMap;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.model.Dept;
import com.itycu.server.model.SysUser;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * APP部门管理
 * @author lch
 * @create 2020-02-03 15:42
 */
@RestController
@RequestMapping("/appDept")
@Slf4j
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
        List<Map> list = new LinkedList<>();
        for (Dept dept : deptList) {
            Map mapData = new HashMap();
            mapData.put("id", dept.getId());
            mapData.put("name", dept.getDeptname());
            mapData.put("deptcode", dept.getDeptcode());
            list.add(mapData);
        }
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
        List<Map> list = new LinkedList<>();
        for (Dept dept : deptList) {
            Map mapData = new HashMap();
            mapData.put("id", dept.getId());
            mapData.put("deptname", dept.getDeptname());
            mapData.put("deptcode", dept.getDeptcode());
            list.add(mapData);
        }
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", list);
        return map;
    }

    @PostMapping("/subDeptList")
    @ApiOperation(value = "获取eleTree部门树列表", notes = "获取部门树状表")
    public Map getSubDept() {
        Map<String, Object> map = null;
        try {
            SysUser sysUser = UserUtil.getLoginUser();
            map = new HashMap();
            List<Map<String, Object>> mapList = deptDao.querySubDeptListById(sysUser.getC03());
            map.put("data", mapList);
            map.put("code", 0);
            map.put("message", "成功");
            return map;
        } catch (Exception e) {
            log.info("获取部门树列表失败{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }

}
