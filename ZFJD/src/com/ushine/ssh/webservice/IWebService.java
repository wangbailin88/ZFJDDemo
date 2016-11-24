package com.ushine.ssh.webservice;
/**
 * webservice接口
 * 接收下级节点注册信息
 * 接收下级节点黑匣子状态基础信息
 * 
 */
import java.io.File;
import java.util.List;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.ushine.ssh.model.BlackBoxStatusInfo;
import com.ushine.ssh.model.Han;
import com.ushine.ssh.model.SubordinateNodeInfo;
import com.ushine.ssh.model.VocationalWork;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
@WebService
public interface IWebService {
	/**
	 * 接收下级节点注册信息
	 * 1删除接收注册节点编码一样的节点信息
	 * 2新增节点
	 * 
	 * @param subordinateNodeInfo
	 * @return
	 * @throws Exception
	 */
	@WebResult(name = "result")
	public boolean saveSubordinateNodeInfo(@WebParam(name = "str") SubordinateNodeInfo subordinateNodeInfo)throws Exception;
	/**
	 * 接收下级节点的黑匣子状态数据
	 * @param blackBoxStatusInfos
	 * @return
	 * @throws Exception
	 */
	@WebResult(name = "result")
	public boolean sumitBlackBoxData(@WebParam(name = "str")List<BlackBoxStatusInfo> blackBoxStatusInfos)throws Exception;
	/**
	 * 接收下级节点的注册业务系统信息数据
	 * @param blackBoxStatusInfos
	 * @return
	 * @throws Exception
	 */
	@WebResult(name = "result")
	public boolean sumitVocationalWork(@WebParam(name = "str")VocationalWork vocationalWork)throws Exception;
	
}
