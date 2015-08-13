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
 * This class represents the basic "Product" which the organization sells .
 *
 */
@Entity
@Table(name = "product")
@Embeddable
@Indexed
@XmlRootElement
public class Product extends BaseObject implements Serializable {

	private static final long serialVersionUID = 2138748424471316041L;
	private String code;
    private String description;        
    private Double price ;
    private Integer quantity;
    

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Product() {
    }

    @Id
    @DocumentId
    public String getCode() {
        return code;
    }

    @Column(nullable = false, length = 100, unique = false)
    @Field
    public String getDescription() {
        return description;
    }

    @Column(name = "price")
    @Field
    public Double getPrice() {
        return price;
    }
    
    @Column(name = "quantity")
    @Field
    public Integer getQuantity() {
        return quantity;
    }


    /**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
     * {@inheritDoc}
     */
    public boolean equals(Object p) {
        if (this == p) {
            return true;
        }
        if (!(p instanceof Product)) {
            return false;
        }

        final Product product = (Product) p;

        return !(code != null ? !code.equals(product.getCode()) : product.getCode() != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (code != null ? code.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
                .append("Code", this.code)
                .append("Description", this.description)
                .append("Price", this.price)
                .append("Quanity", this.quantity);

        return sb.toString();
    }
}
