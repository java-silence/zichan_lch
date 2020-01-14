package com.itycu.server.app.controller;


import com.itycu.server.app.dto.baoxiu.RepairZcInfoListDTO;
import com.itycu.server.app.dto.baoxiu.RepairZcItemRecordListDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.baoxiu.RepairZcInfoListVO;
import com.itycu.server.dao.RepairsDao;
import com.itycu.server.dao.ZcRepairDao;
import com.itycu.server.dao.ZcRepairItemDao;
import com.itycu.server.dto.ZcRepairItemDto;
import com.itycu.server.model.SysUser;
import com.itycu.server.service.RepairService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "app资产保修接口")
@RestController
@RequestMapping(value = "/app/repair")
public class AppRepairController {


    private static Logger logger = LoggerFactory.getLogger(AppRepairController.class);

    @Autowired
    private RepairsDao repairDao;

    @Autowired
    private ZcRepairDao zcRepairDao;

    @Autowired
    private RepairService repairService;

    @Autowired
    private ZcRepairItemDao zcRepairItemDao;


    @PostMapping(value = "/list")
    @ApiOperation(value = "获取保修的资产列表", notes = "获取保修的资产列表")
    public Map<String, Object> getRepairVOList(@RequestBody RepairZcInfoListDTO repairZcInfoListDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            int page = repairZcInfoListDTO.getOffset();
            int limit = repairZcInfoListDTO.getLimit();
            Map<String, Object> params = new HashMap<>();
            SysUser sysUser = UserUtil.getLoginUser();
            params.put("id", sysUser.getDeptid());
            params.put("keyword", repairZcInfoListDTO.getKeyword());
            List<RepairZcInfoListVO> listVOS = repairDao.getRepairVOList(params, page * limit - limit, limit);
            map.put("data", listVOS);
            map.put("code", 0);
            map.put("message", "成功");

        } catch (Exception e) {
            logger.info("获取保修的资产列表失败{}", e.getMessage());
            return FailMap.createFailMapMsg("获取保修的资产列表失败");
        }
        return map;
    }


    @PostMapping(value = "/repair/recordList")
    @ApiOperation(value = "获取保修的【记录列表】", notes = "获取保修的【记录列表】")
    public Map<String, Object> getRecordList(@RequestBody RepairZcInfoListDTO repairZcInfoListDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            int page = repairZcInfoListDTO.getOffset();
            int limit = repairZcInfoListDTO.getLimit();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("del", "0");
            List list = zcRepairDao.list(params, page * limit - limit, limit);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "成功");
        } catch (Exception e) {
            logger.info("获取保修的【记录列表】==>{}", e.getMessage());
            return FailMap.createFailMapMsg("获取保修的记录列表失败");
        }
        return map;
    }


    @PostMapping(value = "/repair/record/zcList")
    @ApiOperation(value = "获取保修记录的【资产列表】", notes = "获取保修记录的【资产列表】")
    public Map<String, Object> getRepairRecordItemList(@RequestBody RepairZcInfoListDTO repairZcInfoListDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            int page = repairZcInfoListDTO.getOffset();
            int limit = repairZcInfoListDTO.getLimit();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("del", "0");
            List list = zcRepairDao.list(params, page * limit - limit, limit);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "成功");
        } catch (Exception e) {
            logger.info("获取保修的【记录列表】==>{}", e.getMessage());
            return FailMap.createFailMapMsg("获取保修的记录列表失败");
        }
        return map;
    }


    @PostMapping(value = "/listByZcReId")
    @ApiOperation(value = "根据报修记录资产数据列表", notes = "根据报修记录资产数据列表")
    public Map<String, Object> listByZcReId(@RequestBody RepairZcItemRecordListDTO repairZcItemRecordListDTO) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<ZcRepairItemDto> list = new ArrayList<>();
            if (repairZcItemRecordListDTO != null) {
                list = zcRepairItemDao.listByZcReId(repairZcItemRecordListDTO.getId());
            }
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "成功");
        } catch (Exception e) {
            logger.info("根据报修记录资产数据列表==>{}", e.getMessage());
            return FailMap.createFailMapMsg("根据报修记录资产数据列表失败");
        }
        return map;
    }

}
