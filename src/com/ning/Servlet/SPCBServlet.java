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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SPCBServlet extends HttpServlet{
	/*
	 * (购物车的查询)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("访问到SPCBServlet！！！");

//		session中户获取email
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		System.out.println(email);
		
		String sc_id = request.getParameter("cid");
		String Sunm = request.getParameter("num");
		
		
		Map map = new HashMap();
		int a = 0;
		int i = 0;
		
		
		if(Sunm==null) {
//			查询购物车中信息条数
			a = new com.ning.Serive.Seriveimp().CNquery(email);
			map.put("Cnum", a);
		}else{
//			查询购物车中信息条数及添加购物车
			a = new com.ning.Serive.Seriveimp().CNquery(email);
			i = new com.ning.Serive.Seriveimp().SPCBserive(email, sc_id, Sunm);
			if(i!=0) {
				map.put("state", "0000");
				map.put("Cnum", a);
			}else {
				map.put("state", "0001");
				map.put("Cnum", a);
			}
		}
		
		
		JSONObject json=JSONObject.fromObject(map);
		response.getWriter().write(json.toString());
	}
	

}
