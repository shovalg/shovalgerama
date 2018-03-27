package com.shoval.coupons.system.facades;

/**
 * An interface for login function which global for all clients types.
 * @author Shoval_G
 */
public interface CouponClientFacade {

	/**
	 * The function is responsible for login by user type.
	 * <br>There are three types of users: administrator = "admin", company and customer.</br>
	 * If there is no match, the function will return null.
	 * @param name user name
	 * @param password user password
	 * @return CouponClientFacade
	 */
	CouponClientFacade login (String name, String password);

}
