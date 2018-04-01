package com.shoval.coupons.system.tables;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * This interface acts as a mediator between the Customer table and the database and responsible for reading and writing to the database.
 * @author Shoval_G
 * @version 1.0
 * @category CustomerRepo interface
 */
public interface CustomerRepo extends CrudRepository<Customer, Long>{
	
	/**
	 * Query that gets collection of all coupons by a given type which belongs to the customer with the given id.
	 * @param customer_id the id of the connected customer.
	 * @param couponType the coupon's type of the requested coupon.
	 * @return Collection of all requested coupons from the database.
	 */
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.type = :coupon_type")
	Collection<Coupon> getCouponsByType(@Param("customer_id")long customer_id, @Param("coupon_type")CouponType couponType);
	
	/**
	 * Query that gets collection of all coupons by a given top price which belongs to the customer with the given id.
	 * <br>Top price is the price of coupons whose price is lower or equal to the given price.</br>
	 * @param customer_id the id of the connected customer.
	 * @param couponPrice coupons whose price is lower or equal to the given price.
	 * @return Collection of all requested coupons from the database.
	 */
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.price <= :coupon_price")
	Collection<Coupon> getCouponsByPrice(@Param("customer_id")long customer_id, @Param("coupon_price")double couponPrice);
	
	/**
	 * Query that gets a coupon by a given coupon's title which belong to the customer with the given id.
	 * @param customer_id the id of the connected customer.
	 * @param couponTitle is the title of the requested coupon.
	 * @return Coupon the requested coupon from the database.
	 */
	@Query("SELECT coupon FROM CUSTOMER CUST INNER JOIN CUST.coupons AS coupon WHERE CUST.id = :customer_id AND coupon.title = :coupon_title")
	Coupon getCouponByTitle(@Param("customer_id")long customer_id, @Param("coupon_title")String couponTitle);
	
	/**
	 * Query that finds a customer from the database by a given name.
	 * @param name the name of the requested customer.
	 * @return Customer the requested customer from the database.
	 */
	@Query(value = "SELECT * FROM CUSTOMER WHERE name = :name", nativeQuery = true)
	Customer findCustomerByName(@Param("name") String name);
	
	/**
	 * Query that checks if a customer is exists in the database by it's name.
	 * @param name the customer's name.
	 * @return boolean true if customer exists and false otherwise.
	 */
	boolean existsByName(String name);
}
