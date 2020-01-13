package com.itycu.server.app.controller;


import com.google.common.collect.Lists;
import com.itycu.server.app.dto.diaopei.DeployZcListDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.app.vo.diaopei.DeployZcListVO;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dao.ZcCategoryDao;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.Dept;
import com.itycu.server.model.SysUser;
import com.itycu.server.model.ZcCategory;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.ZcInfoService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private ZcInfoService zcInfoService;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private ZcCategoryDao zcCategoryDao;


    @Autowired
    private DeptDao deptDao;


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
            List<ZcInfoDto> list = zcInfoDao.list(params, page * limit - limit, limit);
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


    @PostMapping("/dept/subDeptList")
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
            logger.info("获取部门树列表失败{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }


    @PostMapping("/zc/insertZcDeployData")
    @ApiOperation(value = "添加资产调配的数据信息", notes = "添加资产调配的数据信息")
    public Map<String, Object> insertZcDeployData() {
        /**
         * TODO 待完成
         */
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
            logger.info("获取部门树列表失败{}", e.getMessage());
            return FailMap.createFailMap();
        }
    }

}
