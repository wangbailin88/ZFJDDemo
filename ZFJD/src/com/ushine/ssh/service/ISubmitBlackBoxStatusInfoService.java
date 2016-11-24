package com.ushine.ssh.service;

import java.util.List;

import com.ushine.ssh.webcline.BlackBoxStatusInfo;

/**
 * 提交本机及本机下级黑匣子最新数据到本机的父级节点接口
 * @author wangbailin
 *
 */
public interface ISubmitBlackBoxStatusInfoService {
	/**
	 * 条用webservice提交本机和本机下级黑匣子最新数据
	 * @param blackBoxStatusInfos
	 * @return
	 * @throws Exception
	 */
	public boolean submitBlackBoxStatusInfo(List<BlackBoxStatusInfo> blackBoxStatusInfos,String uploadIp)throws Exception;
}
