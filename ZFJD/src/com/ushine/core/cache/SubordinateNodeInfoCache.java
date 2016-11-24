package com.ushine.core.cache;

import java.util.List;

import com.ushine.ssh.model.SubordinateNodeInfo;

/**
 * 下级节点信息
 * 单例模式
 * @author wangbailin
 *
 */
public class SubordinateNodeInfoCache {
	private static SubordinateNodeInfoCache subordinateNodeInfoCache;
	private List<SubordinateNodeInfo> subordinateNodeInfos;
	
	



	public List<SubordinateNodeInfo> getSubordinateNodeInfos() {
		return subordinateNodeInfos;
	}





	public void setSubordinateNodeInfos(
			List<SubordinateNodeInfo> subordinateNodeInfos) {
		this.subordinateNodeInfos = subordinateNodeInfos;
	}
	
	public SubordinateNodeInfo getNodeByCode(String code){
		if(this.subordinateNodeInfos != null){
			for(SubordinateNodeInfo node : subordinateNodeInfos){
				if(node.getNodeCode().equals(code)){
					return node;
				}
			}
		}
		return null;
	}





	/**
	 * 获取权限资源缓存实例（单例模式）
	 * @return subordinateNodeInfoCache
	 */
	public static SubordinateNodeInfoCache getInstance() {
		if(subordinateNodeInfoCache == null) {
			subordinateNodeInfoCache = new SubordinateNodeInfoCache();
		}
		return subordinateNodeInfoCache;
	}

}
