package com.iwisdom.common.service;

import java.util.List;
import java.util.Map;

import com.iwisdom.common.entity.Menu;

public interface UtilService {
	public List<Map> getCountries();
	public List<Map> getProvinces();
	public List<Map> getCountryProvinces(String countryId);
	public List<Map> getProvinceCities(String provincePrefix);
	public List<Map> getCityDistricts(String cityId);
	public void initMenuXMLData();
	public void createRoleMenus(java.util.Set<Menu> menus,int roleId);
}
