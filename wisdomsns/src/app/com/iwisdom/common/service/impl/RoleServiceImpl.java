package com.iwisdom.common.service.impl;

import java.util.List;

import com.iwisdom.common.dao.RoleDao;
import com.iwisdom.common.entity.Role;
import com.iwisdom.common.service.RoleService;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao ;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public Role getRoleById(int roleId) {
		
		return roleDao.getRoleById(roleId);
	}

	public List<Role> getRoles() {
		return roleDao.getRoles();
	}

	public void saveRole(Role role) {
		roleDao.save(role);
		
	}

}
