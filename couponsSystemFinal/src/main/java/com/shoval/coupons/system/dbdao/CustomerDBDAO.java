package com.shoval.coupons.system.dbdao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.connections.SyncObject;
import com.shoval.coupons.system.dao.CustomerDAO;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponRepo;
import com.shoval.coupons.system.tables.CouponType;
import com.shoval.coupons.system.tables.Customer;
import com.shoval.coupons.system.tables.CustomerRepo;

/**
 * This class implements CustomerDAO interface.
 * <br>It lies between CustomerFacade class and CustomerRepo class as a mediator for read and write operations to the database.</br>
 * This class use the CustomerRepo class for those operations.
 * <br>All the functions that want to communicate with the database, get a SyncObject from the Connection pool and return it when finished.</br>
 * @author Shoval_G
 * @version 1.0
 * @category CustomerDBDAO
 */
@Component
public class CustomerDBDAO implements CustomerDAO{

	private long connectedId;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	CouponRepo couponRepo;
	
	/**
	 * Default constructor.
	 */
	public CustomerDBDAO()
	{
		super ();
	}

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CustomerRepo class to save the given Customer object to the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CustomerRepo class to delete the given Customer object from the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CustomerRepo class to update (re-save) the given Customer object to the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CustomerRepo class to get a Customer object from the database by a given id.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CustomerRepo class to get all Customers objects from the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	@Override
	public Iterable<Customer> getAllCustomers() 
	{
		SyncObject syncObject;
		Iterable<Customer> allCustomers;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			allCustomers = customerRepo.findAll();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return allCustomers;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the getConnectedCustomer function in order to receive the current connected customer, and it's coupons.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished in an indirect manner (through getCustomer function).
	 */
	@Override
	public Collection<Coupon> getCoupons() 
	{
		return this.getConnectedCustomer().getCoupons();
	}
	
	/**
	 * <br>This function uses the CouponRepo class to get all Coupons objects from the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	public Iterable<Coupon> getAllCoupons() 
	{
		SyncObject syncObject;
		Iterable<Coupon> allCoupons;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			allCoupons = couponRepo.findAll();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return allCoupons;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function saves the Customer object that successfully logged in.
	 * @return Customer object which is the connected Customer.
	 */
	public Customer getConnectedCustomer()
	{
		Customer connectedCustomer = this.getCustomer(this.connectedId);
		return connectedCustomer;
	}
	
	/**
	 * This function uses the CustomerRepo class to get coupons by a given type.
	 * <br>The coupons belongs to the last Customer that successfully logged in.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 * @param type of the requested coupons.
	 * @return Collection of coupons objects with the requested coupon type.
	 */
	public Collection<Coupon> getCouponsByType(CouponType type)
	{
		SyncObject syncObject;
		Collection<Coupon> couponsByType;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByType = customerRepo.getCouponsByType(this.connectedId, type);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByType;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function uses the CustomerRepo class to get coupons whose price is lower or equal to the given price.
	 * <br>The coupons belongs to the last Customer that successfully logged in.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 * @param price of the requested coupons.
	 * @return Collection of Coupons objects with the requested coupon price.
	 */
	public Collection<Coupon> getCouponsByPrice(double price) 
	{
		SyncObject syncObject;
		Collection<Coupon> couponsByPrice;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByPrice = customerRepo.getCouponsByPrice(this.connectedId, price);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByPrice;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function uses the CustomerRepo class to get customer by a given name.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished.</br>
	 * @param name of the requested customer.
	 * @return Customer object with the requested customer name.
	 */
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
	
	/**
	 * This function uses the CustomerRepo class to check if customer exists in the database by a given name.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished.</br>
	 * @param name of the requested customer. 
	 * @return boolean which indicates whether the requested Customer object exists in the database.
	 */
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
	
	/**
	 * This function uses the CustomerRepo class to get coupon by a given title.
	 * <br>The coupon belongs to the last customer that successfully logged in.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 * @param title of the requested coupon. 
	 * @return Coupon object with the requested coupon title.
	 */
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
	
	/**
	 * <p>{@inheritDoc}</p>
	 * This function uses the CustomerRepo class (in an indirect manner) to check the given credentials (name and password) against the database records.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished in an indirect manner (through CustomerExistsByName and getCustomerByName functions).</br>
	 */
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
