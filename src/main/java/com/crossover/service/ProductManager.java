package com.crossover.service;

import java.util.List;

import com.crossover.dao.ProductDao;
import com.crossover.model.Product;


/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 */
public interface ProductManager extends GenericManager<Product, Long> {
    /**
     * Convenience method for testing - allows you to mock the DAO and set it on an interface.
     * @param productDao the ProductDao implementation to use
     */
    void setProductDao(ProductDao productDao);


    /**
     * Retrieves a product by code.  An exception is thrown if product not found
     *
     * @param code the identifier for the product
     * @return Product
     */
    Product getProductByProductName(String code) throws ProductCodeNotExistsException;


    /**
     * Retrieves a list of all products.
     * @return List
     */
    List<Product> getProducts();

    /**
     * Saves a product's information.
     *
     * @param product the product's information
     * @throws ProductExistsException thrown when product already exists
     * @return product the updated product object
     */
    Product saveProduct(Product product) throws ProductExistsException;

    /**
     * Removes a product from the database
     *
     * @param product the product to remove
     */
    void removeProduct(Product product);

    /**
     * Removes a product from the database by their code
     *
     * @param code the product's code
     */
    void removeProduct(String code) throws ProductCodeNotExistsException;

}
