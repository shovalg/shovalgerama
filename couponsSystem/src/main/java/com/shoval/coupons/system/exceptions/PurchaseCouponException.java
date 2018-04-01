package com.shoval.coupons.system.exceptions;

/**
 * <br>This exception used to prevent from customer users to purchase a coupon at the following cases:</br>
 * <br>1. trying to purchase the same coupon twice.
 * <br>2. trying to purchase an expired coupon.
 * <br>3. trying to purchase an out of stock coupon.</br>
 * <br>This is a Runtime exception.</br>
 * @author Shoval_G
 * @version 1.0
 * @category PurchaseCouponException
 */
public class PurchaseCouponException extends RuntimeException{
	
	/**
	 * Default constructor.
	 * @param message shown when an exception occur.
	 */
	public PurchaseCouponException(String message) 
	{
		super(message);
	}

}
