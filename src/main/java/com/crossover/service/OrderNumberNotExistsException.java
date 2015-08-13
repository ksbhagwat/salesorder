package com.crossover.service;

public class OrderNumberNotExistsException extends Exception {

	private static final long serialVersionUID = -805368420564633879L;

	/**
	 * Constructor for OrderNumberNotExistsException.
	 *
	 * @param message exception message
	 */
	public OrderNumberNotExistsException(final String message) {
		super(message);
	}


}
