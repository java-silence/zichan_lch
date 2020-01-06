package com.itycu.server.dao;


import com.itycu.server.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface IndexDao {


    Map<String,Object> queryJtIndexJinE();

    /**
     * 统计各支行下面的资产分类类别
     *
     * @return 不同的资产分类列表
     */
    List<IndexAssetCategoryVO> queryJtIndexAssetCategory();

    /**
     * 统计不同支行下面的资产分类类别
     *
     * @param deptId 部门编码
     * @return 不同的资产分类列表
     */
    List<IndexAssetCategoryVO> queryIndexAssetCategory(@Param("deptId") String deptId);


    /**
     * 统计不同的地区的资产数量
     *
     * @param deptId 部门编码
     * @return 资产数量
     */
    int queryIndexZoneAssetCount(@PathVariable("deptId") String deptId);
    //List<IndexBranchCountVO>  queryBranchDataInfo(@Param("deptId") String deptId);


    /**
     * 查询该部门今年的报修数量
     *
     * @param deptId 部门id
     * @return 报修数量
     */
    int queryRepairCountThisYear(@Param("deptId") String deptId);


    /**
     * 首页统计商行下面的所有的部门资产的统计数据
     *
     * @param deptId 部门id
     * @return 返回的统计的数据
     */
    List<IndexBranchDataCountVO> queryBranchDataList(@Param("deptId") String deptId);


    /**
     * 统计商行下面的支行的数据信息
     *
     * @param deptcode 部门编码
     * @return 部门下的资产信息
     */
    List<IndexBranchDataCountVO> queryZhihangZcDataInfo(@Param("deptcode") String deptcode);


    /**
     * 首页支行下面的所有分行的资产数据
     *
     * @param deptId 部门id
     * @return 支行资产数量的列表集合
     */
    List<IndexFenhangCountVO> queryBranchAllSubBranchAssert(@Param("deptId") String deptId);


    /**
     * 集团查询所有子公司本月的采购数据信息
     * @param deptId 采购部门id
     * @return 采购物资列表数据
     */
    List<IndexBenYueBuyCountVO> queryJtBenYueBuyDataInfo();


    /**
     * 查询本月的采购数据信息
     * @param deptId 采购部门id
     * @return 采购物资列表数据
     */
    List<IndexBenYueBuyCountVO> queryBenYueBuyDataInfo(@Param("deptId") String deptId);



    /**
     * 查询本月的报修数据信息
     * @param deptId 报修数据的部门id
     * @return 报修数据的列表信息
     */
    List<IndexBenYueRepairDataVO> queryBenYueRepairCountInfo(@Param("deptId") String deptId);


    /**
     * 查询总行的采购数量
     * @param deptId 部门id
     * @return 采购数据的列表
     */
    List<IndexBenYueBuyCountVO> queryZongHangBuyDataInfo(@Param("deptId") String deptId);



    /**
     * 查询总行的采购数量
     * @param deptId 部门id
     * @return 采购数据的列表
     */
    List<IndexBenYueRepairDataVO> queryZongHangRepairDataInfo(@Param("deptId") String deptId);


    /**
     * 查询本月调配的统计数据
     * @param deptId 部门id
     * @return 统计调配的数据列表
     */
    List<IndexBenYueDiaoPeiCountVO>  queryBenYueDiaoPeiDataInfo(@Param("deptId") String deptId);


    /**
     * 查询本月报废的数据数据统计
     * @param deptId 查询部门id
     * @return 返回的查询列表
     */
    List<IndexBenYueBaoFeiCountVO>  queryBenYueBaoFeiDataInfo(@Param("deptId") String deptId);


    /**
     * 查询本月的巡检数量
     * @param deptId 巡检部门id
     * @return 返回的巡检数据
     */
    List<IndexBenYueXunJianCountVO>  queryBenYueXunjianDataInfo(@Param("deptId") String deptId);

    /**
     * 支行资产统计
     * @param deptId
     * @return
     */
    Map queryIndexZhihangCount(@Param("deptId") String deptId);

    /**
     * 获取部门资产净值
     * @param deptId
     * @return
     */
    @Select("SELECT (select ROUND(sum(original_value-(original_value*0.95/use_months)*TIMESTAMPDIFF(MONTH,start_use_time,NOW())),2)as zcJingZhi from zc_info where sy_dept_id=#{deptId} and TIMESTAMPDIFF(MONTH,start_use_time,NOW())<=use_months and original_value is NOT Null and use_months is NOT Null)as zcJingZhi,(select count(1)from zc_info a left join zc_category b on b.id=a.zc_category_id where b.cat_code like'01%'and a.sy_dept_id=#{deptId})as GdzcCount,(select count(1)from zc_info a left join zc_category b on b.id=a.zc_category_id where b.cat_code like'03%'and a.sy_dept_id=#{deptId})as DzyhCount ")
    Map queryzcJingZhiSum(@Param("deptId")String deptId);

    /**
     * 获取部门采购统计
     * @param deptId
     * @return
     */
    List<QueryHomeData> queryBuyData(@Param("deptId")long deptId);
    /**
     * 调配
     * @param deptId
     * @return
     */
    List<QueryHomeData> queryDiaoPei(@Param("deptId")long deptId);

    /**
     * 报修
     * @param deptId
     * @return
     */
    List<QueryHomeData> queryBaoXiu(@Param("deptId")long deptId);

    /**
     * 处置
     * @param deptId
     * @return
     */
    List<QueryHomeData> queryChuZhi(@Param("deptId")long deptId);


}
