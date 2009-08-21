package com.iwisdom.common.action;

import com.iwisdom.common.entity.Role;
import com.iwisdom.common.entity.Menu;
import com.iwisdom.common.service.RoleService;
import com.iwisdom.common.service.MenuService;
import com.iwisdom.common.service.UtilService;
import com.opensymphony.xwork.ActionSupport;

public class AdminAction extends ActionSupport {
	
	private static final long serialVersionUID = -8926576065475705749L;
	private RoleService roleService ;
	private MenuService menuService ;
	private UtilService utilService ;
	private java.util.List<Menu> menus;
	private java.util.List<Role> roles;
	private int roleId ;
	private String menuIdString ;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int groupId) {
		this.roleId = groupId;
	}
	public String getMenuIdString() {
		return menuIdString;
	}
	public void setMenuIdString(String menuIdString) {
		this.menuIdString = menuIdString;
	}
	public UtilService getUtilService() {
		return utilService;
	}
	public void setUtilService(UtilService utilService) {
		this.utilService = utilService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public java.util.List<Menu> getMenus() {
		return menus;
	}
	public java.util.List<Role> getRoles() {
		return roles;
	}
	public String index(){
		return "index";
	}
	public String menuSets(){
		menus = menuService.getMenus();
		roles = roleService.getRoles();
		System.out.println("group size = "+roles.size()+",menu size = "+menus.size());
		utilService.initMenuXMLData();
		return "menusets";
	}
	public String addMenus(){
		System.out.println("roleId = "+roleId+",menuIdString = "+menuIdString);
		Role g = roleService.getRoleById(roleId);
		int index = -1 ;
		while((index=menuIdString.lastIndexOf(','))!=-1){
			String tmp = menuIdString.substring(index+1, menuIdString.length());
			System.out.println(tmp+"\n");
			menuIdString = menuIdString.substring(0,index);
			Menu m = menuService.getMenuById(Integer.parseInt(tmp));
			g.getMenus().add(m);
			
		}
		System.out.println(menuIdString);
		Menu m = menuService.getMenuById(Integer.parseInt(menuIdString));
		g.getMenus().add(m);
		java.util.Set<Menu> menus = g.getMenus();
		utilService.createRoleMenus(menus,roleId);
		roleService.saveRole(g);
		return "menusets";
	}

}
