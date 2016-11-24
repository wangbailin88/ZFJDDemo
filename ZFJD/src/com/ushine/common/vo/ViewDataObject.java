package com.ushine.common.vo;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 视图数据对象: 于保存需要返回页面的信息,
 * 继承ViewObject增加了对象及集合的保存
 * 
 * @author Franklin
 *
 */
public class ViewDataObject extends ViewObject {

	private Object object; // 数据对象
	
	private List<?> array; // 数据集合
	
	public ViewDataObject() {
		super();
	}
	
	public ViewDataObject(int status, String msg) {
		super(status, msg);
	}

	public Object getObject() {
		return object;
	}

	public List<?> getArray() {
		return array;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setArray(List<?> array) {
		this.array = array;
	}
	
	/**
	 * 只将Object生成JSon数据
	 */
	public String toJSonByObject() {
		return JSONObject.fromObject(object).toString();
	}
	
	/**
	 * 只将List生成JSon数据
	 * @return
	 */
	public String toJSonByArray() {
		return JSONArray.fromObject(array).toString();
	}
	
}
