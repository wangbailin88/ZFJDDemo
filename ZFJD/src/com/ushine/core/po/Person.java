package com.ushine.core.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 人员（用户）
 * 
 * @author Franklin
 * 
 */
@Entity
@Table(name = "T_PERSON")
public class Person implements Serializable {
	private static final long serialVersionUID = 874320881861022615L;

	private String id;
	
	private String ip;		//绑定用户ip
	
	private String number; // 编号（警号）

	private String trueName; // 真实姓名

	private String userName; // 用户名

	private String password; // 密码

	private String tel; // 联系电话

	private String createDate; // 创建时间

	private String loginLastDate; // 最后登录时间

	private String loginLastIP; // 最后登录IP

	private int status; // 状态 0不验证IP，1为验证IP

	private Department dept; // 人员所在的部门

	private List<Role> roles = new ArrayList<Role>(); // 用户拥有的角色
	
	private String IDCard; // 人员身份证号码

	public Person() {

	}

	public Person(String id, String number, String trueName, String userName,
			String password, String tel, String createDate,
			String loginLastDate, String loginLastIP, int status,
			Department dept) {
		this.id = id;
		this.number = number;
		this.trueName = trueName;
		this.userName = userName;
		this.password = password;
		this.tel = tel;
		this.createDate = createDate;
		this.loginLastDate = loginLastDate;
		this.loginLastIP = loginLastIP;
		this.status = status;
		this.dept = dept;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// ***********************************************
	// * Getting Method
	// ***********************************************

	@Id
	@GenericGenerator(name = "uId", strategy = "uuid.hex")
	@GeneratedValue(generator = "uId")
	@Column(name = "ID", length = 32)
	public String getId() {
		return id;
	}
	
	@Column(name = "IP", length = 60)
	public String getIp() {
		return ip;
	}

	@Column(name = "NUMBER", length = 60)
	public String getNumber() {
		return number;
	}

	@Column(name = "TRUE_NAME", length = 60)
	public String getTrueName() {
		return trueName;
	}

	@Column(name = "USER_NAME", length = 60)
	public String getUserName() {
		return userName;
	}

	@Column(name = "PASSWORD", length = 60)
	public String getPassword() {
		return password;
	}

	@Column(name = "TEL", length = 60)
	public String getTel() {
		return tel;
	}

	@Column(name = "CREATE_DATE", columnDefinition = "TIMESTAMP")
	public String getCreateDate() {
		return createDate;
	}

	@Column(name = "LOGIN_LAST_DATE", columnDefinition = "TIMESTAMP")
	public String getLoginLastDate() {
		return loginLastDate;
	}

	@Column(name = "LOGIN_LAST_IP", length = 20)
	public String getLoginLastIP() {
		return loginLastIP;
	}

	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}

	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "DEPT_ID")
	public Department getDept() {
		return dept;
	}

	@ManyToMany( // 多对多
	cascade = { CascadeType.MERGE }, // save-update, save
	targetEntity = com.ushine.core.po.Role.class)
	@JoinTable(name = "T_PERSON_ROLE", joinColumns = @JoinColumn(name = "PERSON_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	public List<Role> getRoles() {
		return roles;
	}

	
	@Column(name = "IDCARD", length = 32)
	public String getIDCard() {
		return IDCard;
	}

	// ***********************************************
	// * Setting Method
	// ***********************************************

	public void setId(String id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setLoginLastDate(String loginLastDate) {
		this.loginLastDate = loginLastDate;
	}

	public void setLoginLastIP(String loginLastIP) {
		this.loginLastIP = loginLastIP;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
