package com.ushine.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 业务系统实体类
 * @author wangbailin
 *
 */
@Entity
@Table(name = "T_VOCATIONAL_WORK")
public class VocationalWork {
	private String id;   //id
	private String systemName;//系统名称
	private String dataType;  //数据类型
	private String registerTime;   //注册时间
	private String remarks;//备注
	private String administrationDistrictId;//业务系统所属区域id
	private String status = "0";//是否上报给上级节点0：未上报1：已上报
	
	
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
	/**************************get方法********************************/
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
	 * 系统名称
	 * @return
	 */
	@Column(name = "SYSTEM_NAME", length = 60)
	public String getSystemName() {
		return systemName;
	}
	/**
	 * 数据类型
	 * @return
	 */
	@Column(name = "DATA_TYPE", length = 60)
	public String getDataType() {
		return dataType;
	}
	/**
	 * 注册时间
	 * @return
	 */
	@Column(name="REGISTER_TIME",columnDefinition="TIMESTAMP")
	public String getRegisterTime() {
		return registerTime;
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
	 * 业务系统所属区域id
	 * @return
	 */
	@Column(name = "ADMINISTRATION_DISTRICT_ID", length = 32)
	public String getAdministrationDistrictId() {
		return administrationDistrictId;
	}
	
	
	
	
	
	/************************set方法*******************************/
	/**
	 * id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 系统名称
	 * @param systemName
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	/**
	 * 数据类型
	 * @param dataType
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * 注册时间
	 * @param registerTime
	 */
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 备注
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 业务系统所属区域id
	 * @return
	 */
	public void setAdministrationDistrictId(String administrationDistrictId) {
		this.administrationDistrictId = administrationDistrictId;
	}
}
