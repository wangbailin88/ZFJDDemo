package com.ushine.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ushine.common.vo.ViewObject;
import com.ushine.core.Util;
import com.ushine.core.cache.CityCache;
import com.ushine.core.service.ICityService;
import com.ushine.util.PathUtils;

/**
 * 
 * @author xyt
 * 
 */
@Controller
public class CityController {
	private static final Logger logger = LoggerFactory
			.getLogger(CityController.class);
	@Autowired
	private ICityService cityService;

	/**
	 * 查询所有城市树形菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findCitys", method = RequestMethod.GET)
	@ResponseBody
	public String addCompany(HttpServletRequest request) {
		logger.debug("查询所有城市");
		try {
			CityCache c = CityCache.getInstance();
			// System.out.println(c.getCitys());
			return c.getCitys();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String msg = "查询所有城市树形菜单失败";
			logger.error(msg + e);
			return new ViewObject(-1, msg).toJSon();
		}
	}

	public ICityService getCityService() {
		return cityService;
	}

	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}

	@RequestMapping(value = "/citys", method = RequestMethod.POST)
	@ResponseBody
	public String citys(@RequestParam("dataPath") String dataPath) {
		List<String> list = new ArrayList<String>();
		String path = PathUtils.getWebappsPath(new PathUtils().getClass());
		String jsonContext = new Util().ReadFile(path + "/" + dataPath);
		JSONArray jsonArray = JSONArray.fromObject(jsonContext);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		
		JSONArray j = JSONArray.fromObject(jsonObject.get("features"));
		int size = j.size();
		for (int i = 0; i < size; i++) {
			 JSONArray jo= JSONArray.fromObject(j.getJSONObject(i).get("properties"));
			 JSONObject o = jo.getJSONObject(0);
			 int x = (int)(Math.random()*100);
			 if(x%2==0){
				 list.add("true"); 
				 System.out.println(o.get("name"));
			 }else{
				 list.add("false");
			 }
		}
		return JSONArray.fromObject(list).toString();
	}
	public static void main(String[] args) {
		
	}
}
