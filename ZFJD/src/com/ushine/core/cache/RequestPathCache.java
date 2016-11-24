package com.ushine.core.cache;



/**
 * request单例
 * @author wangbailin
 *
 */
public class RequestPathCache {
	private static RequestPathCache requestPathCache;
	private String  path;



	


	public String getPath() {
		return path;
	}






	public void setPath(String path) {
		this.path = path;
	}






	/**
	 * 获取权限资源缓存实例（单例模式）
	 * @return UserSessionMgr
	 */
	public static RequestPathCache getInstance() {
		if(requestPathCache == null) {
			requestPathCache = new RequestPathCache();
		}
		return requestPathCache;
	}
}
