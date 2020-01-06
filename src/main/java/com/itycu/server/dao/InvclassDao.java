package com.itycu.server.dao;

import com.itycu.server.model.Invclass;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface InvclassDao {

    @Select("select * from t_invclass t where t.id = #{id}")
    Invclass getById(Long id);

    @Delete("delete from t_invclass where id = #{id}")
    int delete(Long id);

    int update(Invclass invclass);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_invclass(pid, ccode, cname, barcode, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03) values(#{pid}, #{ccode}, #{cname}, #{barcode}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Invclass invclass);
    
    int count(@Param("params") Map<String, Object> params);

    List<Invclass> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_invclass t order by id")
    List<Invclass> listAll();

    @Select("select * from t_invclass t where t.id = #{id} or pid = #{id} order by id")
    List<Invclass> listIdOrPid(Long id);

    @Select("select * from t_invclass t where pid = #{pid} order by id")
    List<Invclass> listByPid(Long pid);

}
