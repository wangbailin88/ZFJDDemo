package com.ushine.core.cache;
public class CityCache {
	private static CityCache city;
	private String citys;
	
	public String getCitys() {
		return citys;
	}

	public void setCitys(String citys) {
		this.citys = citys;
	}

	/**
	 * 获取权限资源缓存实例（单例模式）
	 * @return UserSessionMgr
	 */
	public static CityCache getInstance() {
		if(city == null) {
			city = new CityCache();
		}
		return city;
	}

}
