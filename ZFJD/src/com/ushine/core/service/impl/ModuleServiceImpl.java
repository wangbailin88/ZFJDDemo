package com.ushine.core.service.impl;

import java.util.ArrayList;
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
import com.ushine.core.po.Menu;
import com.ushine.core.po.Module;
import com.ushine.core.service.IModuleService;
import com.ushine.dao.IBaseDao;

/**
 * 模块服务接口实现
 * @author franklin
 *
 */
@Transactional
@Service("moduleServiceImpl")
public class ModuleServiceImpl implements IModuleService {
	private static final Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);
	@Autowired
	private IBaseDao<Module, String> moduleDao;

	public String createNewModule(Module module) throws Exception {
		moduleDao.save(module);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加模块信息.").toJSon();
	}

	public String deleteModuleById(String[] ids) throws Exception {
		moduleDao.deleteById(Module.class, ids);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除模块信息.").toJSon();
	}

	@Transactional(readOnly=true)
	public Module findModuleById(String id) throws Exception {
		
		return moduleDao.findById(Module.class, id);
	}

	@Transactional(readOnly=true)
	public List<Module> findModulesByName(String name) throws Exception {
		
		return moduleDao.findByProperty(Module.class, "name", name);
	}

	@Transactional(readOnly=true)
	public List<Module> findModulesAll() throws Exception {
		
		return moduleDao.findAll(Module.class);
	}
	
	@Transactional(readOnly=true)
	public String findAll() throws Exception {
		// TODO Auto-generated method stub
		logger.debug("查询所有模型");
	//	JSONObject obj=new JSONObject();
		JSONArray array=new JSONArray();
		List<Module> list=new ArrayList<Module>();
		list=moduleDao.findAll(Module.class);
		for(int i=0;i<list.size();i++){
			Module m=list.get(i);
			JSONObject json=new JSONObject();
			json.put("id",m.getId());
			json.put("name", m.getName());
//			json.put("iconCls", m.getIcon());
//			json.put("module", m.getModule());
			array.add(json);
			//array.add(i,"{id:+"+m.getId()+"+},");
		}
		//obj.element("data", array);
		return array.toString();
	}
	@Transactional(readOnly=true)
	public String findTree(String node) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("查询模块树形菜单");
		if(node.equals("")||node==null){
			throw new Exception("node为空");
		}
		JSONArray array=new JSONArray();
		if(node.equals("root")){
			List<Module> list=moduleDao.findAll(Module.class);
			for(int i=0;i<list.size();i++){
				Module m=list.get(i);
				JSONObject obj=new JSONObject();
				obj.put("id",m.getId());
				obj.put("text", m.getName());
//				obj.put("iconCls", m.getIcon());
//				obj.put("module", m.getModule());
				obj.put("leaf", false);
				obj.put("expanded", true);
				array.add(obj);
			}
		}else{
			List<Menu> list=moduleDao.findById(Module.class, node).getMenus();
			for(int i=0;i<list.size();i++){
				Menu m=list.get(i);
				JSONObject obj=new JSONObject();
				//obj.put("icon", m.getIcon());
				obj.put("id", m.getId());
				obj.put("text", m.getName());
				obj.put("url", m.getUrl());
				obj.put("leaf", false);
				obj.put("expanded", true);
				//obj.put("className", m.getClassName());
				array.add(obj);
			}
		}
		return array.toString();
	}
	//*************************************************************
	//* 返回Json数据 
	//*************************************************************	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Module> list) {
		
		return JSONArray.fromObject(list).toString();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Module> vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Module> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Module> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Module, String> getModuleDao() {
		return moduleDao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setModuleDao(IBaseDao<Module, String> moduleDao) {
		this.moduleDao = moduleDao;
	}
	
}
