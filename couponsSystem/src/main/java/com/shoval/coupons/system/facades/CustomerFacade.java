package com.shoval.coupons.system.facades;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dbdao.CouponDBDAO;
import com.shoval.coupons.system.dbdao.CustomerDBDAO;
import com.shoval.coupons.system.exceptions.PurchaseCouponException;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

@Component
public class CustomerFacade implements CouponClientFacade{

	private CustomerDBDAO customerDB;
	private CouponDBDAO couponDB;
	
	public CustomerFacade() 
	{
		// TODO Auto-generated constructor stub
	}
	
	//consider to replace runtime exception to checked exception
	public void purchaseCoupon(Coupon coupon)
	{
		if(this.customerDB.getCoupons().contains(coupon))
		{
			throw new PurchaseCouponException("This coupon is already exist!");
		}
		int amount = this.couponDB.getCoupon(coupon.getId()).getAmount();
		if(amount == 0)
		{
			throw new PurchaseCouponException("Coupon is run out!");
		}
		Date today = new Date();
		Calendar endDate = Calendar.getInstance();
		endDate = this.couponDB.getCoupon(coupon.getId()).getEnd_date();
		if(endDate.before(today))
		{
			throw new PurchaseCouponException("Coupon is expired!");
		}
		this.customerDB.getCoupons().add(coupon);
		amount -= 1;
		this.couponDB.getCoupon(coupon.getId()).setAmount(amount);//need to add update coupon and getId to ByName
	}
	
	//In part B of project - replace print with httpResponse
	public void getAllPurchasedCoupons()
	{
		for (Coupon customerCoupon : this.customerDB.getCoupons())
		{
			System.out.println(customerCoupon);
		}
	}
	
	public void getAllPurchasedCouponsByType(CouponType type)
	{
		for (Coupon customerCoupon : this.customerDB.getCouponsByType(type)) 
		{
			System.out.println(customerCoupon);
		}
	}
	
	public void getAllPurchasedCouponsByPrice(Double price)
	{
		for (Coupon customerCoupon : this.customerDB.getCouponsByPrice(price)) 
		{
			System.out.println(customerCoupon);
		}
	}
	
	@Override
	public CouponClientFacade login(String name, String password)
	{
		this.customerDB = new CustomerDBDAO();
		if(this.customerDB.login(name, password))
		{
			return this;
		}
		return null;
	}

}
