package com.itycu.server.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcDeploy;
import org.springframework.security.core.parameters.P;

@Mapper
public interface ZcDeployDao {

    //@Select("select * from zc_deploy t where t.id = #{id}")
    ZcDeploy getById(Long id);

    @Delete("delete from zc_deploy where id = #{id}")
    int delete(Long id);

    int update(ZcDeploy zcDeploy);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_deploy(deployNum ,zc_ids, apply_user_id, apply_dept_id, deptCode,front_dept_id, front_address, back_dept_id, back_address, description, flowid, stepid, status, del, bz, create_by, update_by, create_time, update_time) values(#{deployNum},#{zcIds}, #{applyUserId}, #{applyDeptId}, #{deptCode},#{frontDeptId}, #{frontAddress}, #{backDeptId}, #{backAddress}, #{description}, #{flowid}, #{stepid}, #{status}, #{del}, #{bz}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime})")
    int save(ZcDeploy zcDeploy);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcDeploy> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_deploy t order by id")
    List<ZcDeploy> listAll();

    @Select("select * from zc_deploy t where t.Ccode = #{Ccode}")
    ZcDeploy getByCcode(String Ccode);

    @Select("select max(ccode) from zc_deploy t")
    String getMaxCcode();

    void updateStatus(@Param("params") Map<String, Object> params);

    List<Map<String,Object>> listZcDeploy(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    HashMap<String,Object> getZcDeployDetail(@Param("deployId") Long deployId);

    int countListZcDeployRecord(@Param("params") Map<String, Object> params);

    List<Map<String,Object>> listZcDeployRecord(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int countByDeptThisYear(@Param("deptcode") String deptcode);


    int queryDeployCountById(@Param("id") long id );


    int  queryDeptDeployCountById(@Param("id") long id );
}
