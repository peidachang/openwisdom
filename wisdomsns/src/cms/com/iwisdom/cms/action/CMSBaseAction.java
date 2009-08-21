package com.iwisdom.cms.action;

import java.util.List;

import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.service.ContentManagementService;
import com.opensymphony.xwork.ActionSupport;

public class CMSBaseAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6747954405077304265L;
	private ContentManagementService contentManagementService;
	private List<ContentMainType> contentMainTypes ;
	public List<ContentMainType> getContentMainTypes() {
		return contentMainTypes;
	}
	public void setContentMainTypes(List<ContentMainType> contentMainTypes) {
		this.contentMainTypes = contentMainTypes;
	}
	public ContentManagementService getContentManagementService() {
		return contentManagementService;
	}
	public void setContentManagementService(
			ContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}
}
