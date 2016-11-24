package com.ushine.common.vo;

public class GraphicObject {
	private String from;	//源
	private String to;		//目标
	private double kg;			//重量
	private int count;		//次数
	
	public GraphicObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GraphicObject(String from, String to, double kg,
			int count) {
		super();
		this.from = from;
		this.to = to;
		this.kg = kg;
		this.count = count;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public double getKg() {
		return kg;
	}
	public void setKg(double kg) {
		this.kg = kg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
