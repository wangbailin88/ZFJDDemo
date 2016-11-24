package com.ushine.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ushine.ssh.model.BlackBoxStatusInfo;


/**
 * 基础DAO支持接口v3.0,所有程序数据的CRUD都通过该接口操作（替换IBaseDaoSupport接口操作）
 * com.hj.dao.impl.BaseDaoImpl实现方法不能满足业务需要时,需要自定义方法执行数据库操作时
 * ,可以通过继承com.hj.dao.BaseDaoAdapter重新实现方法
 * 
 * @author {HJ}Franklin
 *
 * @param <T>
 * @param <id>
 */
public interface IBaseDao<T, id extends Serializable> {

	/**
	 * 保持数据
	 * @param entityObject T 实体对象
	 * @throws Exception
	 */
	public void save(T entityObject) throws Exception; 
	
	/**
	 * 更新数据
	 * @param entityObject T 实体对象
	 * @throws Exception
	 */
	public void update(T entityObject) throws Exception;
	
	/**
	 * 保持更新数据, 当数据库中没有数据时执行Save, 有数据时执行Update
	 * @param entityObject T 实体对象
	 * @throws Exception
	 */
	public void saveOrUpdate(T entityObject) throws Exception;
	
	/**
	 * 删除实体对象相应的持久数据
	 * @param entityObject T 实体对象
	 * @throws Exception
	 */
	public void delete(T entityObject) throws Exception;
	
	/**
	 * 根据ID批量删除持久数据, 该方法采用循环删除方法, 不能用于大数据删除
	 * @param entityClass Class<T> 实体类
	 * @param ids Serializable[] 数据ID
	 * @throws Exception
	 */
	public void deleteById(Class<T> entityClass, Serializable[] ids) throws Exception;
	/**
	 * 根据id删除对象
	 * @param entityClass
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(Class<T> entityClass, String id)throws Exception;
	/**
	 * 删除符合指定属性条件的数据
	 * @param entityClass Class<T> 实体类
	 * @param propertyName String 属性名称
	 * @param value Object 比较的值
	 * @throws Exception
	 */
	public void deleteByProperty(Class<T> entityClass, String propertyName, Object value) throws Exception;
	
	/**
	 * 删除指定类型的全部数据,该方法采用bulkUpdate方式,清除缓存数据(包括一级缓存和二级缓存) 
	 * @param entityClass Class<T> 实体类
	 * @throws Exception
	 */
	public void deleteAll(Class<T> entityClass) throws Exception;
	
	/**
	 * 通过ID查询指定数据
	 * @param entityClass
	 * @param id Serializable 数据ID
	 * @return T 数据对象
	 * @throws Exception
	 */
	public T findById(Class<T> entityClass, Serializable id) throws Exception;
	
	/**
	 * 根据对象属性（字段）条件查询数据
	 * @param entityClass Class<T> 实体类
	 * @param propertyName String 属性名称
	 * @param value Object 比较的值
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) throws Exception;
	
	/**
	 * 根据DetachedCriteria条件查询数据
	 * @param criteria DetachedCriteria 动态条件
	 * ***************************************************************
	 | Restrictions.eq      等於
     | Restrictions.allEq   使用Map，使用key/value進行多個等於的比對
     | Restrictions.gt      大於 >
     | Restrictions.ge      大於等於 >=
     | Restrictions.lt      小於 <
     | Restrictions.le      小於等於 <=
     | Restrictions.between 對應SQL的BETWEEN子句
     | Restrictions.like    對應SQL的LIKE子句
     | Restrictions.in      對應SQL的in子句
     | Restrictions.and     AND關係
     | Restrictions.or      OR關係
     | Restrictions.sqlRestriction SQL限定查詢
     ***************************************************************
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List<T> findByCriteria(DetachedCriteria criteria) throws Exception;
	
	/**
	 * 查询指定类型的全部数据
	 * @param entityClass Class<T> 实体类
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List<T> findAll(Class<T> entityClass) throws Exception;
	
	/**
	 * 根据HQL语句查询数据（当需要自定义查询条件时使用）
	 * （在BaseDaoImpl类中该方法实现返回null,如果有需要可以通过继承BaseDaoAdapter重新实现）
	 * 
	 * @param hql String 指定的HQL语句
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List<T> findByHql(String hql) throws Exception;
	
	/**
	 * 通过SQL语句查询数据, 必须按照指定标准格式, 否则抛出异常报错 
	 * （在BaseDaoImpl类中该方法实现返回null,如果有需要可以通过继承BaseDaoAdapter重新实现）
	 * 
	 * @param sql String SQL语句
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List findBySql(String sql) throws Exception;
	public List findBySql(String sql,final Class<BlackBoxStatusInfo> entityClass) throws Exception;
	/**
	 * 根据sql语句查询数据的总条数
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Object getRows(String sql) throws Exception;
	/**
	 * 分页查询指定类型的数据
	 * @param entityClass Class<T> 实体类
	 * @param sizePage int 单页数据量
	 * @param startRecord int 起始记录的序号
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List<T> findPaging(Class<T> entityClass, int sizePage, int startRecord) throws Exception;
	
	/**
	 * 根据动态条件分页查询指定类型的数据
	 * @param criteria DetachedCriteria 动态条件
	 * @param sizePage int 单页数据量
	 * @param startRecord int 起始记录的序号
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List<T> findPagingByCriteria(DetachedCriteria criteria, int sizePage, int startRecord) throws Exception;
	
	/**
	 * 根据HQL语句分页查询数据
	 * @param hql String 指定的HQL语句
	 * @param sizePage int 单页数据量
	 * @param startRecord int 起始记录的序号
	 * @return List<T> 数据对象集合
	 * @throws Exception
	 */
	public List<T> findPagingByHql(String hql, int sizePage, int startRecord) throws Exception;
	
	/**
	 * 统计指定类型数据的记录总量
	 * @param entityClass Class<T> 实体类
	 * @return int 记录总条数
	 * @throws Exception
	 */
	public int getRowCount(Class<T> entityClass) throws Exception;
	
	/**
	 * 根据动态条件统计指定类型数据的记录总量, 可以加入DetachedCriteria动态条件
	 * @param DetachedCriteria criteria
	 *******************************************************************
	 | Projections:
	 | avg(String propertyName)：计算属性字段的平均值。
     | count(String propertyName)：统计一个属性在结果中出现的次数。
     | countDistinct(String propertyName)：统计属性包含的不重复值的数量。
     | max(String propertyName)：计算属性值的最大值。
     | min(String propertyName)：计算属性值的最小值。
     | sum(String propertyName)：计算属性值的总和。
     *******************************************************************
	 * @return int 记录总条数
	 * @throws Exception
	 */
	public int getRowCount(DetachedCriteria criteria) throws Exception;
	
	/**
	 * 执行指定的HQL语句（当需要自定义执行数据库操作时使用）
	 * （在BaseDaoImpl类中该方法实现返回null,如果有需要可以通过继承BaseDaoAdapter重新实现）
	 * 
	 * @param hql String 指定的HQL语句
	 * @return Object 返回结果
	 * @throws Exception
	 */
	public Object executeHql(String hql) throws Exception;
	
	/**
	 * 执行指定的SQL语句（当需要自定义执行数据库操作时使用）
	 * （在BaseDaoImpl类中该方法实现返回null,如果有需要可以通过继承BaseDaoAdapter重新实现）
	 * 
	 * @param hql String 指定的HQL语句
	 * @return Object 返回结果
	 * @throws Exception
	 */
	public Object executeSql(String sql) throws Exception;
	
	/**
	 * 执行指定的SQL语句（当需要自定义执行数据库操作时使用）
	 * （在BaseDaoImpl类中该方法实现返回null,如果有需要可以通过继承BaseDaoAdapter重新实现）
	 * 
	 * @param hql String 指定的HQL语句
	 * @return Object 返回结果
	 * @throws Exception
	 */
	public Object executeSql1(String sql) throws Exception;
	
}
