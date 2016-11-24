package com.ushine.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class RequestUtil {
	  public static String getRemoteAddress(HttpServletRequest request) {  
	        String ip = "";
	        try {
	        	ip = request.getHeader("X-Forwarded-For"); 
	        	if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
					//多次反向代理后会有多个ip值，第一个ip才是真实ip
					int index = ip.indexOf(",");
				  	if(index != -1){
				  		return ip.substring(0,index);
				  	}else{
				  		return ip;
				  	}
				}
				ip = request.getHeader("X-Real-IP");
				if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
				  	return ip;
				}
				return request.getRemoteAddr();
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
