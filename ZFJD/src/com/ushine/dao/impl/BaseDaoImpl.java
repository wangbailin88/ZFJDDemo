package com.ushine.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ushine.dao.IBaseDao;
import com.ushine.ssh.model.BlackBoxStatusInfo;

/**
 * 基础Dao支持接口v3.0实现,当BaseDaoImpl实现方法不能满 足业务需要时,需
 * 要自定义方法执行数据库操作时,可以通过继承BaseDaoAdapter重新实现方法
 * 
 * @author Franklin
 * 
 * @param <T>
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements
		IBaseDao<T, Serializable> {
	private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	public void save(T entityObject) throws Exception {
		logger.debug("保存数据到数据库(" + entityObject.getClass() + ").");
		getHibernateTemplate().save(entityObject);
	}
	
	public void update(T entityObject) throws Exception {
		logger.debug("更新指定数据操作(" + entityObject.getClass() + ").");
		getHibernateTemplate().update(entityObject);
	}

	public void saveOrUpdate(T entityObject) throws Exception {
		logger.debug("保存或更新数据操作(" + entityObject.getClass() + ").");
		getHibernateTemplate().saveOrUpdate(entityObject);
	}

	public void delete(T entityObject) throws Exception {
		logger.debug("删除指定类型数据(" + entityObject.getClass() + ").");
		getHibernateTemplate().delete(entityObject);
	}

	public void deleteById(Class<T> entityClass, Serializable[] ids)
			throws Exception {
		if (ids != null) {
			logger.debug("批量删除数据(ID="+ids+").");
			// 循环删除数据, 不适合大数据使用
			for (Serializable id : ids) {
				getHibernateTemplate().delete(getHibernateTemplate().get(entityClass, id));
			}
		} else {
			logger.error("数据ID为NULL,不能执行删除操作.");
		}
	}
	public void deleteById(Class<T> entityClass, String id)
			throws Exception {
		if (id != null) {
			logger.debug("删除数据(ID="+id+").");
			// 循环删除数据, 不适合大数据使用
			getHibernateTemplate().delete(getHibernateTemplate().get(entityClass, id));
		} else {
			logger.error("数据ID为NULL,不能执行删除操作.");
		}
	}
	public void deleteByProperty(Class<T> entityClass, String propertyName,
			Object value) throws Exception {
		logger.debug("删除符合条件的数据("+propertyName+"="+value+").");
		String queryString = "delete " + entityClass.getName() + " where " + propertyName + " = ?";
		getHibernateTemplate().bulkUpdate(queryString, value);
	}

	public void deleteAll(final Class<T> entityClass) throws Exception {
		logger.debug("删除指定类型(" + entityClass + ")的全部数据");
		getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) 
					throws HibernateException, SQLException {
				session.createQuery ("delete from " + entityClass.getName() + " t").executeUpdate();
				return null;
			}
		});
	}

	public T findById(Class<T> entityClass, Serializable id) throws Exception {
		if (id != null) {
			logger.debug("查询ID=" + id + "数据.");
			return getHibernateTemplate().get(entityClass, id);
		} else {
			logger.error("数据ID为NULL，不能执行查询操作.");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> findByProperty(Class<T> entityClass, String propertyName,
			Object value) throws Exception {
		logger.debug("查询数据，条件:(属性: " + propertyName + "值: " + value + ").");
		
		String queryString = "from " + entityClass.getName() + " as model where model." + 
					propertyName + "= ?";
		return getHibernateTemplate().find(queryString, value);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) throws Exception {
		logger.debug("DetachedCriteria条件查询数据.");
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClass) throws Exception {
		logger.debug("查询全部数据(" + entityClass + ").");
		String queryString = "from " + entityClass.getName();
		return getHibernateTemplate().find(queryString);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByHql(String hql) throws Exception {
		logger.debug("通过HQL语句查询(" + hql + ").");
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	public List findBySql(final String sql)
			throws Exception {
		logger.debug("SQL语句查询(" + sql + ").");
		return (List) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).list();
			}
		});
	}
	@SuppressWarnings("unchecked")
	public List findBySql(final String sql,final Class<BlackBoxStatusInfo> entityClass)
			throws Exception {
		logger.debug("SQL语句查询(" + sql + ").");
		return (List) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).addEntity(entityClass).list();
			}
		});
	}
	@SuppressWarnings("unchecked")
	public Object getRows(final String sql)
			throws Exception {
		logger.debug("SQL语句查询(" + sql + ").");
		return (Object) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).list().get(0);
			}
		});
	}


	@SuppressWarnings("unchecked")
	public List<T> findPaging(Class<T> entityClass, int sizePage,
			int startRecord) throws Exception {
		logger.debug("分页查询(" + entityClass + ")，每页(" + sizePage + ")记录，起始记录(" + startRecord + ").");
		return (List<T>) getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass), startRecord, sizePage);
	}

	@SuppressWarnings("unchecked")
	public List<T> findPagingByCriteria(DetachedCriteria criteria,
			int sizePage, int startRecord) throws Exception {
		logger.debug("DetachedCriteria分页查询，每页(" + sizePage + ")记录，起始记录(" + startRecord + ").");
		return (List<T>) getHibernateTemplate().findByCriteria(criteria,
					startRecord, sizePage);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findPagingByHql(final String hql, final int sizePage, final int startRecord)
			throws Exception {
		logger.debug("HQL语句(" + hql + ")分页查询，每页(" + sizePage + ")记录，起始记录(" + startRecord + ").");
		return (List<T>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery (hql).setFirstResult (startRecord).setMaxResults (sizePage);
                return query.list();
            }
		});
	}

	public int getRowCount(Class<T> entityClass) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.setProjection(Projections.rowCount());
		int count =  (Integer) getHibernateTemplate().findByCriteria(criteria).get(0);
		logger.debug("对(" + entityClass + ")进行Count操作，共有(" + count + ")记录.");
		return count;
	}

	public int getRowCount(DetachedCriteria criteria) throws Exception {
		criteria.setProjection(Projections.rowCount());
		int count = (Integer) getHibernateTemplate().findByCriteria(criteria).get(0);
		logger.debug("(DetachedCriteria条件)Count操作，共有(" + count + ")记录.");
		return count;
	}

	/*
	 * 该方法只做基本实现,根据业务可继承重写方法
	 * (non-Javadoc)
	 * @see com.hj.dao.IBaseDao#executeHql(java.lang.String)
	 */
	public Object executeHql(final String hql) throws Exception {
		logger.debug("执行HQL语句(" + hql + ").");
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, 
						SQLException {
				return session.createQuery(hql).executeUpdate();
			}
		});
	}

	/*
	 * 该方法只做基本实现,根据业务可继承重写方法
	 * (non-Javadoc)
	 * @see com.hj.dao.IBaseDao#executeSql(java.lang.String)
	 */
	public Object executeSql(final String sql) throws Exception {
		logger.debug("执行SQL语句(" + sql + ").");
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, 
						SQLException {
				
				return session.createSQLQuery(sql).executeUpdate();
			}
		});
	}
	
	
	/*
	 * 该方法只做基本实现,根据业务可继承重写方法
	 * (non-Javadoc)
	 * @see com.hj.dao.IBaseDao#executeSql(java.lang.String)
	 */
	public Object executeSql1(final String sql) throws Exception {
		logger.debug("执行SQL语句(" + sql + ").");
		return getHibernateTemplate().execute(new HibernateCallback<Object>() {
			public Object doInHibernate(Session session) throws HibernateException, 
						SQLException {
				session.createSQLQuery(sql).executeUpdate();
				Transaction tx = session.beginTransaction();
				tx.commit();
				return null;
			}
		});
	}
	
}