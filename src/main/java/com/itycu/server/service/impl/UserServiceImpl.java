package com.itycu.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dao.UserDao;
import com.itycu.server.dto.SysUserDto;
import com.itycu.server.dto.UserDto;
import com.itycu.server.model.SysUser;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.UserService;
import com.itycu.server.utils.ExcelUtil;
import com.itycu.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserDao userDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private PermissionDao permissionDao;

	@Override
	@Transactional
	public SysUser saveUser(UserDto userDto) {
		SysUser user = userDto;
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setStatus(SysUser.Status.VALID);

		if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:user:querydept") > 0){
			user.setDeptid(UserUtil.getLoginUser().getDeptid());
		}
		userDao.save(user);
		List<Long> roles=new ArrayList<Long>();
		roles.add(18L);
		if(userDto.getRoleIds() ==null) userDto.setRoleIds(roles);
		if(userDto.getRoleIds().size() ==0) userDto.setRoleIds(roles);
		saveUserRoles(user.getId(), userDto.getRoleIds());

		log.debug("新增用户{}", user.getUsername());
		return user;
	}

	private void saveUserRoles(Long userId, List<Long> roleIds) {
		if (roleIds != null) {
			userDao.deleteUserRole(userId);
			if (!CollectionUtils.isEmpty(roleIds)) {
				userDao.saveUserRoles(userId, roleIds);
			}
		}
	}

	@Override
	public SysUser getUser(String username) {
		return userDao.getUser(username);
	}

	@Override
	public int changePassword(String username, String oldPassword, String newPassword) {
		SysUser u = userDao.getUser(username);
		if (u == null) {
			throw new IllegalArgumentException("用户不存在");
		}

		if (!passwordEncoder.matches(oldPassword, u.getPassword())) {
			throw new IllegalArgumentException("旧密码错误");
		}
		log.debug("修改{}的密码", username);
	  return 	userDao.changePassword(u.getId(), passwordEncoder.encode(newPassword));


	}

	@Override
	@Transactional
	public SysUser updateUser(UserDto userDto) {
		userDao.update(userDto);
		saveUserRoles(userDto.getId(), userDto.getRoleIds());

		return userDto;
	}

	@Override
	public void export(PageTableRequest request, HttpServletResponse response) {
		List<SysUserDto> sysUserDtos = userDao.listByCondition(request.getParams());
		String fileName = "人员档案";
		if (!CollectionUtils.isEmpty(sysUserDtos)) {
			String[] headers = new String[]{"姓名","登录名","部门","状态"};

			List<Object[]> datas = new ArrayList<>(sysUserDtos.size());
			for (SysUserDto sysUserDto : sysUserDtos) {
				Object[] objects = new Object[]{sysUserDto.getNickname(),sysUserDto.getUsername(),sysUserDto.getDeptname(),
						sysUserDto.getStatus()};
				datas.add(objects);
			}

			ExcelUtil.excelExport(
					fileName, headers,
					datas, response);
		}
	}
}
