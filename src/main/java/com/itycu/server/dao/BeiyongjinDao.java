package com.itycu.server.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.itycu.server.model.Beiyongjin;

@Mapper
public interface BeiyongjinDao {

    //@Select("select * from cw_beiyongjin t where t.id = #{id}")
    Beiyongjin getById(Long id);
    @Select("select * from cw_beiyongjin t where t.bussid = #{bussid} order by id desc limit 1 ")
    Beiyongjin getBybussid(Long id);

    @Delete("delete from cw_beiyongjin where id = #{id}")
    int delete(Long id);

    int update(Beiyongjin beiyongjin);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into cw_beiyongjin(ccode, ddate, busstype, csource, bussid, deptid, clid, clbm, dizhi, jsr, zhichu, shouru, yue, guo, fan, qita, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03) values(#{ccode}, #{ddate}, #{busstype}, #{csource}, #{bussid}, #{deptid}, #{clid}, #{clbm}, #{dizhi}, #{jsr}, #{zhichu}, #{shouru}, #{yue}, #{guo}, #{fan}, #{qita}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Beiyongjin beiyongjin);
    
    int count(@Param("params") Map<String, Object> params);

    List<Beiyongjin> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from cw_beiyongjin t order by id")
    List<Beiyongjin> listAll();

    @Select("select * from cw_beiyongjin t where t.Ccode = #{Ccode}")
    Beiyongjin getByCcode(String Ccode);

    @Select("select max(ccode) from cw_beiyongjin t")
    String getMaxCcode();

    @Select("select byj.yue from cw_beiyongjin byj ORDER BY createTime DESC LIMIT 1")
    BigDecimal getYue();

    @Update("update cw_beiyongjin set yue = yue + #{jine} where id > #{id}")
    int updateYue(@Param("jine") BigDecimal jine, @Param("id") Long id);
}
