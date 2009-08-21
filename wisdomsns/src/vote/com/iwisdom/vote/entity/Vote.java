package com.iwisdom.vote.entity;

import java.util.ArrayList;
import java.util.List;

public class Vote {
	private int id;
	private String voteItem;
	private String voteDesc;
	private String startDate;
	private String endDate;
	private int flag;
	private String publisher;
	private List<Vote> voteList = new ArrayList<Vote>();

	public List<Vote> getVoteList() {
		return voteList;
	}

	public void setVoteList(List<Vote> voteList) {
		this.voteList = voteList;
	}

	public void addVote(Vote vote) {

		voteList.add(vote);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVoteItem() {
		return voteItem;
	}

	public void setVoteItem(String voteItem) {
		this.voteItem = voteItem;
	}

	public String getVoteDesc() {
		return voteDesc;
	}

	public void setVoteDesc(String voteDesc) {
		this.voteDesc = voteDesc;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}
