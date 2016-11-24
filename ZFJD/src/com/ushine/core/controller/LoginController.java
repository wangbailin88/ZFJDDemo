package com.ushine.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdcq.common.logging.LogFactory;
import com.tdcq.common.logging.LogInfo;
import com.ushine.common.utils.EncryptUtils;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Role;
import com.ushine.core.service.IPersonService;
import com.ushine.core.service.IRoleService;
import com.ushine.core.verify.ILoginService;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 登录控制器，接受用户登录请求，验证成功后将用户信
 * 息保存到session，并添加在线用户列表，提供查看在线人数及用户名
 * 
 * @author franklin
 * @Modeify xiongrt 140826
 *
 */
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IRoleService roleService;
	/**
	 * 登录操作
	 * @param un String 用户名
	 * @param pa String 密码
	 * @return {"msg":"","status":}
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public void isLogion(@RequestParam("un") String uName,
			@RequestParam("pa") String uPass, HttpServletRequest request,PrintWriter out,HttpServletResponse response) {
		logger.debug("用户登录请求(" + uName + " , " + uPass + ").");
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setParams("用户名:["+uName+"]登录IP:["+request.getRemoteAddr()+"]");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_LOGIN);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			String userInfo = loginService.isLogin(uName, EncryptUtils.getEncrypt(uPass));
			if(userInfo == null) {
				loginfo.setResult("登录失败：用户名或密码错误");
				request.setAttribute("error", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				JSONObject object = JSONObject.fromObject(userInfo);
				if((object.get("status").equals("1"))&&(!object.get("ip").equals(request.getRemoteAddr()))){
					loginfo.setResult("登录失败:IP地址不是绑定IP");
					request.setAttribute("error", "IP地址不是绑定IP");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else{
					// 清楚原有在线用户信息
					HttpSession session = sessionMgr.getLoginUserSession(uName);
					if(session != null) {
						sessionMgr.removeOnlineUser(session.getId());
					}
					sessionMgr.register(request, userInfo);
					// 用户获取角色
					List<Role> roles = roleService.findRoleByPerson(sessionMgr.getUID(request));
					sessionMgr.registerUserRole(request, roleService.listToJSon(roles));
					// 保存用户信息（最后登录时间、最后登录IP）
					personService.modifyPersonLoginInfo(
							(String) sessionMgr.getUID(request), 
							(String) request.getRemoteAddr());
					loginfo.setUserCode((String) object.get("uCode"));
					loginfo.setUserName((String) object.get("tName"));
					loginfo.setResult("登录成功");
					response.sendRedirect("index.jsp");//[- 熊：跳转到桌面 -] 
				}
			}
		} catch(Exception e) {
			logger.error("用户登录操作出现异常." + e);
			e.printStackTrace();
			try {
				loginfo.setResult("登录失败：用户名或密码错误");
				request.setAttribute("error", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally{
			log.log(loginfo);
		}
	}

	/**
	 * 登出操作
	 * @return HttpServletRequest
	 * @return {"logout":true}
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	@ResponseBody
	public String logout(HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_LOGOUT);
		JSONObject object = new JSONObject();
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		loginfo.setUserCode(sessionMgr.getCode(request));
		loginfo.setUserName(sessionMgr.getTrueName(request));
		loginfo.setParams("人员名称:["+sessionMgr.getTrueName(request)+"]");
		sessionMgr.logout(request);
		object.put("logout", true);
		loginfo.setResult("退出成功");
		log.log(loginfo);
		return object.toString();
	}
	/**
	 * 修改密码
	 * @author xyt
	 * @param oldPass老密码
	 * @param pass 新密码
	 * @param pass1 重复密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setPassword", method=RequestMethod.POST)
	@ResponseBody
	public String setPassword(@RequestParam("oldPass") String oldPass,
			@RequestParam("pass") String pass,
			@RequestParam("pass1") String pass1,
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_UPDATE);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			loginfo.setUserCode(sessionMgr.getCode(request));
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setParams("人员名称:["+sessionMgr.getTrueName(request)+"]");
			//Person p = personService.findPersonById(sessionMgr.getUID(request));
			if(!pass.equals(pass1)){
				loginfo.setResult("修改密码失败：两次密码不一致");
				return new ViewObject(ViewObject.RET_ERROR, "两次密码不一致").toJSon();
			}/*else if(!p.getPassword().equals(EncryptUtils.getEncrypt(oldPass))){
				return new ViewObject(ViewObject.RET_ERROR, "密码错误").toJSon();
			}
			p.setPassword(EncryptUtils.getEncrypt(pass));
			personService.modifyPersonInfo(p);*/
			loginfo.setResult("修改密码成功");
			return personService.modifyPassword(EncryptUtils.getEncrypt(oldPass), EncryptUtils.getEncrypt(pass), sessionMgr.getUID(request));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("修改密码操作出现异常.",e);
			loginfo.setResult("修改密码失败："+e.getMessage());
			e.printStackTrace();
			return new ViewObject(ViewObject.RET_SUCCEED, "修改密码操作出现异常").toJSon();
		} finally{
			log.log(loginfo);
		}
		
	}
	/**
	 * 获取在线总人数
	 * @return String {"size"}
	 */
	@RequestMapping(value="/findOlSize", method=RequestMethod.GET)
	@ResponseBody
	public String getOnlineListSize(HttpServletRequest request) {
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		JSONObject object = new JSONObject();
		object.put("size", sessionMgr.getOnlineListSize());
		return object.toString();
	}
	
	/**
	 * 获取在线用户的用户名
	 * @return String Json数组
	 */
	@RequestMapping(value="/findOlUser", method=RequestMethod.GET)
	@ResponseBody
	public String getOnlineListUserNmae(HttpServletRequest request) {
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		return sessionMgr.getOnlineListUserNmae();
	}
	
	/**
	 * 获取当前用户信息，并采用Json返回
	 * （id、用户名、真实姓名、部门id、组织id、当前IP）
	 * @param request HttpServletRequest
	 * @return String Json
	 */
	@RequestMapping(value="/findLoginInfo", method=RequestMethod.GET)
	@ResponseBody
	public String loginInfo(HttpServletRequest request) {
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		return sessionMgr.getUserInfoByISon(request);
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************		
		
	public ILoginService getLoginService() {
		return loginService;
	}
	
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public IPersonService getPersonService() {
		return personService;
	}
	
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}
	
}
