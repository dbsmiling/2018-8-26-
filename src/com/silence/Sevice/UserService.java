package com.silence.Sevice;

import java.util.List;

/**
 * @author Silence
 *业务逻辑层
 */
public interface UserService {
	// 根据邮箱查客户信息
	List<?> UserService(String Cemail); 
	//	根据邮箱修改客户信息
	int updCustomer(String name,String pwd,String sex,String bir,String tell,String email);
	//根据客户id   添加收货地址
	int updAdd(String add,String tell,String name,String id);
	//根据客户id   修改收货地址
	int changeAdd(String add,String tell,String name,String id, String addid);
	//根据客户id   查询收货地址表
	List<?> UserAdd(String id);
	//根据客户id   查询订单表
	List<?> UserOrder(String id);
	//根据商品id   查询商品信息系
	List<?>UserShop(String shopid1,String shopid2,String shopid3,String shopid4);
	//根据客户id   查询订单金额总和
	List<?> UserMoney(String id);
	//根据订单id   删除订单信息
	int delShop (String shopID);
	//根据收件人手机号     删除收货地址
	int delAdd(String tell);
	//根据客户id   获取订单总量
	List<?> allOrders (String id);
}
