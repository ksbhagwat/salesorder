package com.crossover.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.crossover.dao.CustomerDao;
import com.crossover.dao.SalesOrderDao;
import com.crossover.dao.SalesOrderDetailsDao;
import com.crossover.model.Customer;
import com.crossover.model.SalesOrder;
import com.crossover.model.SalesOrderDetails;
import com.crossover.service.CustomerCodeNotExistsException;
import com.crossover.service.InsufficientCreditException;
import com.crossover.service.OrderNumberNotExistsException;
import com.crossover.service.SalesOrderExistsException;
import com.crossover.service.SalesOrderManager;
import com.crossover.service.SalesOrderService;

/**
 * Implementation of SalesOrderManager interface.
 */
@Service("salesOrderManager")
@WebService(serviceName = "SalesOrderService", endpointInterface = "com.crossover.service.SalesOrderService")
public class SalesOrderManagerImpl extends GenericManagerImpl<SalesOrder, Long> implements SalesOrderManager, SalesOrderService {
    private SalesOrderDao salesOrderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private SalesOrderDetailsDao orderDetailsDao;
    

	@Override
    @Autowired
    public void setSalesOrderDao(final SalesOrderDao salesOrderDao) {
        this.dao = salesOrderDao;
        this.salesOrderDao = salesOrderDao;
    }

    /**
     * {@inheritDoc}
     * @throws SalesOrderCodeNotExistsException 
     */
	@Override
	public SalesOrder getSalesOrderBySalesOrderNumber(String orderNumber) throws OrderNumberNotExistsException {
		  return salesOrderDao.getSalesOrderBySalesOrderNumber(orderNumber);
	}

	@Override
	public SalesOrder saveSalesOrder(SalesOrder salesOrderVO) throws SalesOrderExistsException {
		SalesOrder salesOrder = null;
		Double custCredit=0d, totalPrice =0d;
		try {
			// check if the salesOrder is already present or not
			try{
				salesOrder = salesOrderDao.getSalesOrderBySalesOrderNumber(salesOrderVO.getOrderNumber());
			}catch(OrderNumberNotExistsException cnnee){
				log.debug("SalesOrder Does not exist with Number : "+salesOrderVO.getOrderNumber());
			}

			if(salesOrder != null){
				// fetch customer and update it
				totalPrice = updateSalesOrder(salesOrderVO, salesOrder);
				custCredit = updateCustomer(salesOrderVO, salesOrder,totalPrice);
				// salesOrder is present, update it
				salesOrder.setTotalPrice(totalPrice);
			}else{
				// salesOrder does not present save it
				salesOrder = new SalesOrder();
				totalPrice = createSalesOrder(salesOrderVO, salesOrder);
				custCredit = updateCustomer(salesOrderVO, salesOrder,totalPrice);
				salesOrder.setOrderNumber(salesOrderVO.getOrderNumber());
				salesOrder.setTotalPrice(totalPrice);
			}
			validateCreditLimit(custCredit, totalPrice);
			return salesOrderDao.saveSalesOrder(salesOrder);
		} catch (final Exception e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new SalesOrderExistsException("SalesOrder '" + salesOrderVO.getOrderNumber() + "' already exists!");
		}
	}


	@Override
	public List<SalesOrder> getSalesOrders() {
		 return salesOrderDao.getAllDistinct();
	}

	@Override
	public void removeSalesOrder(SalesOrder salesOrder) {
		log.debug("removing salesOrder: " + salesOrder);
		salesOrderDao.remove(salesOrder);
	}

	@Override
	public void removeSalesOrder(String orderNumber) throws OrderNumberNotExistsException {
		SalesOrder salesOrder = salesOrderDao.getSalesOrderBySalesOrderNumber(orderNumber);
		salesOrderDao.remove(salesOrder);
    }
	
	public void validateCreditLimit(double currentCredit , double totalPrice) throws InsufficientCreditException{
		if(totalPrice > currentCredit)
			throw new InsufficientCreditException("Customer Credit :"+currentCredit+" is less than equal to total price "+totalPrice);
	}
	
	public double createSalesOrder(SalesOrder salesOrderVO  ,SalesOrder salesOrder){
		double totalPrice = 0d;
		if(salesOrderVO.getOrderSet() != null){
			Iterator<SalesOrderDetails> orderDetailsIter = salesOrderVO.getOrderSet().iterator();
			while(orderDetailsIter.hasNext()){
				SalesOrderDetails salesOrderDetailsVO = orderDetailsIter.next();
				SalesOrderDetails salesOrderDetails = new SalesOrderDetails();
				salesOrderDetails.setId(salesOrderDetailsVO.getId());
				salesOrderDetails.setQuantity(salesOrderDetailsVO.getQuantity());
				salesOrderDetails.setTotalPrice(salesOrderDetailsVO.getTotalPrice());
				salesOrderDetails.setUnitPrice(salesOrderDetailsVO.getUnitPrice());
				totalPrice = totalPrice + salesOrderDetailsVO.getTotalPrice();

				salesOrder.getOrderSet().add(salesOrderDetails);
			}
		}
		return totalPrice;
	}
	
	public double updateSalesOrder(SalesOrder salesOrderVO  ,SalesOrder salesOrder){
		Long orderDetailId = null;
		double totalPrice = 0d;
		try{
			SalesOrderDetails salesOrderDetails = null;
			if(salesOrderVO.getOrderSet() != null){
				Iterator<SalesOrderDetails> orderDetailSetVO = salesOrderVO.getOrderSet().iterator();
				while(orderDetailSetVO.hasNext()){
					SalesOrderDetails orderDetailsVO = orderDetailSetVO.next();
					orderDetailId = orderDetailsVO.getId();
					salesOrderDetails = orderDetailsDao.get(orderDetailId);
					salesOrderDetails.setQuantity(orderDetailsVO.getQuantity());
					salesOrderDetails.setTotalPrice(orderDetailsVO.getTotalPrice());
					salesOrderDetails.setUnitPrice(orderDetailsVO.getUnitPrice());
					totalPrice = totalPrice + orderDetailsVO.getTotalPrice();

					salesOrder.getOrderSet().add(salesOrderDetails);
				}
			}
			
		}catch(ObjectRetrievalFailureException ex){
			log.debug("Sales Details Does not exist with ID : "+orderDetailId);
		}
		return totalPrice;
	}
	
	public double updateCustomer(SalesOrder salesOrderVO, SalesOrder salesOrder,double totalPrice){
		String customerName = null;
		Customer customer = null;
		double custCredit = 0d;
		try{
			if(salesOrderVO.getCustomerSet() != null){
				Iterator<Customer> customerSetIter = salesOrderVO.getCustomerSet().iterator();
				while(customerSetIter.hasNext()){
					Customer customerVO = customerSetIter.next();
					customerName = customerVO.getName();
					customer = customerDao.getCustomerByCustomerCode(customerVO.getCode());
					custCredit = custCredit +(customer.getCreditLimit() - customer.getCurrentCredit());
					
					customer.setCurrentCredit(customer.getCurrentCredit()+ totalPrice);
					salesOrder.getCustomerSet().add(customer);
				}
			}
		}catch(CustomerCodeNotExistsException ex){
			log.debug("Customer Does not exist with Name : "+customerName);
		}
		return custCredit;
	}
}
