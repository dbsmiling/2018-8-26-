package com.backstage.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage.service.BackstageService;
import com.backstage.service.BackstageServiceimp;

import net.sf.json.JSONObject;

/**
 * @author ����������
 * 	��̨�û���Ϣ�����߼���
 */
public class BackstageCommodityupdservlet extends HttpServlet{
	BackstageService bss = new BackstageServiceimp();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		System.out.println("进入商品信息更新");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String price = request.getParameter("price");
		String remake = request.getParameter("remake");
		String classid = request.getParameter("class");
		String pic= request.getParameter("pic");
		
		String str = bss.Backstagecommodityupd(name,id, price, remake, classid, pic)+"";
		
		Map<String,String> map = new HashMap();
		map.put("code",str);
		JSONObject jsonobject = JSONObject.fromObject(map);
		System.out.println(jsonobject);
		response.getWriter().write(jsonobject.toString());
		
	}
}
