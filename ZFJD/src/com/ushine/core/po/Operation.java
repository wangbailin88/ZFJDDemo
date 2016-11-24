package com.ushine.core.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 操作
 * @author Franklin
 *
 */
@Entity
@Table(name="T_OPERATION")
public class Operation implements Serializable {
	private static final long serialVersionUID = -9186106442607888399L;
	
	private String id;
	
	private String name; // 操作名称
	
	private int type; // 操作类型（对应权限类型,不同类型的资源有不同的操作）
	
	private String code; // 操作编码
	
	public Operation() {
		
	}

	public Operation(String id, String name, int type, String code) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.code = code;
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

	@Column(name="TYPE")
	public int getType() {
		return type;
	}

	@Column(name="CODE", length=60)
	public String getCode() {
		return code;
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

	public void setType(int type) {
		this.type = type;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
