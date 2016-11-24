package com.ushine.ssh.webservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ushine.ssh.model.BlackBoxStatusInfo;
import com.ushine.ssh.model.Han;
import com.ushine.ssh.model.SubordinateNodeInfo;
import com.ushine.ssh.model.ThisNodeInfo;
import com.ushine.ssh.model.VocationalWork;
import com.ushine.ssh.model.VocationalWorkSystemVersion;
import com.ushine.ssh.service.IBlackBoxStatusInfoService;
import com.ushine.ssh.service.ISubordinateNodeInfoService;
import com.ushine.ssh.service.IThisNodeInfoService;
import com.ushine.ssh.service.IVocationalWorkService;
import com.ushine.ssh.service.IVocationalWorkSystemVersionService;
import com.ushine.util.StringUtil;

/**
 * webservice接口实现类
 * @author wangbailin
 *
 */
public class WebServiceImpl implements IWebService{
	@Autowired
	private ISubordinateNodeInfoService subordinateNodeInfoService;
	@Autowired
	private IBlackBoxStatusInfoService blackBoxStatusInfoService;
	@Autowired
	private IVocationalWorkService vocationalWorkService;
	@Resource
	private  WebServiceContext context;
	@Override
	@WebResult(name = "result")
	public boolean saveSubordinateNodeInfo(SubordinateNodeInfo subordinateNodeInfo){
		try {
			//根据nodeCode删除下级节点信息
			subordinateNodeInfoService.deleteSubordinateNodeInfoByNodeCode(subordinateNodeInfo.getNodeCode());
			//将接收到的下级注册节点入库
			subordinateNodeInfoService.saveSubordinateNodeInfo(subordinateNodeInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	@WebResult(name = "result")
	public boolean sumitBlackBoxData(
			@WebParam(name = "str") List<BlackBoxStatusInfo> blackBoxStatusInfos)
			throws Exception {
		// TODO Auto-generated method stub
		for (BlackBoxStatusInfo blackBoxStatusInfo : blackBoxStatusInfos) {
			blackBoxStatusInfo.setGatherTime(StringUtil.dates());
			blackBoxStatusInfoService.saveBlackBoxStatusInfo(blackBoxStatusInfo);
		}
		return true;
	}
	@Override
	@WebResult(name = "result")
	public boolean sumitVocationalWork(
			@WebParam(name = "str") VocationalWork vocationalWork)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(" INSERT INTO T_VOCATIONAL_WORK (ID,ADMINISTRATION_DISTRICT_ID,DATA_TYPE,REGISTER_TIME,REMARKS,SYSTEM_NAME,STATUS) ");
		sb.append(" VALUES(");
		sb.append("'"+vocationalWork.getId()+"',");
		sb.append("'"+vocationalWork.getAdministrationDistrictId()+"',");
		sb.append("'"+vocationalWork.getDataType()+"',");
		sb.append("'"+vocationalWork.getRegisterTime()+"',");
		sb.append("'"+vocationalWork.getRemarks()+"',");
		sb.append("'"+vocationalWork.getSystemName()+"',");
		sb.append("'"+vocationalWork.getStatus()+"'");
		sb.append(")");
		
		
		vocationalWorkService.exSql(sb.toString());
		return true;
	}
}
