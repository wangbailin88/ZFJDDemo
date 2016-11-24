package com.ushine.core.service;

import java.util.List;

import com.ushine.common.vo.IJSonHandle;
import com.ushine.common.vo.PagingObject;
import com.ushine.core.po.Person;

/**
 * 人员服务接口，用于对外部提供添加、删除、修改系统中的人员信息的方法
 * 
 * @author franklin
 * @author 熊永涛修改140828
 */
public interface IPersonService extends IJSonHandle<Person> {

	/**
	 * 新建人员
	 * 
	 * @param obj
	 *            Person
	 * @return String Json
	 * @throws Exception
	 */
	public String createNewPersion(Person obj) throws Exception;
	/**
	 * 修改人员信息
	 * @param obj
	 * @return
	 * @throws Exception
	 *2015-7-3
	 */
	public String updatePersonInfo(Person obj) throws Exception;

	/**
	 * 按ID删除人员
	 * 
	 * @param ids
	 *            String[] ids
	 * @return String Json
	 * @throws Exception
	 */
	public String deletePersonById(String[] ids) throws Exception;

	/**
	 * 按ID删除人员
	 * 
	 * @param id
	 *            String id
	 * @return String Json
	 * @throws Exception
	 * @author xyt
	 */
	public String deletePersonById(String id) throws Exception;

	/**
	 * 根据角色id查询人员
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Person> findByRid(String id) throws Exception;

	/**
	 * 修改人员信息
	 * 
	 * @param obj
	 *            Person
	 * @return String Json
	 * @throws Exception
	 */
	public String modifyPersonInfo(Person obj) throws Exception;

	/**
	 * 人员登录成功后，记录登录信息
	 * 
	 * @param id
	 *            String 人员ID
	 * @param loginIP
	 *            String 登录IP
	 * @throws Exception
	 */
	public void modifyPersonLoginInfo(String id, String loginIP)
			throws Exception;

	/**
	 * 按ID查询人员信息
	 * 
	 * @param id
	 *            String
	 * @return Person
	 * @throws Exception
	 */
	public Person findPersonById(String id) throws Exception;

	/**
	 * 通过用户名查找人员
	 * 
	 * @param uName
	 *            String 用户名
	 * @return List<Person>
	 * @throws Exception
	 */
	public List<Person> findPersonByName(String uName) throws Exception;

	/**
	 * 按部门分页查找人员
	 * 
	 * @param deptId
	 *            String 部门id
	 * @param sizePage
	 *            int 每页显示
	 * @param current
	 *            int 当前页
	 * @return PagingObject<Person> 包含分页信息的视图对象
	 * @throws Exception
	 */
	public PagingObject<Person> findPagingPersonByDept(String deptId,
			int sizePage, int current) throws Exception;

	/**
	 * 分页查询查询
	 * 
	 * @param sizePage
	 *            int 每页显示
	 * @param current
	 *            int 当前页
	 * @return PagingObject<Person> 包含分页信息的视图对象
	 * @throws Exception
	 */
	public PagingObject<Person> findPagingPersonAll(int sizePage, int current)
			throws Exception;

	/**
	 * 根据部门id查询人员
	 * 
	 * @param deptId
	 * @return
	 * @throws Exception
	 */
	public List<Person> findByDeptId(String deptId) throws Exception;

	/**
	 * 通过组织ID查询组织下的，所有人员名字
	 * 
	 * @param orgId
	 *            部门ID
	 * @return
	 * @throws Exception
	 */
	public String findPersonNames(String orgId) throws Exception;
	/**
	 * 修改用户密码
	 * @param oldPass 旧密码
	 * @param pass	新密码
	 * @param uid 用户id
	 * @return
	 * @throws Exception
	 */
	public String modifyPassword(String oldPass,String pass,String uid) throws Exception;
	
	/**
	 * 通过身份证号码查找人员
	 * @Description: TODO
	 * @param IDCard 身份证号码
	 * @return
	 * @throws Exception
	 * @author zhangkq
	 * @date 2015-11-13
	 */
	public List<Person> findPersonByIDCard(String IDCard) throws Exception;
	/**
	 * 通过真实姓名查找人员
	 * @Description: TODO
	 * @param trueName 真实姓名
	 * @return
	 * @throws Exception
	 * @author zhangkq
	 * @date 2015-11-13
	 */
	public List<Person> findPersonByTruename(String trueName) throws Exception;
}
