package com.feng.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feng.Service.Service;
import com.feng.Service.ServiceImp;

public class Cart_dropServlet extends HttpServlet {
	Service se=new ServiceImp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");//ǰ̨�������Ʒid
		
		HttpSession session = request.getSession();
		String email=(String) session.getAttribute("email");
		List<Map> list= (List<Map>) se.Cusquery(email);//���������ѯ�ͻ�id
		String uid=(String)list.get(0).get("Cid");
		int i=se.delupd(id, uid);//����id ��uid�ı��
		if(i!=0){
			response.sendRedirect("ShopCart.html");
		}
		System.out.println(id);
		
	}
}
