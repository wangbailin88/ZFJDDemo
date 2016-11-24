package com.ushine.ssh.qtzJob;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ushine.core.cache.RequestPathCache;
import com.ushine.core.cache.ThisNodeInfoCache;
import com.ushine.ssh.model.SubordinateNodeInfo;
import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.model.VocationalWork;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
import com.ushine.ssh.service.IBlackBoxStatusInfoService;
import com.ushine.ssh.service.ISubmitBlackBoxStatusInfoService;
import com.ushine.ssh.service.ISubmitNodeReisterInfoService;
import com.ushine.ssh.service.ISubmitVocationalWorkService;
import com.ushine.ssh.service.ISubordinateNodeInfoService;
import com.ushine.ssh.service.ISumitVocationalWorkSystemVersionService;
import com.ushine.ssh.service.IThisNodeInfoService;
import com.ushine.ssh.service.IVocationalWorkService;
import com.ushine.ssh.service.IVocationalWorkSystemVersionService;
import com.ushine.ssh.webcline.BlackBoxStatusInfo;
import com.ushine.ssh.webcline.VocationalWorkSystemVersionHan;
import com.ushine.util.StringUtil;

/**
 * 获取当前节点黑盒信息任务调度，
 * 使用qtzjob框架，以一定的时间间隔区获取当前节点黑盒的信息
 * @author wangbailin
 *
 */
public class QtzJob extends HttpServlet{
	@Autowired
	private IBlackBoxStatusInfoService blackBoxStatusInfoService;
	@Autowired
	private ISubordinateNodeInfoService subordinateNodeInfoService;
	@Autowired
	private ISubmitNodeReisterInfoService submitNodeReisterInfoService;
	@Autowired
	private IThisNodeInfoService thisNodeInfoService;
	@Autowired
	private ISubmitBlackBoxStatusInfoService submitBlackBoxStatusInfoService;
	@Autowired
	private IVocationalWorkService vocationalWorkService;
	@Autowired
	private ISubmitVocationalWorkService submitVocationalWorkService;
	@Autowired
	private IVocationalWorkSystemVersionService vocationalWorkSystemVersionService;
	@Autowired
	private ISumitVocationalWorkSystemVersionService sumitVocationalWorkSystemVersionService;
	/**
	 * 获取当前节点黑匣子基础信息及状态信息任务调度将执行的函数
	 */
	public void getThisBlackBoxStatusInfo(){
		//调用获取当前节点黑匣子基础信息及状态信息
		try {
			blackBoxStatusInfoService.getBlackBoxStatusInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String[] dataTypeArr = new String[]{"新增","修改","查询","删除"};
			System.out.println(dataTypeArr[(int)(Math.random()*dataTypeArr.length)]);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 提交当前节点及下级节点黑匣子信息到上级节点，任务调度执行的函数
	 */
	public void submitThisNodeAndSubordinateNodeInfo(){
		try {
			System.out.println("************************************%%%**************************************");
			List<com.ushine.ssh.model.BlackBoxStatusInfo> list = blackBoxStatusInfoService.getBlackBoxUpToDateStatusData();
			if(list==null){
				return;
			}
			for (com.ushine.ssh.model.BlackBoxStatusInfo blackBoxStatusInfo : list) {
				System.out.print(blackBoxStatusInfo.getIp()+"------时间："+blackBoxStatusInfo.getGatherTime()+"\n");
				
			}
			System.out.println("************************************%%%**************************************");
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
		//将服务端的黑匣子数据转换成客服端黑匣子数据对象
		//创建客户端黑匣子数据对象
		List<BlackBoxStatusInfo> blackBoxStatusInfos = null;
		//得到本机节点数据
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		try {
			//判断thisNodeInfo是否为空，如果空。说明没有注册节点信息，反之则判断是否有上级，如果没有说明是顶级节点(部中心)，条件满足不上报黑匣子数据
			if(thisNodeInfo==null||"".equals(thisNodeInfo.getBaseNodeIp()) || thisNodeInfo.getBaseNodeIp()==null){
				return;
			}
			blackBoxStatusInfos = servcieToCline();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		try {
			submitBlackBoxStatusInfoService.submitBlackBoxStatusInfo(blackBoxStatusInfos,thisNodeInfo.getBaseNodeIp());
			System.out.println("已上报本机及本机下级黑匣子状态数据，上报节点ip为:"+thisNodeInfo.getBaseNodeIp());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("上级网络ping不通,上级ip为"+thisNodeInfo.getBaseNodeIp());
		}
	}
	//将服务端的黑匣子数据转换成客服端的黑匣子数据
	public List<BlackBoxStatusInfo> servcieToCline() throws Exception{
		//得到服务端黑匣子数据对象
		List<com.ushine.ssh.model.BlackBoxStatusInfo> list = blackBoxStatusInfoService.getBlackBoxUpToDateStatusData();
		
		//创建客户端黑匣子数据对象
		List<BlackBoxStatusInfo> blackBoxStatusInfos = new ArrayList<BlackBoxStatusInfo>();
		//转换
		for (com.ushine.ssh.model.BlackBoxStatusInfo bl : list) {
			BlackBoxStatusInfo info = new BlackBoxStatusInfo();
			info.setGatherTime(bl.getGatherTime());
			info.setDiskCapacityMonitoring(bl.getDiskCapacityMonitoring());
			info.setDiskBMonitoring(bl.getDiskBMonitoring());
			info.setDiskAMonitoring(bl.getDiskAMonitoring());
			info.setMemoryMonitoring(bl.getMemoryMonitoring());
			info.setNoTrafficMonitoring(bl.getNoTrafficMonitoring());
			info.setCpuMonitoring(bl.getCpuMonitoring());
			info.setTheBypassMonitoring(bl.getTheBypassMonitoring());
			info.setOpenBoxMonitoring(bl.getOpenBoxMonitoring());
			info.setToSubmitTheOperation(bl.getToSubmitTheOperation());
			info.setRAIDStatusMonitoring(bl.getRAIDStatusMonitoring());
			info.setTheAuditSystem(bl.getTheAuditSystem());
			info.setManageCenterConnected(bl.getManageCenterConnected());
			info.setTheTemperature(bl.getTheTemperature());
			info.setHardDiskUnilizationRatio(bl.getHardDiskUnilizationRatio());
			info.setHardDiskName(bl.getHardDiskName());
			info.setMemoryUnilizationRatio(bl.getMemoryUnilizationRatio());
			info.setMemoryName(bl.getMemoryName());
			info.setCpuUnilizationRatio(bl.getCpuUnilizationRatio());
			info.setCpuName(bl.getCpuName());
			info.setDeviceNmae(bl.getDeviceNmae());
			info.setIp(bl.getIp());
			blackBoxStatusInfos.add(info);
		}
		return blackBoxStatusInfos;
		
	}
	/**
	 * 调用删除当前节点黑匣子的状态信息，任务调用将执行的函数
	 */
	public void deleteThisNodeAndSubordinateNodeInfo(){
		try {
			//删除当前节点黑匣子状态信息数据
			blackBoxStatusInfoService.deleteBlackBoxStatusInfo();
			System.out.println("删除了数据");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 提交本机的下级节点到本机的上级节点，任务调度将执行的函数
	 */
	public void submitSubordinateNodeInfo(){
		//得到本机节点是否是顶级节点(没有父节点)
		//如果没有父节点将不再提交下级节点注册信息到本机的上级节点
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		try {
		//判断是否有父级节点，如果不为空说明是有父级节点的，反之则不作任何事
		if(thisNodeInfo==null||thisNodeInfo.getBaseNodeIp()==null||"".equals(thisNodeInfo.getBaseNodeIp())){
			return ;
		}
		//得到未提交到本机上级的下级节点注册信息
		List<SubordinateNodeInfo> list=subordinateNodeInfoService.findSubordinateNodeInfoByStatus("0");
		//循环提交所有未提交到本机上级节点的下级节点注册信息
		for (SubordinateNodeInfo sub : list) {
			//把服务端的下级节点对象转换成客户端的下级节点对象
			com.ushine.ssh.webcline.SubordinateNodeInfo s = new com.ushine.ssh.webcline.SubordinateNodeInfo();
			s.setBaseNodeIp(sub.getBaseNodeIp());
			s.setBaseNodeName(sub.getBaseNodeName());
			s.setBlackBoxCode(sub.getBlackBoxCode());
			s.setBlackBoxIp(sub.getBlackBoxIp());
			s.setBlackBoxName(sub.getBlackBoxName());
			s.setNodeCode(sub.getNodeCode());
			s.setNodeIp(sub.getNodeIp());
			s.setNodeName(sub.getNodeName());
			s.setStatus(sub.getStatus());
			s.setRegisterTime(sub.getRegisterTime());
			submitNodeReisterInfoService.subordinateNodeReisterInfoSumit(s,thisNodeInfo.getBaseNodeIp());
			//修改当前提交的下级节点注册信息状态为已提交
			sub.setStatus("1");
			subordinateNodeInfoService.updateSubordinateNodeInfoStatusById(sub);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("上报本机的下级节点注册信息到本机上级节点时，遇到本机的上级节点网络ping不通,本机上级节点ip为："+thisNodeInfo.getBaseNodeIp());
		}
	}
	//
	/**
	 * 上报业务系统注册信息
	 * 1.判断本机节点是否是顶级节点(部中心节点)，if(true)不需上报else 上报
	 * 2.获取未上报的注册系统系统
	 * 
	 */
	public void submitVocationalWork(){
		
		//获得本机节点信息，判断是否有上级有上级节点
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		if("".equals(thisNodeInfo.getBaseNodeIp())||thisNodeInfo.getBaseNodeIp()==null){
			System.out.println("本机为顶级节点，无需上报注册系统数据");
			return;
		}
		
		//获得未上报的注册系统数据
		try {
			//待上报的注册业务系统数据，服务端
			List<VocationalWork> se = vocationalWorkService.findVocationalWorkByStatus("0");
			System.out.println("上报业务系统注册信息----------》》"+StringUtil.dates());
			System.out.println(se.size());
			for (VocationalWork v : se) {
				//待上报的注册业务系统数据，客服端
				com.ushine.ssh.webcline.VocationalWork cl = new com.ushine.ssh.webcline.VocationalWork();
				//将服务端数据转换成客服端数据
				cl.setId(v.getId());
				cl.setAdministrationDistrictId(v.getAdministrationDistrictId());
				cl.setDataType(v.getDataType());
				cl.setRegisterTime(v.getRegisterTime());
				cl.setRemarks(v.getRemarks());
				cl.setStatus(v.getStatus());
				cl.setSystemName(v.getSystemName());
				//上报数据
				submitVocationalWorkService.sumitVocationalWork(cl, thisNodeInfo.getBaseNodeIp());
				//修改已上报的数据状态
				v.setStatus("1");
				vocationalWorkService.updateVocationalWorkStatus(v);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 上报业务系统插件
	 * 1.判断本机节点是否是顶级节点(部中心等特殊节点)，如果是将不进行上报
	 * 2.获取未
	 * 
	 */
	public void sumitVocationalWorkSystemVersion(){
		//获得本机节点信息，判断是否有上级有上级节点
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		if("".equals(thisNodeInfo.getBaseNodeIp())||thisNodeInfo.getBaseNodeIp()==null){
			System.out.println("本机为顶级节点，无需上报注册系统数据");
			return;
		}
		String path = RequestPathCache.getInstance().getPath();
		try {
			List<VocationalWorkSystemVersion> vocationalWorkSystemVersions = vocationalWorkSystemVersionService.findVocationalWorkVersionByStatus("0");
			System.out.println(vocationalWorkSystemVersions.size());
			for (VocationalWorkSystemVersion v : vocationalWorkSystemVersions) {
				System.out.println(path+v.getFilePath());
				//插件所属业务系统
				VocationalWork work = vocationalWorkService.findVocationalWorkById(v.getVocationalWorkSystemId());
				//创建客服端file传输对象
				VocationalWorkSystemVersionHan han = new VocationalWorkSystemVersionHan();
				DataSource source = new FileDataSource(new File(path+v.getFilePath()));
				DataHandler handler = new DataHandler(source);
				han.setDataHandler(handler);
				han.setFileName(v.getFileName());
				han.setNodeCode(work.getAdministrationDistrictId());
				han.setFilePath(v.getFilePath());
				han.setId(v.getId());
				han.setRemarks(v.getRemarks());
				han.setStatus(v.getStatus());
				han.setUploadTime(v.getUploadTime());
				han.setVersion(v.getVersion());
				
				
				//将客服端的数据转换成服务端数据
				han.setVocationalWorkSystemId(v.getVocationalWorkSystemId());
				//上报数据接口
				sumitVocationalWorkSystemVersionService.sumitVocationalWorkSystemVersion(han,thisNodeInfo.getBaseNodeIp());
				//修改插件数据未已上报
				v.setStatus("1");
				vocationalWorkSystemVersionService.updateVocationalWorkSystemVersionStatus(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
