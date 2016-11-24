package com.ushine.core.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Person;
import com.ushine.core.po.Role;
import com.ushine.core.service.IPersonService;
import com.ushine.core.service.IRoleService;
import com.ushine.dao.IBaseDao;

/**
 * 角色服务接口，用于对外部提供添加、删除及对人员设置角色的方法
 * @author franklin
 *
 */
@Transactional
@Service("roleServiceImpl")
public class RoleServiceImpl implements IRoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private IBaseDao<Role, String> roleDao;

	@Autowired
	private IPersonService personService;
	
	public String createNewRole(Role role) throws Exception {
		roleDao.save(role);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加角色信息.").toJSon();
	}

	public String deleteRoleById(String[] roleIds) throws Exception {
		roleDao.deleteById(Role.class, roleIds);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除角色信息.").toJSon();
	}
	
	public String modifyRoleInfo(Role role) throws Exception {
		roleDao.update(role);
		logger.debug("更新角色("+role.getName()+")信息.");
		return new ViewObject(ViewObject.RET_SUCCEED, "成功修改角色信息.").toJSon();
	}
	
	public String setPersonRole(String pesonId, String[] roleIds)
			throws Exception {
		Person person = personService.findPersonById(pesonId);
		logger.debug("设置用户(" + person.getUserName() + ")的角色信息.");
		person.getRoles().clear(); // 清除原来的角色信息
		for(String roleId : roleIds) {
			Role role = roleDao.findById(Role.class, roleId);
			person.getRoles().add(role);
		}
		personService.modifyPersonInfo(person); // 更新用户信息
		return new ViewObject(ViewObject.RET_SUCCEED, 
				"成功设置用户(" + person.getUserName() + ")的角色信息.").toJSon();
	}
	public String delById(String id) throws Exception {
		roleDao.deleteById(Role.class, id);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除角色信息.").toJSon();
	}
	@Transactional(readOnly=true)
	public List<Role> findRoleByPerson(String personId) throws Exception {
		Person person = personService.findPersonById(personId);
		int size = person.getRoles().size();
		logger.debug("用户("+person.getUserName()+")拥有(" + size + ")个角色.");
		return person.getRoles();
	}
	
	@Transactional(readOnly=true)
	public Role findRoleById(String id) throws Exception {
		
		return roleDao.findById(Role.class, id);
	}
	
	@Transactional(readOnly=true)
	public List<Role> findRoleByName(String name) throws Exception {
		
		return roleDao.findByProperty(Role.class, "name", name);
	}
	
	@Transactional(readOnly=true)
	public List<Role> findRoleAll() throws Exception {
		
		return roleDao.findAll(Role.class);
	}
	
	//*************************************************************
	//* 返回Json数据 
	//*************************************************************		
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Role> list) {
		JSONArray array = new JSONArray();
		for(Role role : list) {
			array.add(role.getId());
		}
		return array.toString();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Role> vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Role> list) {//熊永涛添加
		JSONArray array=new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Role role=list.get(i);
			JSONObject obj=new JSONObject();
			obj.put("id", role.getId());
			obj.put("status", role.getStatus());
			obj.put("text", role.getName());
			obj.put("leaf", "true");
			obj.put("expanded", "false");
			array.add(obj);
		}
		return array.toString();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Role> list) {
		JSONArray array = new JSONArray();
		for(Role role : list) {
			JSONObject object = new JSONObject();
			object.put("text", role.getName());
			object.put("value", role.getId());
			array.add(object);
		}
		return array.toString();
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Role, String> getRoleDao() {
		return roleDao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setRoleDao(IBaseDao<Role, String> roleDao) {
		this.roleDao = roleDao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IPersonService getPersonService() {
		return personService;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}
	
}
