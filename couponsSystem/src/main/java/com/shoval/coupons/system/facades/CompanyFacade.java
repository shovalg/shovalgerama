package com.shoval.coupons.system.facades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dbdao.CompanyDBDAO;
import com.shoval.coupons.system.dbdao.CouponDBDAO;
import com.shoval.coupons.system.exceptions.CompanyExistException;
import com.shoval.coupons.system.exceptions.CouponExistException;
import com.shoval.coupons.system.exceptions.CouponNotExistException;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

@Component
public class CompanyFacade implements CouponClientFacade{

	@Autowired
	CompanyDBDAO companyDB;
	@Autowired
	CouponDBDAO couponDB;
	
	public CompanyFacade() 
	{
		super();
	}
	
	public void createCoupon(Coupon coupon)
	{	
		if(couponDB.couponExistsByTitle(coupon.getTitle()))
		{
			throw new CouponExistException("A coupon with the same title is already exist!");
		}
		Company connectedCompany = companyDB.getConnectedCompany();
		couponDB.createCoupon(coupon);
		Collection<Coupon> coupons = companyDB.getCoupons();
		coupons.add(coupon);
		connectedCompany.setCoupons(coupons);
		companyDB.updateCompany(connectedCompany);
	}
	
	public void removeCoupon(Coupon coupon)
	{
		Company connectedCompany = companyDB.getConnectedCompany();
		couponDB.removeCoupon(coupon);
		Collection<Coupon> coupons = companyDB.getCoupons();
		coupons.remove(coupon);
		connectedCompany.setCoupons(coupons);
		companyDB.updateCompany(connectedCompany);
	}
	
	public void updateCoupon(Coupon coupon)
	{
		Coupon companyCoupon = companyDB.getCouponByTitle(coupon.getTitle());
		companyCoupon.setPrice(coupon.getPrice());
		companyCoupon.setEnd_date(coupon.getEnd_date());
	}
	
	public Coupon getCoupon(long id)
	{
		return couponDB.getCoupon(id);
	}
	
	public Collection<Coupon> getAllCoupons()
	{
		return companyDB.getCoupons();
		
	}
	
	public ArrayList<Coupon> getCouponsByType(CouponType type)
	{
		return companyDB.getCouponsByType(type);
	}
	
	public ArrayList<Coupon> getCouponsByPrice(double price)
	{
		return companyDB.getCouponsByPrice(price);
	}
	
	public ArrayList<Coupon> getCouponsByDate(Date date)
	{
		return companyDB.getCouponsByDate(date);
	}
	
	public Coupon getCouponByTitle(String title)
	{
		if(companyDB.getCouponByTitle(title) == null)
		{
			throw new CouponNotExistException("Coupon is not exist!");
		}
		return companyDB.getCouponByTitle(title);
	}
	
	@Override
	public CouponClientFacade login(String name, String password)
	{	
		if(this.companyDB.login(name, password))
		{
			return this;
		}
		throw new LoginException("Company name don't exist or Wrong password!");
	}

}
