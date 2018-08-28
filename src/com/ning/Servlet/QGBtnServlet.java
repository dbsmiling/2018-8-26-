package com.ning.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ning.Bean.Sql_st;

import net.sf.json.JSONObject;

public class QGBtnServlet extends HttpServlet {
	/*
	 * (抢购按钮的数据处理)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("访问到QGBtnServlet！！！");
		
		String Rcnum = request.getParameter("Rcnum");
		String Rcid = request.getParameter("Rcid");
		
		int i = Sql_st.getSql_st().Rushupdate(Rcnum, Rcid);
		
		Map<String,String> map =new HashMap();	
		if (i!=0) {
			map.put("state","0000");
		}else {
			map.put("state","0001");
		}
		
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().write(json.toString());
	}
}
