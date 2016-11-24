package com.ushine.ssh.service.impl;

import javax.activation.DataHandler;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.ssh.service.ISumitVocationalWorkSystemVersionService;
import com.ushine.ssh.webcline.Exception_Exception;
import com.ushine.ssh.webcline.IWebService;
import com.ushine.ssh.webcline.IWebServiceUpload;
import com.ushine.ssh.webcline.VocationalWork;
import com.ushine.ssh.webcline.VocationalWorkSystemVersionHan;

/**
 * 上报业务系统插件接口实现
 * @author wangbailin
 *
 */
@Transactional
@Service("sumitVocationalWorkSystemVersionServiceImpl")
public class SumitVocationalWorkSystemVersionServiceImpl implements ISumitVocationalWorkSystemVersionService{

	@Override
	public boolean sumitVocationalWorkSystemVersion(
			VocationalWorkSystemVersionHan han,String uploadIp) throws Exception {
		// TODO Auto-generated method stub
		IWebServiceUpload service;
		//创建WebService客户端代理工厂
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		//注册WebService接口
		factory.setServiceClass(IWebServiceUpload.class);
		//设置WebService地址
		factory.setAddress("http://"+uploadIp+":8080/ZFJD/services/webserviceUpload?wsdl");
		service =(IWebServiceUpload) factory.create();
		service.sumitVocationalWorkSystemVersion(han);
	return true;
	}

}
