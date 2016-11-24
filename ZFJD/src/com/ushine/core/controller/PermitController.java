package com.ushine.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

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
import com.ushine.core.po.Resource;
import com.ushine.core.service.IPermitService;
import com.ushine.core.service.IResourceService;
import com.ushine.core.service.IRoleService;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 权限控制器
 * @author franklin
 *
 */
@Controller
public class PermitController {
	private static final Logger logger = LoggerFactory.getLogger(PermitController.class);
	@Autowired
	private IPermitService permitService;
	@Autowired
	private IResourceService resService;
	@Autowired
	private IRoleService roleService;
	/**
	 * 获取角色指定资源的权限操作
	 * @param rcd 资源Code
	 * @param request HttpServletRequest
	 * @return String 操作编码
	 */
	@RequestMapping(value="/getPerCode", method=RequestMethod.GET)
	@ResponseBody
	public String getPermitOperCode(@RequestParam("rcd") String code, 
			HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> roleId = sessionMgr.getRoleIds(request);
			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您还未指定角色信息.");
			
			List<String> codes = new ArrayList<String>(0);
			
			if(roleId!=null && roleId.size()>0) {
				Resource res = resService.findResourcesByCode(code).get(0);
				codes = permitService.getRolePermitCode(roleId, res.getId());
			} else {
				errVo.toJSon();
			}
			
			if(codes!=null && codes.size()>0) {
				return JSONArray.fromObject(codes).toString();
			} else {
				return new ViewObject(ViewObject.RET_FAILURE, "未查找到相应操作.").toJSon();
			}
			
		} catch (Exception e) {
			String msg = "请求获取指定资源的权限操作信息失败.";
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		}
	}

	/**
	 * 获取角色指定资源的权限操作
	 * @param rcd 资源Code
	 * @param request HttpServletRequest
	 * @return String 操作编码
	 */
	@RequestMapping(value="/setPermit", method=RequestMethod.POST)
	@ResponseBody
	public String setPermit(@RequestParam("role") String role, 
			@RequestParam("resId") String[] resId, 
			@RequestParam("operId") String[] operId, 
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
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			loginfo.setParams("角色:["+roleService.findRoleById(role).getName()+"]");
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")){
					loginfo.setResult("设置权限成功");
					return permitService.setRolePermit(role, resId, operId);
				}
			} 
			loginfo.setResult("设置权限失败：没有权限");
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取指定资源的权限操作信息失败.";
			logger.error(msg , e);
			loginfo.setResult("设置权限失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally {
			log.log(loginfo);
		}
	}
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	
	public IPermitService getPermitService() {
		return permitService;
	}
	
	public void setPermitService(IPermitService permitService) {
		this.permitService = permitService;
	}
	
	
	
	
	public IResourceService getResService() {
		return resService;
	}
	
	public void setResService(IResourceService resService) {
		this.resService = resService;
	}
	
}
