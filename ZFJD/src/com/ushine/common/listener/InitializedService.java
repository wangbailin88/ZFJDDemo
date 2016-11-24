package com.ushine.common.listener;

import javax.servlet.ServletContextEvent;

/**
 * 应用程序初始化接口，需要在应用启动时执行的程序，必须实现该接口
 * 
 * @author Franklin
 *
 */
public interface InitializedService {

	/**
	 * 初始化方法
	 * @param sce ServletContextEvent
	 */
	public void initialized(ServletContextEvent sce);
	
}
