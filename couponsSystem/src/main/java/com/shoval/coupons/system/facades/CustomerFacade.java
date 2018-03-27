package com.shoval.coupons.system.facades;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dbdao.CouponDBDAO;
import com.shoval.coupons.system.dbdao.CustomerDBDAO;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.exceptions.PurchaseCouponException;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;
import com.shoval.coupons.system.tables.Customer;

@Component
public class CustomerFacade implements CouponClientFacade{

	@Autowired
	CustomerDBDAO customerDB;
	@Autowired
	CouponDBDAO couponDB;
	
	public CustomerFacade() 
	{
		// TODO Auto-generated constructor stub
	}
	
	//consider to replace runtime exception to checked exception
	public void purchaseCoupon(Coupon coupon)
	{
		Customer customerFromDB = customerDB.getConnectedCustomer();
		if(customerDB.getCouponByTitle(coupon.getTitle()) != null)
		{
			throw new PurchaseCouponException("This coupon is already exist!");
		}
		int amount = couponDB.getCoupon(coupon.getId()).getAmount();
		if(amount == 0)
		{
			throw new PurchaseCouponException("Coupon is run out!");
		}
		Date today = new Date();
		Date endDate = this.couponDB.getCoupon(coupon.getId()).getEnd_date();
		if(endDate.before(today))
		{
			throw new PurchaseCouponException("Coupon is expired!");
		}
		Collection<Coupon> purchaseCoupon = customerFromDB.getCoupons(); //customerDB.getCoupons();
		Coupon couponFromDB = couponDB.getCouponByTitle(coupon.getTitle());
		purchaseCoupon.add(couponFromDB);
		amount--;
		couponFromDB.setAmount(amount);
//		couponFromDB.setAmount(couponFromDB.getAmount()-1);
		couponDB.updateCoupon(couponFromDB);
		customerDB.updateCustomer(customerFromDB);
	}
	
	public Collection<Coupon> getAllPurchasedCoupons()
	{
		return this.customerDB.getCoupons();
	}
	
	public Collection<Coupon> getAllPurchasedCouponsByType(CouponType type)
	{
		return this.customerDB.getCouponsByType(type); 
	}
	
	public Collection<Coupon> getAllPurchasedCouponsByPrice(double price)
	{
		return this.customerDB.getCouponsByPrice(price); 
	}
	
	public Coupon getCouponByTitle(String title)
	{
		return customerDB.getCouponByTitle(title);
	}
		
	@Override
	public CouponClientFacade login(String name, String password)
	{
		if(this.customerDB.login(name, password))
		{
			return this;
		}
		throw new LoginException("Customer name don't exist or Wrong password!");
	}

}
