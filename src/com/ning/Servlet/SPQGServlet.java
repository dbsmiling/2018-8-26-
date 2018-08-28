package com.ning.Servlet;

import java.io.IOException;
import java.util.ArrayList;
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

public class SPQGServlet extends HttpServlet{
	/*
	 * (商品抢购查询)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("访问到SPQGServlet！！！");
		
		String Rbatch=request.getParameter("Rbatch");
		List<Map> list = (List<Map>) Sql_st.getSql_st().Rushcomquery(Rbatch);
		String percent = null;
		List listp = new ArrayList();
		for (int i=0;i<list.size();i++) {
			String Rcnum = (String) list.get(i).get("Rcnum");
			String num = (String) list.get(i).get("Rnum");
			String Rcid = (String) list.get(i).get("Rcid");
			percent = new com.ning.Serive.Seriveimp().Rushserive(Rcnum,num);
			listp.add(percent);
		} 
		Map<Object,Object> mapt = new HashMap<>();
		mapt.put("percent", listp);
		mapt.put("message", list);
		
		JSONObject json = JSONObject.fromObject(mapt);
		response.getWriter().write(json.toString());
	}
}
