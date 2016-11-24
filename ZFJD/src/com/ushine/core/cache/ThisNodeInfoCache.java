package com.ushine.core.cache;

import com.ushine.ssh.model.ThisNodeInfo;

/**
 * 当前节点信息
 * 单例模式
 * @author wangbailin
 *
 */
public class ThisNodeInfoCache {
	private static ThisNodeInfoCache thisNodeInfoCache;
	private ThisNodeInfo thisNodeInfo;
	
	

	public ThisNodeInfo getThisNodeInfo() {
		return thisNodeInfo;
	}



	public void setThisNodeInfo(ThisNodeInfo thisNodeInfo) {
		this.thisNodeInfo = thisNodeInfo;
	}



	/**
	 * 获取权限资源缓存实例（单例模式）
	 * @return UserSessionMgr
	 */
	public static ThisNodeInfoCache getInstance() {
		if(thisNodeInfoCache == null) {
			thisNodeInfoCache = new ThisNodeInfoCache();
		}
		return thisNodeInfoCache;
	}

}
