package com.itycu.server.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcBuy;

@Mapper
public interface ZcBuyDao {

    //@Select("select * from zc_buy t where t.id = #{id}")
    ZcBuy getById(@Param("id") Long buyId);

    @Delete("delete from zc_buy where id = #{id}")
    int delete(Long id);

    int update(ZcBuy zcBuy);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_buy(buyNum,company_name, sy_dept_id, deptCode,apply_time, apply_user_id, gl_dept_id, flowid, stepid, status, fileName, fileUrl,bz,del, create_by, update_by, create_time, update_time) values(#{buyNum},#{companyName}, #{syDeptId}, #{deptCode}, #{applyTime}, #{applyUserId}, #{glDeptId}, #{flowid}, #{stepid}, #{status},#{fileName},#{fileUrl},#{bz}, #{del}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime})")
    int save(ZcBuy zcBuy);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcBuy> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_buy t order by id")
    List<ZcBuy> listAll();

    @Select("select * from zc_buy t where t.Ccode = #{Ccode}")
    ZcBuy getByCcode(String Ccode);

    @Select("select max(ccode) from zc_buy t")
    String getMaxCcode();

    void updateStatus(@Param("params") Map<String, Object> params);

    List<Map<String,Object>> listZcBuy(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    HashMap<String,Object> getZcBuyDetail(@Param("buyId") Long buyId);

    int countByDeptThisYear(@Param("deptcode") String deptcode);

    int countByZcBuyId(@Param("zcBuyId") Long zcBuyId);


    int queryBuyCountById(@Param("id") long id);
}
