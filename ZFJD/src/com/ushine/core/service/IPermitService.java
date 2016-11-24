package com.ushine.core.service;

import java.util.List;

/**
 * 权限服务接口，提供对部门、用户、角色设置权限的方法
 * @author franklin
 *
 */
public interface IPermitService {
	/**
	 * 初始化数据库
	 * @throws Exception
	 */
	public void initPermitModel() throws Exception;
	/**
	 * 设置角色的权限
	 * @param roleId String 角色id
	 * @param resId String 资源id
	 * @param operId String 操作id
	 * @throws Exception
	 */
	public String setRolePermit(String role, String[] resId, 
			String[] operId) throws Exception; 
	
	/**
	 * 获取指定用户权限资源的操作（支持多角色）
	 * @param List<String> 角色id
	 * @param resId String 资源id
	 * @return List<String> 操作coed
	 * @throws Exception
	 */
	public List<String> getRolePermitCode(List<String> list, String resId) throws Exception;
}
