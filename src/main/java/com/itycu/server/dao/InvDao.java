package com.itycu.server.dao;

import com.itycu.server.model.Inv;
import com.itycu.server.model.SelectOption;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface InvDao {

    @Select("select * from t_inv t where t.id = #{id}")
    Inv getById(Long id);

    @Select("select * from t_inv t where t.xtid = #{xtid}")
    List<Inv> getByXtid(Long xtid);

    @Select("select id , invname as name from t_inv t where t.invcid = #{invcid}")
    List<SelectOption> optionsbyinvcid(Long invcid);

    @Delete("delete from t_inv where id = #{id}")
    int delete(Long id);

    int update(Inv inv);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_inv(xtid, invcode, invname, invabbname, invstd, invcid, positionid, iweight, ivolume, iprice, viprice, icost, safenum, topnum, lownum, unit1, unit2, pic, barcode, pid, bomid, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03) values(#{xtid}, #{invcode}, #{invname}, #{invabbname}, #{invstd}, #{invcid}, #{positionid}, #{iweight}, #{ivolume}, #{iprice}, #{viprice}, #{icost}, #{safenum}, #{topnum}, #{lownum}, #{unit1}, #{unit2}, #{pic}, #{barcode}, #{pid}, #{bomid}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03})")
    int save(Inv inv);


    
    int count(@Param("params") Map<String, Object> params);

    List<Inv> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_inv t order by id")
    List<Inv> listAll();

    @Select("select invname as id,invname as name from t_inv t where t.invcid = #{invcid} group by invname")
    List<SelectOption> getinvGroupInvname(Long invcid);

    List<SelectOption> getinvGroupInvnameByInvcid(@Param("invcidList") List<Long> invcidList);

    @Select("select id as id,invname as name from t_inv t where t.ppath like concat(#{ppath},'%') ")
    List<SelectOption> getinvbyppath(String ppath);

    @Select("select t.* from t_inv t where t.invname = #{invname} and t.invstd = #{cpgg} order by invname,invstd")
    List<Inv> getInvByNameCpgg(@Param("invname") String invname, @Param("cpgg") String cpgg);

    @Select("select DISTINCT invstd from t_inv t where invname = #{invname} and  invstd like concat('%', #{cpgg}, '%') ")
    List<String> listcpgg(@Param("invname") String invname, @Param("cpgg") String cpgg);

    @Select("select DISTINCT invstd from t_inv t where invname = #{invname}")
    List<String> listCpggByInvname(@Param("invname") String invname);

    @Select("select DISTINCT invname from t_inv t where invname like concat('%', #{invname}, '%') and ppath like concat(#{ppath},'%')")
    List<String> listInvname(@Param("invname") String invname, @Param("ppath") String ppath);

    int batchUpdate(Inv inv);
}
