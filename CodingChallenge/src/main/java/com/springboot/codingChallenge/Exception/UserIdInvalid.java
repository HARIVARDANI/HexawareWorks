package com.springboot.codingChallenge.Exception;

public class UserIdInvalid extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public UserIdInvalid(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
