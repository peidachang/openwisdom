package com.iwisdom.cms.service;

import com.iwisdom.cms.entity.Content;
import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.entity.ContentSubType;

import java.util.List;
import java.util.Map;
public interface ContentManagementService {
	/**
	 * 取得所有内容主类别
	 * @return
	 */
	public List<ContentMainType> getContentMainTypes();
	/**
	 * 取得某一主类别下的所有子类别
	 * @param contentMainTypeId
	 * @return
	 */
	public List<ContentSubType> getContentSubTypes(int contentMainTypeId);
	/**
	 * 保存一个内容文件
	 * @param params
	 */
	public void saveContent(Map<String,Object> params);
	/**
	 * 通过文件状态取得内容列表
	 * @param status
	 * @return
	 */
	public List<Content> getContentByStatus(Integer status,int developer_id) ;
	
}
