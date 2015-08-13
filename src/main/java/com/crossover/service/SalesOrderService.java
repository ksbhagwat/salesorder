package com.crossover.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.crossover.model.SalesOrder;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/salesOrders")
public interface SalesOrderService {
    /**
     * Retrieves a salesOrder by code.  An exception is thrown if salesOrder not found
     *
     * @param code the identifier for the salesOrder
     * @return salesOrder
     */
    @GET
    @Path("{code}")
    SalesOrder getSalesOrderBySalesOrderNumber(@PathParam("code") String code) throws OrderNumberNotExistsException;


    /**
     * Retrieves a list of all salesorders.
     *
     * @return List
     */
    @GET
    List<SalesOrder> getSalesOrders();

    /**
     * Saves a salesorder's information
     *
     * @param salesorder the salesorder's information
     * @return updated salesorder
     * @throws SalesOrderExistsException thrown when salesorder already exists
     */
    @POST
    SalesOrder saveSalesOrder(SalesOrder salesOrder) throws SalesOrderExistsException;

    /**
     * Removes a salesorder from the database by their code
     *
     * @param salesorderId the salesorder's code
     */
    @DELETE
    void removeSalesOrder(String code) throws OrderNumberNotExistsException ;
}
