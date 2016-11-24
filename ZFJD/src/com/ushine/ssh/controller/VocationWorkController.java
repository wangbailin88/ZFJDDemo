package com.ushine.ssh.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ushine.common.vo.PagingObject;
import com.ushine.common.vo.ViewObject;
import com.ushine.ssh.model.VocationalWork;
import com.ushine.ssh.service.IVocationalWorkService;
import com.ushine.util.StringUtil;

/**
 * 业务系统控制器
 * @author wangbailin
 *
 */
@Controller
public class VocationWorkController {
	@Autowired
	private IVocationalWorkService vocationalWorkService;
	@RequestMapping(value="/saveVocationWork.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveVocationWork(
			HttpServletRequest request,
			@RequestParam("administrationDistrictId") String administrationDistrictId,
			@RequestParam("systemName") String systemName,
			@RequestParam("remarks") String remarks){
		VocationalWork vo = new VocationalWork();
		vo.setDataType("数据查询");
		vo.setRegisterTime(StringUtil.dates());
		vo.setSystemName(systemName);
		vo.setRemarks(remarks);
		vo.setAdministrationDistrictId(administrationDistrictId);
		vo.setStatus("0");
		try {
			vocationalWorkService.saveVocationalWork(vo);
			return new ViewObject(ViewObject.RET_SUCCEED, "新增业务系统成功！").toJSon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	@RequestMapping(value="/findVocationalWorkByDistrictId.do",method=RequestMethod.GET)
	@ResponseBody
	public String findVocationalWorkByDistrictId(
			HttpServletRequest request,
			@RequestParam("districtId") String districtId,
			@RequestParam("page") int nextPage,
			@RequestParam("limit") int size){
		try {
			PagingObject<VocationalWork> object = vocationalWorkService.findVocationalWorkByDistrictId(districtId, nextPage, size);
			return findVocationalWorkByDistrictIdVoToJSon(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String findVocationalWorkByDistrictIdVoToJSon(PagingObject<VocationalWork> vo) throws Exception {
		JSONObject root = new JSONObject();
		root.element("paging", vo.getPaging());
		JSONArray array = new JSONArray();
		for (VocationalWork v : vo.getArray()) {
			JSONObject obj = new JSONObject();
			obj.put("id", v.getId());
			obj.put("systemName", v.getSystemName());
			obj.put("dataType", v.getDataType());
			obj.put("registerTime", v.getRegisterTime());
			obj.put("remarks", v.getRemarks());
			array.add(obj);
		}
		root.element("datas", array);
		return root.toString();
	}
}
