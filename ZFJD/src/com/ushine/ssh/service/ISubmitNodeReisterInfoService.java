package com.ushine.ssh.service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.ushine.ssh.webcline.SubordinateNodeInfo;


/**
 * 上报节点注册信息接口
 * 
 * @author wangbailin
 *
 */
public interface ISubmitNodeReisterInfoService {
	/**
	 * 下级节点信息上报
	 * 1检查当前节点信息是否有上级节点
	 * 2如果没有上级节点无需在向上级上报节点注册信息
	 * 3否则上报节点注册信息，调用父级节点的webservice上报注册信息接口
	 * @return
	 */
	public boolean subordinateNodeReisterInfoSumit(SubordinateNodeInfo subordinateNodeInfo,String uploadIp)throws Exception;
}
