package com.iwisdom.common.service;

import com.iwisdom.common.entity.Menu;

public interface MenuService {
	public java.util.List<Menu> getMenus();
	public Menu getMenuById(int menuId);
	public void saveMenu(Menu menu);
}
