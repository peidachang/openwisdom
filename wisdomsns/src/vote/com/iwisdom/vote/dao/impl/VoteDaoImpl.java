package com.iwisdom.vote.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.iwisdom.vote.dao.VoteDao;
import com.iwisdom.vote.entity.Vote;

public class VoteDaoImpl extends JdbcDaoSupport implements VoteDao {

	public List<Object> query(String sql) {

		return this.getJdbcTemplate().queryForList(sql);
	}

	public void save(String sql) {

		this.getJdbcTemplate().execute(sql);
		
	}
	public void save(String sql,Object[] values){
		
		this.getJdbcTemplate().update(sql, values);
	}
	
	public List<Vote> getVoteList(String sql){
		return this.getJdbcTemplate().queryForList(sql);
	}

}
