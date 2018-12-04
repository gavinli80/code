package com.zs.pms.vo;

import com.zs.pms.utils.Constants;

/**
 * 分页对象
 * 
 * @author Administrator
 *
 */
public class QueryPage {
	protected int start; // 起始数 可以继承
	protected int end;// 截止数

	protected int page; // 当前页

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	/*
	 * 计算起始数
	 */
	public int getStart() {
		// (当前页-1)*每页条数+1
		return (page - 1) * Constants.PAGECOUNT+1;
	}
	

	public void setStart(int start) {
		this.start = start;
	}
	
	/*
	 * 计算截止数
	 */
	public int getEnd() {
		//当前页*每页条数
		return page*Constants.PAGECOUNT;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
