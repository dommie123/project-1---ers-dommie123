package com.revature.exceptions;

public class UnauthorizedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5565959076866743040L;

	public UnauthorizedException() {
		super("Unauthorized user detected!");
	}

}
