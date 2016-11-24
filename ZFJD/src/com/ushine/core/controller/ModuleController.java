package com.ushine.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ushine.common.vo.ViewObject;
import com.ushine.core.service.IModuleService;
import com.ushine.core.verify.session.UserSessionMgr;


@Controller
@RequestMapping ( "/module" )
public class ModuleController {
	private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);
	@Autowired
	private IModuleService moduleService;
	/**
	 * 查询所有模板
	 * @param request
	 * @return
	 */
	@RequestMapping ( "/findAll.do" )
	@ResponseBody
	public String findAll(HttpServletRequest request){
		logger.debug("查询所有模板");
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					// 全部数据
					return moduleService.findAll();
				} 
			} 
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "查询所有模块失败";
			logger.error(msg + e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	/**
	 * 查询所有模块树形菜单
	 * @param request 
	 * @param node 上级id
	 * @return
	 */
	@RequestMapping ( "/findTree.do" )
	@ResponseBody
	public String findTree(HttpServletRequest request,
			@RequestParam("node") String node){
		logger.debug("查询模块树形菜单");
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					return moduleService.findTree(node);
				}
			} 
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "查询模块树形菜单失败";
			logger.error(msg + e);
			return new ViewObject(-1, msg).toJSon();
		}	
	}
}
