package com.shoval.coupons.system.tables;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CouponRepo extends CrudRepository<Coupon, Long>{
	
	//@Query("SELECT customer FROM COUPON INNER JOIN COUPON.customers AS customer WHERE coupon.type = :coupon_type")
	//List<Coupon> getCouponsByType(@Param("coupon_type")CouponType couponType);
	//@Query(SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND COUPON.)
	List<Coupon> findByType(CouponType type);
	Coupon getCouponByTitle(String title);
	boolean existsByTitle(String title);
	//Coupon findCouponByTitleAndCompany_id(String title, long id);
	//List<Coupon> findCouponsByTypeAndCompany_id(CouponType type, long id);
	//@Query("SELECT coupon FROM COUPON WHERE company.id = :company_id AND coupon.price <= :coupon_price")
	//List<Coupon> getCompanyCouponsByMaxPrice(@Param("company_id")long company_id, @Param("coupon_price")double couponPrice);
	//List<Coupon> findCouponsIsLessThanEqualPriceAndCompany_id(Double price, long id);
	
	//List<Coupon> findCouponsIsLessThanEqualDateAndCompany_id(Calendar date, long id);
}
