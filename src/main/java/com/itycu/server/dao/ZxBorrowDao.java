package com.itycu.server.dao;

import com.itycu.server.dto.ZxBorrowVO;
import com.itycu.server.model.ZxBorrow;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ZxBorrowDao {

    @Select("select * from v_zx_borrow t where t.id = #{id}")
    ZxBorrowVO getById(Long id);

    @Delete("delete from zx_borrow where id = #{id}")
    int delete(Long id);

    int update(ZxBorrow zxBorrow);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zx_borrow(deptid, whid, bizdate, eqpid, description, quantity, unit, price, money, expectreturn, returndate, returndesc, status, memo, del, biztype, createby, createTime, updateby, updateTime, auditby, auditTime, c01, c02, c03,flowid,stepid,whid2) values(#{deptid}, #{whid}, #{bizdate}, #{eqpid}, #{description}, #{quantity}, #{unit}, #{price}, #{money}, #{expectreturn}, #{returndate}, #{returndesc}, #{status}, #{memo}, #{del}, #{biztype}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{c01}, #{c02}, #{c03}, #{flowid}, #{stepid}, #{whid2})")
    int save(ZxBorrow zxBorrow);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZxBorrow> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zx_borrow t order by id")
    List<ZxBorrow> listAll();

}
