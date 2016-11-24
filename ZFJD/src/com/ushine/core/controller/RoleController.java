package com.ushine.core.controller;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Role;
import com.ushine.core.service.IPersonService;
import com.ushine.core.service.IRoleService;
import com.ushine.core.verify.session.UserSessionMgr;
@Controller
@RequestMapping ( "/role" )
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPersonService personService;
	/**
	 * 查询所有角色
	 */
	@RequestMapping (value="/findAll.do", method=RequestMethod.GET )
	@ResponseBody
	public String findByMid(HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					List<Role> list=roleService.findRoleAll();
					return roleService.toExtTreeJSon(list);
				}  else {
					return vo.toJSon();
				}
			} else {
				return vo.toJSon();
			}
		} catch (Exception e) {
			String msg = "查询所有角色失败";
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	/**
	 * 添加角色
	 * @param request
	 * @param name 角色名称
	 * @param dec 角色备注
	 * @param roleType 角色状态
	 * @return
	 */
	@RequestMapping (value="/addRole.do", method=RequestMethod.POST )
	@ResponseBody
	public String addRole(HttpServletRequest request,
			@RequestParam("name") String name,
			@RequestParam("dec") String dec) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setParams("角色名称:["+name+"]&备注:["+dec+"]");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_ADD);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					Role role=new Role();
					role.setName(name);
					//role.setStatus(Integer.parseInt(roleType));
					loginfo.setResult("添加角色成功");
					return roleService.createNewRole(role);
				}  else {
					loginfo.setResult("添加角色失败：没有权限");
					return vo.toJSon();
				}
			} else {
				loginfo.setResult("添加角色失败：没有权限");
				return vo.toJSon();
			}
		} catch (Exception e) {
			String msg = "添加角色失败";
			logger.error(msg , e);
			loginfo.setResult("添加角色失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	/**
	 * 删除角色
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping (value="/delRole.do", method=RequestMethod.GET )
	@ResponseBody
	public String findByMid(HttpServletRequest request,
			@RequestParam("id") String id) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setResult("删除角色成功");
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_DELETE);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			loginfo.setParams("角色名称:["+roleService.findRoleById(id).getName()+"]");
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					if(personService.findByRid(id).size()>0){
						return  new ViewObject(-1, "该角色已被使用").toJSon();
					}else{
						return roleService.delById(id);
					}
				}  
			} 
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "删除角色失败";
			logger.error(msg , e);
			loginfo.setResult("删除角色失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally {
			log.log(loginfo);
		}
		
	}
}
