package com.ushine.util;

import java.util.List;

import net.sf.json.JSONObject;

import com.ushine.common.utils.StringUtils;

public class ConditionUtils {
	/**
	 * 获取结束时间
	 * @param endDate 结束时间字符串（yyyy-MM-dd）
	 * @return String yyyy-MM-dd T23:59:59.999Z
	 */
	public static String getEndDate(String endDate) {
		if (endDate.equals("")) {
			endDate = null;
		} else {
			endDate = endDate + "T23:59:59.999Z";
		}
		return endDate;
	}
	/**
	 * 获取开始时间
	 * @param startDate 开始时间字符串（yyyy-MM-dd）
	 * @return String yyyy-MM-dd T00:00:00.000Z
	 */
	public static String getStartDate(String startDate) {
		if (startDate.equals("")) {
			startDate = null;
		} else {
			startDate = startDate + "T00:00:00.000Z";
		}
		return startDate;
	}
	/**
	 * 将json字符参数串转换为日志对应参数
	 * @param param
	 * @return
	 */
	public static String convertParam(String param) {
		param = param.replace(",", "&");
		param = param.replace(":", "=");
		param = param.replace("{", "");
		param = param.replace("}", "");
		param = param.replace("\"", "");
		param = param.replace("00=00=00", "00:00:00");
		param = param.replace("23=59=59", "23:59:59");
		return param;
	}
	/**
	 * 将参数转换为json字符串
	 * @param dateTyle
	 * @param dateSelect
	 * @param sDate
	 * @param eDate
	 * @param shortName
	 * @param companyName
	 * @param sCity
	 * @param rCity
	 * @param sCityIds
	 * @param rCityIds
	 * @param brName
	 * @param brNameType
	 * @param orderNo
	 * @param gName
	 * @param uAdd
	 * @param uAddType
	 * @param uName
	 * @param uNameType
	 * @param uID
	 * @param uIDType
	 * @param uTel
	 * @param uTelType
	 * @param phoneType
	 * @return
	 */
	public static String getParam(String[] dateTyle, String dateSelect, String sDate,
			String eDate, String shortName, String companyName, String sCity,
			String rCity, String[] sCityIds, String[] rCityIds, String brName,
			String[] brNameType, String orderNo, String gName, String uAdd,
			String[] uAddType, String uName, String[] uNameType, String uID,
			String[] uIDType, String uTel, String[] uTelType, String[] phoneType) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		obj.put("dateTyle", StringUtils.arrayToString(dateTyle));
		obj.put("dateSelect", dateSelect);
		obj.put("startDate", sDate+" 00:00:00");
		obj.put("endDate", eDate+" 23:59:59");
		obj.put("shortName", shortName);
		obj.put("companyName", companyName);
		obj.put("sCity", sCity);
		obj.put("rCity", rCity);
		obj.put("sCityIds", StringUtils.arrayToString(sCityIds));
		obj.put("rCityIds", StringUtils.arrayToString(rCityIds));
		obj.put("brName", brName);
		obj.put("brNameType", StringUtils.arrayToString(uNameType));
		obj.put("orderNo", orderNo);
		obj.put("gName", gName);
		obj.put("uAdd", uAdd);
		obj.put("uAddType", StringUtils.arrayToString(uAddType));
		obj.put("uName", uName);
		obj.put("uNameType", StringUtils.arrayToString(uNameType));
		obj.put("uID", uID);
		obj.put("uIDType", StringUtils.arrayToString(uIDType));
		obj.put("uTel", uTel);
		obj.put("uTelType", StringUtils.arrayToString(uTelType));
		obj.put("phoneType", StringUtils.arrayToString(phoneType));
		return obj.toString();
	}
	public static String getSearchCondition(String taskName,String caseName,String[] dateTyle,String sDate,
			String eDate, String companyName, String sCity,
			String rCity, String brName,String[] brNameType, String orderNo, String gName, String uAdd,
			String[] uAddType, String uName, String[] uNameType, String uID,
			String[] uIDType, String uTel, String[] uTelType, String[] phoneType) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append("任务名称:[");
		sb.append(taskName);
		sb.append("]所属案件:[");
		sb.append(caseName);
		sb.append("]单号:[");
		sb.append(orderNo);
		sb.append("]开始时间:[");
		sb.append(sDate);
		sb.append("]结束时间:[");
		sb.append(eDate);
		sb.append("]公司名称:[");
		sb.append(companyName);
		sb.append("]始发站:[");
		sb.append(sCity);
		sb.append("]目的站:[");
		sb.append(rCity);
		sb.append("]网点:[");
		sb.append(brName);
		sb.append("]物品名称:[");
		sb.append(gName);
		sb.append("]地址:[");
		sb.append(uAdd);
		sb.append("]客户姓名:[");
		sb.append(uName);
		sb.append("]身份证号:[");
		sb.append(uID);
		sb.append("]联系方式:[");
		sb.append(uTel);
		sb.append("]");
		return sb.toString();
	}
	public static String getPhoneText(String [] array){
		if(StringUtils.sumArray(array)==0){
			return "手机";
		}else if(StringUtils.sumArray(array)==1){
			return "固话";
		}
		return"手机/固话";
	}
	public static String getTypeText(String [] array){
		if(StringUtils.sumArray(array)==0){
			return "发";
		}else if(StringUtils.sumArray(array)==1){
			return "收";
		}
		return"收/发";
	}

}
