package com.shoval.coupons.system.tables;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * This table entity (CUSTOMER) represent the fields of object Customer in the database.
 * <br>The Customer table is connected to the Coupon table by many to many relation.</br>
 * @author Shoval_G
 * @version 1.0
 * @category Customer table
 */
@Entity(name="CUSTOMER")
public class Customer {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "customer_coupon", 
			   joinColumns = @JoinColumn(name = "customer_id"), 
			   inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	private Collection<Coupon> coupons;
	
	/**
	 * Default constructor
	 */
	public Customer()
	{
		super();
	}
	
	/**
	 * Main constructor
	 * @param name customer name
	 * @param password customer password
	 */
	public Customer(String name, String password)
	{
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * Get function that return the customer's id.
	 * @return long identifier of customer's id.
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * Set function for changing customer's id.
	 * @param id the new customer's id.
	 */
	public void setId(long id)
	{
		this.id = id;
	}

	/**
	 * Get function that return the customer's name.
	 * @return String identifier of customer's name.
	 */
	public String getCust_name() 
	{
		return name;
	}

	/**
	 * Set function for changing customer's name.
	 * @param name the new customer's name.
	 */
	public void setCust_name(String name) 
	{
		this.name = name;
	}

	/**
	 * Get function that return the customer's password.
	 * @return String identifier of customer's password.
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * Set function for changing customer's password.
	 * @param password the new customer's password.
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	/**
	 * Get function that return the customer's coupons.
	 * @return Collection of the customer's Coupons objects.
	 */
	public Collection<Coupon> getCoupons() 
	{
		return coupons;
	}
	
	/**
	 * Set function for changing customer's coupons.
	 * @param coupons the new customer's Coupons objects.
	 */
	public void setCoupons(Collection<Coupon> coupons) 
	{
		this.coupons = coupons;
	}

	/**
	 * This function presents all the customer's details.
	 * @return String with all the customer's details.
	 */
	@Override
	public String toString() 
	{
		return "Customer [id=" + id + ", cust_name=" + name + ", password=" + password + "]";
	}	
}
