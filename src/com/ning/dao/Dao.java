package com.ning.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface Dao {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://cdb-rxolpvc2.bj.tencentcdb.com:10000/Six?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
	String user = "ning";
	String pwd = "123456";
//____________________________________________________
	/*商品表查询*/
	List<?> commodityQuery(String sql,Object[] obj);
	/*商品放大镜图片查询*/
	List<?> CIMGQuery(String sql,Object[] obj);
	/*用户表查询*/
	List<?> userquery(String sql,Object[] obj);
	/*抢购商品表查询*/
	List<?> Rushcomquery(String sql,Object[] obj);
	/*单条信息查询*/
	String Query_dt(String sql,Object[] obj);
	/*购物车的增改*/
	int update(String sql,Object[] obj);
	/*查询特定条件数据条数*/
	int CNquery(String sql,Object[] obj);
}
