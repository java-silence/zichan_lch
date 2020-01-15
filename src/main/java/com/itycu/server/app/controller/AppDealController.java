package com.itycu.server.app.controller;


import com.itycu.server.app.dto.chuzhi.AppDealListDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.chuzhi.DealZcInfoVO;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/deal")
@Api(tags = "App处置管理")
public class AppDealController {


    @Autowired
    private ZcInfoDao zcInfoDao;


    @PostMapping(value = "/bfList")
    @ApiOperation(value = "获取资产报废的列表", notes = "资产报废列表")
    public Map<String, Object> getZcBFList(@RequestBody AppDealListDTO appDealListDTO) {
        try {
            int limit = appDealListDTO.getLimit();
            int offset = appDealListDTO.getOffset();
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("del",0);
            paramsMap.put("glDeptId", UserUtil.getLoginUser().getDeptid());
            paramsMap.put("keyword", appDealListDTO.getKeyword());
            List<DealZcInfoVO> dealZcInfoVOList  = zcInfoDao.getZcBFList(paramsMap,offset * limit - limit, limit);
            map.put("data",dealZcInfoVOList);
            map.put("message","操作成功");
            map.put("code",0);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return FailMap.createFailMap();
        }
    }
}
