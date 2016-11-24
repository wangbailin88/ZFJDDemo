package com.ushine.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 数据获取任务实体类
 * @author wangbailin
 *
 */
@Entity
@Table(name = "T_DATA_TASK")
public class DataGetTask {
	private String id;  //id
	private String dataName;//数据名称
	private String administrationArea;//行政区域
	private String vocationalWorkSystem;//业务系统
	private String version;//插件版本
	private String dataType;//数据类型
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String getSchedule;//获取进度
	private String getTiem;//获取时间
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*******************get方法*********************/
	/**
	 * id
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
	 * 数据名称
	 * @return
	 */
	@Column(name = "DATA_NAME", length = 60)
	public String getDataName() {
		return dataName;
	}
	/**
	 * 行政区域
	 * @return
	 */
	@Column(name = "ADMINISTRATION_AREA", length = 60)
	public String getAdministrationArea() {
		return administrationArea;
	}
	/**
	 * 业务系统
	 * @return
	 */
	@Column(name = "VOCATIONAL_WORK_SYSTEM", length = 60)
	public String getVocationalWorkSystem() {
		return vocationalWorkSystem;
	}
	/**
	 * 插件版本
	 * @return
	 */
	@Column(name = "VERSION", length = 60)
	public String getVersion() {
		return version;
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
	 * 开始时间
	 * @return
	 */
	@Column(name="START_TIME",columnDefinition="TIMESTAMP")
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 结束时间
	 * @return
	 */
	@Column(name="END_TIME",columnDefinition="TIMESTAMP")
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 当前进度
	 * @return
	 */
	@Column(name = "GET_SCHEDULE", length = 60)
	public String getGetSchedule() {
		return getSchedule;
	}
	/**
	 * 获取时间
	 * @return
	 */
	@Column(name="GET_TIME",columnDefinition="TIMESTAMP")
	public String getGetTiem() {
		return getTiem;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*************************set方法******************************/
	/**
	 * id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 数据名称
	 * @param dataName
	 */
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	/**
	 * 行政区域
	 * @param administrationArea
	 */
	public void setAdministrationArea(String administrationArea) {
		this.administrationArea = administrationArea;
	}
	/**
	 * 业务系统
	 * @param vocationalWorkSystem
	 */
	public void setVocationalWorkSystem(String vocationalWorkSystem) {
		this.vocationalWorkSystem = vocationalWorkSystem;
	}
	/**
	 * 插件版本
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 数据类型
	 * @param dataType
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * 开始时间
	 * @param startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 结束时间
	 * @param endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 当前进度
	 * @param getSchedule
	 */
	public void setGetSchedule(String getSchedule) {
		this.getSchedule = getSchedule;
	}
	/**
	 * 获取时间
	 * @param getTiem
	 */
	public void setGetTiem(String getTiem) {
		this.getTiem = getTiem;
	}
}
