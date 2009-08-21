package com.iwisdom.cms.action;

import java.util.List;

import com.opensymphony.xwork.ActionSupport;
import com.iwisdom.brand.entity.BrandCategory;
import com.iwisdom.brand.entity.BrandMainType;
import com.iwisdom.brand.service.BrandService;
import com.iwisdom.cms.entity.ContentMainType;
import com.iwisdom.cms.service.ContentManagementService;
public class ContentControlAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ContentManagementService contentManagementService;
	private BrandService brandService ;
	private List<ContentMainType> contentMainTypes ;
	private List<BrandMainType> brandMainTypes ;
	private List<BrandCategory> brandCategories ;

	public List<BrandCategory> getBrandCategories() {
		return brandCategories;
	}
	public void setBrandCategories(List<BrandCategory> brandCategories) {
		this.brandCategories = brandCategories;
	}
	public BrandService getBrandService() {
		return brandService;
	}
	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}
	public List<BrandMainType> getBrandMainTypes() {
		return brandMainTypes;
	}
	public void setBrandMainTypes(List<BrandMainType> brandMainTypes) {
		this.brandMainTypes = brandMainTypes;
	}
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
	public String index(){
		
		return SUCCESS ;
	}
	public String publishContent(){
		contentMainTypes = contentManagementService.getContentMainTypes();
		return SUCCESS ;
	}
	public String applyBrand(){
		brandMainTypes = brandService.getBrandMainTypes();
		brandCategories = brandService.getBrandCategories();
		System.out.println("size="+brandMainTypes.size()+"---"+brandCategories.size());
		return SUCCESS ;
		
	}
}
