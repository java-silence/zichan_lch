package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.model.Flow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FlowDao {

    @Select("select * from flow t where t.id = #{id}")
    Flow getById(Long id);

    @Delete("delete from flow where id = #{id}")
    int delete(Long id);

    int update(Flow flow);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into flow(flowname, description, createby, createTime, updateby, updateTime, status, del, memo, biztype, c01, c02, c03) values(#{flowname}, #{description}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{status}, #{del}, #{memo}, #{biztype}, #{c01}, #{c02}, #{c03})")
    int save(Flow flow);
    
    int count(@Param("params") Map<String, Object> params);

    List<Flow> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from flow t where del = '0' order by id")
    List<Flow> listAll();

    @Select("select * from flow t where del = '0' and flowname like concat('%',#{flowname},'%')")
    Flow findByName(@Param("flowname") String flowname);
}
