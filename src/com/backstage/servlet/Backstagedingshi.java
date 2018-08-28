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

public class Backstagedingshi extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sjnum = request.getParameter("ds2");
		String dsnum = request.getParameter("ds1");
		long sztime = System.currentTimeMillis();
		System.out.println(dsnum+" "+sjnum+" "+sztime);
		int i = bss.Backstageskadd(sjnum, dsnum, sztime);
		Map map = new HashMap<>();
		if(i==1) {
			map.put("code", "0000");
		}else {
			map.put("code", "0001");
		}
		response.getWriter().write(JSONObject.fromObject(map).toString());
	}
}
