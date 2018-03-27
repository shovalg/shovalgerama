package com.shoval.coupons.system.exceptions;

public class CustomerNotExistException extends RuntimeException{
	
	public CustomerNotExistException(String message) 
	{
		super(message);
	}

}
