package com.shoval.coupons.system.dbdao;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.connections.SyncObject;
import com.shoval.coupons.system.dao.CustomerDAO;
import com.shoval.coupons.system.tables.Company;
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
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			customerRepo.save(customer);
			ConnectionPool.getInstance().returnConnection(syncObject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void removeCustomer(Customer customer) 
	{
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			customerRepo.delete(customer.getId());
			ConnectionPool.getInstance().returnConnection(syncObject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}

	@Override
	public void updateCustomer(Customer customer) 
	{
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			customerRepo.save(customer);
			ConnectionPool.getInstance().returnConnection(syncObject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}

	@Override
	public Customer getCustomer(long id) 
	{
		SyncObject syncObject;
		Customer customer;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			customer = customerRepo.findOne(id);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return customer;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Customer> getAllCustomers() 
	{
		SyncObject syncObject;
		ArrayList<Customer> allCustomers;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			allCustomers = (ArrayList<Customer>) customerRepo.findAll();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return allCustomers;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<Coupon> getCoupons() 
	{
		SyncObject syncObject;
		Collection<Coupon> coupons;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			Customer customer = this.getCustomer(connectedId);
			coupons = customer.getCoupons();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return coupons;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer getConnectedCustomer()
	{
		Customer connectedCustomer = this.getCustomer(this.connectedId);
		return connectedCustomer;
	}
	
	public ArrayList<Coupon> getCouponsByType(CouponType type) 
	{
		SyncObject syncObject;
		ArrayList<Coupon> couponsByType;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByType = (ArrayList<Coupon>) customerRepo.getCouponsByType(this.connectedId, type);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByType;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Coupon> getCouponsByPrice(Double price) 
	{
		SyncObject syncObject;
		ArrayList<Coupon> couponsByPrice;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByPrice = (ArrayList<Coupon>) customerRepo.getCouponsByPrice(this.connectedId, price);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByPrice;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer getCustomerByName(String name)
	{
		SyncObject syncObject;
		Customer customer;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			customer = customerRepo.findCustomerByName(name);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return customer;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean customerExistsyName(String name)
	{
		SyncObject syncObject;
		boolean customerExist;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			customerExist = customerRepo.existsByName(name);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return customerExist;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public Coupon getCouponByTitle(String title)
	{
		SyncObject syncObject;
		Coupon coupon;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			coupon = customerRepo.getCouponByTitle(connectedId, title);
			System.out.println(connectedId);
			ConnectionPool.getInstance().returnConnection(syncObject);
			System.out.println(coupon);
			return coupon;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean login(String customerName, String password) {
		
		if(!this.customerExistsyName(customerName))
		{
			return false;
		}
		if (this.getCustomerByName(customerName).getPassword().equals(password)) 
		{
			connectedId = this.getCustomerByName(customerName).getId();
			return true;
		}
		else
		{
			return false;
		}
	}
}
