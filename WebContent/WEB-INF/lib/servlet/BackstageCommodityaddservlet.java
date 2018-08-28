package com.backstage.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

import net.sf.json.JSONObject;

public class BackstageCommodityaddservlet extends HttpServlet{

	BackstageService bss = new BackstageServiceimp();
	
	public static int getRandNum(int min, int max) {
	    int randNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randNum;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String picname = (String) request.getAttribute("uploadname");
		System.out.println(picname);
		HttpSession session = request.getSession();
		picname = (String) session.getAttribute("uploadname");
		System.out.println(picname);
		String name = request.getParameter("name");
		String pri = request.getParameter("pri");
		String details = request.getParameter("details");
		String ify = request.getParameter("ify");
		Map map = new HashMap();
		if(picname!=null) {
			String id=null;
			int i=0;
			do {
				id=getRandNum(100,999)+"";//随机生成的用户id
				System.out.println(id);
				i=bss.Backstagecommodityqueryid(id);
			}while(i>=1);
				int a=bss.Backstageusercommodityadd(id, name, pri, details, ify, picname);
				if (a==1) {
					map.put("code", "0000");
					JSONObject jsob=JSONObject.fromObject(map);
			        response.getWriter().write(jsob.toString());
				}else {
					map.put("code","0002");
					JSONObject jsob=JSONObject.fromObject(map);
			        response.getWriter().write(jsob.toString());
				}
			}else {
				map.put("code","0001");
				JSONObject jsob=JSONObject.fromObject(map);
				System.out.println(jsob);
		        response.getWriter().write(jsob.toString());
			}
		
	}

}
