package com.ushine.core.controller;

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
import com.ushine.common.config.Configured;
import com.ushine.common.utils.DateUtils;
import com.ushine.common.utils.RequestUtil;
import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewDataObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.cache.ResCache;
import com.ushine.core.po.Department;
import com.ushine.core.po.Organiz;
import com.ushine.core.po.Person;
import com.ushine.core.po.Resource;
import com.ushine.core.po.Role;
import com.ushine.core.service.IDeptService;
import com.ushine.core.service.IOrganizService;
import com.ushine.core.service.IPermitService;
import com.ushine.core.service.IPersonService;
import com.ushine.core.service.IRoleService;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 人员控制器
 * @author franklin
 *
 */
@Controller
public class PersonController {
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private IPersonService personService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermitService permitService;
	@Autowired
	private IDeptService deptService;
	@Autowired
	private IOrganizService organizService;
	/**
	 * 获取当前用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPerson")
	@ResponseBody
	public String getPerson(HttpServletRequest request){
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		try {
			Person	p=personService.findPersonById(sessionMgr.getUID(request));
			JSONObject obj=new JSONObject();
			obj.put("id", p.getId());
			obj.put("date",DateUtils.toString(DateUtils.parseDate(p.getLoginLastDate(), "yyyy-MM-dd"), "yyyy年MM月dd日"));
			obj.put("ip", RequestUtil.getRemoteAddress(request));
			obj.put("name", p.getTrueName());
			obj.put("orgAdd", p.getDept().getOrganiz().getRegion());
			obj.put("version", Configured.getInstance().get("version"));
//			ResCache resCache = ResCache.getInstance(); // 判读资源是否有权限
//			Resource res = resCache.getResource("/search.do");
//			List<String> codes =  permitService.getRolePermitCode(
//					sessionMgr.getRoleIds(request), res.getId());
//			obj.put("code", codes.get(0));
			return obj.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String msg = "请求获取人员信息操作失败.";
			logger.error(msg,e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	/**
	 * 获取部门人员分页列表
	 * @param did 部门id
	 * @param sz 获取数据量
	 * @param p 当前页
	 * @param request HttpServletRequest
	 * @return 人员分页列表(JSON)
	 */
	@RequestMapping(value="/getPersons", method=RequestMethod.GET)
	@ResponseBody
	public String getPersonsById(@RequestParam("dempId") String deptId,
			@RequestParam("start") String start,
			@RequestParam("limit") int size, @RequestParam("page") int page, 
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_QUERY);
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			loginfo.setParams("部门名称:["+deptService.findDeptById(deptId).getName()+"]");
			PagingObject<Person> pagVo = personService.findPagingPersonByDept(deptId, size, page);
			loginfo.setResult("查询人员成功");
			return personService.voToJSon(pagVo);
		} catch (Exception e) {
			String msg = "请求获取人员信息操作失败.";
			logger.error(msg,e);
			loginfo.setResult("查询人员失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			if(page==1){
				log.log(loginfo);	
			}
		}
	}
	/**
	 * 查询用户角色
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPersonsRoles", method=RequestMethod.GET)
	@ResponseBody
	public String getPersonsRoles(@RequestParam("id") String id,
			HttpServletRequest request) {
		try {
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					JSONObject obj=new JSONObject();
					JSONArray in=new JSONArray();
					JSONArray out=new JSONArray();
					Person p=personService.findPersonById(id);
					obj.put("id",id);
					obj.put("name",p.getUserName());
					obj.put("number",p.getNumber());
					obj.put("relName",p.getTrueName());
					obj.put("tel",p.getTel());
					List<Role> roles=p.getRoles();
					List<Role> roles1=roleService.findRoleAll();
					for(Role role:roles1){
						JSONArray array1=new JSONArray();
						array1.add(role.getId());
						array1.add(role.getName());
						out.add(array1);
					}
					for(Role role:roles){
						JSONArray array1=new JSONArray();
						array1.add(role.getId());
						//out.remove(obj1);
						in.add(array1);
					}
					obj.element("indata", in);
					obj.element("data", out);
					ViewDataObject vo=new ViewDataObject(1, "查询用户角色成功");
					vo.setObject(obj);
					//System.out.println(vo.toJSon());
					return vo.toJSon();
				} 
			} 
			return errVo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取人员信息操作失败.";
			logger.error(msg,e);
			return new ViewObject(-1, msg).toJSon();
		}
	}
	/**
	 * 设置角色
	 * @param id 用户id
	 * @param ids 角色数组
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setRole", method=RequestMethod.POST)
	@ResponseBody
	public String setRole(@RequestParam("id") String id,
			@RequestParam("itemselector") String itemselector,
			HttpServletRequest request) {
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_UPDATE);
		try {
			String[] ids=itemselector.split(",");
			UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
			//loginfo.setUserCode("0x0001");
			loginfo.setUserCode(sessionMgr.getCode(request));
			loginfo.setUserName(sessionMgr.getTrueName(request));
/*			Person p=personService.findPersonById(id);
			List<Role> rs=new ArrayList<Role>();
			String[] rids=itemselector.split(",");
			for(String rid:rids){
				Role r=new Role();
				r.setId(rid);
				rs.add(r);
			}
			p.setRoles(rs);
			personService.modifyPersonInfo(p);
			ViewObject vo = new ViewObject(ViewObject.RET_SUCCEED, "设置角色成功");*/
			//设置日志参数
			StringBuffer sb=new StringBuffer();
			sb.append("人员名称:["+personService.findPersonById(id).getTrueName()+"]");
			sb.append("角色名称:[");
			for(String rid:ids){
				sb.append(roleService.findRoleById(rid).getName());
				sb.append(",");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append("]");
			loginfo.setParams(sb.toString());
			loginfo.setResult("设置角色成功");
			return roleService.setPersonRole(id,ids);
		} catch (Exception e) {
			String msg = "请求获取人员信息操作失败.";
			logger.error(msg,e);
			loginfo.setResult("设置角色失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	/**
	 * 添加人员
	 * @param dept  上级部门
	 * @param number 人员编号
	 * @param org 上级组织
	 * @param status 人员状态
	 * @param tel 人员电话
	 * @param trueName 真实姓名
	 * @param userName 用户名
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addPerson", method=RequestMethod.POST)
	@ResponseBody
	public String addPersons(
			@RequestParam("dept") String dept,
			@RequestParam("number") String number,
			@RequestParam("org") String org,
			@RequestParam("status") int status,
			@RequestParam("IDCard") String IDCard,
			@RequestParam("tel") String tel,
			@RequestParam("ip") String ip,
			@RequestParam("trueName") String trueName,
			@RequestParam("userName") String userName,
			HttpServletRequest request) {
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_ADD);
		try {
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			loginfo.setParams("用户名:["+userName+"]用户编号:["+number+"]真实姓名:["+trueName+"]联系电话:["+tel+"]登录IP:["+ip+"]身份证号码:["+IDCard+"]所属组织:["+organizService.findOrganizById(org).getName()+"]所属部门:["+deptService.findDeptById(dept).getName()+"]是否启用IP验证:["+(status==1?"是":"否")+"]");
			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					Person p=new Person();
					p.setNumber(number);
					p.setStatus(status);
					p.setTel(tel);
					p.setTrueName(trueName);
					p.setUserName(userName);
					p.setIDCard(IDCard);
					Department d=new Department();
					d.setId(dept);
					p.setDept(d);
					p.setIp(ip);
					p.setPassword("e10adc3949ba59abbe56e057f20f883e");
					Organiz o=new Organiz();
					o.setId(org);
					loginfo.setResult("添加用户成功");
					return personService.createNewPersion(p);
				} 
			} 
			loginfo.setResult("添加用户失败：没有权限");
			return errVo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取人员信息操作失败.";
			logger.error(msg,e);
			loginfo.setResult("添加用户失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	/**
	 * 添加人员
	 * @param dept  上级部门
	 * @param number 人员编号
	 * @param org 上级组织
	 * @param status 人员状态
	 * @param tel 人员电话
	 * @param trueName 真实姓名
	 * @param userName 用户名
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updatePerson", method=RequestMethod.POST)
	@ResponseBody
	public String updatePersons(
			@RequestParam("dept") String dept,
			@RequestParam("number") String number,
			@RequestParam("org") String org,
			@RequestParam("status") String status,
			@RequestParam("IDCard") String IDCard,
			@RequestParam("tel") String tel,
			@RequestParam("ip") String ip,
			@RequestParam("trueName") String trueName,
			@RequestParam("userName") String userName,
			@RequestParam("personId") String personId,
			HttpServletRequest request) {
		UserSessionMgr sessionMgr = UserSessionMgr.getInstance();
		com.tdcq.common.logging.Logger log = LogFactory.getLogger();
		LogInfo loginfo = new LogInfo();
		loginfo.setApplication("test");
		loginfo.setUri(request.getRequestURI());
		loginfo.setClientIP(request.getRemoteAddr());
		loginfo.setLogTime(new Date());
		loginfo.setOperationType(com.tdcq.common.logging.Logger.LOG_OPERATION_TYPE_UPDATE);
		try {
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			loginfo.setUserName(sessionMgr.getTrueName(request));
			loginfo.setUserCode(sessionMgr.getCode(request));
			loginfo.setParams("用户名:["+userName+"]用户编号:["+number+"]真实姓名:["+trueName+"]联系电话:["+tel+"]登录IP:["+ip+"]身份证号码:["+IDCard+"]所属组织:["+organizService.findOrganizById(org).getName()+"]所属部门:["+deptService.findDeptById(dept).getName()+"]是否启用IP验证:["+(status.equals("1")?"是":"否")+"]");
			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					//System.out.println("进来了");
					Person p=new Person();
					p.setId(personId);
					p.setNumber(number);
					p.setStatus(Integer.parseInt(status));
					p.setTel(tel);
					p.setTrueName(trueName);
					p.setUserName(userName);
					Department d=new Department();
					d.setId(dept);
					p.setDept(d);
					p.setIp(ip);
					p.setIDCard(IDCard);
					loginfo.setResult("修改用户成功");
					return personService.updatePersonInfo(p);
				} 
			} 
			loginfo.setResult("修改用户失败：没有权限");
			return errVo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取人员信息操作失败.";
			logger.error(msg,e);
			loginfo.setResult("修改用户失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	/**
	 * 删除人员 
	 * @param id 人员id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delPerson", method=RequestMethod.GET)
	@ResponseBody
	public String delPersons(@RequestParam("id") String id,
			HttpServletRequest request) {
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
			loginfo.setParams("人员名称:["+personService.findPersonById(id).getTrueName()+"]");
			List<String> codes = sessionMgr.getPermitResOperCode(request);
			ViewObject errVo = new ViewObject(ViewObject.RET_FAILURE, "您没有对该资源操作的权限.");
			if(codes!=null && codes.size()>0) {
				//loginfo.setUserCode(codes.get(0));
				logger.debug("该权限操作编码:" + codes + ".");
				if(codes.get(0).equals("0x0001")) {
					loginfo.setResult("删除人员成功");
					return personService.deletePersonById(id);
				} 
			} 
			loginfo.setResult("删除人员失败：没有权限");
			return errVo.toJSon();
		} catch (Exception e) {
			String msg = "请求获取人员信息操作失败.";
			logger.error(msg,e);
			loginfo.setResult("删除人员失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}
	/**
	 * 验证用户名是否存在
	 * @param name 用户名
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/VUserName", method=RequestMethod.POST)
	@ResponseBody
	public String VUserName(@RequestParam("name") String name,
			HttpServletRequest request) {
		ViewObject vo = new ViewObject(ViewObject.RET_FAILURE, "该用户名不存在");
		try{
			List<Person> list=personService.findPersonByName(name);
			if(list.size()>0){
				return new ViewDataObject(ViewObject.RET_SUCCEED, "该用户名已被使用").toJSon();
			}
		}catch(Exception e){
			String msg = "根据用户名查询人员失败";
			logger.error(msg,e);
			return new ViewObject(-1, msg).toJSon();
		}
		return vo.toJSon();
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	public IPersonService getPersonService() {
		return personService;
	}
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}
	public IRoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
}
