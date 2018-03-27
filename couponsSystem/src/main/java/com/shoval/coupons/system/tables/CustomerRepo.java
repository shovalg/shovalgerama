package com.shoval.coupons.system.tables;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends CrudRepository<Customer, Long>{
	
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.type = :coupon_type")
	List<Coupon> getCouponsByType(@Param("customer_id")long customer_id, @Param("coupon_type")CouponType couponType);
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.price <= :coupon_price")
	List<Coupon> getCouponsByPrice(@Param("customer_id")long customer_id, @Param("coupon_price")double couponPrice);
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.title = :coupon_title")
	Coupon getCouponByTitle(@Param("customer_id")long customer_id, @Param("coupon_title")String couponTitle);
	@Query(value = "SELECT * FROM CUSTOMER WHERE name = :name", nativeQuery = true)
	Customer findCustomerByName(@Param("name") String name);
	boolean existsByName(String name);
}
