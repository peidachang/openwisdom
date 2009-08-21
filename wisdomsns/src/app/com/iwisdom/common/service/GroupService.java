package com.iwisdom.common.service;

import com.iwisdom.common.entity.Group;

public interface GroupService {
	public java.util.List<Group> getGroups();
	public Group getGroupById(int groupId);
}
