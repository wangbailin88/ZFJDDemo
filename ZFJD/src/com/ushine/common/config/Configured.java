package com.ushine.common.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ushine.common.utils.PathUtils;
import com.ushine.common.utils.PropertiesUtils;
import com.ushine.common.utils.StringUtils;

/**
 * 数据引擎配置信息类, 用于获取应用程序中的配置信息
 * 配置文件可以任意放置在"/WebRoot/WEB-INF/config/"或"/WebRoot/WEB-INF/classes/"下
 * @author Franklin
 *
 */
public class Configured {
	private static final Logger logger = LoggerFactory.getLogger(Configured.class);
	
	private static final String FILE_NAME = "conf.properties";
	
	private static Configured config = new Configured();
	
	/*
	 * 内置属性池
	 */
	private Map<String, String> attribute = new HashMap<String, String>();
	
	private PropertiesUtils propConfFile = null;
	
	private Configured() {
		try {
			// 检测"/WebRoot/WEB-INF/config/"下文件是否存在
			String filePath = PathUtils.getConfigPath(Configured.class) + FILE_NAME;
			if(!new File(filePath).isFile()) {
				System.out.println("aaaaaaaaaaaaa");
				// 检测"/WebRoot/WEB-INF/classes/"下文件是否存在
				filePath = PathUtils.getWebClassesPath(Configured.class) + FILE_NAME;
				if(!new File(filePath).isFile()) {
					logger.warn("没有找到配置文件, 关闭应用程序.");
					System.exit(-1);
				}
			} 
			
			propConfFile = new PropertiesUtils(filePath);
			logger.info("成功加载配置文件:" + filePath + ".");
		} catch(Exception e) {
			logger.error("加载配置文件失败:" + FILE_NAME + ",关闭应用程序.", e);
			System.exit(-1);
		}
	}
	
	/**
	 * 获取配置信息中指定属性的值:先在Properties文件中查找，
	 * 如果没有找到该属性，就在Configured内置Map中查找
	 * @param attrName String 属性名称
	 * @return String 值
	 * @throws Exception 
	 */
	public String get(String attrName) {
		String retVal = null;
		try {
			retVal = propConfFile.getValue(attrName);
			if(retVal == null) {
				retVal = attribute.get(attrName);
				if(retVal == null) {
					logger.warn("没有查询到" + attrName + "配置信息.");
				}
			}
		} catch(Exception e) {
			logger.error("查询" + attrName + "配置信息错误.", e);
		}
		return retVal;
	}
	
	/**
	 * 在配置信息中设置新属性及值
	 * @param attrName String 属性名称
	 * @param value String 值
	 * @throws Exception 
	 */
	public void set(String attrName, String value) {
		if(StringUtils.isNull(attrName) || StringUtils.isNull(value)) {
			logger.warn("配置信息的属性名称或值不能为null.");
		} else {
			attribute.put(attrName, value);
			logger.debug("添加配置信息(name=" + attrName + ", value=" + value + ").");
		}
	}
	
	/**
	 * 应用程序配置信息
	 * @return Configured
	 */
	public static Configured getInstance() {
		if(config == null) {
			config = new Configured();
		}
		return config;
	}
	
}
