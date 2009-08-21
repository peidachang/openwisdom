package com.iwisdom.brand.service;

import java.util.List;
import java.util.Map;

import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.entity.BrandCategory;
import com.iwisdom.brand.entity.BrandMainType;

public interface BrandService {
	/**
	 * 取得品牌大类列表
	 * @return
	 */
	public List<BrandMainType> getBrandMainTypes() ;
	/**
	 * 取得品牌所属行业列表
	 * @return
	 */
	public List<BrandCategory> getBrandCategories() ;
	/**
	 * 保存品牌
	 * @param params
	 */
	public void saveBrand(Map<String,Object> params) ;
	/**
	 * 取得品牌大类，通过品牌大类ID
	 * @param brandMainTypeId
	 * @return
	 */
	public BrandMainType getBrandMainTypeById(int brandMainTypeId);
	/**
	 * 取得品牌所属行业，通过品牌所属行业ID
	 * @param brandCategoryId
	 * @return
	 */
	public BrandCategory getBrandCategoryById(int brandCategoryId);
	/**
	 * 取得所有品牌列表
	 * @return
	 */
	public List<Brand> getBrands();
	/**
	 * 通过品牌ID取得品牌信息
	 * @param brandId
	 * @return
	 */
	public Brand getBrand(int brandId);
}
