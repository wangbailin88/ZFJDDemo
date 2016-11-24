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
import com.ushine.ssh.service.IVocationalWorkService;

/**
 * 业务系统接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("vocationalWorkServiceImpl")
public class VocationalWorkServiceImpl implements IVocationalWorkService{
	@Autowired
	private IBaseDao<VocationalWork, String> baseDao;
	public void saveVocationalWork(VocationalWork vocationalWork)
			throws Exception {
		// TODO Auto-generated method stub
		baseDao.save(vocationalWork);
	}
	public PagingObject<VocationalWork> findVocationalWorkByDistrictId(String districtId,int nextPage,int size)
			throws Exception {
		// TODO Auto-generated method stub
		PagingObject<VocationalWork> pagingObject = new PagingObject<VocationalWork>();
		DetachedCriteria criteria = DetachedCriteria.forClass(VocationalWork.class);
		criteria.add(Restrictions.eq("administrationDistrictId", districtId));
		int rowCount = baseDao.getRowCount(criteria);
		Paging paging = new Paging(size, nextPage, rowCount);
		// c ORDER BY c.collectFirstTime DESC
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<VocationalWork> baseInfos = baseDao.findPagingByCriteria(criteria,
				size, paging.getStartRecord());
		pagingObject.setArray(baseInfos);
		pagingObject.setPaging(paging);
		return pagingObject;
	}
	@Override
	public List<VocationalWork> findVocationalWorkByStatus(String status)
			throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findByProperty(VocationalWork.class, "status", "0");
	}
	@Override
	public void updateVocationalWorkStatus(VocationalWork vocationalWork)
			throws Exception {
		// TODO Auto-generated method stub
		baseDao.update(vocationalWork);
	}
	@Override
	public VocationalWork findVocationalWorkById(String id) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findById(VocationalWork.class, id);
	}
	@Override
	public void exSql(String sql) throws Exception {
		// TODO Auto-generated method stub
		baseDao.executeSql(sql);
	}

}
