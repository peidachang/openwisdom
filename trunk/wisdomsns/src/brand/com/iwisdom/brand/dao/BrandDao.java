package com.iwisdom.brand.dao;

import java.util.List;
import java.util.Map;

import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.entity.BrandCategory;
import com.iwisdom.brand.entity.BrandMainType;
public interface BrandDao {
	public List<BrandMainType> getBrandMainTypes();
	public List<BrandCategory> getBrandCategories();
	public void saveBrand(Brand brand);
	public BrandMainType getBrandMainTypeById(int brandMainTypeId);
	public BrandCategory getBrandCategoryById(int brandCategoryId);
	public List<Brand> getBrands();
	public Brand getBrand(int brandId);
}
