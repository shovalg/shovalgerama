package com.shoval.coupons.system.facades;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dbdao.CompanyDBDAO;
import com.shoval.coupons.system.dbdao.CouponDBDAO;
import com.shoval.coupons.system.exceptions.CouponExistException;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

@Component
public class CompanyFacade implements CouponClientFacade{

	private CompanyDBDAO companyDB;
	private CouponDBDAO couponDB;
	public CompanyFacade() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public void createCoupon(Coupon coupon)
	{		
		for (Coupon companyCoupon : companyDB.getCoupons()) 
		{
			if(companyCoupon.getTitle().equals(coupon.getTitle()))
			{
				throw new CouponExistException("A coupon with the same title is already exist!");
			}
		}
		companyDB.getCoupons().add(coupon);
	}
	
	//needed to be tested
	public void removeCoupon(Coupon coupon)
	{
		couponDB.removeCoupon(coupon);
	}
	
	public void updateCoupon(Coupon coupon)
	{
		Coupon companyCoupon = companyDB.getCouponByTitle(coupon.getTitle());
		companyCoupon.setPrice(coupon.getPrice());
		companyCoupon.setEnd_date(coupon.getEnd_date());
	}
	
	public Coupon getCoupon(long id)
	{
		return companyDB.getCoupons().get((int)id);
	}
	
	public ArrayList<Coupon> getAllCoupons()
	{
		return companyDB.getCoupons();
		
	}
	
	public ArrayList<Coupon> getCouponsByType(CouponType type)
	{
//		ArrayList<Coupon> couponsByType = new ArrayList<>();
//		for (Coupon couponByType : couponDB.getCouponsByType(type)) 
//		{
//			if(this.getAllCoupons().contains(couponByType))
//			{
//				couponsByType.add(couponByType);
//			}
//		}
//		return couponsByType;
		return companyDB.getCouponsByType(type);
	}
	
	public ArrayList<Coupon> getCouponsByPrice(Double price)
	{
		return companyDB.getCouponsByPrice(price);
	}
	
	public ArrayList<Coupon> getCouponsByDate(Calendar date)
	{
		return companyDB.getCouponsByDate(date);
	}
	
	@Override
	public CouponClientFacade login(String name, String password)
	{
		this.companyDB = new CompanyDBDAO();
		if(this.companyDB.login(name, password))
		{
			return this;
		}
		return null;
	}

}
