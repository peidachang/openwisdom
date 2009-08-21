package com.iwisdom.brand.entity;

public class BrandMainType {
	private int brandMainTypeId ;
	private String brandMainTypeName ;
	private String brandMainTypeDesc ;
	private int status ;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp lastModify ;
	private java.util.Set<Brand> brands = new java.util.HashSet<Brand>();
	public int getBrandMainTypeId() {
		return brandMainTypeId;
	}
	public void setBrandMainTypeId(int brandMainTypeId) {
		this.brandMainTypeId = brandMainTypeId;
	}
	public String getBrandMainTypeName() {
		return brandMainTypeName;
	}
	public void setBrandMainTypeName(String brandMainTypeName) {
		this.brandMainTypeName = brandMainTypeName;
	}
	public String getBrandMainTypeDesc() {
		return brandMainTypeDesc;
	}
	public void setBrandMainTypeDesc(String brandMainTypeDesc) {
		this.brandMainTypeDesc = brandMainTypeDesc;
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
	public java.util.Set<Brand> getBrands() {
		return brands;
	}
	public void setBrands(java.util.Set<Brand> brands) {
		this.brands = brands;
	}
	public void addBrand(Brand brand){
		this.brands.add(brand);
	}
	
}
