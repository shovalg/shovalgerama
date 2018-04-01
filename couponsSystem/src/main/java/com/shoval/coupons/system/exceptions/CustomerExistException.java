package com.shoval.coupons.system.exceptions;

/**
 * This exception used to prevent from admin users to add a Customer object twice to the database.
 * <br>This is a Runtime exception.</br>
 * @author Shoval_G
 * @version 1.0
 * @category CustomerExistException
 */
public class CustomerExistException extends RuntimeException{
	
	/**
	 * Default constructor.
	 * @param message shown when an exception occur.
	 */
	public CustomerExistException(String message) 
	{
		super(message);
	}
}
