package com.crossover.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.crossover.model.Product;
import com.crossover.service.ProductCodeNotExistsException;

/**
 * User Data Access Object (GenericDao) interface.
 *
 */
public interface ProductDao extends GenericDao<Product, Long> {

    /**
     * Gets product information based on login name.
     * @param code the Product's codes
     * @return Product populated Product object
     * @throws ProductCodeNotExistsException thrown when product not
     * found in database */
     
    @Transactional
    Product getProductByProductCode(String code) throws ProductCodeNotExistsException;

    /**
     * Gets a list of products ordered by the uppercase version of their code.
     *
     * @return List populated list of Products
     */
    List<Product> getProduct();

    /**
     * Saves a product's information.
     * @param product the object to be saved
     * @return the persisted User object
     */
    Product saveProduct(Product product);

   
    
}
