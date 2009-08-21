package com.iwisdom.vote.service.impl;

import java.util.Date;
import java.util.List;

import com.iwisdom.vote.dao.VoteDao;
import com.iwisdom.vote.entity.Vote;
import com.iwisdom.vote.util.DateTimeUtil;

public class VoteServiceImpl {

	private VoteDao voteDao;
	
	public VoteDao getVoteDao() {
		return voteDao;
	}

	

	public void setVoteDao(VoteDao voteDao) {
		this.voteDao = voteDao;
	}

	public void test(Vote vote) {
		// List list = voteDao.query("select * from votes");
		System.out.println("--" + voteDao);
		
	
	}

	public void saveVote(Vote vote){
		
		String sql = "insert into vote" +
				"(vote_item,vote_desc,start_date,end_date,flag,publisher) " +
				"values ('"+vote.getVoteItem()+
				"','"+vote.getVoteDesc()+
				"','"+DateTimeUtil.format()+
				"','"+vote.getEndDate()+"',0,'admin')";
		voteDao.save(sql);
	}
	public List<Vote> getVoteList(){
		String sql = "select * from vote";
		return voteDao.getVoteList(sql);
	}
}
