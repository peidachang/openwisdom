package com.iwisdom.servicebus.dao;

import java.util.List;

import com.iwisdom.common.entity.User;
import com.iwisdom.servicebus.entity.Service;
import com.iwisdom.servicebus.entity.SystemService;
import com.iwisdom.servicebus.entity.UserSystemService;

public interface ServiceDao {
	public List<Service> getServices(String providerId);
	public List<Service> getServices(int serviceType);
	public List<SystemService> getSystemServices();
	public void openSystemService(User user, SystemService systemService);
	public SystemService getSystemServiceById(int serviceId);
	public void addUserSystemService(UserSystemService userSystemService);
	public void addSystemService(SystemService systemService);
	public List<UserSystemService> getUserSystemService(String userId);
	public List<UserSystemService> getUserSystemService(int systemServiceId);
}
