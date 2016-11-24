package com.ushine.ssh.service.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ushine.ssh.service.ISubmitVocationalWorkService;
import com.ushine.ssh.webcline.Exception_Exception;
import com.ushine.ssh.webcline.IWebService;
import com.ushine.ssh.webcline.VocationalWork;

/**
 * 上报未上报的注册业务系统数据到上级节点接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("submitVocationalWorkServiceImpl")
public class SubmitVocationalWorkServiceImpl implements ISubmitVocationalWorkService{

	@Override
	public void sumitVocationalWork(VocationalWork vocationalWork,String uploadIp)
			throws Exception {
		// TODO Auto-generated method stub
		IWebService service;
		//创建WebService客户端代理工厂
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		//注册WebService接口
		factory.setServiceClass(IWebService.class);
		//设置WebService地址
		factory.setAddress("http://"+uploadIp+":8080/ZFJD/services/webservice?wsdl");
		service =(IWebService) factory.create();
		try {
			service.sumitVocationalWork(vocationalWork);
		} catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
