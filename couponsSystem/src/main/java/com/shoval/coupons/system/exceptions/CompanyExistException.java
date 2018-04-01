package com.shoval.coupons.system.exceptions;

/**
 * This exception used to prevent from admin users to add a Company object twice to the database.
 * <br>This is a Runtime exception.</br>
 * @author Shoval_G
 * @version 1.0
 * @category CompanyExistException
 */
public class CompanyExistException extends RuntimeException{
	
	/**
	 * Default constructor.
	 * @param message shown when an exception occur.
	 */
	public CompanyExistException(String message) 
	{
		super(message);
	}
}
