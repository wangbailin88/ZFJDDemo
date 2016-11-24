package com.ushine.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.dao.IBaseDao;
import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.service.IThisNodeInfoService;
/**
 * 本机节点信息接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("thisNodeInfoServiceImpl")
public class ThisNodeInfoServiceImpl implements IThisNodeInfoService{
	@Autowired
	private IBaseDao<ThisNodeInfo, String> baseDao;

	@Override
	public void saveThisNodeInfo(ThisNodeInfo thisNodeInfo) throws Exception {
		// TODO Auto-generated method stub
		deleteThisNodeInfo();//删除注册信息
		baseDao.save(thisNodeInfo);
	}

	@Override
	public ThisNodeInfo findThisNodeInfo() throws Exception {
		// TODO Auto-generated method stub
		List<ThisNodeInfo> infos = baseDao.findAll(ThisNodeInfo.class);
		//判断是否为null
		if(infos.size()<=0){
			return null;
		}
		//保留第一个，
		if(infos.size()>1){
			for(int i = 1;i<infos.size();i++){
				baseDao.deleteById(ThisNodeInfo.class, infos.get(i).getId());
			}
		}
		return infos.get(0);
	}

	@Override
	public void deleteThisNodeInfo() throws Exception {
		// TODO Auto-generated method stub
		baseDao.deleteAll(ThisNodeInfo.class);
		
	}

}
