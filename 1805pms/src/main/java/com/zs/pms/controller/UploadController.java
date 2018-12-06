package com.zs.pms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.zs.pms.utils.Constants;

import net.fckeditor.response.UploadResponse;

/**
 * 文件上传控制器
 * @author Administrator
 *
 */
@Controller
public class UploadController {
	/**
	 * 普通文件上传
	 * @return  新文件名
	 * @param 上传的文件  与 input的name相同
	 * 
	 */
	@RequestMapping("/upload/common.do")
	@ResponseBody 
	public String uploadFile(MultipartFile file,HttpServletRequest req) {
		//获得upload文件夹的物理路径
		String path=req.getRealPath("/upload");
		//创建新文件名 目标文件
		//利用uuid算法 随机生成32位码
		/*
		 * uuid算法
		 * 根据网卡、时间、IP地址等信息
		 * 自动生成绝不重复的32位码
		 */
		
		UUID uuid=UUID.randomUUID();
		//目标文件名 32位码+文件后缀(源文件的原生文件名)
		String destfilename=uuid.toString()+file.getOriginalFilename();
		//创建新文件  物理路径\新文件名
		// url : upload/新文件名
		File destfile=new File(path+File.separator+destfilename);
		
		/*
		 * 将原始文件拷贝到新文件
		 */
		try {
			file.transferTo(destfile);
			return destfilename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
			
	}
	
	
	/**
	 * 上传图片服务器
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload/server.do")
	@ResponseBody
	public String uploadServer(MultipartFile file) {
		//生成新文件名
		UUID uuid=UUID.randomUUID();
		//目标文件名 32位码+文件后缀(源文件的原生文件名)
		String destfilename=uuid.toString()+file.getOriginalFilename();
		
		//调用jersey服务 
		Client client=new Client();
		// 图片服务器路径+文件名
		WebResource wr=client.resource(Constants.PICSERVER+destfilename);
		try {
			//利用webservice写入图片
			wr.put(String.class, file.getBytes());
			//回完整路径
			return Constants.PICSERVER+destfilename;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
		
	}
	
	@RequestMapping("/upload/fck.do")
	/**
	 * 通过fckeditor上传到图片服务器上
	 * @param req
	 * @param resp
	 */
	public void uploadFCK(HttpServletRequest req,HttpServletResponse resp) {
		//强转request
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)req;
		//获得上传文件
		Map<String, MultipartFile> map=mr.getFileMap();
		
		//遍历map
		Set  keys=map.keySet();
		Iterator<String> its=keys.iterator();
		while(its.hasNext()) {
			MultipartFile file=map.get(its.next());
			//上传文件
			String path=uploadServer(file);
			//上传成功
			if(!"ERROR".equals(path)) {
				//写fckEditor
				UploadResponse ur=UploadResponse.getOK(path);
				try {
					//写到response中
					resp.getWriter().print(ur);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
}
