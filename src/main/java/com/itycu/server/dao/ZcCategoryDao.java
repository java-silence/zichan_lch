package com.itycu.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.itycu.server.model.ZcCategory;

@Mapper
public interface ZcCategoryDao {

    //@Select("select * from zc_category t where t.id = #{id}")
    ZcCategory getById(Long id);

    @Delete("delete from zc_category where id = #{id}")
    int delete(Long id);

    int update(ZcCategory zcCategory);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into zc_category(cat_code, pid, code, name, card_style, accountant_code, accountant_name, del, bz, c01, c02, c03, create_by, update_by, create_time, update_time) values(#{catCode}, #{pid}, #{code}, #{name}, #{cardStyle}, #{accountantCode}, #{accountantName}, #{del}, #{bz}, #{c01}, #{c02}, #{c03}, #{createBy}, #{updateBy}, #{createTime}, #{updateTime})")
    int save(ZcCategory zcCategory);
    
    int count(@Param("params") Map<String, Object> params);

    List<ZcCategory> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from zc_category t where del = 0 order by id")
    List<ZcCategory> listAll();

    @Select("select count(1) from zc_category t where pid = #{pid} and del = 0 order by id")
    int listByPid(Long pid);

    @Select("select * from zc_category t where t.Ccode = #{Ccode}")
    ZcCategory getByCcode(String Ccode);

    @Select("select max(ccode) from zc_category t")
    String getMaxCcode();
}
