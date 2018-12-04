package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.dao.UserDao2;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;

/**
 * user服务实现
 * 
 * @author Administrator
 *
 */
@Service
@Transactional // 该业务支持事务
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao udao;
	
	@Override
	public TUser chkLogin(QueryUser query) throws AppException {
		// TODO Auto-generated method stub

		// 将明码变成密码

		MD5 md5 = new MD5();
		// 加密后的密码
		String p1 = md5.getMD5ofStr(query.getPassword());
		// 将密码放到query中
		query.setPassword(p1);

		List<TUser> list = udao.queryByCon(query);
		// 没有用户
		if (list == null || list.size() != 1) {
			throw new AppException(1000, "用户名或密码输入有误，请重新输入");
		}
		// 获得第一条
		TUser user = list.get(0);

		// 关联权限列表返回
		return udao.queryById(user.getId());

	}

	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return udao.queryByCon(query);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 有异常就回滚否则提交
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		udao.deleteByIds(ids);

	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 有异常就回滚否则提交
	public void update(TUser user) throws AppException {
		
		//不可用
		if(user.getIsenabled()==-1) {
			throw new AppException(1003, "不能修改不可用用户");
		}
		
		// TODO Auto-generated method stub
		//获得原来的用户
		TUser ouser=udao.queryById(user.getId());
		//原密码不等于新密码 才加密
		if (user.getPassword() != null && (!"".equals(user.getPassword()))&&!user.getPassword().equals(ouser.getPassword())) {
			// 密码加密
			MD5 md5 = new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}

		udao.update(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 有异常就回滚否则提交
	public int insert(TUser user) throws AppException {
		// TODO Auto-generated method stub
		// udao.insert(user); //回滚 新增不成功
		// int a=1/0; //抛异常
		
		//登录名=admin
		if("admin".equals(user.getLoginname())) {
			throw new AppException(1002, "登录名不能为admin");
		}
		
		return udao.insert(user);

	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 有异常就回滚否则提交
	public void delete(int id) throws AppException {
		// TODO Auto-generated method stub

		udao.delete(id);
	}

	@Override
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return udao.queryById(id);
	}

	@Override
	/**
	 * query : 条件 page: 当前页
	 */
	public List<TUser> queryByPage(QueryUser query, int page) {
		// TODO Auto-generated method stub
		// 将当前页设置到条件中
		query.setPage(page);

		// 可以设置起始和截止

		return udao.queryByPage(query);
	}

	@Override
	/**
	 * 计算总页数
	 */
	public int queryPageCount(QueryUser query) {
		// TODO Auto-generated method stub
		// 获得总条数
		int count = udao.queryCount(query);
		// 能整除
		if (count % Constants.PAGECOUNT == 0) {
			return count / Constants.PAGECOUNT;
		}

		// 不能整除
		else {
			return count / Constants.PAGECOUNT + 1;
		}

	}

}
