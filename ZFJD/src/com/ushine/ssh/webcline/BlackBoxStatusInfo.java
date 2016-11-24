
package com.ushine.ssh.webcline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>blackBoxStatusInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="blackBoxStatusInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cpuMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpuName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpuUnilizationRatio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceNmae" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diskAMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diskBMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diskCapacityMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gatherTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hardDiskName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hardDiskUnilizationRatio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manageCenterConnected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memoryMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memoryUnilizationRatio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noTrafficMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="openBoxMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RAIDStatusMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="theAuditSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="theBypassMonitoring" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="theTemperature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toSubmitTheOperation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blackBoxStatusInfo", propOrder = {
    "cpuMonitoring",
    "cpuName",
    "cpuUnilizationRatio",
    "deviceNmae",
    "diskAMonitoring",
    "diskBMonitoring",
    "diskCapacityMonitoring",
    "gatherTime",
    "hardDiskName",
    "hardDiskUnilizationRatio",
    "id",
    "ip",
    "manageCenterConnected",
    "memoryMonitoring",
    "memoryName",
    "memoryUnilizationRatio",
    "noTrafficMonitoring",
    "openBoxMonitoring",
    "raidStatusMonitoring",
    "theAuditSystem",
    "theBypassMonitoring",
    "theTemperature",
    "toSubmitTheOperation"
})
public class BlackBoxStatusInfo {

    protected String cpuMonitoring;
    protected String cpuName;
    protected String cpuUnilizationRatio;
    protected String deviceNmae;
    protected String diskAMonitoring;
    protected String diskBMonitoring;
    protected String diskCapacityMonitoring;
    protected String gatherTime;
    protected String hardDiskName;
    protected String hardDiskUnilizationRatio;
    protected String id;
    protected String ip;
    protected String manageCenterConnected;
    protected String memoryMonitoring;
    protected String memoryName;
    protected String memoryUnilizationRatio;
    protected String noTrafficMonitoring;
    protected String openBoxMonitoring;
    @XmlElement(name = "RAIDStatusMonitoring")
    protected String raidStatusMonitoring;
    protected String theAuditSystem;
    protected String theBypassMonitoring;
    protected String theTemperature;
    protected String toSubmitTheOperation;

    /**
     * ��ȡcpuMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpuMonitoring() {
        return cpuMonitoring;
    }

    /**
     * ����cpuMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpuMonitoring(String value) {
        this.cpuMonitoring = value;
    }

    /**
     * ��ȡcpuName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpuName() {
        return cpuName;
    }

    /**
     * ����cpuName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpuName(String value) {
        this.cpuName = value;
    }

    /**
     * ��ȡcpuUnilizationRatio���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpuUnilizationRatio() {
        return cpuUnilizationRatio;
    }

    /**
     * ����cpuUnilizationRatio���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpuUnilizationRatio(String value) {
        this.cpuUnilizationRatio = value;
    }

    /**
     * ��ȡdeviceNmae���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceNmae() {
        return deviceNmae;
    }

    /**
     * ����deviceNmae���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceNmae(String value) {
        this.deviceNmae = value;
    }

    /**
     * ��ȡdiskAMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiskAMonitoring() {
        return diskAMonitoring;
    }

    /**
     * ����diskAMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiskAMonitoring(String value) {
        this.diskAMonitoring = value;
    }

    /**
     * ��ȡdiskBMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiskBMonitoring() {
        return diskBMonitoring;
    }

    /**
     * ����diskBMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiskBMonitoring(String value) {
        this.diskBMonitoring = value;
    }

    /**
     * ��ȡdiskCapacityMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiskCapacityMonitoring() {
        return diskCapacityMonitoring;
    }

    /**
     * ����diskCapacityMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiskCapacityMonitoring(String value) {
        this.diskCapacityMonitoring = value;
    }

    /**
     * ��ȡgatherTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatherTime() {
        return gatherTime;
    }

    /**
     * ����gatherTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatherTime(String value) {
        this.gatherTime = value;
    }

    /**
     * ��ȡhardDiskName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHardDiskName() {
        return hardDiskName;
    }

    /**
     * ����hardDiskName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardDiskName(String value) {
        this.hardDiskName = value;
    }

    /**
     * ��ȡhardDiskUnilizationRatio���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHardDiskUnilizationRatio() {
        return hardDiskUnilizationRatio;
    }

    /**
     * ����hardDiskUnilizationRatio���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHardDiskUnilizationRatio(String value) {
        this.hardDiskUnilizationRatio = value;
    }

    /**
     * ��ȡid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * ��ȡip���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIp() {
        return ip;
    }

    /**
     * ����ip���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIp(String value) {
        this.ip = value;
    }

    /**
     * ��ȡmanageCenterConnected���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManageCenterConnected() {
        return manageCenterConnected;
    }

    /**
     * ����manageCenterConnected���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManageCenterConnected(String value) {
        this.manageCenterConnected = value;
    }

    /**
     * ��ȡmemoryMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemoryMonitoring() {
        return memoryMonitoring;
    }

    /**
     * ����memoryMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemoryMonitoring(String value) {
        this.memoryMonitoring = value;
    }

    /**
     * ��ȡmemoryName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemoryName() {
        return memoryName;
    }

    /**
     * ����memoryName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemoryName(String value) {
        this.memoryName = value;
    }

    /**
     * ��ȡmemoryUnilizationRatio���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemoryUnilizationRatio() {
        return memoryUnilizationRatio;
    }

    /**
     * ����memoryUnilizationRatio���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemoryUnilizationRatio(String value) {
        this.memoryUnilizationRatio = value;
    }

    /**
     * ��ȡnoTrafficMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoTrafficMonitoring() {
        return noTrafficMonitoring;
    }

    /**
     * ����noTrafficMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoTrafficMonitoring(String value) {
        this.noTrafficMonitoring = value;
    }

    /**
     * ��ȡopenBoxMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenBoxMonitoring() {
        return openBoxMonitoring;
    }

    /**
     * ����openBoxMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenBoxMonitoring(String value) {
        this.openBoxMonitoring = value;
    }

    /**
     * ��ȡraidStatusMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRAIDStatusMonitoring() {
        return raidStatusMonitoring;
    }

    /**
     * ����raidStatusMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRAIDStatusMonitoring(String value) {
        this.raidStatusMonitoring = value;
    }

    /**
     * ��ȡtheAuditSystem���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTheAuditSystem() {
        return theAuditSystem;
    }

    /**
     * ����theAuditSystem���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTheAuditSystem(String value) {
        this.theAuditSystem = value;
    }

    /**
     * ��ȡtheBypassMonitoring���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTheBypassMonitoring() {
        return theBypassMonitoring;
    }

    /**
     * ����theBypassMonitoring���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTheBypassMonitoring(String value) {
        this.theBypassMonitoring = value;
    }

    /**
     * ��ȡtheTemperature���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTheTemperature() {
        return theTemperature;
    }

    /**
     * ����theTemperature���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTheTemperature(String value) {
        this.theTemperature = value;
    }

    /**
     * ��ȡtoSubmitTheOperation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToSubmitTheOperation() {
        return toSubmitTheOperation;
    }

    /**
     * ����toSubmitTheOperation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToSubmitTheOperation(String value) {
        this.toSubmitTheOperation = value;
    }

}
