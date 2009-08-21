package com.iwisdom.common.dao;

import java.util.List;
import java.util.Map;

public interface UtilDao {
	public List<Map> getCountries();
	public List<Map> getProvinces();
	public List<Map> getCountryProvinces(String countryId);
	public List<Map> getProvinceCities(String provincePrefix);
	public List<Map> getCityDistricts(String cityPrefix);
}
