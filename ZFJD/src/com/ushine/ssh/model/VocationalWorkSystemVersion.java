package com.ushine.ssh.model;

import javax.activation.DataHandler;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 业务系统插件实体
 * @author wangbailin
 *
 */
@Entity
@Table(name = "T_VOCATIONAL_WORK_SYSTEM_VERSION")
public class VocationalWorkSystemVersion {
	private String id;//id
	private String fileName;//文件名称
	private String filePath;//文件路径行程
	private String vocationalWorkSystemId;//所属业务系统id
	
	private String version;//版本
	private String uploadTime;//上传时间
	private String remarks;//备注
	private String status;//是否上报上级节点，0：未上报，1：已上报
	/**
	 * 是否上报给上级节点0：未上报1：已上报
	 * @return
	 */
	@Column(name = "STATUS", length = 60)
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
	@Id
	@GenericGenerator(name = "uId", strategy = "uuid.hex")
	@GeneratedValue(generator = "uId")
	@Column(name = "ID", length = 32)
	public String getId() {
		return id;
	}
	/**
	 * 文件名称
	 * @return
	 */
	@Column(name = "FILE_NAME", length = 60)
	public String getFileName() {
		return fileName;
	}
	/**
	 * 版本
	 * @return
	 */
	@Column(name = "VERSION", length = 60)
	public String getVersion() {
		return version;
	}
	/**
	 * 上传时间
	 * @return
	 */
	@Column(name="UPLOAD_TIME",columnDefinition="TIMESTAMP")
	public String getUploadTime() {
		return uploadTime;
	}
	/**
	 * 备注
	 * @return
	 */
	@Column(name = "REMARKS", length = 20000)
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 文件路径
	 * @return
	 */
	@Column(name = "FILE_PATH", length = 1000)
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 所属业务系统id
	 * @return
	 */
	@Column(name = "VOCATIONAL_WORK_SYSTEM_ID", length = 60)
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
