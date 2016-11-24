package com.ushine.core.service;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.City;

/**
 * 
 * @author xyt
 *
 */
public interface ICityService extends IJSonHandle<City>{
	/**
	 * 树形菜单
	 * 点击某个节点才加载
	 * @return 树形json
	 */
	public String findCity(String node)throws Exception;
	/**
	 * 查询最1级城市
	 * @return
	 * @throws Exception
	 */
	public String findCity()throws Exception;
}
