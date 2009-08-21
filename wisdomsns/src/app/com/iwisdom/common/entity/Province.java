package com.iwisdom.common.entity;

public class Province {
	private int id ;
	private String provinceName ;
	private String provinceCnName ;
	private String postCode ;
	private String provinceShortName ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceCnName() {
		return provinceCnName;
	}
	public void setProvinceCnName(String provinceCnName) {
		this.provinceCnName = provinceCnName;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getProvinceShortName() {
		return provinceShortName;
	}
	public void setProvinceShortName(String provinceShortName) {
		this.provinceShortName = provinceShortName;
	}
}
