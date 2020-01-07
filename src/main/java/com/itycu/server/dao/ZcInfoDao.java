package com.itycu.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itycu.server.dto.ZcInfoDto;
import com.itycu.server.model.ZcInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ZcInfoDao {

    //@Select("select * from zc_info t where t.id = #{id}")
    ZcInfoDto getById(Long id);

    @Delete("delete from zc_info where id = #{id}")
    int delete(Long id);

    int update(ZcInfo zcInfo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_info(self_codenum, zc_codenum, zc_name, zc_category_id, specification, model, factory, brand, support_name, support_phone, installation_factory, depreciation_time, jcz, stock_time, unit, zc_from, use_status, accountentry_status, accountentry_time, accountant_num, original_value, card_status, card_time, responsible, gl_dept_id, sy_dept_id, sy_name, store_address, start_use_time, predict_years, maintain_cycle, lave_time, maintain_deadline, last_maintain_time, lj_zhejiu, bn_zhejiu, netvalue, jzzb, net, net_residual_rate, net_residual_value, use_months, have_count, remainingperiod, cname, venperson, venphone, venaddress, warrantyperiod, del, bf, bz, c01, c02, c03, create_by, update_by, create_time, update_time, card_num,inspect_time,epcid,zc_coding) values(#{selfCodenum}, #{zcCodenum}, #{zcName}, #{zcCategoryId}, #{specification}, #{model}, #{factory}, #{brand}, #{supportName}, #{supportPhone}, #{installationFactory}, #{depreciationTime}, #{jcz}, #{stockTime}, #{unit}, #{zcFrom}, #{useStatus}, #{accountentryStatus}, #{accountentryTime}, #{accountantNum}, #{originalValue}, #{cardStatus}, #{cardTime}, #{responsible}, #{glDeptId}, #{syDeptId}, #{syName}, #{storeAddress}, #{startUseTime}, #{predictYears}, #{maintainCycle}, #{laveTime}, #{maintainDeadline}, #{lastMaintainTime}, #{ljZhejiu}, #{bnZhejiu}, #{netvalue}, #{jzzb}, #{net}, #{netResidualRate}, #{netResidualValue}, #{useMonths}, #{haveCount}, #{remainingperiod}, #{cname}, #{venperson}, #{venphone}, #{venaddress}, #{warrantyperiod}, #{del}, #{bf}, #{bz}, #{c01}, #{c02}, #{c03}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime},#{cardNum},#{inspectTime},#{epcid},#{zcCoding})")
    int save(ZcInfo zcInfo);

    int saves(List<ZcInfo> zcInfo);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcInfoDto> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<ZcInfoDto> listByCondition(@Param("params") Map<String, Object> params);

    @Select("select * from zc_info where del = 0 t order by id")
    List<ZcInfo> listAll();

    @Select("select * from zc_info t where t.Ccode = #{Ccode}")
    ZcInfo getByCcode(String Ccode);

    @Select("select max(ccode) from zc_info t")
    String getMaxCcode();

    void updateStatusList(@Param("status") Integer status, @Param("zcids") List<Long> zcids);

    /** 更改报废状态. */
    void updateBfStatusList(@Param("status") Integer status, @Param("zcids") List<Long> zcids);

    /**
     * 查询财务部下面的资产的信息列表
     * @param params 查询条件map
     * @return 查询返回的数据
     */
    List<ZcInfoDto>  queryCwbZcInfoList(@Param("params") Map<String, Object> params);


    /**
     * 查询其他部门的资产信息表
     * @param params 查询条件map
     * @return 查询返回的数据
     */
    List<ZcInfoDto>  queryOtherZcInfoList(@Param("params") Map<String, Object> params);


    /**
     * 创建使用部门的资产信息列表
     * @param params
     * @return
     */
    List<ZcInfoDto> querySyDeptZcList(@Param("params") Map<String, Object> params);

    /**
     * 根据使用部门统计资产数
     * @param syDeptId
     * @return
     */
    int countByDeptId(@Param("syDeptId") Long syDeptId);

    @Select("SELECT count(t.id) FROM zc_info t LEFT JOIN t_dept p ON t.sy_dept_id = p.id WHERE p.deptcode like concat(#{deptcode},'%')")
    int countByDeptcode(@Param("deptcode") String deptcode);




    List<ZcInfoDto>  getZcValueAndZcNumber(@Param("id") long  id);


    Map<String,Object>  getDifferentDeptZcCount(@Param("id") long id);


    List<ZcInfoDto> queryGlDeptZcList(@Param("id") long id);


    Map<String, Object>  getGlDeptZcCount(@Param("id") long id);


    List<ZcInfoDto> queryAllDeptZcList(@Param("id") long id);


    List<ZcInfoDto> getAllZcInfoListByManager(Map<String, Object> map );


    /**
     * 普通的部门获取的资产信息
     * @param map
     * @return
     */
    List<ZcInfoDto> getAllZcInfoListOrdinaryDept(Map<String, Object> map );

}
