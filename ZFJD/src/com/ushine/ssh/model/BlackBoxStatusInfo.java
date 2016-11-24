package com.ushine.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 本机黑盒状态信息实体
 * 用于封装黑盒状体信息
 * @author wangbailin
 *
 */
@Entity
@Table(name = "T_BLACK_BOX_STATUS_INFO")
public class BlackBoxStatusInfo {
	private String id;//id
	private String ip;//获取黑盒信息的ip
	private String deviceNmae;//设备名称
	private String cpuName;//cpu名称
	private String cpuUnilizationRatio;//cpu使用率
	private String memoryName;//内存名称
	private String memoryUnilizationRatio;//内存使用率
	private String hardDiskName;//硬盘名称
	private String hardDiskUnilizationRatio;//硬盘使用率
	private String theTemperature;//当前温度
	
	
	//状态1:正常 2:异常   等等
	//状态
	private String manageCenterConnected;//管理中心连通状态  
	private String theAuditSystem;//审计系统状态
	private String RAIDStatusMonitoring;//RAID状态监测状态
	private String toSubmitTheOperation;//报送操作日志状态
	private String openBoxMonitoring;//开箱监测状态
	private String theBypassMonitoring;//旁路监测状态
	private String cpuMonitoring;//cpu状态
	private String noTrafficMonitoring;//无流量监测
	private String memoryMonitoring;//内存监测
	private String diskAMonitoring;//磁盘A监测
	private String diskBMonitoring;//磁盘B监测
	private String diskCapacityMonitoring;//磁盘容量监测
	private String gatherTime;//当前信息采集时间
	
	
	
	
	
	
	
	
	
	
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
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 当前信息采集时间
	 * @return
	 */
	@Column(name = "GATHER_TIME", columnDefinition = "TIMESTAMP")
	public String getGatherTime() {
		return gatherTime;
	}
	/**
	 * 当前信息采集时间
	 * @param gatherTime
	 */
	public void setGatherTime(String gatherTime) {
		this.gatherTime = gatherTime;
	}
	/*********************get,set方法******************************/
	/**
	 * 获取黑盒信息的ip
	 * @return
	 */
	@Column(name = "IP", length = 60)
	public String getIp() {
		return ip;
	}
	/**
	 * 获取黑盒信息的ip
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 设备名称
	 * @return
	 */
	@Column(name = "DEVICE_NAME", length = 60)
	public String getDeviceNmae() {
		return deviceNmae;
	}
	/**
	 * 设备名称
	 * @param deviceNmae
	 */
	public void setDeviceNmae(String deviceNmae) {
		this.deviceNmae = deviceNmae;
	}
	/**
	 * cpu名称
	 * @return
	 */
	@Column(name = "CPU_NAME", length = 60)
	public String getCpuName() {
		return cpuName;
	}
	/**
	 * cpu名称
	 * @param cpuName
	 */
	public void setCpuName(String cpuName) {
		this.cpuName = cpuName;
	}
	/**
	 * cpu使用率
	 * @return
	 */
	@Column(name = "CPU_UNILIZATION_RATIO", length = 60)
	public String getCpuUnilizationRatio() {
		return cpuUnilizationRatio;
	}
	/**
	 * cpu使用率
	 * @param cpuUnilizationRatio
	 */
	public void setCpuUnilizationRatio(String cpuUnilizationRatio) {
		this.cpuUnilizationRatio = cpuUnilizationRatio;
	}
	/**
	 * 内存名称
	 * @return
	 */
	@Column(name = "MEMORY_NAME", length = 60)
	public String getMemoryName() {
		return memoryName;
	}
	/**
	 * 内存名称
	 * @param memoryName
	 */
	public void setMemoryName(String memoryName) {
		this.memoryName = memoryName;
	}
	/**
	 * 内存使用率
	 * @return
	 */
	@Column(name = "MEMORY_UNILIZATION_RATIO", length = 60)
	public String getMemoryUnilizationRatio() {
		return memoryUnilizationRatio;
	}
	/**
	 * 内存使用率
	 * @param memoryUnilizationRatio
	 */
	public void setMemoryUnilizationRatio(String memoryUnilizationRatio) {
		this.memoryUnilizationRatio = memoryUnilizationRatio;
	}
	/**
	 * 硬盘名称
	 * @return
	 */
	@Column(name = "HARD_DISK_NAME", length = 60)
	public String getHardDiskName() {
		return hardDiskName;
	}
	/**
	 * 硬盘名称
	 * @param hardDiskName
	 */
	public void setHardDiskName(String hardDiskName) {
		this.hardDiskName = hardDiskName;
	}
	/**
	 * 硬盘使用率
	 * @return
	 */
	@Column(name = "HARD_DISK_UNILIZATION_RATIO", length = 60)
	public String getHardDiskUnilizationRatio() {
		return hardDiskUnilizationRatio;
	}
	/**
	 * 硬盘使用率
	 * @param hardDiskUnilizationRatio
	 */
	public void setHardDiskUnilizationRatio(String hardDiskUnilizationRatio) {
		this.hardDiskUnilizationRatio = hardDiskUnilizationRatio;
	}
	/**
	 * 
	 * @return
	 */
	@Column(name = "THE_TEMPERATURE", length = 60)
	public String getTheTemperature() {
		return theTemperature;
	}
	/**
	 * 当前温度
	 * @param theTemperature
	 */
	public void setTheTemperature(String theTemperature) {
		this.theTemperature = theTemperature;
	}
	/**
	 * 
	 * @return
	 */
	@Column(name = "MANAGE_CENTER_CONNECTED", length = 60)
	public String getManageCenterConnected() {
		return manageCenterConnected;
	}
	/**
	 * 管理中心连通状态  
	 * @param manageCenterConnected
	 */
	public void setManageCenterConnected(String manageCenterConnected) {
		this.manageCenterConnected = manageCenterConnected;
	}
	/**
	 * 审计系统状态
	 * @return
	 */
	@Column(name = "THE_AUDIT_SYSTEM", length = 60)
	public String getTheAuditSystem() {
		return theAuditSystem;
	}
	/**
	 * 审计系统状态
	 * @param theAuditSystem
	 */
	public void setTheAuditSystem(String theAuditSystem) {
		this.theAuditSystem = theAuditSystem;
	}
	/**
	 * RAID状态监测状态
	 * @return
	 */
	@Column(name = "RAID_STATUS_MONITORING", length = 60)
	public String getRAIDStatusMonitoring() {
		return RAIDStatusMonitoring;
	}
	/**
	 * RAID状态监测状态
	 * @param rAIDStatusMonitoring
	 */
	public void setRAIDStatusMonitoring(String rAIDStatusMonitoring) {
		RAIDStatusMonitoring = rAIDStatusMonitoring;
	}
	/**
	 * 报送操作日志状态
	 * @return
	 */
	@Column(name = "GET_TO_SUBMIT_THE_OPERATION", length = 60)
	public String getToSubmitTheOperation() {
		return toSubmitTheOperation;
	}
	/**
	 * 报送操作日志状态
	 * @param toSubmitTheOperation
	 */
	public void setToSubmitTheOperation(String toSubmitTheOperation) {
		this.toSubmitTheOperation = toSubmitTheOperation;
	}
	/**
	 * 开箱监测状态
	 * @return
	 */
	@Column(name = "OPEN_BOX_MONITORING", length = 60)
	public String getOpenBoxMonitoring() {
		return openBoxMonitoring;
	}
	/**
	 * 开箱监测状态
	 * @param openBoxMonitoring
	 */
	public void setOpenBoxMonitoring(String openBoxMonitoring) {
		this.openBoxMonitoring = openBoxMonitoring;
	}
	/**
	 * 旁路监测状态
	 * @return
	 */
	@Column(name = "THE_BY_PASS_MONITORING", length = 60)
	public String getTheBypassMonitoring() {
		return theBypassMonitoring;
	}
	/**
	 * 旁路监测状态
	 * @param theBypassMonitoring
	 */
	public void setTheBypassMonitoring(String theBypassMonitoring) {
		this.theBypassMonitoring = theBypassMonitoring;
	}
	/**
	 * cpu状态
	 * @return
	 */
	@Column(name = "CPU_MONITORING", length = 60)
	public String getCpuMonitoring() {
		return cpuMonitoring;
	}
	/**
	 * cpu状态
	 * @param cpuMonitoring
	 */
	public void setCpuMonitoring(String cpuMonitoring) {
		this.cpuMonitoring = cpuMonitoring;
	}
	/**
	 * 无流量监测
	 * @return
	 */
	@Column(name = "NO_TRAFFIC_MONITORING", length = 60)
	public String getNoTrafficMonitoring() {
		return noTrafficMonitoring;
	}
	/**
	 * 无流量监测
	 * @param noTrafficMonitoring
	 */
	public void setNoTrafficMonitoring(String noTrafficMonitoring) {
		this.noTrafficMonitoring = noTrafficMonitoring;
	}
	/**
	 * 内存监测
	 * @return
	 */
	@Column(name = "MEMORY_MONITORING", length = 60)
	public String getMemoryMonitoring() {
		return memoryMonitoring;
	}
	/**
	 * 内存监测
	 * @param memoryMonitoring
	 */
	public void setMemoryMonitoring(String memoryMonitoring) {
		this.memoryMonitoring = memoryMonitoring;
	}
	/**
	 * 磁盘A监测
	 * @return
	 */
	@Column(name = "DISK_A_MONITORING", length = 60)
	public String getDiskAMonitoring() {
		return diskAMonitoring;
	}
	/**
	 * 磁盘A监测
	 * @param diskAMonitoring
	 */
	public void setDiskAMonitoring(String diskAMonitoring) {
		this.diskAMonitoring = diskAMonitoring;
	}
	/**
	 * 磁盘B监测
	 * @return
	 */
	@Column(name = "DISK_B_MONITORING", length = 60)
	public String getDiskBMonitoring() {
		return diskBMonitoring;
	}
	/**
	 * 磁盘B监测
	 * @param diskBMonitoring
	 */
	public void setDiskBMonitoring(String diskBMonitoring) {
		this.diskBMonitoring = diskBMonitoring;
	}
	/**
	 * 磁盘容量监测
	 * @return
	 */
	@Column(name = "DISK_CAPACITY_MONITORING", length = 60)
	public String getDiskCapacityMonitoring() {
		return diskCapacityMonitoring;
	}
	/**
	 * 磁盘容量监测
	 * @param diskCapacityMonitoring
	 */
	public void setDiskCapacityMonitoring(String diskCapacityMonitoring) {
		this.diskCapacityMonitoring = diskCapacityMonitoring;
	}
	
	
}
