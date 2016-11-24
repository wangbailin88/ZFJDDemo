package com.ushine.core.relolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ushine.common.config.Constant;
import com.ushine.common.utils.EncryptUtils;
import com.ushine.common.utils.PathUtils;
import com.ushine.common.utils.XMLUtils;
import com.ushine.core.po.Department;
import com.ushine.core.po.Menu;
import com.ushine.core.po.Module;
import com.ushine.core.po.Operation;
import com.ushine.core.po.Organiz;
import com.ushine.core.po.Person;
import com.ushine.core.po.Resource;
import com.ushine.core.po.Role;

/**
 * 基本数据模型解释器
 * 用于解释"app-init-config.xml"文件，获取系统基本的数据
 * 
 * @author franklin
 *
 */
public class CoreConfigRelolver {
	private static final Logger logger = LoggerFactory.getLogger(CoreConfigRelolver.class);
	
	private static CoreConfigRelolver coreRelolver = null;
	
	private static final String configFile = "app-init-config.xml";
	
	private static final String TAG_ORGANIZ = "organiz"; // 组织标签
	
	private static final String TAG_DEPT = "department"; // 部门标签
	
	private static final String TAG_PERSON = "person"; // 人员标签
	
	private static final String TAG_MODULE = "module"; // 模块标签
	
	private static final String TAG_MENU = "menu"; // 菜单标签
	
	private static final String TAG_OPERATION_S = "operations"; // 操作组标签
	
	private static final String TAG_OPERATION = "operation"; // 操作标签
	
	private static final String TAG_PERMIT = "permit"; // 权限标签
	
	private static final String TAG_ROLE = "role"; // 角色标签
	
	private static final String ATTR_NAME = "name"; // 名称属性
	
	private static final String ATTR_REGION = "region"; // 地域属性
	
	private static final String ATTR_CONTACTS = "contacts"; // 联系人属性
	
	private static final String ATTR_TEL = "tel"; // 联系电话属性
	
	private static final String ATTR_NUMBER = "number"; // 人员编号属性
	
	private static final String ATTR_TRUENAME = "trueName"; // 真实姓名属性
	
	private static final String ATTR_USERBANE = "userName"; // 用户名称属性
	
	private static final String TAG_RESOURCE = "resource"; // 资源标签
	
	private static final String ATTR_CLASS = "class"; // 类属性
	
	private static final String ATTR_URL = "url"; // 连接地址属性
	
	private static final String ATTR_ICON = "icon"; // 图标属性
	
	private static final String ATTR_OPER_TYPE = "operType"; // 操作类型属性
	
	private static final String ATTR_CODE = "code"; // 代码属性
	
	private static final String ATTR_RES_CODE = "resCode"; // 资源代码属性
	
	private static final String ATTR_OPER_CODE = "operCode"; // 操作代码属性
	
	private static final String ATTR_INTECEPTOR = "inteceptor"; // 拦截器属性
	
	private static final String ATTR_LINK = "link"; // 连接属性
	
	private static final String ATTR_BLACKITEM = "blackitem"; // 黑名单检测属性
	
	private XMLUtils xml = null;
	
	private List<Organiz> organizs = new ArrayList<Organiz>(0);
	
	private List<Module> modules = new ArrayList<Module>(0);
	
	private List<Operation> operations = new ArrayList<Operation>(0);
	
	private List<Role> roles = new ArrayList<Role>(0);
	
	private Map<String, String> rolePerson = new HashMap<String, String>();
	
	private Map<String, List<String>> rolePermit = new HashMap<String, List<String>>();
	
	
	private CoreConfigRelolver() {
		try {
			String filePath = PathUtils.getConfigPath(CoreConfigRelolver.class) + configFile;
			xml = new XMLUtils(filePath);
			
			// 获取组织机构信息
			List<Element> organizEles = xml.getNodes(TAG_ORGANIZ);
			for(Element orgEle : organizEles) {
				organizs.add(relolverOrganizEle(orgEle));
			}
			
			// 获取系统模块信息
			List<Element> moduleEles = xml.getNodes(TAG_MODULE);
			for(Element moduleEle : moduleEles) {
				modules.add(relolverModule(moduleEle));
			}
			
			// 获取定义的操作模型
			List<Element> operGroupEles = xml.getNodes(TAG_OPERATION_S); // 操作组
			for(Element operGroupEle : operGroupEles) {
				int type = Integer.parseInt(xml.getNodeAttrVal(operGroupEle, ATTR_OPER_TYPE));
				List<Element> operEles = xml.getNodes(operGroupEle, TAG_OPERATION); // 操作
				for(Element operEle : operEles) {
					Operation operation = new Operation(null, 
							xml.getNodeAttrVal(operEle, ATTR_NAME), type, 
							xml.getNodeAttrVal(operEle, ATTR_CODE));
					operations.add(operation);
				}
			}
			
			// 获取定义的角色模型
			List<Element> roleEles = xml.getNodes(TAG_ROLE); // 角色
			for(Element roleEle : roleEles) {
				roles.add(relolverRole(roleEle));
			}
			

			logger.info("成功基本数据模型文件:" + filePath + ".");
		} catch(Exception e) {
			logger.error("基本数据模型文件加载失败:" + configFile + ",关闭应用程序.", e);
			System.exit(-1);
		}
	}
	
	/**
	 * 解析组织结构
	 */
	private Organiz relolverOrganizEle(Element orgEle) throws Exception {
		Organiz organiz = new Organiz(null, 
				xml.getNodeAttrVal(orgEle, ATTR_NAME), 
				xml.getNodeAttrVal(orgEle, ATTR_REGION), 
				xml.getNodeAttrVal(orgEle, ATTR_CONTACTS),
				xml.getNodeAttrVal(orgEle, ATTR_TEL), null, null);
		
		// 子级组织
		List<Element> subOrgEles = xml.getNodes(orgEle, TAG_ORGANIZ);
		for(Element subOrgEle : subOrgEles) {
			Organiz subOrganiz = relolverOrganizEle(subOrgEle);
			subOrganiz.setParent(organiz);
			organiz.getSubOrganizs().add(subOrganiz);
		}
		
		// 组织下的部门
		List<Element> deptEles = xml.getNodes(orgEle, TAG_DEPT);
		for(Element deptEle : deptEles) {
			Department dept = relolverDeptEle(organiz, deptEle);
			organiz.getDepts().add(dept);
		}
		
		return organiz;
	}
	
	/**
	 * 解析组织下部门的结构
	 */
	private Department relolverDeptEle(Organiz organiz, Element deptEle) throws Exception  {
		Department dept = new Department(null, 
				xml.getNodeAttrVal(deptEle, ATTR_NAME), null, Constant.NORMAL, organiz, null);
		
		// 子级部门
		List<Element> subDeptEles = xml.getNodes(deptEle, TAG_DEPT);
		for(Element subDeptEle : subDeptEles) {
			Department subDept = relolverDeptEle(organiz, subDeptEle);
			subDept.setParent(dept);
			dept.getSubDepts().add(subDept);
		}
		
		// 部门下的人员
		List<Element> personEles = xml.getNodes(deptEle, TAG_PERSON);
		for(Element personEle : personEles) {
			Person person = new Person(null, 
					xml.getNodeAttrVal(personEle, ATTR_NUMBER), 
					xml.getNodeAttrVal(personEle, ATTR_TRUENAME), 
					xml.getNodeAttrVal(personEle, ATTR_USERBANE),
					EncryptUtils.getEncrypt("123456"), 
					xml.getNodeAttrVal(personEle, ATTR_TEL), 
					null, null, null, Constant.NORMAL, dept);
			dept.getPersons().add(person);
		}
		
		return dept;
	}
	
	/**
	 * 解析模块结构
	 */
	private Module relolverModule(Element moduleEle) throws Exception {
		Module module = new Module(null, xml.getNodeAttrVal(moduleEle, ATTR_NAME));
		
		// 模块下的菜单
		List<Element> menuEles = xml.getNodes(moduleEle, TAG_MENU);
		for(Element menuEle : menuEles) {
			module.getMenus().add(relolverMenu(module, menuEle));
		}
		
		return module;
	}
	
	/**
	 * 解析模块下菜单的结构
	 */
	private Menu relolverMenu(Module module, Element menuEle) throws Exception {
		Menu menu = new Menu(null, 
				xml.getNodeAttrVal(menuEle, ATTR_NAME), 
				xml.getNodeAttrVal(menuEle, ATTR_ICON), 
				xml.getNodeAttrVal(menuEle, ATTR_URL),
				xml.getNodeAttrVal(menuEle, ATTR_CLASS), 
				module, null);
		
		// 子级菜单
		List<Element> subMenuEles = xml.getNodes(menuEle, TAG_MENU);
		for(Element subMenuEle : subMenuEles) {
			Menu subMenu = relolverMenu(module, subMenuEle);
			subMenu.setParent(menu);
			menu.getSubMenus().add(subMenu);
		}
		
		// 菜单下的资源
		List<Element> resEles = xml.getNodes(menuEle, TAG_RESOURCE);
		for(Element resEle : resEles) {
			Resource resource = new Resource(null, 
				xml.getNodeAttrVal(resEle, ATTR_NAME), 
				Integer.parseInt(xml.getNodeAttrVal(resEle, ATTR_OPER_TYPE)), 
				xml.getNodeAttrVal(resEle, ATTR_CODE), 
				xml.getNodeAttrVal(resEle, ATTR_LINK), 
				xml.getNodeAttrVal(resEle, ATTR_CLASS), 
				xml.getNodeAttrVal(resEle, ATTR_ICON), 
				xml.getNodeAttrVal(resEle, ATTR_INTECEPTOR), 
				menu);
			menu.getResources().add(resource);
		}
		
		return menu;
	}
	
	/**
	 * 解析角色信息
	 */
	private Role relolverRole(Element roleEle) throws Exception {
		Role role = new Role(null, 
				xml.getNodeAttrVal(roleEle, ATTR_NAME), null, Constant.NORMAL);
		
		// 获取拥有该角色的人员
		List<Element> personEles = xml.getNodes(roleEle, TAG_PERSON);
		for(Element personEle : personEles) {
			String userNames = xml.getNodeAttrVal(personEle, ATTR_USERBANE);
			if(userNames!=null && !"".equals(userNames)) {
				rolePerson.put(xml.getNodeAttrVal(roleEle, ATTR_NAME), userNames);
			}
		}
		
		// 获取角色的权限配置
		List<String> permitCodes = new ArrayList<String>(0);
		List<Element> permitEles = xml.getNodes(roleEle, TAG_PERMIT);
		for(Element permitEle : permitEles) {
			String permitCode = xml.getNodeAttrVal(permitEle, ATTR_RES_CODE) + "," +
					xml.getNodeAttrVal(permitEle, ATTR_OPER_CODE);
			permitCodes.add(permitCode);
		}
		rolePermit.put(xml.getNodeAttrVal(roleEle, ATTR_NAME), permitCodes);
		
		return role;
	}
	
	/**
	 * 获取组织模型,包含组织、部门、人员
	 * @return List<Organiz>
	 */
	public List<Organiz> getOrganizModel() {
		
		return organizs;
	}
	
	/**
	 * 获取系统模块信息,包括模块、菜单、资源
	 * @return List<Module>
	 */
	public List<Module> getModuleModel() {
		
		return modules;
	}
	
	/**
	 * 获取定义的操作模型
	 * @return List<Operation>
	 */
	public List<Operation> getOperationModel() {
		
		return operations;
	}
	
	/**
	 * 获取定义的角色信息
	 * @return List<Role>
	 */
	public List<Role> getRoleModel() {
		
		return roles;
	}
	
	/**
	 * 获取角色、用户关系
	 * @return Map<String, String> 返回信息<角色名称, 用户名>
	 * 
	 * 多用户名使用","分割
	 */
	public Map<String, String> getRolePerson() {
		
		return rolePerson;
	}
	
	/**
	 * 获取角色、权限关系
	 * @return Map<String, List<String>> 返回信息<角色名称, 权限>
	 * 
	 * 权限 = 资源code,操作code
	 */
	public Map<String, List<String>> getRolePermit() {
		
		return rolePermit;
	}
	
	public static CoreConfigRelolver getInstance() {
		if(coreRelolver == null) {
			coreRelolver = new CoreConfigRelolver();
		}
		logger.debug("获取基础数据模型解析器("+coreRelolver.toString()+").");
		return coreRelolver;
	}

	
}
