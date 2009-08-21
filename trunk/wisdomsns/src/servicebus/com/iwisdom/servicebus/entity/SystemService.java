package com.iwisdom.servicebus.entity;

import java.util.HashSet;
import java.util.Set;

import com.iwisdom.common.entity.Role;
import com.iwisdom.common.entity.User;
public class SystemService {
	private int serviceId ;
	private String serviceName ;
	private String serviceDesc ;
	private String provider ;
	private int status ;
	private String version ;
	private double price ;
	private int freeCredit ;
	private int serviceType ;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp expireDate ;
	private java.sql.Timestamp lastModify ;
	private Set<UserSystemService> userSystemServices = new HashSet<UserSystemService>();
	
	public Set<UserSystemService> getUserSystemServices() {
		return userSystemServices;
	}
	public void setUserSystemServices(Set<UserSystemService> userSystemServices) {
		this.userSystemServices = userSystemServices;
	}
	public void addUserSystemService(UserSystemService userSystemService){
		this.userSystemServices.add(userSystemService);
	}
	/**
	private Set<User> users = new HashSet<User>();
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void addUser(User user){
		this.users.add(user);
	}
	*/
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
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
