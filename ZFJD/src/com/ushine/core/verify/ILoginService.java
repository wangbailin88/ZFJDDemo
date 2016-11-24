package com.ushine.core.verify;


/**
 * 登陆操作服务接口，，提供登陆判断、在线判断、权限判断方法实现
 * 
 * @author franklin
 *
 */
public interface ILoginService {

	/**
	 * 用户登陆验证操作
	 * @param uName String 用户名
	 * @param uPass String 密码
	 * @return String 成功后返回用户信息，否则为null
	 * @throws Exception
	 */
	public String isLogin(String uName, String uPass) throws Exception;
	
	/**
	 * 用户CA认证登录验证操作
	 * @Description: TODO
	 * @param userName 账户名称
	 * @return
	 * @throws Exception
	 * @author zhangkq
	 * @date 2015-11-13
	 */
	public String iscaLogin(String userName) throws Exception;
	
}
