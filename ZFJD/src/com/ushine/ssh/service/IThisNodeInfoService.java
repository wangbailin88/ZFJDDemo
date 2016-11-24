package com.ushine.ssh.service;

import java.util.List;

import com.ushine.ssh.model.ThisNodeInfo;

/**
 * 本机节点信息接口类
 * @author wangbailin
 *
 */
public interface IThisNodeInfoService {
	/**
	 * 新增本机节点信息方法
	 * 不管数据表中是否有当前注册节点的注册信心，都将先删除全部在新增
	 * @param thisNodeInfo
	 * @throws Exception
	 */
	public void saveThisNodeInfo(ThisNodeInfo thisNodeInfo)throws Exception;
	/**
	 * 查询本机节点信息方法
	 * 该数据表只允许存在一条本机的节点信息数据，
	 * 该数据超过1条以上的情况，就只保留第一条数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public ThisNodeInfo findThisNodeInfo()throws Exception;
	/**
	 * 删除本机节点信息
	 * @throws Exception
	 */
	public void deleteThisNodeInfo()throws Exception;
}
