package com.crossover.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crossover.dao.CustomerDao;
import com.crossover.model.Customer;
import com.crossover.service.CustomerCodeNotExistsException;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve Customer objects.
 *
 */
@Repository("customerDao")
public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements CustomerDao {

    /**
     * Constructor that sets the entity to User.class.
     */
    public CustomerDaoHibernate() {
        super(Customer.class);
    }


	@Override
	public List<Customer> getCustomer() {
		Query qry = getSession().createQuery("from Customer c order by upper(c.name)");
		return qry.list();
	}

	@Override
	public Customer saveCustomer(Customer customer) {

		if (log.isDebugEnabled()) {
			log.debug("customer's code: " + customer.getCode());
		}
		getSession().saveOrUpdate(customer);
		// necessary to throw a DataIntegrityViolation and catch it in CustomerManager
		getSession().flush();
		return customer;

	}


	@Override
	public Customer getCustomerByCustomerCode(String code) throws CustomerCodeNotExistsException {
		List customers = getSession().createCriteria(Customer.class).add(Restrictions.eq("code", code)).list();
        if (customers == null || customers.isEmpty()) {
            throw new CustomerCodeNotExistsException("Customer '" + code + "' not found...");
        } else {
            return (Customer) customers.get(0);
        }
	}
}
