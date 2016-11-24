package com.ushine.ssh.service;

import javax.activation.DataHandler;

import com.ushine.ssh.webcline.VocationalWorkSystemVersionHan;



/**
 * 上报业务系统插件接口
 * @author wangbailin
 *
 */
public interface ISumitVocationalWorkSystemVersionService {
	/**
	 * 上报业务系统插件函数
	 * @param vocationalWorkSystemVersion   插件信息
	 * @param handler   插件jar包
	 * @throws Exception
	 */
	public boolean sumitVocationalWorkSystemVersion(VocationalWorkSystemVersionHan han,String uploadIp)throws Exception;
}
