package com.backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backstage.util.Getmap;

public class exit extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入remove");
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("name");
		Getmap.map.remove(name);
		session.invalidate();
		System.out.println(Getmap.map.size());
		//移除map中的账号信息 返回首页会自动打回登陆
		resp.sendRedirect("index.html");
	}
}
