package com.zs.pms.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.RedisService;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.DateUtil;
import com.zs.pms.vo.QueryUser;

@Controller
/*
 * 登录页和主页
 */
public class FrameController {

	@Autowired
	UserService us;
	
	@Autowired
	RedisService cs;

	@RequestMapping("/tologin.do")
	/**
	 * 去登录页
	 * 
	 * @return
	 */
	public String tologin() {
		return "login";
	}

	@RequestMapping("/login.do")
	/**
	 * 检测登录
	 * @param query  登录名和密码
	 * @param code   验证码
	 * @param session  产生会话
	 * @param map      回带数据
	 * @return              返回页面 111
	 */
	public String login(QueryUser query, String code, HttpSession session, ModelMap map) {
		// 验证码验证
		// 从session中取得kaptcha生成的验证码
		String ocode = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

		
		// 验证码不相同
		if (!ocode.equals(code)) {
			// 页面回带信息
			map.addAttribute("MSG", "验证码输入有误，请重新输入");
			// 回到登录页面
			return "login";
		}
		// 验证码相同 校验登录

		TUser user;
		try {
			user = us.chkLogin(query);
			session.setAttribute("CUSER", user);
			//当前日期
			session.setAttribute("TODAY", DateUtil.getStrDate(new Date()));
			
			//登录成功  将码表取出后写入redis中
			cs.setTCodes("F"); //材质
			cs.setTCodes("C");//颜色
			cs.setTCodes("S"); //尺码
			
			cs.setBrands();//品牌
			cs.setTypes(1);//上衣类型的子类别
			
			return "main";
		}
		// 业务异常
		catch (AppException e) {
			// TODO Auto-generated catch block
			// 页面带信息
			map.addAttribute("MSG", e.getErrMsg());
			// 回到登录页面
			return "login";
		}
		// 系统异常
		catch (Exception e1) {
			e1.printStackTrace();
			return "error";
		}

	}
	
	@RequestMapping("/totop.do")
	/**
	 * 去top页
	 * @return
	 */
	public String toTop() {
		return "top";
	}
	@RequestMapping("/toleft.do")
	//去左侧菜单
	public String toleft() {
		return "left";
	}
	@RequestMapping("/toright.do")
	//去右侧页面
	public String toright() {
		return "right";
	}
}
