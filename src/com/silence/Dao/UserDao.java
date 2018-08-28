package com.silence.Dao;

import java.util.List;

public interface UserDao {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://cdb-rxolpvc2.bj.tencentcdb.com:10000/Six?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
	String user = "li";
	String pwd = "123456";

	
	//查询客户表
	List<?> query(String sql,String str[]);
	int upd(String sql,String str[]);
	//查询客户地址表
	List<?>query_add(String sql,String str);
	//查询定单表
	List<?>query_order(String sql,String str[]);
	//查询商品表
	List<?>query_shop(String sql,String str[]);
	//查询订单表金额总和
	List<?>query_money(String sql,String id);
	//获取用户对应的所有订单信息
	List<?>query_allOrders(String sql,String id);
}
