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
import com.ushine.ssh.model.VocationalWork;
import com.ushine.ssh.service.IDataGetTaskService;

/**
 * 数据获取任务接口实现类
 * @author wangbailin
 *
 */
@Transactional
@Service("dataGetTaskServiceImpl")
public class DataGetTaskServiceImpl implements IDataGetTaskService{
	@Autowired
	private IBaseDao<DataGetTask, String> baseDao;

	public void saveDataGetTask(DataGetTask dataGetTask) throws Exception {
		// TODO Auto-generated method stub
		baseDao.save(dataGetTask);
	}

	public PagingObject<DataGetTask> findDataGetTask(int nextPage, int size)
			throws Exception {
		PagingObject<DataGetTask> pagingObject = new PagingObject<DataGetTask>();
		DetachedCriteria criteria = DetachedCriteria.forClass(DataGetTask.class);
		int rowCount = baseDao.getRowCount(criteria);
		Paging paging = new Paging(size, nextPage, rowCount);
		// c ORDER BY c.collectFirstTime DESC
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<DataGetTask> baseInfos = baseDao.findPagingByCriteria(criteria,
				size, paging.getStartRecord());
		pagingObject.setArray(baseInfos);
		pagingObject.setPaging(paging);
		return pagingObject;
	}
	
}
