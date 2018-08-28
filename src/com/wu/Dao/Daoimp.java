package com.wu.Dao;

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
	 * 鏉╃偞甯撮弫鐗堝祦鎼达拷
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
	 * 閸忔娊妫撮弫鐗堝祦鎼达拷
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
	 * 閺屻儴顕楃拠顓炲綖閿涘瞼娅ラ梽锟�
	 */
	@Override
	public List<?> query(String sql) {
		//1,鏉╃偞甯�
		getconn();
		//2.閹笛嗩攽鐠囶厼褰�
		List<Map> list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				Map<String, Comparable> map = new HashMap();
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
	public int upd(String sql) {
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
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			if (rs.next()) {
				i=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
		
		
	}


}
