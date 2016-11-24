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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 模块
 * @author Franklin
 *
 */
@Entity
@Table(name="T_MODULE")
public class Module implements Serializable {
	private static final long serialVersionUID = 443603767373021426L;

	private String id;
	
	private String name; // 模块名称
	
	private List<Menu> menus = new ArrayList<Menu>(); // 该模块下的菜单
	
	public Module() {
		
	}
	
	public Module(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	@OneToMany(
		cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, 
		mappedBy="module"
	)
	public List<Menu> getMenus() {
		return menus;
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
	
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
