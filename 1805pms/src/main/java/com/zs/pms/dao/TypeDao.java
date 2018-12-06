package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TBrand;
import com.zs.pms.po.TCode;
import com.zs.pms.po.TPType;

public interface TypeDao {
	public List<TPType> queryByPid(int pid);

}
