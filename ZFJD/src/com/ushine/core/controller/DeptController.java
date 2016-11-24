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
 * 部门控制器
 * @author franklin
 *
 */
@Controller
public class DeptController {
	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IOrganizService organizService;
	/**
	 * 获取部门信息
	 * @param rt 返回类型：c=ComboBox t=Tree
	 * @param request HttpServletRequest
	 * @return 部门树(JSON)
	 */
	@RequestMapping(value="/getDepts", method=RequestMethod.GET)
	@ResponseBody
	public String getDeptsTree(@RequestParam("scope") String scope,
			@RequestParam("orgId") String orgId, 
			@RequestParam("page") String page, 
			@RequestParam("start") String start,
			@RequestParam("limit") String limit,
			HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			
			List<Department> depts = new ArrayList<Department>(0);
			
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("1x0001")||codes.get(0).equals("1x0010")) {
					// 全部数据
					depts = deptService.findDeptsTreeByOrganiz(orgId);
					
				} else if(codes.get(0).equals("1x0011")||codes.get(0).equals("1x0100")) {
					// 所属地区
					String did = sessionMgr.getUDID(request);
					Department d=deptService.findDeptById(did);
					depts=d.getSubDepts();
					depts.add(d);
				} else {
					return vo.toJSon();
				}
			} else {
				return vo.toJSon();
			}
			JSONArray array=new JSONArray();
			if(scope.equals("all")){
				JSONObject ob=new JSONObject();
				ob.put("text", "无");
				ob.put("value", "0");
				array.add(ob);
			}
			for(Department d:depts){
				JSONObject obj=new JSONObject();
				obj.put("text",d.getName());
				obj.put("value", d.getId());
				array.add(obj);
			}
			return array.toString(); 
		} catch (Exception e) {
			String msg = "请求获取部门信息操作失败.";
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	/**
	 * 添加部门
	 * @param name 部门名称
	 * @param org  组织id
	 * @param dept 上级部门
	 * @param status 部门状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addDept", method=RequestMethod.POST)
	@ResponseBody
	public String addDept(@RequestParam("name") String name,
			@RequestParam("org") String org,
			@RequestParam("dept") String dept,
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setResult("添加部门成功");
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_QUERY);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			if(dept.equals("0")){
				loginfo.setParams("部门名称:["+name+"]组织名称:["+organizService.findOrganizById(org).getName()+"]上级部门:[无]");
			}else{
				loginfo.setParams("部门名称:["+name+"]组织名称:["+organizService.findOrganizById(org).getName()+"]上级部门:["+deptService.findDeptById(dept).getName()+"]");	
			}
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					// 全部数据
					Department department=new Department();
					department.setName(name);
					Department d=new Department();
					if(!dept.equals("0")){
						d.setId(dept);
						department.setParent(d);
					}
					Organiz o=new Organiz();
					o.setId(org);
					department.setOrganiz(o);
				//	department.setStatus(Integer.parseInt(status));
					return deptService.createNewDept(department);
				} 
			} 
			loginfo.setResult("添加部门失败：没有权限");
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "添加部门失败";
			logger.error(msg , e);
			loginfo.setResult("添加部门失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}

	
	/**
	 * 获取部门信息
	 * @param rt 返回类型：c=ComboBox t=Tree
	 * @param request HttpServletRequest
	 * @return 部门树(JSON)
	 */
	@RequestMapping(value="/getDeptse", method=RequestMethod.GET)
	@ResponseBody
	public String getDeptsTree(HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			
			String orgId = sessionMgr.getUOID(request);
			
			List<Department> depts = new ArrayList<Department>(0);
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("1x0001")||codes.get(0).equals("1x0010")) {
					// 全部数据
					depts = deptService.findDeptsTreeByOrganiz(orgId);
					
				} else if(codes.get(0).equals("1x0011")||codes.get(0).equals("1x0100")) {
					// 所属地区
					String did = sessionMgr.getUDID(request);
					Department d=deptService.findDeptById(did);
					depts=d.getSubDepts();
					depts.add(d);
				} else {
					return vo.toJSon();
				}
			} else {
				return vo.toJSon();
			}
			JSONArray array=new JSONArray();
			for(Department d:depts){
				JSONObject obj=new JSONObject();
				obj.put("text",d.getName());
				obj.put("value", d.getId());
				array.add(obj);
			}
			return array.toString(); 
		} catch (Exception e) {
			String msg = "请求获取部门信息操作失败.";
			logger.error(msg , e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	
	@RequestMapping(value="/getRootDept", method=RequestMethod.GET)
	@ResponseBody
	public String getRootDept(){
		try {
			return deptService.toExtComboJSON(deptService.findDeptsTree());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	
	public IDeptService getDeptService() {
		return deptService;
	}
	
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	/**
	 * 修改部门
	 * @param name 部门名称
	 * @param org  组织id
	 * @param dept 上级部门
	 * @param status 部门状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateDept", method=RequestMethod.POST)
	@ResponseBody
	public String updateDept(@RequestParam("name") String name,
			@RequestParam("org") String org,
			@RequestParam("dept") String dept,
			@RequestParam("id") String id,
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setResult("修改部门成功");
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_QUERY);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			if(dept.equals("0")){
				loginfo.setParams("部门名称:["+name+"]组织名称:["+organizService.findOrganizById(org).getName()+"]上级部门:[无]");
			}else{
				loginfo.setParams("部门名称:["+name+"]组织名称:["+organizService.findOrganizById(org).getName()+"]上级部门:["+deptService.findDeptById(dept).getName()+"]");	
			}
			ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					// 全部数据
					Department department=new Department();
					department.setId(id);
					department.setName(name);
					Department d=new Department();
					if(!dept.equals("0")){
						d.setId(dept);
						department.setParent(d);
					}
					Organiz o=new Organiz();
					o.setId(org);
					department.setOrganiz(o);
					return deptService.updateDept(department);
				} 
			} 
			loginfo.setResult("修改部门失败：没有权限");
			return vo.toJSon();
		} catch (Exception e) {
			String msg = "修改部门失败";
			logger.error(msg , e);
			loginfo.setResult("修改部门失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	@RequestMapping(value="/findDeptByOrId",method=RequestMethod.POST)
	@ResponseBody
	public String findDeptByOrId(HttpServletRequest request,
			@RequestParam("id") String id){
		try {
			Department department = deptService.findDeptById(id);
			JSONObject object = new JSONObject();
			object.put("deptName", department.getName());
			object.put("org", department.getOrganiz().getId());
			if(department.getParent()==null){
				object.put("topDept", "0");
			}else{
				object.put("topDept", department.getParent().getId());
			}
			return object.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
