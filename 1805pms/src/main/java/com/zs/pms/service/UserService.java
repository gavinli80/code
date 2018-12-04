package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

/**
 * 用户业务接口
 * @author Administrator
 *
 */
public interface UserService {
	//登录
	public TUser chkLogin(QueryUser query) throws AppException;
	
	//修改
	public void update(TUser user) throws AppException ;
	//新增
	public int insert(TUser user) throws AppException ;
	//删除一条
	public void delete(int id)throws AppException ;
	
	//获得一条
	public  TUser queryById(int id);
	
	//按条件查询
	public List<TUser> queryByCon(QueryUser query);
	//查分页
	public List<TUser> queryByPage(QueryUser query,int page);
	
	//批量删除
	public void deleteByIds(int[] ids);
	
	//获得总页数
	public int queryPageCount(QueryUser query) ;


}
