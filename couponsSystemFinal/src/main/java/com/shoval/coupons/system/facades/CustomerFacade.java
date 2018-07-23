package com.shoval.coupons.system.facades;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.connections.SyncObject;
import com.shoval.coupons.system.dbdao.CouponDBDAO;
import com.shoval.coupons.system.dbdao.CustomerDBDAO;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.exceptions.PurchaseCouponException;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;
import com.shoval.coupons.system.tables.Customer;

/**
 * This class is one of three management classes.
 * <br>Each one of this classes is responsible for all the logic behind an end user type.</br>
 * This class is responsible for customer user type.
 * @author Shoval_G
 * @version 1.0
 * @category CustomerFacade
 */
@Component
public class CustomerFacade implements CouponClientFacade{

	@Autowired
	CustomerDBDAO customerDB;
	@Autowired
	CouponDBDAO couponDB;

	/**
	 * Default constructor.
	 */
	public CustomerFacade() 
	{
		super();
	}
	
	/**
	 * This function is responsible for the ability to purchase coupon from the database by customer users.
	 * @param coupon object received from user customer to be purchased from the database.
	 * <p>@throws PurchaseCouponException in three cases:</p>
	 *     1. When customer users will try to purchase a coupon that they already have.
	 * <br>2. When customer users will try to purchase an expired coupon (end date has passed).
	 * <br>3. When customer users will try to purchase an out of stock coupon (amount = 0).</br>
	 */
	public void purchaseCoupon(Coupon coupon)
	{
		Customer customerFromDB = customerDB.getConnectedCustomer();
		if(customerDB.getCouponByTitle(coupon.getTitle()) != null)
		{
			throw new PurchaseCouponException("Coupon " + coupon.getTitle() + " has already been purchased!");
		}
		int amount = couponDB.getCoupon(coupon.getId()).getAmount();
		if(amount == 0)
		{
			throw new PurchaseCouponException("Coupon " + coupon.getTitle() + " has run out!");
		}
		Date today = new Date();
		Date endDate = this.couponDB.getCoupon(coupon.getId()).getEnd_date();
		if(endDate.before(today))
		{
			throw new PurchaseCouponException("Coupon " + coupon.getTitle() + " expired!");
		}
		Collection<Coupon> purchaseCoupon = customerFromDB.getCoupons();
		Coupon couponFromDB = couponDB.getCouponByTitle(coupon.getTitle());
		purchaseCoupon.add(couponFromDB);
		amount--;
		couponFromDB.setAmount(amount);
		couponDB.updateCoupon(couponFromDB);
		customerDB.updateCustomer(customerFromDB);
	}
	
	/**
	 * This function is responsible for getting all purchased coupons from the database according to the current connected customer.
	 * @return Collection of coupons objects that the customer purchased.
	 */
	public Collection<Coupon> getAllPurchasedCoupons()
	{
		return this.customerDB.getCoupons();
	}
	
	/**
	 * This function is responsible for getting all purchased coupons from the database according to the current connected customer by a given coupon type.
	 * @param type the coupon type of the coupons the customer users want to get from the database.
	 * @return Collection of purchased coupons objects with the given type.
	 */
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type)
	{
		return this.customerDB.getCouponsByType(type); 
	}
	
	/**
	 * This function is responsible for getting all purchased coupons from the database according to the current connected customer by a given coupons top price.
	 * @param price is the top price which is all the coupons whose price is lower or equal to the given price.
	 * @return Collection of purchased coupons objects with the given top price.
	 */
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price)
	{
		return this.customerDB.getCouponsByPrice(price); 
	}
	
	/**
	 * This function is responsible for getting a coupon from the database according to the current connected customer by a given coupon title.
	 * @param title is the coupon title.
	 * @return Coupon object with the given title.
	 */
	public Coupon getCouponByTitle(String title)
	{
		return customerDB.getCouponByTitle(title);
	}
	
	/**
	 * This function is responsible for getting a all companies from the database.
	 * @return Iterable of companies objects stored in the database.
	 */
	public Iterable<Coupon> getAllCoupons()
	{
		return customerDB.getAllCoupons();
	}
	
	/**{@inheritDoc}
	 * <p>This function is used for customer user type login.</p>
	 * @throws LoginException indicates about wrong name or password typo.
	 */
	@Override
	public CouponClientFacade login(String name, String password)
	{
		if(this.customerDB.login(name, password))
		{
			return this;
		}
		throw new LoginException("Customer " + name + " don't exist or Wrong password!");
	}

}
