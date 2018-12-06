package com.zs.pms.service;

import java.util.List;

import com.zs.pms.po.TBrand;
import com.zs.pms.po.TCode;
import com.zs.pms.po.TPType;

public interface RedisService {
	// 设置码表
	public void setTCodes(String type);

	// 获得码表
	public List<TCode> getTCodes(String type);

	// 设置品牌
	public void setBrands();

	// 获得品牌
	public List<TBrand> getBrands();

	// 设置类别
	public void setTypes(int pid);

	// 获得类别
	public List<TPType> getTypes(int pid);
	
	//获得名称
	public String getCodeName(String type,int cid);

}
