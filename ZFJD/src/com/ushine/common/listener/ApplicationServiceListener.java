package com.ushine.common.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ushine.common.utils.PathUtils;
import com.ushine.common.utils.PropertiesUtils;

/**
 * 应用服务监听器, 在程序启动与停止时，执行初始化和注销方法。
 * 
 * @author Franklin
 *
 */
public class ApplicationServiceListener implements ServletContextListener {
	private static final Logger logger = 
			LoggerFactory.getLogger(ApplicationServiceListener.class);
	
	/*
	 * 配置文件路径/WEB-INF/config
	 */
	private static final String CONFIG_PATH = 
			PathUtils.getConfigPath(ApplicationServiceListener.class);
	
	/*
	 * 初始化文件
	 */
	private static final String INIT_FILE = "init.properties";
	
	/*
	 * 注销文件
	 */
	private static final String DESTROY_FILE = "destroy.properties";
	
	/*
	 * 需要执行初始化方法的类
	 */
	private static List<String> initClass = null;
	
	/*
	 * 需要执行注销方法的类
	 */
	private static List<String> destroyClass = null;
	
	/**
	 * 读出配置文件init.properties, destroy.properties
	 */
	static {
		try {
			logger.debug("加载初始化程序:" + CONFIG_PATH + INIT_FILE);
			PropertiesUtils propInit = new PropertiesUtils(CONFIG_PATH + INIT_FILE);
			initClass = propInit.getValues();
		
			logger.debug("加载注销程序:" + CONFIG_PATH + DESTROY_FILE);
			PropertiesUtils propDestroy = new PropertiesUtils(CONFIG_PATH + DESTROY_FILE);
			destroyClass = propDestroy.getValues();
		} catch(Exception e) {
			logger.error("加载系统配置文件失败.", e);
			System.exit(-1);
		}
	}
	
	/**
	 * 应用程序启动时执行
	 */
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("应用程序初始化启动.");
		
		// 有初始化程序时，才执行操作
		if(initClass.size() > 0) {
			logger.debug("需要执行"+ initClass.size() +"个初始化程序.");
			try {
				for(String className : initClass) {
					logger.debug("执行:" + className);
					InitializedService service = 
							(InitializedService)Class.forName(className).newInstance();
					service.initialized(sce);
				}
			} catch(Exception e) {
				logger.error("应用程序执行初始化失败.", e);
				System.exit(-1);
			}
		}
	}
	
	/**
	 * 应用程序关闭时执行
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("应用程序资源关闭...");
		
		// 有注销程序时，才执行操作
		if(destroyClass.size() > 0) {
			logger.debug("需要执行"+ destroyClass.size() +"个注销程序.");
			try {
				for(String className : destroyClass) {
					logger.debug("执行:" + className);
					DestroyedService service = 
							(DestroyedService)Class.forName(className).newInstance();
					service.destroy(sce);
				}
			} catch(Exception e) {
				logger.error("应用程序资源关闭失败.", e);
				System.exit(-1);
			}
		}
	}
	
}
