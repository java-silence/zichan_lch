package com.itycu.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.FlowDeptUser;

@Mapper
public interface FlowDeptUserDao {

    //@Select("select * from flow_dept_user t where t.id = #{id}")
    FlowDeptUser getById(Long id);

    @Delete("delete from flow_dept_user where id = #{id}")
    int delete(Long id);

    int update(FlowDeptUser flowDeptUser);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into flow_dept_user(flow_id, flowstep_id, dept_id, user_id, del, bz, create_time, update_time) values(#{flowId}, #{flowstepId}, #{deptId}, #{userId}, #{del}, #{bz}, #{createTime}, #{updateTime})")
    int save(FlowDeptUser flowDeptUser);
    
    int count(@Param("params") Map<String, Object> params);

    List<FlowDeptUser> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from flow_dept_user t order by id")
    List<FlowDeptUser> listAll();

    @Select("select * from flow_dept_user t where t.Ccode = #{Ccode}")
    FlowDeptUser getByCcode(String Ccode);

    @Select("select max(ccode) from flow_dept_user t")
    String getMaxCcode();

    List listFloeDeptUser(@Param("params") Map<String,Object> params, @Param("offset") int offset, @Param("limit") Integer limit);

    /* 根据部门信息查询节点信息 */
    List<FlowDeptUser> listAllFlowDeptUser(@Param("deptIdList") List<Long> deptIdList,@Param("flowId") Long flowId,@Param("stepId") Long stepId);

    Map getFlowDeptUser(String id);
}
