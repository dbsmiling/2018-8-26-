package com.silence.Sevicelmp;

import java.util.List;

import com.silence.Dao.UserDao;
import com.silence.Daolmp.UserDaolmp;
import com.silence.Sevice.UserService;

public class UserServicelmp implements UserService{
	UserDao dao = new UserDaolmp(); 
	/* 
	 * 根据邮箱查客户信息    控制层获取对应的信息
	 */
	@Override
	public List<?> UserService(String Cemail) {
		String sql = "select * from Customer where Cemail = ?";
		String str[] = {Cemail};
		return dao.query(sql,str);
	}
	/* 
	 * 根据客户邮箱修改客户表信息    
	 */
	@Override
	public int updCustomer(String name,String pwd,String sex,String bir,String tell,String Cemail) {
		String sql ="UPDATE Customer SET Cname =?,Cpwd=?,Csex=?,Cbirthday=?,Cphone=? WHERE Cemail = ?";
		String str[]= {name==null?"":name,pwd==null?"":pwd,sex==null?"":sex,bir==null?"":bir,tell==null?"":tell,Cemail==null?"":Cemail};
		return dao.upd(sql,str);
	}
	/* 
	 * 根据客户id 添加客户地址
	 */
	@Override
	public int updAdd(String add,String tell,String name,String id) {
		String sq2="INSERT INTO Ship_add (SACid,SA_add,SA_phone,SAname) VALUES(?,?,?,?)";
		String str[]= {id==null?"":id,add==null?"":add,tell==null?"":tell,name==null?"":name};
		return dao.upd(sq2,str);
	}
	/* 
	 * 根据客户id查询地址表
	 */
	@Override
	public List<?> UserAdd(String id) {
		String sql="SELECT * FROM Ship_add WHERE SACid = ? ";
		if(id!=null) {id=id;}else {id="";}
		return dao.query_add(sql, id);
	}
	/* 
	 * 根据客户id查询订单表
	 */
	@Override
	public List<?> UserOrder(String id) {
		String str[] = {id==null?"":id};
		String sql="SELECT * FROM Orders WHERE O_Cid=? LIMIT 0,4";
		System.out.println(id+"wozaizheli");
		return dao.query_order(sql, str);
	}
	/**
	 * 根据商品ID查询商品信息
	 */
	@Override
	public List<?> UserShop(String shopid1,String shopid2,String shopid3,String shopid4) {
		String sql ="SELECT * FROM Commodity WHERE cid=? or cid =? or cid=? or cid=?";
		String str[] = {shopid1==null?"":shopid1,shopid2==null?"":shopid2,shopid3==null?"":shopid3,shopid4==null?"":shopid4};
		return dao.query_shop(sql, str);
	}
	/**
	  * 查询总金额
	 **/
	@Override
	public List<?> UserMoney(String id) {
		String sql="SELECT SUM(Omoney) FROM Orders GROUP BY O_Cid=?";
		if(id!=null) {
			id=id;
		}else {
			id="";
		}
		return dao.query_money(sql, id);
	}
	
	/**
	  * 修改收货地址
	 **/
	@Override
	public int changeAdd(String add, String tell, String name, String id,String addid) {
		String sql="update Ship_add set SA_add=?,SA_phone=?,SAname=? where SACid=? AND addid=?";
		String str[] = {add==null?"":add,tell==null?"":tell,name==null?"":name,id==null?"":id,addid==null?"":addid};
		return dao.upd(sql, str);
	}
	
	/* 
	 * 删除订单   根据商品订单编号
	 */
	@Override
	public int delShop(String shopID) {
		String sql="DELETE FROM Orders WHERE Oid = ?;";
		String str[] = {shopID==null?"":shopID};
		return dao.upd(sql, str);
	}
	/* 
	 * 根据收货人手机号  删除收件地址
	 */
	@Override
	public int delAdd(String tell) {
		String sql ="DELETE FROM Ship_add WHERE addid = ?;";
		String str[]= {tell==null?"":tell};
		return dao.upd(sql, str);
	}
	//根据客户id   获取订单总量
	@Override
	public List<?> allOrders( String id) {
		String sql ="SELECT COUNT(*) FROM Orders WHERE O_Cid=?;";
		if(id!=null) {
			id=id;
		}else {
			id="";
		}
		return dao.query_allOrders(sql, id);
	}
	
	
}
