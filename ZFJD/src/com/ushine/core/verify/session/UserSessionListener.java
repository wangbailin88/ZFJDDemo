package com.ushine.core.verify.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户Session监听器，用于统计用户在线信息
 * @author franklin
 *
 */
public class UserSessionListener implements HttpSessionListener {
	private static final Logger logger = LoggerFactory.getLogger(UserSessionListener.class);
	
	public void sessionCreated(HttpSessionEvent hsEvent) {
		logger.debug("Create Session: " + hsEvent.getSession().getId());
	}
	
	/**
	 * 用户登出后出发事件删除在线用户列表中该用户的信息
	 */
	public void sessionDestroyed(HttpSessionEvent hsEvent) {
		String sessionId = hsEvent.getSession().getId();
		logger.debug("Destroy Session" + sessionId);
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		sessionMgr.removeOnlineUser(sessionId); // 移除用户在线信息
	}
	
}
