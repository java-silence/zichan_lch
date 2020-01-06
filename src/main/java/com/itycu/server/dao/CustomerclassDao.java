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

import com.itycu.server.model.Customerclass;

@Mapper
public interface CustomerclassDao {

    @Select("select * from t_customerclass t where t.id = #{id}")
    Customerclass getById(Long id);

    @Delete("delete from t_customerclass where id = #{id}")
    int delete(Long id);

    int update(Customerclass customerclass);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_customerclass(pid, ccode, cname, barcode, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03,deptid) values(#{pid}, #{ccode}, #{cname}, #{barcode}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{deptid})")
    int save(Customerclass customerclass);
    
    int count(@Param("params") Map<String, Object> params);

    List<Customerclass> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_customerclass t order by id")
    List<Customerclass> listAll();

    @Select("select * from t_customerclass t order by ccode")
    List<Customerclass> listcustomerclass();

    @Select("select * from t_customerclass t where t.pid=#{pid} order by ccode")
    List<Customerclass> listTopcustomerclass(Long pid);

    @Select("select * from t_customerclass t where deptid = #{deptid} order by id")
    List<Customerclass> listByDeptid(Long deptid);

    @Select("select * from t_customerclass t where c01 = '线路' order by id")
    List<Customerclass> listXianlu();
}
