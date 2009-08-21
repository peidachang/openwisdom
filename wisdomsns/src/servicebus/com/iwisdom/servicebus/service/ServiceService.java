package com.iwisdom.servicebus.service;

import java.util.List;

import com.iwisdom.common.entity.User;
import com.iwisdom.servicebus.entity.Service;
import com.iwisdom.servicebus.entity.SystemService;
import com.iwisdom.servicebus.entity.UserSystemService;

public interface ServiceService {
	/**
	 * 根据提供者ID取得服务列表
	 * @param providerId
	 * @return
	 */
	public List<Service> getServices(String providerId);
	/**
	 * 根据服务类别取得服务列表
	 * @param serviceType
	 * @return
	 */
	public List<Service> getServices(int serviceType);
	/**
	 * 取得系统服务列表
	 * @return
	 */
	public List<SystemService> getSystemService();
	/**
	 * 为用户ID 为userId 的用户开通服务Id为serviceId的系统服务
	 * @param userId
	 * @param serviceId
	 */
	public void openSystemService(User user,SystemService systemService);
	/**
	 * 通过服务Id 取得系统服务
	 * @param serviceId
	 * @return
	 */
	public SystemService getSystemServiceById(int serviceId);
	/**
	 * 保存用户系统服务关系
	 * @param userSystemService
	 */
	public void addUserSystemService(UserSystemService userSystemService);
	/**
	 * 取得用户Id 为userId的用户所开通的系统服务
	 * @param userId
	 * @return
	 */
	public List<UserSystemService> getUserSystemService(String userId);
	/**
	 * 取得用户开通某系统服务的情况
	 * @param systemServiceId
	 * @return
	 */
	public List<UserSystemService> getUserSystemService(int systemServiceId);
	/**
	 * 
	 * @param systemService
	 */
	public void addSystemService(SystemService systemService);
}
