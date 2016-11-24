package com.ushine.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 切分字符串到List
	 * @param str 原始字符�?
	 * @param begin �?��切分的位�?
	 * @param end 结束切分的位�?
	 * @param splitChar 分隔�?
	 * @param trim 是否去除前后空格
	 * @return
	 */
	public static List<String> split(String str, int begin, int end,
			                         char splitChar, boolean trim){
		List<String> splits = new LinkedList<String>();
		int lastSplit = begin - 1 ,thisSplit=end;
		while(lastSplit <= thisSplit){
			thisSplit=str.indexOf(splitChar,lastSplit + 1);
			if(thisSplit < 0 || thisSplit >= end){
				if (trim){
					splits.add(str.substring(lastSplit + 1,end).trim());
				}else{
					splits.add(str.substring(lastSplit + 1,end));
				}
				break;
			}else{
				if (trim){
					splits.add(str.substring(lastSplit + 1,thisSplit).trim());
				}else{
					splits.add(str.substring(lastSplit + 1,thisSplit));
				}
				lastSplit = thisSplit;
			}
		};
		return splits;
	}
	//判断字符串是否是null值，如果是返回"",反之返回本身值
	public static String toEmty(String ss){
		if(ss==null||ss.trim().length()==0){
			return "";
		}
		return ss;
	}
	public static boolean isEmty(String ss){
		boolean flag=true;
		if(ss!=null&&ss.trim().length()>0){
			flag=false;
		}
		return flag;
	}

	public static String nullCheck(String str) {
		return str==null?"":str;
	}
	
	public static String uuidUpperCase(){
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}
	
	//判断数组是否为空
	public static boolean isEmpty(String[] ss){
		boolean flag = true;
		if(ss!=null&&ss.length>0){
			flag =false;
		}
		
		return flag;
	}
	/**
	 * 验证参数
	 * @param ss 参数数组
	 * @return OK表示通过否则返回错误参数
	 */
	public static String isParam(String[]ss){
		String b="OK";
		if(!StringUtil.isEmpty(ss)){
			for(int i=0;i<ss.length;i++){
				if(ss[i]==null||ss[i].trim().length()==0){
					b="第"+i+"个参数为空";
					return b;
				}
			}
		}else{
			b="数组为空";
		}
		return b;
	}
	
	public static String getCollectGap(String ss){
		String result = "";
		if(ss!=null&&ss.length()>0){
			
			Integer num = Integer.valueOf(ss);
			if(num<=24){
				result = num +"小时";
				
			}else if(num==168){
				
				result = "每周一次";
			}else if(num==360){
				
				result = "每半月一次";
				
			}else if(num==720){
				
				result = "每个月一次";
				
			}else if(num==4380){
				
				result = "每半年一次";
				
			}else if(num==8760){
				result = "每一年一次";
			}
			
		}
		
		return result;
		
	}
	/**
	 * 判断字符窜是否等于null，等于"null",去空格等于""
	 * @param string
	 * @return
	 */
	public static Boolean isNull(String string){
		boolean bo=false;
		if(string==null||string.trim().equals("null")||string.trim().equals("")){
			bo=true;
		}	
		return bo;
		
	}
	//判断输入的电话是否正确,true：正确.false：不正确
		public static boolean isPhone(String phoneNumber) {
			String regex = "^[1][3-8]+\\d{9}";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(phoneNumber);
			return m.matches();
		}
		//判断输入的身份证是否正确,true：正确.false：不正确
		public static boolean isCard(String cardNumber) {
			String regex = "^[0-9]{17}([0-9]|x)";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(cardNumber);
			return m.matches();
		}
		
		
		public static String dates() {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s = format.format(date);
			return s;
		}
}
