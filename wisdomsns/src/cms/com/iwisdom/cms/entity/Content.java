package com.iwisdom.cms.entity;

public class Content {
	private int contentId ;
	private int developerId;
	private String contentName ;
	private ContentMainType contentMainType;
	private java.sql.Timestamp createDate;
	private java.sql.Timestamp lastModify ;
	private int version ;
	private String description ;
	private Integer status ;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	public ContentMainType getContentMainType() {
		return contentMainType;
	}
	public void setContentMainType(ContentMainType contentMainType) {
		this.contentMainType = contentMainType;
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
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getStatusI18N(String key,Integer status){
		return key+"."+status;
	}
}
