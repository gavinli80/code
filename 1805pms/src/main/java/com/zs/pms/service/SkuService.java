package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TSku;

public interface SkuService {
	public List<TSku> queryByPid(int pid);
	
	public void updateSku(TSku sku) ;

}
