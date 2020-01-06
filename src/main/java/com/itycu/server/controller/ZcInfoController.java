package com.itycu.server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dao.ZcCategoryDao;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.ZcCategory;
import com.itycu.server.model.ZcInfo;
import com.itycu.server.service.ZcInfoService;
import com.itycu.server.utils.DynamicConditionUtil;
import com.itycu.server.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.dao.ZcInfoDao;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/zcInfos")
public class ZcInfoController {

    @Autowired
    private ZcInfoDao zcInfoDao;

    @Autowired
    private ZcInfoService zcInfoService;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private ZcCategoryDao zcCategoryDao;

    @LogAnnotation
    @PostMapping
    @ApiOperation(value = "保存资产档案")
    @PreAuthorize("hasAuthority('sys:zcInfo:add')")
    public ZcInfo save(@RequestBody ZcInfo zcInfo) {
        return zcInfoService.save(zcInfo);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public ZcInfoDto get(@PathVariable Long id) {
        return zcInfoDao.getById(id);
    }

    @LogAnnotation
    @PutMapping
    @ApiOperation(value = "修改资产档案")
    @PreAuthorize("hasAuthority('sys:zcInfo:edit')")
    @Transactional
    public ZcInfo update(@RequestBody ZcInfo zcInfo) {
        return zcInfoService.update(zcInfo);
    }

    @GetMapping("/layuiList")
    @ApiOperation(value = "列表")
//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
    public Map list2(PageTableRequest request, HttpServletRequest httpServletRequest) {
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcInfo:querysydept") > 0){
			request.getParams().put("syDeptId", UserUtil.getLoginUser().getDeptid());
		}
        if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:zcInfo:querygldept") > 0){
			request.getParams().put("glDeptId", UserUtil.getLoginUser().getDeptid());
		}
        Map map = new HashMap();
        request.getParams().put("del","0");
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
        int count = zcInfoDao.count(request.getParams());
        List list = zcInfoDao.list(request.getParams(), page*limit-limit, limit);

        if (!CollectionUtils.isEmpty(list)){
            List<ZcCategory> zcCategoryList = zcCategoryDao.listAll();
            findZcCategorys(list,zcCategoryList);
        }
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    //找到资产分类的一级分类，二级分类
    void findZcCategorys(List<ZcInfoDto> zcInfoDtoList, List<ZcCategory> zcCategoryList){
        if (!CollectionUtils.isEmpty(zcCategoryList)){
             for (ZcInfoDto zcInfoDto : zcInfoDtoList){
                  if (zcInfoDto.getZcCategoryPid() == null || new Long(0).equals(zcInfoDto.getZcCategoryPid())){
                      zcInfoDto.setZcCategoryYiJi(zcInfoDto.getZcCategoryName());
                      continue;
                  }
                  List<ZcCategory> zcCategorys1 = findZcCategory(zcCategoryList,zcInfoDto.getZcCategoryPid());
                  if (!CollectionUtils.isEmpty(zcCategorys1)){
                        ZcCategory zcCategory1 = zcCategorys1.get(0);
                        if (zcCategory1.getPid() == null || new Long(0).equals(zcCategory1.getPid())){
                            zcInfoDto.setZcCategoryYiJi(zcCategory1.getName());
                            continue;
                        }
                        zcInfoDto.setZcCategoryErJi(zcCategory1.getName());
                        List<ZcCategory> zcCategorys2 = findZcCategory(zcCategoryList,zcCategory1.getPid());
                        if (!CollectionUtils.isEmpty(zcCategorys2)){
                            ZcCategory zcCategory2 = zcCategorys2.get(0);
                            if (zcCategory2.getPid() == null || new Long(0).equals(zcCategory2.getPid())){
                                zcInfoDto.setZcCategoryYiJi(zcCategory2.getName());
                                continue;
                            }
                        }
                  }
             }
        }
    }
    List<ZcCategory> findZcCategory(List<ZcCategory> zcCategoryList, Long id){
        List<ZcCategory> collect = new ArrayList<>();
        if (!CollectionUtils.isEmpty(zcCategoryList)){
            collect = zcCategoryList.stream().filter(c -> c.getId().equals(id)).collect(Collectors.toList());
        }
        return collect;
    }

    /**
     * 针对报废资产提交,查询本部门
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/bflayuiList")
    @ApiOperation(value = "列表")
    public Map bflayuiList(PageTableRequest request, HttpServletRequest httpServletRequest) {
        request.getParams().put("syDeptId", UserUtil.getLoginUser().getDeptid());
        Map map = new HashMap();
        request.getParams().put("del","0");
        Integer page = Integer.valueOf((String)request.getParams().get("offset"));
        Integer limit = Integer.valueOf((String)request.getParams().get("limit"));
        DynamicConditionUtil.dynamicCondition(request,httpServletRequest);
        int count = zcInfoDao.count(request.getParams());
        List list = zcInfoDao.list(request.getParams(), page*limit-limit, limit);
        map.put("data",list);
        map.put("count",count);
        map.put("code","0");
        map.put("msg","");
        return map;
    }

    @LogAnnotation
    @GetMapping("/epcidcount/{epcid}")
    @ApiOperation(value = "查询重复数据")
    public int count(@PathVariable String epcid) {
        //UserUtil.getLoginUser().getDeptid();
        Map map = new HashMap();
        map.put("epcid",epcid);
        return  zcInfoDao.count(map);
    }


    @LogAnnotation
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除资产档案")
    @PreAuthorize("hasAuthority('sys:zcInfo:del')")
    public void delete(@PathVariable Long id) {
        zcInfoService.delete(id);
    }

    @GetMapping("/listall")
    @ApiOperation(value = "列出所有数据")
    public List<ZcInfo> listAll() {
        List<ZcInfo> list = zcInfoDao.listAll();
        return list;
    }

    @LogAnnotation
    @PostMapping("/export")
    @ApiOperation(value = "导出资产档案")
    @PreAuthorize("hasAuthority('sys:zcInfo:export')")
    public void export(PageTableRequest request, HttpServletResponse response) {
        zcInfoService.export(request, response);
    }

    @LogAnnotation
    @PostMapping("/gudingImport")
    @ApiOperation(value = "固定资产导入档案")
    @PreAuthorize("hasAuthority('sys:zcInfo:import')")
    @Transactional
    public Map gudingImport(MultipartFile file) throws IOException {
        return zcInfoService.gudingImport(file);
    }

    @LogAnnotation
    @PostMapping("/dizhiImport")
    @ApiOperation(value = "低值易耗品资产导入档案")
    @PreAuthorize("hasAuthority('sys:zcInfo:import')")
    @Transactional
    public Map dizhiImport(MultipartFile file) throws IOException {
        return zcInfoService.dizhiImport(file);
    }
}