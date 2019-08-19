package com.bytesedge.bookvenue.dto;

public class ApiValidationError {
	private String attr;
	private String code;
	private String msg;
	
	public ApiValidationError(String attr, String code, String msg) {
		this.attr = attr;
		this.code = code;
		this.msg = msg;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
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

}