package com.ushine.core.inteceptor;

/**
 * 接口返回结果
 * @author Lee
 *
 */
public class DataResult {
	
	public static final int STATUS_NO_SIGN_UP = -999;
	
	public static final int STATUS_NO_RIGHT = -1;
	
	public static final int STATUS_SYSTEM_EXCEPTION = 0;
	
	public static final int STATUS_SUCCESS = 1;
	
	private Object data;
	
	private int status;
	
	private boolean success;
	
	private String msg;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
