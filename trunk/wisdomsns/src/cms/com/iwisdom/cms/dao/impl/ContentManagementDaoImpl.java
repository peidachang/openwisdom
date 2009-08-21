package com.iwisdom.cms.dao.impl;

import java.util.List;
import com.iwisdom.cms.dao.ContentManagementDao;
import com.iwisdom.cms.entity.Content;
import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.entity.ContentSubType;
import com.iwisdom.common.dao.impl.BaseHibernateDao;

public class ContentManagementDaoImpl extends BaseHibernateDao implements ContentManagementDao {
	public List<ContentMainType> getContentMainTypes(){
		String hql = "from ContentMainType";
		return getHibernateTemplate().find(hql);
	}

	public List<ContentSubType> getContentSubTypes(int contentMainTypeId) {
		String hql = "from ContentSubType cst where cst.ContentMainTypeId=?";
		return getHibernateTemplate().find(hql, contentMainTypeId);
	}

	public boolean saveContent(Content content) {
		try{
		getHibernateTemplate().save(content);
		return true ;
		}catch(Exception e){
			e.printStackTrace();
			return false ;
		}
		
	}

	public Content getContentById(int contentId) {
		
		return (Content) getHibernateTemplate().get(Content.class, contentId);
	}

	public ContentMainType getContentMainTypeById(int contentMainTypeId) {
		
		return (ContentMainType) getHibernateTemplate().get(ContentMainType.class, contentMainTypeId);
	}

	public List<Content> getContentByStatus(Integer status,int developer_id) {
		String hql = null ;
		if(status==null) 
		{
			hql = "from Content c where developer_id=?";
			return getHibernateTemplate().find(hql, status);
		}else{
			hql = "from Content c where c.status=? and developer_id=?";
			Integer[] params = {status,developer_id};
			return getHibernateTemplate().find(hql, params);
		}
		
	}
}
