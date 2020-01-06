package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.Vendor;

@Mapper
public interface VendorDao {

    @Select("select t.*,c.cname classname  from t_vendor t left join t_vendorclass c on c.id = t.cid where t.id = #{id}")
    Vendor getById(Long id);

    @Delete("delete from t_vendor where id = #{id}")
    int delete(Long id);

    int update(Vendor vendor);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_vendor(ccode, cname, abbname, cid, venaddress, venpostcode, venregcode, venbank, venaccount, devdate, venperson, venphone, venfax, venemail, legalperson, pic, barcode, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03,ppath,deptid) values(#{ccode}, #{cname}, #{abbname}, #{cid}, #{venaddress}, #{venpostcode}, #{venregcode}, #{venbank}, #{venaccount}, #{devdate}, #{venperson}, #{venphone}, #{venfax}, #{venemail}, #{legalperson}, #{pic}, #{barcode}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{ppath}, #{deptid})")
    int save(Vendor vendor);
    
    int count(@Param("params") Map<String, Object> params);

    List<Vendor> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_vendor t order by id")
    List<Vendor> listAll();

    @Select("select * from t_vendor t where t.ctype = #{ctype} order by id")
    List<Vendor> listctype(String ctype);

}
