package com.backstage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class Backstagedingshi extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ds = req.getParameter("ds");
		long num = Long.valueOf(ds);
		System.out.println(num);
		Map map = new HashMap<>();
		map.put("ds", num);
		
		resp.getWriter().write(JSONObject.fromObject(map).toString());
	}
}
