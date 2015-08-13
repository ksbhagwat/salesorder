package com.crossover.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.dao.CustomerDao;
import com.crossover.model.Customer;
import com.crossover.service.CustomerCodeNotExistsException;
import com.crossover.service.CustomerExistsException;
import com.crossover.service.CustomerManager;
import com.crossover.service.CustomerService;


/**
 * Implementation of CustomerManager interface.
 *
 */
@Service("customerManager")
@WebService(serviceName = "CustomerService", endpointInterface = "com.crossover.service.CustomerService")
public class CustomerManagerImpl extends GenericManagerImpl<Customer, Long> implements CustomerManager, CustomerService {
    private CustomerDao customerDao;


    @Override
    @Autowired
    public void setCustomerDao(final CustomerDao customerDao) {
        this.dao = customerDao;
        this.customerDao = customerDao;
    }

    /**
     * {@inheritDoc}
     * @throws CustomerCodeNotExistsException 
     */
	@Override
	public Customer getCustomerByCustomerName(String code) throws CustomerCodeNotExistsException {
		  return customerDao.getCustomerByCustomerCode(code);
	}

	@Override
	public Customer saveCustomer(Customer customerVO) throws CustomerExistsException {
		Customer customer = null;
		try {
			// check if the customer is already present or not
			try{
			customer = customerDao.getCustomerByCustomerCode(customerVO.getCode());
			}catch(CustomerCodeNotExistsException cnnee){
				log.debug("Customer Does not exist with name : "+customerVO.getName());
			}
			if(customer != null){
				// customer is present, update it
				customer.setAddress(customerVO.getAddress());
				customer.setName(customerVO.getName());
				customer.setCreditLimit(customerVO.getCreditLimit());
				customer.setCurrentCredit(customerVO.getCurrentCredit());
				customer.setPhoneNumber1(customerVO.getPhoneNumber1());
				customer.setPhoneNumber2(customerVO.getPhoneNumber2());
			}else{
				// customer does not present save it
				customer = new Customer();
				customer.setCode(customerVO.getCode());
				customer.setAddress(customerVO.getAddress());
				customer.setName(customerVO.getName());
				customer.setCreditLimit(customerVO.getCreditLimit());
				customer.setCurrentCredit(customerVO.getCurrentCredit());
				customer.setPhoneNumber1(customerVO.getPhoneNumber1());
				customer.setPhoneNumber2(customerVO.getPhoneNumber2());
			}
			return customerDao.saveCustomer(customerVO);
		} catch (final Exception e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			throw new CustomerExistsException("Customer '" + customerVO.getCode() + "' already exists!");
		}
	}


	@Override
	public List<Customer> getCustomers() {
		 return customerDao.getAllDistinct();
	}

	@Override
	public void removeCustomer(Customer customer) {
		log.debug("removing customer: " + customer);
		customerDao.remove(customer);
	}

	@Override
	public void removeCustomer(String code) throws CustomerCodeNotExistsException {
        Customer customer = customerDao.getCustomerByCustomerCode(code);
        customerDao.remove(customer);
    }
}
