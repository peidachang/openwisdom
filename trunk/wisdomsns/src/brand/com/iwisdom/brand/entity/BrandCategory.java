package com.iwisdom.brand.entity;

public class BrandCategory {
	private int brandCategoryId ;
	private String brandCategoryName ;
	private String brandCategoryDesc ;
	private int status ;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp lastModify ;
	private java.util.Set<Brand> brands ;
	public java.util.Set<Brand> getBrands() {
		return brands;
	}
	public void setBrands(java.util.Set<Brand> brands) {
		this.brands = brands;
	}
	public void addBrand(Brand brand){
		this.brands.add(brand);
	}
	public int getBrandCategoryId() {
		return brandCategoryId;
	}
	public void setBrandCategoryId(int brandCategoryId) {
		this.brandCategoryId = brandCategoryId;
	}
	public String getBrandCategoryName() {
		return brandCategoryName;
	}
	public void setBrandCategoryName(String brandCategoryName) {
		this.brandCategoryName = brandCategoryName;
	}
	public String getBrandCategoryDesc() {
		return brandCategoryDesc;
	}
	public void setBrandCategoryDesc(String brandCategoryDesc) {
		this.brandCategoryDesc = brandCategoryDesc;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}
	public java.sql.Timestamp getLastModify() {
		return lastModify;
	}
	public void setLastModify(java.sql.Timestamp lastModify) {
		this.lastModify = lastModify;
	}
	
}
