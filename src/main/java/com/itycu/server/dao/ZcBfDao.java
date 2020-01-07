package com.itycu.server.dao;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcBf;

@Mapper
public interface ZcBfDao {

    //@Select("select * from zc_bf t where t.id = #{id}")
    ZcBf getById(Long id);

    @Delete("delete from zc_bf where id = #{id}")
    int delete(Long id);

    int update(ZcBf zcBf);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_bf(bfNum,apply_user_id, apply_dept_id, deptCode,zc_ids, bf_des, bz, flowid, stepid, status, bf_category,del, create_by, update_by, create_time, update_time) values(#{bfNum},#{applyUserId}, #{applyDeptId}, #{deptCode},#{zcIds}, #{bfDes}, #{bz}, #{flowid}, #{stepid}, #{status},#{bfCategory}, #{del}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime})")
    int save(ZcBf zcBf);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcBf> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_bf t order by id")
    List<ZcBf> listAll();

    @Select("select * from zc_bf t where t.Ccode = #{Ccode}")
    ZcBf getByCcode(String Ccode);

    @Select("select max(ccode) from zc_bf t")
    String getMaxCcode();

    List<Map<String,Object>> listZcbf(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    void updateStatus(@Param("params") Map<String, Object> params);

    List<Map<String,Object>> listZcBfRecord(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int countListZcBfRecord(@Param("params") Map<String, Object> params);

    // 查询报废说明
    HashMap<String,Object> getZcBfDetail(@Param("id") Long id);

    @Select("SELECT count(1) FROM zc_bf t WHERE YEAR(t.create_time) = YEAR (now()) AND t.apply_dept_id = #{deptid}")
    Integer countRecord(@Param("createTime") Date createTime, @Param("deptid") Long deptid);

    void updateStatusList(@Param("status") Integer status, @Param("zcbfIds") ArrayList<Long> zcbfIds);

    int countByDeptThisYear(@Param("deptcode") String deptcode);


    int queryBaoFeiCountById(@Param("id") long  id);


    int queryDeptBaoFeiCountById(@Param("id") long  id);

}
