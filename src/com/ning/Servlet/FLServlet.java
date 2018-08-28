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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FLServlet extends HttpServlet{
	/*
	 * (商品分类页面的查询)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("访问到flServlet！！！");
		
		String CAbb = request.getParameter("fl");
		String page = request.getParameter("page");
		int a = Integer.valueOf(page);
		
		System.out.println(a);
		List<Map> list = Sql_st.getSql_st().Cpagequery(CAbb, a);
		Map map = new HashMap();
		if(a<1) {
			if (list.size()==0) {
				map.put("state", "0001");
			}else {
				map.put("state", "0000");
				map.put("msg", list);
			}
		}else {
			if (list.size()==0) {
				map.put("state", "0002");
			}else {
				map.put("state", "0000");
				map.put("msg", list);
			}
		}
		
		
		
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().write(json.toString());
	}
}
