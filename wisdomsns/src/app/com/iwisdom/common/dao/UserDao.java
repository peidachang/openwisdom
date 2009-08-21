package com.iwisdom.common.dao;

import java.util.List;
import java.util.Map;

import com.iwisdom.common.entity.User;

public interface UserDao {
	public void save(User user);
	
	public User getUser(String email,String password);
	
	public List<Map> getUsers();
}
