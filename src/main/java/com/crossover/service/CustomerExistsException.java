package com.crossover.service;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 */
public class CustomerExistsException extends Exception {

	private static final long serialVersionUID = -9132069601650701054L;

	/**
     * Constructor for CustomerExistsException.
     *
     * @param message exception message
     */
    public CustomerExistsException(final String message) {
        super(message);
    }
}
