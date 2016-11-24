package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.core.po.Operation;

/**
 * 操作服务接口: 用于对外提供添加、删除、查询操作信息的方法
 * @author franklin
 *
 */
public interface IOperationService extends IJSonHandle<Operation> {

	/**
	 * 创建新操作信息
	 * @param oper Operation oper
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String createNewOperation(Operation oper) throws Exception;
	
	/**
	 * 按id删除操作信息
	 * @param ids String[]
	 * @return String {msg:"", status:""}
	 * @throws Exception
	 */
	public String deleteOperationsById(String[] ids) throws Exception;
	
	/**
	 * 按id查询操作信息
	 * @param id String
	 * @return Operation
	 * @throws Exception
	 */
	public Operation findOperationsById(String id) throws Exception;
	
	/**
	 * 按代码查询操作信息
	 * @param code String
	 * @return Operation
	 * @throws Exception
	 */
	public List<Operation> findOperationsByCode(String code) throws Exception;
	
	/**
	 * 按类型查找操作信息
	 * @param type int 
	 * @return List<Operation>
	 * @throws Exception
	 */
	public List<Operation> findOperationsByType(int type) throws Exception;
	
	/**
	 * 查询全部操作信息
	 * @return List<Operation>
	 * @throws Exception
	 */
	public List<Operation> findOperations() throws Exception;
	
}
