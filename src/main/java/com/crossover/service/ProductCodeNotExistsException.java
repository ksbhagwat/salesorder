package com.crossover.service;

public class ProductCodeNotExistsException extends Exception {

	private static final long serialVersionUID = -8831672624605601814L;

	/**
     * Constructor for ProductCodeNotExistsException.
     *
     * @param message exception message
     */
    public ProductCodeNotExistsException(final String message) {
        super(message);
    }

}
