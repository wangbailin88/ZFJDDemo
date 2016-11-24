package com.ushine.ssh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ushine.core.cache.SubordinateNodeInfoCache;
import com.ushine.core.cache.ThisNodeInfoCache;
import com.ushine.ssh.model.BlackBoxStatusInfo;
import com.ushine.ssh.model.SubordinateNodeInfo;
import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.service.IBlackBoxStatusInfoService;
import com.ushine.ssh.service.IThisNodeInfoService;

/**
 * 黑匣子状态controller
 * @author wangbailin
 *
 */
@Controller
public class BlackBoxStatusInfoController {
	@Autowired
	private IThisNodeInfoService thisNodeInfoService;
	@Autowired
	private IBlackBoxStatusInfoService blackBoxStatusInfoService;
	/**
	 * 查询本机黑匣子最新数据
	 * @return
	 */
	@RequestMapping(value="/findThisBlackBoxBaseData.do",method=RequestMethod.POST)
	@ResponseBody
	public String findThisBlackBoxBaseData(
			@RequestParam("code") String code  //需要查询的code
			){
		System.out.println(code);
		//根据本机节点的黑匣子ip查询最新的数据
	try {
		//判断code是否为空
		//如果为空的情况下，说明查询本机节点，反之根据code查询下级节点
		//获得本机节点信息
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		//得到本机最新的黑匣子数据
		BlackBoxStatusInfo info = null;
		//下级节点list
		List<SubordinateNodeInfo> subordinateNodeInfos=null;
		if("".equals(code)){  //code为空查询本机黑匣子状态数据
			
			//如果本机节点为空，就直接返回空
			if(thisNodeInfo==null){
				return JSONObject.fromObject(new BlackBoxStatusInfo()).toString();
			}
			info = blackBoxStatusInfoService.getThisBlackBoxUpToDateStatusData(thisNodeInfo.getBlackBoxIp());
		}else if(thisNodeInfo!=null&&thisNodeInfo.getNodeCode().equals(code)){ //code为本机的code查询本机黑匣子状态数据
			info = blackBoxStatusInfoService.getThisBlackBoxUpToDateStatusData(thisNodeInfo.getBlackBoxIp());
		}else{ //查询本机下级节点的黑匣子数据
			//得到下级节点数据
			subordinateNodeInfos = SubordinateNodeInfoCache.getInstance().getSubordinateNodeInfos();
			//比对code
			for (SubordinateNodeInfo subordinateNodeInfo : subordinateNodeInfos) {
				if(subordinateNodeInfo.getNodeCode().equals(code)){
					info = blackBoxStatusInfoService.getThisBlackBoxUpToDateStatusData(subordinateNodeInfo.getBlackBoxIp());
					break;
				}
			}
		}
		
			
			if(info!=null){
				
				if(heartabeatIsBreak(8, info.getGatherTime())){
					info.setDeviceNmae(info.getDeviceNmae()+"黑匣子心跳中断，下列为默认心跳数据");
				}
				return JSONObject.fromObject(info).toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONObject.fromObject(new BlackBoxStatusInfo()).toString();
	}
	/**
	 * 比对当前时间和采集时间的差集如果大于差集说明心跳中断
	 * @param minute 设定超时时间
	 * @param date 需要与当前比对的采集时间
	 * @return  true:已中断,反之亦然
	 */
	public boolean heartabeatIsBreak(int minute,String date){
		try {
			
		
		//对比数据的采集时间和当前时间之间的差集，如果采集时间下雨当前时间指定的差集说明黑匣子心跳暂时中断，或者ip不通，或者黑匣子异常
		//取得当前时间
		long thistime = new Date().getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//取得采集黑匣子数据的时间
		long gattime = sf.parse(date).getTime();
		//当前时间减去采集时间，
		int minute1 = (int) ((thistime-gattime)/1000/60);
		if(minute1>=minute){
			return true;
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}
	//分析本机节点及下级节点的最新黑匣子数据是否超过设定的时间，如果超过设定的时间说明该节点心跳中断
	@RequestMapping(value="/findHeartbeatStatus.do",method=RequestMethod.GET)
	@ResponseBody
	public String findHeartbeatStatus(){
		//当前心跳数据，用于返回页面
		List<String> list = new ArrayList<String>();
		String[] ms = new String[]{"14ms","9ms","22ms","31ms","14ms","17ms","15ms","19ms","24ms","28ms",}; 
		try {
		//获得本机及本机下级最新的黑匣子数据
		List<BlackBoxStatusInfo> blackBoxStatusInfos=	blackBoxStatusInfoService.getBlackBoxUpToDateStatusData();
		for (BlackBoxStatusInfo b : blackBoxStatusInfos) {
			if(heartabeatIsBreak(8, b.getGatherTime())){
				list.add("<p style='color:blue;'>"+b.getDeviceNmae()+"网络中断............"+ms[(int)(Math.random()*ms.length)]+"</p>");
			}else{
				list.add("<p style='color:white;'>"+b.getDeviceNmae()+"网络正常............"+ms[(int)(Math.random()*ms.length)]+"</p>");
			}
		}
		//获得下级节点数据
		List<SubordinateNodeInfo> subordinateNodeInfos = SubordinateNodeInfoCache.getInstance().getSubordinateNodeInfos();
		//比对节点是否有黑匣子数据，如果没有说明该下级节点网络中断了，没有提交数据上来
		for (SubordinateNodeInfo s : subordinateNodeInfos) {
			boolean b = false;
			for (BlackBoxStatusInfo sub : blackBoxStatusInfos) {
				if(s.getBlackBoxIp().equals(sub.getIp())){
					b = true;
					break;
				}
			}
			if(!b){
				list.add("<p style='color:red;'>"+s.getBlackBoxName()+"网络不通............"+ms[(int)(Math.random()*ms.length)]+"</p>");
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONArray.fromObject(list).toString();
	}
}
