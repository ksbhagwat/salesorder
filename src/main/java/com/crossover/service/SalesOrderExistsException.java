package com.crossover.service;

public class SalesOrderExistsException extends Exception {

	private static final long serialVersionUID = -2815871030234017438L;

	public SalesOrderExistsException(final String message) {
		super(message);
	}
}
