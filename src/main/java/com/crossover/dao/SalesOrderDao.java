package com.crossover.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.crossover.model.SalesOrder;
import com.crossover.service.OrderNumberNotExistsException;

/**
 * User Data Access Object (GenericDao) interface.
 *
 */
public interface SalesOrderDao extends GenericDao<SalesOrder, Long> {

    /**
     * Gets product information based on login name.
     * @param orderNumber the sales order orderNumber
     * @return SalesOrder populated SalesOrder object
     * @throws OrderNumberNotExistsException thrown when salesorder not
     * found in database */
     
    @Transactional
    SalesOrder getSalesOrderBySalesOrderNumber(String orderNumber) throws OrderNumberNotExistsException;

    /**
     * Gets a list of SalesOrder ordered by the uppercase version of their order number.
     *
     * @return List populated list of SalesOrder
     */
    List<SalesOrder> getSalesOrder();

    /**
     * Saves a SalesOrder's information.
     * @param SalesOrder the object to be saved
     * @return the persisted SalesOrder object
     */
    SalesOrder saveSalesOrder(SalesOrder product);

    
}
