package com.ushine.ssh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.core.cache.SubordinateNodeInfoCache;
import com.ushine.dao.IBaseDao;
import com.ushine.ssh.model.SubordinateNodeInfo;
import com.ushine.ssh.service.ISubordinateNodeInfoService;

/**
 * 下级节点信息接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("subordinateNodeInfoServiceImpl")
public class SubordinateNodeInfoServiceImpl implements ISubordinateNodeInfoService{
	@Autowired
	private IBaseDao<SubordinateNodeInfo, String> baseDao;
	@Override
	public void saveSubordinateNodeInfo(SubordinateNodeInfo subordinateNodeInfo)
			throws Exception {
		// TODO Auto-generated method stub
		baseDao.save(subordinateNodeInfo);
		//刷新下级节点的单例模式
		SubordinateNodeInfoCache.getInstance().setSubordinateNodeInfos(findSubordinateNodeInfoByStatus());
	}
	@Override
	public void deleteSubordinateNodeInfoByNodeCode(String nodeCode)
			throws Exception {
		// TODO Auto-generated method stub
		baseDao.deleteByProperty(SubordinateNodeInfo.class, "nodeCode", nodeCode);
	}
	@Override
	public List<SubordinateNodeInfo> findSubordinateNodeInfoByStatus(
			String status) throws Exception {
		// TODO Auto-generated method stub
		
		return baseDao.findByProperty(SubordinateNodeInfo.class, "status", status);
	}
	@Override
	public void updateSubordinateNodeInfoStatusById(SubordinateNodeInfo entityObject) throws Exception {
		// TODO Auto-generated method stub
		baseDao.update(entityObject);
		
	}
	@Override
	public List<SubordinateNodeInfo> findSubordinateNodeInfoByStatus()
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAll(SubordinateNodeInfo.class);
	}

}
