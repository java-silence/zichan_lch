package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.model.Chfl;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.Vendorclass;

@Mapper
public interface VendorclassDao {

    @Select("select * from t_vendorclass t where t.id = #{id}")
    Vendorclass getById(Long id);

    @Delete("delete from t_vendorclass where id = #{id}")
    int delete(Long id);

    int update(Vendorclass vendorclass);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_vendorclass(pid, ccode, cname, barcode, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03,deptid) values(#{pid}, #{ccode}, #{cname}, #{barcode}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{deptid})")
    int save(Vendorclass vendorclass);
    
    int count(@Param("params") Map<String, Object> params);

    List<Vendorclass> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_vendorclass t order by id")
    List<Vendorclass> listAll();

    @Select("select * from t_vendorclass t order by ccode")
    List<Vendorclass> listvendorclass();

    @Select("select * from t_vendorclass t where t.pid=#{pid} order by ccode")
    List<Vendorclass> listTopchfls(Long pid);

    @Select("select * from t_vendorclass t where deptid = #{deptid} order by id")
    List<Vendorclass> listByDeptid(Long deptid);
}
