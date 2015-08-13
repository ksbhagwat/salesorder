package com.crossover.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crossover.dao.SalesOrderDao;
import com.crossover.model.SalesOrder;
import com.crossover.service.OrderNumberNotExistsException;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve SalesOrder objects.
 *
 */
@Repository("salesOrderDao")
public class SalesOrderDaoHibernate extends GenericDaoHibernate<SalesOrder, Long> implements SalesOrderDao {

    /**
     * Constructor that sets the entity to User.class.
     */
    public SalesOrderDaoHibernate() {
        super(SalesOrder.class);
    }


	@Override
	public List<SalesOrder> getSalesOrder() {
		Query qry = getSession().createQuery("from SalesOrder c order by upper(c.name)");
		return qry.list();
	}

	@Override
	public SalesOrder saveSalesOrder(SalesOrder salesOrder) {

		if (log.isDebugEnabled()) {
			log.debug("salesOrder's Nuber: " + salesOrder.getOrderNumber());
		}
		getSession().merge(salesOrder);
		// necessary to throw a DataIntegrityViolation and catch it in UserManager
		getSession().flush();
		return salesOrder;

	}

	@Override
	public SalesOrder getSalesOrderBySalesOrderNumber(String orderNumber) throws OrderNumberNotExistsException {
		List salesOrders = getSession().createCriteria(SalesOrder.class).add(Restrictions.eq("orderNumber", orderNumber)).list();
        if (salesOrders == null || salesOrders.isEmpty()) {
            throw new OrderNumberNotExistsException("SalesOrder '" + orderNumber + "' not found...");
        } else {
            return (SalesOrder) salesOrders.get(0);
        }
	}
	
}
