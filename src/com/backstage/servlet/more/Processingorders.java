package com.backstage.servlet.more;

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

public class Processingorders extends HttpServlet{
	BackstageService bbs = new BackstageServiceimp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setCharacterEncoding("UTF-8");
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		System.out.println(limit+" "+page);
		JSONObject jsonobject=null;
		while(jsonobject==null || jsonobject.toString()=="[]") {
			List list = bbs.Backstageorderprocessquery(page,limit);
			List list1 = bbs.Backstageorderprocessquery();
			Map map = new HashMap();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", list1.size());
			map.put("data", list);
			jsonobject = JSONObject.fromObject(map);
		}
		System.out.println(jsonobject.toString());
		response.getWriter().write(jsonobject.toString());
		
		
	}
}
