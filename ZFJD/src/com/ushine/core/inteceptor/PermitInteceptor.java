package com.ushine.core.inteceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ushine.core.cache.ResCache;
import com.ushine.core.po.Resource;
import com.ushine.core.service.IPermitService;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 权限拦截器，作用于拦截拥有权限的资源
 * 1. 在资源中声明拦截地址
 * 2. 用户请求资源地址，拦截器做出相应处理（获取用户对该资源的操作代码）
 * 3. 将资源代码加入Request中
 * 4. 在控制器中获取操作代码做出相应处理
 * @author franklin
 *
 */
@Component("permitInteceptor")
public class PermitInteceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(PermitInteceptor.class);
	
	@Autowired
	private IPermitService permitService;
	
	//*******************************************************************
	//* Action之前执行
	//*******************************************************************
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		
		String reqUrl = request.getServletPath(); // 用户请求地址
		ResCache resCache = ResCache.getInstance(); // 判读资源是否有权限
		Resource res = resCache.getResource(reqUrl);
		
		if(res != null) {
			// 获取用户对该资源的操作权限
			logger.debug("[权限拦截器 ]已拦截到权限资源(" + res.getName() + ").");
			
			
			List<String> codes =  permitService.getRolePermitCode(
					sessionMgr.getRoleIds(request), res.getId());
			sessionMgr.setPermitResOperCode(request, codes);
		} else {
			sessionMgr.setPermitResOperCode(request, null);
		}
		
		return super.preHandle(request, response, handler);
	}
	
	//*******************************************************************
	//* 生成视图之前执行
	//*******************************************************************
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// String reqUrl = request.getRequestURI(); // 用户请求地址
		
		super.postHandle(request, response, handler, modelAndView);
	}

	//*******************************************************************
	//* 最后执行，可用于释放资源
	//*******************************************************************
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// String reqUrl = request.getRequestURI(); // 用户请求地址
		
		// 清除资源操作记录
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		sessionMgr.setPermitResOperCode(request, null);
		super.afterCompletion(request, response, handler, ex);
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
	
}