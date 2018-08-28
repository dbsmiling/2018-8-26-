package com.feng.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feng.Service.Service;
import com.feng.Service.ServiceImp;

import net.sf.json.JSONObject;

public class Order_ChangeServlet extends HttpServlet {
	Service se=new ServiceImp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("������order_changeServlet");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cemail=(String) session.getAttribute("email");
		List<Map> list= (List<Map>) se.Cusquery(cemail);//��ѯ�ͻ���
		String id=(String)list.get(0).get("Cid");
		String one="1";
		int i=se.orderchange(id, one);
		int drop=se.drop(id);
		Map map1=new HashMap();
		if(drop==0){
			map1=null;
		}else{
			map1.put("success", 1);
		}
		JSONObject ob = JSONObject.fromObject(map1);
		response.getWriter().write(ob.toString());
	}
}
