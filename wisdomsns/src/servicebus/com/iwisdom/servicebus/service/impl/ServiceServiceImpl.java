package com.iwisdom.servicebus.service.impl;

import java.util.List;

import com.iwisdom.common.entity.User;
import com.iwisdom.servicebus.dao.ServiceDao;
import com.iwisdom.servicebus.entity.Service;
import com.iwisdom.servicebus.entity.SystemService;
import com.iwisdom.servicebus.entity.UserSystemService;
import com.iwisdom.servicebus.service.ServiceService;

public class ServiceServiceImpl implements ServiceService{
	private ServiceDao serviceDao ;
	
	public ServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public List<Service> getServices(String providerId) {
		return serviceDao.getServices(providerId);
	}

	public List<Service> getServices(int serviceType) {
		return serviceDao.getServices(serviceType);
	}

	public List<SystemService> getSystemService() {
		return serviceDao.getSystemServices();
	}

	public void openSystemService(User user, SystemService systemService) {
		
		serviceDao.openSystemService(user, systemService);
		
	}

	public SystemService getSystemServiceById(int serviceId) {
		
		return serviceDao.getSystemServiceById(serviceId);
	}

	public void addUserSystemService(UserSystemService userSystemService) {
		serviceDao.addUserSystemService(userSystemService);
		
	}

	public void addSystemService(SystemService systemService) {
		serviceDao.addSystemService(systemService);
	}

	public List<UserSystemService> getUserSystemService(String userId) {
		return serviceDao.getUserSystemService(userId);
	}

	public List<UserSystemService> getUserSystemService(int systemServiceId) {
			return serviceDao.getUserSystemService(systemServiceId);
	}

}
