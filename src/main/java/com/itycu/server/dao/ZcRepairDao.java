package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.dto.ZcRepairDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcRepair;

@Mapper
public interface ZcRepairDao {

    //@Select("select * from zc_repair t where t.id = #{id}")
    ZcRepairDto getById(Long id);

    @Delete("delete from zc_repair where id = #{id}")
    int delete(Long id);

    int update(ZcRepair zcRepair);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_repair(apply_user_id, apply_dept_id, zc_ids, repair_des, bz, flowid, stepid, status, repair_category, del, create_by, update_by, create_time, update_time,code,deptCode,confirm_time,confirm_dept,confirm_by) values(#{applyUserId}, #{applyDeptId}, #{zcIds}, #{repairDes}, #{bz}, #{flowid}, #{stepid}, #{status}, #{repairCategory}, #{del}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime},#{code},#{deptCode},#{confirmTime},#{confirmBy},#{confirmDept})")
    int save(ZcRepair zcRepair);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcRepairDto> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_repair t order by id")
    List<ZcRepair> listAll();

    @Select("select * from zc_repair t where t.Ccode = #{Ccode}")
    ZcRepair getByCcode(String Ccode);

    @Select("select max(ccode) from zc_repair t")
    String getMaxCcode();

    void updateStatus(@Param("params") Map<String, Object> params);

    int countByDeptThisYear(@Param("deptcode") String deptcode);


    int queryZcRepairById(@Param("id") long  id);

    int queryDeptZcRepairById(@Param("id") long  id);
}
