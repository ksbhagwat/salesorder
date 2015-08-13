package com.crossover.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.dao.ProductDao;
import com.crossover.model.Product;
import com.crossover.service.ProductCodeNotExistsException;
import com.crossover.service.ProductExistsException;
import com.crossover.service.ProductManager;
import com.crossover.service.ProductService;


/**
 * Implementation of ProductManager interface.
 *
 */
@Service("productManager")
@WebService(serviceName = "ProductService", endpointInterface = "com.crossover.service.ProductService")
public class ProductManagerImpl extends GenericManagerImpl<Product, Long> implements ProductManager, ProductService {
    private ProductDao productDao;


    @Override
    @Autowired
    public void setProductDao(final ProductDao productDao) {
        this.dao = productDao;
        this.productDao = productDao;
    }

    /**
     * {@inheritDoc}
     * @throws ProductCodeNotExistsException 
     */
	@Override
	public Product getProductByProductName(String code) throws ProductCodeNotExistsException {
		  return productDao.getProductByProductCode(code);
	}

	@Override
	public Product saveProduct(Product productVO) throws ProductExistsException {
		Product product = null;
		try {
			// check if the product is already present or not
			try{
			product = productDao.getProductByProductCode(productVO.getCode());
			}catch(ProductCodeNotExistsException cnnee){
				log.debug("Product Does not exist with name : "+productVO.getCode());
			}
			if(product != null){
				// product is present, update it
				product.setDescription(productVO.getDescription());
				product.setPrice(productVO.getPrice());
				product.setQuantity(productVO.getQuantity());
			}else{
				// product does not present save it
				product = new Product();
				product.setCode(productVO.getCode());
				product.setDescription(productVO.getDescription());
				product.setPrice(productVO.getPrice());
				product.setQuantity(productVO.getQuantity());
				
			}
			return productDao.saveProduct(productVO);
		} catch (final Exception e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new ProductExistsException("Product '" + productVO.getCode() + "' already exists!");
		}
	}


	@Override
	public List<Product> getProducts() {
		 return productDao.getAllDistinct();
	}

	@Override
	public void removeProduct(Product product) {
		log.debug("removing product: " + product);
		productDao.remove(product);
	}

	@Override
	public void removeProduct(String code) throws ProductCodeNotExistsException {
		Product product = productDao.getProductByProductCode(code);
		productDao.remove(product);
		log.debug("Please implement this method removeProduct" + code);

    }
}
