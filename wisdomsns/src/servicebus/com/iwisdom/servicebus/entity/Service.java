package com.iwisdom.servicebus.entity;

import com.iwisdom.common.entity.User;

public class Service {
	private int serviceId ;
	private String serviceName ;
	private String serviceDesc ;
	private User provider ;
	private int status ;
	private String version ;
	private double price ;
	private int freeCredit ;
	private int serviceType ;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp expireDate ;
	private java.sql.Timestamp lastModify ;
	public User getProvider() {
		return provider;
	}
	public void setProvider(User provider) {
		this.provider = provider;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}
	public java.sql.Timestamp getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(java.sql.Timestamp expireDate) {
		this.expireDate = expireDate;
	}
	public java.sql.Timestamp getLastModify() {
		return lastModify;
	}
	public void setLastModify(java.sql.Timestamp lastModify) {
		this.lastModify = lastModify;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getFreeCredit() {
		return freeCredit;
	}
	public void setFreeCredit(int freeCredit) {
		this.freeCredit = freeCredit;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
}
