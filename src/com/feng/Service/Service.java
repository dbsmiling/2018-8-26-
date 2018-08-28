package com.feng.Service;

import java.util.List;

public interface Service {
	List<?> Cusquery(String cemail);
	List<?> Shopquery(String id);
	List<?> Comquery(String cid);
	int delupd(String id,String uid);
	int inser(String O_Cid,String Oid,String Ogenerdate,String Omoney, String Ostate,String O_cname,String O_Scom_quan);
	int drop(String uid);
	List<?> addquery(String uid);
	List<?> orderquery(String uid,String one);
	int orderchange(String uid,String one);
 }
