package com.liang.login.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.liang.login.util.Getmap;

public class HttpsessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
		}

		@Override
		public void sessionDestroyed(HttpSessionEvent se) {
			HttpSession session = se.getSession();
			ServletContext sc = session.getServletContext();
			 // 取得登录的用户名
	        String username = (String) session.getAttribute("email");
	        
	        if(username!=null) {
	        	// 从在线列表中删除用户名
	            Getmap.map.remove("email");
	            System.out.println(username + "超时退出。");
	        }
	}
}
