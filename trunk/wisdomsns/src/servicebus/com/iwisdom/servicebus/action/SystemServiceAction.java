package com.iwisdom.servicebus.action;

import java.util.ArrayList;
import java.util.List;
import com.iwisdom.servicebus.entity.SystemService;
import com.iwisdom.servicebus.entity.UserSystemService;
import com.iwisdom.servicebus.service.ServiceService;
import com.opensymphony.xwork.ActionSupport;
import com.opensymphony.webwork.ServletActionContext;
import com.iwisdom.common.entity.User;
import com.iwisdom.common.util.Constants;
public class SystemServiceAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2955237829136745933L;
	private List<SystemService> systemServices;
	private List<UserSystemService> userSystemServices ;
	private ServiceService serviceService;
	private int serviceId;

	public List<UserSystemService> getUserSystemServices() {
		return userSystemServices;
	}
	public void setUserSystemServices(List<UserSystemService> userSystemServices) {
		this.userSystemServices = userSystemServices;
	}
	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public List<SystemService> getSystemServices() {
		return systemServices;
	}

	public void setSystemServices(List<SystemService> systemServices) {
		this.systemServices = systemServices;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public String listSystemService() {
		systemServices = serviceService.getSystemService();
		return SUCCESS;
	}

	public String openSystemService() {
		//serviceService.openSystemService((User)ServletActionContext.getRequest().getSession()
		//						.getAttribute(Constants.USER_SESSION), serviceService.getSystemServiceById(serviceId));
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION);
		UserSystemService userSystemService = new UserSystemService();
		//userSystemService.setUserId(user.getUserId());
		SystemService systemService = serviceService.getSystemServiceById(serviceId);
		//userSystemService.setSystemServiceId(systemService.getServiceId());
		userSystemService.setUser(user);
		userSystemService.setSystemService(systemService);
		userSystemService.setHandler("Jason");
		userSystemService.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		//user.getUserSystemServices().add(userSystemService);
		systemService.getUserSystemServices().add(userSystemService);
		serviceService.addSystemService(systemService);
		//serviceService.addUserSystemService(userSystemService);
		return SUCCESS;
	}
	public String listMySystemService(){
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION);
		
		userSystemServices = serviceService.getUserSystemService(user.getUserId());
		//userSystemServices = new ArrayList(user.getUserSystemServices());
		System.out.println("user systemservice list size = "+userSystemServices.size());
		for(int i=0;i<userSystemServices.size();i++){
			UserSystemService uss = userSystemServices.get(i);
			System.out.println("uss = "+uss.getSystemService());
		}
		return SUCCESS ;
	}

}
