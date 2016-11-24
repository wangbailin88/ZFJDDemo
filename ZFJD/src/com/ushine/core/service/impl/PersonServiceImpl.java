package com.ushine.core.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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

import com.ushine.common.utils.DateUtils;
import com.ushine.common.vo.DataObject;
import com.ushine.common.vo.Paging;
import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.core.po.Department;
import com.ushine.core.po.Person;
import com.ushine.core.service.IDeptService;
import com.ushine.core.service.IPersonService;
import com.ushine.dao.IBaseDao;

/**
 * 人员接口实现
 * @author franklin
 *
 */
@Transactional
@Service("personServiceImpl")
public class PersonServiceImpl implements IPersonService {
	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Resource(name="baseDao")
	private IBaseDao<Person, Serializable> dao;

	@Autowired
	private IDeptService deptService;

	public String createNewPersion(Person obj) throws Exception {
		dao.save(obj);
		Person person = findPersonByName(obj.getUserName()).get(0);
		
		return new ViewObject(ViewObject.RET_SUCCEED, "成功添加人员信息.").toJSon();
	}
	public String updatePersonInfo(Person obj) throws Exception {
		dao.executeSql("UPDATE T_PERSON SET NUMBER = '"+obj.getNumber()+"',IP = '"+obj.getIp()+"',IDCARD = '"+obj.getIDCard()+"',STATUS = "+obj.getStatus()+",TEL ='"+obj.getTel()+"',TRUE_NAME = '"+obj.getTrueName()+"',USER_NAME='"+obj.getUserName()+"',DEPT_ID='"+obj.getDept().getId()+"' WHERE ID = '"+obj.getId()+"'");
		return new ViewObject(ViewObject.RET_SUCCEED, "成功修改人员信息.").toJSon();
	}
	public String deletePersonById(String[] ids) throws Exception {
		dao.deleteById(Person.class, ids);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除人员信息.").toJSon();
	}

	public String modifyPersonInfo(Person obj) throws Exception {
		dao.update(obj);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功修改人员信息.").toJSon();
	}
	public String modifyPassword(String oldPass, String pass, String uid)
			throws Exception {
		// TODO Auto-generated method stub
		Person p=dao.findById(Person.class, uid);
		if(!p.getPassword().equals(oldPass)){
			return new ViewObject(ViewObject.RET_ERROR, "密码错误").toJSon();
		}
		p.setPassword(pass);
		dao.update(p);
		return new ViewObject(ViewObject.RET_SUCCEED,"修改密码成功").toJSon();
	}
	public void modifyPersonLoginInfo(String id, String loginIP)
			throws Exception {
		Person person = dao.findById(Person.class, id);
		person.setLoginLastDate(DateUtils.currTimeToString());
		person.setLoginLastIP(loginIP);
		dao.update(person);
	}
	public String deletePersonById(String id) throws Exception {
		dao.deleteById(Person.class, id);
		return new ViewObject(ViewObject.RET_SUCCEED, "成功删除人员信息.").toJSon();
	}
	@Transactional(readOnly=true)
	public Person findPersonById(String id) throws Exception {
		return dao.findById(Person.class, id);
	}
	
	@Transactional(readOnly=true)
	public List<Person> findPersonByName(String uName) throws Exception {
		
		return dao.findByProperty(Person.class, "userName", uName);
	}
	
	@Transactional(readOnly=true)
	public PagingObject<Person> findPagingPersonByDept(String deptId, int sizePage, 
			int current) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(Person.class);
		criteria.createAlias("dept", "d");
		criteria.add(Restrictions.eq("d.id", deptId));
		int rowCount = dao.getRowCount(criteria);
		Paging paging = new Paging(sizePage, current, rowCount);
		
		logger.debug("分页信息:" + JSONObject.fromObject(paging));
		
		//******************************************************************************************
		//* DetachedCriteria查询是会返回的list里不是根对象而是包含根对象和子对象的数组，解决的办法是加 
		//* criteria.setProjection(null)
		//* criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		//******************************************************************************************
		criteria.setProjection(null); // 撤销统计
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		
		List<Person> datas = dao.findPagingByCriteria(criteria, sizePage, paging.getStartRecord());
		PagingObject<Person> vObject = new PagingObject<Person>();
		vObject.setPaging(paging);
		vObject.setArray(datas);
		return vObject;
	}
	
	@Transactional(readOnly=true)
	public PagingObject<Person> findPagingPersonAll(int sizePage, int current)
			throws Exception {
		int rowCount = dao.getRowCount(Person.class);
		Paging paging = new Paging(sizePage, current, rowCount);
		logger.debug("分页信息:" + JSONObject.fromObject(paging));
		List<Person> datas = dao.findPaging(Person.class, sizePage, paging.getStartRecord());
		PagingObject<Person> vObject = new PagingObject<Person>();
		vObject.setPaging(paging);
		vObject.setArray(datas);
		return vObject;
	}

	@Transactional(readOnly=true)
	public List<Person> findByRid(String id) throws Exception {
		@SuppressWarnings("unchecked")
		List<Person> list=dao.findBySql("SELECT p.* FROM T_PERSON p,T_PERSON_ROLE t WHERE t.ROLE_ID='"+id+"'");
		return list;
	}
	
	//*************************************************************
	//* 返回Json数据 
	//*************************************************************	
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<Person> list) {
		
		return null;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<Person> vo) {
		JSONObject root = new JSONObject();
		root.element("paging", vo.getPaging());
		JSONArray array = new JSONArray();
		for(Person person : vo.getArray()) {
			JSONObject obj = new JSONObject();
			obj.put("id", person.getId());
			obj.put("number", person.getNumber());
			obj.put("trueName", person.getTrueName());
			obj.put("userName", person.getUserName());
			obj.put("tel", person.getTel());
			obj.put("ip", person.getIp());
			obj.put("IDCard", person.getIDCard());
			obj.put("orgId", person.getDept().getOrganiz().getId());
			obj.put("deptId", person.getDept().getId());
			obj.put("orgName", person.getDept().getOrganiz().getName());
			obj.put("deptName", person.getDept().getName());
			obj.put("createDate", person.getCreateDate());
			obj.put("loginLastDate", person.getLoginLastDate());
			obj.put("loginLastIP", person.getLoginLastIP());
			obj.put("status", person.getStatus());
			array.add(obj);
		}
		root.element("datas", array);
		return root.toString();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<Person> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<Person> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(readOnly=true)
	public String findPersonNames(String orgId) throws Exception {
		List<Department> list = deptService.findDeptsTreeByOrganiz(orgId);
		List<String> listName = new ArrayList<String>();
		for(Department department:list){
			List<Person> listPerson = findByDeptId(department.getId());
			for(Person person:listPerson){
				listName.add(person.getTrueName());
			}
		}
		DataObject<String> dataObject = new DataObject<String>(ViewObject.RET_SUCCEED, "加载成功");
		dataObject.setArray(listName);
		return dataObject.toJSon();
	}
	@Transactional(readOnly=true)
	public List<Person> findByDeptId(String deptId) throws Exception {
		logger.info("按部门ＩＤ查询部门人员");
		DetachedCriteria criteria = DetachedCriteria.forClass(Person.class);
		criteria.createAlias("dept", "d");
		criteria.add(Restrictions.eq("d.id", deptId));
		
		criteria.setProjection(null); // 撤销统计
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		
		List<Person> datas = dao.findByCriteria(criteria);
		return datas;
	}
	
	
	//*************************************************************
	//* Getting / Setting 
	//*************************************************************
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IBaseDao<Person, Serializable> getDao() {
		return dao;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setDao(IBaseDao<Person, Serializable> dao) {
		this.dao = dao;
	}
	
	


	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public IDeptService getDeptService() {
		return deptService;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	
	@Transactional(readOnly=true)
	public List<Person> findPersonByIDCard(String IDCard) throws Exception {
		
		return dao.findByProperty(Person.class, "IDCard", IDCard);
	}
	
	@Transactional(readOnly=true)
	public List<Person> findPersonByTruename(String trueName) throws Exception {
		
		return dao.findByProperty(Person.class, "trueName", trueName);
	}

}
