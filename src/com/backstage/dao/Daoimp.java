package com.backstage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Daoimp implements Dao{

	Connection con=null;
	ResultSet rs=null;
	Statement sm=null;
	PreparedStatement ps=null;

	/**
	 * 获取connection连接
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
	
	/**
	 * 用户信息查询
	 */
	@Override
	public List<?> userquery(String sql) {
		getconn();
		List<Map> list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
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
	
	/* (non-Javadoc)
	 * @see com.dao.Dao#userupd(java.lang.String)
	 *    用户信息更新
	 */
	@Override
	public int userupd(String sql) {
		int i=0;
		getconn();
		try {
			ps= con.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return i;
	}

	/* (non-Javadoc)
	 * @see com.dao.Dao#commodityquery(java.lang.String)
	 * 商品查询
	 */
	@Override
	public List<?> commodityquery(String sql) {
		//1,鏉╃偞甯�
				getconn();
				//2.閹笛嗩攽鐠囶厼褰�
				List<Map> list=new ArrayList();
				try {
					ps=con.prepareStatement(sql);
					rs=ps.executeQuery();
					while(rs.next()) {
						Map map = new HashMap();
						map.put("cser_num", rs.getInt(1));
						map.put("cid", rs.getString(2));
						map.put("cname", rs.getString(3));
						map.put("cprice", rs.getDouble(4));
						map.put("cremark", rs.getString(5));
						map.put("cclassify", rs.getString(6));
						map.put("Cimgurl", rs.getString(7));
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
	public int commodityupd(String sql) {
		int i=0;
		getconn();
		try {
			ps= con.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return i;
	}
	@Override
	public List<?> orderquery(String sql) {
		getconn();
		List<Map> list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Map map = new HashMap();
				map.put("Oser_num", rs.getInt(1));
				map.put("O_Cid", rs.getString(2));
				map.put("Oid", rs.getString(3));
				map.put("Ogenerdate", rs.getString(4));
				map.put("Omoney", rs.getString(5));
				map.put("Ostate", rs.getString(6));
				map.put("O_cname", rs.getString(7));
				map.put("O_Scom_quan", rs.getString(8));
				map.put("OleaveM", rs.getString(9));
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
	public int orderupd(String sql) {
		int i=0;
		getconn();
		try {
			ps= con.prepareStatement(sql);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return i;
	}
	@Override
	public int adminquery(String sql) {
		int i=0;
		getconn();
		List<Map> list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Map map = new HashMap();
				map.put("name", rs.getString(2));
				map.put("pwd", rs.getString(3));
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		System.out.println(list.size());
		return list.size();
	}


}
