package com.iwisdom.common.dao;

import com.iwisdom.common.entity.Group;

public interface GroupDao {
	public java.util.List<Group> getGroups();
	public Group getGroupById(int groupId);
}
