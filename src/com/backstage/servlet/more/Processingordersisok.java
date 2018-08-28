package com.backstage.servlet.more;

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

public class Processingordersisok extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderid = request.getParameter("orderid");
		System.out.println(orderid);
		int i = bss.Backstageorderisok(orderid);
		
		Map map = new HashMap<>();
		if(i==1) {
			map.put("code", "0000");
		}else {
			map.put("code", "0001");
		}
		response.getWriter().write(JSONObject.fromObject(map).toString());
	}
}
