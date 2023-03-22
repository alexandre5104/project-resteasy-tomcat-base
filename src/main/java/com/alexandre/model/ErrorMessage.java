package com.alexandre.model;

import java.io.Serializable;

public class ErrorMessage implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String message;
	
	private int code;
	
	public ErrorMessage(String message, int code) {
		this.setMessage(message);
		this.setCode(code);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
