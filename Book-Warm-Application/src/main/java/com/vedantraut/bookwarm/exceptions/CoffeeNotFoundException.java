package com.vedantraut.bookwarm.exceptions;

public class CoffeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoffeeNotFoundException(String message) {
		super(message);
	}
	
}
