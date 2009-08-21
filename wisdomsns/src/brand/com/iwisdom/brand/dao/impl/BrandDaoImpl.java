package com.iwisdom.brand.dao.impl;

import java.util.List;
import java.util.Map;

import com.iwisdom.brand.dao.BrandDao;
import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.entity.BrandCategory;
import com.iwisdom.brand.entity.BrandMainType;
import com.iwisdom.common.dao.impl.BaseHibernateDao;

public class BrandDaoImpl extends BaseHibernateDao  implements BrandDao {

	public List<BrandMainType> getBrandMainTypes() {
		String hql = "from BrandMainType" ;
		
		return getHibernateTemplate().find(hql);
	}

	public List<BrandCategory> getBrandCategories() {
		String hql = "from BrandCategory";
		return getHibernateTemplate().find(hql);
	}

	public void saveBrand(Brand brand) {
		getHibernateTemplate().save(brand);
	}

	public BrandCategory getBrandCategoryById(int brandCategoryId) {
		
		return (BrandCategory) getHibernateTemplate().get(BrandCategory.class, brandCategoryId);
		
	}

	public BrandMainType getBrandMainTypeById(int brandMainTypeId) {
	
		return (BrandMainType) getHibernateTemplate().get(BrandMainType.class, brandMainTypeId);
	}

	public List<Brand> getBrands() {
		String hql = "from Brand";
		return getHibernateTemplate().find(hql);
	}

	public Brand getBrand(int brandId) {
		return (Brand) getHibernateTemplate().get(Brand.class, brandId);
	}

}
