package com.zs.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;

@Controller
/**
 * 提供restful服务
 * @author Administrator
 *
 */
public class RestController {
	@Autowired
	UserService us;
	
	@RequestMapping(value="/userinfo/{id}.do")
	//{id}  占位 实际  /userinfo/3162.do
	@ResponseBody //json返回
	public TUser queryById(@PathVariable("id")  int id) {
		return us.queryById(id);
	}
	

}
