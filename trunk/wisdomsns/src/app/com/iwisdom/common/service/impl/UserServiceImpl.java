package com.iwisdom.common.service.impl;

import java.util.List;
import java.util.Map;

import com.iwisdom.common.dao.UserDao;
import com.iwisdom.common.entity.User;
import com.iwisdom.common.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao ;
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void saveUser(User user) {
		userDao.save(user);
	}

	public List<Map> getUsers() {
		return userDao.getUsers();
	}

	public User getUser(User user) {
		return null;
	}

	public User getUser(String email, String password) {
		return userDao.getUser(email, password);
	}

}
