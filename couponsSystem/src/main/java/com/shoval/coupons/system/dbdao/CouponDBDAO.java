package com.shoval.coupons.system.dbdao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dao.CouponDAO;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponRepo;
import com.shoval.coupons.system.tables.CouponType;

@Component
public class CouponDBDAO implements CouponDAO{

	@Autowired
	CouponRepo couponRepo;
	
	public CouponDBDAO() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCoupon(Coupon coupon) 
	{
		couponRepo.save(coupon);	
	}

	@Override
	public void removeCoupon(Coupon coupon) 
	{
		couponRepo.delete(coupon.getId());	
	}

	@Override
	public void updateCoupon(Coupon coupon) 
	{
		couponRepo.save(coupon);	
	}

	@Override
	public Coupon getCoupon(long id) 
	{
		return couponRepo.findOne(id);
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() 
	{
		return (ArrayList<Coupon>) couponRepo.findAll();
	}

	@Override
	public ArrayList<Coupon> getCouponsByType(CouponType type) 
	{
		return (ArrayList<Coupon>) couponRepo.findByType(type);
	}
	
}
