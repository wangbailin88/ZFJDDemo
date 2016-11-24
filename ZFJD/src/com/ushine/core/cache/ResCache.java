package com.ushine.core.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ushine.core.po.Resource;

/**
 * 权限资源缓存：提供拦截器快速查询
 * @author franklin
 *
 */
public class ResCache {

	private static ResCache resCacheObject;
	
	private Map<String, Resource> cache;
	
	private ResCache() {
		cache = new HashMap<String, Resource>();
	}
	
	/**
	 * 添加资源
	 * @param opers Operation
	 */
	public void pup(Resource res) {
		cache.put(res.getId(), res);
	}
	
	/**
	 * 按拦截地址获取资源信息
	 * @param Inteceptor
	 * @return
	 */
	public Resource getResource(String Inteceptor) {
		Set<Entry<String, Resource>> entrys = cache.entrySet();
		for(Entry<String, Resource> entry : entrys) {
			Resource res = entry.getValue();
			if(res.getInteceptor().equals(Inteceptor)) {
				return res;
			}
		}
		return null;
	}
	
	/**
	 * 按id查找缓存资源
	 * @param id String 资源id
	 * @return Resource
	 */
	public Resource getResourceById(String id) {
		return cache.get(id);
	}
	
	/**
	 * 获取权限资源缓存实例（单例模式）
	 * @return UserSessionMgr
	 */
	public static ResCache getInstance() {
		if(resCacheObject == null) {
			resCacheObject = new ResCache();
		}
		return resCacheObject;
	}
	
}
