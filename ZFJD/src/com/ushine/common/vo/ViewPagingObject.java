package com.ushine.common.vo;

import net.sf.json.JSONObject;

/**
 * 视图分页对象: 于保存需要返回页面的信息,
 * 继承ViewDataObject增加了集合的分页信息
 * 
 * @author Franklin
 *
 */
public class ViewPagingObject extends ViewDataObject {

	private Paging paging; // 分页信息
	
	public ViewPagingObject() {
		super();
	}
	
	public ViewPagingObject(int status, String msg) {
		super(status, msg);
	}
	
	public Paging getPaging() {
		return paging;
	}
	
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	
	/**
	 * 只将List及分页信息生成JSon数据
	 * @return
	 */
	public String toJSonByPaging() {
		JSONObject object = new JSONObject();
		object.put("paging", getPaging());
		object.put("data", getArray());
		return object.toString();
	}
	
}
