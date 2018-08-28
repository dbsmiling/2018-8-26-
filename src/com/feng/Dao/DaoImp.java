package com.feng.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.ISTORE;

public class DaoImp implements Dao {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	//连接数据库
	public void getcon(){
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//关闭连接
	public void close(){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//查询客户表
	@Override
	public List<?> Cusquery(String sql, Object[] obj) {
		List list= new ArrayList();
		try {
			getcon();
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setString(i+1, (String) obj[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				Map map=new HashMap();
				map.put("Cid",rs.getString(2));
				list.add(map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	//查询购物车表
	@Override
	public List<?> Shopquery(String sql, Object[] obj) {
		List list= new ArrayList();
		try {
			getcon();
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setString(i+1,(String)obj[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				Map map=new HashMap();
				map.put("Snum",rs.getString(1));
				map.put("sc_id",rs.getString(2));
				map.put("SCID",rs.getString(3));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	//查询商品表
	@Override
	public List<?> Comquery(String sql, Object[] obj) {
		List list= new ArrayList();
		try {
			getcon();
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setString(i+1,(String)obj[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				Map map=new HashMap();
				map.put("cser_num",rs.getString(1));
				map.put("cid",rs.getString(2));
				map.put("cname",rs.getString(3));
				map.put("cprice",rs.getString(4));
				map.put("cremark",rs.getString(5));
				map.put("cclassify",rs.getString(6));
				map.put("cimgurl",rs.getString(7));
				list.add(map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	
	//增删改
	@Override
	public int del(String sql,Object[] obj) {
		int i=0;
		getcon();
		try {
			ps=con.prepareStatement(sql);
			for(int j=0;j<obj.length;j++){
				ps.setString(j+1, (String) obj[j]);
			}
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	//查询地址表
	@Override
	public List<?> addquery(String sql, Object[] obj) {
		List list= new ArrayList();
		try {
			getcon();
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setString(i+1,(String)obj[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				Map map=new HashMap();
				map.put("SACid",rs.getString(1));
				map.put("SA_add",rs.getString(2));
				map.put("SA_phone",rs.getString(3));
				map.put("SAname",rs.getString(4));
				map.put("addid",rs.getString(5));
				list.add(map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	
	//查询订单表
	@Override
	public List<?> orderquery(String sql, Object[] obj) {
		List list= new ArrayList();
		try {
			getcon();
			ps=con.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setString(i+1,(String)obj[i]);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				Map map=new HashMap();
				map.put("Oser_num",rs.getString(1));
				map.put("O_Cid",rs.getString(2));
				map.put("Oid",rs.getString(3));
				map.put("Ogenerdate",rs.getString(4));
				map.put("Omoney",rs.getString(5));
				map.put("Ostate",rs.getString(6));
				map.put("O_cname",rs.getString(7));
				map.put("O_Scom_quan",rs.getString(8));
				map.put("OleaveM",rs.getString(9));
				list.add(map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			close();
		}
		return list;
	}
	
	
	
	
}
