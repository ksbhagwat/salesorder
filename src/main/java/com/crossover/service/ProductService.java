package com.crossover.service;

import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.crossover.model.Product;

/**
 * Web Service interface so hierarchy of Generic Manager isn't carried through.
 */
@WebService
@Path("/products")
public interface ProductService {
    /**
     * Retrieves a product by code.  An exception is thrown if product not found
     *
     * @param code the identifier for the product
     * @return product
     */
    @GET
    @Path("{code}")
    Product getProductByProductName(@PathParam("code") String code) throws ProductCodeNotExistsException;


    /**
     * Retrieves a list of all products.
     *
     * @return List
     */
    @GET
    List<Product> getProducts();

    /**
     * Saves a product's information
     *
     * @param product the product's information
     * @return updated product
     * @throws ProductExistsException thrown when product already exists
     */
    @POST
    Product saveProduct(Product product) throws ProductExistsException;

    /**
     * Removes a product from the database by their code
     *
     * @param code the product's code
     */
    @DELETE
    void removeProduct(String code) throws ProductCodeNotExistsException;
}
