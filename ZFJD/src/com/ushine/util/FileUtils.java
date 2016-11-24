package com.ushine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Reader;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;

/**
 * 文件工具类,用于对文件的处理
 * 
 * @author 蔡平
 * 
 */

public class FileUtils {
	/**
	 * 将前台的csv文件读取出来 return ArrayList<String[]>
	 */
	public static ArrayList<String[]> readeCsv(Reader re) {
		ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
		try {
			BufferedReader bfd = new BufferedReader(re);
			Character c = ' ';
			String str = "";
			int length=0;
			while ((str = bfd.readLine()) != null) {
				if ((length=str.indexOf(",")) != -1) {
					c = ',';
				} else if ((length=str.indexOf("|")) != -1) {
					c = '|';
				}
				if((length+1)==str.length()){
					str+=" ";
				}
				String[] lins = str.split("\\" + c + "");
				csvList.add(lins);
			}
			System.out.println(JSONArray.fromObject(csvList).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return csvList;
	}
	//删除文件
	public static boolean deleteFile(String sPath) {  
		boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	/**
	 * 
	 * @param targetPaperFileAdd   copy到目标文件夹地址
	 * @param file   待copy的文件
	 * @return
	 * @throws Exception 
	 */
	public static boolean copyFile(String targetPaperFileAdd,File file) {
		//判断copy到目标文件夹是否存在如果不存在创建该文件夹
		String path = targetPaperFileAdd;
		File fileT = new File(path);
		 if (!fileT.exists()) {
			 fileT.mkdirs();
	      }
		 
		try {
			//读取待copy的文件
			 FileInputStream fis= new FileInputStream(file.getPath());
			 //写入到指定文件夹中
		     FileOutputStream fos = new FileOutputStream(path+"/"+file.getName());
			 int len = 0;
			 byte[] buf = new byte[1024];
				while((len=fis.read(buf)) !=-1){
					fos.write(buf, 0, len);
				}
			fis.close();
			fos.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
