package com.shoval.coupons.system.dao;

import java.util.ArrayList;

import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

public interface CouponDAO {
	
	void createCoupon(Coupon coupon);
	void removeCoupon(Coupon coupon);
	void updateCoupon(Coupon coupon);
	Coupon getCoupon(long id);
	ArrayList<Coupon> getAllCoupons();
	ArrayList<Coupon> getCouponsByType(CouponType type);

}
