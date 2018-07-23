package com.shoval.coupons.system.dao;

import java.util.Collection;

import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

/**
 * This interface is responsible for reading and writing to the Coupon table in the database.
 * @author Shoval_G
 * @version 1.0
 * @category CouponDAO interface
 */
public interface CouponDAO {
	
	/**
	 * The function is abstract and responsible for creating a required coupon.
	 * @param coupon to be created.
	 */
	void createCoupon(Coupon coupon);
	
	/**
	 * The function is abstract and responsible for removing a required coupon.
	 * @param coupon to be removed.
	 */
	void removeCoupon(Coupon coupon);
	
	/**
	 * The function is abstract and responsible for updating a required coupon.
	 * @param coupon to be updated.
	 */
	void updateCoupon(Coupon coupon);
	
	/**
	 * The function is abstract and responsible for getting a coupon from the DB by it's PK - id parameter.
	 * @param id parameter is the PK for the coupon object stored in the DB.
	 * @return Coupon
	 */
	Coupon getCoupon(long id);
	
	/**
	 * The function is abstract and responsible for getting all coupons from the DB.
	 * @return Iterable of all coupons.
	 */
	Iterable<Coupon> getAllCoupons();

	/**
	 * The function is abstract and responsible for getting all coupons from the DB that's corresponds to a given coupon type.
	 * @return Collection of all coupons with the given coupon type.
	 */
	Collection<Coupon> getCouponsByType(CouponType type);
}
