package com.xsw.common.vo;

import java.io.Serializable;

public class UserResult implements Serializable {
	private static final long serialVersionUID = -5881518151772913165L;

	/** 状态码 */
	private String code = "0";
	/** 状态信息 */
	private String msg = "操作成功";

	private Object data;

	private int count;

	@Override
	public String toString() {
		return "UserResult [code=" + code + ", msg=" + msg + ", data=" + data + ", count=" + count + "]";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public UserResult() {

	}

	public UserResult(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
