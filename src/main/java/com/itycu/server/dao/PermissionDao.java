package com.itycu.server.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.*;

import com.itycu.server.model.Permission;

@Mapper
public interface PermissionDao {

	@Select("select * from sys_permission t order by t.sort")
	List<Permission> listAll();

	@Select("select * from sys_permission t where t.type = 1 order by t.sort")
	List<Permission> listParents();

	@Select("select * from sys_permission t where t.type = 1 and t.id>75 order by t.parentId,t.sort")
	List<Permission> listNavigation();

	@Select("select * from sys_permission t where parentId = #{id} and t.type = 1 order by t.sort")
	List<Permission> listChildMenu(Long id);

	@Select("select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = #{userId} order by p.sort")
	List<Permission> listByUserId(Long userId);

	@Select("select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = #{roleId} order by p.sort")
	List<Permission> listByRoleId(Long roleId);

	@Select("select * from sys_permission t where t.id = #{id}")
	Permission getById(Long id);

//	@Insert("insert into sys_permission(parentId, name, css, href, type, permission, sort) values(#{parentId}, #{name}, #{css}, #{href}, #{type}, #{permission}, #{sort})")
	@Insert("insert into sys_permission(parentId, name, css, cssapp, href, hrefapp, type, typeapp, permission, sort, memo, biztype, c01, c02, c03, createby, createTime, updateby, updateTime) values(#{parentId}, #{name}, #{css}, #{cssapp}, #{href}, #{hrefapp}, #{type}, #{typeapp}, #{permission}, #{sort}, #{memo}, #{biztype}, #{c01}, #{c02}, #{c03}, #{createby}, #{createTime}, #{updateby}, #{updateTime})")
	int save(Permission permission);

	@Update("update sys_permission t set parentId = #{parentId}, name = #{name}, css = #{css}, cssapp = #{cssapp},href = #{href},hrefapp = #{hrefapp}, type = #{type},typeapp = #{typeapp}, permission = #{permission}, sort = #{sort}, memo = #{memo}, biztype = #{biztype}, c01 = #{c01}, c02 = #{c02}, c03 = #{c03}, updateby = #{updateby}, updateTime = #{updateTime} where t.id = #{id}")
	int update(Permission permission);

	@Delete("delete from sys_permission where id = #{id}")
	int delete(Long id);

	@Delete("delete from sys_permission where parentId = #{id}")
	int deleteByParentId(Long id);

	@Delete("delete from sys_role_permission where permissionId = #{permissionId}")
	int deleteRolePermission(Long permissionId);

	@Select("select ru.userId from sys_role_permission rp inner join sys_role_user ru on ru.roleId = rp.roleId where rp.permissionId = #{permissionId}")
	Set<Long> listUserIds(Long permissionId);

	//判断某一用户是否有某一权限
	@Select("select count(1) from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = #{userId} and p.permission=#{permission}")
	int hasPermission(@Param("userId") Long userId, @Param("permission") String permission);
}
