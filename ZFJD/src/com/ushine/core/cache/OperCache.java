package com.ushine.core.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ushine.core.po.Operation;

/**
 * 权限操作缓存：分类缓存系统定义的所有操作
 * (单例模式)
 * @author franklin
 *
 */
public class OperCache {
	
	private static OperCache operCacheObject;
	
	private Map<String, Operation> cache;

	private OperCache() {
		cache = new HashMap<String, Operation>(0);
	}
	
	/**
	 * 添加操作信息
	 * @param opers Operation
	 */
	public void pup(Operation oper) {
		cache.put(oper.getId(), oper);
	}
	
	/**
	 * 按分类获取操作集合
	 * @param type int
	 * @return List<Operation>
	 */
	public List<Operation> getOpersByType(int type) {
		List<Operation> opers = new ArrayList<Operation>();
		Set<Entry<String, Operation>> entries = cache.entrySet();
		for(Entry<String, Operation> entry : entries) {
			Operation oper = entry.getValue();
			if(oper.getType() == type) {
				opers.add(oper);
			}
		}
		return opers;
	}
	
	/**
	 * 按id查找缓存操作
	 * @param id String
	 * @return Operation
	 */
	public Operation getOperationById(String id) {
		return cache.get(id);
	}
	
	/**
	 * 获取权限操作缓存实例（单例模式）
	 * @return UserSessionMgr
	 */
	public static OperCache getInstance() {
		if(operCacheObject == null) {
			operCacheObject = new OperCache();
		}
		return operCacheObject;
	}
	
}
