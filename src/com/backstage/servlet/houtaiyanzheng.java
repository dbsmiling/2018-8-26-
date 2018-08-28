package com.backstage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liang.login.util.Getmap;

import net.sf.json.JSONObject;

public class houtaiyanzheng extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String adminid = (String) session.getAttribute("adminid");
		System.out.println("adminid:"+adminid);
		Map mmp = new HashMap<>();
		if(adminid==null) {
			//找不到账号
			mmp.put("code","0001");
		}else {
			//找到账号 显示账号
			mmp.put("code", adminid);
		}
		response.getWriter().write(JSONObject.fromObject(mmp).toString());
	}
}
