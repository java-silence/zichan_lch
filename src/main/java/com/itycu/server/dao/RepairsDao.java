package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.dto.RepairsVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.Repairs;

@Mapper
public interface RepairsDao {

    @Select("select * from v_zx_repairs t where t.id = #{id}")
    Repairs getById(Long id);

    @Select("select * from v_zx_repairs t where t.pid = #{repairid}")
    List<RepairsVO> getByRepairId(Long repairid);

    @Delete("delete from zx_repairs where id = #{id}")
    int delete(Long id);

    @Delete("delete from zx_repairs where pid = #{id}")
    int deletelist(Long repairid);

    int update(Repairs repairs);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zx_repairs(pid, invid, inum, iprice, discount, taxrate, itax, imoney, cbatch, status, del, memo, ctype, c01, c02, c03) values(#{pid}, #{invid}, #{inum}, #{iprice}, #{discount}, #{taxrate}, #{itax}, #{imoney}, #{cbatch}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Repairs repairs);

    int saves(@Param("repairsList") List<Repairs> stockinsList, @Param("repairid") Long repairid);
    
    int count(@Param("params") Map<String, Object> params);

    List<Repairs> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zx_repairs t order by id")
    List<Repairs> listAll();

}
