package com.liang.login.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liang.login.util.Getmap;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class yanzheng extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		Map mmp = new HashMap<>();
		if(name==null) {
			//找不到账号
			mmp.put("code","0001");
		}else {
			//找到账号 显示账号
			mmp.put("code", name);
		}
		// 统计在线人数
		session.setAttribute("renshu", Getmap.map.size());
		System.out.println(Getmap.map.size()+"个账号已登录");
		resp.getWriter().write(JSONObject.fromObject(mmp).toString());
		
	}
}
