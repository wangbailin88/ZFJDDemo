package com.ushine.core.verify.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.core.po.Person;
import com.ushine.core.service.IPersonService;
import com.ushine.core.verify.ILoginService;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 验证服务接口实现
 * @author franklin
 *
 */
@Transactional
@Service("verifyServiceImpl")
public class LoginServiceImpl implements ILoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private IPersonService personService;
	
	@Transactional(readOnly=true)
	public String isLogin(String uName, String uPass) throws Exception {
		
		//*********************************************
		//* 登录验证操作
		//*********************************************/
		List<Person> persons =  personService.findPersonByName(uName);
		if(persons.size() > 0) {
			Person person = persons.get(0);
			if(person.getPassword().equals(uPass)) {
				//*********************************************
				//* 获取用户相关信息：id，用户名、真实姓名
				//* 本次登陆的时间、用户所在的组织及部门
				//*********************************************/
				logger.info("登录成功(" + uName + ")加载用户个人信息.");
				JSONObject object = new JSONObject(); 
				object.put(UserSessionMgr.U_ID, person.getId());
				object.put(UserSessionMgr.U_NAME, person.getUserName());
				object.put("status", person.getStatus()+"");
				object.put("ip", person.getIp());
				if(!uName.equals("admin")){
					object.put(UserSessionMgr.U_CODE, person.getNumber());
					object.put(UserSessionMgr.T_NAME, person.getTrueName());
					object.put(UserSessionMgr.U_DID, person.getDept().getId());
					object.put(UserSessionMgr.U_OID, person.getDept().getOrganiz().getId());	
				}
				return object.toString();
			} else {
				logger.info("登录失败(" + uName + ")用户名或密码失败.");
				return null;
			}
		} else {
			logger.info("登录失败(" + uName + ")用户名或密码失败.");
			return null;
		}
	}
	
	@Transactional(readOnly=true)
	public String iscaLogin(String userName) throws Exception {
		
		//*********************************************
		//* 登录验证操作
		//*********************************************/
		List<Person> persons = personService.findPersonByName(userName);
		if(persons.size() > 0) {
			Person person = persons.get(0);
			//*********************************************
			//* 获取用户相关信息：id，用户名、真实姓名
			//* 本次登陆的时间、用户所在的组织及部门
			//*********************************************/
			logger.info("登录成功(" + userName + ")加载用户个人信息.");
			JSONObject object = new JSONObject(); 
			object.put(UserSessionMgr.U_ID, person.getId());
			object.put(UserSessionMgr.U_NAME, person.getUserName());
			if(!person.getUserName().equals("admin")){
				object.put(UserSessionMgr.U_CODE, person.getNumber());
				object.put(UserSessionMgr.T_NAME, person.getTrueName());
				object.put(UserSessionMgr.U_DID, person.getDept().getId());
				object.put(UserSessionMgr.U_OID, person.getDept().getOrganiz().getId());	
			}
			return object.toString();
		} else {
			logger.info("登录失败(" + userName + ")用户不存在.");
			return null;
		}
	}

	//*************************************************************
	//* Getting / Setting 
	//*************************************************************		
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IPersonService getPersonService() {
		return personService;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}
	
}
