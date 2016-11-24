package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.Module;

/**
 * 模块服务接口，对外提供添加、删除、查询模块信息的方法
 * @author franklin
 *
 */
public interface IModuleService extends IJSonHandle<Module> {

	/**
	 * 查询所有模块
	 * @return json
	 * @throws Exception
	 */
	public String findAll() throws Exception;
	
	/**
	 *	查询模块树形菜单
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public String findTree(String node) throws Exception;
	
	/****************************************************************************/
	/**
	 * 检查新模块
	 * @param module Module
	 * @return String {msg:"",status:}
	 * @throws Exception
	 */
	public String createNewModule(Module module) throws Exception;
	
	/**
	 * 按id删除模块
	 * @param ids String[]
	 * @return String {msg:"",status:}
	 * @throws Exception
	 */
	public String deleteModuleById(String[] ids) throws Exception;
	
	/**
	 * 按id查询模块
	 * @param id String
	 * @return Module
	 * @throws Exception
	 */
	public Module findModuleById(String id) throws Exception;
	
	/**
	 * 按名称查询模块(只能查询到根模块）
	 * @param name String
	 * @return List<Module>
	 * @throws Exception
	 */
	public List<Module> findModulesByName(String name) throws Exception;
	
	/**
	 * 查询全部模块(只能查询到根模块）
	 * @return List<Module>
	 * @throws Exception
	 */
	public List<Module> findModulesAll() throws Exception;
	
}
