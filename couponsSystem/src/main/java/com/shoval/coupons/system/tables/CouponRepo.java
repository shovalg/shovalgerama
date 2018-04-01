package com.shoval.coupons.system.tables;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * This interface acts as a mediator between the Coupon table and the database and responsible for reading and writing to the database.
 * @author Shoval_G
 * @version 1.0
 * @category CouponRepo interface
 */
public interface CouponRepo extends CrudRepository<Coupon, Long>{

	/**
	 * Query that finds collection of all coupons by a given type.
	 * @param type the coupon's type of the requested coupon.
	 * @return Collection of all requested coupons from the database.
	 */
	@Query(value = "SELECT * FROM COUPON WHERE type = :type", nativeQuery = true)
	Collection<Coupon> findCouponsByType(@Param("type") CouponType type);
	
	/**
	 * Query that gets a coupon by a given coupon's title.
	 * @param title of the requested coupon.
	 * @return Coupon the requested coupon from the database.
	 */
	Coupon getCouponByTitle(String title);

	/**
	 * Query that checks if a coupon is exists in the database by it's title.
	 * @param title the coupon's title.
	 * @return boolean true if coupon exists and false otherwise.
	 */
	boolean existsByTitle(String title);
	
	/**
	 * Query that delete all the coupons whose end date is before or equal to the given date.
	 * <br>Mainly use by the daily thread to delete expired coupons.</br>
	 * @param endDate is the expiration date of the coupons.
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM COUPON COUP WHERE COUP.end_date <= :coupon_endDate")
	void deleteCouponsByEndDate(@Param("coupon_endDate")Date endDate);
}
