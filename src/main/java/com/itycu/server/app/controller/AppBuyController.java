package com.itycu.server.app.controller;


import com.itycu.server.app.dto.goumai.InsertBuyDataDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.dto.ZcBuyDto;
import com.itycu.server.model.ZcBuy;
import com.itycu.server.service.ZcBuyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "/buy")
@RestController
@Api(tags = "App购买申请")
public class AppBuyController {


    private static Logger logger = LoggerFactory.getLogger(AppBuyController.class);

    @Autowired
    ZcBuyService zcBuyService;


    @PostMapping(value = "/insertData")
    @ApiOperation(value = "增加购买的信息", notes = "增加购买的信息")
    public Map<String, Object> insertBuyData(@RequestBody InsertBuyDataDTO insertBuyDataDTO) {
        try {
            Map<String, Object> map = new HashMap<>();
            ZcBuyDto zcBuyDto = new ZcBuyDto();
            BeanUtils.copyProperties(insertBuyDataDTO, zcBuyDto);
            zcBuyDto.setType("1");
            zcBuyService.save(zcBuyDto);
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", null);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("增加购买的信息错误{}", e.getMessage());
            return FailMap.createFailMapMsg("");
        }
    }


    @PostMapping(value = "/buyRecordList")
    @ApiOperation(value = "购买的记录列表", notes = "购买的记录列表")
    public Map<String, Object> getBuyRecordList(@RequestBody InsertBuyDataDTO insertBuyDataDTO) {
        try {
            Map<String, Object> map = new HashMap<>();
            /**
             * TODO  逻辑处理
             *
             *
             *
             */
            map.put("code", 0);
            map.put("message", "成功");
            map.put("data", null);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("增加购买的信息错误{}", e.getMessage());
            return FailMap.createFailMapMsg("");
        }
    }
}
