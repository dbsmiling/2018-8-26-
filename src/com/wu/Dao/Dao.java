package com.wu.Dao;

import java.util.List;

public interface Dao {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://cdb-rxolpvc2.bj.tencentcdb.com:10000/Six?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
	String user = "wu";
	String pwd = "123456";
	
	List<?> query(String sql);
	int upd(String sql);
	int adminquery(String sql);
}
