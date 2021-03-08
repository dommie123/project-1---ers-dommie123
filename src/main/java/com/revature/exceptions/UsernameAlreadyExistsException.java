package com.revature.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1712843686624302051L;

	public UsernameAlreadyExistsException() {
		super("This username and password combo already exists!");
	}

}
