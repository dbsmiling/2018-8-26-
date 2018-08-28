package com.backstage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface Dao {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://cdb-rxolpvc2.bj.tencentcdb.com:10000/Six?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
	String user = "liang";
	String pwd = "123456";
	
	int adminquery(String sql);
	
	List userquery(String sql);
	int userupd(String sql);
	
	List<?> commodityquery(String sql);
	int commodityupd(String sql);
	
	List<?> orderquery(String sql);
	int orderupd(String sql);
}
