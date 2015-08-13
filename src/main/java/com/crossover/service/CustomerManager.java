package com.crossover.service;

import java.util.List;

import com.crossover.dao.CustomerDao;
import com.crossover.model.Customer;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 */
public interface CustomerManager extends GenericManager<Customer, Long> {
    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param customerDao the CustomerDao implementation to use
     */
    void setCustomerDao(CustomerDao customerDao);


    /**
     * Retrieves a customer by code.  An exception is thrown if customer not found
     *
     * @param code the identifier for the customer
     * @return Customer
     */
    Customer getCustomerByCustomerName(String code) throws CustomerCodeNotExistsException;


    /**
     * Retrieves a list of all Customers.
     * @return List
     */
    List<Customer> getCustomers();

    /**
     * Saves a Customer's information.
     *
     * @param Customer the Customer's information
     * @throws CustomerExistsException thrown when customer already exists
     * @return customer the updated customer object
     */
    Customer saveCustomer(Customer customer) throws CustomerExistsException;

    /**
     * Removes a customer from the database
     *
     * @param customer the customer to remove
     */
    void removeCustomer(Customer customer);

    /**
     * Removes a customer from the database by their code
     *
     * @param code the customer's code
     */
    void removeCustomer(String code) throws CustomerCodeNotExistsException;

}
