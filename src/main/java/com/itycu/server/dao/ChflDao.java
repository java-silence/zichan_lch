package com.itycu.server.dao;

import com.itycu.server.model.Chfl;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChflDao {

    @Select("select * from t_invclass t where t.id = #{id}")
    Chfl getById(Long id);

    @Delete("delete from t_invclass where id = #{id}")
    int delete(Long id);

    int update(Chfl chfl);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_invclass(pid, ccode, cname, barcode, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03) values(#{pid}, #{ccode}, #{cname}, #{barcode}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Chfl chfl);

    int count(@Param("params") Map<String, Object> params);

    List<Chfl> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_invclass t order by ccode")
    List<Chfl> listChfl();

    @Select("select * from t_invclass t where pid <> 1 and id <> 1 and id <> 16 order by ccode")
    List<Chfl> listChflsm();

    @Select("select * from t_invclass t order by id")
    List<Chfl> listAll();

    @Select("SELECT t.pid,t.cname FROM t_invclass t")
    List<Chfl> listpidAndcname();

    @Select("select * from t_invclass t where t.pid=#{pid} order by ccode")
    List<Chfl> listTopchfls(Long pid);

}
