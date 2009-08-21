package com.iwisdom.cms.dao;

import java.util.List;
import com.iwisdom.cms.entity.Content;
import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.entity.ContentSubType;

public interface ContentManagementDao {
	public List<ContentMainType> getContentMainTypes();
	public List<ContentSubType> getContentSubTypes(int contentMainTypeId);
	public boolean saveContent(Content content);
	public Content getContentById(int contentId);
	public ContentMainType getContentMainTypeById(int contentMainTypeId);
	public List<Content> getContentByStatus(Integer status,int developer_id);
}
