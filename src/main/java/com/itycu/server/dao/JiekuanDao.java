package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.Jiekuan;

@Mapper
public interface JiekuanDao {

    //@Select("select * from jiekuan t where t.id = #{id}")
    Jiekuan getById(Long id);

    @Delete("delete from jiekuan where id = #{id}")
    int delete(Long id);

    int update(Jiekuan jiekuan);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into jiekuan(deptid, jkje, jksy, status, memo, del, createby, createTime, updateby, updateTime, auditby, auditTime, c01, c02, c03, flowid, stepid) values(#{deptid}, #{jkje}, #{jksy}, #{status}, #{memo}, #{del}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{c01}, #{c02}, #{c03}, #{flowid}, #{stepid})")
    int save(Jiekuan jiekuan);
    
    int count(@Param("params") Map<String, Object> params);

    List<Jiekuan> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from jiekuan t order by id")
    List<Jiekuan> listAll();

    @Select("select * from jiekuan t where t.Ccode = #{Ccode}")
    Jiekuan getByCcode(String Ccode);

    @Select("select max(ccode) from jiekuan t")
    String getMaxCcode();
}
