package com.ushine.ssh.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.core.cache.SubordinateNodeInfoCache;
import com.ushine.core.cache.ThisNodeInfoCache;
import com.ushine.dao.IBaseDao;
import com.ushine.ssh.model.BlackBoxStatusInfo;
import com.ushine.ssh.model.SubordinateNodeInfo;
import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.service.IBlackBoxStatusInfoService;
import com.ushine.ssh.service.ISubordinateNodeInfoService;
import com.ushine.ssh.service.IThisNodeInfoService;
import com.ushine.util.StringUtil;

/**
 * 黑盒状态信息接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("blackBoxStatusInfoServiceImpl")
public class BlackBoxStatusInfoServiceImpl implements IBlackBoxStatusInfoService{
	@Autowired
	private IBaseDao<BlackBoxStatusInfo, String> baseDao;
	@Autowired
	private IThisNodeInfoService thisNodeInfoService;
	@Autowired
	private ISubordinateNodeInfoService subordinateNodeInfoService;
	@Override
	public void getBlackBoxStatusInfo() throws Exception {
		ThisNodeInfo thisNodeInfo = ThisNodeInfoCache.getInstance().getThisNodeInfo();
		if(thisNodeInfo==null){
			System.out.println("节点未注册");
			return;
		}
		// TODO Auto-generated method stub
		BlackBoxStatusInfo b = new BlackBoxStatusInfo();
		b.setCpuMonitoring("1");
		b.setCpuName("i7");
		b.setCpuUnilizationRatio("80%");
		b.setDeviceNmae(thisNodeInfo.getBlackBoxName());
		b.setDiskAMonitoring("1");
		b.setDiskBMonitoring("2");
		b.setDiskCapacityMonitoring("1");
		b.setHardDiskName("西部数据500T");
		b.setHardDiskUnilizationRatio("20%");
		b.setIp(InetAddress.getLocalHost().getHostAddress());
		b.setManageCenterConnected("1");
		b.setMemoryMonitoring("2");
		b.setMemoryName("金士顿128G");
		b.setMemoryUnilizationRatio("50%");
		b.setNoTrafficMonitoring("2");
		b.setOpenBoxMonitoring("1");
		b.setRAIDStatusMonitoring("1");
		b.setTheAuditSystem("1");
		b.setTheBypassMonitoring("1");
		b.setTheTemperature("80°c");
		b.setToSubmitTheOperation("1");
		b.setGatherTime(StringUtil.dates());
		baseDao.save(b);
	}
	@Override
	public void deleteBlackBoxStatusInfo() throws Exception {
		// TODO Auto-generated method stub
		baseDao.deleteAll(BlackBoxStatusInfo.class);
	}
	@Override
	public List<BlackBoxStatusInfo> getBlackBoxUpToDateStatusData()
			throws Exception {
		// TODO Auto-generated method stub
		//获得本机节点信息
		ThisNodeInfo thisNodeInfo =ThisNodeInfoCache.getInstance().getThisNodeInfo();
		//如果本机节点为空，就直接返回空
		if(thisNodeInfo==null){
			return null;
		}
		//获得本机的下级节点,并只查询为本机下级节点已提交给本机上级的节点的下级节点注册信息
		List<SubordinateNodeInfo> subordinateNodeInfos = SubordinateNodeInfoCache.getInstance().getSubordinateNodeInfos();
		//声明一个list容器，用于存放本机节点及下级节点的黑匣子ip地址，该ip用于查询最新的黑匣子数据的条件
		List<String> listIP = new ArrayList<>();
		listIP.add(thisNodeInfo.getBlackBoxIp());
		//判断是否有下级节点，有则把下级节点的黑匣子ip地址装入到listIP容器中
		if(subordinateNodeInfos.size()>0){
			for (SubordinateNodeInfo s : subordinateNodeInfos) {
				listIP.add(s.getBlackBoxIp());
			}
		}
		
		//SELECT * FROM t_black_box_status_info WHERE ID = (SELECT ID FROM t_black_box_status_info WHERE IP = '192.168.0.2' ORDER BY  GATHER_TIME DESC LIMIT 0,1) OR ID = (SELECT ID FROM t_black_box_status_info WHERE IP = '192.168.0.1' ORDER BY  GATHER_TIME DESC LIMIT 0,1) OR ID = (SELECT ID FROM t_black_box_status_info WHERE IP = '192.168.0.3' ORDER BY  GATHER_TIME DESC LIMIT 0,1)
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT * FROM T_BLACK_BOX_STATUS_INFO WHERE ");
		for (String s : listIP) {
			sb.append("  ID = (SELECT ID FROM T_BLACK_BOX_STATUS_INFO WHERE IP = '"+s+"' ORDER BY  GATHER_TIME DESC LIMIT 0,1)  OR  ");
		}
		//得到查询每个节点的最新黑匣子状态数据
		String sql = sb.toString().substring(0, sb.toString().length()-5);
		List<BlackBoxStatusInfo> list = baseDao.findBySql(sql,BlackBoxStatusInfo.class);
		return list;
	}
	@Override
	public synchronized void saveBlackBoxStatusInfo(BlackBoxStatusInfo blackBoxStatusInfo)
			throws Exception {
		// TODO Auto-generated method stub
		baseDao.save(blackBoxStatusInfo);
	}
	@Override
	public BlackBoxStatusInfo getThisBlackBoxUpToDateStatusData(String blackIp)
			throws Exception {
		// TODO Auto-generated method stub
				//得到查询节点的最新黑匣子状态数据
				String sql = "SELECT * FROM T_BLACK_BOX_STATUS_INFO WHERE ID = (SELECT ID FROM T_BLACK_BOX_STATUS_INFO WHERE IP = '"+blackIp+"' ORDER BY  GATHER_TIME DESC LIMIT 0,1) ";
				List<BlackBoxStatusInfo> list = baseDao.findBySql(sql,BlackBoxStatusInfo.class);
				if(list.size()>0){
					return list.get(0);
				}
				return null;
	}
}
