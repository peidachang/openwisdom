package com.iwisdom.brand.service;

import java.util.List;
import java.util.Map;

import com.iwisdom.brand.entity.Brand;
import com.iwisdom.brand.entity.BrandCategory;
import com.iwisdom.brand.entity.BrandMainType;

public interface BrandService {
	/**
	 * ȡ��Ʒ�ƴ����б�
	 * @return
	 */
	public List<BrandMainType> getBrandMainTypes() ;
	/**
	 * ȡ��Ʒ��������ҵ�б�
	 * @return
	 */
	public List<BrandCategory> getBrandCategories() ;
	/**
	 * ����Ʒ��
	 * @param params
	 */
	public void saveBrand(Map<String,Object> params) ;
	/**
	 * ȡ��Ʒ�ƴ��࣬ͨ��Ʒ�ƴ���ID
	 * @param brandMainTypeId
	 * @return
	 */
	public BrandMainType getBrandMainTypeById(int brandMainTypeId);
	/**
	 * ȡ��Ʒ��������ҵ��ͨ��Ʒ��������ҵID
	 * @param brandCategoryId
	 * @return
	 */
	public BrandCategory getBrandCategoryById(int brandCategoryId);
	/**
	 * ȡ������Ʒ���б�
	 * @return
	 */
	public List<Brand> getBrands();
	/**
	 * ͨ��Ʒ��IDȡ��Ʒ����Ϣ
	 * @param brandId
	 * @return
	 */
	public Brand getBrand(int brandId);
}
