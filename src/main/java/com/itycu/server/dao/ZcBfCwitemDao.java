package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcBfCwitem;

@Mapper
public interface ZcBfCwitemDao {

    //@Select("select * from zc_bf_cwitem t where t.id = #{id}")
    ZcBfCwitem getById(Long id);

    @Delete("delete from zc_bf_cwitem where id = #{id}")
    int delete(Long id);

    int update(ZcBfCwitem zcBfCwitem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_bf_cwitem(todoId,bfId,codeNum, todoIds, createTime, updateTime) values(#{todoId},#{bfId},#{codeNum}, #{todoIds}, #{createTime}, #{updateTime})")
    int save(ZcBfCwitem zcBfCwitem);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcBfCwitem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_bf_cwitem t order by id")
    List<ZcBfCwitem> listAll();

    @Select("select * from zc_bf_cwitem t where t.Ccode = #{Ccode}")
    ZcBfCwitem getByCcode(String Ccode);

    @Select("select max(ccode) from zc_bf_cwitem t")
    String getMaxCcode();

    int countThisYear();
}
