package com.iwisdom.vote.action;

import com.iwisdom.vote.service.impl.VoteServiceImpl;
import com.iwisdom.vote.entity.Vote;

public class VoteAction {
	
	private VoteServiceImpl voteService ;
	private Vote vote ;
	public VoteServiceImpl getVoteService() {
		return voteService;
	}
	public void setVoteService(VoteServiceImpl voteService) {
		this.voteService = voteService;
	}
	public Vote getVote() {
		return vote;
	}
	public void setVote(Vote vote) {
		this.vote = vote;
	}
	public String addVote(){
		//System.out.println("vote ="+vote.getVoteItem());
		voteService.saveVote(vote);
		System.out.println("vote size = "+voteService.getVoteList()==null?"null":voteService.getVoteList().size());
		vote.setVoteList(voteService.getVoteList());
		return "success";
	}
	public String voteList(){
		vote.setVoteList(voteService.getVoteList());
		return "success";
		
	}
	
	
}
