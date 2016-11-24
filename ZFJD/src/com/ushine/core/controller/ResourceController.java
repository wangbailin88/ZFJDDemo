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
import com.ushine.core.cache.OperCache;
import com.ushine.core.po.Operation;
import com.ushine.core.po.Resource;
import com.ushine.core.service.IMenuService;
import com.ushine.core.service.IPermitService;
import com.ushine.core.service.IResourceService;
import com.ushine.core.service.IRoleService;
import com.ushine.core.verify.session.UserSessionMgr;

/**
 * 资源控制器，获取指定菜单下的资源信息
 * 
 * @author franklin
 *d
 */
@Controller
public class ResourceController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private IResourceService resService;
	@Autowired
	private IPermitService permitService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IMenuService menuService;
	/**
	 * 获取指定菜单下的资源
	 * @param mId 菜单id
	 * @param rId 角色id
	 * @param request HttpServletRequest
	 * @return 资源列表，包括该资源对应的操作(JSON) 
	 */
	@RequestMapping(value="/getres", method=RequestMethod.GET)
	@ResponseBody
	public String getResourceByMenu(@RequestParam("mId") String menuId,
			@RequestParam("rId") String roleId,
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
			loginfo.setParams("角色:["+roleService.findRoleById(roleId).getName()+"]菜单:["+menuService.findMenuById(menuId).getName()+"]");
			JSONArray array = new JSONArray();
			OperCache operCache = OperCache.getInstance();
			List<Resource> resources = resService.findResourcesByMenu(menuId);
			for(Resource res : resources) {
				// 转换成JSon输出到页面
				JSONObject object = new JSONObject();
				object.put("inputValue", res.getId());
				object.put("labelWidth", "100");
				object.put("fieldLabel", res.getName());
				object.put("xtype", "radiogroup");
				object.put("columns", 3);
			//	object.put("code", res.getCode());
			//	object.put("link", res.getLink());
			//	object.put("className", res.getClassName());
			//	object.put("icon", res.getIcon());
			//	object.put("inteceptor", res.getInteceptor());
				//获取菜单下的所有操作
				List<Operation>os=operCache.getOpersByType(res.getType());
				// 获取角色对应的资源操作
				List<String> roleIds = new ArrayList<String>();
				roleIds.add(roleId);
				roleIds=permitService.getRolePermitCode(roleIds, res.getId());
				JSONArray opers=new JSONArray();
				for(int i=0;i<os.size();i++){
					Operation o=os.get(i);
					JSONObject ob = new JSONObject();
					ob.put("name", res.getId());
					ob.put("boxLabel", o.getName());
					ob.put("inputValue", o.getId());
					if(roleIds.size()>0){
						if(roleIds.get(0).equals(o.getCode())){
							ob.put("checked", true);
						}
					}
					opers.add(ob);
				}
				object.element("items", opers); // 获取资源对应的操作
				array.add(object);
			}
			loginfo.setResult("查询权限成功");
			return array.toString();
		} catch(Exception e) {
			String msg = "请求获取资源信息操作失败.";
			logger.error(msg , e);
			loginfo.setResult("查询权限失败："+e.getMessage());
			return new ViewObject(-1, msg).toJSon();
		} finally{
			log.log(loginfo);
		}
	}

	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	
	public IResourceService getResService() {
		return resService;
	}
	
	public void setResService(IResourceService resService) {
		this.resService = resService;
	}
	
	public IPermitService getPermitService() {
		return permitService;
	}
	
	public void setPermitService(IPermitService permitService) {
		this.permitService = permitService;
	}
	
}
