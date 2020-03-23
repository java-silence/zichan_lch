package com.itycu.server.app.controller;


import com.itycu.server.app.model.AppUserInfo;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.model.Dept;
import com.itycu.server.model.SysUser;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Api(tags = "获取登录用户信息")
public class AppApiController {


    @Autowired
    private DeptDao deptDao;


    @ApiOperation(value = "当前登录用户")
    @GetMapping("/getUserInfo")
    @Transactional
    public Map<String,Object>  getUserInfo() {

        Map<String,Object> hashMap=new HashMap<>();
        SysUser sysUser = UserUtil.getLoginUser();
        if(null!=sysUser){
            AppUserInfo appUserInfo=new AppUserInfo();
            appUserInfo.setId(sysUser.getId());
            appUserInfo.setC03(sysUser.getC03());
            appUserInfo.setDeptId(sysUser.getDeptid());
            appUserInfo.setCreateTime(sysUser.getCreateTime().toString());
            appUserInfo.setUpdateTime(sysUser.getUpdateTime().toString());
            appUserInfo.setUsername(sysUser.getUsername());
            appUserInfo.setNickname(sysUser.getNickname());
            appUserInfo.setPhone(sysUser.getPhone());
            appUserInfo.setHeadImgUrl(sysUser.getHeadImgUrl());
            appUserInfo.setEmail(sysUser.getEmail());
            appUserInfo.setBirthday(sysUser.getBirthday());
            appUserInfo.setSex(sysUser.getSex());
            appUserInfo.setStatus(sysUser.getStatus());
            appUserInfo.setMemo(sysUser.getMemo());
            Dept dept = deptDao.getById(sysUser.getDeptid());
            String name = dept ==null? null : dept.getDeptname();
            appUserInfo.setDeptName(name);

            // 总部账号对c03处理
            String username = sysUser.getUsername();
            if ("664000".equals(username)) {
                appUserInfo.setC03("zonghang");
            }
            if ("650000".equals(username)) {
                appUserInfo.setC03("zonghang1");
            }

            hashMap.put("code", "0");
            hashMap.put("message", "成功");
            hashMap.put("data",appUserInfo);
        }else{
            hashMap.put("code", "500");
            hashMap.put("message", "成功");
        }
        return hashMap;
    }
}
