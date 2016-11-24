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
import com.ushine.core.po.Resource;
import com.ushine.core.service.IResourceService;
import com.ushine.dao.IBaseDao;

/**
 * 资源服务接口实现
 * @author franklin
 *
 */
@Transactional
@Service("resourceServiceImpl")
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IBaseDao<Resource, String> resDao;

	public String createNewResource(Resource res) throws Exception {
		resDao.save(res);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加资源信息.").toJSon();
	}

	public String deleteResourcesById(String[] ids) throws Exception {
		resDao.deleteById(Resource.class, ids);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除资源信息.").toJSon();
	}

	@Transactional(readOnly=true)
	public Resource findResourceById(String id) throws Exception {
		
		return resDao.findById(Resource.class, id);
	}

	@Transactional(readOnly=true)
	public List<Resource> findResourcesByName(String name) throws Exception {
		
		return resDao.findByProperty(Resource.class, "name", name);
	}
	
	@Transactional(readOnly=true)
	public List<Resource> findResourcesByCode(String code) throws Exception {
		
		return resDao.findByProperty(Resource.class, "code", code);
	}

	@Transactional(readOnly=true)
	public List<Resource> findResourcesByMenu(String menuId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Resource.class);
		criteria.createAlias("menu", "m");
		criteria.add(Restrictions.eq("m.id", menuId));
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		
		List<Resource> resources = resDao.findByCriteria(criteria);
		return resources;
	}

	@Transactional(readOnly=true)
	public List<Resource> findResInteceptor(String inteceptor) throws Exception {
		
		return resDao.findByProperty(Resource.class, "inteceptor", inteceptor);
	}

	@Transactional(readOnly=true)
	public List<Resource> findResources() throws Exception {
		
		return resDao.findAll(Resource.class);
	}

	//*************************************************************
	//* 返回Json数据 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Resource> list) {
		JSONArray array = new JSONArray();
		for(Resource res : list) {
			JSONObject object = new JSONObject();
			object.put("id", res.getId());
			object.put("name", res.getName());
			object.put("type", res.getType());
			object.put("code", res.getCode());
			object.put("link", res.getLink());
			object.put("className", res.getClassName());
			object.put("icon", res.getIcon());
			object.put("inteceptor", res.getInteceptor());
			array.add(object);
		}
		return array.toString();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Resource> vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Resource> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Resource> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Resource, String> getResDao() {
		return resDao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setResDao(IBaseDao<Resource, String> resDao) {
		this.resDao = resDao;
	}
	
}
