package com.shoval.coupons.system.exceptions;

/**
 * This exception used to prevent from company users to add a Coupon object twice to the database.
 * <br>This is a Runtime exception.</br>
 * @author Shoval_G
 * @version 1.0
 * @category CouponExistException
 */
public class CouponExistException extends RuntimeException{
	
	/**
	 * Default constructor.
	 * @param message shown when an exception occur.
	 */
	public CouponExistException(String message) 
	{
		super(message);
	}

}
