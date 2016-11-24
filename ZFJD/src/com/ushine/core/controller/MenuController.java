package com.ushine.core.controller;

import java.util.ArrayList;
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
import com.ushine.core.po.Menu;
import com.ushine.core.service.IMenuService;
import com.ushine.core.verify.session.UserSessionMgr;

@Controller
public class MenuController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 上级菜单
	 * @param rt 返回类型：c=ComboBox t=Tree
	 * @param request HttpServletRequest
	 * @return 组织树(JSON)
	 */
	@RequestMapping(value="/getMenus", method=RequestMethod.GET)
	@ResponseBody
	public String getMenusTree(@RequestParam("rt") String type, 
			HttpServletRequest request) {
		try {
//			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
//			List<String> codes = sessionMgr.getPermitResOperCode(request);
//			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
//			
			List<Menu> menus = new ArrayList<Menu>();
//			
//			if(codes!=null && codes.size()>0) {
//				logger.debug("该权限操作编码:" + codes + ".");
//				if(codes.get(0).equals("0000")) {
//					// 全部数据
					menus = menuService.findMenusTree();
//				} else {
//					return errVo.toJSon();
//				}
//			} else {
//				return errVo.toJSon();
//			}
			
			if(type==null || type.equals("t")) {
				return menuService.toExtTreeJSon(menus);
			} else {
				return menuService.toExtComboJSon(menus);
			}
			
		} catch (Exception e) {
			String msg = "请求获取菜单信息操作失败.";
			logger.error(msg,e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	/**
	 * 系统设置下的菜单
	 * @param rt 返回类型：c=ComboBox t=Tree
	 * @param request HttpServletRequest
	 * @return 组织树(JSON)
	 */
	@RequestMapping(value="/systemMenus", method=RequestMethod.GET)
	@ResponseBody
	public String systemMenus(@RequestParam("rt") String type,
			@RequestParam("id") String id,
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_QUERY);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			List<Menu> menus = new ArrayList<Menu>();
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					loginfo.setResult("查看系统设置成功");
					menus = menuService.findByParentId(id);
				} else {
					loginfo.setResult("查看系统设置失败：没有权限");
					return errVo.toJSon();
				}
			} else {
				loginfo.setResult("查看系统设置失败：没有权限");
				return errVo.toJSon();
			}
			if(type==null || type.equals("t")) {
				return menuService.toExtTreeJSon(menus);
			} else {
				return menuService.toExtComboJSon(menus);
			}
		} catch (Exception e) {
			String msg = "请求获取菜单信息操作失败.";
			logger.error(msg,e);
			loginfo.setResult("查看系统设置失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	/**
	 * 全部树形菜单
	 * @param rt 返回类型：c=ComboBox t=Tree
	 * @param request HttpServletRequest
	 * @return 组织树(JSON)
	 */
	@RequestMapping(value="/findMenuTree", method=RequestMethod.GET)
	@ResponseBody
	public String findMenuTree(@RequestParam("node") String node,
			HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");	
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					return menuService.toExtTreeJSon(menuService.findMenusTree());
				}
			} 
			return errVo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取菜单信息操作失败.";
			logger.error(msg,e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	public IMenuService getMenuService() {
		return menuService;
	}
	
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	
}
