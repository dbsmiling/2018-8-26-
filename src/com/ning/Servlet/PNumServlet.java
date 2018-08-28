package com.ning.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class PNumServlet extends HttpServlet {
	/*
	 * (某一类商品总数查询)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("访问到PNumServlet！！！");
		
		String CAbb = request.getParameter("fl"); 
		System.out.println(CAbb);
		int pagenum = new com.ning.Serive.Seriveimp().PNumquery(CAbb);
		String page =String.valueOf(pagenum);
		Map map = new HashMap<>();
		map.put("page", page);
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json);
		response.getWriter().write(json.toString());
	}
}
