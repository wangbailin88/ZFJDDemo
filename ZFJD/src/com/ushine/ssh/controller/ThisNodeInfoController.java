package com.ushine.ssh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ushine.common.vo.ViewObject;
import com.ushine.core.cache.SubordinateNodeInfoCache;
import com.ushine.core.cache.ThisNodeInfoCache;
import com.ushine.core.po.City;
import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.service.ISubmitNodeReisterInfoService;
import com.ushine.ssh.service.IThisNodeInfoService;
import com.ushine.ssh.webcline.SubordinateNodeInfo;
import com.ushine.util.StringUtil;
/**
 * 当前节点控制器
 * @author wangbailin
 *
 */
@Controller
public class ThisNodeInfoController {
	@Autowired
	private IThisNodeInfoService thisNodeInfoService;
	@Autowired
	private ISubmitNodeReisterInfoService submitNodeReisterInfoService;
	/**
	 * 注册本机节点
	 * @param nodeCode
	 * @param nodeName
	 * @param nodeIp
	 * @param baseNodeIp
	 * @param baseNodeName
	 * @param blackBoxName
	 * @param blackBoxCode
	 * @param blackBoxIp
	 * @return
	 */
	@RequestMapping(value="/saveThisNodeInfo.do",method=RequestMethod.POST)
	@ResponseBody
	public String saveThisNodeInfo(
			@RequestParam("nodeCode") String nodeCode,
			@RequestParam("nodeName") String nodeName,
			@RequestParam("nodeIp") String nodeIp,
			@RequestParam("baseNodeIp") String baseNodeIp,
			@RequestParam("baseNodeName") String baseNodeName,
			@RequestParam("blackBoxName") String blackBoxName,
			@RequestParam("blackBoxCode") String blackBoxCode,
			@RequestParam("blackBoxIp") String blackBoxIp,
			@RequestParam("baseNodeCode") String baseNodeCode){
		//创建本机节点对象
		ThisNodeInfo t = new ThisNodeInfo();
		t.setBaseNodeIp(baseNodeIp);
		t.setBaseNodeName(baseNodeName);
		t.setBlackBoxCode(blackBoxCode);
		t.setBlackBoxIp(blackBoxIp);
		t.setBlackBoxName(blackBoxName);
		t.setNodeCode(nodeCode);
		t.setNodeIp(nodeIp);
		t.setNodeName(nodeName);
		t.setBaseNodeCode(baseNodeCode);
		t.setRegisterTime(StringUtil.dates());
		try {
			//调用注册节点接口
			thisNodeInfoService.saveThisNodeInfo(t);
			//将当前节点信息修改
			ThisNodeInfoCache.getInstance().setThisNodeInfo(t);
			System.out.println(ThisNodeInfoCache.getInstance().getThisNodeInfo().getNodeIp());
			//判断是否有父级节点，如果不为空说明是有父级节点的，上报注册节点信息到上级节点，反之则不作任何事
			if("".equals(baseNodeIp)||baseNodeIp==null){
				return new ViewObject(ViewObject.RET_SUCCEED, "注册成功!").toJSon();
			}
			//创建客户端下级节点注册信息
			SubordinateNodeInfo s = new SubordinateNodeInfo();
			s.setBaseNodeIp(baseNodeIp);
			s.setBaseNodeName(baseNodeName);
			s.setBlackBoxCode(blackBoxCode);
			s.setBlackBoxIp(blackBoxIp);
			s.setBlackBoxName(blackBoxName);
			s.setNodeCode(nodeCode);
			s.setNodeIp(nodeIp);
			s.setNodeName(nodeName);
			s.setStatus("0");
			s.setRegisterTime(t.getRegisterTime());
			//上报注册信息
			try{
				submitNodeReisterInfoService.subordinateNodeReisterInfoSumit(s,baseNodeIp);
			}catch(Exception ex){
				//如果遇到上级节点网络不通的情况下，删除刚入库的注册信息
				thisNodeInfoService.deleteThisNodeInfo();
				return new ViewObject(ViewObject.RET_FAILURE, "上级节点网络ping不通,请稍后重试").toJSon();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ViewObject(ViewObject.RET_FAILURE, "注册失败!请联系管理员").toJSon();
		}
		return new ViewObject(ViewObject.RET_SUCCEED, "注册成功!").toJSon();
	}
	/**
	 * 获取当前节点信息，如果当前节点为空，返回null
	 * @return
	 */
	@RequestMapping(value="/findThisNodeInfo.do",method=RequestMethod.GET)
	@ResponseBody
	public String findThisNodeInfo(){
		try {
			ThisNodeInfo info = ThisNodeInfoCache.getInstance().getThisNodeInfo();
			if(info!=null){
				return JSONObject.fromObject(info).toString();
			}
			info = new ThisNodeInfo();
			return JSONObject.fromObject(info).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//加载节点信息函数
	@RequestMapping(value="/loadingNode.do",method=RequestMethod.GET)
	@ResponseBody
	public String loadingNode(){
		JSONArray array = new JSONArray();
		//取得当前节点
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		//取得下级节点
		List<com.ushine.ssh.model.SubordinateNodeInfo> subordinateNodeInfos = SubordinateNodeInfoCache.getInstance().getSubordinateNodeInfos();
		//取得省级
		List<List<String>> prList = new ArrayList<List<String>>();
		for (com.ushine.ssh.model.SubordinateNodeInfo sub : subordinateNodeInfos) {
			if(thisNodeInfo.getNodeIp().equals(sub.getBaseNodeIp())){
				List<String> list = new ArrayList<String>();
				list.add(sub.getNodeCode());
				list.add(sub.getNodeName());
				list.add(sub.getNodeIp());
				prList.add(list);
			}
		}
		//取得市级
		List<Map<String, List<String>>> ciList = new ArrayList<Map<String, List<String>>>();
		for (List<String> ci : prList) {
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (com.ushine.ssh.model.SubordinateNodeInfo sub : subordinateNodeInfos) {
				if(ci.get(2).equals(sub.getBaseNodeIp())){
					List<String> list = new ArrayList<String>();
					list.add(sub.getNodeCode());
					list.add(sub.getNodeName());
					list.add(sub.getNodeIp());
					map.put(ci.get(0), list);
				}
			}
			ciList.add(map);
		}
		//生成json数组
		JSONObject thisObject = new JSONObject();
		thisObject.put("id", thisNodeInfo.getNodeCode());
		thisObject.put("text", thisNodeInfo.getNodeName());
		thisObject.put("leaf", false);
		thisObject.put("expanded", false);
		thisObject.put("checked", false);
		JSONArray thisArray = new JSONArray();
		for(List<String> prStr : prList) {
			JSONObject object = new JSONObject();
			object.put("id", prStr.get(0));
			object.put("text", prStr.get(1));
			object.put("leaf", false);
			object.put("expanded", false);
			object.put("checked", false);
			JSONArray array1 = new JSONArray();
			for(Map<String, List<String>> ciStr : ciList){
				if(ciStr.size()<=0){
					continue;
				}
				JSONObject obj1=new JSONObject();
				obj1.put("id", ciStr.get(prStr.get(0)).get(0));
				obj1.put("text", ciStr.get(prStr.get(0)).get(1));
				obj1.put("leaf", true);
				obj1.put("checked", false);
				obj1.put("expanded", false);
				array1.add(obj1);
			}
			object.element("children", array1);
			thisArray.add(object);
		}
		thisObject.element("children", thisArray);
		array.add(thisObject);
		return array.toString();
	}
}
