package com.iwisdom.common.dao.impl.hibernate;

import java.util.List;

import com.iwisdom.common.dao.MenuDao;
import com.iwisdom.common.dao.impl.BaseHibernateDao;
import com.iwisdom.common.entity.Menu;

public class MenuDaoImpl extends BaseHibernateDao implements MenuDao {

	public Menu getMenuById(int menuId) {
		
		return (Menu)getHibernateTemplate().get(Menu.class, menuId);
	}

	public List<Menu> getMenus() {
		//just select the group common menus,avoid the private menus.
		String hql = "from Menu m where m.status=1";
		return getHibernateTemplate().find(hql);
	}

	public List<Menu> getChildrenMenus(int parentId) {
		String hql = "from Menu m where m.pid=? and m.status=1";
		return getHibernateTemplate().find(hql, parentId);
	}

	public void save(Menu menu) {
		getHibernateTemplate().saveOrUpdate(menu);
	}

}
