package com.alexandre.excessoes;

public class RepositoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private int code;
	
	public RepositoryException(String message, int code) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
