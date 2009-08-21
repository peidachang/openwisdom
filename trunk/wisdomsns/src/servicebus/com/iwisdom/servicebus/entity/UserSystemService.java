package com.iwisdom.servicebus.entity;

import com.iwisdom.common.entity.User;

public class UserSystemService {
	private int id ;
	private User user ;
	private SystemService systemService ;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp expireDate ;
	private java.sql.Timestamp lastModify ;
	private int status ;
	private String handler;
	public String getHandler() {
		return handler;
	}
	public void setHandler(String handler) {
		this.handler = handler;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SystemService getSystemService() {
		return systemService;
	}
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
