package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.app.vo.todovo.AppTodoVO;
import com.itycu.server.model.Todo;
import com.itycu.server.dto.TodoVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface TodoDao {

    @Select("select * from flow_todo t where t.id = #{id}")
    Todo getById(Long id);

    @Delete("delete from flow_todo where id = #{id}")
    int delete(Long id);

    int update(Todo todo);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into flow_todo(type,auditby, sendby, createTime, updateTime, biaoti, neirong, biztype, bizid, biztable, bizcreateby,bizdeptid, status, memo, c01, c02, c03,flowid,stepid,url,todoIds) values(#{type},#{auditby}, #{sendby}, #{createTime}, #{updateTime}, #{biaoti}, #{neirong}, #{biztype}, #{bizid}, #{biztable},#{bizcreateby},#{bizdeptid}, #{status}, #{memo}, #{c01}, #{c02}, #{c03}, #{flowid}, #{stepid},#{url},#{todoIds})")
    int save(Todo todo);

    int saveFiles(@Param("todoId") Long todoId, @Param("biztype") String biztype, @Param("fileIds") List<String> fileIds);

    int count(@Param("params") Map<String, Object> params);

    List<TodoVO> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from flow_todo t where t.status = #{type} and auditby =#{auditby} ")
    List<Todo> listByUser(@Param("type") String type, @Param("auditby") long auditby);

    @Select("select flow_todo.*,sys_user.nickname as sendname from flow_todo LEFT JOIN sys_user on flow_todo.sendby = sys_user.id where flow_todo.status = #{type} and flow_todo.auditby =#{auditby} order by updateTime desc limit #{limit}" )
    List<TodoVO> listTodoVOByUser(@Param("type") String type, @Param("auditby") long auditby, @Param("limit") int limit);

    //@Select("select t.*,senduser.nickname as sendname,senduser.deptid as senddeptid, senddept.deptname as senddeptname from flow_todo t LEFT JOIN sys_user senduser on t.sendby = senduser.id LEFT JOIN t_dept senddept on senduser.deptid = senddept.id  where t.bizid = #{bizid} order by t.updateTime")
    List<TodoVO> listByBizid(@Param("bizid") long bizid, @Param("flowid") long flowid);

//    @Update("update #{biztable} set stepid = #{stepid} where id = #{bizid}")
    int bizNextStep(@Param("biztable") String biztable, @Param("bizid") Long bizid, @Param("stepid") Long stepid);

    @Select("select * from (select * from flow_todo where stepid = 5 or stepid = 10) t where t.flowid = 1 and t.bizid = #{bizid}")
    TodoVO listByWxzc(Long bizid);   //维修完成

    @Select("select * from flow_todo t where t.auditby = #{userId} and t.type = #{type} and t.status = #{status} and t.url = #{bfActionUrl} and t.bizid = #{bfid} limit 0,1")
    Todo getNewTodo(@Param("userId") Long userId, @Param("type") Integer type,@Param("status")Integer status, @Param("bfActionUrl")String bfActionUrl,@Param("bfid") Long bfid);

    @Select("select * from flow_todo t where t.auditby = #{userId} and t.type = #{type} and t.status = #{status} and t.url = #{bfActionUrl} limit 0,1")
    Todo getNewTodoNoBfid(@Param("userId") Long userId, @Param("type") Integer type,@Param("status")Integer status, @Param("bfActionUrl")String bfActionUrl);

    List<Map<String,Object>> findAuditors(@Param("buyId") Long buyId, @Param("actionUrl") String actionUrl);

    List<Map<String,Object>> findShenheList(@Param("bizid") Long bizid, @Param("actionUrl")String actionUrl, @Param("memo")String memo);

    List<Map<String,Object>> getShenhe(@Param("todoId") Long todoId);

    List<Map<String,Object>> getShenpi(@Param("ids") List<String> ids);


    List<Todo> queryAllTodoList(@Param("userId")long userId);

    List<AppTodoVO> appQueryAllTodoList(@Param("userId")long userId);

    List<AppTodoVO> appQueryTodoList(@Param("userId")Long userId,@Param("status")Integer status);

    @Select("select id from flow_todo where bizid = #{bizid} and url = #{actionUrl}")
    List<Long> getByBizidAndUrl(@Param("bizid") Long bizid, @Param("actionUrl") String actionUrl);

    List<Map<String, Object>> findAgreeAuditors(@Param("strFlowItemIds") String strFlowItemIds, @Param("strTodoIds") String strTodoIds);
}
