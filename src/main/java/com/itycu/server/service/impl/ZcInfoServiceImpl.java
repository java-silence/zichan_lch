package com.itycu.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.DictDao;
import com.itycu.server.dao.ZcCategoryDao;
import com.itycu.server.dao.ZcInfoDao;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.*;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.ZcChangeRecordService;
import com.itycu.server.service.ZcInfoService;
import com.itycu.server.utils.EcpIdUtil;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import com.itycu.server.utils.ZiChanCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ZcInfoServiceImpl implements ZcInfoService {
    private static final Logger log = LoggerFactory.getLogger("adminLogger");
    @Autowired
    private ZcInfoDao zcInfoDao;
    @Autowired
    private ZcCategoryDao zcCategoryDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private DictDao dictDao;
    @Autowired
    private ZcChangeRecordService zcChangeRecordService;

    @Override
    public ZcInfo save(ZcInfo zcInfo) {
        zcInfo.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
        zcInfo.setCreateBy(UserUtil.getLoginUser().getId());
        zcInfo.setDel(0);
        zcInfo.setBf("0");
        zcInfoDao.save(zcInfo);
        log.debug("新增资产档案{}", zcInfo.getCreateBy() + zcInfo.getZcName());
        return zcInfo;
    }

    @Override
    public ZcInfo update(ZcInfo zcInfo) {
        zcInfo.setUpdateBy(UserUtil.getLoginUser().getId());
        zcInfoDao.update(zcInfo);

        zcChangeRecordService.save(zcInfo); //保存变更记录
        log.debug("编辑资产档案{}", zcInfo.getUpdateBy() + zcInfo.getZcName());
        return zcInfo;
    }

    @Override
    public void delete(Long id) {
        ZcInfo zcInfo = new ZcInfo();
        zcInfo.setId(id);
        zcInfo.setUpdateBy(UserUtil.getLoginUser().getId());
        zcInfo.setDel(1);
        zcInfoDao.update(zcInfo);
        log.debug("删除资产档案{}", zcInfo.getUpdateBy() + zcInfo.getZcName());
    }

    @Override
    public void export(PageTableRequest request, HttpServletResponse response) {
        request.getParams().put("del","0");
        request.getParams().put("syDeptId",UserUtil.getLoginUser().getDeptid());
        List<ZcInfoDto> zcInfoList = zcInfoDao.listByCondition(request.getParams());
        String fileName = "资产档案";
        if ("90".equals(request.getParams().get("daoqi"))) fileName = "到期资产";
        if ("90".equals(request.getParams().get("linbao"))) fileName = "临保资产";
//        if (!CollectionUtils.isEmpty(equipmentList)) {
        String[] headers = new String[]{"资产追溯码","卡片编号","资产编码","资产名称","资产分类","规格","型号","单位","使用状态","管理部门","使用部门","使用人","存放地点","维护周期/天"
                ,"资产来源","原价值","累计折旧","本年折旧","净值","减值准备","净额","净残值率","净残值","开始使用日期","使用月限","已计提期数"
                ,"剩余期限","服务商名称","联系人","联系方式","服务商地址","保修期限","创建人","创建时间","备注"};

        List<Object[]> datas = new ArrayList<>(zcInfoList.size());
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ZcInfoDto zcInfo : zcInfoList) {
            String StartUseTime = "";
            String createTime = "";
            if(zcInfo.getStartUseTime() != null){
                StartUseTime = s.format(zcInfo.getStartUseTime());
            }
            if(zcInfo.getCreateTime() != null){
                createTime = s1.format(zcInfo.getCreateTime());
            }
            Object[] objects = new Object[]{zcInfo.getEpcid(),zcInfo.getCardNum(),zcInfo.getZcCodenum(),zcInfo.getZcName(),zcInfo.getZcCategoryName(),zcInfo.getSpecification(),zcInfo.getModel()
                    ,zcInfo.getUnit(),zcInfo.getUseStatusName(),zcInfo.getGlDeptName(),zcInfo.getSyDeptName(),zcInfo.getSyName(),zcInfo.getStoreAddress()
                    ,zcInfo.getMaintainCycle(),zcInfo.getZcFrom(),zcInfo.getOriginalValue(),zcInfo.getLjZhejiu(),zcInfo.getBnZhejiu(),zcInfo.getNetvalue()
                    ,zcInfo.getJzzb(),zcInfo.getNet(),zcInfo.getNetResidualRate(),zcInfo.getNetResidualValue(),StartUseTime,zcInfo.getUseMonths()
                    ,zcInfo.getHaveCount(),zcInfo.getRemainingperiod(),zcInfo.getCname(),zcInfo.getVenperson()
                    ,zcInfo.getVenphone(),zcInfo.getVenaddress(),zcInfo.getWarrantyperiod(),zcInfo.getCreator(),createTime,zcInfo.getBz()
            };
            datas.add(objects);
        }

        ExcelUtil.excelExport(
                fileName, headers,
                datas, response);
//        }
        log.debug("资产档案导出{}", UserUtil.getLoginUser().getId());
    }

    @Override
    public Map Import(MultipartFile file) throws IOException {
        Map map = new HashMap();
        ImportParams params = new ImportParams();
        List<ZcInfoDto> list = new ArrayList<>();
        List<ZcInfo> insertlist = new ArrayList<>();
        FileInfo fileInfo = null;
        try {
            params.setTitleRows(5);
            params.setHeadRows(0);
//            params.setNeedVerfiy(true);
            list = ExcelImportUtil.importExcel(file.getInputStream(), ZcInfoDto.class, params);
            if(! CollectionUtils.isEmpty(list)){
                List<ZcCategory> zcCategoryList = zcCategoryDao.listAll();
                List<Dept> deptList = deptDao.listDepts();
//                List<Dict> useStatusList = dictDao.listByType("useStatus");
                LoginUser loginUser = UserUtil.getLoginUser();
                for (int i=0;i<list.size();i++){
                    ZcInfoDto zcInfo = list.get(i);
                    if ("合计".equals(zcInfo.getCardNum().trim()))  continue;  //卡片编号值如果是合计就跳过
                    //判断必需的数据是否为空
                    if (zcInfo.getZcName() == null || "".equals(zcInfo.getZcName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产名称不能为空");
                        return map;
                    }
                    if (zcInfo.getZcCategoryName() == null || "".equals(zcInfo.getZcCategoryName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产分类不能为空");
                        return map;
                    }
                    List<ZcCategory> zcCategories = findZcCategory(zcCategoryList,zcInfo.getZcCategoryName());  //根据资产分类名称查找资产分类id
                    if (CollectionUtils.isEmpty(zcCategories)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产分类不存在");
                        return map;
                    }
                    ZcCategory zcCategory = zcCategories.get(0);
                    zcInfo.setZcCategoryId(zcCategory.getId());

                    if (zcInfo.getGlDeptName() == null || "".equals(zcInfo.getGlDeptName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行管理部门不能为空");
                        return map;
                    }
                    List<Dept> glDepts = findDept(deptList,zcInfo.getGlDeptName());  //根据管理部门名称查找管理部门id
                    if (CollectionUtils.isEmpty(glDepts)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行管理部门不存在");
                        return map;
                    }
                    Dept glDept = glDepts.get(0);
                    zcInfo.setGlDeptId(glDept.getId());

                    if (zcInfo.getSyDeptName() == null || "".equals(zcInfo.getSyDeptName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行使用部门不能为空");
                        return map;
                    }
                    List<Dept> syDepts = findDept(deptList,zcInfo.getSyDeptName());  //根据使用部门名称查找使用部门id
                    if (CollectionUtils.isEmpty(syDepts)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行使用部门不存在");
                        return map;
                    }
                    Dept syDept = syDepts.get(0);
                    zcInfo.setSyDeptId(syDept.getId());

//                    if (zcInfo.getUseStatusName() == null || "".equals(zcInfo.getUseStatusName())){
//                        map.put("code","1");
//                        map.put("msg","第"+(i+2)+"行使用状态不能为空");
//                        return map;
//                    }
//                    List<Dict> useStatuss = findDict(useStatusList,zcInfo.getUseStatusName());  //根据使用部门名称查找使用部门id
//                    if (CollectionUtils.isEmpty(useStatuss)) {
//                        map.put("code","1");
//                        map.put("msg","第"+(i+2)+"行使用状态不存在");
//                        return map;
//                    }
//                    Dict useStatus = useStatuss.get(0);
//                    zcInfo.setUseStatus(Integer.valueOf(useStatus.getK()));

                    zcInfo.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
                    zcInfo.setCreateBy(loginUser.getId());
                    zcInfo.setDel(0);
                    zcInfo.setBf("0");
                    zcInfo.setAccountentryStatus(1);
                    zcInfo.setCardStatus(1);
                    ZcInfo insertZcInfo = (ZcInfo)zcInfo;
                    insertlist.add(insertZcInfo);

                    Integer quantity = zcInfo.getQuantity();   //资产数量
                    if (quantity > 1){     //数量大于1，复制导入资产
                         for (int a=0;a<quantity-1;a++){
                             ZcInfo zcInfo1 = new ZcInfo();
                             BeanUtils.copyProperties(insertZcInfo,zcInfo1);
                             zcInfo1.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
                             insertlist.add(zcInfo1);
                         }
                    }
                }

                zcInfoDao.saves(insertlist);
                map.put("code","0");
//                fileInfo = fileService.save(file);
            }
        } catch (Exception e) {
            map.put("code","-1");
            map.put("msg",e.getMessage());
            e.printStackTrace();
        } finally {
            log.debug("资产档案导入{}", UserUtil.getLoginUser().getId());
            return map;
        }
    }

    @Override
    public Map gudingImport(MultipartFile file) throws IOException {
        Map map = new HashMap();
        ImportParams params = new ImportParams();
        List<GudingZcInfo> list = new ArrayList<>();
        List<ZcInfo> insertlist = new ArrayList<>();
        FileInfo fileInfo = null;
        // 统计该县的资产数
        LoginUser loginUser = UserUtil.getLoginUser();
        long deptid = loginUser.getDeptid();
        Dept dept = deptDao.getById(deptid);
        Dept pDept = deptDao.getById(dept.getPid());
        int count = zcInfoDao.countByDeptcode(pDept.getDeptcode());
        try {
            params.setHeadRows(0);
            params.setTitleRows(2);
            //params.setStartSheetIndex(1);
            //params.setNeedVerfiy(true);
            list = ExcelImportUtil.importExcel(file.getInputStream(), GudingZcInfo.class, params);
            if(! CollectionUtils.isEmpty(list)){
                List<ZcCategory> zcCategoryList = zcCategoryDao.listAll();
                List<Dept> deptList = deptDao.listDepts();
                //List<Dict> useStatusList = dictDao.listByType("useStatus");
                for (int i=0;i<list.size();i++){
                    GudingZcInfo zcInfo = list.get(i);
                    if ("合计".equals(zcInfo.getCardNum().trim()))  continue;  //卡片编号值如果是合计就跳过
                    //判断必需的数据是否为空
//                    if (zcInfo.getEpcid() == null || "".equals(zcInfo.getEpcid())){
//                        map.put("code","1");
//                        map.put("msg","第"+(i+6)+"行资产追溯码不能为空");
//                        return map;
//                    }
                    if (zcInfo.getZcName() == null || "".equals(zcInfo.getZcName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产名称不能为空");
                        return map;
                    }
                    if (zcInfo.getZcCategoryCode() == null || "".equals(zcInfo.getZcCategoryCode())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产分类不能为空");
                        return map;
                    }
                    List<ZcCategory> zcCategories = findZcCategory(zcCategoryList,zcInfo.getZcCategoryCode());  //根据资产分类名称查找资产分类id
                    if (CollectionUtils.isEmpty(zcCategories)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产分类不存在");
                        return map;
                    }
                    ZcCategory zcCategory = zcCategories.get(0);
                    zcInfo.setZcCategoryId(zcCategory.getId());

                    if (zcInfo.getGlDeptName() == null || "".equals(zcInfo.getGlDeptName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行管理部门不能为空");
                        return map;
                    }
                    List<Dept> glDepts = findDept(deptList,zcInfo.getGlDeptName());  //根据管理部门名称查找管理部门id
                    if (CollectionUtils.isEmpty(glDepts)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行管理部门不存在");
                        return map;
                    }
                    Dept glDept = glDepts.get(0);
                    zcInfo.setGlDeptId(glDept.getId());

                    if (zcInfo.getSyDeptName() == null || "".equals(zcInfo.getSyDeptName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行使用部门不能为空");
                        return map;
                    }
                    List<Dept> syDepts = findDept(deptList,zcInfo.getSyDeptName());  //根据使用部门名称查找使用部门id
                    if (CollectionUtils.isEmpty(syDepts)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行使用部门不存在");
                        return map;
                    }
                    Dept syDept = syDepts.get(0);
                    zcInfo.setSyDeptId(syDept.getId());

                    zcInfo.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
                    zcInfo.setCreateBy(loginUser.getId());
                    zcInfo.setDel(0);
                    zcInfo.setBf("0");
                    zcInfo.setAccountentryStatus(1);
                    zcInfo.setCardStatus(1);
                    zcInfo.setUseStatus(1);
                    ZcInfo zc = new ZcInfo();
                    BeanUtils.copyProperties(zcInfo,zc);
                    insertlist.add(zc);
                }

                // 插入资产追溯码
                for (int i = 0; i < insertlist.size(); i++) {
                    String ecpId = EcpIdUtil.getStaticNum(count + (i + 1), 5);
                    insertlist.get(i).setEpcid("000"+ecpId);
                }
                zcInfoDao.saves(insertlist);
                map.put("code","0");
//                fileInfo = fileService.save(file);
            }
        } catch (Exception e) {
            map.put("code","-1");
            map.put("msg",e.getMessage());
            e.printStackTrace();
        } finally {
            log.debug("固定资产档案导入{}", UserUtil.getLoginUser().getId());
            return map;
        }
    }

    @Override
    public Map dizhiImport(MultipartFile file) throws IOException {
        Map map = new HashMap();
        ImportParams params = new ImportParams();
        List<DizhiZcInfo> list = new ArrayList<>();
        List<ZcInfo> insertlist = new ArrayList<>();
        FileInfo fileInfo = null;
        // 统计该县的资产数
        LoginUser loginUser = UserUtil.getLoginUser();
        long deptid = loginUser.getDeptid();
        Dept dept = deptDao.getById(deptid);
        Dept pDept = deptDao.getById(dept.getPid());
        int count = zcInfoDao.countByDeptcode(pDept.getDeptcode());
        try {
            params.setHeadRows(0);
            params.setTitleRows(2);
//            params.setNeedVerfiy(true);
            list = ExcelImportUtil.importExcel(file.getInputStream(), DizhiZcInfo.class, params);
            if(! CollectionUtils.isEmpty(list)){
                List<ZcCategory> zcCategoryList = zcCategoryDao.listAll();
                List<Dept> deptList = deptDao.listDepts();
//                List<Dict> useStatusList = dictDao.listByType("useStatus");
                for (int i=0;i<list.size();i++){
                    DizhiZcInfo zcInfo = list.get(i);
                    if ("合计".equals(zcInfo.getCardNum().trim()))  continue;  //资产编号值如果是合计就跳过
                    //判断必需的数据是否为空
//                    if (zcInfo.getEpcid() == null || "".equals(zcInfo.getEpcid())){
//                        map.put("code","1");
//                        map.put("msg","第"+(i+6)+"行资产追溯码不能为空");
//                        return map;
//                    }
                    if (zcInfo.getZcName() == null || "".equals(zcInfo.getZcName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产名称不能为空");
                        return map;
                    }
                    if (zcInfo.getZcCategoryCode() == null || "".equals(zcInfo.getZcCategoryCode())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产分类不能为空");
                        return map;
                    }
                    List<ZcCategory> zcCategories = findZcCategory(zcCategoryList,zcInfo.getZcCategoryCode());  //根据资产分类名称查找资产分类id
                    if (CollectionUtils.isEmpty(zcCategories)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行资产分类不存在");
                        return map;
                    }
                    ZcCategory zcCategory = zcCategories.get(0);
                    zcInfo.setZcCategoryId(zcCategory.getId());

                    if (zcInfo.getGlDeptName() == null || "".equals(zcInfo.getGlDeptName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行管理部门不能为空");
                        return map;
                    }
                    List<Dept> glDepts = findDeptgl(deptList,zcInfo.getGlDeptName());  //根据管理部门名称查找管理部门id
                    if (CollectionUtils.isEmpty(glDepts)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行管理部门不存在");
                        return map;
                    }
                    Dept glDept = glDepts.get(0);
                    zcInfo.setGlDeptId(glDept.getId());

                    if (zcInfo.getSyDeptName() == null || "".equals(zcInfo.getSyDeptName())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行使用部门不能为空");
                        return map;
                    }
                    List<Dept> syDepts = findDept(deptList,zcInfo.getSyDeptName());  //根据使用部门名称查找使用部门id
                    if (CollectionUtils.isEmpty(syDepts)) {
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行使用部门不存在");
                        return map;
                    }
                    // 处理数量
                    Dept syDept = syDepts.get(0);
                    Integer quantity1 = zcInfo.getQuantity();
                    if (zcInfo.getQuantity() > 1) {
                        BigDecimal originalValue = zcInfo.getOriginalValue();
                        BigDecimal netvalue = zcInfo.getNetvalue();
                        BigDecimal net = zcInfo.getNet();
                        BigDecimal subOriginalValue = originalValue.divide(new BigDecimal(quantity1), 2, RoundingMode.HALF_UP);
                        BigDecimal subNetvalue = netvalue.divide(new BigDecimal(quantity1), 2, RoundingMode.HALF_UP);
                        BigDecimal subNet = net.divide(new BigDecimal(quantity1), 2, RoundingMode.HALF_UP);
                        zcInfo.setOriginalValue(subOriginalValue);
                        zcInfo.setNetvalue(subNetvalue);
                        zcInfo.setNet(subNet);
                        for (int j = 0; j < quantity1 - 1; j++) {
                            ZcInfo info = new ZcInfo();
                            zcInfo.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
                            zcInfo.setSyDeptId(syDept.getId());
                            zcInfo.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
                            zcInfo.setCreateBy(loginUser.getId());
                            zcInfo.setDel(0);
                            zcInfo.setBf("0");
                            zcInfo.setAccountentryStatus(1);
                            zcInfo.setCardStatus(1);
                            zcInfo.setUseStatus(1);
                            BeanUtils.copyProperties(zcInfo,info);
                            insertlist.add(info);
                        }
                    }
                    zcInfo.setSyDeptId(syDept.getId());
                    zcInfo.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
                    zcInfo.setCreateBy(loginUser.getId());
                    zcInfo.setDel(0);
                    zcInfo.setBf("0");
                    zcInfo.setAccountentryStatus(1);
                    zcInfo.setCardStatus(1);
                    zcInfo.setUseStatus(1);
                    ZcInfo zc = new ZcInfo();
                    BeanUtils.copyProperties(zcInfo,zc);
                    insertlist.add(zc);
                    if (zcInfo.getQuantity() == null || "".equals(zcInfo.getQuantity())){
                        map.put("code","1");
                        map.put("msg","第"+(i+6)+"行数量不能为空");
                        return map;
                    }
//                    Integer quantity = zcInfo.getQuantity();   //资产数量
//                    if (quantity > 1){     //数量大于1，复制导入资产
//                        for (int a=0;a<quantity-1;a++){
//                            ZcInfo zcInfo1 = new ZcInfo();
//                            BeanUtils.copyProperties(zc,zcInfo1);
//                            zcInfo1.setSelfCodenum(ZiChanCodeUtil.getZiChanCode());
//                            insertlist.add(zcInfo1);
//                        }
//                    }
                }
                // 插入资产追溯码
                for (int i = 0; i < insertlist.size(); i++) {
                    String ecpId = EcpIdUtil.getStaticNum(count + (i + 1), 5);
                    insertlist.get(i).setEpcid("000"+ecpId);
                }
                zcInfoDao.saves(insertlist);
                map.put("code","0");
                //fileInfo = fileService.save(file);
            }
        } catch (Exception e) {
            map.put("code","-1");
            map.put("msg",e.getMessage());
            e.printStackTrace();
        } finally {
            log.debug("低值易耗品资产档案导入{}", UserUtil.getLoginUser().getId());
            return map;
        }
    }

    List<ZcCategory> findZcCategory(List<ZcCategory> zcCategoryList, String zcCategoryCode){
        List<ZcCategory> collect = new ArrayList<>();
        if (!CollectionUtils.isEmpty(zcCategoryList))
            collect = zcCategoryList.stream().filter(zc -> zcCategoryCode.equals(zc.getCode())).collect(Collectors.toList());
        return collect;
    }

    List<Dept> findDept(List<Dept> deptList,String deptname){
        List<Dept> collect = new ArrayList<>();
        // String newDeptname = deptname.replace("垣曲农商行","");    //暂用
        if (!CollectionUtils.isEmpty(deptList))
            collect = deptList.stream().filter(d -> deptname.equals(d.getAllName())).collect(Collectors.toList());
        return collect;
    }

    List<Dept> findDeptgl(List<Dept> deptList,String deptname){
        List<Dept> collect = new ArrayList<>();
        // String newDeptname = deptname.replace("垣曲农商银行","");    //暂用
        if (!CollectionUtils.isEmpty(deptList))
            collect = deptList.stream().filter(d -> deptname.equals(d.getOtherAllName())).collect(Collectors.toList());
        return collect;
    }

    List<Dict> findDict(List<Dict> dictList,String val){
        List<Dict> collect = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dictList))
            collect = dictList.stream().filter(d -> val.equals(d.getVal())).collect(Collectors.toList());
        return collect;
    }

}