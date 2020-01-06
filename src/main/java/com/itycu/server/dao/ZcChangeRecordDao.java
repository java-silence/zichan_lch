package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.dto.ZcChangeRecordDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcChangeRecord;

@Mapper
public interface ZcChangeRecordDao {

    //@Select("select * from zc_change_record t where t.id = #{id}")
    ZcChangeRecord getById(Long id);

    @Delete("delete from zc_change_record where id = #{id}")
    int delete(Long id);

    int update(ZcChangeRecord zcChangeRecord);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_change_record(zc_info_id, self_codenum, zc_codenum, zc_name, zc_category_id, specification, model, factory, brand, support_name, support_phone, installation_factory, depreciation_time, jcz, stock_time, unit, zc_from, use_status, accountentry_status, accountentry_time, accountant_num, original_value, card_status, card_time, responsible, gl_dept_id, sy_dept_id, sy_name, store_address, start_use_time, predict_years, maintain_cycle, lave_time, maintain_deadline, last_maintain_time, lj_zhejiu, bn_zhejiu, netvalue, jzzb, net, net_residual_rate, net_residual_value, use_months, have_count, remainingperiod, cname, venperson, venphone, venaddress, warrantyperiod, del, bf, bz, c01, c02, c03, create_by, update_by, create_time, update_time,epcid,zc_coding,card_num,changeField) values(#{zcInfoId}, #{selfCodenum}, #{zcCodenum}, #{zcName}, #{zcCategoryId}, #{specification}, #{model}, #{factory}, #{brand}, #{supportName}, #{supportPhone}, #{installationFactory}, #{depreciationTime}, #{jcz}, #{stockTime}, #{unit}, #{zcFrom}, #{useStatus}, #{accountentryStatus}, #{accountentryTime}, #{accountantNum}, #{originalValue}, #{cardStatus}, #{cardTime}, #{responsible}, #{glDeptId}, #{syDeptId}, #{syName}, #{storeAddress}, #{startUseTime}, #{predictYears}, #{maintainCycle}, #{laveTime}, #{maintainDeadline}, #{lastMaintainTime}, #{ljZhejiu}, #{bnZhejiu}, #{netvalue}, #{jzzb}, #{net}, #{netResidualRate}, #{netResidualValue}, #{useMonths}, #{haveCount}, #{remainingperiod}, #{cname}, #{venperson}, #{venphone}, #{venaddress}, #{warrantyperiod}, #{del}, #{bf}, #{bz}, #{c01}, #{c02}, #{c03}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime},#{epcid},#{zcCoding},#{cardNum},#{changeField})")
    int save(ZcChangeRecord zcChangeRecord);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcChangeRecordDto> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int groupCount(@Param("params") Map<String, Object> params);

    List<ZcChangeRecordDto> groupList(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    List<ZcChangeRecordDto> listByCondition(@Param("params") Map<String, Object> params);

    @Select("select * from zc_change_record t order by id")
    List<ZcChangeRecord> listAll();

    @Select("select * from zc_change_record t where t.Ccode = #{Ccode}")
    ZcChangeRecord getByCcode(String Ccode);

    @Select("select max(ccode) from zc_change_record t")
    String getMaxCcode();
}
