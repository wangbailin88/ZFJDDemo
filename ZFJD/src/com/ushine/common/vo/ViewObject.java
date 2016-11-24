package com.ushine.common.vo;

import net.sf.json.JSONObject;

/**
 * 视图对象，用于保存需要返回页面的信息
 * 
 * @author Franklin
 *
 */
public class ViewObject {

	/**
	 * 返回状态：成功
	 */
	public static final int RET_SUCCEED = 1;
	
	/**
	 * 返回状态：失败
	 */
	public static final int RET_FAILURE = 0;
	
	/**
	 * 返回状态：错误
	 */
	public static final int RET_ERROR = -1;
	
	private int status; // 返回的页面的状态
	
	private String msg; // 返回给页面的信息
	
	private boolean success;
	
	public ViewObject() {
		
	}
	
	public ViewObject(int status, String msg) {
		if(status==-1){
			this.success=false;
		}else{
			this.success=true;
		}
		this.status = status;
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 返回JSon数据
	 * @return String JSon数据
	 */
	public String toJSon() {
		return JSONObject.fromObject(this).toString();
	}
	
}
