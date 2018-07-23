package com.shoval.coupons.system.tables;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * This interface acts as a mediator between the Company table and the database and responsible for reading and writing to the database.
 * @author Shoval_G
 * @version 1.0
 * @category CompanyRepo interface
 */
public interface CompanyRepo extends CrudRepository<Company, Long>{
	
	/**
	 * Query that gets collection of all coupons by a given type which belongs to the company with the given id.
	 * @param company_id the id of the connected company.
	 * @param couponType the coupon's type of the requested coupon.
	 * @return Collection of all requested coupons from the database.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.type = :coupon_type")
	Collection<Coupon> getCouponsByType(@Param("company_id")long company_id, @Param("coupon_type")CouponType couponType);
	
	/**
	 * Query that gets collection of all coupons by a given top price which belongs to the company with the given id.
	 * <br>Top price is the price of coupons whose price is lower or equal to the given price.</br>
	 * @param company_id the id of the connected company.
	 * @param couponPrice coupons whose price is lower or equal to the given price.
	 * @return Collection of all requested coupons from the database.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.price <= :coupon_price")
	Collection<Coupon> getCouponsByPrice(@Param("company_id")long company_id, @Param("coupon_price")double couponPrice);
	
	/**
	 * Query that gets collection of all coupons by a given end date which belongs to the company with the given id.
	 * @param company_id the id of the connected company.
	 * @param couponDate is the expiration date of the coupons. 
	 * @return Collection of all requested coupons from the database.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.end_date <= :coupon_end_date")
	Collection<Coupon> getCouponsByDate(@Param("company_id")long company_id, @Param("coupon_end_date")Date couponDate);
	
	/**
	 * Query that gets a coupon by a given coupon's title which belong to the company with the given id.
	 * @param company_id the id of the connected company.
	 * @param couponTitle is the title of the requested coupon.
	 * @return Coupon the requested coupon from the database.
	 */
	@Query("SELECT coupon FROM COMPANY COMP INNER JOIN COMP.coupons AS coupon WHERE COMP.id = :company_id AND coupon.title = :coupon_title")
	Coupon getCouponByTitle(@Param("company_id")long company_id, @Param("coupon_title")String couponTitle);
	
	/**
	 * Query that finds a company from the database by a given name.
	 * @param name the name of the requested company.
	 * @return Company the requested company from the database.
	 */
	@Query(value = "SELECT * FROM COMPANY WHERE name = :name", nativeQuery = true)
	Company findCompanyByName(@Param("name") String name);
	
	/**
	 * Query that checks if a company is exists in the database by it's name.
	 * @param name the company's name.
	 * @return boolean true if company exists and false otherwise.
	 */
	boolean existsByName(String name);
}
