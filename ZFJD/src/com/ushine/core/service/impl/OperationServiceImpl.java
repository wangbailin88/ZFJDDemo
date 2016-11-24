package com.ushine.core.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Operation;
import com.ushine.core.service.IOperationService;
import com.ushine.dao.IBaseDao;

/**
 * 操作信息服务接口实现
 * @author franklin
 *
 */
@Transactional
@Service("operationServiceImpl")
public class OperationServiceImpl implements IOperationService {

	@Autowired
	private IBaseDao<Operation, String> operDao;
	
	public String createNewOperation(Operation oper) throws Exception {
		operDao.save(oper);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加操作信息.").toJSon();
	}

	public String deleteOperationsById(String[] ids) throws Exception {
		operDao.deleteById(Operation.class, ids);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加操作信息.").toJSon();
	}

	@Transactional(readOnly=true)
	public Operation findOperationsById(String id) throws Exception {
		
		return operDao.findById(Operation.class, id);
	}

	@Transactional(readOnly=true)
	public List<Operation> findOperationsByCode(String code) throws Exception {
		
		return operDao.findByProperty(Operation.class, "code", code);
	}
	
	@Transactional(readOnly=true)
	public List<Operation> findOperationsByType(int type) throws Exception {
		
		return operDao.findByProperty(Operation.class, "type", type);
	}

	@Transactional(readOnly=true)
	public List<Operation> findOperations() throws Exception {
		
		return operDao.findAll(Operation.class);
	}

	//*************************************************************
	//* 返回Json数据 
	//*************************************************************		

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Operation> list) {
		
		return JSONArray.fromObject(list).toString();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Operation> vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Operation> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Operation> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Operation, String> getOperDao() {
		return operDao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setOperDao(IBaseDao<Operation, String> operDao) {
		this.operDao = operDao;
	}
	
}
