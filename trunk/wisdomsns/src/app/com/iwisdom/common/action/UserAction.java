package com.iwisdom.common.action;

import java.util.List;
import java.util.Map;
import com.iwisdom.common.util.Constants;
import com.iwisdom.common.entity.Group;
import com.iwisdom.common.entity.User;
import com.iwisdom.common.service.GroupService;
import com.iwisdom.common.service.UserService;
import com.iwisdom.common.service.UtilService;
import com.opensymphony.xwork.ActionSupport;
import com.opensymphony.webwork.ServletActionContext;

public class UserAction extends ActionSupport{
	private UserService userService;
	private GroupService groupService ;
	private UtilService utilService;
	private List<Map> provinces ;
	private User user ;
	private List<Group> groups ;
	private int groupId ;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public GroupService getGroupService() {
		return groupService;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public List getProvinces() {
		return provinces;
	}
	public void setProvinces(List provinces) {
		this.provinces = provinces;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public UtilService getUtilService() {
		return utilService;
	}
	public void setUtilService(UtilService utilService) {
		this.utilService = utilService;
	}
	
	/**
	 * ����ע���û�������Ϣ
	 * @return
	 */
	public String saveUser(){
		Group group = groupService.getGroupById(groupId);
		user.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		user.setGroup(group);
		userService.saveUser(user);
		return "success";
	}
	
	/**
	 * ����ע��ҳ��
	 * @return
	 */
	public String registration(){
		provinces =  utilService.getProvinces();
		groups = groupService.getGroups();
		return SUCCESS ;
	}
	/**
	 * ȡ��ĳʡ��Ͻ�ĳ����б�
	 * @param provincePrefix
	 * @return
	 */
	public List getProvinceCities(String provincePrefix){
		
		return utilService.getProvinceCities(provincePrefix) ;
	}
	/**
	 * ȡ��ĳ����Ͻ������
	 * @param cityPrefix
	 * @return
	 */
	public List getCityDistricts(String cityPrefix){
		
		return utilService.getCityDistricts(cityPrefix);
	}
	/**
	 * �û���¼
	 * @return
	 */
	public String login(){
		if(user!=null){
		if((user=userService.getUser(user.getEmail(),user.getPassword()))!=null){	
			ServletActionContext.getRequest().getSession().setAttribute(Constants.USER_SESSION, user);
			return SUCCESS ;
		}else{
				return "fail";
			}
		}else{
			
			return INPUT;
		}
	}
	
	/**
	 * �û��ǳ�,ע���û�session
	 * @return
	 */
	public String logout(){
		
		if(ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION)!=null){
			ServletActionContext.getRequest().getSession().setAttribute(Constants.USER_SESSION, null);
		}
			return SUCCESS ;
		
	}

}
