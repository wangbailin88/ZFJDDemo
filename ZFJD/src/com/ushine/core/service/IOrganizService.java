package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.Organiz;

/**
 * 组织机构服务接口，负责添加、删除、查询组织机构信息
 * @author franklin
 *
 */
public interface IOrganizService extends IJSonHandle<Organiz> {

	/**
	 * 创建新组织机构
	 * @param org Organiz
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String createNewOrganiz(Organiz org) throws Exception;
	/**
	 * 修改组织机构
	 * @param org Organiz
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String updateOrganiz(Organiz org) throws Exception;
	/**
	 * 查询所有组织
	 * @return
	 * @throws Exception
	 */
	public List<Organiz> findAll() throws Exception;
	/**
	 * 删除组织机构
	 * @param ids String[]
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String deleteOrganizById(String[] ids) throws Exception;
	
	/**
	 * 按id查询组织机构信息
	 * @param orgId String 
	 * @return Organiz
	 * @throws Exception
	 */
	public Organiz findOrganizById(String orgId) throws Exception;
	
	/**
	 * 按名称查询组织机构信息
	 * @param name String 
	 * @return List<Organiz>
	 * @throws Exception
	 */
	public List<Organiz> findOrganizsByName(String name) throws Exception;
	
	/**
	 * 查询组织机构信息层次树
	 * @return List<Organiz>
	 * @throws Exception
	 */
	public List<Organiz> findOrganizsTree() throws Exception;
	
	/**
	 * 查询指定地区的组织机构信息
	 * @param region String
	 * @return List<Organiz>
	 * @throws Exception
	 */
	public List<Organiz> findOrganizsTreeByRegion(String region) throws Exception;
	/**
	 * 查询下级组织
	 * @param pid 上级组织id
	 * @return List<Organiz>
	 * @throws Exception
	 */
	public List<Organiz> findSubTree(String pid) throws Exception;
	/**
	 * 根据组织id删除组织
	 * @param orgId
	 * @throws Exception
	 *2015-12-28
	 */
	public void deleteOrganizById(String orgId)throws Exception;
}
