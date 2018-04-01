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
import javax.persistence.OneToMany;

/**
 * This table entity (COMPANY) represent the fields of object Company in the database.
 * <br>The Company table is connected to the Coupon table by one to many relation.</br>
 * @author Shoval_G
 * @version 1.0
 * @category Company table
 */
@Entity(name="COMPANY")
public class Company {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	private Collection<Coupon> coupons;

	/**
	 * Default constructor
	 */
	public Company() 
	{
		super();
	}
	
	/**
	 * Main constructor
	 * @param name company name
	 * @param password company password
	 * @param email company email
	 */
	public Company(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}

	/**
	 * Get function that return the company's id.
	 * @return long identifier of company's id.
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * Set function for changing company's id.
	 * @param id the new company's id.
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * Get function that return the company's name.
	 * @return String identifier of company's name.
	 */
	public String getCompName() 
	{
		return name;
	}

	/**
	 * Set function for changing company's name.
	 * @param name the new company's name.
	 */
	public void setCompName(String name) 
	{
		this.name = name;
	}

	/**
	 * Get function that return the company's password.
	 * @return String identifier of company's password.
	 */
	public String getPassword() 
	{
		return password;
	}

	/**
	 * Set function for changing company's password.
	 * @param password the new company's password.
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}

	/**
	 * Get function that return the company's email.
	 * @return String identifier of company's email.
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * Set function for changing company's email.
	 * @param email the new company's email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get function that return the company's coupons.
	 * @return Collection of the company's Coupons objects.
	 */
	public Collection<Coupon> getCoupons() 
	{
		return coupons;
	}

	/**
	 * Set function for changing company's coupons.
	 * @param coupons the new company's Coupons objects.
	 */
	public void setCoupons(Collection<Coupon> coupons) 
	{
		this.coupons = coupons;
	}

	/**
	 * This function presents all the company's details.
	 * @return String with all the company's details.
	 */
	@Override
	public String toString() 
	{
		return "Company [id=" + id + ", comp_name=" + name + ", password=" + password + ", email=" + email + "]";
	}
}
