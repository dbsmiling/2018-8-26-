package com.ning.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ning.Bean.Sql_st;

import net.sf.json.JSONObject;

public class DLServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("访问到DLServlet！！！");
//		session中户获取email
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		System.out.println(email);
		
		String name = Sql_st.getSql_st().Uesrquery(email);
		System.out.println(name);
		Map map = new HashMap();
		if(email==null) {
			map.put("state","0001");
		}else {
			map.put("state","0000");
			map.put("user", name);
		}
		
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().write(json.toString());
	}
}
