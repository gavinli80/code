package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TCode;

public interface CodeDao {
	public List<TCode> queryByType(String type);

}
