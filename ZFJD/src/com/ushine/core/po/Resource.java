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
 * 元素
 * @author Franklin
 *
 */
@Entity
@Table(name="T_RESOURCE")
public class Resource implements Serializable {
	private static final long serialVersionUID = -5183984682038263946L;
	
	private String id;
	
	private String name; // 名称
	
	private int type; // 操作类型（区分资源）
	
	private String code; // 编码
	
	private String link; // 连接地址
	
	private String className; // JS类
	
	private String icon; // 图片路径

	private String inteceptor; // 拦截器拦截地址
	
	private Menu menu; // 资源所属的菜单
	
	public Resource() {
		
	}

	public Resource(String id, String name, int type, String code, String link,
			String className, String icon, String inteceptor, Menu menu) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.code = code;
		this.link = link;
		this.className = className;
		this.icon = icon;
		this.inteceptor = inteceptor;
		this.menu = menu;
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

	@Column(name="LINK", length=60)
	public String getLink() {
		return link;
	}

	@Column(name="CLASS_NAME", length=60)
	public String getClassName() {
		return className;
	}
	
	@Column(name="ICON", length=60)
	public String getIcon() {
		return icon;
	}

	@Column(name="INTECEPTOR", length=200)
	public String getInteceptor() {
		return inteceptor;
	}
	
	@ManyToOne(
		cascade = {CascadeType.MERGE}
	)
	@JoinColumn(name="MENU_ID")
	public Menu getMenu() {
		return menu;
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

	public void setLink(String link) {
		this.link = link;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setInteceptor(String inteceptor) {
		this.inteceptor = inteceptor;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
