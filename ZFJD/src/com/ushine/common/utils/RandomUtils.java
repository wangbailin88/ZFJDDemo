package com.ushine.common.utils;

import java.util.Random;

/**
 * 随机数工具
 * 
 * @author Franklin
 *
 */
public class RandomUtils {

	private static String[] baseDatas = {
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", 
		"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
		"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	
	/**
	 * 获取Int随机数据
	 * @param range int 返回
	 * @return int
	 */
	public static int getRandomInt(int range) {
		Random random = new Random();
		return Math.abs(random.nextInt()) % range;
	}
	
	/**
	 * 返回随机字符串（从baseDatas中生成,取值范围A-Z, a-z, 0-9）
	 * @param len int 返回字符串的长度
	 * @return String
	 */
	public static String getRandomString(int len) {
		return getRandomString(0, len);
	}
	
	/**
	 * 返回随机字符串（从baseDatas中生成）
	 * @param range int 取值范围
	 * @param len int 返回字符串的长度
	 * @return String
	 */
	public static String getRandomString(int range, int len) {
		if(range <= 0 || range>=62) {
			range = 62;
		}
		
		StringBuffer aBuffer = new StringBuffer(len);
		Random random = new Random();
		int tmp = 0;
		for(int i=0; i<len; i++) {
			tmp = Math.abs(random.nextInt()) % range;
			aBuffer.append(baseDatas[tmp]);
		}
		
		return aBuffer.toString();
	}
	
}
