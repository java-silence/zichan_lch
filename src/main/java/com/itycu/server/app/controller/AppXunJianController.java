package com.itycu.server.app.controller;


import com.itycu.server.app.dto.xunjian.XunJianItemDetailDTO;
import com.itycu.server.app.dto.xunjian.XunJianSubmitDTO;
import com.itycu.server.app.service.XunJianService;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.xunjian.XunJianVO;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.ZcInfo;
import com.itycu.server.model.ZcInspectRecord;
import com.itycu.server.service.ZcInfoService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "App巡检接口")
@RequestMapping(value = "/xunjian")
public class AppXunJianController {


    private static Logger logger = LoggerFactory.getLogger(AppXunJianController.class);

    @Autowired
    private XunJianService xunJianService;

    @Autowired
    private ZcInfoService zcInfoService;


    @Value("${IMAGE_SERVER}")
    private String IMAGE_SERVER;


    @PostMapping(value = "/list")
    @ApiOperation(notes = "获取App端巡检列表", value = "获取App端巡检列表")
    public Map<String, Object> getXunjianList() {
        Map<String, Object> map = new HashMap<>();
        try {
            SysUser sysUser = UserUtil.getLoginUser();
            if (null != sysUser) {
                List<XunJianVO> list = xunJianService.getXunjianList(sysUser);
                map.put("code", 0);
                map.put("message", "成功");
                map.put("data", list);
            }
        } catch (Exception e) {
            logger.error("获取资产净值和资产数量错误,{}", e.getMessage());
            map = FailMap.createFailMap();
        }
        return map;
    }


    @PostMapping(value = "/insertRecord")
    @ApiOperation(notes = "添加巡检数据到数据库", value = "添加巡检数据到数据库")
    public Map<String, Object> insertXunJianRecord(@RequestBody XunJianSubmitDTO xunJianSubmitDTO) {
        ZcInfo zcInfo = zcInfoService.queryZnInfoByEpcId(xunJianSubmitDTO.getEpcid());
        if (null != zcInfo) {
            Map<String, Object> map  = xunJianService.insertInspectRecord(xunJianSubmitDTO,zcInfo);
            return map;
        } else {
            return null;
        }
    }


    @PostMapping(value = "/inspect/list")
    @ApiOperation(notes = "获取已经巡检的列表数据", value = "获取已经巡检的列表数据")
    public Map<String, Object> getInspectRecordList() {
        SysUser sysUser = UserUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        try {
            List<XunJianVO> list = xunJianService.getInspectRecordList(sysUser);
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", list);
        } catch (Exception e) {
            logger.error("获取资产净值和资产数量错误,{}", e.getMessage());
            map = FailMap.createFailMap();
        }
        return map;
    }


    @PostMapping(value = "/inspect/detail")
    @ApiOperation(notes = "获取巡检的详情", value = "获取巡检的详情")
    public Map<String, Object> getInspectDetail(@RequestBody XunJianItemDetailDTO xunJianItemDetailDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            ZcInspectRecord record = xunJianService.getInspectRecordById(xunJianItemDetailDTO.getId());
            if (null != record) {
                record.setImg(IMAGE_SERVER + record.getImg());
            }
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", record);
        } catch (Exception e) {
            logger.error("获取巡检的详情,{}", e.getMessage());
            map = FailMap.createFailMap();
        }
        return map;
    }
}
