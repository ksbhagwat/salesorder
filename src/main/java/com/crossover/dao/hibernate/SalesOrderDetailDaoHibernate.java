package com.crossover.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.crossover.dao.SalesOrderDetailsDao;
import com.crossover.model.SalesOrderDetails;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve SalesOrderDetails objects.
 *
 */
@Repository("salesOrderDetailsDao")
public class SalesOrderDetailDaoHibernate extends GenericDaoHibernate<SalesOrderDetails, Long> implements SalesOrderDetailsDao {

    /**
     * Constructor that sets the entity to User.class.
     */
    public SalesOrderDetailDaoHibernate() {
        super(SalesOrderDetails.class);
    }


	@Override
	public List<SalesOrderDetails> getSalesOrderDetails() {
		Query qry = getSession().createQuery("from SalesOrderDetails c order by upper(c.name)");
		return qry.list();
	}

	@Override
	public SalesOrderDetails saveSalesOrderDetails(SalesOrderDetails salesOrderDetails) {

		if (log.isDebugEnabled()) {
			log.debug("salesOrderDetails's Nuber: " + salesOrderDetails.getId());
		}
		getSession().merge(salesOrderDetails);
		// necessary to throw a DataIntegrityViolation and catch it in UserManager
		getSession().flush();
		return salesOrderDetails;

	}


	
}
