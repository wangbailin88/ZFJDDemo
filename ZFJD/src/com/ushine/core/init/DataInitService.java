package com.ushine.core.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ushine.common.utils.SpringUtils;
import com.ushine.core.po.Module;
import com.ushine.core.po.Operation;
import com.ushine.core.po.Organiz;
import com.ushine.core.po.Person;
import com.ushine.core.po.Resource;
import com.ushine.core.po.Role;
import com.ushine.core.relolver.CoreConfigRelolver;
import com.ushine.core.service.IModuleService;
import com.ushine.core.service.IOperationService;
import com.ushine.core.service.IOrganizService;
import com.ushine.core.service.IPermitService;
import com.ushine.core.service.IPersonService;
import com.ushine.core.service.IResourceService;
import com.ushine.core.service.IRoleService;

/**
 * 解析WEB-INF下的app-init-config.xml文件
 * (单例实现)
 * @author franklin
 *
 */
@Component("appInitServiceTest")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class DataInitService {
	private static final Logger logger = LoggerFactory.getLogger(DataInitService.class);
	
	private CoreConfigRelolver coreRelolver;
	
	private IOrganizService orgService;
	
	private IModuleService moduleService;
	
	private IPersonService personService;
	
	private IRoleService roleService;
	
	private IPermitService permitService;
	
	private IOperationService operService;
	
	private IResourceService resService;
	
	@Test
	public void DataInitService() {
		orgService = (IOrganizService) SpringUtils.getBean("organizServiceImpl");
		System.out.println(orgService);
		moduleService = (IModuleService) SpringUtils.getBean("moduleServiceImpl");
		personService = (IPersonService) SpringUtils.getBean("personServiceImpl");
		roleService = (IRoleService) SpringUtils.getBean("roleServiceImpl");
		permitService = (IPermitService) SpringUtils.getBean("permitServiceImpl");
		operService = (IOperationService) SpringUtils.getBean("operationServiceImpl");
		resService = (IResourceService) SpringUtils.getBean("resourceServiceImpl");
		coreRelolver = CoreConfigRelolver.getInstance();
		try {
			initOrganizData();
			initModuleData();
			initOperationData();
			initRoleData();
			createRolePermit();
			createPersonRole();
			//修改中间库状态
			logger.info("{数据初始化}成功初始化基础数据.");
		} catch(Exception e) {
			logger.error("初始化基础数据失败，强制关闭应用程序.", e);
			System.exit(-1);
		}
		
	}
	

	/**
	 * 初始化组织机构数据
	 * @throws Exception
	 */
	private void initOrganizData() throws Exception {
		List<Organiz> organizs = coreRelolver.getOrganizModel();
		
		for(Organiz org : organizs) {
			orgService.createNewOrganiz(org);
		}
		logger.info("成功初始化组织机构数据...");
	}
	
	/**
	 * 初始化系统模块数据
	 * @throws Exception
	 */
	private void initModuleData() throws Exception {
		List<Module> modules = coreRelolver.getModuleModel();
		
		for(Module module : modules) {
			moduleService.createNewModule(module);
		}
		logger.info("成功初始化系统模块数据...");
	}
	
	/**
	 * 初始化系统操作
	 * @throws Exception
	 */
	private void initOperationData() throws Exception {
		List<Operation> operations = coreRelolver.getOperationModel();
		
		for(Operation oper : operations) {
			operService.createNewOperation(oper);
		}
		logger.info("成功初始化系统操作...");
	}
	
	/**
	 * 初始化系统角色数据
	 * @throws Exception
	 */
	private void initRoleData() throws Exception {
		List<Role> roles = coreRelolver.getRoleModel();
		
		for(Role role : roles) {
			roleService.createNewRole(role);
		}
		logger.info("成功初始化系统角色数据...");
	}
	
	/**
	 * 建立人员、角色关联关系
	 * @throws Exception
	 */
	private void createPersonRole() throws Exception {
		Map<String, String> rolePerson = coreRelolver.getRolePerson();
		
		Set<Entry<String, String>> entries = rolePerson.entrySet();
		for(Entry<String, String> entry : entries) {
			
			// 获取角色
			String roleName = entry.getKey();
			List<Role> roles = roleService.findRoleByName(roleName);
			String[] roleIds = new String[roles.size()];
			int i=0;
			for(Role role : roles) {
				roleIds[i] = role.getId();
				i++;
			}
			
			
			
			// 获取人员，并建立关联关系, 并设置业务列模型
			String[] userNames = entry.getValue().split(",");
			for(String uName : userNames) {
				List<Person> persons = personService.findPersonByName(uName);
				
				for(Person person : persons) {
					roleService.setPersonRole(person.getId(), roleIds);
					
				}
			}
		}
		logger.info("成功建立人员、角色关联关系...");
	}
	
	/**
	 * 建立角色、权限关联关系
	 * @throws Exception
	 */
	private void createRolePermit() throws Exception {
		Map<String, List<String>> rolePermit = coreRelolver.getRolePermit();
		
		Set<Entry<String, List<String>>> entries = rolePermit.entrySet();
		for(Entry<String, List<String>> entry : entries) {
			
			// 获取角色
			String roleName = entry.getKey();
			List<Role> roles = roleService.findRoleByName(roleName);
			String roleIds = null;
			for(Role role : roles) {
				roleIds = role.getId();
			}
			
			// 获取资源、操作组成权限，并与角色建立关系
			List<String> permitCodes = entry.getValue();
			String[] resIds = new String[permitCodes.size()];
			String[] operIds = new String[permitCodes.size()];
			int j=0;
			for(String permitCode : permitCodes) {
				String[] temp = permitCode.split(",");
				System.out.println(temp);
				List<Resource> resList = resService.findResourcesByCode(temp[0]);
				if(resList!=null && resList.size()>0) {
					resIds[j] = resList.get(0).getId();
				} else {
					continue;
				}
				
				List<Operation> operList = operService.findOperationsByCode(temp[1]);
				if(resList!=null && resList.size()>0) {
					operIds[j] = operList.get(0).getId();
				} else {
					continue;
				}
				
				j++;
			}
			
			permitService.setRolePermit(roleIds, resIds, operIds);
		}
		logger.info("成功建立角色、权限关联关系...");
	}
	

	
}
