package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.itycu.server.model.Pushmsg;

@Mapper
public interface PushmsgDao {

    @Select("select * from t_pushmsg t where t.id = #{id}")
    Pushmsg getById(Long id);

    @Delete("delete from t_pushmsg where id = #{id}")
    int delete(Long id);

    int update(Pushmsg pushmsg);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_pushmsg(title, content, userid, url, bizid, todoid, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03) values(#{title}, #{content}, #{userid}, #{url}, #{bizid}, #{todoid}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Pushmsg pushmsg);
    
    int count(@Param("params") Map<String, Object> params);

    List<Pushmsg> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_pushmsg t order by id")
    List<Pushmsg> listAll();

    //根据用户ID提取最新一条未读消息
    @Select("select * from t_pushmsg t where userid = #{userid} and status='0' order by id desc limit 1")
    Pushmsg getmsgbyuserid(Long userid);

    //根据用户ID提取最新一条未读消息
    @Update("update t_pushmsg t set status='1' where userid = #{userid} and status='0'")
    int setmsgbyuserid(Long userid);
}
