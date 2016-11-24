package com.ushine.ssh.service;

import com.ushine.common.vo.PagingObject;
import com.ushine.ssh.model.DataGetTask;

/**
 * 数据获取任务接口
 * @author wangbailin
 *
 */
public interface IDataGetTaskService {
	/**
	 * 新增数据获取任务
	 * @param dataGetTask
	 * @throws Exception
	 */
	public void saveDataGetTask(DataGetTask dataGetTask)throws Exception;
	/**
	 * 查询数据获取任务
	 * @return
	 * @throws Exception
	 */
	public PagingObject<DataGetTask> findDataGetTask(int nextPage,int size)throws Exception;
}
