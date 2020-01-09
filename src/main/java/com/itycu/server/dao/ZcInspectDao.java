package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import com.itycu.server.app.vo.xunjian.XunJianVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcInspect;

@Mapper
public interface ZcInspectDao {

    //@Select("select * from zc_inspect t where t.id = #{id}")
    ZcInspect getById(Long id);

    @Delete("delete from zc_inspect where id = #{id}")
    int delete(Long id);

    int update(ZcInspect zcInspect);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_inspect(zc_id, days, last_check_time, del, bz, create_by, update_by, create_time, update_time, check_time, check_user_id, check_username, status, code,check_dept_id,check_dept_name) values(#{zcId}, #{days}, #{lastCheckTime}, #{del}, #{bz}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime}, #{checkTime}, #{checkUserId}, #{checkUsername}, #{status}, #{code},#{checkDeptId},#{checkDeptName})")
    int save(ZcInspect zcInspect);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcInspect> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_inspect t order by id")
    List<ZcInspect> listAll();

    @Select("select * from zc_inspect t where t.Ccode = #{Ccode}")
    ZcInspect getByCcode(String Ccode);

    @Select("select max(ccode) from zc_inspect t")
    String getMaxCcode();


    List<XunJianVO>  getByDeptId(@Param("deptId") long deptId);


    int saveZcInspectRel(Map<String, Object> params);


    com.itycu.server.app.model.AppXunJianReal  queryZcRealIdByZcId(@Param("zc_id") long zcId);


    Integer queryXunjianAgainId(@Param("zcId") long  zcId);
}
