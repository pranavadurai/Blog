package com.miniproj.blog.ExceptionHandlers;

public class ProfileCreationErrorHandler extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfileCreationErrorHandler(String message) {
		super(message);
	}	
}
