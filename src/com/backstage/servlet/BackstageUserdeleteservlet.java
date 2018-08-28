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
public class BackstageUserdeleteservlet extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		System.out.println("userid:"+userid);
		
		String str = bss.Backstageuserdelete(userid)+"";
		
		if(str.equals("1")) {
			response.sendRedirect("usermanager.html");
		}else {
			request.getRequestDispatcher("http://ljxnb.xin");
		}
		
	}
}
