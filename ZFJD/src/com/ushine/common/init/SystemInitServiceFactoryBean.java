package com.ushine.common.init;

import java.util.List;

import javax.xml.ws.Endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ushine.ssh.webservice.WebServiceImpl;

public class SystemInitServiceFactoryBean {
	
	private static final Logger logger = LoggerFactory.getLogger(SystemInitServiceFactoryBean.class);
	
	private List<ISystemInitService> services;

	public void setServices(List<ISystemInitService> services) {
		this.services = services;
	}
	
	public void init() throws Exception {
		if(services==null||services.size()==0){
			logger.warn("There is no system init service!");
			return;
		}
		for(ISystemInitService service : services){
			try{
				service.load().init();
				logger.info("service --"+service.name()+" init success!");
			} catch(Exception e){
				logger.error("service --"+service.name()+" init faild!", e);
			} 
		}
		
		
		
		
	}
	
	public void destroy(){
		for(ISystemInitService service : services){
			try{
				service.destroy();
				logger.info("service --"+service.name()+" destory success!");
			} catch(Exception e){
				logger.error("service --"+service.name()+" destory faild!", e);
			} 
		}
	}
}
