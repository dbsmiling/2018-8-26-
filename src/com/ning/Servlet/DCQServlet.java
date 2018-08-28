package com.ning.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ning.Bean.Sql_st;

import net.sf.json.JSONArray;

public class DCQServlet extends HttpServlet{
	/*
	 * (单个商品的查询)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("访问到DCQServlet！！！");
//		获取商品Id
		String id = request.getParameter("cid");
		List<Map> list = new ArrayList<>();
//		查询
		list = Sql_st.getSql_st().DCQuery(id);
		
		JSONArray jsonArray=JSONArray.fromObject(list);
		response.getWriter().write(jsonArray.toString());
	}
}
