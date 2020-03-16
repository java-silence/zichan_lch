package com.itycu.server.app.controller;

import com.itycu.server.app.dto.diaopei.DeployZcListDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.diaopei.DeployZcItemRecordDTO;
import com.itycu.server.app.vo.diaopei.DeployZcListVO;
import com.itycu.server.app.vo.fenye.PageVO;
import com.itycu.server.dao.*;
import com.itycu.server.dto.ZcDeployCheckDto;
import com.itycu.server.dto.ZcDeployDto;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.ZcCategory;
import com.itycu.server.model.ZcDeployItem;
import com.itycu.server.service.ZcDeployService;
import com.itycu.server.service.ZcInfoService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/deploy")
@Api(tags = "App端资产调配管理")
public class AppDeployController {

    private static Logger logger = LoggerFactory.getLogger(AppDeployController.class);

    @Autowired
    private ZcInfoDao zcInfoDao;

    @Autowired
    private ZcDeployService zcDeployService;

    @Autowired
    private ZcCategoryDao zcCategoryDao;

    @Autowired
    private ZcDeployDao zcDeployDao;

    @Autowired
    private ZcDeployItemDao zcDeployItemDao;


    @PostMapping("/zc/deployList")
    @ApiOperation(value = "获取盘点资产的列表数据", notes = "获取盘点资产的列表数据")
    public Map<String, Object> getDeployList(@RequestBody DeployZcListDTO deployZcListDTO) {
        List<DeployZcListVO> zcListVOList = new ArrayList<>();

        try {
            Map<String, Object> map = new HashMap();
            Map<String, Object> params = new HashMap();
            params.put("del", "0");
            params.put("keyword", deployZcListDTO.getKeyword());
            params.put("glDeptId", UserUtil.getLoginUser().getDeptid());
            Integer page = deployZcListDTO.getOffset();
            Integer limit = deployZcListDTO.getLimit();
            // 查询调配资产
            List<ZcInfoDto> list = zcDeployDao.appList(params, page * limit - limit, limit);
            if (!CollectionUtils.isEmpty(list)) {
                List<ZcCategory> zcCategoryList = zcCategoryDao.listAll();
                findZcCategorys(list, zcCategoryList);
                list.forEach(k -> {
                    DeployZcListVO deployZcListVO = new DeployZcListVO();
                    deployZcListVO.setId(k.getId().intValue());
                    deployZcListVO.setGlDeptName(k.getGlDeptName());
                    deployZcListVO.setSyDeptName(k.getSyDeptName());
                    deployZcListVO.setEpcid(k.getEpcid());
                    deployZcListVO.setStoreAddress(k.getStoreAddress());
                    deployZcListVO.setZcName(k.getZcName());
                    deployZcListVO.setZcCodenum(k.getZcCodenum());
                    deployZcListVO.setNetvalue(k.getNetvalue());
                    deployZcListVO.setGlDeptId(k.getGlDeptId().intValue());
                    deployZcListVO.setSyDeptId(k.getSyDeptId().intValue());
                    deployZcListVO.setOriginalValue(k.getOriginalValue());
                    zcListVOList.add(deployZcListVO);
                });
            }
            map.put("data", zcListVOList);
            map.put("code", "0");
            map.put("msg", "成功");
            return map;
        } catch (Exception e) {
            logger.info("获得的调配的资产列表失败{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }


    //找到资产分类的一级分类，二级分类
    private void findZcCategorys(List<ZcInfoDto> zcInfoDtoList, List<ZcCategory> zcCategoryList) {
        if (!CollectionUtils.isEmpty(zcCategoryList)) {
            for (ZcInfoDto zcInfoDto : zcInfoDtoList) {
                if (zcInfoDto.getZcCategoryPid() == null || new Long(0).equals(zcInfoDto.getZcCategoryPid())) {
                    zcInfoDto.setZcCategoryYiJi(zcInfoDto.getZcCategoryName());
                    continue;
                }
                List<ZcCategory> zcCategorys1 = findZcCategory(zcCategoryList, zcInfoDto.getZcCategoryPid());
                if (!CollectionUtils.isEmpty(zcCategorys1)) {
                    ZcCategory zcCategory1 = zcCategorys1.get(0);
                    if (zcCategory1.getPid() == null || new Long(0).equals(zcCategory1.getPid())) {
                        zcInfoDto.setZcCategoryYiJi(zcCategory1.getName());
                        continue;
                    }
                    zcInfoDto.setZcCategoryErJi(zcCategory1.getName());
                    List<ZcCategory> zcCategorys2 = findZcCategory(zcCategoryList, zcCategory1.getPid());
                    if (!CollectionUtils.isEmpty(zcCategorys2)) {
                        ZcCategory zcCategory2 = zcCategorys2.get(0);
                        if (zcCategory2.getPid() == null || new Long(0).equals(zcCategory2.getPid())) {
                            zcInfoDto.setZcCategoryYiJi(zcCategory2.getName());
                            continue;
                        }
                    }
                }
            }
        }
    }

    private List<ZcCategory> findZcCategory(List<ZcCategory> zcCategoryList, Long id) {
        List<ZcCategory> collect = new ArrayList<>();
        if (!CollectionUtils.isEmpty(zcCategoryList)) {
            collect = zcCategoryList.stream().filter(c -> c.getId().equals(id)).collect(Collectors.toList());
        }
        return collect;
    }

    @PostMapping("/zc/insertZcDeployData")
    @ApiOperation(value = "添加资产调配的数据信息", notes = "添加资产调配的数据信息")
    public Map<String, Object> insertZcDeployData(@RequestBody List<DeployZcListVO> listVOList) {
        if (CollectionUtils.isEmpty(listVOList)) {
            String msg = "数据列表为空";
            return FailMap.createFailMapMsg(msg);
        }
        ZcDeployDto zcDeployDto = new ZcDeployDto();
        List<ZcDeployItem> zcDeployItemList = new ArrayList<>();
        zcDeployDto.setType("1");
        Map<String, Object> map = new HashMap();
        try {
            for (DeployZcListVO deployZcListVO : listVOList) {
                ZcDeployItem zcDeployItem = new ZcDeployItem();
                zcDeployItem.setZcId((long) deployZcListVO.getId());
                zcDeployItem.setGlDeptId((long) (deployZcListVO.getGlDeptId()));
                zcDeployItem.setBackDeptId(new Long(deployZcListVO.getBackDeptId()));
                zcDeployItem.setSyDeptId((long) (deployZcListVO.getSyDeptId()));
                zcDeployItem.setDel(0);
                zcDeployItemList.add(zcDeployItem);
            }
            zcDeployDto.setZcDeployItems(zcDeployItemList);
            zcDeployService.save(zcDeployDto);
            map.put("data", null);
            map.put("code", 0);
            map.put("message", "成功");
            return map;
        } catch (Exception e) {
            logger.info("添加资产调配的数据信息{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }


    @PostMapping("/deployRecordList")
    @ApiOperation(value = "获取调配记录的列表", notes = "获取调配记录的列表")
    public Map getDeployRecordList(@RequestBody PageVO pageVO) {
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> map = new HashMap();
        try {
            params.put("applyUserId", UserUtil.getLoginUser().getId());
            Integer page = pageVO.getOffset();
            Integer limit = pageVO.getLimit();
            List<Map<String, Object>> list = zcDeployDao.listZcDeploy(params, page * limit - limit, limit);
            map.put("data", list);
            map.put("code", "0");
            map.put("msg", "操作成功");
            return map;
        } catch (Exception e) {
            logger.info("获取调配记录的列表{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }


    @PostMapping("/listByZcDeployId")
    @ApiOperation(value = "获取调配记录找到子记录数据",notes = "获取调配记录找到子记录数据")
    public Map listByZcDeployId(@RequestBody DeployZcItemRecordDTO deployZcItemRecordDTO) {
        Map<String,Object> map = new HashMap();
        try {
            String cw = "";
            SysUser sysUser = UserUtil.getLoginUser();
            if(null!=sysUser && "cwb".equals(sysUser.getC03())){
                cw =  sysUser.getC03()!=null ? sysUser.getC03() : null;
            }
            List<Map<String,Object>> list = new ArrayList<>();
            if ( null!=deployZcItemRecordDTO.getZcDeployId()) {
                list = zcDeployItemDao.listDetailByZcDeployId(deployZcItemRecordDTO.getZcDeployId(),cw);
            }
            map.put("data",list);
            map.put("code","0");
            map.put("msg","成功");
        } catch (Exception e) {
            logger.info("获取调配记录找到子记录数据{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }

    @PostMapping("/deployCheckMainInfo")
    @ApiOperation(value = "资产调配审核主信息",notes = "资产调配审核主信息")
    public Map deployMainInfo(@RequestBody ZcDeployCheckDto zcDeployCheckDto) {
        Map<String,Object> map = new HashMap();
        try {
            HashMap<String, Object> data = zcDeployDao.getZcDeployDetail(zcDeployCheckDto.getDeployId());
            map.put("data",data);
            map.put("code","0");
            map.put("message","成功");
        } catch (Exception e) {
            logger.info("资产调配审核主信息{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }

    @PostMapping("/deployCheckItemList")
    @ApiOperation(value = "资产调配审核列表信息",notes = "资产调配审核列表信息")
    public Map deployCheckItemList(@RequestBody ZcDeployCheckDto zcDeployCheckDto) {
        Map<String,Object> map = new HashMap();
        try {
            List<Map<String,Object>> list = zcDeployItemDao.listDetailByFlowTodoIdNew(zcDeployCheckDto.getTodoId());
            map.put("data",list);
            map.put("code","0");
            map.put("message","成功");
        } catch (Exception e) {
            logger.info("资产调配审核列表信息{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }

    @PostMapping("/deployCheck")
    @ApiOperation(value = "资产调配审核",notes = "资产调配审核")
    public Map deployCheck(@RequestBody ZcDeployCheckDto zcDeployCheckDto) {
        Map<String,Object> map = new HashMap();
        try {
            String status = zcDeployService.check(zcDeployCheckDto);
            HashMap<String, Object> data = new HashMap<>();
            data.put("status",status);
            map.put("data",data);
            map.put("code","0");
            map.put("message","成功");
        } catch (Exception e) {
            logger.info("资产调配审核{}", e.getMessage());
            return FailMap.createFailMap();
        }
        return map;
    }

}
