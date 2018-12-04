package com.zs.pms.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zs.pms.dao.UserDao;
import com.zs.pms.utils.DateUtil;

/**
 * 用户表的PO
 * 
 * @author Administrator
 *
 */
public class TUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5293214558214995122L;

	private int id;
	private String loginname;
	private String realname;
	private String sex;
	private Date birthday;

	private TDep dept; // 关联对象 一对一

	private String email;
	private int isenabled;
	private String password;
	private int creator;
	private Date createtime;
	private int updator;
	private Date updatetime;
	private String picurl;
	// 显示字段
	private String sexTxt;

	private String isenabledTxt;
	// 用于显示
	private String birthdayTxt;

	public String getBirthdayTxt() {
		return DateUtil.getStrDate(birthday);
	}

	public void setBirthdayTxt(String birthdayTxt) {
		this.birthdayTxt = birthdayTxt;
	}

	public String getIsenabledTxt() {
		if (isenabled == 1) {
			return "可用";
		} else {
			return "不可用";
		}

	}

	public void setIsenabledTxt(String isenabledTxt) {
		this.isenabledTxt = isenabledTxt;
	}

	// ${user.sexTxt}
	// 调用了getSexTxt()
	public String getSexTxt() {
		/*
		 * 根据现有数据来返回不同的显示数据
		 */
		if ("1".equals(sex)) {
			return "男";
		} else {
			return "女";
		}

	}

	// 关联 权限列表
	private List<TPermission> permissions;
	// 左侧菜单,由permissions整理出来
	private List<TPermission> menu = new ArrayList<>();

	/**
	 * 整理菜单
	 * 
	 * @return
	 */
	public List<TPermission> getMenu() {
		// 清空
		menu.clear();

		for (TPermission per1 : permissions) {
			// 一级菜单
			if (per1.getPid() == 0) {
				// 清空
				per1.getChildren().clear();
				// 装载一级菜单下的二级菜单
				for (TPermission per2 : permissions) {
					// 一级菜单的id==二级菜单的pid
					// 说明该权限是一级菜单的子权限
					if (per1.getId() == per2.getPid()) {
						per1.getChildren().add(per2);
					}
				}
				// 将装载好的一级菜单放入菜单中
				menu.add(per1);
			}
		}

		return menu;
	}

	public List<TPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<TPermission> permissions) {
		this.permissions = permissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public TDep getDept() {
		return dept;
	}

	public void setDept(TDep dept) {
		this.dept = dept;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getUpdator() {
		return updator;
	}

	public void setUpdator(int updator) {
		this.updator = updator;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

}
