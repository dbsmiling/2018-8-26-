package com.silence.Daolmp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silence.Dao.UserDao;
import com.slience.Bean.UseraddBean;

public class UserDaolmp implements UserDao {

	Connection con=null;
	ResultSet rs=null;
	Statement sm=null;
	PreparedStatement ps=null;
	@Override
	public List<?> query(String sql,String str[]) {
		getconn();
		List<Map> list = new ArrayList();
		try {
			ps= con.prepareStatement(sql);
			for(int i=0;i<str.length;i++) {
				ps.setString(i+1, (String) str[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				Map map = new HashMap();
				map.put("Cser_num", rs.getInt(1));
				map.put("Cid", rs.getString(2));
				map.put("Cname", rs.getString(3));
				map.put("Cpwd", rs.getString(4));
				map.put("Csex", rs.getString(5));
				map.put("Clenvel", rs.getString(6));
				map.put("Cbirthday", rs.getString(7));
				map.put("Cphone", rs.getString(8));
				map.put("Cemail", rs.getString(9));
				map.put("Cregis_date", rs.getString(10));
				map.put("Clast_date", rs.getString(11));
				map.put("Cmember", rs.getString(12));
				map.put("Ctotal", rs.getString(13));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return list;
	}

	@Override
	public int upd(String sql,String str[]) {
		int y =0;
		getconn();
		try {
			ps=con.prepareStatement(sql);
			for(int i=0;i<str.length;i++) {
				ps.setString(i+1, str[i]);
			}
			y =ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return y;
	}
	/**
	 * 获得连接
	 */
	public void getconn(){
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭连接
	 */
	public void close() {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//查询收货地址表
	@Override
	public List<?> query_add(String sql, String str) {
		getconn();//连接数据库
		List list = new ArrayList();
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1,str);
//			for(int i=0;i<1;i++) {
//				ps.setString(i+1, (String) str);
//			}
			rs=ps.executeQuery();
			while(rs.next()) {
//				UseraddBean address = new UseraddBean();
//				address.setUserId(rs.getString(1));
//				address.setUserAdd(rs.getString(2));
//				address.setUserTell(rs.getString(3));
//				address.setUserName(rs.getString(4));
				Map map = new HashMap();
				map.put("userid", rs.getString(1));
				map.put("useradd", rs.getString(2));
				map.put("usertell", rs.getString(3));
				map.put("username", rs.getString(4));
				map.put("addid", rs.getString(5));
				list.add(map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return list;
	}

	//查询 订单表
	@Override
	public List<?> query_order(String sql, String str []) {
		getconn();
		List list1 = new ArrayList();
		try {
			ps= con.prepareStatement(sql);
			for(int i=0;i<str.length;i++) {
				ps.setString(i+1, str[i]);
			}
			System.out.println(ps);
			rs=ps.executeQuery();
			while(rs.next()) {
				Map UserOrder = new HashMap();
				UserOrder.put("UserId", rs.getString(2));
				UserOrder.put("UserOid", rs.getString(3));
				UserOrder.put("UserDate", rs.getString(4));
				UserOrder.put("UserMoney", rs.getString(5));
				UserOrder.put("UserStatus", rs.getString(6));
				UserOrder.put("UserShopId", rs.getString(7));
				UserOrder.put("UserShopnum", rs.getString(8));
				UserOrder.put("UserRemark", rs.getString(9));
				list1.add(UserOrder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return list1;
	}

	//根据商品ID查询商品信息
	@Override
	public List<?> query_shop(String sql, String str[]) {
		getconn();
		List list2 = new ArrayList();
		try {
			ps= con.prepareStatement(sql);
			for(int i=0;i<str.length;i++) {
				ps.setString(i+1, str[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				Map shop = new HashMap();
				shop.put("shopId", rs.getString(2));
				shop.put("shopName", rs.getString(5));
				shop.put("ShopUnitPrice", rs.getString(4));
				shop.put("shopImg", rs.getString(7));
				list2.add(shop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return list2;
	}
	//根据ID查询订单总金额
	@Override
	public List<?> query_money(String sql, String id) {
		getconn();
		List money = new ArrayList();
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			while(rs.next()) {
				Map num = new HashMap();
				num.put("sum", rs.getString(1));
				money.add(num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return money;
	}
	//根据Id 获取一个有多少订单
	@Override
	public List<?> query_allOrders(String sql, String id) {
		getconn();
		List allOrders = new ArrayList();
		try {
			ps= con.prepareStatement(sql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			while(rs.next()) {
				Map num = new HashMap();
				num.put("sum", rs.getString(1));
				allOrders.add(num);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return allOrders;
	}
}
