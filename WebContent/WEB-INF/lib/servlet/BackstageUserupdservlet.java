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

/**
 * @author ����������
 * 	��̨�û���Ϣ�����߼���
 */
public class BackstageUserupdservlet extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("���ʵ�userupd");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String sex = request.getParameter("sex");
		String bir= request.getParameter("bir");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String total = request.getParameter("total");
		
		String str = bss.Backstageuserupd(name,id, pwd, sex, bir, email, phone,total)+"";
		
		Map<String,String> map = new HashMap();
		map.put("code",str);
		JSONObject jsonobject = JSONObject.fromObject(map);
		System.out.println(jsonobject);
		response.getWriter().write(jsonobject.toString());
		
	}
}
