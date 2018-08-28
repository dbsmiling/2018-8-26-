package com.backstage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

/**
 * @author ����������
 *	��̨�û���Ϣɾ���߼���
 */
public class BackstageCommoditydeleteservlet extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String commodityid = request.getParameter("commodityid");
		System.out.println("commodityid:"+commodityid);
		
		String str = bss.BackstageCommoditydelete(commodityid)+"";
		
		if(str.equals("1")) {
			response.sendRedirect("Commodity.html");
		}else {
			request.getRequestDispatcher("http://ljxnb.xin");
		}
		
	}
}
