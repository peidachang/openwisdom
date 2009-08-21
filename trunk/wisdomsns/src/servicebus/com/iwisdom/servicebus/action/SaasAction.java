package com.iwisdom.servicebus.action;

import java.util.List;

import com.iwisdom.common.entity.User;
import com.iwisdom.common.util.Constants;
import com.iwisdom.servicebus.entity.Service;
import com.iwisdom.servicebus.service.ServiceService;
import com.opensymphony.xwork.ActionSupport;
public class SaasAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2556968244592524445L;
	private List<Service> services ;
	private ServiceService serviceService ;
	public String listSaas(){
		services = serviceService.getServices(Constants.SAAS_SERVICE_ID);
		return SUCCESS ;
	}

	public String registrateSaas(){
		
		return SUCCESS ;
	}
}
