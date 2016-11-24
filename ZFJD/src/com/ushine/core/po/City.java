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

@Entity
@Table(name="T_CITY")
public class City implements Serializable{
	private static final long serialVersionUID = 1L;
	private String wid;							//编号
	private String XZQHDM;						//市编号
	private String XZQHMC;						//名称
	private String CC;							//等级
	private String JC;							//本级加上级名称
	private List<City> subcitys=new ArrayList<City>();
	private String alphabet;					//字母表
	private String pid;							//省编号
	public City() {}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Column(name="wid")
	public String getWid() {
		return wid;
	}
	@Id
	@GeneratedValue(generator = "guid")  
	@GenericGenerator(name = "guid", strategy = "guid")
	public String getXZQHDM() {
		return XZQHDM;
	}
	@Column(name="XZQHMC")
	public String getXZQHMC() {
		return XZQHMC;
	}
	@Column(name="CC")
	public String getCC() {
		return CC;
	}
	@Column(name="JC")
	public String getJC() {
		return JC;
	}
	@OneToMany(
			cascade = {CascadeType.ALL}, fetch=FetchType.LAZY
		)
	@JoinColumn(name="PID")
	public List<City> getSubcitys() {
		return subcitys;
	}
	@Column(name="ALPHABET")
	public String getAlphabet() {
		return alphabet;
	}
	@Column(name="PID")
	public String getPid() {
		return pid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public void setXZQHDM(String xZQHDM) {
		XZQHDM = xZQHDM;
	}

	public void setXZQHMC(String xZQHMC) {
		XZQHMC = xZQHMC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

	public void setJC(String jC) {
		JC = jC;
	}

	public void setSubcitys(List<City> subcitys) {
		this.subcitys = subcitys;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
