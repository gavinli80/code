package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TProduct;
import com.zs.pms.po.TSku;
import com.zs.pms.po.TUser;
import com.zs.pms.service.ProductService;
import com.zs.pms.service.RedisService;
import com.zs.pms.service.SkuService;

@Controller
public class ProductController {
	@Autowired
	RedisService rs;
	@Autowired
	ProductService ps;
	@Autowired
	SkuService ss;

	@RequestMapping("/product/toadd.do")
	public String toAdd(ModelMap map) {
		// 商品类型 上衣
		map.addAttribute("TYPES", rs.getTypes(1));
		// 品牌
		map.addAttribute("BRANDS", rs.getBrands());
		// 材质
		map.addAttribute("FEATURES", rs.getTCodes("F"));
		// 颜色
		map.addAttribute("COLORS", rs.getTCodes("C"));
		// 尺码
		map.addAttribute("SIZES", rs.getTCodes("S"));

		return "/product/add";
	}

	@RequestMapping("/product/list.do")
	public String list(ModelMap map) {
		map.addAttribute("LIST", ps.queryAll());
		return "/product/list";
	}

	@RequestMapping("/product/add.do")
	public String add(TProduct product, ModelMap map, HttpSession session) {
		try {

			TUser cu = (TUser) session.getAttribute("CUSER");
			product.setCreator(cu.getId());
			product.setStatus(1); // 新增 入库 提交审核 上架 下架
			ps.addProduct(product);
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG", e.getErrMsg());
			return toAdd(map);
		}

	}

	@RequestMapping("/product/sku.do")
	public String sku(int pid ,ModelMap map) {
		List<TSku> list =ss.queryByPid(pid);
		for(TSku sku:list) {

			sku.setColorTxt(rs.getCodeName("C", sku.getColor()));//设置颜色中文
			
			sku.setFeatureTxt(rs.getCodeName("F", sku.getFeature()));//设置材质中文
			
			sku.setSizTxt(rs.getCodeName("S", sku.getSiz()));//设置尺码中文
		}
		
		map.addAttribute("LIST", list);
		return "/product/sku";
		
	}
	
	@RequestMapping("product/supdate.do")
	@ResponseBody
	public String skuUpdate(TSku sku) {
		ss.updateSku(sku);
		
		return "suc";
	}
}
