package com.ushine.core.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Menu;
import com.ushine.core.service.IMenuService;
import com.ushine.dao.IBaseDao;

/**
 * 菜单服务接口实现
 * @author franklin
 *
 */
@Transactional
@Service("menuSerciceImpl")
public class MenuSerciceImpl implements IMenuService {

	@Autowired
	private IBaseDao<Menu, String> menuDao;

	public String createNewMenu(Menu menu) throws Exception {
		menuDao.save(menu);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加菜单信息.").toJSon();
	}

	public String deleteMenusBydId(String[] ids) throws Exception {
		for(String id : ids) {
			Menu menu = menuDao.findById(Menu.class, id);
			if(menu.getParent() != null) {
				menu.getParent().getSubMenus().remove(menu);
				menu.setParent(null);
			}
			menuDao.delete(menu);
		}
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除菜单信息.").toJSon();
	}
	
	@Transactional(readOnly=true)
	public Menu findMenuById(String id) throws Exception {
		
		return menuDao.findById(Menu.class, id);
	}
	@Transactional(readOnly=true)
	public List<Menu> findByParentId(String id) throws Exception {
		// TODO Auto-generated method stub
		return menuDao.findByProperty(Menu.class, "parent.id", id);
	}
	@Transactional(readOnly=true)
	public List<Menu> findMenusByName(String name) throws Exception {
		
		return menuDao.findByProperty(Menu.class, "name", name);
	}

	@Transactional(readOnly=true)
	public List<Menu> findMenusTree() throws Exception {
		List<Menu> rootMenus = menuDao.findByHql("from Menu m where m.parent is null");
		
		return rootMenus;
	}

	@Transactional(readOnly=true)
	public List<Menu> findMenusTreeByModule(String moduleId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Menu.class);
		criteria.createAlias("module", "modl");
		criteria.add(Restrictions.eq("modl.id", moduleId));
		criteria.add(Restrictions.isNull("parent"));
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		
		List<Menu> menus = menuDao.findByCriteria(criteria);
		return menus;
	}

	//*************************************************************
	//* 返回Json数据 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Menu> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Menu> vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Menu> list) {
		
		return genExtTreeJSon(list).toString();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Menu> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*********************************************
	 * 生成ExtJS树形JSon
	 * @return String
	/*********************************************/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private JSONArray genExtTreeJSon(List<Menu> list) {
		JSONArray array = new JSONArray();
		for(Menu menu : list) {
			JSONObject object = new JSONObject();
			object.put("id", menu.getId());
			object.put("text", menu.getName());
			object.put("url", menu.getUrl());
			object.put("className", menu.getClassName());
			object.put("iconCls", menu.getIcon());
			object.put("leaf", true);
			object.put("expanded", false);
			array.add(object);
		}
		return array;
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Menu, String> getMenuDao() {
		return menuDao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setMenuDao(IBaseDao<Menu, String> menuDao) {
		this.menuDao = menuDao;
	}
	
}
