package com.iwisdom.common.dao.impl.jdbc;

import java.util.List;
import java.util.Map;
import com.iwisdom.common.dao.UserDao;
import com.iwisdom.common.dao.impl.BaseJdbcDao;
import com.iwisdom.common.entity.User;

public class UserJdbcDaoImpl extends BaseJdbcDao implements UserDao {

	public void save(User user) {
		

	}

	public List<Map> getUsers() {
		return null ;
	}

	public User getUser(String email, String password) {
		return null;
	}

}
