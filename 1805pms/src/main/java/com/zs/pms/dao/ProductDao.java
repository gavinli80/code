package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TProduct;

public interface ProductDao {
	public List<TProduct> queryAll();
	
	public int insert(TProduct product);

}
