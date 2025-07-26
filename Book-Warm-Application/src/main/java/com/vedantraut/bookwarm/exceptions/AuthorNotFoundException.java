package com.vedantraut.bookwarm.exceptions;


public class AuthorNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthorNotFoundException(String message) {
		super(message);
	}
	
}
