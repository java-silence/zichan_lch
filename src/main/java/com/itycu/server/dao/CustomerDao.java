package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.Customer;

@Mapper
public interface CustomerDao {

    @Select("select t.*,c.cname classname from t_customer t left join t_customerclass c on c.id = t.cid where t.id = #{id}")
    Customer getById(Long id);

    @Delete("delete from t_customer where id = #{id}")
    int delete(Long id);

    int update(Customer customer);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_customer(ccode, cname, abbname, cid, caddress, cpostcode, cregcode, cbank, caccount, devdate, cperson, cphone, cfax, cemail, legalperson, pic, barcode, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03, xinyong,shuilv,ppath,deptid,xianluid) values(#{ccode}, #{cname}, #{abbname}, #{cid}, #{caddress}, #{cpostcode}, #{cregcode}, #{cbank}, #{caccount}, #{devdate}, #{cperson}, #{cphone}, #{cfax}, #{cemail}, #{legalperson}, #{pic}, #{barcode}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{xinyong}, #{shuilv}, #{ppath}, #{deptid}, #{xianluid})")
    int save(Customer customer);

    int saves(@Param("customers") List<Customer> customers);
    
    int count(@Param("params") Map<String, Object> params);

    List<Customer> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_customer t order by id")
    List<Customer> listAll();

    @Select("select * from t_customer t where t.deptid=#{deptid} order by id")
    List<Customer> listbydept(Long deptid);


    @Select("select * from t_customer t where t.ctype = #{ctype} order by id")
    List<Customer> listctype(String ctype);

}
