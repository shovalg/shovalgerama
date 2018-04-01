package com.shoval.coupons.system.exceptions;

/**
 * This exception used for notifications about cases of trying to get a connection from the connection pool in shutdown mode.
 * <br>This is a Runtime exception.</br>
 * @author Shoval_G
 * @version 1.0
 * @category SystemShutDownException
 */
public class SystemShutDownException extends RuntimeException{

	/**
	 * Default constructor.
	 * @param message shown when an exception occur.
	 */
	public SystemShutDownException(String message) 
	{
		super(message);
	}

}
