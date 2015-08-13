package com.crossover.service;

import java.util.List;

import com.crossover.dao.SalesOrderDao;
import com.crossover.model.SalesOrder;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 */
public interface SalesOrderManager extends GenericManager<SalesOrder, Long> {
    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param salesorderDao the SalesOrderDao implementation to use
     */
    void setSalesOrderDao(SalesOrderDao salesOrderDao);


    /**
     * Retrieves a salesorder by code.  An exception is thrown if salesorder not found
     *
     * @param code the identifier for the salesorder
     * @return salesorder
     */
    SalesOrder getSalesOrderBySalesOrderNumber(String code) throws OrderNumberNotExistsException;


    /**
     * Retrieves a list of all salesorders.
     * @return List
     */
    List<SalesOrder> getSalesOrders();

    /**
     * Saves a salesorder's information.
     *
     * @param salesorder the salesorder's information
     * @throws SalesOrderExistsException thrown when salesorder already exists
     * @return salesorder the updated salesorder object
     */
    SalesOrder saveSalesOrder(SalesOrder salesOrder) throws SalesOrderExistsException;

    /**
     * Removes a salesorder from the database
     *
     * @param salesorder the salesorder to remove
     */
    void removeSalesOrder(SalesOrder salesOrder);

    /**
     * Removes a salesorder from the database by their code
     *
     * @param code the salesorder's code
     */
    void removeSalesOrder(String code) throws OrderNumberNotExistsException ;

}
