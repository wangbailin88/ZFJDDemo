package com.ushine.core.service.impl;

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
import com.ushine.core.po.Organiz;
import com.ushine.core.service.IOrganizService;
import com.ushine.dao.IBaseDao;

/**
 * 组织机构服务接口实现
 * @author franklin
 *
 */
@Transactional
@Service("organizServiceImpl")
public class OrganizServiceImpl implements IOrganizService {
	private static final Logger logger = LoggerFactory.getLogger(OrganizServiceImpl.class);

	@Autowired
	private IBaseDao<Organiz, String> dao;	

	public String createNewOrganiz(Organiz org) throws Exception {
		dao.save(org);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加组织机构信息.").toJSon();
	}

	public String deleteOrganizById(String[] ids) throws Exception {
		for(String id : ids) {
			Organiz org = dao.findById(Organiz.class, id);
			if(org.getParent() != null) {
				org.getParent().getSubOrganizs().remove(org);
				org.setParent(null);
			}
			dao.delete(org);
		}
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除组织机构信息.").toJSon();
	}

	@Transactional(readOnly=true)
	public Organiz findOrganizById(String orgId) throws Exception {
		
		return dao.findById(Organiz.class, orgId);
	}
	
	@Transactional(readOnly=true)
	public List<Organiz> findOrganizsByName(String name) throws Exception {
		List<Organiz> orgs = dao.findByProperty(Organiz.class, "name", name);
		return orgs;
	}
	
	@Transactional(readOnly=true)
	public List<Organiz> findOrganizsTree() throws Exception {
		List<Organiz> rootOrgs = dao.findByHql("from Organiz o where o.parent is null");
		logger.info("查询组织，共(" + rootOrgs.size() + ")根节点，循环载入各个下属节点.");
		return rootOrgs;
	}
	
	@Transactional(readOnly=true)
	public List<Organiz> findOrganizsTreeByRegion(String region) throws Exception {
		List<Organiz> orgs = dao.findByProperty(Organiz.class, "region", region);
		logger.info("查询组织[" + region + "]，共(" + orgs.size() + ")根节点，循环载入各个下属节点.");
		return orgs;
	}
	@Transactional(readOnly=true)
	public List<Organiz> findSubTree(String pid) throws Exception {
		// TODO Auto-generated method stub
		return dao.findByProperty(Organiz.class, "parent.id", pid);
	}
	@Transactional(readOnly=true)
	public List<Organiz> findAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.findAll(Organiz.class);
	}
	//*************************************************************
	//* 返回Json数据 
	//*************************************************************	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Organiz> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Organiz> vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Organiz> list) {
		
		return genExtComboJSon(list).toString();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Organiz> list) {
		
		return genExtTreeJSon(list).toString();
	}
	
	/*********************************************
	 * 生成ExtJS ComboBox JSon
	 * @return String
	/*********************************************/
	private JSONArray extCombo = new JSONArray();
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private JSONArray genExtComboJSon(List<Organiz> list) {
		for(Organiz org : list) {
			JSONObject object = new JSONObject();
			object.put("text", org.getName());
			object.put("value", org.getId());
			extCombo.add(object);
//			if(org.getSubOrganizs().size() > 0) {
//				genExtComboJSon(org.getSubOrganizs());
//			}
		}
		return extCombo;
	}
	
	/*********************************************
	 * 生成ExtJS树形JSon
	 * @return String
	/*********************************************/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private JSONArray genExtTreeJSon(List<Organiz> list) {
		JSONArray array = new JSONArray();
		for(Organiz org : list) {
			JSONObject object = new JSONObject();
			object.put("id", org.getId());
			object.put("text", org.getName());
			object.put("iconCls", "");
			object.put("leaf", "false");
			if(org.getSubOrganizs().size() > 0) {
				// 有子节点
				object.put("expanded", "true");
				object.element("children", genExtTreeJSon(org.getSubOrganizs()));
			}
			array.add(object);
		}
		return array;
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Organiz, String> getDao() {
		return dao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setDao(IBaseDao<Organiz, String> dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see com.ushine.core.service.IOrganizService#deleteOrganizById(java.lang.String)
	 * 下午3:40:46
	 */
	public void deleteOrganizById(String orgId) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteById(Organiz.class, orgId);
	}

	/* (non-Javadoc)
	 * @see com.ushine.core.service.IOrganizService#updateOrganiz(com.ushine.core.po.Organiz)
	 * 下午5:44:58
	 */
	public String updateOrganiz(Organiz org) throws Exception {
		dao.update(org);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功修改组织机构信息.").toJSon();
	}
}