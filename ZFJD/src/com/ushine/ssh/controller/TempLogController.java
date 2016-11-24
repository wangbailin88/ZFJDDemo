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
import com.ushine.ssh.model.DataGetTask;
import com.ushine.ssh.model.TempLog;
import com.ushine.ssh.service.ITempLogService;
import com.ushine.util.StringUtil;

/**
 * 临时数据控制器
 * @author wangbailin
 *
 */
@Controller
public class TempLogController {
	@Autowired
	private ITempLogService logService;
	/**
	 * 查询日志系统
	 * @param nextPage
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/findTempLog.do",method=RequestMethod.GET)
	@ResponseBody
	public String findTempLog(
			@RequestParam("page") int nextPage,
			@RequestParam("limit") int size,
			@RequestParam("systemName") String systemName,
			@RequestParam("dataType") String dataType){
		PagingObject<TempLog> object;
		try {
			if(!"undefined".equals(dataType) && !StringUtil.isNull(dataType)){
				dataType = new String(dataType.getBytes("ISO-8859-1"),"UTF-8");
			}
			object = logService.findTempLog(systemName,dataType,nextPage, size);
			return findTempLogVoToJSon(object);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String findTempLogVoToJSon(PagingObject<TempLog> vo) throws Exception {
		JSONObject root = new JSONObject();
		root.element("paging", vo.getPaging());
		JSONArray array = new JSONArray();
		for (TempLog v : vo.getArray()) {
			JSONObject obj = new JSONObject();
			obj.put("id", v.getId());
			obj.put("systemName", v.getSystemName());
			obj.put("dataType", v.getDataType());
			obj.put("operatePerson", v.getOperatePerson());
			obj.put("createDate", v.getAskTime());
			obj.put("operateBearfruit", v.getOperateFruit());
			array.add(obj);
		}
		root.element("datas", array);
		return root.toString();
	}
}		
