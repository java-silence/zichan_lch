package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.dto.FlowstepDto;
import com.itycu.server.model.Flowstep;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FlowstepDao {

    @Select("select * from flowstep t where t.id = #{id}")
    Flowstep getById(Long id);

    @Select("select * from flowstep t where t.flowid = #{flowid} and t.stepid=#{stepid}")
    Flowstep getByStpeId(@Param("flowid") Long flowid, @Param("stepid") Long stepid);

    @Delete("delete from flowstep where id = #{id}")
    int delete(Long id);

    int update(Flowstep flowstep);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into flowstep(flowid, stepid, stepname, description, tofinish, flowrule, flowact, passnum, localalert, basehour, cyctimes, period, createby, createTime, updateby, updateTime, status, del, memo, biztype, c01, c02, c03) values(#{flowid}, #{stepid}, #{stepname}, #{description}, #{tofinish}, #{flowrule}, #{flowact}, #{passnum}, #{localalert}, #{basehour}, #{cyctimes}, #{period}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{status}, #{del}, #{memo}, #{biztype}, #{c01}, #{c02}, #{c03})")
    int save(Flowstep flowstep);
    
    int count(@Param("params") Map<String, Object> params);

    List<Flowstep> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from flowstep t where t.flowid = #{flowid} order by stepid")
    List<Flowstep> listSteps(long flowid);

    @Select("select t.*,m.memid 'flowmember.memid',m.memtype 'flowmember.memtype' from flowstep t LEFT JOIN (select * from flowmember where Id in (select max(id) from flowmember where flowid = #{flowid} group by stepid)) m on t.stepid = m.stepid and t.flowid = m.flowid  where t.flowid = #{flowid}  order by t.stepid")
    List<FlowstepDto> listStepsAndMember(long flowid);

    @Select("select max(stepid) from flowstep t where t.flowid=#{flowid}")
    int getMaxStepbyFlowId(long flowid);

    List<String> findBynickname(@Param("flowid") Long flowid, @Param("stepid") Long stepid);

    List<String> findByuserId(@Param("flowid") Long flowid, @Param("stepid") Long stepid);

//    List<FlowstepDto> liststepNames(Long flowid);

}
