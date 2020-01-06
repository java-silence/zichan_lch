package com.itycu.server.controller;


import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.IndexDao;
import com.itycu.server.model.*;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "首页统计数据展示")
@RestController
@RequestMapping("/index")
public class IndexController {


    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexDao indexDao;

    @Autowired
    private DeptDao deptDao;


    @RequestMapping(value = "/queryJtIndexAssetCategory")
    @ApiOperation(value = "集团首页资产分类统计")
    public List<IndexAssetCategoryVO> queryIndexAssetCategory() {
        List<IndexAssetCategoryVO> list = indexDao.queryJtIndexAssetCategory();
        return list;
    }

    @RequestMapping(value = "/queryJtIndexJinE")
    @ApiOperation(value = "集团首页资产总值和净值统计")
    public Map queryJtIndexJinE() {
        Map<String, Object> list = indexDao.queryJtIndexJinE();
        return list;
    }


    @RequestMapping(value = "/queryIndexAssetCategory/{deptId}")
    @ApiOperation(value = "首页资产分类统计")
    public List<IndexAssetCategoryVO> queryIndexAssetCategory(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("首页资产分类统计数据id为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        List<IndexAssetCategoryVO> list = indexDao.queryIndexAssetCategory(deptId);
        return list;
    }


    @RequestMapping(value = "/queryZhihangZcDataInfo/{deptId}")
    @ApiOperation(value = "统计支行各个部门下面的资产数据")
    public List<IndexBranchCountVO> queryZhihangZcDataInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("首页支行下面的所有分行的资产数据id为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        //总资产值
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        IndexBranchCountVO indexBranchCountVO = new IndexBranchCountVO();
        List<IndexBranchDataCountVO> branchData = indexDao.queryZhihangZcDataInfo(deptId);
        if (!CollectionUtils.isEmpty(branchData)) {
            int zcTotal = 0;
            for (int i = 0; i < branchData.size(); i++) {
                zcTotal += branchData.get(i).getZcCount();
                //综合办公室资产统计
                if (("zhb".equals(branchData.get(i).getDeptType()))) {
                    //资产总值
                    indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                    //综合办资产数量
                    indexBranchCountVO.setZhbZcNumber(branchData.get(i).getZcCount());
                    //本年处置数量
                    indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                } else if (("kjb".equals(branchData.get(i).getDeptType()))) {
                    //科技部资产统计

                    //科技部资产总数
                    indexBranchCountVO.setKjbZcNumber(branchData.get(i).getZcCount());
                    //本年处置数量
                    indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                    //资产总值
                    indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                } else if (("yyb".equals(branchData.get(i).getDeptType()))) {
                    //运营部资产统计

                    //运营部资产数量
                    indexBranchCountVO.setYybZcNumber(branchData.get(i).getZcCount());
                    //本年处置数量
                    indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                    //资产总值
                    indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                } else if ("bwb".equals(branchData.get(i).getDeptType())) {
                    //保卫部资产统计

                    //保卫部资产数量
                    indexBranchCountVO.setBwbZcNumber(branchData.get(i).getZcCount());
                    //本年处置数量
                    indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                    //资产总值
                    indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                }
                total = branchData.get(i).getZcTotal().add(total);
            }
            //资产总数
            indexBranchCountVO.setZcNumber(zcTotal);
        }
        List<IndexBranchCountVO> resultList = new ArrayList<>();
        //查询本年的保修数量
        int repairCountThisYear = indexDao.queryRepairCountThisYear(deptId);
        //本年的报修数量
        indexBranchCountVO.setRepairCount(repairCountThisYear);
        //资产总值
        indexBranchCountVO.setZcTotalValue(total);
        resultList.add(indexBranchCountVO);
        return resultList;
    }


    @RequestMapping(value = "/queryIndexZoneAssetCount/{deptId}")
    @ApiOperation(value = "首页统计地区资数量总数")
    public List<?> queryIndexZoneAssetCount(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("首页统计地区资数量总数的部门id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        //部门编码是01表示是运城
        if ("01".equals(deptId)) {
            List<IndexZoneAssetCountVO> resultList = new ArrayList<>();
            List<Dept> list = deptDao.queryAllDept();
            Map<String, Object> map = new HashMap<>();
            if (!CollectionUtils.isEmpty(list)) {
                list.forEach(k -> {
                    String deptname = k.getDeptname().substring(0, k.getDeptname().length() - 3);
                    String deptcode = k.getDeptcode();
                    int result = indexDao.queryIndexZoneAssetCount(deptcode);
                    IndexZoneAssetCountVO indexZoneAssetCountVO = new IndexZoneAssetCountVO();
                    indexZoneAssetCountVO.setZoneName(deptname);
                    indexZoneAssetCountVO.setDataCount(result);
                    resultList.add(indexZoneAssetCountVO);
                });
                return resultList;
            } else {
                return null;
            }
        } else {
            //总资产值
            BigDecimal total = new BigDecimal(BigInteger.ZERO);
            IndexBranchCountVO indexBranchCountVO = new IndexBranchCountVO();
            List<IndexBranchDataCountVO> branchData = indexDao.queryBranchDataList(deptId);
            if (!CollectionUtils.isEmpty(branchData)) {
                int zcTotal = 0;
                int dealCount = 0;
                for (int i = 0; i < branchData.size(); i++) {
                    zcTotal += branchData.get(i).getZcCount();
                    //综合办公室资产统计
                    if (("zhb".equals(branchData.get(i).getDeptType()))) {
                        //资产总值
                        indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                        //综合办资产数量
                        indexBranchCountVO.setZhbZcNumber(branchData.get(i).getZcCount());
                        //本年处置数量
                        indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                    } else if (("kjb".equals(branchData.get(i).getDeptType()))) {
                        //科技部资产统计

                        //科技部资产总数
                        indexBranchCountVO.setKjbZcNumber(branchData.get(i).getZcCount());
                        //本年处置数量
                        indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                        //资产总值
                        indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                    } else if (("yyb".equals(branchData.get(i).getDeptType()))) {
                        //运营部资产统计

                        //运营部资产数量
                        indexBranchCountVO.setYybZcNumber(branchData.get(i).getZcCount());
                        //本年处置数量
                        indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                        //资产总值
                        indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                    } else if ("bwb".equals(branchData.get(i).getDeptType())) {
                        //保卫部资产统计

                        //保卫部资产数量
                        indexBranchCountVO.setBwbZcNumber(branchData.get(i).getZcCount());
                        //本年处置数量
                        indexBranchCountVO.setDealCount(branchData.get(i).getDealCount());
                        //资产总值
                        indexBranchCountVO.setZcTotalValue(branchData.get(i).getZcTotal());
                    }
                    total = branchData.get(i).getZcTotal().add(total);
                    dealCount += branchData.get(i).getDealCount();
                }
                //资产总数
                indexBranchCountVO.setZcNumber(zcTotal);
                indexBranchCountVO.setDealCount(dealCount);
            }

            List<IndexBranchCountVO> list = new ArrayList<>();
            //查询本年的保修数量
            int repairCountThisYear = indexDao.queryRepairCountThisYear(deptId);
            //本年的报修数量
            indexBranchCountVO.setRepairCount(repairCountThisYear);
            //资产总值
            indexBranchCountVO.setZcTotalValue(total);
            list.add(indexBranchCountVO);
            return list;
        }
    }

    @RequestMapping(value = "/queryIndexZhihangCount/{deptId}")
    @ApiOperation(value = "支行概况统计")
    public Map queryIndexZhihangCount(@PathVariable("deptId") String deptId) {
        Map map = indexDao.queryIndexZhihangCount(deptId);
        return map;
    }

    @RequestMapping(value = "/queryBranchAllSubBranchAssert/{deptId}")
    @ApiOperation(value = "首页支行下面的所有分行的资产数据")
    public List<IndexFenhangCountVO> queryBranchAllSubBranchAssert(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("首页支行下面的所有分行的资产数据id为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        List<IndexFenhangCountVO> list = indexDao.queryBranchAllSubBranchAssert(deptId);
//        if (!CollectionUtils.isEmpty(list)) {
//            list.forEach(k -> {
//                //截取行 不然前端显示不下
//                //垣曲农商行长直支行      最后显示 长直
//                //垣曲农商行东峰山分理处  最后显示 东峰山
//                String name = k.getName();
//                if (name.contains("行")) {
//                    int begin = name.indexOf("行");
//                    name = name.substring(begin + 1, name.length());
//                    if (name.contains("支行")) {
//                        name = name.substring(0, name.length() - 2);
//                    } else if (name.contains("分理处")) {
//                        name = name.substring(0, name.length() - 3);
//                    }
//                    k.setName(name);
//                }
//            });
//        }
        return list;
    }


    @RequestMapping(value = "/queryBenYueBuyDataInfo/{deptId}")
    @ApiOperation(value = "展示首页上面本月的采购统计数据")
    public Map<String, Object> queryBenYueBuyDataInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("展示首页上面本月的采购统计数据id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        List<IndexBenYueBuyCountVO> list = new ArrayList<>();
        List<IndexBenYueBuyCountVO> resultList = new ArrayList<>();
        logger.info("统计本月的采购采购数据,部门id==》》" + deptId);
        list = indexDao.queryBenYueBuyDataInfo(deptId);
//        if (!CollectionUtils.isEmpty(list)) {
//            list.forEach(k -> {
//                String dataStr = k.getBuyStatus();
//                if ("0".equals(String.valueOf(dataStr))) {
//                    k.setStatusText("待提交");
//                } else if ("1".equals(String.valueOf(dataStr))) {
//                    k.setStatusText("审核中");
//                } else if ("2".equals(String.valueOf(dataStr))) {
//                    k.setStatusText("审核完成");
//                }
//
//                if (!StringUtils.isEmpty(k.getBuyCompany()) && !StringUtils.isEmpty(k.getBuyTime())
//                        && !StringUtils.isEmpty(k.getBuyStatus())
//                ) {
//                    resultList.add(k);
//                }
//            });
//        }
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }


    @RequestMapping(value = "/queryJtBenYueBuyDataInfo/{deptId}")
    @ApiOperation(value = "展示集团首页上面本月的采购统计数据")
    public Map<String, Object> queryJtBenYueBuyDataInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("展示首页上面本月的采购统计数据id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        List<IndexBenYueBuyCountVO> list = new ArrayList<>();
        List<IndexBenYueBuyCountVO> resultList = new ArrayList<>();
        logger.info("统计本月的采购采购数据,部门id==》》" + deptId);
        list = indexDao.queryJtBenYueBuyDataInfo();
//        if (!CollectionUtils.isEmpty(list)) {
//            list.forEach(k -> {
//                String dataStr = k.getBuyStatus();
//                if ("0".equals(String.valueOf(dataStr))) {
//                    k.setStatusText("待提交");
//                } else if ("1".equals(String.valueOf(dataStr))) {
//                    k.setStatusText("审核中");
//                } else if ("2".equals(String.valueOf(dataStr))) {
//                    k.setStatusText("审核完成");
//                }
//
//                if (!StringUtils.isEmpty(k.getBuyCompany()) && !StringUtils.isEmpty(k.getBuyTime())
//                        && !StringUtils.isEmpty(k.getBuyStatus())
//                ) {
//                    resultList.add(k);
//                }
//            });
//        }
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }

    @RequestMapping(value = "/queryJtBenYueDiaoPeiDataInfo/{deptId}")
    @ApiOperation(value = "集团展示首页上面本月的调配统计数据")
    public Map<String, Object> queryJtBenYueDiaoPeiDataInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("展示首页上面本月的调配统计数据 id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        List<IndexBenYueDiaoPeiCountVO> list = new ArrayList<>();
        logger.info("展示首页上面本月的调配统计数据,部门id==》》" + deptId);
        list = indexDao.queryBenYueDiaoPeiDataInfo(deptId);
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }


    @RequestMapping(value = "/queryBenYueDiaoPeiDataInfo/{deptId}")
    @ApiOperation(value = "展示首页上面本月的调配统计数据")
    public Map<String, Object> queryBenYueDiaoPeiDataInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("展示首页上面本月的调配统计数据 id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        List<IndexBenYueDiaoPeiCountVO> list = new ArrayList<>();
        logger.info("展示首页上面本月的调配统计数据,部门id==》》" + deptId);
        list = indexDao.queryBenYueDiaoPeiDataInfo(deptId);
//        if (!CollectionUtils.isEmpty(list)) {
//            list.forEach(k -> {
//                int dataStr = k.getDiaoPeiStatus();
//                if ("0".equals(dataStr)) {
//                    k.setDiaoPeiText("初始");
//                } else if ("1".equals(dataStr)) {
//                    k.setDiaoPeiText("审核中");
//                } else if ("2".equals(dataStr)) {
//                    k.setDiaoPeiText("审核完成");
//                }
//                String strings = k.getDataStr();
//                if (!StringUtils.isEmpty(strings)) {
//                    String[] array = strings.split(",");
//                    if (null != array && array.length > 0) {
//                        k.setDiaoPeiCount(array.length);
//                    }
//                } else {
//                    k.setDiaoPeiCount(0);
//                }
//           });
//        }
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }


    @RequestMapping(value = "/queryBenYueXunjianDataInfo/{deptId}")
    @ApiOperation(value = "展示首页上面本月的巡检统计数据")
    public Map<String, Object> queryBenYueXunjianDataInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("展示首页上面本月的巡检统计数据 id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        List<IndexBenYueXunJianCountVO> list = new ArrayList<>();
        logger.info("展示首页上面本月的巡检统计数据 ,部门id==》》" + deptId);
        list = indexDao.queryBenYueXunjianDataInfo(deptId);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(k -> {
                if (k.getXunjianStatus() == 1) {
                    k.setXunjianText("提交");
                } else if (k.getXunjianStatus() == 0) {
                    k.setXunjianText("巡检");
                }
            });
        }
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }


    @RequestMapping(value = "/queryBenYueBaoFeiDataInfo/{deptId}")
    @ApiOperation(value = "展示首页上面本月的报废统计数据")
    public Map<String, Object> queryBenYueBaoFeiDataInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("展示首页上面本月的报废统计数据 id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        List<IndexBenYueBaoFeiCountVO> list = new ArrayList<>();
        logger.info("展示首页上面本月的报废统计数据 ,部门id==》》" + deptId);

        list = indexDao.queryBenYueBaoFeiDataInfo(deptId);

        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }


    @RequestMapping(value = "/queryBenYueRepairCountInfo/{deptId}")
    @ApiOperation(value = "展示首页上面本月的报修统计数据")
    public Map<String, Object> queryBenYueRepairCountInfo(@PathVariable("deptId") String deptId) {
        if (StringUtils.isEmpty(deptId)) {
            logger.error("展示首页上面本月的报修统计数据id不能为空===={}", deptId);
            throw new NullPointerException("部门id不能为空");
        }
        Map<String, Object> map = new HashMap<>();
        List<IndexBenYueRepairDataVO> list = new ArrayList<>();
        logger.info("展示首页上面本月的报修统计数据,部门id==》》" + deptId);
        if ("01".equals(deptId)) {
            list = indexDao.queryZongHangRepairDataInfo(deptId);
//            if (!CollectionUtils.isEmpty(list)) {
//                list.forEach(k -> {
//                    String dataStr = k.getDataStr();
//                    String[] strings = dataStr.split(",");
//                    if (null != strings && strings.length > 0) {
//                        k.setRepairCount(strings.length);
//                    }
//                    if ("0".equals(String.valueOf(k.getRepairStatus()))) {
//                        k.setStatusText("初始");
//                    } else if ("1".equals(String.valueOf(k.getRepairStatus()))) {
//                        k.setStatusText("审核中");
//                    } else if ("2".equals(String.valueOf(k.getRepairStatus()))) {
//                        k.setStatusText("审核完成");
//                    }
//                });
//            }
        } else {
            list = indexDao.queryBenYueRepairCountInfo(deptId);
//            if (!CollectionUtils.isEmpty(list)) {
//                list.forEach(v -> {
//                    String dataStr = v.getDataStr();
//                    String[] strings = dataStr.split(",");
//                    if (null != strings && strings.length > 0) {
//                        v.setRepairCount(strings.length);
//                    }
//                    if ("0".equals(String.valueOf(v.getRepairStatus()))) {
//                        v.setStatusText("初始");
//                    } else if ("1".equals(String.valueOf(v.getRepairStatus()))) {
//                        v.setStatusText("审核中");
//                    } else if ("2".equals(String.valueOf(v.getRepairStatus()))) {
//                        v.setStatusText("审核完成");
//                    }
//                });
//            }
        }
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }

    @RequestMapping(value = "/queryzcJingZhiSum/{deptId}")
    @ApiOperation(value = "统计支行各个部门下面的资产净值")
    public Map queryzcJingZhiSum(@PathVariable("deptId") String deptId) {
      return  indexDao.queryzcJingZhiSum(deptId);
    }



    @RequestMapping(value = "/queryBuyData")
    @ApiOperation(value = "展示本月的采购统计数据")
    public Map<String, Object> queryBuyData() {

        Map<String, Object> map = new HashMap<>();
        List<QueryHomeData> list = new ArrayList<>();
        long deptId=UserUtil.getLoginUser().getDeptid();
        list = indexDao.queryBuyData(deptId);
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }

    @RequestMapping(value = "/queryDiaoPei")
    @ApiOperation(value = "展示本月的调配统计数据")
    public Map<String, Object> queryDiaoPei() {

        Map<String, Object> map = new HashMap<>();
        List<QueryHomeData> list = new ArrayList<>();
        long deptId=UserUtil.getLoginUser().getDeptid();
        list = indexDao.queryDiaoPei(deptId);
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }
    @RequestMapping(value = "/queryBaoXiu")
    @ApiOperation(value = "展示本月的报修统计数据")
    public Map<String, Object> queryBaoXiu() {

        Map<String, Object> map = new HashMap<>();
        List<QueryHomeData> list = new ArrayList<>();
        long deptId=UserUtil.getLoginUser().getDeptid();
        list = indexDao.queryBaoXiu(deptId);
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }

    @RequestMapping(value = "/queryChuZhi")
    @ApiOperation(value = "展示本月的处置")
    public Map<String, Object> queryChuZhi() {

        Map<String, Object> map = new HashMap<>();
        List<QueryHomeData> list = new ArrayList<>();
        long deptId=UserUtil.getLoginUser().getDeptid();
        list = indexDao.queryChuZhi(deptId);
        map.put("data", list);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }



}
