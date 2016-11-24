package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.Role;

/**
 * 角色服务接口，用于对外部提供添加、删除及对人员设置角色的方法
 * @author franklin
 * @author 熊永涛修改 140828
 */
public interface IRoleService extends IJSonHandle<Role> {

	/**
	 * 创建新角色
	 * @param role Role 角色
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String createNewRole(Role role) throws Exception;
	
	/**
	 * 按id删除角色
	 * @param roleIds String[]
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String deleteRoleById(String[] roleIds) throws Exception;
	/**
	 * 根据id删除角色
	 * @param id
	 * @return
	 * @throws Exception
	 * @autho xyt
	 */
	public String delById(String id) throws Exception;
	/**
	 * 修改角色信息
	 * @param role Role
	 * @return String
	 * @throws Exception
	 */
	public String modifyRoleInfo(Role role) throws Exception;
	
	/**
	 * 设置用户的角色
	 * @param pesonId String 用户ID
	 * @param roleIds String[] 角色ID
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String setPersonRole(String pesonId, String[] roleIds) throws Exception;
	
	/**
	 * 查询指定用户的角色信息
	 * @param personId String
	 * @return List<Role>
	 * @throws Exception
	 */
	public List<Role> findRoleByPerson(String personId) throws Exception;
	
	/**
	 * 按id查询角色
	 * @param id String
	 * @return Role
	 * @throws Exception
	 */
	public Role findRoleById(String id) throws Exception;
	
	/**
	 * 通过名称查询角色信息
	 * @param name String
	 * @return Role List<Role>
	 * @throws Exception
	 */
	public List<Role> findRoleByName(String name) throws Exception;
	
	/**
	 * 查询全部角色信息
	 * @return List<Role>
	 * @throws Exception
	 */
	public List<Role> findRoleAll() throws Exception;
	
}
