package com.iwisdom.common.action;

import java.util.List;

import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.service.BrandService;
import com.opensymphony.xwork.ActionSupport;

public class MenuAction extends ActionSupport {
	private BrandService brandService ;
	private List<Brand> brands ;
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	public BrandService getBrandService() {
		return brandService;
	}
	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}
	public String index(){
	
		return SUCCESS ;
	}
	public String vote(){
		
		return SUCCESS ;
	}
	public String interactive(){

		return SUCCESS ;
	}
	public String mobilecontent(){
	
		return SUCCESS ;
	}
	public String service(){

		return SUCCESS ;
	}
	public String share(){
	
		return SUCCESS ;
	}
	public String brandCenter(){
		brands = brandService.getBrands();
		return SUCCESS; 
	}

}
