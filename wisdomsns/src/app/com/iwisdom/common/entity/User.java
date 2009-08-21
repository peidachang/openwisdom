package com.iwisdom.common.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.iwisdom.servicebus.entity.Service;
import com.iwisdom.servicebus.entity.SystemService;
import com.iwisdom.servicebus.entity.UserSystemService;
public class User {
	private String userId;
	private String userName;
	private String password;
	private int gender;
	private String email;
	private String mobile ;
	private String location ;
	private Timestamp birthday;
	private Timestamp createDate ;
	private String controlCode;
	private Group group ;
	//private Set<SystemService> systemServices = new HashSet<SystemService>();
	private Set<UserSystemService> userSystemServices = new HashSet<UserSystemService>();
	private Set<Role> roles = new HashSet<Role>();
	
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
	public Set<SystemService> getSystemServices() {
		return systemServices;
	}
	public void setSystemServices(Set<SystemService> systemServices) {
		this.systemServices = systemServices;
	}
	public void addSystemService(SystemService systemService){
		this.systemServices.add(systemService);
	}
	*/
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getControlCode() {
		return controlCode;
	}
	public void setControlCode(String controlCode) {
		this.controlCode = controlCode;
	}
	public void addRole(Role role){
		this.roles.add(role);
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	
}
