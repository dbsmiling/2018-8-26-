package com.ning.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ning.Bean.Sql_st;

import net.sf.json.JSONObject;

public class CAROServlet extends HttpServlet{
		/*
		 * (商品详情页面部分内容的查询)
		 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			System.out.println("访问到CAROServlet！！！");
			
			String id = request.getParameter("cid");
			
			List<Map> DClist = Sql_st.getSql_st().DCQuery(id);
			List CIMGlist = Sql_st.getSql_st().CIMGQuery(id);
			
			Map<String, List> map = new HashMap<>();
			map.put("ComInfo",DClist);
			map.put("ComIMG",CIMGlist);
			
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().write(json.toString());
		}
}
