package com.ushine.ssh.service;

import java.util.List;

import com.ushine.ssh.model.SubordinateNodeInfo;

/**
 * 下级节点信息接口
 * 接收下级节点注册节点信息
 * 
 * @author wangbailin
 *
 */
public interface ISubordinateNodeInfoService {
	/**
	 * 接收下级节点的注册信息
	 * @param subordinateNodeInfo
	 * @throws Exception
	 */
	public void saveSubordinateNodeInfo(SubordinateNodeInfo subordinateNodeInfo)throws Exception;
	/**
	 * 根据下级节点的nodeCode删除
	 * @param nodeCode
	 * @throws Exception
	 */
	public void deleteSubordinateNodeInfoByNodeCode(String nodeCode)throws Exception;
	/**
	 * 查询状态为0（未提交）的下级节点信息
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<SubordinateNodeInfo> findSubordinateNodeInfoByStatus(String status)throws Exception;
	/**
	 * 查询下级节点信息
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<SubordinateNodeInfo> findSubordinateNodeInfoByStatus()throws Exception;
	/**
	 * 根据id修改状态
	 * @param id
	 * @throws Exception
	 */
	public void updateSubordinateNodeInfoStatusById(SubordinateNodeInfo entityObject)throws Exception;
}
