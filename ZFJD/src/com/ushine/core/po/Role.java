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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 角色
 * 
 * @author Franklin
 *
 */
@Entity
@Table(name="T_ROLE")
public class Role implements Serializable {
	private static final long serialVersionUID = 6007294189541215292L;

	private String id;
	
	private String name; // 角色名称
	
	private String createTime; // 创建时间
	
	private int status; // 状态
	
	private List<Permit> permits = new ArrayList<Permit>(); // 角色拥有的权限
	
	public Role() {
		
	}

	public Role(String id, String name, String createTime, int status) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.status = status;
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
	public String getCreateTime() {
		return createTime;
	}

	@Column(name="STATUS")
	public int getStatus() {
		return status;
	}

	@OneToMany(
		cascade={CascadeType.ALL}, fetch=FetchType.LAZY
	)
	@JoinColumn(name="ROLE_ID")
	public List<Permit> getPermits() {
		return permits;
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

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setPermits(List<Permit> permits) {
		this.permits = permits;
	}
	
}
