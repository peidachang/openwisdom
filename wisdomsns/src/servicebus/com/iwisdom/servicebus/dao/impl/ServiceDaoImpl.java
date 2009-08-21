package com.iwisdom.servicebus.dao.impl;

import java.util.List;

import com.iwisdom.common.dao.impl.BaseHibernateDao;
import com.iwisdom.common.entity.User;
import com.iwisdom.servicebus.dao.ServiceDao;
import com.iwisdom.servicebus.entity.Service;
import com.iwisdom.servicebus.entity.SystemService;
import com.iwisdom.servicebus.entity.UserSystemService;

public class ServiceDaoImpl extends BaseHibernateDao implements ServiceDao{

	public List<Service> getServices(String providerId) {
		String hql = "from Service s  where s.provider.userId=?";
		return getHibernateTemplate().find(hql, providerId);
	}

	public List<Service> getServices(int serviceType) {
		String hql = "from Service s  where s.serviceType=?";
		return getHibernateTemplate().find(hql, serviceType);
	}

	public List<SystemService> getSystemServices() {
		String hql = "from SystemService";
		return getHibernateTemplate().find(hql);
	}

	public void openSystemService(User user, SystemService systemService) {
		//user.getSystemServices().add(systemService);
		getHibernateTemplate().merge(user);
		
	}

	public SystemService getSystemServiceById(int serviceId) {
		return (SystemService)getHibernateTemplate().get(SystemService.class,serviceId);
	}

	public void addUserSystemService(UserSystemService userSystemService) {
		getHibernateTemplate().saveOrUpdate(userSystemService);
		
	}

	public void addSystemService(SystemService systemService) {
		getHibernateTemplate().saveOrUpdate(systemService);
		
	}

	public List<UserSystemService> getUserSystemService(String userId) {
		String hql = "from UserSystemService uss where uss.user.userId=?";
		return getHibernateTemplate().find(hql, userId);
	}

	public List<UserSystemService> getUserSystemService(int systemServiceId) {
		String hql = "from UserSystemService uss where uss.systemService.systemServiceId=?";
		return getHibernateTemplate().find(hql, systemServiceId);
	}

}
