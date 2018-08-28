package com.ning.dao;

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
	
	/*
	 * (宁成龙)
	 * @see com.dao.Dao#commodityQuery(java.lang.String, java.lang.Object[])
	 */
	@Override
	public List<?> commodityQuery(String sql, Object[] obj) {
		getconn();
		List<Map> list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
			for(int j=0;j<obj.length;j++) {
				ps.setString(j+1, (String) obj[j]);
			}
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
				map.put("cbigclass", rs.getString(8));
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
	public List<?> userquery(String sql,Object[] obj) {
		getconn();
		List<Map> list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
			for(int j=0;j<obj.length;j++) {
				ps.setString(j+1, (String) obj[j]);
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
	public String Query_dt(String sql, Object[] obj) {
		getconn();
		String str=null;
		try {
			ps=con.prepareStatement(sql);
			for(int j=0;j<obj.length;j++) {
				ps.setString(j+1, (String) obj[j]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
		}finally {
			this.close();
		}
		return str;
	}
	
	
	@Override
	public int update(String sql, Object[] obj) {
		int i=0;
		getconn();
		try {
			ps= con.prepareStatement(sql);
			for(int j=0;j<obj.length;j++) {
				ps.setString(j+1, (String) obj[j]);
			}
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return i;
	}
	
	
	@Override
	public int CNquery(String sql, Object[] obj) {
		getconn();
		int i = 0;
		try {
			ps=con.prepareStatement(sql);
			for(int j=0;j<obj.length;j++) {
				ps.setString(j+1, (String) obj[j]);
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				i++;
			}
		} catch (SQLException e) {
			System.out.println("查询出错!");
		}finally {
			this.close();
		}
		return i;
	}
	
	
	
	@Override
	public List<?> Rushcomquery(String sql, Object[] obj) {
		getconn();
		List<Map> list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {
				ps.setString(i+1, (String) obj[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				Map map = new HashMap();
				map.put("Rcid", rs.getString(1));
				map.put("Rcname", rs.getString(2));
				map.put("Rcnum", rs.getString(3));
				map.put("Rcimgurl", rs.getString(5));
				map.put("Rcprice", rs.getString(6));
				map.put("Rnum", rs.getString(7));
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
	public List<?> CIMGQuery(String sql, Object[] obj) {
		getconn();
		List list=new ArrayList();
		try {
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {
				ps.setString(i+1, (String) obj[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close();
		
		return list;
	}

}
