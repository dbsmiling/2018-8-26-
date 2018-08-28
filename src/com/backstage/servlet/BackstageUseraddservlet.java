package com.backstage.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

import net.sf.json.JSONObject;

public class BackstageUseraddservlet extends HttpServlet {
	
	BackstageService bss = new BackstageServiceimp();
	
	public static int getRandNum(int min, int max) {
	    int randNum = min + (int)(Math.random() * ((max - min) + 1));
	    return randNum;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("name");
		Map map = new HashMap();
		if(pwd.equals(pwd2)) {
			Date date= new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String loginTime = sdf.format(date);//当前时间生成的注册时间
			String id=null;
			int i=0;
			do {
				id=getRandNum(10000,99999)+"";//随机生成的用户id
				System.out.println(id);
				i=bss.Backstageuserqueryid(id);
			}while(i>=1);
			
			int b=bss.Backstageuserqueryemail(email);//int b 查询是否数据库存在该条信息 b=0 不存在 b=1 已存在
			if (b==0) {
				int a=bss.Backstageuserqueryadd(id,name,pwd,email,loginTime);
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
				System.out.println(jsob);
		        response.getWriter().write(jsob.toString());
			}
		}else {
			map.put("code","0003");
			JSONObject jsob=JSONObject.fromObject(map);
			System.out.println(jsob);
	        response.getWriter().write(jsob.toString());
		}
		
	}

}
