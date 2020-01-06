package com.itycu.server.dao;

import com.itycu.server.model.Warehouse;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface WarehouseDao {

    @Select("select * from zx_warehouse t where t.id = #{id}")
    Warehouse getById(Long id);

    @Select("select * from zx_warehouse t where t.deptid = #{deptid} limit 0,1")
    Warehouse getByDeptId(Long deptid);

    @Delete("delete from zx_warehouse where id = #{id}")
    int delete(Long id);

    int update(Warehouse warehouse);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zx_warehouse(whcode, whname, whaddress, whphone, whperson, deptid, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, biztype, c01, c02, c03) values(#{whcode}, #{whname}, #{whaddress}, #{whphone}, #{whperson}, #{deptid}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{biztype}, #{c01}, #{c02}, #{c03})")
    int save(Warehouse warehouse);
    
    int count(@Param("params") Map<String, Object> params);

    List<Warehouse> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zx_warehouse t order by id")
    List<Warehouse> listAll();

    @Select("select * from zx_warehouse where biztype = '0' order by id")
    List<Warehouse> listsbwh();

    @Select("select * from zx_warehouse where biztype = '1' order by id")
    List<Warehouse> listbpwh();

    @Select("select * from zx_warehouse where deptid = #{deptid} order by id")
    Warehouse listByDept(Long deptid);

    @Select("select * from zx_warehouse where biztype = #{biztype} order by id")
    List<Warehouse> listbytype(String biztype);

    @Select("select * from zx_warehouse where biztype = #{biztype} and deptid=#{deptid} order by id")
    List<Warehouse> listbytypedept(@Param("biztype") String biztype,@Param("deptid") Long deptid);

//    @Select("select * from zx_warehouse where biztype = #{biztype} order by id")
//    List<Warehouse> listsbwh(@Param("biztype") int biztype);
}
