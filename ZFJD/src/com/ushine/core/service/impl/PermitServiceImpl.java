package com.ushine.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.common.utils.ExportDMUtils;
import com.ushine.common.utils.InitUtils;
import com.ushine.common.utils.RandomUtils;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Module;
import com.ushine.core.po.Operation;
import com.ushine.core.po.Organiz;
import com.ushine.core.po.Permit;
import com.ushine.core.po.Person;
import com.ushine.core.po.Resource;
import com.ushine.core.po.Role;
import com.ushine.core.service.IPermitService;
import com.ushine.dao.IBaseDao;

/**
 * 权限服务接口的实现
 * @author franklin
 *
 */
@Transactional
@Service("permitServiceImpl")
public class PermitServiceImpl implements IPermitService {
	private static final Logger logger = LoggerFactory.getLogger(PermitServiceImpl.class);
	
	@Autowired
	IBaseDao<Permit,String> permitDao;
	
	@Autowired
	IBaseDao<String,String> baseDao;
	
	@Autowired
	IBaseDao<Module,String> moduleDao;
	
	@Autowired
	IBaseDao<Operation,String> operDao;
	
	@Autowired
	IBaseDao<Role,String> roleDao;
	
	@Autowired
	IBaseDao<Person,String> perDao;
	
	@Autowired
	IBaseDao<Resource,String> resceDao;
	@Autowired
	IBaseDao<Organiz,String> orgDao;
	public void initPermitModel() throws Exception {
		
		ExportDMUtils.exportDM();
		List<Organiz> orgs=InitUtils.getOrganiz();
		for (Organiz o:orgs) {
			orgDao.save(o);
		}
		List<Module> modules = InitUtils.getModules();
		for(Module module : modules) {
			moduleDao.save(module);
		}
		
		logger.debug("解析并保存配置文件中的操作信息.");
		List<Operation> operations = InitUtils.getOperations();
		for(Operation oper : operations) {
			operDao.save(oper);
		}
		List<Role> roles=InitUtils.getRoles();
		for(Role role : roles) {
			List<Permit> permits=role.getPermits();
			for(Permit permit : permits){
				permit.setOperation(operDao.findByProperty(Operation.class, "code", permit.getOperation().getCode()).get(0));
				permit.setResource(resceDao.findByProperty(Resource.class, "code", permit.getResource().getCode()).get(0));
			}
			roleDao.save(role);
		}
		Person p=new Person();
		p.setUserName("admin");
		p.setPassword("123");
		List<Role> rols=roleDao.findByProperty(Role.class, "name", "超级管理员");
		p.setRoles(rols);
		perDao.save(p);
		logger.debug("初始化系统权限配置文件完成.");
	}
	public String setRolePermit(String roleId, String[] resId, String[] operId)
			throws Exception {
		
		// 清除原有权限
		//permitDao.executeSql("DELETE FROM t_permit WHERE role_id = '" + roleId + "'"); // Ful 修改清除方法（不能全部清除，只能某一菜单下的资源）
		
		for(String rId : resId) {
			permitDao.executeSql("DELETE FROM T_PERMIT WHERE role_id = '" + roleId + "' AND resource_id = '" + rId + "'");
		}
		logger.debug("成功清除角色("+roleId+")原有权限.");
		
		// 添加新权限
		for(int i=0; i<resId.length; i++) {
			permitDao.executeSql("INSERT INTO T_PERMIT" +
					"(id, operation_id, resource_id, role_id) " +
					"VALUES('"+RandomUtils.getRandomString(32)+"', '"+operId[i]+"', '"+resId[i]+"', '"+roleId+"')");
		}
		logger.debug("成功设置角色("+roleId+")权限信息.");
		
		return new ViewObject(ViewObject.RET_SUCCEED, "成功设置角色的权限信息.").toJSon();
	}
	
	@Transactional(readOnly=true)
	public List<String> getRolePermitCode(List<String> roleIds, String resId) throws Exception {
		List<String> codes = new ArrayList<String>();
		
		if(roleIds != null) {
			for(String roleId : roleIds) {
				// 查询角色对指定资源拥有的权限操作
				@SuppressWarnings("unchecked")
				List<String> tmp = (List<String>) baseDao.findBySql("SELECT t2.code as code FROM (" +
						"SELECT operation_id as oper_id FROM T_PERMIT WHERE resource_id='" + resId + "' AND role_id='"+roleId+"') t1, " +
						"T_OPERATION t2 WHERE t1.oper_id=t2.id");
				if(tmp.size() > 0) {
					codes.add(tmp.get(0));
				}
			}
		}
		
		return codes;
	}

	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Permit, String> getPermitDao() {
		return permitDao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setPermitDao(IBaseDao<Permit, String> permitDao) {
		this.permitDao = permitDao;
	}
	
}
