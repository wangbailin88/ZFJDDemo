package com.ushine.common.listener;

import javax.servlet.ServletContextEvent;

/**
 * 应用程序注销接口，需要在应用停止时执行的程序，必须实现该接口
 * 
 * @author Franklin
 *
 */
public interface DestroyedService {

	/**
	 * 注销方法
	 * @param sce ServletContextEvent
	 */
	public void destroy(ServletContextEvent sce);
	
}
