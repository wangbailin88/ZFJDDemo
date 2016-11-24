package com.ushine.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	  public static String getRemoteAddress(HttpServletRequest request) {  
	        String ip = request.getHeader("x-forwarded-for");
	        try {
		        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
		            ip = request.getHeader("Proxy-Client-IP");  
		        }  
		        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
		            ip = request.getHeader("WL-Proxy-Client-IP"); 
		        }  
		        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
		            ip = request.getRemoteAddr();  
		            if(ip.equals("127.0.0.1")){
		            	InetAddress inet = null;
		            	try {
							inet = InetAddress.getLocalHost();
						} catch (UnknownHostException e) {
							e.printStackTrace();
						}
		            	ip = inet.getHostAddress();
		            }
		        }
		        if(ip != null && ip.length()> 15){
		        	if(ip.indexOf(",")>0){
		        		ip = ip.substring(0,ip.indexOf(","));
		        	}
		        }
			} catch (Exception e) {
				e.printStackTrace();
				ip = request.getRemoteHost();
			}
	        return ip;  
	    }  
	  
	    public static String getMACAddress(String ip) {  
	        String str = "";  
	        String macAddress = "";  
	        try {  
	            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
	            InputStreamReader ir = new InputStreamReader(p.getInputStream());  
	            LineNumberReader input = new LineNumberReader(ir);  
	            for (int i = 1; i < 100; i++) {  
	                str = input.readLine();  
	                if (str != null) {  
	                    if (str.indexOf("MAC Address") > 1) {  
	                        macAddress = str.substring(  
	                                str.indexOf("MAC Address") + 14, str.length());  
	                        break;  
	                    }  
	                }  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace(System.out);  
	        }  
	        return macAddress;  
	    }  
}
