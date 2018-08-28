package com.silence.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.slience.Bean.UseraddBean;

import net.sf.json.JSONArray;

@WebServlet("/cc.aa")
public class Userload extends HttpServlet {
	UserService us = new UserServicelmp();
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("个人信息加载cc.aa----get请求");
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setContentType("text/html;Charset=utf-8");//设置响应的编码格式
		HttpSession session = request.getSession();
		String youxiang=(String) session.getAttribute("email");
		if(youxiang==null) {
			response.sendRedirect("login.html");
		}else {
	//		/*****************查询客户信息************************/
			List<Map> a =(List<Map>) us.UserService(youxiang);
			System.out.println("a.size():"+a.size());
			System.out.println(youxiang);
	//		获取客户信息
			String name = (String) a.get(0).get("Cname");
			String pwd  = (String) a.get(0).get("Cpwd");
			String sex  = (String) a.get(0).get("Csex");
			String bir  = (String) a.get(0).get("Cbirthday");
			String tell = (String) a.get(0).get("Cphone");
			String email = (String) a.get(0).get("Cemail");
			String total = (String) a.get(0).get("Ctotal");//用户积分
			String lenvel = (String) a.get(0).get("Clenvel");//等级
			String member =(String) a.get(0).get("Cmember");//是否会员(1/0)
			String id = (String)a.get(0).get("Cid");
			/**********************获取用户订单总金额信息****************************************/
			
			List<Map> sum =(List<Map>) us.UserMoney(id);
			String Money = (String) sum.get(0).get("sum");//总金额
			double integral = Double.valueOf(Money)/100.00;
			int integrate = Integer.parseInt(new java.text.DecimalFormat("0").format(integral));
			System.out.println("消费金额折叠成的积分:"+integrate);
			
				
			/*******************会员级别判断***********************************/
			/**
			 * 0-100   一级会员
			 * 100-300 二级会员
			 * 300-700 三级会员
			 * x--x*2+100  依次类推
			 *     */
			if(integrate>=0&&integrate<100) {
				lenvel="1";
			}else if(integrate>=100&&integrate<300){
				lenvel="2";
			}else if(integrate>=300&&integrate<700){
				lenvel="3";
			}else if(integrate>=700){
				lenvel="4";
			}else {
				System.out.println("账号异常，等级为0");
			}
			/**********************判断是否会员**********************/
			if(Integer.parseInt(member)==0) {
				member="普通用户";
			}else  {
				member="VIP用户";
			}
	
			/*   json信息 */
			Map user = new HashMap();
			user.put("id", id);
			user.put("name", name);
			user.put("sex", sex);
			user.put("bir", bir);
			user.put("tell", tell);
			user.put("email", email);
			user.put("total", integrate);
			user.put("lenvel", lenvel);
			user.put("member", member);
			List userList = new ArrayList();
			userList.add(user);
			JSONArray jsonarr = JSONArray.fromObject(userList);
			response.getWriter().write(jsonarr.toString());
	}

	}


}
