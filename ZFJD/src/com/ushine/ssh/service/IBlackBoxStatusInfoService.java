package com.ushine.ssh.service;

import java.util.List;

import com.ushine.ssh.model.BlackBoxStatusInfo;

/**
 * 黑盒状态信息接口
 * @author wangbailin
 *
 */
public interface IBlackBoxStatusInfoService {
	/**
	 * 新增黑匣子数据
	 * @param blackBoxStatusInfo
	 * @throws Exception
	 */
	public void  saveBlackBoxStatusInfo(BlackBoxStatusInfo blackBoxStatusInfo)throws Exception;
	/**
	 * 获取当前黑盒的基础及状态信息，并存入数据中
	 * @return
	 * @throws Exception
	 */
	public void getBlackBoxStatusInfo()throws Exception;
	/**
	 * 删除当前节点黑盒状态信息，
	 * 设计用于任务调度使用,
	 * 任务调度将在一定的时间间隔调用该方法
	 * 其余用途请谨慎使用
	 * @throws Exception
	 */
	public void deleteBlackBoxStatusInfo()throws Exception;
	/**
	 * 获得当前节点及下级节点最新的黑匣子数据
	 * 1获得当前节点及下级节点信息
	 * 2根据每一个节点信息查询改节点的黑匣子数据
	 * 
	 * @return 如果本机的节点未注册，返回空
	 * @throws Exception
	 */
	public List<BlackBoxStatusInfo> getBlackBoxUpToDateStatusData() throws Exception;
	/**
	 * 获得当前节点最新的黑匣子数据
	 * 1获得当前节点及信息
	 * 2根据节点信息查询节点的黑匣子数据
	 * 
	 * @return 如果本机的节点未注册，返回空
	 * @throws Exception
	 */
	public BlackBoxStatusInfo getThisBlackBoxUpToDateStatusData(String blackIp) throws Exception;
	
}
