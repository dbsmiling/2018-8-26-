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

import com.sun.xml.internal.ws.wsdl.writer.document.Service;
import com.wu.Been.UserList;
import com.wu.Service.LoginService;
import com.wu.Service.LoginServiceimp;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    LoginService lService=new LoginServiceimp();  
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map<String, String> map=new HashMap<>();
			HttpSession Session=request.getSession();
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			
			int a=lService.Loginquery(email);
			if (a==0) {
				map.put("code", "0003");
				JSONObject jsob=JSONObject.fromObject(map);
		        response.getWriter().write(jsob.toString());
			}else {
				List<Map> list=(List<Map>) lService.Loginquery(email, password);
				if (list.size()==0) {
					map.put("code","0004");
					JSONObject jsob=JSONObject.fromObject(map);
			        response.getWriter().write(jsob.toString());
				}else {
//					Session.setAttribute("email",email);
					map.put("code","0000");
					JSONObject jsob=JSONObject.fromObject(map);
			        response.getWriter().write(jsob.toString());
				}
			}
			
	}

}
