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

public class CQServlet extends HttpServlet {
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 *	商品表的查询
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String cname = request.getParameter("cname");
		
		List<Map> list = new ArrayList<>();
		list = Sql_st.getSql_st().ComQuery(cname);
		
		JSONArray jsonArray=JSONArray.fromObject(list);
		response.getWriter().write(jsonArray.toString());
	}
}
