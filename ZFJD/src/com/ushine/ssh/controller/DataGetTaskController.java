package com.ushine.ssh.controller;

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
import com.ushine.ssh.model.DataGetTask;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
import com.ushine.ssh.service.IDataGetTaskService;
import com.ushine.util.StringUtil;

/**
 * 数据获取任务控制器
 * @author wangbailin
 *
 */
@Controller
public class DataGetTaskController {
	@Autowired
	private IDataGetTaskService getTaskService;
	/**
	 * 添加数据获取任务控制器
	 * @param dataName
	 * @param administrationDistrictId
	 * @param vocationWorkSys
	 * @param workVersion
	 * @param dataType
	 * @param startTime
	 * @param endTimes
	 * @return
	 */
	@RequestMapping(value="/saveDataGetTask.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveDataGetTask(
			@RequestParam("dataName") String dataName,
			@RequestParam("administrationDistrictId") String administrationDistrictId,
			@RequestParam("vocationWorkSys") String vocationWorkSys,
			@RequestParam("workVersion") String workVersion,
			@RequestParam("dataType") String dataType,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime){
		DataGetTask d = new DataGetTask();
		d.setAdministrationArea(administrationDistrictId);
		d.setDataName(dataName);
		d.setDataType(dataType);
		d.setEndTime(endTime);
		d.setGetSchedule("10%");
		d.setGetTiem(StringUtil.dates());
		d.setStartTime(startTime);
		d.setVersion(workVersion);
		d.setVocationalWorkSystem(vocationWorkSys);
		try {
			getTaskService.saveDataGetTask(d);
			return new ViewObject(ViewObject.RET_SUCCEED, "添加数据获取成功!").toJSon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询数据获取任务控制器
	 * @return
	 */
	@RequestMapping(value="/findDataGetTask.do",method=RequestMethod.GET)
	@ResponseBody
	public String findDataGetTask(
			@RequestParam("page") int nextPage,
			@RequestParam("limit") int size){
		try {
			PagingObject<DataGetTask> object = getTaskService.findDataGetTask(nextPage, size);
			return findDataGetTaskVoToJSon(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String findDataGetTaskVoToJSon(PagingObject<DataGetTask> vo) throws Exception {
		JSONObject root = new JSONObject();
		root.element("paging", vo.getPaging());
		JSONArray array = new JSONArray();
		for (DataGetTask v : vo.getArray()) {
			JSONObject obj = new JSONObject();
			obj.put("id", v.getId());
			obj.put("dataName", v.getDataName());
			obj.put("dataType", v.getDataType());
			obj.put("systemName", v.getVocationalWorkSystem());
			obj.put("systemVersion", v.getVersion());
			obj.put("administrativeArea", v.getAdministrationArea());
			obj.put("getSchedule", v.getGetSchedule());
			obj.put("getDate", v.getGetTiem());
			array.add(obj);
		}
		root.element("datas", array);
		return root.toString();
	}
}
