package com.ushine.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * String工具集
 * 
 * @author Franklin
 *
 */
public class StringUtils {

	/**
	 * 判断是否为空
	 * @param s String
	 * @return boolean True=值为空/null,False=非空
	 */
	public static boolean isNull(String s) {
		if(s==null || "".equals(s.trim())) {
			return true;
		}
		return false;
	}
	/**
	 * 将以逗号分隔的字符串转为字符串数组
	 * @param str String
	 * @return String[]
	 */
	public static String[] split(String str) {
		return str.equals("")?null:str.split(",");
	}
	/**
	 * 判断字符串数组是否为空
	 * @param s String[]
	 * @return boolean True=值为空/null,False=非空
	 */
	public static boolean isNull(String[] s) {
		if(s==null || s.length<=0) {
			return true;
		}
		return false;
	}
	
	/**
	 * String转换成int
	 * @param s String
	 * @return int
	 */
	public static int toInt(String s) {
		int retVal = 0;
		if(s!=null && !"".equals(s)) {
			retVal = Integer.parseInt(s);
		}
		return retVal;
	}
	/**
	 * 获取UUID
	 * @return
	 */
	public static String uuidUpperCase(){
		return UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
	}
	/**
	 * String[]装换成int[]
	 * @param array String[]
	 * @return int[]
	 */
	public static int[] toInt(String[] array) {
		int[] retVal = null;
		if(array!=null && array.length>0) {
			retVal = new int[array.length];
		}
		for(int i=0; i<array.length; i++) {
			if(isNull(array[i])) {
				retVal[i] = 0;
			} else {
				retVal[i] = Integer.parseInt(array[i]);
			}
		}
		return retVal;
	}
	
	/**
	 * 字符串转换为Boolean
	 * @param s String “true” 或  “false”
	 * @return boolean 输入错误返回false
	 */
	public static boolean toBoolean(String s) {
		if(s.equals("true") || s.equals("false")) {
			return Boolean.parseBoolean(s);
		}
		return false;
	}
	
	/**
	 * 打印显示字符数组
	 * @param array String[]
	 */
	public static void printArray(String[] array) {
		for(String str : array) {
			System.out.print(str);
			System.out.print(",");
		}
		System.out.println();
	}

	/**
	 * 获取字符串数组二在字符串一中出现的值
	 * @param arrayOne 源字符串数组
	 * @param arrayTow 种子字符串数组
	 * @return List<String> 结果字符串集合
	 */
	public static List<String> ListByArrayTwo(String[] arrayOne, String[] arrayTow) {
		List<String> temp=new ArrayList<String>();
		if (arrayOne!=null) {
			for (String city : arrayOne) {
				for (String region : arrayTow) {
					if (city.equals(region)) {
						temp.add(region);
						break;
					}
				}
			}
		}
		return temp;
	}
	/**
	 * 数组1存在数组2中
	 * @param arrayOne
	 * @param arrayTow
	 * @return boolean
	 */
	public static boolean arrayOneInArrayTow(String[] arrayOne, String[] arrayTow) {
		for(int i=0;i<arrayOne.length;i++){
			if(strInArrayTow(arrayOne[i],arrayTow)==false){
				return false;
			}
		}
		return true;
	}
	/**
	 * 字符串在数组中出现
	 * @param str
	 * @param arrayTow
	 * @return boolean
	 */
	public static boolean strInArrayTow(String str, String[] arrayTow) {
		for(int i=0;i<arrayTow.length;i++){
			if(str.equals(arrayTow[i])){
				return true;
			}
		}
		return false;
	}
	/**
	 * List转换成String[]
	 * @param list List<String>
	 * @return String[]
	 */
	public static String[] listToStrArray(List<String> list) {
		String[] array = new String[list.size()];
		int i = 0;
		
		for(String str : list) {
			array[i] = str;
			i++;
		}
		return array;
	}
	/**
	 * 将数组转换为以逗号分隔的字符串
	 * @param array String数组
	 * @return
	 */
	public static String arrayToString(String [] array){
		StringBuffer sb=new StringBuffer();
		for(String s:array){
			sb.append(s);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	/**
	 * 将数组字符串类型转为对应int类型
	 * @return
	 * 数组大小为1时返回对应数字
	 * 数组大小为2时返回2
	 * 默认返回0
	 */
	public static int sumArray(String [] array){
		int sum=0;
		if(!array[0].equals("")){
			if(array.length==2){
				return 2;
			}else{
				return Integer.parseInt(array[0]);
			}
		}
		return sum;
	}
}
