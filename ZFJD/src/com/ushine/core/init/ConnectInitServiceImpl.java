package com.ushine.core.init;

import org.springframework.beans.factory.annotation.Autowired;

import com.ushine.common.init.ISystemInitService;
import com.ushine.core.service.IPersonService;

public class ConnectInitServiceImpl implements ISystemInitService,Runnable{
	private Thread thread;
	@Autowired
	private IPersonService personService;
//	public ConnectInitServiceImpl(){
//		cityService = (ICityService) SpringUtils.getBean("cityServiceImpl");
//	}
	public String name() {
		// TODO Auto-generated method stub
		return "获取连接";
	}

	public ISystemInitService load() throws Exception {
		// TODO Auto-generated method stub
		thread = new Thread(this);
		return this;
	}

	public void init() throws Exception {
		// TODO Auto-generated method stub
		thread.start();
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				Thread.sleep(1800000);
				personService.findPersonByName("ss");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
