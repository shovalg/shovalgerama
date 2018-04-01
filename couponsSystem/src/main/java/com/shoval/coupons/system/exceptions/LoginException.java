package com.shoval.coupons.system.exceptions;

/**
 * This exception is used for cases of wrong login details by users.
 * <br>Used in login function at AdminFacade, CompanyFacade and CustomerFacade classes.</br>
 * This is a Runtime exception.
 * @author Shoval_G
 * @version 1.0
 * @category LoginException
 */
public class LoginException extends RuntimeException{
	
	/**
	 * Default constructor.
	 * @param message shown when an exception occur.
	 */
	public LoginException(String message) 
	{
		super(message);
	}
}
