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
 * 菜单
 * 
 * @author Franklin
 *
 */
@Entity
@Table(name="T_MENU")
public class Menu implements Serializable {
	private static final long serialVersionUID = -8305049568528870648L;

	private String id;
	
	private String name; //  菜单名称
	
	private String icon; // 菜单图标
	
	private String url; // 连接地址
	
	private String className; // JS类
	
	private Module module; // 所属模块
	
	private Menu parent; // 父菜单
	
	private List<Menu> subMenus = new ArrayList<Menu>(); // 下级菜单
	
	private List<Resource> resources = new ArrayList<Resource>(); // 资源
	
	public Menu() {
		
	}

	public Menu(String id, String name, String icon, String url,
			String className, Module module, Menu parent) {
		this.id = id;
		this.name = name;
		this.icon = icon;
		this.url = url;
		this.className = className;
		this.module = module;
		this.parent = parent;
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

	@Column(name="ICON", length=60)
	public String getIcon() {
		return icon;
	}

	@Column(name="URL", length=60)
	public String getUrl() {
		return url;
	}

	@Column(name="CLASS_NAME", length=200)
	public String getClassName() {
		return className;
	}

	@ManyToOne(
		cascade = {CascadeType.MERGE}
	)
	@JoinColumn(name="MODULE_ID")
	public Module getModule() {
		return module;
	}

	@ManyToOne(
		cascade = {CascadeType.MERGE, CascadeType.REFRESH}
	)
	@JoinColumn(name="PARENT_ID")
	public Menu getParent() {
		return parent;
	}

	@OneToMany(
		cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, 
		mappedBy="parent"
	)
	public List<Menu> getSubMenus() {
		return subMenus;
	}

	@OneToMany(
		cascade = {CascadeType.ALL}, fetch=FetchType.LAZY, 
		mappedBy="menu"
	)
	public List<Resource> getResources() {
		return resources;
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

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
	
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
}
