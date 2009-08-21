package com.iwisdom.cms.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwisdom.cms.entity.Content;
import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.entity.Copyright;
import com.iwisdom.cms.service.ContentManagementService;
import com.opensymphony.xwork.ActionSupport;
import com.iwisdom.common.entity.User;
import com.iwisdom.common.util.Constants;
import com.iwisdom.servicebus.entity.Service;
import com.iwisdom.servicebus.entity.SystemService;
import com.iwisdom.servicebus.service.ServiceService;
public class ContentManagementAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9031746728635708759L;
	private Content content ;
	private File contentfile ;
	private String contentfileFileName ;
	private String contentfileContentType ;
	private int contentMainTypeId ;
	private List<Content> contents ;
	private List<ContentMainType> contentMainTypes ;
	private ContentManagementService contentManagementService;

	public List<Content> getContents() {
		return contents;
	}
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	public void setContentMainTypeId(int contentMainTypeId) {
		this.contentMainTypeId = contentMainTypeId;
	}
	public void setContentManagementService(
			ContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}
	
	public void setContentfile(File contentfile) {
		this.contentfile = contentfile;
	}
	
	public void setContentfileFileName(String contentfileFileName) {
		this.contentfileFileName = contentfileFileName;
	}
	
	public void setContentfileContentType(String contentfileContentType) {
		this.contentfileContentType = contentfileContentType;
	}
	
	public void setContent(Content content) {
		this.content = content;
	}
	public List<ContentMainType> getContentMainTypes() {
		return contentMainTypes;
	}
	public void setContentMainTypes(List<ContentMainType> contentMainTypes) {
		this.contentMainTypes = contentMainTypes;
	}
	
	public String saveContent(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("content", content);
		params.put("contentfile", contentfile);
		params.put("contentmaintypeid", contentMainTypeId);
		params.put("contentfileFileName", contentfileFileName);
		params.put("contentfileContentType", contentfileContentType);
		System.out.println("content = "+content+",contentfile="+contentfile+",contentfileFileName="+contentfileFileName);
		contentManagementService.saveContent(params);
		
		return SUCCESS ;
	}
	public String listPenddingContents(){
		contents = contentManagementService.getContentByStatus(Constants.CONTENT_STATUS_PENDING,1);
		contentMainTypes = contentManagementService.getContentMainTypes();
		return SUCCESS ;
	}
	public String listActivedContents(){
		contents = contentManagementService.getContentByStatus(Constants.CONTENT_STATUS_ACTIVED,1);
		contentMainTypes = contentManagementService.getContentMainTypes();
		return SUCCESS ;
	}
	public String listDeletedContents(){
		contents = contentManagementService.getContentByStatus(Constants.CONTENT_STATUS_DELETED,1);
		contentMainTypes = contentManagementService.getContentMainTypes();
		return SUCCESS ;
	}
	
	
}
