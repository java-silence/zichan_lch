package com.itycu.server.service;

import com.itycu.server.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
