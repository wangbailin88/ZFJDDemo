package com.ushine.ssh.test;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.service.IThisNodeInfoService;

@Component("ThisNodeInfoTest")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ThisNodeInfoTest {
	@Autowired
	private IThisNodeInfoService service;
//	@Test
//	public void main(){
//		HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//		System.out.println(request);
//	}
	//@Test
	public void saveThisNodeInfoTest(){
		ThisNodeInfo t = new ThisNodeInfo();
		t.setBaseNodeIp("192.168.10.46");
		t.setBaseNodeName("部中心");
		
		
		t.setBlackBoxCode("192.168.10.45");
		t.setBlackBoxIp("192.168.10.45");
		t.setBlackBoxName("部中心黑匣子");
		t.setNodeCode("wangbailin");
		t.setNodeIp("192.168.10.44");
		t.setNodeName("四川省");
		try {
			service.saveThisNodeInfo(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		IWebService service;
//		//创建WebService客户端代理工厂
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		//注册WebService接口
//		factory.setServiceClass(IWebService.class);
//		//设置WebService地址
//		factory.setAddress("http://192.168.10.44:8080/ZFJD/services/webservice?wsdl");
//		service =(IWebService) factory.create();
//		try {
//			SubordinateNodeInfo s = new SubordinateNodeInfo();
//			s.setBaseNodeIp("");
//			s.setBaseNodeName("664wqe");
//			service.saveSubordinateNodeInfo(s );
//		} catch (Exception_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
		
}
