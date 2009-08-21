package com.iwisdom.cms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import com.iwisdom.common.util.SystemUtil;

import com.iwisdom.cms.entity.Content;
import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.entity.ContentSubType;
import com.iwisdom.cms.service.ContentManagementService;
import com.iwisdom.cms.dao.ContentManagementDao;
import com.iwisdom.common.util.FileUpload;
public class ContentManagementServiceImpl implements ContentManagementService {
	private ContentManagementDao contentManagementDao ;
	public ContentManagementDao getContentManagementDao() {
		return contentManagementDao;
	}
	public void setContentManagementDao(ContentManagementDao contentManagementDao) {
		this.contentManagementDao = contentManagementDao;
	}
	public List<ContentMainType> getContentMainTypes() {
		
		return contentManagementDao.getContentMainTypes();
	}
	public List<ContentSubType> getContentSubTypes(int contentMainTypeId) {
		System.out.println("SIZE = "+contentManagementDao.getContentSubTypes(contentMainTypeId).size());
		return contentManagementDao.getContentSubTypes(contentMainTypeId);
	}
	public void saveContent(Map<String, Object> params) {
		Content content = (Content)params.get("content");
		File contentfile = (File)params.get("contentfile");
		String contentfileFileName = (String)params.get("contentfileFileName");
		String contentfileContentType = (String)params.get("contentfileContentType");
		int contentMainTypeId = (Integer)params.get("contentmaintypeid");
		if(content!=null){
			content.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
			content.setLastModify(new java.sql.Timestamp(System.currentTimeMillis()));
			content.setDeveloperId(1);
			content.setVersion(1);
			content.setStatus(new Integer(0));
			content.setContentMainType(contentManagementDao.getContentMainTypeById(contentMainTypeId));
			System.out.println("contentMainType = "+content.getContentMainType());
		if(contentManagementDao.saveContent(content)){
			long contentfileSize = 0 ;
			String fileserverbasepath = SystemUtil.getPropertyValue("fileserver.base.path");
			StringBuffer filepath = new StringBuffer(fileserverbasepath);
			File p = new File(filepath.append(content.getContentMainType().getContentMainTypeId()+"/").toString());
			if(!p.exists()) p.mkdir();
			File file = new File(filepath.append(contentfileFileName).toString());
			System.out.println("contentfilepath = "+filepath);
			if(contentfile!=null){
				contentfileSize = contentfile.length();
				System.out.println("contentFileSize = "+contentfileSize);
				FileUpload.uploadFile(contentfile, file);//do upload
			}
		}
		}
		
		
	}
	public List<Content> getContentByStatus(Integer status,int developer_id) {
		
		return contentManagementDao.getContentByStatus(status,developer_id);
	}

}
