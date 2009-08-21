package com.iwisdom.cms.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.service.BrandService;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;
import com.opensymphony.xwork.ActionContext;
public class BrandManagementAction extends ActionSupport {

	private static final long serialVersionUID = 5225135772867444107L;
	private BrandService brandService ;
	private Brand brand ;
	private File brandLogos ;
	private String brandLogosFileName ;
	private String brandLogosContentType ;
	private int brandMainTypeId ;
	private int brandCategoryId ;
	private List<Brand> brands ;
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	public int getBrandMainTypeId() {
		return brandMainTypeId;
	}
	public void setBrandMainTypeId(int brandMainTypeId) {
		this.brandMainTypeId = brandMainTypeId;
	}
	public int getBrandCategoryId() {
		return brandCategoryId;
	}
	public void setBrandCategoryId(int brandCategoryId) {
		this.brandCategoryId = brandCategoryId;
	}
	public File getBrandLogos() {
		return brandLogos;
	}
	public void setBrandLogos(File brandLogos) {
		this.brandLogos = brandLogos;
	}
	public String getBrandLogosFileName() {
		return brandLogosFileName;
	}
	public void setBrandLogosFileName(String brandLogosFileName) {
		this.brandLogosFileName = brandLogosFileName;
	}
	public String getBrandLogosContentType() {
		return brandLogosContentType;
	}
	public void setBrandLogosContentType(String brandLogosContentType) {
		this.brandLogosContentType = brandLogosContentType;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public BrandService getBrandService() {
		return brandService;
	}
	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}
	public String saveBrand(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("brand", brand);
		params.put("brandlogos", brandLogos);
		params.put("brandlogoscontenttype", brandLogosContentType);
		params.put("brandlogosfilename", brandLogosFileName);
		params.put("brandcategoryid", brandCategoryId);
		params.put("brandmaintypeid", brandMainTypeId);
		brandService.saveBrand(params);
		return SUCCESS ;
	}
	public String listBrands(){
		brands = brandService.getBrands();
		System.out.println("--"+brands.size());
		return SUCCESS ;
	}

}
