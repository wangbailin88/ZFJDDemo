package com.ushine.ssh.service;

import com.ushine.common.vo.PagingObject;
import com.ushine.ssh.model.TempLog;

/**
 * 临时日志接口类
 * @author wangbailin
 *
 */
public interface ITempLogService {
	/**
	 * 新增临时日志数据
	 * @param tempLog
	 * @throws Exception
	 */
	public void saveTempLog(TempLog tempLog)throws Exception;
	/**
	 * 查询临时日志数据
	 * @param nextPage
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public PagingObject<TempLog> findTempLog(String systemName,String dataType,int nextPage,int size)throws Exception;
}
