package com.wu.Service;

import java.util.List;

import com.wu.Dao.Dao;
import com.wu.Dao.Daoimp;

public interface LoginService {

	Dao dao = new Daoimp();
	
	public int Loginquery(String email);
	public int Loginupd(String email,String password,String username,String loginTime,String id);
	public int Forgetupd(String newpwd,String email);
	public List<?> Loginquery(String email,String password);
	public int adminquery(String adminid,String adminpwd);
	public int adminquery(String id);
}
