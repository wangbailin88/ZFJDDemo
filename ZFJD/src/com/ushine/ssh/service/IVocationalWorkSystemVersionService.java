package com.ushine.ssh.service;

import java.util.List;

import com.ushine.common.vo.PagingObject;
import com.ushine.ssh.model.VocationalWorkSystemVersion;

/**
 * 业务系统插件接口类
 * @author wangbailin
 *
 */
public interface IVocationalWorkSystemVersionService {
	/**
	 * 新增业务系统插件方法
	 * @param systemVersion
	 * @throws Exception
	 */
	public void saveVocationalWorkSystemVersion(VocationalWorkSystemVersion systemVersion)throws Exception;
	/**
	 *  根据业务系统id查询业务系统插件
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	public PagingObject<VocationalWorkSystemVersion> findVocationalWorkVersionBySystemId(String systemId,int nextPage,int size)throws Exception;
	/**
	 * 根据状态查询数据
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<VocationalWorkSystemVersion> findVocationalWorkVersionByStatus(String status)throws Exception;
	/**
	 * 修改业务系统插件状态为已上报
	 * @param vocationalWorkSystemVersion
	 * @throws Exception
	 */
	public void updateVocationalWorkSystemVersionStatus(VocationalWorkSystemVersion vocationalWorkSystemVersion)throws Exception;
}
