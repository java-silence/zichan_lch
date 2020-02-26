package com.itycu.server.app.controller;


import com.itycu.server.app.dto.chuzhi.AppDealListDTO;
import com.itycu.server.app.dto.chuzhi.AppDealListRecordDetailDTO;
import com.itycu.server.app.dto.chuzhi.AppInsertDataDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.chuzhi.DealZcInfoVO;
import com.itycu.server.app.vo.fenye.PageVO;
import com.itycu.server.dao.ZcBfDao;
import com.itycu.server.dao.ZcBfItemDao;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.dto.ZcBfDto;
import com.itycu.server.dto.ZcBuyCheckDto;
import com.itycu.server.model.ZcBfItem;
import com.itycu.server.service.ZcBfService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/deal")
@Api(tags = "App处置管理")
public class AppDealController {


    private static Logger logger = LoggerFactory.getLogger(AppDealController.class);

    @Autowired
    private ZcInfoDao zcInfoDao;

    @Autowired
    private ZcBfService zcBfService;

    @Autowired
    private ZcBfDao zcBfDao;

    @Autowired
    private ZcBfItemDao zcBfItemDao;


    @PostMapping(value = "/bfList")
    @ApiOperation(value = "获取资产报废的列表", notes = "资产报废列表")
    public Map<String, Object> getZcBFList(@RequestBody AppDealListDTO appDealListDTO) {
        try {
            int limit = appDealListDTO.getLimit();
            int offset = appDealListDTO.getOffset();
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("del", 0);
            paramsMap.put("glDeptId", UserUtil.getLoginUser().getDeptid());
            paramsMap.put("keyword", appDealListDTO.getKeyword());
            List<DealZcInfoVO> dealZcInfoVOList = zcInfoDao.getZcBFList(paramsMap, offset * limit - limit, limit);
            map.put("data", dealZcInfoVOList);
            map.put("message", "操作成功");
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return FailMap.createFailMap();
        }
    }


    /**
     * TODO 需要测试
     *
     * @param appInsertDataDTO
     * @return
     */
    @PostMapping(value = "/insertBfData")
    @ApiOperation(value = "添加报废记录申请", notes = "添加报废记录申请")
    public Map<String, Object> insertBfData(@RequestBody AppInsertDataDTO appInsertDataDTO) {
        try {
            if (CollectionUtils.isEmpty(appInsertDataDTO.getZcBfItemList())) {
                return FailMap.createFailMapMsg("列表参数为空");
            }
            Map<String, Object> map = new HashMap<>();
            ZcBfDto zcBfDto = new ZcBfDto();
            zcBfDto.setType(appInsertDataDTO.getType() + "");
            zcBfDto.setBfDes(appInsertDataDTO.getBfDes());
            zcBfDto.setBfCategory(appInsertDataDTO.getBfCategory());
            List<ZcBfItem> zcBfItemList = new ArrayList<>();
            List<DealZcInfoVO> infoVOList = appInsertDataDTO.getZcBfItemList();
            infoVOList.forEach(k -> {
                ZcBfItem zcBfItem = new ZcBfItem();
                zcBfItem.setBz(k.getBz());
                zcBfItem.setDel(0);
                zcBfItem.setId(k.getId());
                zcBfItem.setSyDeptId(k.getSyDeptId());
                zcBfItem.setGlDeptId(k.getGlDeptId());
                zcBfItem.setEpcid(k.getEpcid());
                zcBfItem.setZcName(k.getZcName());
                zcBfItemList.add(zcBfItem);
            });
            zcBfDto.setZcBfItemList(zcBfItemList);
            zcBfService.save(zcBfDto);
            map.put("data", "");
            map.put("message", "操作成功");
            map.put("code", 0);
            return map;
        } catch (Exception e) {
            logger.error("添加报废记录申请错误{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }


    /**
     * @param pageVO
     * @return
     */
    @PostMapping(value = "/getBFRecordList")
    @ApiOperation(value = "获取报废的记录列表", notes = "获取报废的记录列表")
    public Map<String, Object> getBFRecordList(@RequestBody PageVO pageVO) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", "gl");
        params.put("glDeptId", UserUtil.getLoginUser().getDeptid());
        Map<String, Object> map = new HashMap<>();
        Integer page = pageVO.getOffset();
        Integer limit = pageVO.getLimit();
        List<Map<String, Object>> list = zcBfDao.listZcbf(params, page * limit - limit, limit);
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "成功");
        return map;
    }


    /**
     * @param
     * @return
     */
    @PostMapping(value = "/getBFRecordItemList")
    @ApiOperation(value = "获取报废的记录列表下面的详情数据列表", notes = "获取报废的记录列表下面的详情数据列表")
    public Map<String, Object> getBFRecordItemList(@RequestBody AppDealListRecordDetailDTO appDealListRecordDetailDTO) {
        try {
            Map<String, Object> map = new HashMap();
            List<Map<String, Object>> list = new ArrayList<>();
            if (0 != appDealListRecordDetailDTO.getZcBfId()) {
                list = zcBfItemDao.listDetailByZcBfId(appDealListRecordDetailDTO.getZcBfId());
            }
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "成功");
            return map;
        } catch (Exception e) {
            logger.error("获取报废的记录列表下面的详情数据列表错误{}", e.getMessage());
            return FailMap.createFailMapMsg("数据列表错误");
        }
    }

    /**
     * 审核部门
     * @param zcBuyCheckDto
     * @return
     */
    @PostMapping("/bfCheckMainInfo")
    @ApiOperation(value = "资产处置审核主信息",notes = "资产处置审核主信息")
    public Map bfCheckMainInfo(@RequestBody ZcBuyCheckDto zcBuyCheckDto) {
        Map<String,Object> map = new HashMap();
        try {

            //map.put("data",data);
            map.put("code","0");
            map.put("message","成功");
        } catch (Exception e) {
            logger.info("资产处置审核主信息{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }


    /**
     * 审核部门
     * @param zcBuyCheckDto
     * @return
     */
    @PostMapping("/bfCheckItemList")
    @ApiOperation(value = "资产处置审核列表信息",notes = "资产处置审核列表信息")
    public Map bfCheckItemList(@RequestBody ZcBuyCheckDto zcBuyCheckDto) {
        Map<String,Object> map = new HashMap();
        try {

            //map.put("data",list);
            map.put("code","0");
            map.put("message","成功");
        } catch (Exception e) {
            logger.info("资产处置审核列表信息{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }


    /**
     * 审核部门
     * @param zcBuyCheckDto
     * @return
     */
    @PostMapping("/buyCheck")
    @ApiOperation(value = "资产处置审核",notes = "资产处置审核")
    public Map buyCheck(@RequestBody ZcBuyCheckDto zcBuyCheckDto) {
        Map<String,Object> map = new HashMap();
        try {
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("status",itemStatus);
//            map.put("data",data);
            map.put("code","0");
            map.put("message","请求成功");
        } catch (Exception e) {
            logger.info("资产处置审核{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }

}
