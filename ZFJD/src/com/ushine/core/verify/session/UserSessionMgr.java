package com.ushine.core.verify.session;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ushine.common.vo.ViewObject;

/**
 * 用户会话管理器，主要用于获取用户的登陆信息，包括
 * 用户登陆后记录的uId、基本信息、角色、权限
 * 
 * @author franklin
 *
 */
public class UserSessionMgr {
	private static final Logger logger = LoggerFactory.getLogger(UserSessionMgr.class);
	
	public static final String U_ID = "uId"; // uId String 用户id
	
	public static final String U_NAME = "uName"; // uName String 用户名
	
	public static final String U_CODE = "uCode"; // uName String 用户名
	
	public static final String T_NAME = "tName"; // tName String 真实姓名
	
	public static final String U_DID = "uDId"; // uDId String 用户所在部门id
	
	public static final String U_OID = "uOId"; // uDId String 用户所在部门id
	
	public static final String U_LA = "uLA"; // uLA String IP
	
	public static final String U_ROLES = "uRole"; // 用户角色
	
	public static final String PERMIT_OPER = "permitOperCode";
	
	private static UserSessionMgr sessionMgr = null;
	
	private static final String  C_COM="cCOM";
	private UserSessionMgr() {
		
	}
	
	/**
	 * 在线用户列表（SessionId, UserId）
	 */
	private Map<String, HttpSession> onlineList = new HashMap<String, HttpSession>();
	
	/**
	 * 登入注册用户会话信息
	 */
	public void register(HttpServletRequest request, String userInfo) {
		JSONObject object = JSONObject.fromObject(userInfo);
		HttpSession uSession = request.getSession();
		uSession.setAttribute(U_ID, object.get(U_ID));
		uSession.setAttribute(U_NAME, object.get(U_NAME));
		uSession.setAttribute(U_CODE, object.get(U_CODE));
		uSession.setAttribute(T_NAME, object.get(T_NAME));
		uSession.setAttribute(U_DID, object.get(U_DID));
		uSession.setAttribute(U_OID, object.get(U_OID));
		uSession.setAttribute(U_LA, request.getLocalAddr());
		onlineList.put(uSession.getId(), uSession);
		logger.debug("用户Session设置完成:" + userInfo);
	}
	
	/**
	 * 注册用户的角色信息
	 * @param request HttpServletRequest
	 * @param roleInfo roleInfo 角色信息(JSON)
	 */
	public void registerUserRole(HttpServletRequest request, String roleInfo) {
		HttpSession uSession = onlineList.get(request.getSession().getId());
		if(uSession != null) {
			List<String> roles = new ArrayList<String>();
			JSONArray array = JSONArray.fromObject(roleInfo);
			for(int i=0; i<array.size(); i++) {
				roles.add(array.getString(i));
			}
			uSession.setAttribute(U_ROLES, roles);
			logger.debug("用户(" + getUDID(request) + ")角色" + roleInfo + "信息加入Session完成.");
		} else {
			logger.warn("在线列表中没有该Session存在.");
		}
		
	}
	
	/**
	 * 手动调用退出操作
	 * @param request HttpServletRequest
	 */
	public void logout(HttpServletRequest request) {
		HttpSession uSession = request.getSession();
		logger.debug("用户(" + getName(request) + ")手动执行退出操作.");
		uSession.invalidate();
	}
	
	/**
	 * 检查当前用户是否已经登录
	 * @param request HttpServletRequest
	 * @return
	 */
	public boolean isLogin(HttpServletRequest request) {
		HttpSession uSession = onlineList.get(request.getSession().getId());
		if(uSession != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 删除用户的在线信息(该方法是SessionListener的回调方法)
	 */
	public void removeOnlineUser(String sessionId) {
		HttpSession session = onlineList.get(sessionId);
		if(session != null) {
			String uName = (String) session.getAttribute(U_NAME);
			onlineList.remove(sessionId);
			logger.debug("清除用户(" + uName + ")在线信息.");
		} else {
			logger.warn("在线列表中没有该Session存在.");
		}
	}
	
	/**
	 * 获取在线总人数
	 * @return int
	 */
	public int getOnlineListSize() {
		return onlineList.size();
	}
	
	/**
	 * 获取全部在线用户的用户名
	 * @return String Json数组
	 */
	public String getOnlineListUserNmae() {
		List<String> userNames = new ArrayList<String>();
		Set<String> sessionIds = onlineList.keySet();
		// 循环迭代获取全部Value
		for(Iterator<String> it = sessionIds.iterator(); it.hasNext();) {
			String sessionId = (String)it.next();
			HttpSession session = onlineList.get(sessionId);
			userNames.add((String) session.getAttribute(U_NAME));
		}
		
		return JSONArray.fromObject(userNames).toString();
	}
	
	//**************************************************************
	//* 以下是对当前用户的操作
	//**************************************************************
	
	/**
	 * 在Request中权限操作代码（支持多角色）
	 * @param request HttpServletRequest
	 * @param codes List<String> 操作代码
	 */
	public void setPermitResOperCode(HttpServletRequest request, List<String> codes) {
		request.setAttribute(PERMIT_OPER, codes);
	}
	
	/**
	 * 获取Request中权限操作代码（支持多角色）
	 * @param request HttpServletRequest
	 * @return List<String> 操作代码
	 */
	@SuppressWarnings("unchecked")
	public List<String> getPermitResOperCode(HttpServletRequest request) {
		return (List<String>) request.getAttribute(PERMIT_OPER);
	}
	
	/**
	 * 获取当前用户登陆后记录的用户id
	 * @param request HttpServletRequest
	 * @return String
	 */
	public String getUID(HttpServletRequest request) {
		
		return (String) request.getSession().getAttribute(U_ID);
	}
	
	/**
	 * 获取当前用户登陆后记录的用户名
	 * @param request HttpServletRequest
	 * @return String
	 */
	public String getName(HttpServletRequest request) {
	
		return (String) request.getSession().getAttribute(U_NAME);
	}
	
	/**
	 * 获取当前用户登陆后记录的真实姓名
	 * @param request HttpServletRequest
	 * @return String
	 */
	public String getTrueName(HttpServletRequest request) {
		
		return (String) request.getSession().getAttribute(T_NAME);
	}
	
	/**
	 * 获取当前用户登陆后记录的所在部门id
	 * @param request HttpServletRequest
	 * @return String
	 */
	public String getUDID(HttpServletRequest request) {
		
		return (String) request.getSession().getAttribute(U_DID);
	}
	
	/**
	 * 获取当前用户登陆后记录的所在组织id 
	 * @param request HttpServletRequest
	 * @return String
	 */
	public String getUOID(HttpServletRequest request) {
	
		return (String) request.getSession().getAttribute(U_OID);
	}
	
	/**
	 * 获取当前用户登陆后记录的IP
	 * @param request HttpServletRequest
	 * @return String
	 */
	public String getLocalAddr(HttpServletRequest request) {
		
		return (String) request.getSession().getAttribute(U_LA);
	}
	/**
	 * 获取用户编号
	 * @param request
	 * @return
	 */
	public String getCode(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(U_CODE);
	}

	/**
	 * 获取列模型
	 * @param request
	 * @return
	 */
	public String getColoums(HttpServletRequest request) {
		return (String)request.getSession().getAttribute(C_COM);
	}

	/**
	 * 获取当前用户的角色id
	 * @param request HttpServletRequest
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public List<String> getRoleIds(HttpServletRequest request) {
		
		return (List<String>) request.getSession().getAttribute(U_ROLES);
	}
	
	/**
	 * 获取当前用户信息，并采用Json返回
	 * （id、用户名、真实姓名、部门id、组织id、当前IP）
	 * @param request HttpServletRequest
	 * @return String Json
	 */
	public String getUserInfoByISon(HttpServletRequest request) {
		HttpSession uSession = onlineList.get(request.getSession().getId());
		if(uSession != null) {
			JSONObject object = new JSONObject();
			object.put(U_ID, uSession.getAttribute(U_ID));
			object.put(U_NAME, uSession.getAttribute(U_NAME));
			object.put(U_CODE, uSession.getAttribute(U_CODE));
			object.put(T_NAME, uSession.getAttribute(T_NAME));
			object.put(U_DID, uSession.getAttribute(U_DID));
			object.put(U_OID, uSession.getAttribute(U_OID));
			object.put(U_LA, uSession.getAttribute(U_LA));
			object.put(U_ROLES, JSONArray.fromObject(getRoleIds(request)));
			return object.toString();
		} else {
			ViewObject object = new ViewObject(ViewObject.RET_FAILURE, "当前用户还未登录系统.");
			return object.toJSon();
		}
	}
	
	/**
	 * 获取用户会话管理器实例（单例模式）
	 * @return UserSessionMgr
	 */
	public static UserSessionMgr getInstance() {
		if(sessionMgr == null) {
			sessionMgr = new UserSessionMgr();
		}
		return sessionMgr;
	}
	
	/**
	 * 检查用户是否在线, 如果在则将该用户Session信息从在线列表中清除
	 * @param uName String 用户名
	 */
	public HttpSession getLoginUserSession(String uName){
		Set<Entry<String, HttpSession>> entries = onlineList.entrySet();
		for(Entry<String, HttpSession> entry : entries) {
			HttpSession session = entry.getValue();
			if(session.getAttribute(U_NAME).equals(uName)) {
				return session;
			}
		}
		
		return null;
	}
	
}
