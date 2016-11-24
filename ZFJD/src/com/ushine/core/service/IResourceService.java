package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.Resource;

/**
 * 资源服务接口，用于对外提供添加、删除、查询资源信息的方法
 * 
 * @author franklin
 *
 */
public interface IResourceService extends IJSonHandle<Resource> {

	/**
	 * 创建新资源
	 * @param res Resource 
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String createNewResource(Resource res) throws Exception;
	
	/**
	 * 按id删除资源
	 * @param ids String[]
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String deleteResourcesById(String[] ids) throws Exception;
	
	/**
	 * 按id查询资源
	 * @param id String 
	 * @return Resource
	 * @throws Exception
	 */
	public Resource findResourceById(String id) throws Exception;
	
	/**
	 * 按名称查询资源
	 * @param name String
	 * @return List<Resource>
	 * @throws Exception
	 */
	public List<Resource> findResourcesByName(String name) throws Exception;
	
	/**
	 * 按代码查询资源
	 * @param name String
	 * @return List<Resource>
	 * @throws Exception
	 */
	public List<Resource> findResourcesByCode(String code) throws Exception;
	
	/**
	 * 查询指定查单下的资源
	 * @param menuId String
	 * @return List<Resource>
	 * @throws Exception
	 */
	public List<Resource> findResourcesByMenu(String menuId) throws Exception;
	
	/**
	 * 查询资源拦截器
	 * @param inteceptor String 拦截地址
	 * @return List<Resource> 拥有该拦截地址的资源
	 * @throws Exception
	 */
	public List<Resource> findResInteceptor(String inteceptor) throws Exception;
	
	/**
	 * 查看全部资源
	 * @return List<Resource>
	 * @throws Exception
	 */
	public List<Resource> findResources() throws Exception;
	
}
