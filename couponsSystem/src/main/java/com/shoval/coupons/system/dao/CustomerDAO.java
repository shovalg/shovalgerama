package com.shoval.coupons.system.dao;

import java.util.ArrayList;
import java.util.Collection;

import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.Customer;

public interface CustomerDAO {

	void createCustomer(Customer customer);
	void removeCustomer(Customer customer);
	void updateCustomer(Customer customer);
	Customer getCustomer(long id);
	ArrayList<Customer> getAllCustomers();
	Collection<Coupon> getCoupons();
	boolean login(String custName, String password);
}
