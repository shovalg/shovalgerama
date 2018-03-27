package com.shoval.coupons.system.tables;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepo extends CrudRepository<Company, Long>{
	
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.type = :coupon_type")
	List<Coupon> getCouponsByType(@Param("company_id")long company_id, @Param("coupon_type")CouponType couponType);
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.price <= :coupon_price")
	List<Coupon> getCouponsByPrice(@Param("company_id")long company_id, @Param("coupon_price")double couponPrice);
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.end_date <= :coupon_end_date")
	Collection<Coupon> getCouponsByDate(@Param("company_id")long company_id, @Param("coupon_end_date")Date couponDate);
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.title = :coupon_title")
	Coupon getCouponByTitle(@Param("company_id")long company_id, @Param("coupon_title")String couponTitle);
	@Query(value = "SELECT * FROM COMPANY WHERE name = :name", nativeQuery = true)
	Company findCompanyByName(@Param("name") String name);
	boolean existsByName(String name);
}
