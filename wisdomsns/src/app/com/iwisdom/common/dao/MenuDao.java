package com.iwisdom.common.dao;

import com.iwisdom.common.entity.Menu;

public interface MenuDao {
	public java.util.List<Menu> getMenus();
	public Menu getMenuById(int menuId);
	public java.util.List<Menu> getChildrenMenus(int parentId);
	public void save(Menu menu);
}
