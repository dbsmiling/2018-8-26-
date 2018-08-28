package com.wu.Servalet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wu.Service.LoginService;
import com.wu.Service.LoginServiceimp;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class adminLogin
 */
@WebServlet("/adminLogin")
public class adminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> map=new HashMap<>();
		LoginService lService=new LoginServiceimp();
		String adminid=request.getParameter("adminid");
		String adminpwd=request.getParameter("adminpwd");
		HttpSession session=request.getSession();
		System.out.println(adminid+" / "+adminpwd);
		
		int i=lService.adminquery(adminid, adminpwd);
		if (i==0) {
			map.put("code", "0004");
			JSONObject jsob=JSONObject.fromObject(map);
	        response.getWriter().write(jsob.toString());
		}else {
			map.put("code","0000");
			session.setAttribute("adminid", adminid);
			JSONObject jsob=JSONObject.fromObject(map);
	        response.getWriter().write(jsob.toString());
	        request.getRequestDispatcher("liang/index.html");
		}
		
	}

}
