package com.feng.Dao;

import java.util.List;

public interface Dao {
	String driver ="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://cdb-rxolpvc2.bj.tencentcdb.com:10000/Six?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
	String user="feng";
	String password="123456";
	List<?> Cusquery(String sql,Object[] obj);//��ѯ�ͻ���
	List<?> Shopquery(String sql,Object[] obj);//��ѯ���ﳵ��
	List<?> Comquery(String sql,Object[] obj);//��ѯ��Ʒ��
	int del(String sql,Object[] obj);//��ɾ��
	List<?> addquery(String sql,Object[] obj);//��ѯ��ַ��
	List<?> orderquery(String sql,Object[] obj);//��ѯ������
}
