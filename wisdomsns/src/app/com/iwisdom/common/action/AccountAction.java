package com.iwisdom.common.action;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionSupport;

public class AccountAction extends ActionSupport{
	private String p;
	public String getP() {
		return p;
	}
	public void setP(String p) {
		ServletActionContext.getRequest().getSession().setAttribute("p", p);
		this.p = p;
	}
	public String index(){
		
		return SUCCESS ;
	}
	public String accountInfo(){
		
		return SUCCESS ;
	}
	public String accountSets(){
		
		return SUCCESS ;
	}
	public String listServices(){
		
		return SUCCESS ;
	}
	public String servicesSets(){
		
		return SUCCESS ;
	}
}
