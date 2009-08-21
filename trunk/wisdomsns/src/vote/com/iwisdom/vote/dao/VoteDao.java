package com.iwisdom.vote.dao;

import java.util.List;

import com.iwisdom.vote.entity.Vote;

public interface VoteDao {
	
	public List query(String sql);

	public void save(String sql);
	
	public void save(String sql,Object[] values);
	
	public List<Vote> getVoteList(String sql);
}
