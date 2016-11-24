package com.ushine.ssh.service.impl;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.service.ISubmitNodeReisterInfoService;
import com.ushine.ssh.service.IThisNodeInfoService;
import com.ushine.ssh.webcline.Exception_Exception;
import com.ushine.ssh.webcline.IWebService;
import com.ushine.ssh.webcline.SubordinateNodeInfo;
/**
 * 上报信息接口接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("submitNodeReisterInfoServiceImpl")
public class SubmitNodeReisterInfoServiceImpl implements ISubmitNodeReisterInfoService{
	@Autowired
	private IThisNodeInfoService thisNodeInfoService;
	@Override
	public boolean subordinateNodeReisterInfoSumit(SubordinateNodeInfo subordinateNodeInfo,String uploadIp) throws Exception {
			IWebService service;
			//创建WebService客户端代理工厂
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			//注册WebService接口
			factory.setServiceClass(IWebService.class);
			//设置WebService地址
			factory.setAddress("http://"+uploadIp+":8080/ZFJD/services/webservice?wsdl");
			service =(IWebService) factory.create();
			try {
				service.saveSubordinateNodeInfo(subordinateNodeInfo);
			} catch (Exception_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}

}
