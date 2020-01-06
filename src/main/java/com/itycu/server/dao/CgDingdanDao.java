package com.itycu.server.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.*;
import com.itycu.server.model.CgDingdan;

@Mapper
public interface CgDingdanDao {

    //@Select("select * from cg_dingdan t where t.id = #{id}")
    CgDingdan getById(Long id);

    @Select("select * from cg_dingdan t where t.Ccode = #{Ccode}")
    CgDingdan getByCcode(String Ccode);

    @Select("select max(ccode) from cg_dingdan t")
    String getMaxCcode();

    @Delete("delete from cg_dingdan where id = #{id}")
    int delete(Long id);

    int update(CgDingdan cgDingdan);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into cg_dingdan(ccode, ddate, busstype, csource, bussid, jglx, fkfs, whid, deptid, userid, ksid, inum, taxrate, tax, imoney, itotal, yfdj, yfje, yffkid, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03, flowid, stepid, dhsl, dhje, rksl, ykfp, yfkje,clbm,telephone) values(#{ccode}, #{ddate}, #{busstype}, #{csource}, #{bussid}, #{jglx}, #{fkfs}, #{whid}, #{deptid}, #{userid}, #{ksid}, #{inum}, #{taxrate}, #{tax}, #{imoney}, #{itotal}, #{yfdj}, #{yfje}, #{yffkid}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{flowid}, #{stepid}, #{dhsl}, #{dhje}, #{rksl}, #{ykfp}, #{yfkje},#{clbm},#{telephone})")
    int save(CgDingdan cgDingdan);
    
    int count(@Param("params") Map<String, Object> params);

    List<CgDingdan> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from cg_dingdan t order by id")
    List<CgDingdan> listAll();

    @Update("update cg_dingdan set yfkje = ifnull(yfkje,0) + #{fkje} where id = #{id}")
    int addyfkje(@Param("id") Long id, @Param("fkje") BigDecimal fkje);

    @Update("update cg_dingdan set yfkje = ifnull(yfkje,0) - #{fkje} where id = #{id}")
    int undoyfkje(@Param("id") Long id, @Param("fkje") BigDecimal fkje);



}
