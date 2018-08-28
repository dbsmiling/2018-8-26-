package com.feng.Dao;

import java.util.List;

public interface Dao {
	String driver ="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://cdb-rxolpvc2.bj.tencentcdb.com:10000/Six?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
	String user="feng";
	String password="123456";
	List<?> Cusquery(String sql,Object[] obj);//查询客户表
	List<?> Shopquery(String sql,Object[] obj);//查询购物车表
	List<?> Comquery(String sql,Object[] obj);//查询商品表
	int del(String sql,Object[] obj);//增删改
	List<?> addquery(String sql,Object[] obj);//查询地址表
	List<?> orderquery(String sql,Object[] obj);//查询订单表
}
