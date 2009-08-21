package com.iwisdom.common.service.impl;

import java.util.List;

import com.iwisdom.common.dao.GroupDao;
import com.iwisdom.common.entity.Group;
import com.iwisdom.common.service.GroupService;

public class GroupServiceImpl implements GroupService {
	private GroupDao groupDao ;
	
	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public List<Group> getGroups() {
		return groupDao.getGroups();
	}

	public Group getGroupById(int groupId) {
		return groupDao.getGroupById(groupId);
	}

}
