package com.shoval.coupons.system.exceptions;

/**
 * This exception used for notifications about mismatch between a Company object search and it's record in the database.
 * <br>This is a Runtime exception.</br>
 * @author Shoval_G
 * @version 1.0
 * @category CompanyNotExistException
 */
public class CompanyNotExistException extends RuntimeException{

	/**
	 * Default constructor.
	 * @param message shown when an exception occur.
	 */
	public CompanyNotExistException(String message) 
	{
		super(message);
	}
}
