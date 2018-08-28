package com.wu.Service;

import java.util.List;

public class LoginServiceimp implements LoginService{

	@Override
	public int Loginquery(String email) {
		String sql = "SELECT * FROM Customer WHERE Cemail='"+email+"'";
		int i = dao.query(sql).size();
		return i;
	}

	@Override
	public int Loginupd(String email,String password,String username,String loginTime,String id) {
		String sql = "INSERT INTO Customer (Cid,Cname,Cpwd,Cemail,Cregis_date) VALUES ("+id+",'"+username+"','" + 
				password+"','"+email+"','"+loginTime+"')";
		int i = dao.upd(sql);
		return i;
	}

	@Override
	public int Forgetupd(String newpwd,String email) {
		String sql="UPDATE Customer SET Cpwd = '"+newpwd+"' WHERE Cemail = '"+email+"'";
		int a=dao.upd(sql);
		return a;
	}

	@Override
	public List<?> Loginquery(String email, String password) {
		String sql="SELECT * FROM Customer WHERE Cemail='"+email+"' AND Cpwd='"+password+"'";
		List<?> list=dao.query(sql);
		return list;
	}

	@Override
	public int adminquery(String adminid, String adminpwd) {
		String sql="SELECT * FROM Admin WHERE Aname='"+adminid+"' AND Apwd='"+adminpwd+"'";
		int i=dao.adminquery(sql);
		return i;
	}

	@Override
	public int adminquery(String id) {
		String sql="SELECT * FROM Customer WHERE Cid='"+id+"'";
		int i=dao.adminquery(sql);
		return i;
	}

}
