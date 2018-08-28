package com.feng.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.feng.Service.Service;
import com.feng.Service.ServiceImp;

import net.sf.json.JSONObject;

public class OrderServlet extends HttpServlet {
	Service se=new ServiceImp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���붩������");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cemail=(String) session.getAttribute("email");
		List<Map> list= (List<Map>) se.Cusquery(cemail);//��ѯ�ͻ���
		String id=(String)list.get(0).get("Cid");	
		List<Map> list2=(List<Map>) se.addquery(id);//��ѯ��ַ��
		String ad=null;
		String tel=null;
		String sjr=null;
		for(int i=0;i<list2.size();i++){
			ad=(String) list2.get(i).get("SA_add");
			tel=(String) list2.get(i).get("SA_phone");
			sjr=(String) list2.get(i).get("SAname");
		}
		Map map =new HashMap();
		map.put("ad", ad);
		map.put("tel", tel);
		map.put("sjr", sjr);
		JSONObject adress = JSONObject.fromObject(map);
		response.getWriter().write(adress.toString());
		System.out.println(adress.toString());
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("������orderServlet");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cemail=(String) session.getAttribute("email");
		List<Map> list= (List<Map>) se.Cusquery(cemail);//��ѯ�ͻ���
		String id=(String)list.get(0).get("Cid");
		String one="1";
		List<Map> list2=(List<Map>) se.orderquery(id,one);//ֻ��ѯ�ͻ�����״̬Ϊ1��
		Map map = new HashMap();
		Map map1 = new HashMap();
		String[] str = null;
		List<Map> list4 = null;
		String sq="";
		for(int i=0;i<list2.size();i++){
			Map map2 = new HashMap();
			String cid=(String) list2.get(i).get("O_cname");//��ȡ��Ʒ
			String cnum=(String) list2.get(i).get("O_Scom_quan");//��ȡ��������Ʒ������
			String cmoeny=(String) list2.get(i).get("Omoney");//��ȡ��Ʒ���
			List<Map> list3 = (List<Map>) se.Comquery(cid);
			if(list3.size()>0){
				map2.put("cmoney", cmoeny);
				map2.put("cnum", cnum);
				map2.put("goods", list3);
				sq=String.valueOf((i+1));
				map1.put(sq, map2);
			}else{
				map1=null;
			}
		}
		JSONObject order = JSONObject.fromObject(map1);
		response.getWriter().write(order.toString());
		System.out.println(order.toString());
	}
}
