package com.backstage.service;

import java.util.List;
import java.util.Map;

import com.backstage.dao.Dao;
import com.backstage.dao.Daoimp;

/**
 * @author 嘉如新上人
 *	后台服务层接口
 */
public interface BackstageService {

	Dao dao = new Daoimp();
	
	
	int login(String id,String pwd);
	//
	public List<?> Backstageuserquery();
	public List<?> Backstageuserquery(String page,String limit);
	public int Backstageuserdelete(String userid);
	public int Backstageuserupd(String name,String userid,String pwd,String sex,String bir,String email,String phone,String total);
	int Backstageuserqueryid(String id);
	int Backstageuserqueryemail(String email);
	int Backstageuserqueryadd(String id,String name,String pwd,String email,String time);
	//
	public List<?> Backstagecommodityquery();
	public List<?> Backstagecommodityquery(String page,String limit);
	int BackstageCommoditydelete(String commdity);
	public int Backstagecommodityupd(String name,String id,String price,String remake,String classid,String pic);
	int Backstagecommodityqueryid(String id);
	int Backstageusercommodityadd(String id,String name,String pri,String drtails,String ify,String picname);
	//
	public List<?> Backstageorderquery();
	public List<?> Backstageorderprocessquery();
	public List<?> Backstageorderprocessquery(String page,String limit);
	public List<?> Backstageorderquery(String limit,String page);
	int Backstageorderdelete(String orderid);
	public int Backstageorderupd(String name,String id,String price,String remake,String classid,String pic);


}
