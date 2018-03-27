package com.shoval.coupons.system.tables;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CouponRepo extends CrudRepository<Coupon, Long>{
	
	@Query(value = "SELECT * FROM COUPON WHERE type = :type", nativeQuery = true)
	Collection<Coupon> findCouponByType(@Param("type") CouponType type);
	Coupon getCouponByTitle(String title);
	boolean existsByTitle(String title);
	@Transactional
	@Modifying
	@Query("DELETE FROM COUPON COUP WHERE COUP.end_date <= :coupon_endDate")
	void deleteCouponByEndDate(@Param("coupon_endDate")Date endDate);
}
