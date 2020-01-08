package com.itycu.server.app.controller;


import com.itycu.server.app.service.XunJianService;
import com.itycu.server.app.vo.XunJianVO;
import com.itycu.server.model.SysUser;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "App巡检接口")
@RequestMapping(value = "/xunjian")
public class AppXunJianController {


    @Autowired
    private XunJianService xunJianService;


    @RequestMapping(value = "/list")
    @ApiOperation(notes = "获取App端巡检列表", value = "获取App端巡检列表")
    public List<XunJianVO> getXunjianList() {
        SysUser sysUser = UserUtil.getLoginUser();
        if (null != sysUser) {

        }
        return null;
    }


    @RequestMapping(value = "/insertRecord")
    @ApiOperation(notes = "添加巡检数据到数据库的接口", value = "添加巡检数据到数据库的接口")
    public void insertXunJianRecord() {


    }


    @RequestMapping(value = "/inspect/list")
    @ApiOperation(notes = "获取已经巡检的列表数据", value = "获取已经巡检的列表数据")
    public void getInspectList() {


    }


    @RequestMapping(value = "/inspect/detail")
    @ApiOperation(notes = "获取巡检的详情", value = "获取巡检的详情")
    public void getInspectDetail() {


    }


}
