package com.ushine.core.init;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.opensymphony.xwork2.ActionContext;
import com.ushine.common.config.Configured;
import com.ushine.common.init.ISystemInitService;
import com.ushine.core.cache.CityCache;
import com.ushine.core.cache.OperCache;
import com.ushine.core.cache.RequestPathCache;
import com.ushine.core.cache.ResCache;
import com.ushine.core.cache.SubordinateNodeInfoCache;
import com.ushine.core.cache.ThisNodeInfoCache;
import com.ushine.core.po.Operation;
import com.ushine.core.po.Resource;
import com.ushine.core.service.ICityService;
import com.ushine.core.service.IOperationService;
import com.ushine.core.service.IResourceService;
import com.ushine.ssh.service.ISubordinateNodeInfoService;
import com.ushine.ssh.service.IThisNodeInfoService;
import com.ushine.util.PathUtils;

public class InitCoreServiceImpl extends HttpServlet implements ISystemInitService {
	private static final Logger logger = LoggerFactory.getLogger(InitCoreServiceImpl.class);
	@Autowired
	private IOperationService operService;
	@Autowired
	private IResourceService resService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IThisNodeInfoService thisNodeInfoService;
	@Autowired
	private ISubordinateNodeInfoService subordinateNodeInfoService;
	
	public String name() {
		return "基础模块初始化服务实现";
	}

	public ISystemInitService load() throws Exception {
		return this;
	}

	public void init() {
		try {
			//****************************************
			//* 添加操作到缓存
			//****************************************
			/*OperCache operMgr = OperCache.getInstance();
			List<Operation> opers = operService.findOperations();
			for(Operation oper : opers) {
				operMgr.pup(oper);
			}
			logger.info("{缓存加载}成功加载系统操作信息.");*/
			
			//****************************************
			//* 添加资源到缓存
			//****************************************
			/*ResCache resCache = ResCache.getInstance();
			List<Resource> ress = resService.findResources();
			for(Resource res : ress) {
				resCache.pup(res);
			}
			logger.info("{缓存加载}成功加载系统资源信息.");*/
			
			//****************************************
			//* 添加城市到缓存
			//****************************************
			CityCache c=CityCache.getInstance();
			c.setCitys(cityService.findCity());
			logger.info("{缓存加载}成功加载城市树形菜单.");
			//索引地址
	    	/*String path =Configured.getInstance().get("luceneIndexPath");
	    	File file = new File(path);
			if(!file.exists()){
				file.mkdirs();
				System.out.println("成功创建索引地址");
			}*/
			//取得当前节点注册信息,
			//使用单例模式保存在内存中
			ThisNodeInfoCache.getInstance().setThisNodeInfo(thisNodeInfoService.findThisNodeInfo());
			//取得下级节点注册信息
			SubordinateNodeInfoCache.getInstance().setSubordinateNodeInfos(subordinateNodeInfoService.findSubordinateNodeInfoByStatus());
			RequestPathCache.getInstance().setPath(PathUtils.getWebappsPath(new PathUtils().getClass()));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("基础模块初始化服务失败，强制关闭应用程序.", e);
			// System.exit(-1);
		}
	}

	public void destroy() {
		
	}

}
