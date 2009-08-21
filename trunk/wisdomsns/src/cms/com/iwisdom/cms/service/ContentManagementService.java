package com.iwisdom.cms.service;

import com.iwisdom.cms.entity.Content;
import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.entity.ContentSubType;

import java.util.List;
import java.util.Map;
public interface ContentManagementService {
	/**
	 * ȡ���������������
	 * @return
	 */
	public List<ContentMainType> getContentMainTypes();
	/**
	 * ȡ��ĳһ������µ����������
	 * @param contentMainTypeId
	 * @return
	 */
	public List<ContentSubType> getContentSubTypes(int contentMainTypeId);
	/**
	 * ����һ�������ļ�
	 * @param params
	 */
	public void saveContent(Map<String,Object> params);
	/**
	 * ͨ���ļ�״̬ȡ�������б�
	 * @param status
	 * @return
	 */
	public List<Content> getContentByStatus(Integer status,int developer_id) ;
	
}
