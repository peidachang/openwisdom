package com.iwisdom.common.dao.impl.hibernate;

import java.util.List;

import com.iwisdom.common.dao.GroupDao;
import com.iwisdom.common.dao.impl.BaseHibernateDao;
import com.iwisdom.common.entity.Group;
import com.iwisdom.common.util.Constants;
public class GroupDaoImpl extends BaseHibernateDao implements GroupDao {

	public List<Group> getGroups() {
		String hql = "from Group g where g.groupId!=?";
		
		return getHibernateTemplate().find(hql,Constants.SYSTEM_ADMIN_CODE);
	}

	public Group getGroupById(int groupId) {
		String hql = "from Group g where g.groupId=?";
		List list = getHibernateTemplate().find(hql,groupId);
		if(list.size()>0)
			return (Group)list.get(0);
		else
			return null ;
	}

}
