package com.iwisdom.common.service;

import com.iwisdom.common.entity.User;

import java.util.List;
import java.util.Map;
public interface UserService {
	
	public void saveUser(User user);
	
	public User getUser(User user);
	
	public User getUser(String email,String password);
	
	//public User getUser(String msisdn,String password);
	public List<Map> getUsers();
}
