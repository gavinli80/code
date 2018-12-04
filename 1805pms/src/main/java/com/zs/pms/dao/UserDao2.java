package com.zs.pms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

/**
 * 利用注解实现DAO的接口
 * 没有xml
 * @author Administrator
 *
 */

@Repository  //与数据打交道
public interface UserDao2 {
	@Select("select * from tuser where loginname=#{loginname} and password=#{password}")
	public List<TUser> queryByCon(QueryUser query);

}
