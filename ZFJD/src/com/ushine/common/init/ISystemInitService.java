package com.ushine.common.init;

/**
 * 
 * @company ushine
 * @author Lee
 * @date Dec 5, 2014
 * @description 
 *
 */
public interface ISystemInitService {
	
	
	/**
	 * service name
	 * @return
	 */
	public String name();
	
	
	/**
	 * load info
	 * @throws Exception
	 */
	public ISystemInitService load() throws Exception;
	
	/**
	 * init service
	 * 
	 */
	public void init() throws Exception;
	
	/**
	 * destory service
	 */
	public void destroy();
}
