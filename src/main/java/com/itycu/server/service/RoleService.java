package com.itycu.server.service;

import com.itycu.server.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
