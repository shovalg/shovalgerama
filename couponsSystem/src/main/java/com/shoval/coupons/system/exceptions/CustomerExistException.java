package com.shoval.coupons.system.exceptions;

public class CustomerExistException extends RuntimeException{
	
	public CustomerExistException(String message) 
	{
		super(message);
	}
}
