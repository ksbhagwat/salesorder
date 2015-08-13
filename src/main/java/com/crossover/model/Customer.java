package com.crossover.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 * This class represents the basic "Customer" who are the buyers of the products in the organization .
 *
 */
@Entity
@Table(name = "customer")
@Indexed
@XmlRootElement
public class Customer extends BaseObject implements Serializable {
	private static final long serialVersionUID = 8047182660837419995L;
	private String code;
    private String name;        
    private String address ;
    private String phoneNumber1;
    private String phoneNumber2;
    private Double creditLimit;
    private Double currentCredit;
    

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Customer() {
    }

    @Id
    @Column(nullable = false, length = 50, unique = true)
    @DocumentId
    public String getCode() {
        return code;
    }

    @Column(nullable = false, length = 50, unique = false)
    @Field
    public String getName() {
        return name;
    }

    @Column(nullable = true, length = 200, unique = false)
    @Field
    public String getAddress() {
        return address;
    }
    
    @Column(name = "phone_number1")
    @Field(analyze= Analyze.NO)
    public String getPhoneNumber1() {
        return phoneNumber1;
    }
    
    @Column(name = "phone_number2")
    @Field(analyze= Analyze.NO)
    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    @Column(nullable = false, unique = false)
    @Field
    public Double getCreditLimit() {
		return creditLimit;
	}

    @Column(nullable = false, unique = false)
	@Field
	public Double getCurrentCredit() {
		return currentCredit;
	}
    
	/**
	 * @param code the code to set
	 */    
    public void setCode(String code) {
        this.code = code;
    }    
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param phoneNumber1 the phoneNumber1 to set
	 */
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	/**
	 * @param phoneNumber2 the phoneNumber2 to set
	 */
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

    /**
	 * @param creditLimit the creditLimit to set
	 */
	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * @param currentCredit the currentCredit to set
	 */
	public void setCurrentCredit(Double currentCredit) {
		this.currentCredit = currentCredit;
	}

	/**
     * {@inheritDoc}
     */
    public boolean equals(Object c) {
        if (this == c) {
            return true;
        }
        if (!(c instanceof Customer)) {
            return false;
        }

        final Customer customer = (Customer) c;

        return !(code != null ? !code.equals(customer.getCode()) : customer.getCode() != null);

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
                .append("Name", this.name)
                .append("Address", this.address)
                .append("Phone1", this.phoneNumber1)
                .append("Phone2", this.phoneNumber2)
                .append("Credit Limit" ,this.creditLimit)
                .append("Current Credit",this.currentCredit);

        return sb.toString();
    }
}
