package com.crossover.service;

public class InsufficientCreditException extends Exception {

	private static final long serialVersionUID = 5916790421906679402L;

	public InsufficientCreditException(final String message) {
		super(message);
	}
}
