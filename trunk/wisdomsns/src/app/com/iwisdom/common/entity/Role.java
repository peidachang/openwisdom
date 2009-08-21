package com.iwisdom.common.entity;

public class Role {
	private int roleId ;
	private String roleName ;
	private String remark ;
	private java.util.Set<Menu> menus = new java.util.HashSet<Menu>() ;
	private java.util.Set<User> users = new java.util.HashSet<User>() ;
	public java.util.Set<User> getUsers() {
		return users;
	}
	public void setUsers(java.util.Set<User> users) {
		this.users = users;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public java.util.Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(java.util.Set<Menu> menus) {
		this.menus = menus;
	}
	public void addMenu(Menu menu){
		this.menus.add(menu);
	}
	public void addUser(User user){
		this.users.add(user);
	}
	
}
