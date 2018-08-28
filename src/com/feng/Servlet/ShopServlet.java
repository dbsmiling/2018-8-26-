package com.feng.Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.MacSpi;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.event.ListDataEvent;

import com.feng.Service.Service;
import com.feng.Service.ServiceImp;
import com.mysql.cj.xdevapi.JsonArray;


import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

public class ShopServlet extends HttpServlet{
	Service se=new ServiceImp();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("��Ӷ�����ɾ�����ﳵ");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String state=(String) session.getAttribute("state");
		String cemail=(String) session.getAttribute("email");
		List<Map> list= (List<Map>) se.Cusquery(cemail);//�����û������ѯid
		String id=(String)list.get(0).get("Cid");
		List<Map> list1= (List<Map>) se.Shopquery(id);//��ѯ���ﳵ�е���Ϣ
		for(int i=0;i<list1.size();i++){
			String sc_id = (String) list1.get(i).get("sc_id");//�ڹ��ﳵ�л�ȡ��Ʒid
			String snum = (String) list1.get(i).get("Snum");//��ȡ��Ʒ����
			List<Map> list2 = (List<Map>) se.Comquery(sc_id);//��ѯ��Ʒ��
			String money=(String) list2.get(0).get("cprice");//��ȡ��Ʒ����
			 double summoney =Double.valueOf(snum)*Double.valueOf(money);
			 String sum_money=String.valueOf(summoney);
			//����Ϣ��ӵ�������s
			 Date d =new Date();
			 SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			 int rodm=(int) (Math.random()*99999);
			 String suiji=rodm+"";
			 int suc= se.inser(id, suiji, df.format(d), sum_money, "1", sc_id, snum);
		}
		//ɾ�����ﳵ
		Map map1=new HashMap();
		int drop=se.drop(id);
			if(drop==0){
				map1=null;
			}else{
				map1.put("success", 1);
			}
			JSONObject ob = JSONObject.fromObject(map1);
			response.getWriter().write(ob.toString());
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("���뵽���ﳵservlet");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String state=(String) session.getAttribute("state");
		String cemail=(String) session.getAttribute("email");
		List<Map> list= (List<Map>) se.Cusquery(cemail);//�����û������ѯid
		if(list.size()>0){
			String id=(String)list.get(0).get("Cid");
			List<Map> list1= (List<Map>) se.Shopquery(id);//��ѯ�ͻ��Ĺ��ﳵ
			Map map1 = new HashMap();
			String sq="";
			for (int i = 0; i < list1.size(); i++) {
				Map map2 = new HashMap();
				String snum = (String) list1.get(i).get("Snum");//���ﳵ��Ʒ������
				String sc_id = (String) list1.get(i).get("sc_id");//��Ʒid
				List<Map> list2 = (List<Map>) se.Comquery(sc_id);//��ѯ��Ʒ�����Ϣ
				if(list2.size()>0){
					map2.put("snum", snum);
					map2.put("goods", list2);
					sq=String.valueOf((i+1));
					map1.put(sq, map2);
				}else{
					map1=null;
				}
			}	
			JSONObject js = JSONObject.fromObject(map1);
			response.getWriter().write(js.toString());
		}
	}
}
