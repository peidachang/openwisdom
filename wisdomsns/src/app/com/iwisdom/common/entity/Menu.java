package com.iwisdom.common.entity;

public class Menu {
	private int menuId ;
	private String menuName ;
	private String menuUrl ;
	private int pid ;
	private String remark ;
	private int status ;
	private java.util.Set<Role> roles = new java.util.HashSet<Role>();
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public java.util.Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(java.util.Set<Role> roles) {
		this.roles = roles;
	}
	public void addRole(Role role){
		this.roles.add(role);
	}
	
}
