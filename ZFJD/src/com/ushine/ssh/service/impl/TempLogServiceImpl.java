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
import com.ushine.ssh.model.DataGetTask;
import com.ushine.ssh.model.TempLog;
import com.ushine.ssh.service.ITempLogService;
import com.ushine.util.StringUtil;

/**
 * 临时日志接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("tempLogServiceImpl")
public class TempLogServiceImpl implements ITempLogService{
	@Autowired
	private IBaseDao<TempLog, String> baseDao;
	public void saveTempLog(TempLog tempLog) throws Exception {
		// TODO Auto-generated method stub
		baseDao.save(tempLog);
	}
	public PagingObject<TempLog> findTempLog(String systemName,String dataType,int nextPage, int size)
			throws Exception {
		PagingObject<TempLog> pagingObject = new PagingObject<TempLog>();
		DetachedCriteria criteria = DetachedCriteria.forClass(TempLog.class);
		if(!"undefined".equals(dataType) && !StringUtil.isNull(dataType)){
			criteria.add(Restrictions.eq("dataType", dataType));
		}
		if(!"undefined".equals(systemName) && !StringUtil.isNull(systemName)){
			criteria.add(Restrictions.eq("systemName", systemName));
		}
		int rowCount = baseDao.getRowCount(criteria);
		Paging paging = new Paging(size, nextPage, rowCount);
		// c ORDER BY c.collectFirstTime DESC
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<TempLog> baseInfos = baseDao.findPagingByCriteria(criteria,
				size, paging.getStartRecord());
		pagingObject.setArray(baseInfos);
		pagingObject.setPaging(paging);
		return pagingObject;
	}

}
