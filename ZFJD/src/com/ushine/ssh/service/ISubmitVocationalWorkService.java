package com.ushine.ssh.service;

import com.ushine.ssh.webcline.VocationalWork;


/**
 * 上报未上报的注册业务系统数据到上级节点接口
 * @author wangbailin
 *
 */
public interface ISubmitVocationalWorkService {
	/**
	 * 调用客服端上报注册业务系统数据
	 * @param vocationalWork
	 * @throws Exception
	 */
	public void sumitVocationalWork(VocationalWork vocationalWork,String uploadIp)throws Exception;
}
