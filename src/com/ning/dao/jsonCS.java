package com.ning.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.tools.jar.resources.jar;

public class jsonCS {
	public static void main(String[] args) {
		String str = null;
		int id = 275;
		
		for (int i = 0; i <40; i++) {
			id=id+1;
			int b=(int)(Math.random()*900)+100;
			str = "("+id+",'images/cfwy/cfwy_1.png','images/cfwy/cfwy_2.png','images/cfwy/cfwy_3.png','images/cfwy/cfwy_4.png','images/cfwy/cfwy_5.png'),";
			System.out.println(str);
		}
	}
}
