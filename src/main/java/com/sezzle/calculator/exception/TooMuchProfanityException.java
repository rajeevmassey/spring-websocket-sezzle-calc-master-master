package com.sezzle.calculator.exception;

/**
 * 
 * @author Rajeev Massey
 */
public class TooMuchProfanityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TooMuchProfanityException(String message) {
		super(message);
	}
}
