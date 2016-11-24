package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.Menu;

/**
 * 菜单服务接口：对外提供添加、删除、查询菜单的方法
 * @author franklin
 *
 */
public interface IMenuService extends IJSonHandle<Menu> {

	/**
	 * 创建新菜单
	 * @param menu
	 * @return String {msg:"",status:}
	 * @throws Exception
	 */
	public String createNewMenu(Menu menu) throws Exception;
	
	/**
	 * 按id删除菜单
	 * @param id String[]
	 * @return String {msg:"",status:}
	 * @throws Exception
	 */
	public String deleteMenusBydId(String[] id) throws Exception;
	
	/**
	 * 按id查询菜单
	 * @param id String
	 * @return Menu
	 * @throws Exception
	 */
	public Menu findMenuById(String id) throws Exception;
	/**
	 * 根据父级菜单id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Menu> findByParentId(String id) throws Exception;
	/**
	 * 按名称查询菜单
	 * @param name String
	 * @return List<Menu>
	 * @throws Exception
	 */
	public List<Menu> findMenusByName(String name) throws Exception;
	
	/**
	 * 查看全部菜单树
	 * @return List<Menu>
	 * @throws Exception
	 */
	public List<Menu> findMenusTree() throws Exception;
	
	/**
	 * 按模块查询菜单树
	 * @param moduleId String 模块id
	 * @return List<Menu>
	 * @throws Exception
	 */
	public List<Menu> findMenusTreeByModule(String moduleId) throws Exception;
	
}
