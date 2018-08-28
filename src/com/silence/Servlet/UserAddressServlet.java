package com.silence.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.silence.Sevice.UserService;
import com.silence.Sevicelmp.UserServicelmp;

import net.sf.json.JSONArray;


@WebServlet("/dd.aa")
public class UserAddressServlet extends HttpServlet {
	UserService us = new UserServicelmp();
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserAddressServlet--get请求");
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setContentType("text/html;Charset=utf-8");//设置响应的编码格式
		/***********************删除地址(根据用户手机号)****************************/
		String userTell = request.getParameter("tell");
		int y = us.delAdd(userTell);
		if(y==1) {
			System.out.println("地址删除成功");
		}else {
			System.out.println("地址删除失败");
		}
		/*********************************获取用户id**/
		
		HttpSession session = request.getSession();
		String youxiang = (String) session.getAttribute("email");//获取session里的邮箱信息
//		String em = "gmail@gamil.com";//临时的
		List<Map> a =(List<Map>)us.UserService(youxiang);
		String userid=(String) a.get(0).get("Cid");//客户id 
		
		/***********************获取用户收货地址********************************/
		 List<Map> userAdd = (List<Map>) us.UserAdd(userid);
	     System.out.println("userAdd.size():"+userAdd.size());
	     /***********************把数据输出到前台********************************/
	     JSONArray array = JSONArray.fromObject(userAdd);
	     response.getWriter().write(array.toString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserAddressServlet--post请求");
	}

}
