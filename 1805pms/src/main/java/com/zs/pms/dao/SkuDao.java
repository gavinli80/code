package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TSku;

public interface SkuDao {
	public int insert(TSku sku);
	//根据商品编号查询
	public  List<TSku> queryByPid(int pid);
	
	public void update(TSku sku);

}
