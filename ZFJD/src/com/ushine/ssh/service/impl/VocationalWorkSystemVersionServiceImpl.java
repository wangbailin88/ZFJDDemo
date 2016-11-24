package com.ushine.ssh.service.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.common.vo.Paging;
import com.ushine.common.vo.PagingObject;
import com.ushine.dao.IBaseDao;
import com.ushine.ssh.model.VocationalWork;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
import com.ushine.ssh.service.IVocationalWorkSystemVersionService;

/**
 * 业务系统插件接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("vocationalWorkSystemVersionServiceImpl")
public class VocationalWorkSystemVersionServiceImpl implements IVocationalWorkSystemVersionService{
	@Autowired
	private IBaseDao<VocationalWorkSystemVersion, String> baseDao;
	public void saveVocationalWorkSystemVersion(
			VocationalWorkSystemVersion systemVersion) throws Exception {
		// TODO Auto-generated method stub
		baseDao.save(systemVersion);
		
	}
	public PagingObject<VocationalWorkSystemVersion> findVocationalWorkVersionBySystemId(
			String systemId,int nextPage,int size) throws Exception {
		PagingObject<VocationalWorkSystemVersion> pagingObject = new PagingObject<VocationalWorkSystemVersion>();
		DetachedCriteria criteria = DetachedCriteria.forClass(VocationalWorkSystemVersion.class);
		criteria.add(Restrictions.eq("vocationalWorkSystemId", systemId));
		int rowCount = baseDao.getRowCount(criteria);
		Paging paging = new Paging(size, nextPage, rowCount);
		// c ORDER BY c.collectFirstTime DESC
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<VocationalWorkSystemVersion> baseInfos = baseDao.findPagingByCriteria(criteria,
				size, paging.getStartRecord());
		pagingObject.setArray(baseInfos);
		pagingObject.setPaging(paging);
		return pagingObject;
	}
	@Override
	public List<VocationalWorkSystemVersion> findVocationalWorkVersionByStatus(
			String status) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findByProperty(VocationalWorkSystemVersion.class, "status", "0");
	}
	@Override
	public void updateVocationalWorkSystemVersionStatus(
			VocationalWorkSystemVersion vocationalWorkSystemVersion)
			throws Exception {
		// TODO Auto-generated method stub
		baseDao.update(vocationalWorkSystemVersion);
	}

}
