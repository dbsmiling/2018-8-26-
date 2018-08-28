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

import net.sf.json.JSONArray;

@WebServlet("/ee.aa")
public class UserOrderServlet extends HttpServlet {
	UserService us = new UserServicelmp();
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserOrderServlet--get请求");
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setContentType("text/html;Charset=utf-8");//设置响应的编码格式
		
		HttpSession session = request.getSession();
		String youxiang = (String) session.getAttribute("email");
		List<Map> list = (List<Map>) us.UserService(youxiang);
		String id = (String) list.get(0).get("Cid");//客户id
		
		List<Map> sum =(List<Map>) us.UserOrder(id);
		String shopID = (String) sum.get(0).get("UserShopId");
		System.out.println("要删除的商品id"+shopID);
		
		
		/**********************删除订单******************/
		int y = us.delShop(shopID);
		if(y==1) {
			System.out.println("订单删除成功");
		}else {
			System.out.println("订单删除失败");
		}
		
		/*********************查询订单表********************/
		
		String  page =request.getParameter("page");//订单页数
		List<Map> UserOrder=  (List<Map>) us.UserOrder(id);
		String id1 =(String) UserOrder.get(0).get("UserShopId");
		String id2 =(String) UserOrder.get(1).get("UserShopId");
		String id3 =(String) UserOrder.get(2).get("UserShopId");
		String id4 =(String) UserOrder.get(3).get("UserShopId");
		if(id1==null) {id1="";}else {id1=id1;}
		if(id2==null) {id2="";}else {id2=id2;}
		if(id3==null) {id3="";}else {id3=id3;}
		if(id4==null) {id4="";}else {id4=id4;}
		List<Map> Shop = (List<Map>) us.UserShop(id1,id2,id3,id4);//查询商品表
		Map  table  = new HashMap();
		table.put("one", UserOrder);
		table.put("two", Shop);
		table.put("sum", sum);
		List Send = new ArrayList();
		Send.add(table);
		System.out.println("Shop.size():"+Shop.size());
		JSONArray array = JSONArray.fromObject(Send);
	    response.getWriter().write(array.toString());
	}


}

