package com.itycu.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itycu.server.model.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeptDao {

    @Select("select * from t_dept t where t.id = #{id}")
    Dept getById(Long id);

    @Delete("delete from t_dept where id = #{id}")
    int delete(Long id);

    int update(Dept dept);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_dept(deptcode, deptname,jx, pid, phone, address, leader, createTime, updateTime, status, del, memo,zhfhgl, c01, c02, c03,ctype) values(#{deptcode}, #{deptname},#{jx}, #{pid}, #{phone}, #{address}, #{leader}, #{createTime}, #{updateTime}, #{status}, #{del}, #{memo},#{zhfhgl}, #{c01}, #{c02}, #{c03},#{ctype})")
    int save(Dept dept);
    
    int count(@Param("params") Map<String, Object> params);

    List<Dept> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_dept t order by id")
    List<Dept> listDepts();

    List<Dept> listDeptxm(@Param("params") Map<String, Object> params);

    @Select("select * from t_dept t where deptcode like #{deptcode}  order by id")
    List<Dept> jtlistDept(String deptcode);

    @Select("select * from t_dept t where t.pid=#{pid} order by deptcode")
    List<Dept> listTopDepts(Long pid);

    @Select("select * from t_dept t where t.ctype='收费站' order by deptcode")
    List<Dept> listzhan();

    @Select("SELECT * FROM t_dept d WHERE id in (SELECT id FROM t_dept WHERE deptcode LIKE concat(#{id},'%'))")
    List<Dept> listByIdAndChildIds(@Param("id") Long id);

    List<Dept> listDeptSAndParent(@Param("deptIdList") List<Long> deptIdList);

    List<Dept> queryAllDept();

    @Select("select * from t_dept t where t.pid = #{pid} and zhfhgl = 3")
    List<Dept> listChildGlDept(@Param("pid") Long pid, @Param("zhfhgl") Long zhfhgl);

    @Select("select * from t_dept t where t.pid = #{pid}")
    List<Dept> listByPid(@Param("pid") Long pid);


    /**
     * 查询盘点单位的简写
     * @param checkId
     * @return
     */
    String  getJxById(@Param("checkId") Long checkId);


    Integer  queryMaxBh(@Param("jx") String jx);


    List<Dept> queryAllSonDeptByPid(@Param("pid") Long pid);

    Dept  queryDeptByPid(@Param("pid") Long pid);

    @Select("select * from t_dept t where t.jx = #{jx} and t.c03 = #{c03} limit 0,1")
    Dept deptByJx(@Param("jx") String jx,@Param("c03") String c03);
}
