package com.backstage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

import net.sf.json.JSONObject;

/**
 * @author 嘉如新上人
 *
 */
public class BackstageOrderservlet extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * dopost方法
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setCharacterEncoding("UTF-8");
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		JSONObject jsonobject=null;
		while(jsonobject==null || jsonobject.toString()=="[]") {
			List list = bss.Backstageorderquery();
			List list1 = bss.Backstageorderquery(limit,page);
			Map map = new HashMap();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", list.size());
			map.put("data", list1);
			jsonobject = JSONObject.fromObject(map);
		}
		System.out.println(jsonobject.toString());
		response.getWriter().write(jsonobject.toString());
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setCharacterEncoding("UTF-8");
		String limit=request.getParameter("limit");
		String page=request.getParameter("page");
		JSONObject jsonobject=null;
		while(jsonobject==null || jsonobject.toString()=="[]") {
			List list = bss.Backstageuserquery(page,limit);
			Map map = new HashMap();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", 200);
			map.put("data", list);
			jsonobject = JSONObject.fromObject(map);
		}
		System.out.println(jsonobject.toString());
		response.getWriter().write(jsonobject.toString());
	}
}
