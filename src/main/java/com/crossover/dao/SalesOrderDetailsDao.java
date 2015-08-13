package com.crossover.dao;

import java.util.List;

import com.crossover.model.SalesOrderDetails;

/**
 * User Data Access Object (GenericDao) interface.
 *
 */
public interface SalesOrderDetailsDao extends GenericDao<SalesOrderDetails, Long> {

   

    /**
     * Gets a list of SalesOrderDetails ordered by the uppercase version of their id.
     *
     * @return List populated list of SalesOrderDetails
     */
    List<SalesOrderDetails> getSalesOrderDetails();

    /**
     * Saves a SalesOrderDetails's information.
     * @param SalesOrderDetails the object to be saved
     * @return the persisted SalesOrderDetails object
     */
    SalesOrderDetails saveSalesOrderDetails(SalesOrderDetails product);

   
    
}
