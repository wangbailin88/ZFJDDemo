package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.Department;

/**
 * 部门服务接口，用于对外提供添加、删除、查询部门信息的方法
 * 
 * @author franklin
 *
 */
public interface IDeptService extends IJSonHandle<Department> {

	/**
	 * 创建新部门
	 * @param dept Department
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String createNewDept(Department dept) throws Exception;
	/**
	 * 修改新部门
	 * @param dept Department
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String updateDept(Department dept) throws Exception;
	
	/**
	 * 按id删除部门信息
	 * @param ids String[]
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String deleteDeptsById(String[] ids) throws Exception;
	
	/**
	 * 按id查询部门信息
	 * @param id String
	 * @return Department
	 * @throws Exception
	 */
	public Department findDeptById(String id) throws Exception;
	
	/**
	 * 按名称查询指定地区的部门信息
	 * @param orgId String
	 * @param name String
	 * @return List<Department>
	 * @throws Exception
	 */
	public List<Department> findDeptsTreeByName(String orgId, String name) throws Exception;
	/**
	 * 查询上级部门
	 * @param oId
	 * @return
	 * @throws Exception
	 */
	public List<Department> findDeptsByOid(String oId) throws Exception;
	/**
	 * 根据组织id查询树
	 * @param oId
	 * @return
	 * @throws Exception
	 */
	public String findTreeByOid(String oId) throws Exception;
	/**
	 * 根据父级id查询树
	 * @param pId
	 * @return
	 * @throws Exception
	 */
	public String findTreeByPid(String pId) throws Exception;
	/**
	 * 查询部门层次信息
	 * @return List<Department>
	 * @throws Exception
	 */
	public List<Department> findDeptsTree() throws Exception;
	
	/**
	 * 查询指定组织的部门层次信息
	 * @param orgId
	 * @return List<Department>
	 * @throws Exception
	 */
	public List<Department> findDeptsTreeByOrganiz(String orgId) throws Exception;
	
	
	public String toExtComboJSON(List<Department> list) ;
	/**
	 * 根据部门id删除部门
	 * @param deptId
	 * @throws Exception
	 *2015-12-28
	 */
	public void deleteDeptById(String deptId)throws Exception;
	
	
}
