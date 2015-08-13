package com.crossover.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.crossover.model.Customer;
import com.crossover.service.CustomerCodeNotExistsException;

/**
 * User Data Access Object (GenericDao) interface.
 *
 */
public interface CustomerDao extends GenericDao<Customer, Long> {

    /**
     * Gets customer information based on code
     * @param code the customer code
     * @return Customer populated Customer object
     * @throws CustomerCodeNotExistsException thrown when customer not
     * found in database */
     
    @Transactional
    Customer getCustomerByCustomerCode(String code) throws CustomerCodeNotExistsException;

    /**
     * Gets a list of customers
     *
     * @return List populated list of customers
     */
    List<Customer> getCustomer();

    /**
     * Saves a Customer's information.
     * @param user the object to be saved
     * @return the persisted Customer object
     */
    Customer saveCustomer(Customer customer);


    
}
