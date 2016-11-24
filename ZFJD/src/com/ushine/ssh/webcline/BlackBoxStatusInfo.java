
package com.ushine.ssh.webcline;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>blackBoxStatusInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取cpuMonitoring属性的值。
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
     * 设置cpuMonitoring属性的值。
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
     * 获取cpuName属性的值。
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
     * 设置cpuName属性的值。
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
     * 获取cpuUnilizationRatio属性的值。
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
     * 设置cpuUnilizationRatio属性的值。
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
     * 获取deviceNmae属性的值。
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
     * 设置deviceNmae属性的值。
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
     * 获取diskAMonitoring属性的值。
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
     * 设置diskAMonitoring属性的值。
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
     * 获取diskBMonitoring属性的值。
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
     * 设置diskBMonitoring属性的值。
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
     * 获取diskCapacityMonitoring属性的值。
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
     * 设置diskCapacityMonitoring属性的值。
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
     * 获取gatherTime属性的值。
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
     * 设置gatherTime属性的值。
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
     * 获取hardDiskName属性的值。
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
     * 设置hardDiskName属性的值。
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
     * 获取hardDiskUnilizationRatio属性的值。
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
     * 设置hardDiskUnilizationRatio属性的值。
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
     * 获取id属性的值。
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
     * 设置id属性的值。
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
     * 获取ip属性的值。
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
     * 设置ip属性的值。
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
     * 获取manageCenterConnected属性的值。
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
     * 设置manageCenterConnected属性的值。
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
     * 获取memoryMonitoring属性的值。
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
     * 设置memoryMonitoring属性的值。
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
     * 获取memoryName属性的值。
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
     * 设置memoryName属性的值。
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
     * 获取memoryUnilizationRatio属性的值。
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
     * 设置memoryUnilizationRatio属性的值。
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
     * 获取noTrafficMonitoring属性的值。
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
     * 设置noTrafficMonitoring属性的值。
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
     * 获取openBoxMonitoring属性的值。
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
     * 设置openBoxMonitoring属性的值。
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
     * 获取raidStatusMonitoring属性的值。
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
     * 设置raidStatusMonitoring属性的值。
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
     * 获取theAuditSystem属性的值。
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
     * 设置theAuditSystem属性的值。
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
     * 获取theBypassMonitoring属性的值。
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
     * 设置theBypassMonitoring属性的值。
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
     * 获取theTemperature属性的值。
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
     * 设置theTemperature属性的值。
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
     * 获取toSubmitTheOperation属性的值。
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
     * 设置toSubmitTheOperation属性的值。
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
