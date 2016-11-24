package com.ushine.core.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * 组织机构
 * 
 * @author Franklin
 *
 */
@Entity
@Table(name="T_ORGANIZ")
public class Organiz implements Serializable {
	private static final long serialVersionUID = -6956412184085958420L;

	private String id;

	private String name; // 组织名称
	
	private String region; // 所在地区
	
	private String contacts; // 联系人
	
	private String tel; // 联系电话
	
	private String createDate; // 创建时间
	
	private Organiz parent; // 上级组织
	
	private List<Organiz> subOrganizs = new ArrayList<Organiz>(); // 下级组织
	
	private List<Department> depts = new ArrayList<Department>(); // 该组织下的部门
	
	public Organiz() {
		
	}

	public Organiz(String id, String name, String region, String contacts,
			String tel, String createDate, Organiz parent) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.contacts = contacts;
		this.tel = tel;
		this.createDate = createDate;
		this.parent = parent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//***********************************************
	//* Getting Method
	//***********************************************
	
	@Id
	@GenericGenerator(name="uId", strategy="uuid.hex")
	@GeneratedValue(generator="uId")
	@Column(name="ID", length=32)
	public String getId() {
		return id;
	}

	@Column(name="NAME", length=60)
	public String getName() {
		return name;
	}

	@Column(name="REGION", length=200)
	public String getRegion() {
		return region;
	}

	@Column(name="CONTACTS", length=60)
	public String getContacts() {
		return contacts;
	}

	@Column(name="TEL", length=60)
	public String getTel() {
		return tel;
	}

	@Column(name="CREATE_DATE", columnDefinition="TIMESTAMP")
	public String getCreateDate() {
		return createDate;
	}

	@ManyToOne(// 多对一，添加、更新级联操作
		cascade = {CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="PARENT_ID")
	public Organiz getParent() {
		return parent;
	}

	@OneToMany(// 一对多，级联全部操作
		cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, 
		mappedBy="parent"
	)
	public List<Organiz> getSubOrganizs() {
		return subOrganizs;
	}

	@OneToMany(
		cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, 
		mappedBy="organiz"
	)
	public List<Department> getDepts() {
		return depts;
	}
	
	//***********************************************
	//* Setting Method
	//***********************************************
	
	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setParent(Organiz parent) {
		this.parent = parent;
	}

	public void setSubOrganizs(List<Organiz> subOrganizs) {
		this.subOrganizs = subOrganizs;
	}

	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}
	
}
