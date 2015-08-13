package com.crossover.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crossover.dao.ProductDao;
import com.crossover.model.Product;
import com.crossover.service.ProductCodeNotExistsException;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve Product objects.
 *
 */
@Repository("productDao")
public class ProductDaoHibernate extends GenericDaoHibernate<Product, Long> implements ProductDao {

    /**
     * Constructor that sets the entity to User.class.
     */
    public ProductDaoHibernate() {
        super(Product.class);
    }


	@Override
	public List<Product> getProduct() {
		Query qry = getSession().createQuery("from Product c order by upper(c.name)");
		return qry.list();
	}

	@Override
	public Product saveProduct(Product product) {

		if (log.isDebugEnabled()) {
			log.debug("product's code: " + product.getCode());
		}
		getSession().saveOrUpdate(product);
		// necessary to throw a DataIntegrityViolation and catch it in UserManager
		getSession().flush();
		return product;

	}


	@Override
	public Product getProductByProductCode(String code) throws ProductCodeNotExistsException{
		List products = getSession().createCriteria(Product.class).add(Restrictions.eq("code", code)).list();
        if (products == null || products.isEmpty()) {
            throw new ProductCodeNotExistsException("Product '" + code + "' not found...");
        } else {
            return (Product) products.get(0);
        }
	}
}
