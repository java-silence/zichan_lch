package com.itycu.server.app.controller;


import com.itycu.server.app.dto.baoxiu.RepairZcInfoListDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.baoxiu.RepairZcInfoListVO;
import com.itycu.server.dao.RepairsDao;
import com.itycu.server.model.SysUser;
import com.itycu.server.service.RepairService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "app资产保修接口")
@RestController
@RequestMapping(value = "/app/repair")
public class AppRepairController {


    private static Logger logger = LoggerFactory.getLogger(AppRepairController.class);

    @Autowired
    private RepairsDao repairsDao;

    @Autowired
    private RepairService  repairService;



    @PostMapping(value = "/list")
    @ApiOperation(value = "获取保修的资产列表",notes = "获取保修的资产列表")
    public Map<String,Object> getRepairVOList(@RequestBody RepairZcInfoListDTO repairZcInfoListDTO){
        Map<String,Object>  map = new HashMap<>();
        try {
            int page = repairZcInfoListDTO.getOffset();
            int limit = repairZcInfoListDTO.getLimit();
            Map<String,Object> params = new HashMap<>();
            SysUser sysUser = UserUtil.getLoginUser();
            params.put("id",sysUser.getDeptid());
            params.put("keyword",repairZcInfoListDTO.getKeyword());
            List<RepairZcInfoListVO> listVOS = repairsDao.getRepairVOList(params,page * limit - limit, limit);
            map.put("data",listVOS);
            map.put("code",0);
            map.put("message","成功");

        } catch (Exception e) {
            logger.info("获取保修的资产列表失败{}",e.getMessage());
            return FailMap.createFailMapMsg("获取保修的资产列表失败");
        }
        return map;
    }

}
