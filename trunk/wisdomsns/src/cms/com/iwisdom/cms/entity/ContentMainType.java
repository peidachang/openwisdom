package com.iwisdom.cms.entity;

import java.util.HashSet;
import java.util.Set;

public class ContentMainType {
	private int contentMainTypeId;
	private String labels ;
	private java.sql.Timestamp createDate;
	private java.sql.Timestamp lastModify ;
	private int version ;
	private Set<Content> contents = new HashSet<Content>();
	public Set<Content> getContents() {
		return contents;
	}
	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}
	public void addContent(Content content){
		this.contents.add(content);
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
