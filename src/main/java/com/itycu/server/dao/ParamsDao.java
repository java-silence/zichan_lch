package com.itycu.server.dao;

import com.itycu.server.model.Params;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ParamsDao {

    @Select("select * from sys_params t where t.id = #{id}")
    Params getById(Long id);

    @Select("select * from sys_params t where t.cname = #{cname}")
    Params getByName(String cname);

    @Delete("delete from sys_params where id = #{id}")
    int delete(Long id);

    int update(Params params);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into sys_params(pid, ccode, cname, val1, val2, val3, isort, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03) values(#{pid}, #{ccode}, #{cname}, #{val1}, #{val2}, #{val3}, #{isort}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Params params);
    
    int count(@Param("params") Map<String, Object> params);

    List<Params> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from sys_params t order by id")
    List<Params> listAll();

}
