package com.crossover.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.crossover.model.Customer;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/customers")
public interface CustomerService {
    /**
     * Retrieves a customer by code.  An exception is thrown if customer not found
     *
     * @param code the identifier for the customer
     * @return customer
     */
    @GET
    @Path("{code}")
    Customer getCustomerByCustomerName(@PathParam("code") String code) throws CustomerCodeNotExistsException;


    /**
     * Retrieves a list of all customers.
     *
     * @return List
     */
    @GET
    List<Customer> getCustomers();

    /**
     * Saves a customer's information
     *
     * @param customer the customer's information
     * @return updated customer
     * @throws CustomerExistsException thrown when customer already exists
     */
    @POST
    Customer saveCustomer(Customer customer) throws CustomerExistsException;

    /**
     * Removes a customer from the database by their code
     *
     * @param code the customer's id
     */
    @DELETE
    void removeCustomer(String code) throws CustomerCodeNotExistsException;
}
