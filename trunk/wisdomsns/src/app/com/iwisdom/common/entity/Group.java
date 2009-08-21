package com.iwisdom.common.entity;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private int groupId ;
	private String groupName ;
	private String remark ;
	private Set<User> users = new HashSet<User>();
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void addUser(User user){
		this.users.add(user);
	}
}
