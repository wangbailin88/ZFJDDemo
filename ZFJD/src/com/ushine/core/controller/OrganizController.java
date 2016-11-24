package com.ushine.core.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdcq.common.logging.LogFactory;
import com.tdcq.common.logging.LogInfo;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Department;
import com.ushine.core.po.Organiz;
import com.ushine.core.service.IDeptService;
import com.ushine.core.service.IOrganizService;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 组织控制器
 * @author franklin
 *
 */
@Controller
public class OrganizController {
	private static final Logger logger = LoggerFactory.getLogger(OrganizController.class);
	
	@Autowired
	private IOrganizService orgService;
	@Autowired
	private IDeptService deptService;
	/**
	 * 获取所有组织
	 * @param rt 返回类型：c=ComboBox t=Tree
	 * @param request HttpServletRequest
	 * @return 组织树(JSON)
	 */
	@RequestMapping(value="/getOrgs", method=RequestMethod.GET)
	@ResponseBody
	public String getOrganizsTree(@RequestParam("scope") String scope, 
			@RequestParam("page") String page, 
			@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			List<Organiz> orgs = new ArrayList<Organiz>();
			JSONArray array=new JSONArray();
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("1x0001")) {
					if(scope.equals("all")){
						JSONObject ob=new JSONObject();
						ob.put("text", "无");
						ob.put("value", "0");
						array.add(ob);
					}
					orgs = orgService.findAll();
				} else if(codes.get(0).equals("1x0010")) {
					String orgId = sessionMgr.getUOID(request);
					Organiz o=orgService.findOrganizById(orgId);
					orgs=o.getSubOrganizs();
					orgs.add(o);
				}else if(codes.get(0).equals("1x0011")||codes.get(0).equals("1x0100")){
					String orgId = sessionMgr.getUOID(request);
					orgs.add(orgService.findOrganizById(orgId));
				} else {
					return vo.toJSon();
				}
			} else {
				return vo.toJSon();
			}
			for(Organiz o:orgs){
				if(!scope.equals("person")){
					JSONObject obj=new JSONObject();
					obj.put("text", o.getName());
					obj.put("value", o.getId());
					array.add(obj);
				}else{
					if(o.getDepts().size()>0){
						JSONObject obj=new JSONObject();
						obj.put("text", o.getName());
						obj.put("value", o.getId());
						array.add(obj);
					}
				}
			}
			return array.toString(); 
		} catch (Exception e) {
			String msg = "请求获取组织信息操作失败.";
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	/**
	 * 添加组织
	 * @param name 组织名称
	 * @param org  上级组织
	 * @param province 省
	 * @param city  市
	 * @param contacts 联系人
	 * @param tel 联系电话
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addOrg", method=RequestMethod.POST)
	@ResponseBody
	public String addOrg(@RequestParam("name") String name ,
			@RequestParam("org") String org, 
			@RequestParam("city") String city, 
			@RequestParam("contacts") String contacts, 
			@RequestParam("tel") String tel, 
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_ADD);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			if(org.equals("0")){
				loginfo.setParams("组织名称:["+name+"]上级组织:[无]所属城市:["+city+"]联系人:["+contacts+"]联系电话["+tel+"]");
			}else{
				loginfo.setParams("组织名称:["+name+"]上级组织:["+orgService.findOrganizById(org).getName()+"]所属城市:["+city+"]联系人:["+contacts+"]联系电话["+tel+"]");	
			}
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					Organiz o=new Organiz();
					o.setName(name);
					if(!org.equals("0")){
						Organiz organiz=new Organiz();
						organiz.setId(org);	
						o.setParent(organiz);
					}
					o.setContacts(contacts);
					o.setTel(tel);
					o.setRegion(city);
					loginfo.setResult("添加组织成功");
					return orgService.createNewOrganiz(o);
				} 
			}
			loginfo.setResult("添加组织失败：没有权限");
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取组织信息操作失败.";
			loginfo.setResult("添加组织失败："+e.getMessage());
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	/**
	 * 查询树形菜单
	 * @param node 上级id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOrgsTree", method=RequestMethod.GET)
	@ResponseBody
	public String findByTree(@RequestParam("node") String node, 
			HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			List<Organiz> orgs = new ArrayList<Organiz>();
			JSONArray array=new JSONArray();
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("1x0001")) {
					// 全部数据
					if(node.equals("root")){
						orgs = orgService.findOrganizsTree();
						for (Organiz org:orgs) {
							JSONObject obj=new JSONObject();
							obj.put("text", org.getName());
							obj.put("id", org.getId());
							obj.put("type", "0");  //0：表示组织1：部门
							obj.put("leaf", false);
							obj.put("expanded", true);
							array.add(obj);
						}
					}else{
						orgs=orgService.findSubTree(node);
						if(orgs.size()>0){
							for (Organiz org:orgs) {
								JSONObject obj=new JSONObject();
								obj.put("text", org.getName());
								obj.put("id", org.getId());
								obj.put("type", "0");  //0：表示组织1：部门
								obj.put("leaf", false);
								array.add(obj);
							}
							List<Department> departments=deptService.findDeptsByOid(node);
							for (Department dep:departments) {
								JSONObject obj=new JSONObject();
								obj.put("id", dep.getId());
								obj.put("type", "1");  //0：表示组织1：部门
								obj.put("text", dep.getName());
								obj.put("leaf", false);
								array.add(obj);
							}
						}else{
							String json=deptService.findTreeByOid(node);
							if(json.equals("[]")){
								json=deptService.findTreeByPid(node);
							}
							return json;
						}
					}
					return array.toString();
				} else if(!codes.get(0).equals("1x0000")) {
					// 所属地区
					if(node.equals("root")){
						String orgId = sessionMgr.getUOID(request);
						orgs.add(orgService.findOrganizById(orgId));
						for (Organiz org:orgs) {
							JSONObject obj=new JSONObject();
							obj.put("text", org.getName());
							obj.put("id", org.getId());
							obj.put("type", "0");  //0：表示组织1：部门
							obj.put("leaf", false);
							array.add(obj);
						}
					}else{
						orgs=orgService.findSubTree(node);
						if(orgs.size()>0){
							for (Organiz org:orgs) {
								JSONObject obj=new JSONObject();
								obj.put("text", org.getName());
								obj.put("id", org.getId());
								obj.put("type", "0");  //0：表示组织1：部门
								obj.put("leaf", false);
								array.add(obj);
							}
							List<Department> departments=deptService.findDeptsByOid(node);
							for (Department dep:departments) {
								JSONObject obj=new JSONObject();
								obj.put("id", dep.getId());
								obj.put("text", dep.getName());
								obj.put("type", "1");  //0：表示组织1：部门
								obj.put("leaf", true);
								if(dep.getSubDepts().size()>0){
									obj.put("leaf", false);
								}
								array.add(obj);
							}
						}else{
							String json=deptService.findTreeByOid(node);
							if(json.equals("[]")){
								json=deptService.findTreeByPid(node);
							}
							return json;
						}
					}
					return array.toString();
				}
			} 
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取组织信息操作失败.";
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	
	public IOrganizService getOrgService() {
		return orgService;
	}
	
	public void setOrgService(IOrganizService orgService) {
		this.orgService = orgService;
	}
	/**
	 * 删除组织和部门
	 * 
	 *2015-12-28
	 */
	@RequestMapping(value="/deleteOrgAndDempt",method=RequestMethod.POST)
	@ResponseBody
	public String deleteOrgAndDempt(HttpServletRequest request,
			@RequestParam("id") String id,
			@RequestParam("type") String type
			){
		logger.info("删除组织和部门");
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_DELETE);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			List<String> list = sessionMgr.getPermitResOperCode(request);
			// 判断当前登录用户是否有权限修改企业信息
			if (list != null && list.size() > 0) {
				if ("0x0001".equals(list.get(0))) {
					//判断删除的是部门还是组织   0：组织  1：部门
					if("0".equals(type)){
						//查询当前要删除的组织信息，
						Organiz organiz = orgService.findOrganizById(id);
						//判断当前要删除的组织下是否有部门和下级组织如果有提示用户不能删除类似信息
						if(organiz.getDepts().size()>0){
							loginfo.setResult("该组织下有部门,不能删除");
							return new ViewObject(ViewObject.RET_FAILURE,"该组织下有部门,不能删除").toJSon();
						}else if(organiz.getSubOrganizs().size()>0){
							loginfo.setResult("该组织下有下级组织,不能删除");
							return new ViewObject(ViewObject.RET_FAILURE,"该组织下有下级组织,不能删除").toJSon();
						}else{
							orgService.deleteOrganizById(id);
							loginfo.setResult("删除组织成功");
							return new ViewObject(ViewObject.RET_SUCCEED,"删除组织《"+organiz.getName()+"》成功").toJSon();
						}
			 		}else if("1".equals(type)){
						Department department = deptService.findDeptById(id);
						//判断当前要删除的部门是否有下级人员,如果有下级提示用户不能删除类型信息
						if(department.getPersons().size()>0){
							loginfo.setResult("该部门下有下级人员,不能删除");
							return new ViewObject(ViewObject.RET_FAILURE,"该部门下有下级人员,不能删除").toJSon();
						}else{
							deptService.deleteDeptById(id);
							loginfo.setResult("删除部门成功");
							return new ViewObject(ViewObject.RET_SUCCEED,"删除部门《"+department.getName()+"》成功").toJSon();
						}
					}
				}
				
			}
			return new ViewObject(ViewObject.RET_FAILURE,"删除失败,没有权限").toJSon();
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "删除组织和部门操作失败";
			logger.error(msg, e);
			loginfo.setResult("删除组织和部门操作失败：" + e.getMessage());
			ViewObject object = new ViewObject(ViewObject.RET_FAILURE, "删除失败");
			return object.toJSon();
		}finally{
			log.log(loginfo);
		}
	}
	@RequestMapping(value="/findOrgByOrId",method=RequestMethod.POST)
	@ResponseBody
	public String findOrgByOrId(HttpServletRequest request,
			@RequestParam("id") String id){
		try {
			Organiz organiz = orgService.findOrganizById(id);
			JSONObject object = new JSONObject();
			object.put("orgName", organiz.getName());
			if(organiz.getParent() == null){
				object.put("topOrg", "0");
			}else{
				object.put("topOrg", organiz.getParent().getId());
			}
			
			object.put("region", organiz.getRegion());
			object.put("contacts", organiz.getContacts());
			object.put("tel", organiz.getTel());
			return object.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改组织
	 * @param name 组织名称
	 * @param org  上级组织
	 * @param province 省
	 * @param city  市
	 * @param contacts 联系人
	 * @param tel 联系电话
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateOrg", method=RequestMethod.POST)
	@ResponseBody
	public String updateOrg(@RequestParam("name") String name ,
			@RequestParam("org") String org, 
			@RequestParam("city") String city, 
			@RequestParam("contacts") String contacts, 
			@RequestParam("tel") String tel,
			@RequestParam("id") String id,
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_ADD);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			if(!org.equals("0")){
				loginfo.setParams("组织名称:["+name+"]上级组织:["+orgService.findOrganizById(org).getName()+"]所属城市:["+city+"]联系人:["+contacts+"]联系电话["+tel+"]");	
			}else{
				loginfo.setParams("组织名称:["+name+"]上级组织:[无]所属城市:["+city+"]联系人:["+contacts+"]联系电话["+tel+"]");
			}
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					Organiz o= new Organiz();
					o.setId(id);
					o.setName(name);
					if(!org.equals("0")){
						Organiz organiz=new Organiz();
						organiz.setId(org);	
						o.setParent(organiz);
					}
					o.setContacts(contacts);
					o.setTel(tel);
					o.setRegion(city);
					loginfo.setResult("修改组织成功");
					return orgService.updateOrganiz(o);
				} 
			}
			loginfo.setResult("修改组织失败：没有权限");
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取组织信息操作失败.";
			loginfo.setResult("修改组织失败："+e.getMessage());
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
}
