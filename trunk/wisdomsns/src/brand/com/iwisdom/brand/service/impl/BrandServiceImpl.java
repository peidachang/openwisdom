package com.iwisdom.brand.service.impl;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.iwisdom.brand.dao.BrandDao;
import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.entity.BrandCategory;
import com.iwisdom.brand.entity.BrandMainType;
import com.iwisdom.brand.service.BrandService;
import com.iwisdom.common.util.FileUpload;
import com.iwisdom.common.util.SystemUtil;

public class BrandServiceImpl implements BrandService{
	private BrandDao brandDao ;
	
	public BrandDao getBrandDao() {
		return brandDao;
	}

	public void setBrandDao(BrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public List<BrandMainType> getBrandMainTypes() {
		return brandDao.getBrandMainTypes();
	}

	public List<BrandCategory> getBrandCategories() {
		return brandDao.getBrandCategories();
	}

	public void saveBrand(Map<String, Object> params) {
		Brand brand = (Brand)params.get("brand");
		int brandMainTypeId = (Integer)params.get("brandmaintypeid");
		int brandCategoryId = (Integer)params.get("brandcategoryid");
		String fileName = (String)params.get("brandlogosfilename");
		
		
		
		brand.setBrandMainType(brandDao.getBrandMainTypeById(brandMainTypeId));
		brand.setBrandCategory(brandDao.getBrandCategoryById(brandCategoryId));
		System.out.println(brandDao.getBrandMainTypeById(brandMainTypeId));
		brand.setStatus(0);
		brand.setBrandLogos("/data/brandlogos/"+fileName.toLowerCase());
		brand.setCreateDate(new java.sql.Timestamp(System.currentTimeMillis()));
		brand.setLastModify(new java.sql.Timestamp(System.currentTimeMillis()));
		Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, 3);//三个月后的今天
		brand.setExpireDate(new java.sql.Timestamp(c.getTimeInMillis()));
		brandDao.saveBrand(brand);
		
		File in = (File)params.get("brandlogos");
		StringBuffer filePath = SystemUtil.getSystemRootPath(this.getClass());
		filePath.append("/data/brandlogos/"+fileName.toLowerCase());
		File out = new File(filePath.toString());
		FileUpload.uploadFile(in, out);
		
	}

	public BrandCategory getBrandCategoryById(int brandCategoryId) {
		return brandDao.getBrandCategoryById(brandCategoryId);
	}

	public BrandMainType getBrandMainTypeById(int brandMainTypeId) {
		return null;
	}

	public List<Brand> getBrands() {
		return brandDao.getBrands();
	}
	
	public Brand getBrand(int brandId) {
		return brandDao.getBrand(brandId);
	}

}
