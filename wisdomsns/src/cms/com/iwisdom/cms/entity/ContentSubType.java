package com.iwisdom.cms.entity;

public class ContentSubType {
	private int contentSubTypeId ;
	private int contentMainTypeId ;
	private String labels ;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp lastModify ;
	private int version ;
	public int getContentSubTypeId() {
		return contentSubTypeId;
	}
	public void setContentSubTypeId(int contentSubTypeId) {
		this.contentSubTypeId = contentSubTypeId;
	}
	public int getContentMainTypeId() {
		return contentMainTypeId;
	}
	public void setContentMainTypeId(int contentMainTypeId) {
		this.contentMainTypeId = contentMainTypeId;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
