package com.iwisdom.brand.action;

import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.service.BrandService;
import com.opensymphony.xwork.ActionSupport;

public class BrandAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3374329726333313503L;
	private Brand brand ;
	private BrandService brandService ;
	public BrandService getBrandService() {
		return brandService;
	}
	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String brandSummary(){
		
		return SUCCESS;
	}
	public String brandDetail(){
		System.out.println(brand+"--"+brand.getBrandId());
		brand = brandService.getBrand(brand.getBrandId());
		return SUCCESS;
	}
}
