package com.silence.Servlet;

import java.io.IOException;
import java.net.HttpURLConnection;
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

/**
 * @author Silence 控制层
 */
public class UserServlet extends HttpServlet {
	//	把业务层带到控制层
	UserService us = new UserServicelmp();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get---servlet");
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setContentType("text/html;Charset=utf-8");//设置响应的编码格式
		HttpSession session = request.getSession();
		String youxiang=(String) session.getAttribute("email");
		if(youxiang==null) {
			response.sendRedirect("登录界面地址");
		}else {
			/*****************查询客户信息************************/
			List<Map> a =(List<Map>) us.UserService(youxiang);
			System.out.println("a.size():"+a.size());
			//获取客户信息
			String pwd  = (String) a.get(0).get("Cpwd");

			
			/*******************修改客户信息(客户表)**************************
			 * 通过页面传过来的请求的邮箱   进行客户信息修改    
			 * 客户的信息通过页面提交的进行修改(获取到的是空 就改为"")
			 * */
			String name=request.getParameter("username");
			String sex=request.getParameter("sex");
			String tell=request.getParameter("tell");
			String bir=request.getParameter("bir");
			int i = us.updCustomer(name, pwd, sex, bir, tell, youxiang);
			Map status = new HashMap();
			if(i==1) {
				status.put("status","0001");
				System.out.println("修改成功");
			}else {
				status.put("status","0000");
				System.out.println("修改失败");
			}
			/***********************json数据*************************/
			JSONObject json = JSONObject.fromObject(status);
			response.getWriter().write(json.toString());
		}
	}

}
