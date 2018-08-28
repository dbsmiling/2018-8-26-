package com.feng.Service;

import java.util.List;

import com.feng.Dao.Dao;
import com.feng.Dao.DaoImp;

public class ServiceImp implements Service {
	Dao dao =new DaoImp();
	
	//查询客户表语句
	@Override
	public List<?> Cusquery(String cemail) {
		String sql="SELECT * FROM Customer WHERE cemail=?";
		Object[] obj={cemail==null?"":cemail};
		return dao.Cusquery(sql, obj);
	}
	//查询购物车语句
	@Override
	public List<?> Shopquery(String id) {
		String sql="SELECT * FROM Shop_cart WHERE SCID=?";
		Object[] obj={id==null?"":id};
		return dao.Shopquery(sql, obj);
	}
	//查询商品信息语句
	@Override
	public List<?> Comquery(String cid) {
		String sql="SELECT * FROM Commodity WHERE cid=?";
		Object[] obj={cid==null?"":cid};
		return dao.Comquery(sql, obj);
	}
	//删除购物车中的商品
	@Override
	public int delupd(String id,String uid) {
		String sql="DELETE FROM Shop_cart WHERE sc_id=? AND SCID=?";
		Object[] obj={id==null?"":id,uid==null?"":uid};
		return dao.del(sql, obj);
	}
	//将购物车中的信息加入到订单
	@Override
	public int inser(String O_Cid, String Oid, String Ogenerdate, String Omoney, String Ostate, String O_cname,String O_Scom_quan) {
		String sql="INSERT INTO Orders(O_Cid,Oid,Ogenerdate,Omoney,Ostate,O_cname,O_Scom_quan)VALUES(?,?,?,?,?,?,?);";
		Object[] obj={O_Cid==null?"":O_Cid,Oid==null?"":Oid,Ogenerdate==null?"":Ogenerdate,Omoney==null?"":Omoney,Ostate==null?"":Ostate,O_cname==null?"":O_cname,O_Scom_quan==null?"":O_Scom_quan};
		return dao.del(sql, obj);
	}
	//清空购物车
	@Override
	public int drop(String uid) {
		String sql="DELETE FROM Shop_cart WHERE SCID=?";
		Object[] obj={uid==null?"":uid};
		return dao.del(sql, obj);
	}
	//查询地址
	@Override
	public List<?> addquery(String uid) {
		String sql="SELECT * FROM Ship_add WHERE SACid=?";
		Object[] obj={uid==null?"":uid};
		return dao.addquery(sql, obj);
	}
	//查询订单
	@Override
	public List<?> orderquery(String uid,String one) {
		String sql="SELECT * FROM Orders WHERE O_Cid=? AND Ostate=?";
		Object[] obj={uid==null?"":uid,one==null?"":one};
		return dao.orderquery(sql, obj);
	}
	@Override
	public int orderchange(String uid, String one) {
		String sql="UPDATE Orders SET Ostate=2 WHERE O_Cid=? AND Ostate=?";
		Object[] obj={uid==null?"":uid,one==null?"":one};
		return dao.del(sql, obj);
	}

}
