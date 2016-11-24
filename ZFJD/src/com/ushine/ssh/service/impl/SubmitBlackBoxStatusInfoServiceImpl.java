package com.ushine.ssh.service.impl;

import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.service.ISubmitBlackBoxStatusInfoService;
import com.ushine.ssh.service.IThisNodeInfoService;
import com.ushine.ssh.webcline.BlackBoxStatusInfo;
import com.ushine.ssh.webcline.Exception_Exception;
import com.ushine.ssh.webcline.IWebService;

/**
 * 提交本机及本机下级黑匣子最新数据到本机的父级节点接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("submitBlackBoxStatusInfoServiceImpl")
public class SubmitBlackBoxStatusInfoServiceImpl implements ISubmitBlackBoxStatusInfoService{
	@Autowired
	private IThisNodeInfoService thisNodeInfoService;
	@Override
	public boolean submitBlackBoxStatusInfo(
			List<BlackBoxStatusInfo> blackBoxStatusInfos,String uploadIp) throws Exception {
					IWebService service;
					//创建WebService客户端代理工厂
					JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
					//注册WebService接口
					factory.setServiceClass(IWebService.class);
					//设置WebService地址
					factory.setAddress("http://"+uploadIp+":8080/ZFJD/services/webservice?wsdl");
					service =(IWebService) factory.create();
					try {
						service.sumitBlackBoxData(blackBoxStatusInfos);
					} catch (Exception_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				
				return true;
	}

}
