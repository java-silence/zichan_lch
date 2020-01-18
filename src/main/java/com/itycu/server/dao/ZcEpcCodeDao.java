package com.itycu.server.dao;

import com.itycu.server.model.ZcEpcCode;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface ZcEpcCodeDao {

    ZcEpcCode getById(Long id);

    @Delete("delete from zc_epc_code where id = #{id}")
    int delete(Long id);

    int update(ZcEpcCode zcEpcCode);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_epc_code(epcid, deptId, enable, createTime, updateTime) values(#{epcid}, #{deptId}, #{enable}, #{createTime}, #{updateTime})")
    int save(ZcEpcCode zcEpcCode);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcEpcCode> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_epc_code t order by id")
    List<ZcEpcCode> listAll();

    @Select("select * from zc_epc_code t where t.epcid = #{epcid}")
    ZcEpcCode getByEpcid(String epcid);

    void saves(ArrayList<ZcEpcCode> zcEpcCodeList);

    @Select("SELECT epcid FROM zc_epc_code t LEFT JOIN t_dept p ON t.deptId = p.id WHERE p.deptcode LIKE concat(#{pDeptCode},'%') order by t.id desc limit 1")
    String findDeptLastCode(@Param("syDeptId") Long syDeptId,@Param("pDeptCode") String pDeptCode);
}
