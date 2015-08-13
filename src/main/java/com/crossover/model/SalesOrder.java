package com.crossover.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * This class represents the basic "SalesOrder" process of selling product to customer .
 *
 */
@Entity
@Table(name = "sales_order")
@Indexed
@XmlRootElement
public class SalesOrder extends BaseObject implements Serializable {

	private static final long serialVersionUID = -2012285699265670281L;
	private String orderNumber;
    private Set<Customer> customerSet = new HashSet<>();      
    private Double totalPrice ;
    private Set<SalesOrderDetails> orderSet = new HashSet<>(); 

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public SalesOrder() {
    }

    @Id
    @Column(name = "order_number")
    @DocumentId
    public String getOrderNumber() {
        return orderNumber;
    }

    @ManyToMany(fetch = FetchType.EAGER ,cascade= {CascadeType.MERGE})
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "customer_order_link",
            joinColumns = { @JoinColumn(name = "order_number") },
            inverseJoinColumns = @JoinColumn(name = "customer_code")
    )
    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    @Column(name = "total_price")
    @Field
    public Double getTotalPrice() {
        return totalPrice;
    }
    
    @ManyToMany(fetch = FetchType.EAGER ,cascade= {CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "sales_order_link",
            joinColumns = { @JoinColumn(name = "order_number") },
            inverseJoinColumns = @JoinColumn(name = "sales_order_details_id")
    )
    public Set<SalesOrderDetails> getOrderSet() {
        return orderSet;
    }
   
	/**
	 * @param orderNumber the orderNumber to set
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomerSet(Set<Customer> customerSet) {
		this.customerSet = customerSet;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderSet(Set<SalesOrderDetails> ordserSet) {
		this.orderSet = orderSet;
	}

	/**
     * {@inheritDoc}
     */
    public boolean equals(Object so) {
        if (this == so) {
            return true;
        }
        if (!(so instanceof SalesOrder)) {
            return false;
        }

        final SalesOrder salesOrder = (SalesOrder) so;

        return !(orderNumber != null ? !orderNumber.equals(salesOrder.getOrderNumber()) : salesOrder.getOrderNumber() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (orderNumber != null ? orderNumber.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("Order Number", this.orderNumber);

        return sb.toString();
    }
}
