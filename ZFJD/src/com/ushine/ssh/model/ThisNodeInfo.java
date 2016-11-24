package com.ushine.ssh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 本机节点信息实体
 * @author wangbailin
 *
 */
@Entity
@Table(name = "T_THIS_NODE_INFO")
public class ThisNodeInfo {
	//节点信息
	private String id;
	private String nodeCode;//节点编码
	private String nodeName;//节点名称
	private String nodeIp;//节点ip
	private String baseNodeIp;//上级节点ip，如果是部中心可为空
	private String baseNodeName;//上级节点名称，如果是部中心可为空
	private String baseNodeCode;//上级节点编码，如果是部中心可为空
	
	
	private String registerTime;//注册时间
	//黑盒记录信息
	private String blackBoxName;//黑盒名称
	private String blackBoxCode;//黑盒编号
	private String blackBoxIp;//黑盒ip
	
	
	
	
	
	
	
	
	/*********************get方法********************/
	/**
	 * 上级节点编码，如果是部中心可为空
	 * @return
	 */
	@Column(name = "BASE_NODE_CODE", length = 60)
	public String getBaseNodeCode() {
		return baseNodeCode;
	}
	/**
	 * 注册时间
	 * @return
	 */
	@Column(name = "REGISTER_TIME", columnDefinition = "TIMESTAMP")
	public String getRegisterTime() {
		return registerTime;
	}
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
	 * 节点编码
	 * @return
	 */
	@Column(name = "NODE_CODE", length = 60)
	public String getNodeCode() {
		return nodeCode;
	}
	/**
	 * 节点名称
	 * @return
	 */
	@Column(name = "NODE_NAME", length = 60)
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * 节点ip
	 * @return
	 */
	@Column(name = "NODE_IP", length = 60)
	public String getNodeIp() {
		return nodeIp;
	}
	/**
	 * 上级节点ip，如果是部中心可为空
	 * @return
	 */
	@Column(name = "BASE_NODE_IP", length = 60)
	public String getBaseNodeIp() {
		return baseNodeIp;
	}
	/**
	 * 上级节点名称，如果是部中心可为空
	 * @return
	 */
	@Column(name = "BASE_NODE_NAME", length = 60)
	public String getBaseNodeName() {
		return baseNodeName;
	}
	/**
	 * 黑盒名称
	 * @return
	 */
	@Column(name = "BLACK_BOX_NAME", length = 60)
	public String getBlackBoxName() {
		return blackBoxName;
	}
	/**
	 * 黑盒编号
	 * @return
	 */
	@Column(name = "BLACK_BOX_CODE", length = 60)
	public String getBlackBoxCode() {
		return blackBoxCode;
	}
	/**
	 * 黑盒ip
	 * @return
	 */
	@Column(name = "BLACK_BOX_IP", length = 60)
	public String getBlackBoxIp() {
		return blackBoxIp;
	}
	
	
	
	
	
	/********************set方法**********************/
	
	/**
	 * 注册时间
	 * @param registerTime
	 */
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 节点编码
	 * @param nodeCode
	 */
	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}
	/**
	 * 节点名称
	 * @param nodeName
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * 节点ip
	 * @param nodeIp
	 */
	public void setNodeIp(String nodeIp) {
		this.nodeIp = nodeIp;
	}
	/**
	 * 上级节点ip，如果是部中心可为空
	 * @param baseNodeIp
	 */
	public void setBaseNodeIp(String baseNodeIp) {
		this.baseNodeIp = baseNodeIp;
	}
	/**
	 * 上级节点名称，如果是部中心可为空
	 * @param baseNodeName
	 */
	public void setBaseNodeName(String baseNodeName) {
		this.baseNodeName = baseNodeName;
	}
	/**
	 * 黑盒名称
	 * @param blackBoxName
	 */
	public void setBlackBoxName(String blackBoxName) {
		this.blackBoxName = blackBoxName;
	}
	/**
	 * 黑盒编号
	 * @param blackBoxCode
	 */
	public void setBlackBoxCode(String blackBoxCode) {
		this.blackBoxCode = blackBoxCode;
	}
	/**
	 * 黑盒ip
	 * @param blackBoxIp
	 */
	public void setBlackBoxIp(String blackBoxIp) {
		this.blackBoxIp = blackBoxIp;
	}
	/**
	 * 上级节点编码，如果是部中心可为空
	 * @param baseNodeCode
	 */
	public void setBaseNodeCode(String baseNodeCode) {
		this.baseNodeCode = baseNodeCode;
	}
}
