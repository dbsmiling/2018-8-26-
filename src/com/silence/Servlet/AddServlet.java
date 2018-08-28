package com.silence.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.silence.Sevice.UserService;
import com.silence.Sevicelmp.UserServicelmp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AddServlet  extends HttpServlet{
	//	把业务层带到控制层
	UserService us = new UserServicelmp();
	/*
	 * 修改收货地址
	 **/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get---servlet");
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setContentType("text/html;Charset=utf-8");//设置响应的编码格式
		HttpSession session = request.getSession();
		String youxiang = (String) session.getAttribute("email");//获取session里的邮箱信息
		List<Map> a =(List<Map>) us.UserService(youxiang);
		String userid=(String) a.get(0).get("Cid");//客户id 
		
		/***********************获取用户收货地址********************************/
		 List<Map> userAdd = (List<Map>) us.UserAdd(userid);
	     System.out.println("userAdd.size():"+userAdd.size());
	     
	     /**************************判断收货地址*********************************/
	     Map status = new HashMap();
	   //获取客户提交的内容
 		String getadd =request.getParameter("add-add");
 		String gettell =request.getParameter("addtell");
 		String getname =request.getParameter("addname");
 		String recipients =request.getParameter("recipients");
	     if(userAdd.size()>=5) {
	    	 /**
	    	  * 修改客户地址
	    	  * */
	    	 int y = us.changeAdd(getadd,gettell,getname,userid,recipients);
	    	 if(y==1) {
	    		 status.put("status", "2222");
	    		 System.out.println("客户地址修改成功");
	    	 }else {
	    		 status.put("status", "3333");
	    		 System.out.println("客户地址修改失败");
	    	 }
	     }else {
    	 	/**
    	  	* 添加客户收货地址(Ship_add)
 		 	* 根据客户id进行添加(ID是注册时分发     确保不重复且存在)
 		 	*/
	 		int y = us.updAdd(getadd, gettell, getname, userid);
	 		if(y==1) {
	 			status.put("status", "0000");
	 			System.out.println("收货地址添加成功");
	 		}else {
	 			status.put("ststus","1111");
	 			System.out.println("收货地址添加失败");
	 		}
	     }
		
//		/***********************json数据*************************/
		JSONObject jsonarr = JSONObject.fromObject(status);
		response.getWriter().write(jsonarr.toString());
	}
}
