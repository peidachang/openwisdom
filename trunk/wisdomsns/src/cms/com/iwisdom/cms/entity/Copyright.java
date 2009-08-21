package com.iwisdom.cms.entity;

public class Copyright {
	private int copyrightId ;
	private int contentId;
	private java.sql.Timestamp createDate ;
	private java.sql.Timestamp lastModify ;
	private int version ;
	private String copyrightFilePath ;
	public int getCopyrightId() {
		return copyrightId;
	}
	public void setCopyrightId(int copyrightId) {
		this.copyrightId = copyrightId;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
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
	public String getCopyrightFilePath() {
		return copyrightFilePath;
	}
	public void setCopyrightFilePath(String copyrightFilePath) {
		this.copyrightFilePath = copyrightFilePath;
	}
}
