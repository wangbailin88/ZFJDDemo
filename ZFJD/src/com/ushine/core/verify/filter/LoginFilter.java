package com.ushine.core.verify.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ushine.common.utils.DataResult;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 登陆验证过滤器
 * 
 * @author user
 *
 */
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		PrintWriter writer = null;	
		
		String url = req.getServletPath();
		//如果请求为”/login.do“或者”login.do“将不进行拦截
		if(!"/ca.do".equals(url)&&!"/calogin.do".equals(url)&&!"/login.do".equals(url) && !sessionMgr.isLogin(req)) {
			if(!"/initDB.do".equals(url)) {
				DataResult result = new DataResult();
				result.setStatus(DataResult.STATUS_NO_SIGN_UP);
				result.setMsg("请先登录在操作!");
				
				result.setSuccess(true);
				
				res.setContentType("text/html; charset=UTF-8");
				writer = response.getWriter();
				writer.print(JSONObject.fromObject(result));
				writer.flush();
			} else {
				
				chain.doFilter(req, res);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
