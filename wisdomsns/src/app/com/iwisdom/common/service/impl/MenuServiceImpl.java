package com.iwisdom.common.service.impl;

import java.util.List;

import com.iwisdom.common.dao.MenuDao;
import com.iwisdom.common.entity.Menu;
import com.iwisdom.common.service.MenuService;

public class MenuServiceImpl implements MenuService {
	private MenuDao menuDao ;
	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public Menu getMenuById(int menuId) {
		return menuDao.getMenuById(menuId);
	}

	public List<Menu> getMenus() {
		return menuDao.getMenus();
	}

	public void saveMenu(Menu menu) {
		menuDao.save(menu);
	}

}
