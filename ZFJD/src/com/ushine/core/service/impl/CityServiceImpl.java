package com.ushine.core.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.common.vo.PagingObject;
import com.ushine.core.po.City;
import com.ushine.core.service.ICityService;
import com.ushine.dao.IBaseDao;

@Transactional
@Service("cityServiceImpl")
public class CityServiceImpl implements ICityService{
	private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);
	@Autowired
	private IBaseDao<City, String> dao;
	@Transactional(readOnly=true)
	public String findCity(String node) throws Exception {
		// TODO Auto-generated method stub
		logger.debug("查询模块树形菜单");
		if(node.equals("")||node==null){
			throw new Exception("node为空");
		}
		JSONArray array=new JSONArray();
		if(node.equals("root")){
			DetachedCriteria criteria = DetachedCriteria.forClass(City.class);
			criteria.add(Restrictions.eq("CC", "1"));
			criteria.addOrder(Order.asc("alphabet"));
			List<City> citys=dao.findByCriteria(criteria);
			for(City c:citys){
				JSONObject obj=new JSONObject();
				obj.put("id", c.getXZQHDM());
				obj.put("text", c.getXZQHMC());
				obj.put("leaf", false);
				obj.put("expanded", true);
				array.add(obj);
			}
		}else{
			DetachedCriteria criteria = DetachedCriteria.forClass(City.class);
			criteria.add(Restrictions.eq("pid", node));
			List<City> citys=dao.findByCriteria(criteria);
			for(City c:citys){
				JSONObject obj=new JSONObject();
				obj.put("id", c.getXZQHDM());
				obj.put("text", c.getXZQHMC());
				obj.put("leaf", false);
				obj.put("expanded", false);
				array.add(obj);
			}
		}
		return array.toString();
	}
	@Transactional(readOnly=true)
	public String findCity()throws Exception{
		DetachedCriteria criteria = DetachedCriteria.forClass(City.class);
		criteria.add(Restrictions.eq("CC", "1"));
		criteria.addOrder(Order.asc("alphabet"));
		List<City> cs=dao.findByCriteria(criteria);
		return toExtTreeJSon(cs);
	}
	/*********************************************
	 * 生成ExtJS树形JSon
	 * @return String
	/*********************************************/
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String listToJSon(List<City> list) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String voToJSon(PagingObject<City> vo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtTreeJSon(List<City> list) {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
		for(City city : list) {
			JSONObject object = new JSONObject();
			object.put("id", city.getXZQHDM());
			object.put("text", city.getXZQHMC());
			object.put("leaf", false);
			object.put("expanded", false);
			object.put("checked", false);
			JSONArray array1 = new JSONArray();
			for(City c1:city.getSubcitys()){
				JSONObject obj1=new JSONObject();
				obj1.put("id", c1.getXZQHDM());
				obj1.put("text", c1.getXZQHMC());
				obj1.put("leaf", true);
				obj1.put("checked", false);
				obj1.put("expanded", false);
				array1.add(obj1);
			}
			object.element("children", array1);
			array.add(object);
		}
	//	System.out.println(array.toString());
		return array.toString();
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String toExtComboJSon(List<City> list) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
