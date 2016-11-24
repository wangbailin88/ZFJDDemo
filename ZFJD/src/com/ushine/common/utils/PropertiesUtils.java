package com.ushine.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Properties文件操作工具
 * 读取Properties文件，并通过给定Key来获取Value
 * 
 * @author Franklin
 *
 */
public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	private Properties prop = new Properties();
	
	/**
	 * 创建PropertiesUtils
	 * @param fileName String Properties文件
	 */
	public PropertiesUtils(String fileName) throws Exception {
		InputStream in = null;
		try {
			in = new FileInputStream(fileName);
			prop.load(in);
			logger.debug("成功加载Properties文件: " + fileName + ".");
		} catch(Exception e) {
			throw new Exception("加载Properties文件错误:" + fileName, e);
		} finally {
			if(in!=null){
				in.close();
			}
		}
	}
	
	/**
	 * 根据Key获取指定的Value
	 * @param key String 
	 * @return String Key对应的Value
	 */
	public String getValue(String key) throws Exception {
		if(key==null || "".equals(key)) {
			throw new Exception("查询配置信息错误, 属性名称不能为null.");
		}
		return prop.getProperty(key);
	}
	
	/**
	 * 获取Properties文件中的所有Value
	 * @return List<String> Values
	 */
	@SuppressWarnings("rawtypes")
	public List<String> getValues() {
		List<String> values = new ArrayList<String>();
		Set keys = prop.keySet();
		// 循环迭代获取全部Value
		for(Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String)it.next();
			values.add(prop.getProperty(key));
		}
		return values;
	}
	
}
