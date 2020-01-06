package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.FlowDoc;

@Mapper
public interface FlowDocDao {

    @Select("select * from v_flow_doc t where t.id = #{id}")
    FlowDoc getById(Long id);

    @Delete("delete from flow_doc where id = #{id}")
    int delete(Long id);

    int update(FlowDoc flowDoc);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into flow_doc(title, author, brief, content, img, imgtype, ddate1, ddate2, status, deptid, createby, createTime, updateby, updateTime, auditby, auditTime, memo, biztype, doctype, c01, c02, c03, c04, c05, flowid, stepid) values(#{title}, #{author}, #{brief}, #{content}, #{img}, #{imgtype}, #{ddate1}, #{ddate2}, #{status}, #{deptid}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{memo}, #{biztype}, #{doctype}, #{c01}, #{c02}, #{c03}, #{c04}, #{c05}, #{flowid}, #{stepid})")
    int save(FlowDoc flowDoc);
    
    int count(@Param("params") Map<String, Object> params);

    List<FlowDoc> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from flow_dv_oc t order by id")
    List<FlowDoc> listAll();

}
