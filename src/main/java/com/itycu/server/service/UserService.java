package com.itycu.server.service;

import com.itycu.server.dto.UserDto;
import com.itycu.server.model.SysUser;
import com.itycu.server.page.table.PageTableRequest;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	int  changePassword(String username, String oldPassword, String newPassword);

	void export(PageTableRequest request, HttpServletResponse response);

}
