package com.shoval.coupons.system.dao;

import java.util.Collection;

import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.Customer;

/**
 * This interface is responsible for reading and writing to the Customer table in the database.
 * @author Shoval_G
 * @version 1.0
 * @category CustomerDAO interface
 */
public interface CustomerDAO {

	/**
	 * The function is abstract and responsible for creating a required customer.
	 * @param customer to be created.
	 */
	void createCustomer(Customer customer);
	
	/**
	 * The function is abstract and responsible for removing a required customer.
	 * @param customer to be removed.
	 */
	void removeCustomer(Customer customer);
	
	/**
	 * The function is abstract and responsible for updating a required customer.
	 * @param customer to be updated.
	 */
	void updateCustomer(Customer customer);
	
	/**
	 * The function is abstract and responsible for getting a customer from the DB by it's PK - id parameter.
	 * @param id parameter is the PK for the customer object stored in the DB.
	 * @return Customer
	 */
	Customer getCustomer(long id);
	
	/**
	 * The function is abstract and responsible for getting all customers from the DB.
	 * @return Iterable of all customers.
	 */
	Iterable<Customer> getAllCustomers();
	
	/**
	 * The function is abstract and responsible for getting all coupons purchased by the customer from the DB.
	 * @return Collection of all coupons purchased by the customer.
	 */
	Collection<Coupon> getCoupons();
	
	/**
	 * The function is abstract and its purpose is for login by user type customer.
	 * @param custName customer's name
	 * @param password customer's password
	 * @return boolean - true if login succeeded and false otherwise.
	 */
	boolean login(String custName, String password);
}
