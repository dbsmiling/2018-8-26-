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
	// 数据库用户信息操作
	List<?> Backstageuserquery();
	List<?> Backstageuserquery(String page,String limit);
	int Backstageuserdelete(String userid);
	int Backstageuserupd(String name,String userid,String pwd,String sex,String bir,String email,String phone,String total);
	int Backstageuserqueryid(String id);
	int Backstageuserqueryemail(String email);
	int Backstageuserqueryadd(String id,String name,String pwd,String email,String time);
	// 数据库商品操作
	List<?> Backstagecommodityquery();
	List<?> Backstagecommodityquery(String page,String limit);
	int BackstageCommoditydelete(String commdity);
	int Backstagecommodityupd(String name,String id,String price,String remake,String classid,String pic);
	int Backstagecommodityqueryid(String id);
	int Backstageusercommodityadd(String id,String name,String pri,String drtails,String ify,String picname);
	// 数据库订单
	List<?> Backstageorderquery();
	List<?> Backstageorderprocessquery();
	List<?> Backstageorderprocessquery(String page,String limit);
	List<?> Backstageorderquery(String limit,String page);
	int Backstageorderdelete(String orderid);
	int Backstageorderupd(String name,String id,String price,String remake,String classid,String pic);
	// 确认订单
	int Backstageorderisok(String oid);
	// 定时器数据操作
	int Backstageskadd(String sknum, String sktime, long sztime);

}
