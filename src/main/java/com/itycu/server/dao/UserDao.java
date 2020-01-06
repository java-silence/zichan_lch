package com.itycu.server.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dto.SysUserDto;
import com.itycu.server.model.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime,deptid, del, memo, ctype, c01, c02, c03, rzsj, zytc, yuexin, jjlxr, jjlxrdh, zjzp, jlfj, zzzt, lzsj, lzyy) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now(),#{deptid}, #{del}, #{memo}, #{ctype}, #{c01}, #{c02}, #{c03}, #{rzsj}, #{zytc}, #{yuexin}, #{jjlxr}, #{jjlxrdh}, #{zjzp}, #{jlfj}, #{zzzt}, #{lzsj}, #{lzyy})")
	int save(SysUser user);

	@Delete("delete from sys_user where id = #{id}")
	int delete(Long id);

	@Select("select * from v_sys_user t where t.id = #{id}")
	SysUserDto getById(Long id);

	@Select("select * from sys_user t where t.username = #{username}")
	SysUser getUser(String username);

	@Update("update sys_user t set t.password = #{password} where t.id = #{id}")
	int changePassword(@Param("id") Long id, @Param("password") String password);

	Integer count(@Param("params") Map<String, Object> params);

	List<SysUserDto> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
                          @Param("limit") Integer limit);

	List<SysUserDto> listByCondition(@Param("params") Map<String, Object> params);

	@Delete("delete from sys_role_user where userId = #{userId}")
	int deleteUserRole(Long userId);

	int saveUserRoles(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

	int update(SysUser user);


	@Update("update sys_user t set t.clientid = #{clientid} where t.id = #{id}")
	int saveClientInfo(@Param("clientid") String clientid, @Param("id") Long id);

	@Update("update sys_user t set t.clientid = '' where t.clientid = #{clientid}")
	int clearClientInfo(@Param("clientid") String clientid);

	//根据角色名提取用户
	@Select("select id,nickname from sys_role_user ru LEFT JOIN sys_user u on ru.userId = u.id where roleId = #{roleId}")
	List<SysUser> getUserByRoleid(Long roleId);

	//根据角色名和所在部门提取用户(仅以符合条件的第一个)
	@Select("select id,nickname from sys_role_user ru LEFT JOIN sys_user u on ru.userId = u.id where roleId = #{roleid} and u.deptid=#{deptid} LIMIT 0,1")
	SysUser getUserByRoleDept(@Param("roleid") Long roleid, @Param("deptid") Long deptid);

	//根据角色名和所在部门提取用户列表
	@Select("select id,nickname from sys_role_user ru LEFT JOIN sys_user u on ru.userId = u.id where roleId = #{roleid} and u.deptid=#{deptid} ")
	List<SysUser> getUsersByRoleDept(@Param("roleid") Long roleid, @Param("deptid") Long deptid);

	@Select("select *,d.deptname from sys_user u left join t_dept d on u.deptid = d.id where u.id = #{id}")
	SysUserDto getDeptnameById(Long id);

	@Select("select * from v_sys_user t order by id")
	List<SysUserDto> listUsers();

	@Select("select * from v_sys_user t where t.deptid =#{deptid} order by id")
	List<SysUserDto> listUsersByDept(Long deptid);

    List<HashMap<String,Object>> findByRoleIdAndDeptCode(@Param("memidByFlowStep") Long memidByFlowStep, @Param("deptcode")String deptcode);

	@Select("select * from v_sys_user t where t.deptid =#{deptid} limit 0,1")
	SysUserDto getUserByDeptTop1(Long deptid);


	SysUserDto getByDeptId(@Param("deptid") String deptid);
}
