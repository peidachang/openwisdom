package com.iwisdom.common.dao.impl.hibernate;

import java.util.List;

import com.iwisdom.common.dao.RoleDao;
import com.iwisdom.common.dao.impl.BaseHibernateDao;
import com.iwisdom.common.entity.Role;

public class RoleDaoImpl extends BaseHibernateDao implements RoleDao {

	public Role getRoleById(int roleId) {
		
		return (Role)getHibernateTemplate().get(Role.class, roleId);
	}

	public List<Role> getRoles() {
		String hql = "from Role";
		return getHibernateTemplate().find(hql);
	}

	public void save(Role role) {
		
		getHibernateTemplate().merge(role);
	}

}
