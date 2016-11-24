package com.ushine.core.po;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 权限
 * @author Franklin
 *
 */
@Entity
@Table(name="T_PERMIT")
public class Permit implements Serializable {
	private static final long serialVersionUID = -8407391518190706978L;
	
	private String id;
	
	private Resource resource; // 资源
	
	private Operation operation; // 操作
	
	public Permit() {
		
	}
	
	public Permit(String id, int type, 
			Resource res, Operation oper) {
		this.id = id;
		this.resource = res;
		this.operation = oper;
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
	
	@ManyToOne(
		cascade = {CascadeType.MERGE, CascadeType.PERSIST}
	)
	@JoinColumn(name="RESOURCE_ID")	
	public Resource getResource() {
		return resource;
	}

	@ManyToOne(
		cascade = {CascadeType.MERGE, CascadeType.PERSIST}
	)
	@JoinColumn(name="OPERATION_ID")
	public Operation getOperation() {
		return operation;
	}
	
	//***********************************************
	//* Setting Method
	//***********************************************
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
}