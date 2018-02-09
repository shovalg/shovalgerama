package com.shoval.coupons.system.dbdao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dao.CustomerDAO;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;
import com.shoval.coupons.system.tables.Customer;
import com.shoval.coupons.system.tables.CustomerRepo;

@Component
public class CustomerDBDAO implements CustomerDAO{

	private long connectedId;
	@Autowired
	CustomerRepo customerRepo;
	
	public CustomerDBDAO()
	{
		super ();
	}

	@Override
	public void createCustomer(Customer customer) 
	{
		System.out.println("customerDBDAO create");
		customerRepo.save(customer);
		System.out.println("save");
	}

	@Override
	public void removeCustomer(Customer customer) 
	{
		customerRepo.delete(customer.getId());	
	}

	@Override
	public void updateCustomer(Customer customer) 
	{
		customerRepo.save(customer);	
	}

	@Override
	public Customer getCustomer(long id) 
	{
		return customerRepo.findOne(id);
	}

	@Override
	public ArrayList<Customer> getAllCustomers() 
	{
		return (ArrayList<Customer>) customerRepo.findAll();
	}

	@Override
	public ArrayList<Coupon> getCoupons() 
	{
		Customer customer = this.getCustomer(connectedId);
		return (ArrayList<Coupon>) customer.getCoupons();
	}
	
	public ArrayList<Coupon> getCouponsByType(CouponType type) 
	{
		return (ArrayList<Coupon>) customerRepo.getCouponsByType(this.connectedId, type);
	}

	public ArrayList<Coupon> getCouponsByPrice(Double price) 
	{
		return (ArrayList<Coupon>) customerRepo.getCouponsByPrice(this.connectedId, price);
	}
	
	@Override
	public boolean login(String customerName, String password) {
		
		if(!customerRepo.existsByName(customerName))
		{
			throw new LoginException("Customer don't exist!");
		}
		if (customerRepo.findCustomerByName(customerName).getPassword().equals(password)) 
		{
			connectedId = customerRepo.findCustomerByName(customerName).getId();
			return true;
		}
		else
		{
			throw new LoginException("Wrong password!");
		}
	}
	

}
