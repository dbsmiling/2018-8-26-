package com.wu.Servalet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wu.Service.LoginService;
import com.wu.Service.LoginServiceimp;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class forgetto
 */
@WebServlet("/forgetto")
public class forgetto extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> map=new HashMap<>();
		LoginService lService=new LoginServiceimp();
		String newpwd=request.getParameter("newpwd");
		String email=request.getParameter("email");
		
			int a=lService.Forgetupd(newpwd,email);
			if (a==1) {
				map.put("code", "0000");
				JSONObject jsob=JSONObject.fromObject(map);
		        response.getWriter().write(jsob.toString());
			}else {
				map.put("code","0002");
				JSONObject jsob=JSONObject.fromObject(map);
		        response.getWriter().write(jsob.toString());
			}
			
			
		}
	
	}


