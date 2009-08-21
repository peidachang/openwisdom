package com.iwisdom.common.dao;

import com.iwisdom.common.entity.Role;

public interface RoleDao {
	public java.util.List<Role> getRoles();
	public Role  getRoleById(int roleId);
	public void save(Role role);
	
}
