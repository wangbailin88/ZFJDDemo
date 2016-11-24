package com.ushine.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * json工具类
 * @author wangbailin
 *
 */
public class JsonUtil {
	/**
	 * 读取json文件
	 * @param filePath
	 * @return
	 */
	public String readJosnFile(String filePath){
		BufferedReader reader = null;
		String laststr="";
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString  = null;
			while((tempString=reader.readLine()) != null){
				laststr+= tempString;
			}
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return laststr;
	}
	
}
