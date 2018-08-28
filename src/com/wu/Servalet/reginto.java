package com.wu.Servalet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.wu.Service.LoginService;
import com.wu.Service.LoginServiceimp;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class reginto
 */
@WebServlet("/reginto")
public class reginto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	public static int getRandNum(int min, int max) {
	    int randNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randNum;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		LoginService lService=new LoginServiceimp();
		Map<String, String> map=new HashMap<>();
		//设置编码格式
		String email=request.getParameter("email");//接收的邮箱地址
		String password=request.getParameter("password");//接收的密码
		String username=request.getParameter("names");//接收的用户名
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String loginTime = sdf.format(date);//当前时间生成的注册时间
		String id=null;
		int i=0;
		do {
			id=getRandNum(10000,99999)+"";//随机生成的用户id
			System.out.println(id);
			i=lService.adminquery(id);
		}while(i>=1);
		
		int b=lService.Loginquery(email);//int b 查询是否数据库存在该条信息 b=0 不存在 b=1 已存在
		if (b==0) {
			int a=lService.Loginupd(email, password, username, loginTime, id);
			if (a==1) {
				map.put("code", "0000");
				JSONObject jsob=JSONObject.fromObject(map);
		        response.getWriter().write(jsob.toString());
			}else {
				map.put("code","0002");
				JSONObject jsob=JSONObject.fromObject(map);
		        response.getWriter().write(jsob.toString());
			}
		}else {
			map.put("code","0001");
			JSONObject jsob=JSONObject.fromObject(map);
	        response.getWriter().write(jsob.toString());
		}
		
		
		
		
		
		
	}

}
