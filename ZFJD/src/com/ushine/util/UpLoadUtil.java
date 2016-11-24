package com.ushine.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UpLoadUtil {
	public String ip = null;

	public UpLoadUtil() {
	}

	public UpLoadUtil(String ip) {
		this.ip = ip;
	}

	@SuppressWarnings("unused")
	private String getIP() {
		String[] s = this.ip.split("\\.");// 以点分割字符，因为这里需要传入正则表达
											// 式，而正则表达式的点是代表所有字符，所以这里\\来转义
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			sb.append(addZeor(s[i], 3));// 调用方法补零，不够3位的补零填充
		}
		return sb.toString();
	}

	private String addZeor(String s, int number) {
		StringBuffer sb = new StringBuffer();
		sb.append(s);
		while (sb.length() < 3) {
			sb.insert(0, "0");// 在第一位添加0
		}
		return sb.toString();
	}

	private String getTime() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 时间格式
		String DateStirng = sf.format(new Date());// 以以上时间格式生成时间
		return DateStirng;
	}

	public String getTpTime() {
		int a = (int) ((Math.random()) * 100);// 产生一个0-99的随机数
		return this.getTime() + a;// 把处理过后的时间返回
	}
}
