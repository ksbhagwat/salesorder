package com.crossover.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * This class is used to represent individual sales order line items.
 *
 */
@Entity
@Table(name = "sales_order_details")
@Indexed
@Embeddable
@XmlRootElement
public class SalesOrderDetails extends BaseObject implements Serializable {

	private static final long serialVersionUID = 6873056266954602878L;
	
	private Long id;
//	private Product product;
    private Integer quantity;
	private Double unitPrice;
    private Double totalPrice;
//	private String orderNumber;
//	private SalesOrder salesOrder;


    /**
     * Default constructor - creates a new instance with no values set.
     */
    public SalesOrderDetails() {
    }

    @Id
    @DocumentId
    public Long getId() {
		return id;
	}
    
    /*@Embedded
    @IndexedEmbedded
	public Product getProduct() {
		return product;
	}*/
	
    @Column(name = "quantity")
    @Field
	public Integer getQuantity() {
		return quantity;
	}
	
    @Column(name = "unit_price")
    @Field
	public Double getUnitPrice() {
		return unitPrice;
	}
    
    @Column(name = "total_price")
    @Field
	public Double getTotalPrice() {
		return totalPrice;
	}
	
   /* @Column(name = "order_number")
    @Field
    public String getOrderNumber() {
        return orderNumber;
    }
    
    *//**
	 * @param orderNumber the orderNumber to set
	 *//*
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}*/
	
  /*  @ManyToOne
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	*//**
	 * @param salesOrder the salesOrder to set
	 *//*
	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}*/

    /**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param product the product to set
	 */
	/*public void setProduct(Product product) {
		this.product = product;
	}*/

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	/**
     * {@inheritDoc}
     */
    public boolean equals(Object sod) {
        if (this == sod) {
            return true;
        }
        if (!(sod instanceof SalesOrderDetails)) {
            return false;
        }

        final SalesOrderDetails salesOrderDetails = (SalesOrderDetails) sod;

        return !(id != null ? !id.equals(salesOrderDetails.id) : salesOrderDetails.id != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.id)
                .toString();
    }
}
