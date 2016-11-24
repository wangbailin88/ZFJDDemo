package com.ushine.test;

import java.io.File;

import com.ushine.util.JsonUtil;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Test {
	public static void main(String[] args) {
		String jsonContent = new JsonUtil().readJosnFile("E:/123.json");
		JSONArray jsonArray = JSONArray.fromObject(jsonContent);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
			
		JSONArray jsonArrayResult = JSONArray.fromObject(jsonObject.get("result"));
		JSONObject jsonObjectResult = jsonArrayResult.getJSONObject(0);
		
		
		JSONArray jsonArrayContact = JSONArray.fromObject(jsonObjectResult.get("Contact"));
		int size  = jsonArrayContact.size();
		for (int i = 0; i < size; i++) {
			System.out.println(jsonArrayContact.get(i));
		}
		
		
		
		
		
		System.out.print("联系人数据");
		System.out.println(jsonObjectResult.get("Contact"));
//		System.out.print("code");
//		System.out.println(jsonObjectResult.get("code"));
		System.out.print("流水号");
		System.out.println(jsonObjectResult.get("swift_number"));
//		System.out.print("模块输出标识");
//		System.out.println(jsonObjectResult.get("Flag"));
//		System.out.print("地址信息数据");
//		System.out.println(jsonObjectResult.get("Properties"));
//		System.out.print("联系人数据");
//		System.out.println(jsonObjectResult.get("Contact"));
//		System.out.print("QQ群数据");
//		System.out.println(jsonObjectResult.get("Qqgroup"));
//		System.out.print("关系数据");
//		System.out.println(jsonObjectResult.get("Relation"));
		
	}
	
}
