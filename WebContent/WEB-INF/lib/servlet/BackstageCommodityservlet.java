package com.backstage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author ����������
 *	��̨��Ʒ��Ϣ��ѯ�����͵�ǰ̨
 */
public class BackstageCommodityservlet extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		response.setCharacterEncoding("UTF-8");
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		List list=new ArrayList();
		JSONObject jsonobject = null;
		while(list.size()==0) {
			List list1 = bss.Backstagecommodityquery();
			list = bss.Backstagecommodityquery(page,limit);
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
