package com.backstage.servlet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.backstage.util.Getmap;

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
        String username = (String) session.getAttribute("name");

        // 从在线列表中删除用户名
        Getmap.map.remove("username");
        
        System.out.println(username + "超时退出。");
		
	}

	
}
