package com.liang.login.util;

import java.util.HashMap;
import java.util.Map;

public class Getmap {
	//构造私有化
	private Getmap() {}
	//私有静态对象
	private static Getmap getmap = new Getmap();
	//静态容器
	public static Map<String,String> map =new HashMap<String, String>();
	//共有获取对象方法
	public Getmap getmap() {
		return getmap;
	}
}
