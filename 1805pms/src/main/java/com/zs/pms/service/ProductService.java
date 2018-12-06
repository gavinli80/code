package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TProduct;

public interface ProductService {
	public List<TProduct> queryAll();
	
	public int addProduct(TProduct product)throws AppException;

}
