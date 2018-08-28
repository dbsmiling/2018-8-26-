package com.liang.login.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class getsessionid extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String sessionid=request.getSession().getId();
		System.out.println(email+" "+sessionid);
		HttpSession session = request.getSession();
		//当session里面存在name!=sessionid时  
		Map mmp = new HashMap<>();
		System.out.println(session.getAttribute("email"));
		if(session.getAttribute("email")!=null && Getmap.map.get(email)==sessionid) {
			//用户已登陆，本次登陆失效
			mmp.put("code", "0001");
		}else {
//			0D3C0F7629DBB1C84E5F52C493186895
//			0D3C0F7629DBB1C84E5F52C493186895
			//用户未登陆过 第一次登陆 先放入session 可以在登陆后页面进行get  然后往map中
			session.setAttribute("email", email);
			//往map放入key=账户名  value=sessionid
			Getmap.map.put(email, sessionid);
			System.out.println("已放入map");
			mmp.put("code", "0000");
		}
		response.getWriter().write(JSONObject.fromObject(mmp).toString());
	}
	
}
