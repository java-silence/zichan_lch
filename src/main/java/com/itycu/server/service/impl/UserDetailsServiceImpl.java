package com.itycu.server.service.impl;

import java.util.List;

import com.itycu.server.dao.PermissionDao;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.model.Permission;
import com.itycu.server.model.SysUser;
import com.itycu.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * spring security登陆处理<br>
 * <p>
 * 密码校验请看文档（02 框架及配置），第三章第4节
 *
 * @author 小威老师 xiaoweijiagou@163.com
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = userService.getUser(username);
		if (sysUser == null) {
			throw new AuthenticationCredentialsNotFoundException("用户名不存在");
		} else if (sysUser.getStatus() == SysUser.Status.LOCKED) {
			throw new LockedException("用户被锁定,请联系管理员");
		} else if (sysUser.getStatus() == SysUser.Status.DISABLED) {
			throw new DisabledException("用户已作废");
		}

		LoginUser loginUser = new LoginUser();
		BeanUtils.copyProperties(sysUser, loginUser);

		List<Permission> permissions = permissionDao.listByUserId(sysUser.getId());
		loginUser.setPermissions(permissions);

		return loginUser;
	}

}
