package com.iwisdom.common.dao.impl.hibernate;

import java.util.List;
import java.util.Map;
import com.iwisdom.common.dao.UserDao;
import com.iwisdom.common.dao.impl.BaseHibernateDao;
import com.iwisdom.common.entity.User;

public class UserDaoImpl extends BaseHibernateDao implements UserDao {

	public void save(User user) {
		getHibernateTemplate().save(user);
	}

	public List<Map> getUsers() {
		return null;
	}

	public User getUser(String email, String password) {
		String hql = "from User user where user.email=? and user.password=?";
		String[] param = {email,password};
		List users = getHibernateTemplate().find(hql, param);
		if(users.size()>0)
			return (User)users.get(0);
		
		return null ;
	}
	
}
