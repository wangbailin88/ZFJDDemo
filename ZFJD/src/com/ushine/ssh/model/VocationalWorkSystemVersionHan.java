package com.ushine.ssh.model;

import javax.activation.DataHandler;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * 业务系统插件实体
 * @author wangbailin
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VocationalWorkSystemVersionHan {
	private String id;//id
	private String fileName;//文件名称
	private String filePath;//文件路径行程
	private String vocationalWorkSystemId;//所属业务系统id
	
	private String version;//版本
	private String uploadTime;//上传时间
	private String remarks;//备注
	private String status;//是否上报上级节点，0：未上报，1：已上报
	
	
	
	@XmlMimeType("application/octet-stream")
	private DataHandler dataHandler;  //文件
	private String nodeCode; //存储jar文件夹名称
	public DataHandler getDataHandler() {
		return dataHandler;
	}
	public void setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}
	public String getNodeCode() {
		return nodeCode;
	}
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	/**
	 * 是否上报给上级节点0：未上报1：已上报
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 是否上报给上级节点0：未上报1：已上报
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	/***************get方法*********************/
	/**
	 * ID
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 文件名称
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 版本
	 * @return
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 上传时间
	 * @return
	 */
	public String getUploadTime() {
		return uploadTime;
	}
	/**
	 * 备注
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 文件路径
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 所属业务系统id
	 * @return
	 */
	public String getVocationalWorkSystemId() {
		return vocationalWorkSystemId;
	}
	
	
	
	
	
	
	
	
	/**********************set方法***********************/
	/**
	 * id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 文件名称
	 * @param fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 版本
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 上传时间
	 * @param uploadTime
	 */
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**
	 * 备注
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 文件路径
	 * @param filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 *  所属业务系统id
	 * @param vocationalWorkSystemId
	 */
	public void setVocationalWorkSystemId(String vocationalWorkSystemId) {
		this.vocationalWorkSystemId = vocationalWorkSystemId;
	}
}
