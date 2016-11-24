package com.ushine.ssh.service;

import java.util.List;

import com.ushine.common.vo.PagingObject;
import com.ushine.ssh.model.VocationalWork;

/**
 * 业务系统接口类
 * @author wangbailin
 *
 */
public interface IVocationalWorkService {
	/**
	 * 新增业务系统数据
	 * @param vocationalWork
	 * @throws Exception
	 */
	public void saveVocationalWork(VocationalWork vocationalWork)throws Exception;
	/**
	 * 根据区域id查询
	 * @param districtId
	 * @return
	 * @throws Excetption
	 */
	public PagingObject<VocationalWork> findVocationalWorkByDistrictId(String districtId,int nextPage,int size)throws Exception;
	/**
	 * 获取未提交的注册系统数据
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<VocationalWork> findVocationalWorkByStatus(String status)throws Exception;
	/**
	 * 修改数据状态
	 * @param vocationalWork
	 * @throws Exception
	 */
	public void updateVocationalWorkStatus(VocationalWork vocationalWork)throws Exception;
	/**
	 * 根据id查询业务信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public VocationalWork findVocationalWorkById(String id)throws Exception;
	public void exSql(String sql)throws Exception;
}
