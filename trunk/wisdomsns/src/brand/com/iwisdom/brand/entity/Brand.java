package com.iwisdom.brand.entity;

public class Brand {
	private int brandId;
	private String brandName ;
	private String brandArea ;
	private String brandLogos ;
	private String brandSummary ;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp lastModify ;
	private java.sql.Timestamp expireDate ;
	private int status ;
	private BrandMainType brandMainType ;
	private BrandCategory brandCategory ;
	public java.sql.Timestamp getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(java.sql.Timestamp expireDate) {
		this.expireDate = expireDate;
	}
	public BrandCategory getBrandCategory() {
		return brandCategory;
	}
	public void setBrandCategory(BrandCategory brandCategory) {
		this.brandCategory = brandCategory;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandArea() {
		return brandArea;
	}
	public void setBrandArea(String brandArea) {
		this.brandArea = brandArea;
	}
	public String getBrandLogos() {
		return brandLogos;
	}
	public void setBrandLogos(String brandLogos) {
		this.brandLogos = brandLogos;
	}
	public String getBrandSummary() {
		return brandSummary;
	}
	public void setBrandSummary(String brandSummary) {
		this.brandSummary = brandSummary;
	}

	public BrandMainType getBrandMainType() {
		return brandMainType;
	}
	public void setBrandMainType(BrandMainType brandMainType) {
		this.brandMainType = brandMainType;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
