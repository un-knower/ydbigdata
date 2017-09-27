package com.yd.ydbi.cainiao.controller;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.runtime.CIPRuntime;
import com.yd.ydbi.common.aspect.AopLog;
import com.yd.ydbi.utils.WatermarkPngUtil;

/**
 * 
 * Function:菜鸟信息维护管理控制器. <br/>
 * 
 * @date:2017-08-07 10:09:29 
 * @author 康元佳
 * @version:
 * @since:JDK 1.7
 */
@RestController
@RequestMapping(value = "/action/mark/watermarkController")
public class Mark_watermarkController{
	
	@RequestMapping(value = "/watermark1")
	public void watermark1(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		String username = URLDecoder.decode(request.getParameter("username"), "utf-8"); 
//        String userid = request.getParameter("userid");  
        String username = CIPRuntime.getOperateSubject().getSubject_name(); 
        String userid = CIPRuntime.getOperateSubject().getSubject_id();  
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		WatermarkPngUtil.markImageByText(response.getOutputStream(),username+" "+userid, -30,130,80); 
		response.flushBuffer();
	}
	
//	@AopLog(action = "生成水印", module = "水印模块")
	@RequestMapping(value = "/watermark")
	public void watermark(@RequestParam("userid") String userid,@RequestParam("username") String username, HttpServletResponse response) throws Exception{
		username = URLDecoder.decode(username, "utf-8"); 
		System.out.println(username);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		WatermarkPngUtil.markImageByText(response.getOutputStream(),CIPRuntime.getOperateSubject().getSubject_name()+" "+CIPRuntime.getOperateSubject().getSubject_id(), -30,130,80); 
		response.flushBuffer();
	}
	

}
