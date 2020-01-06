package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.Fukuanshenqing;

@Mapper
public interface FukuanshenqingDao {

    @Select("select t.*,t_dept.deptname,t_vendor.cname as ksmc,t_creator.nickname as creator from cw_fukuanshenqing t LEFT OUTER JOIN t_dept ON t.deptid=t_dept.id LEFT OUTER JOIN sys_user t_creator ON t.createby=t_creator.id LEFT OUTER JOIN t_vendor on t.ksid =t_vendor.id where t.id = #{id}")
    Fukuanshenqing getById(Long id);

    @Delete("delete from cw_fukuanshenqing where id = #{id}")
    int delete(Long id);

    int update(Fukuanshenqing fukuanshenqing);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into cw_fukuanshenqing(ccode, ddate, busstype, csource, bussid, fkfs, fkxm, fkje,deptid, ksid, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03, flowid, stepid) values(#{ccode}, #{ddate}, #{busstype}, #{csource}, #{bussid}, #{fkfs}, #{fkxm}, #{fkje}, #{deptid},#{ksid}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{flowid}, #{stepid})")
    int save(Fukuanshenqing fukuanshenqing);

    int saveFiles(@Param("fukuanshengqingId") Long fukuanshengqingId, @Param("biztype") String biztype, @Param("fileIds") List<String> fileIds);
    
    int count(@Param("params") Map<String, Object> params);

    List<Fukuanshenqing> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from cw_fukuanshenqing t order by id")
    List<Fukuanshenqing> listAll();

}
