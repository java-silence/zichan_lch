package com.itycu.server.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.*;
import com.itycu.server.model.CgDingdans;

@Mapper
public interface CgDingdansDao {

    @Select("select * from cg_dingdans t where t.id = #{id}")
    CgDingdans getById(Long id);

    @Delete("delete from cg_dingdans where id = #{id}")
    int delete(Long id);

    @Delete("delete from cg_dingdans where pid = #{pid}")
    int delbypid(Long pid);

    int update(CgDingdans cgDingdans);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into cg_dingdans(pid, invid, cpgg, danwei, inum, iprice, taxrate, itax, imoney, itotal, cbatch, status, del, memo, ctype, c01, c02, c03, dhsl, rksl,yfdj,yfje) values(#{pid}, #{invid}, #{cpgg}, #{danwei}, #{inum}, #{iprice}, #{taxrate}, #{itax}, #{imoney}, #{itotal}, #{cbatch}, #{status}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{dhsl}, #{rksl}, #{yfdj}, #{yfje})")
    int save(CgDingdans cgDingdans);
    
    int count(@Param("params") Map<String, Object> params);

    List<CgDingdans> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);



    @Select("select * from cg_dingdans t order by id")
    List<CgDingdans> listAll();

    @Select("select t.*,t_inv.invname,inum-ifnull(dhsl,0) as wdsl,inum-ifnull(rksl,0) as wrsl from cg_dingdans t LEFT OUTER JOIN t_inv ON t.invid=t_inv.id where t.pid = #{pid}")
    List<CgDingdans> getBypid(Long pid);

    int saves(@Param("datasList") List<CgDingdans> datasList, @Param("pid") Long pid);

    @Update("update cg_dingdans set dhsl = ifnull(dhsl,0) + #{inum} where pid = #{pid} and invid= #{invid}")
    int updateDhsl(@Param("pid") Long pid , @Param("invid") Long invid, @Param("inum") BigDecimal inum);

    @Update("update cg_dingdans set dhsl = ifnull(dhsl,0) - #{inum} where pid = #{pid} and invid= #{invid}")
    int undoDhsl(@Param("pid") Long pid , @Param("invid") Long invid, @Param("inum") BigDecimal inum);

}
