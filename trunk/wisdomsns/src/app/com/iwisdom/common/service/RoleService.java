package com.iwisdom.common.service;

import com.iwisdom.common.entity.Role;

public interface RoleService {
	public java.util.List<Role> getRoles();
	public Role  getRoleById(int roleId);
	public void saveRole(Role role);
}
