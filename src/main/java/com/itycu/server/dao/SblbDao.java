package com.itycu.server.dao;

import com.itycu.server.model.Sblb;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface SblbDao {

    @Select("select * from zx_sblb t where t.id = #{id}")
    Sblb getById(Long id);

    @Delete("delete from zx_sblb where id = #{id}")
    int delete(Long id);

    int update(Sblb sblb);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zx_sblb(pid, xtid, ccode, cname, isort, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03) values(#{pid}, #{xtid}, #{ccode}, #{cname}, #{isort}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Sblb sblb);
    
    int count(@Param("params") Map<String, Object> params);

    List<Sblb> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zx_sblb t order by id")
    List<Sblb> listAll();
    @Select("select * from zx_sblb t where xtid=#{xtid} order by id")
    List<Sblb> listByXtid(@Param("xtid") Long xtid);
}
