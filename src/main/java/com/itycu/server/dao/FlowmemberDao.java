package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.model.Flowmember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FlowmemberDao {

    @Select("select * from flowmember t where t.id = #{id}")
    Flowmember getById(Long id);

    @Delete("delete from flowmember where id = #{id}")
    int delete(Long id);

    int update(Flowmember flowmember);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into flowmember(flowid, stepid, memid, memtype, description, createby, createTime, updateby, updateTime, status, del, memo, biztype, c01, c02, c03) values(#{flowid}, #{stepid}, #{memid}, #{memtype}, #{description}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{status}, #{del}, #{memo}, #{biztype}, #{c01}, #{c02}, #{c03})")
    int save(Flowmember flowmember);
    
    int count(@Param("params") Map<String, Object> params);

    List<Flowmember> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select memid from flowmember t where t.flowid=#{flowid} and t.stepid = #{stepid} limit 0,1")
    Long getMemidByFlowStep(@Param("flowid") Long flowid, @Param("stepid") Long stepid);

    @Select("select * from flowmember t where t.flowid=#{flowid} and t.stepid = #{stepid} limit 0,1")
    Flowmember getByFlowStep(@Param("flowid") Long flowid, @Param("stepid") Long stepid);

    int insertFlowmember(@Param("flowid") Long flowid, @Param("stepid") Long stepid, @Param("memid") String memid);

    int delFlowidAndStepid(@Param("flowid") Long flowid, @Param("stepid") Long stepid);

}
