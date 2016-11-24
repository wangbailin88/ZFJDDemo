package com.ushine.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring工具类
 * @author Franklin
 *
 */
public class SpringUtils implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringUtils.context = context;
	}
	
	/**
	 * 获取Bean
	 * @param beanName Bean名称
	 * @return Object 对象
	 */
	public synchronized static Object getBean(String beanName) {
		
		return SpringUtils.context.getBean(beanName);
	}
	
}