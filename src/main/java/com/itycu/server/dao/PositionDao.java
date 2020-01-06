package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.Position;

@Mapper
public interface PositionDao {

    @Select("select * from t_position t where t.id = #{id}")
    Position getById(Long id);

    @Delete("delete from t_position where id = #{id}")
    int delete(Long id);

    int update(Position position);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_position(positioncode, positionname, description, deptid, pid, createTime, updateTime, status, memo, c01, c02, c03) values(#{positioncode}, #{positionname}, #{description}, #{deptid}, #{pid}, #{createTime}, #{updateTime}, #{status}, #{memo}, #{c01}, #{c02}, #{c03})")
    int save(Position position);
    
    int count(@Param("params") Map<String, Object> params);

    List<Position> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_position t order by id")
    List<Position> listAll();
}
