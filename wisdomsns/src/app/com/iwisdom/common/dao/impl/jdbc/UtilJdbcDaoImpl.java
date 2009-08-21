package com.iwisdom.common.dao.impl.jdbc;

import java.util.List;
import java.util.Map;

import com.iwisdom.common.dao.UtilDao;
import com.iwisdom.common.dao.impl.BaseJdbcDao;

public class UtilJdbcDaoImpl extends BaseJdbcDao implements UtilDao {

	public List<Map> getCountries() {
		String sql = "SELECT * FROM I_COUNTRY";
		return getJdbcTemplate().queryForList(sql);
	}

	public List<Map> getCountryProvinces(String countryId) {
		String sql = "SELECT * FROM I_PROVINCE WHERE COUNTRY_ID=?";
		String[] args = { countryId };
		return getJdbcTemplate().queryForList(sql, args);
	}

	public List<Map> getProvinceCities(String provincePrefix) {
		
		String prefix = provincePrefix.substring(0, 2)+"%";
		
		String sql = "SELECT * FROM I_CITY WHERE CITY_CARD_PREFIX like ?";
		String[] args = { prefix };
		return getJdbcTemplate().queryForList(sql, args);
	}

	public List<Map> getProvinces() {
		String sql = "SELECT * FROM I_PROVINCE";
		return getJdbcTemplate().queryForList(sql);
	}

	public List<Map> getCityDistricts(String cityPrefix) {
		String prefix = null ;
		if(cityPrefix.startsWith("11")||cityPrefix.startsWith("12")||cityPrefix.startsWith("31")||cityPrefix.startsWith("50"))
			prefix = cityPrefix.substring(0, 2)+"%";
		else
			prefix = cityPrefix.substring(0, 4)+"%";
		String sql = "SELECT * FROM I_DISTRICT WHERE DISTRICT_CARD_PREFIX like ?";
		String[] args = { prefix };
		return getJdbcTemplate().queryForList(sql, args);
	}

	

}
