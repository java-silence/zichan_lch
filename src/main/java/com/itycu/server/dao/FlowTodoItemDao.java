package com.itycu.server.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.FlowTodoItem;

@Mapper
public interface FlowTodoItemDao {

    //@Select("select * from flow_todo_item t where t.id = #{id}")
    FlowTodoItem getById(Long id);

    @Delete("delete from flow_todo_item where id = #{id}")
    int delete(Long id);

    int update(FlowTodoItem flowTodoItem);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into flow_todo_item(auditby,sendby,flow_todo_id, flow_item_id, status, bz, content, create_time) values(#{auditby},#{sendby},#{flowTodoId}, #{flowItemId}, #{status}, #{bz}, #{content}, #{createTime})")
    int save(FlowTodoItem flowTodoItem);
    
    int count(@Param("params") Map<String, Object> params);

    List<FlowTodoItem> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from flow_todo_item t order by id")
    List<FlowTodoItem> listAll();

    @Select("select * from flow_todo_item t where t.Ccode = #{Ccode}")
    FlowTodoItem getByCcode(String Ccode);

    @Select("select max(ccode) from flow_todo_item t")
    String getMaxCcode();

    // 批量更新flowtodoitem的状态
    void updateListStatus(@Param("status") Integer status, @Param("toDoItemIds") List<Long> toDoItemIds);

    @Select("select * from flow_todo_item t where t.flow_todo_id = #{todoId}")
    List<FlowTodoItem> listByToDoId(@Param("todoId") Long todoId);

    FlowTodoItem getByParams(@Param("params") HashMap<String, Object> params);

}
