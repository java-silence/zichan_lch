package com.itycu.server.dao;

import com.itycu.server.model.Chda;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChdaDao {

    @Select("select t.*,t_invclass.cname as invcname from t_inv t LEFT OUTER JOIN t_invclass ON t.invcid=t_invclass.id where t.id = #{id}")
    Chda getById(Long id);

    @Delete("delete from t_inv where id = #{id}")
    int delete(Long id);

    int update(Chda chda);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_inv(xtid, lbid, invcode, invname, invabbname, invstd, invcid, positionid, iweight, ivolume, iprice, viprice, icost, safenum, topnum, lownum, unit1, unit2, pic, barcode, pid, bomid, tdesc, createby, createTime, updateby, updateTime, auditby, auditTime, status, del, memo, ctype, c01, c02, c03,ppath,deptid,printgg) values(#{xtid}, #{lbid}, #{invcode}, #{invname}, #{invabbname}, #{invstd}, #{invcid}, #{positionid}, #{iweight}, #{ivolume}, #{iprice}, #{viprice}, #{icost}, #{safenum}, #{topnum}, #{lownum}, #{unit1}, #{unit2}, #{pic}, #{barcode}, #{pid}, #{bomid}, #{tdesc}, #{createby}, #{createTime}, #{updateby}, #{updateTime}, #{auditby}, #{auditTime}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{ppath}, #{deptid},#{printgg})")
    int save(Chda chda);
    
    int count(@Param("params") Map<String, Object> params);

    List<Chda> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_inv t order by id")
    List<Chda> listAll();

    //根据品名和规格提取物料ID
    @Select("select id from t_inv where invname = #{invname} and invstd=#{cpgg} limit 0,1")
    Long getidbyname(@Param("invname") String invname, @Param("cpgg") String cpgg);

    //根据品名提取物料ID
    @Select("select id from t_inv where invname = #{invname} limit 0,1")
    Long getidbyinvname(@Param("invname") String invname);

    @Select("select invcid from t_inv where id=#{id}")
    Long getinvcid(Long id);

    //根据品名和规格获取存货档案，用来判断是否已存在
    @Select("select id from t_inv where invname = #{invname} and invstd=#{cpgg}")
    List<Chda> listbyNameCpgg(@Param("invname") String invname, @Param("cpgg") String cpgg);

    @Select("select * from t_inv t where invcid = #{invcid} order by id")
    List<Chda> listByinvcid(Long invcid);

    List<Chda> listByCondition(@Param("params") Map<String, Object> params);

    @Select("select invname from t_inv t where invcid = #{invcid} group by invcid")
    String invnameByChfl(Long invcid);
}
