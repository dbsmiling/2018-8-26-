package com.backstage.service;

import java.util.List;
import java.util.Map;

/**
 * @author ����������
 *	��̨�����
 */
public class BackstageServiceimp implements BackstageService{

	@Override
	public List Backstageuserquery() {
		String sql = "SELECT * FROM Customer";
		List list = dao.userquery(sql);
		return list;
	}

	@Override
	public int Backstageuserdelete(String userid) {
		String sql = "DELETE FROM Customer WHERE Cid="+userid+"";
		int i = dao.userupd(sql);
		return i;
	}

	@Override
	public List Backstagecommodityquery() {
		String sql = "SELECT * FROM Commodity";
		List list = dao.commodityquery(sql);
		return list;
	}

	@Override
	public int Backstageuserupd(String name,String userid,String pwd,String sex,String bir,String email,String phone,String total) {
		String sql = "UPDATE Customer SET Cname='"+name+"',Cpwd='"+pwd+"',Csex='"+sex+"',Cbirthday='"+bir+"',Cemail='"+email+"',Cphone='"+phone+"',Ctotal='"+total+"' WHERE Cid="+userid+"";
		System.out.println(sql);
		int i = dao.userupd(sql);
		return i;
	}

	@Override
	public List<?> Backstageuserquery(String page, String limit) {
		int i = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
		String pin =""+i+","+limit+"";
		String sql = "SELECT * FROM Customer limit "+pin+"";
		System.out.println(sql);
		List list = dao.userquery(sql);
		return list;
	}

	@Override
	public int BackstageCommoditydelete(String commdity) {
		String sql = "DELETE FROM Commodity WHERE Cid="+commdity+"";
		int i = dao.userupd(sql);
		return i;
	}

	@Override
	public int Backstagecommodityupd(String name, String id, String price, String remake, String classid, String pic) {
		String sql = "UPDATE Commodity SET cname='"+name+"',cprice='"+price+"',cremark='"+remake+"',cclassify='"+classid+"',Cimgurl='"+pic+"' WHERE cid="+id+"";
		System.out.println(sql);
		int i = dao.commodityupd(sql);
		return i;
	}

	@Override
	public List<?> Backstageorderquery() {
		String sql = "SELECT * FROM `Orders`";
		List list = dao.orderquery(sql);
		return list;
	}

	@Override
	public int Backstageorderdelete(String orderid) {
		String sql = "DELETE FROM `Orders` WHERE O_Cid="+orderid+"";
		int i = dao.userupd(sql);
		return i;
	}

	@Override
	public int Backstageorderupd(String name, String id, String price, String remake, String classid, String pic) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int Backstageuserqueryid(String id) {
		String sql = "SELECT * FROM Customer WHERE Cid="+id+"";
		List list = dao.userquery(sql);
		if(list.size()>0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int Backstageuserqueryemail(String email) {
		String sql = "SELECT * FROM Customer WHERE Cemail='"+email+"'";
		List list = dao.userquery(sql);
		if(list.size()>0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int Backstageuserqueryadd(String id, String name, String pwd, String email, String time) {
		String sql = "INSERT INTO Customer(Cid,Cname,Cpwd,Csex,Clenvel,Cemail,Cregis_date) VALUES("+id+",'"+name+"','"+pwd+"','男',1,'"+email+"','"+time+"')";
		System.out.println(sql);
		return dao.userupd(sql);
	}

	@Override
	public int Backstagecommodityqueryid(String id) {
		String sql = "SELECT * FROM Commodity WHERE cid="+id+"";
		List list = dao.userquery(sql);
		if(list.size()>0) {
			return 1;
		}
		return 0;
	}

	@Override
	public int Backstageusercommodityadd(String id,String name, String pri, String drtails, String ify, String picname) {
		String sql = "INSERT INTO Commodity(cid,cname,cprice,cremark,cclassify,Cimgurl) VALUES("+id+",'"+name+"','"+pri+"','"+drtails+"','"+ify+"','"+picname+"')";
		System.out.println(sql);
		return dao.userupd(sql);
	}

	@Override
	public int login(String id, String pwd) {
		String sql = "SELECT * FROM Admin WHERE Aname='"+id+"' AND Apwd='"+pwd+"'";
		System.out.println(sql);
		return dao.adminquery(sql);
	}

	@Override
	public List<?> Backstagecommodityquery(String page, String limit) {
		int i = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
		String pin =""+i+","+limit+"";
		String sql = "SELECT * FROM Commodity limit "+pin+"";
		System.out.println(sql);
		List list = dao.commodityquery(sql);
		return list;
	}

	@Override
	public List<?> Backstageorderquery(String limit, String page) {
		int i = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
		String pin =""+i+","+limit+"";
		String sql = "SELECT * FROM Orders limit "+pin+"";
		System.out.println(sql);
		List list = dao.orderquery(sql);
		return list;
	}

	@Override
	public List<?> Backstageorderprocessquery(String page,String limit) {
		int i = (Integer.valueOf(page)-1)*Integer.valueOf(limit);
		String pin =""+i+","+limit+"";
		String sql = "SELECT * FROM Orders WHERE Ostate=2 OR Ostate=1 limit "+pin+"";
		System.out.println(sql);
		List list = dao.orderquery(sql);
		return list;
	}

	@Override
	public List<?> Backstageorderprocessquery() {
		String sql = "SELECT * FROM Orders WHERE Ostate=2 OR Ostate=1";
		System.out.println(sql);
		List list = dao.orderquery(sql);
		return list;
	}



}
