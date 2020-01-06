package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.dto.ZcInspectRecordDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcInspectRecord;

@Mapper
public interface ZcInspectRecordDao {

    //@Select("select * from zc_inspect_record t where t.id = #{id}")
    ZcInspectRecord getById(Long id);

    @Delete("delete from zc_inspect_record where id = #{id}")
    int delete(Long id);

    @Delete("delete from zc_inspect_record where zc_inspect_id = #{zcInReId}")
    int deleteByZcInReId(Long zcInReId);

    int update(ZcInspectRecord zcInspectRecord);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_inspect_record(zc_id, content, check_time, result, check_user_id, check_username, create_by, create_time, bz, zc_inspect_id) values(#{zcId}, #{content}, #{checkTime}, #{result}, #{checkUserId}, #{checkUsername}, #{createBy}, #{createTime}, #{bz}, #{zcInspectId})")
    int save(ZcInspectRecord zcInspectRecord);

    int saves(@Param("zcInspectRecords")List<ZcInspectRecord> zcInspectRecords, @Param("zcInReId") Long zcInReId);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcInspectRecordDto> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_inspect_record t order by id")
    List<ZcInspectRecord> listAll();

    @Select("select * from zc_inspect_record t where t.Ccode = #{Ccode}")
    ZcInspectRecord getByCcode(String Ccode);

    @Select("select max(ccode) from zc_inspect_record t")
    String getMaxCcode();

    @Select("select * from v_zc_inspect_record t where t.zc_inspect_id = #{zcInReId}")
    List<ZcInspectRecordDto> listByZcInReId(Long zcInReId);

    List<ZcInspectRecordDto> listByCondition(@Param("params") Map<String, Object> params);
}
