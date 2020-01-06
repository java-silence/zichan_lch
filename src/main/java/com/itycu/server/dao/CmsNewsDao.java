package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.itycu.server.model.CmsNews;

@Mapper
public interface CmsNewsDao {

    @Select("select * from cms_news t where t.id = #{id}")
    CmsNews getById(Long id);

    @Delete("delete from cms_news where id = #{id}")
    int delete(Long id);

    int update(CmsNews cmsNews);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into cms_news(permissionId, title, author, brief, content, img, imgtype, status, createby, createTime, updateby, updateTime, auditby, auditTime, memo, biztype, c01, c02, c03, hits,eventdate,eventtime,eventlocation) values(#{permissionId}, #{title}, #{author}, #{brief}, #{content}, #{img}, #{imgtype}, #{status}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{memo}, #{biztype}, #{c01}, #{c02}, #{c03}, #{hits},#{eventdate},#{eventtime},#{eventlocation})")
    int save(CmsNews cmsNews);
    
    int count(@Param("params") Map<String, Object> params);

    List<CmsNews> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from cms_news t where t.permissionid = #{id} order by eventDate desc limit 1")
    CmsNews LastContent(Long id);

    @Select("select * from cms_news t where t.id = #{id} ")
    CmsNews getContent(Long id);

    @Update("update cms_news t set t.hits = ifnull(t.hits,0) +1 where t.id = #{id} ")
    int addhits(Long id);

    @Select("select * from cms_news t where t.permissionid = #{id} order by eventDate desc limit #{limit}")
    List<CmsNews> getNewsList(@Param("id") Long id, @Param("limit") int limit);

    @Select("select * from cms_news t where t.permissionid = #{id} and eventdate >= CURDATE() order by eventDate limit #{limit}")
    List<CmsNews> getEventList(@Param("id") Long id, @Param("limit") int limit);

    @Select("select * from cms_news t where t.imgtype = #{imgtype} and t.img <> '' order by eventDate desc limit #{limit}")
    List<CmsNews> getHomeImg(@Param("imgtype") String imgtype, @Param("limit") int limit);
}
