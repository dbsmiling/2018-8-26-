package com.wu.Dao;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class test {
	public static void main(String[] args) {
		Map  map = new HashMap();
		map.put("wu", "车干");
	JSONObject jsonObject = JSONObject.fromObject(map);
	
//		Map maps = (Map)JSON.parse(jsonObject);
//		Map mapTypes = JSON.parseObject(jsonObject);
//		Map<String, Object> itemMap = JSONObject.fromObject(jsonObject);
		JSONObject  myJson = JSONObject.fromObject(jsonObject);
		Map m=myJson;
		System.out.println(m.get("wu"));
	}
}
