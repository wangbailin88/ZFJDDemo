package com.ushine.common.vo;

import net.sf.json.JSONObject;

/**
 * 视图分页对象: 于保存需要返回页面的信息,
 * 继承ViewDataObject增加了集合的分页信息
 * 
 * @author Franklin
 *
 */
public class PagingObject<T> extends DataObject<T> {

	private Paging paging; // 分页信息
	
	public PagingObject() {
		super();
	}
	
	public PagingObject(int status, String msg) {
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
	 * @return String
	 */
	public String toJSonByPaging() {
		JSONObject object = new JSONObject();
//		if(this.getArray().size()==this.getPaging().getSizePage()){
//			this.paging.setTotalRecord(this.getPaging().getStartRecord()+this.getPaging().getSizePage()+1);	
//		}else if(this.getArray().size()>0){
//			this.paging.setTotalRecord(this.getPaging().getStartRecord()+this.getArray().size());
//		}else{
//			this.paging.setTotalRecord(this.getPaging().getStartRecord());
//		}
		object.put("paging", getPaging());
		object.put("datas", getArray());
		return object.toString();
	}
	
}
