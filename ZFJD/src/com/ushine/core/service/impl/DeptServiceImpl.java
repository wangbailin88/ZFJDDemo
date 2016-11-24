package com.ushine.core.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Department;
import com.ushine.core.service.IDeptService;
import com.ushine.dao.IBaseDao;

/**
 * 部门服务接口实现
 * @author franklin
 *
 */
@Transactional
@Service("deptServiceImpl")
public class DeptServiceImpl implements IDeptService {
	private static final Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);
	
	@Autowired
	private IBaseDao<Department, String> dao;

	public String createNewDept(Department dept) throws Exception {
		dao.save(dept);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加部门信息.").toJSon();
	}

	public String deleteDeptsById(String[] ids) throws Exception {
		for(String id : ids) {
			Department dept  = dao.findById(Department.class, id);
			if(dept.getParent() != null) {
				dept.getParent().getSubDepts().remove(dept);
				dept.setParent(null);
			}
			dao.delete(dept);
		}
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除部门信息.").toJSon();
	}

	@Transactional(readOnly=true)
	public Department findDeptById(String id) throws Exception {
		
		return dao.findById(Department.class, id);
	}

	@Transactional(readOnly=true)
	public List<Department> findDeptsTreeByName(String orgId, String name) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		criteria.createAlias("organiz", "org");
		criteria.add(Restrictions.eq("org.id", orgId));
		criteria.add(Restrictions.eq("name", name));
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		
		List<Department> depts = dao.findByCriteria(criteria);
		return depts;
	}

	@Transactional(readOnly=true)
	public List<Department> findDeptsTree() throws Exception {
		List<Department> rootDepts = dao.findByHql("from Department o where o.parent is null");
		logger.info("查询部门，共(" + rootDepts.size() + ")根节点，循环载入各个下属节点.");
		return rootDepts;
	}

	@Transactional(readOnly=true)
	public List<Department> findDeptsTreeByOrganiz(String orgId) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		criteria.createAlias("organiz", "org");
		criteria.add(Restrictions.eq("org.id", orgId));
		//criteria.add(Restrictions.isNull("parent"));
		criteria.setProjection(null); // 撤销统计
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<Department> depts = dao.findByCriteria(criteria);
		return depts;
	}
	@Transactional(readOnly=true)
	public List<Department>  findDeptsByOid(String oId) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		criteria.createAlias("organiz", "org");
		criteria.add(Restrictions.eq("org.id", oId));
		criteria.add(Restrictions.isNull("parent"));
		criteria.setProjection(null); // 撤销统计
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<Department> depts = dao.findByCriteria(criteria);
		return depts;
	}
	@Transactional(readOnly=true)
	public String findTreeByOid(String oId) throws Exception {
		// TODO Auto-generated method stub
		DetachedCriteria criteria = DetachedCriteria.forClass(Department.class);
		criteria.createAlias("organiz", "org");
		criteria.add(Restrictions.eq("org.id", oId));
		criteria.add(Restrictions.isNull("parent"));
		criteria.setProjection(null); // 撤销统计
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<Department> departments = dao.findByCriteria(criteria);
		JSONArray array=new JSONArray();
		for (Department dep:departments) {
			JSONObject obj=new JSONObject();
			obj.put("id", dep.getId());
			obj.put("text", dep.getName());
			obj.put("type", "1");
			obj.put("leaf", true);
			if(dep.getSubDepts().size()>0){
				obj.put("leaf", false);
			}
			array.add(obj);
		}
		return array.toString();
	}
	@Transactional(readOnly=true)
	public String findTreeByPid(String pId) throws Exception {
		// TODO Auto-generated method stub
		List<Department> departments=dao.findByProperty(Department.class, "parent.id", pId);
		JSONArray array=new JSONArray();
		for (Department dep:departments) {
			JSONObject obj=new JSONObject();
			obj.put("id", dep.getId());
			obj.put("text", dep.getName());
			obj.put("type", "1");
			obj.put("leaf", true);
			if(dep.getSubDepts().size()>0){
				obj.put("leaf", false);
			}
			array.add(obj);
		}
		return array.toString();
	}
	//*************************************************************
	//* 返回Json数据 
	//*************************************************************	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Department> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Department> vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Department> list) {
		
		return genExtTreeJSon(list).toString();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Department> list) {
		
		return genExtComboJSon(list).toString();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSON(List<Department> list) {
		
		return getExtTreeJSon(list).toString();
	}
	
	/*********************************************
	 * 生成ExtJS ComboBox JSon
	 * @return String
	/*********************************************/
	private JSONArray extCombo = new JSONArray();
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private JSONArray genExtComboJSon(List<Department> list) {
		for(Department dept : list) {
			JSONObject object = new JSONObject();
			object.put("text", dept.getName());
			object.put("value", dept.getId());
			extCombo.add(object);
//			if(dept.getSubDepts().size() > 0) {
//				genExtComboJSon(dept.getSubDepts());
//			}
		}
		return extCombo;
	}
	
	/*********************************************
	 * 生成ExtJS树形JSon
	 * @return String
	/*********************************************/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private JSONArray genExtTreeJSon(List<Department> list) {
		JSONArray array = new JSONArray();
		for(Department dept : list) {
			JSONObject object = new JSONObject();
			object.put("id", dept.getId());
			object.put("text", dept.getName());
			object.put("iconCls", "none");
			object.put("leaf", "false");
			if(dept.getSubDepts().size() > 0) {
				// 有子节点
				object.put("expanded", true);
				object.element("children", genExtTreeJSon(dept.getSubDepts()));
			}
			array.add(object);
		}
		return array;
	}
	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private JSONArray getExtTreeJSon(List<Department> list) {
		JSONArray array = new JSONArray();
		for(Department dept : list) {
			JSONObject object = new JSONObject();
			object.put("id", dept.getId());
			object.put("text", dept.getName());
			object.put("iconCls", "none");
			if(dept.getSubDepts().size() > 0) {
				object.put("leaf", false);
				// 有子节点
				object.put("expanded", true);
				object.element("children", genExtTreeJSon(dept.getSubDepts()));
			}else{
				object.put("leaf", true);
			}
			array.add(object);
		}
		return array;
	}
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Department, String> getDao() {
		return dao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setDao(IBaseDao<Department, String> dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see com.ushine.core.service.IDeptService#deleteDeptById(java.lang.String)
	 * 下午3:48:48
	 */
	public void deleteDeptById(String deptId) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteById(Department.class, deptId);
	}

	/* (non-Javadoc)
	 * @see com.ushine.core.service.IDeptService#updateDept(com.ushine.core.po.Department)
	 * 下午6:34:02
	 */
	public String updateDept(Department dept) throws Exception {
		dao.update(dept);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功修改部门信息.").toJSon();
	}
	
}
