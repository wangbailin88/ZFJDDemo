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
 * 组织下的部门
 * 
 * @author Franklin
 *
 */
@Entity
@Table(name="T_DEPARTMENT")
public class Department implements Serializable {
	private static final long serialVersionUID = -1099228070861350127L;

	private String id;
	
	private String name; // 部门名称 
	
	private String createDate; // 创建时间
	
	private int status; // 状态

	private Organiz organiz; // 所属组织
	
	private Department parent; // 上级部门
	
	private List<Department> subDepts = new ArrayList<Department>(); // 下级部门
	
	private List<Person> persons = new ArrayList<Person>(); // 该部门的人员
	
	public Department() {
		
	}

	public Department(String id, String name, String createDate, int status,
			Organiz organiz, Department parent) {
		this.id = id;
		this.name = name;
		this.createDate = createDate;
		this.status = status;
		this.organiz = organiz;
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

	@Column(name="CREATE_DATE", columnDefinition="TIMESTAMP")
	public String getCreateDate() {
		return createDate;
	}

	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}

	@ManyToOne(
		cascade = {CascadeType.MERGE}
	)
	@JoinColumn(name="ORGANIZ_ID")
	public Organiz getOrganiz() {
		return organiz;
	}

	@ManyToOne(
		cascade = {CascadeType.MERGE}
	)
	@JoinColumn(name="PARENT_ID")
	public Department getParent() {
		return parent;
	}

	@OneToMany(
		cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, 
		mappedBy="parent"
	)
	public List<Department> getSubDepts() {
		return subDepts;
	}

	@OneToMany(
		cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, 
		mappedBy="dept"
	)
	public List<Person> getPersons() {
		return persons;
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

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setOrganiz(Organiz organiz) {
		this.organiz = organiz;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public void setSubDepts(List<Department> subDepts) {
		this.subDepts = subDepts;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
}
