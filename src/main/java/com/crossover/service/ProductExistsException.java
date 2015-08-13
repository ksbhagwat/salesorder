package com.crossover.service;

public class ProductExistsException extends Exception {

	private static final long serialVersionUID = 2735162712384820425L;

	public ProductExistsException(final String message) {
		super(message);
	}
}
