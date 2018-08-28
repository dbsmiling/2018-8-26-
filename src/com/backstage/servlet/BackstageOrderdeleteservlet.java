package com.backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

public class BackstageOrderdeleteservlet extends HttpServlet{

	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String orderid = request.getParameter("orderid");
		System.out.println("orderid:"+orderid);
		
		String str = bss.Backstageorderdelete(orderid)+"";
		
		if(str.equals("1")) {
			response.sendRedirect("order.html");
		}else {
			request.getRequestDispatcher("http://ljxnb.xin");
		}
		
	}
}

