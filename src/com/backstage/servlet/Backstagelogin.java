package com.backstage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

import net.sf.json.JSONObject;

public class Backstagelogin extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("进入post");
		String id = req.getParameter("adminid");
		String pwd = req.getParameter("adminpwd");
		Map map = new HashMap<>();
		int i=bss.login(id,pwd);
		if(i==1) {
			map.put("code", "0000");
		}else {
			map.put("code", "0001");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		resp.getWriter().write(jsonObject.toString());
	}
}
