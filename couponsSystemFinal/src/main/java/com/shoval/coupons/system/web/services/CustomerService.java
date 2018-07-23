package com.shoval.coupons.system.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoval.coupons.system.entrance.CouponSystem;
import com.shoval.coupons.system.facades.CustomerFacade;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

@RestController
@CrossOrigin("*")
public class CustomerService {

	@Autowired
	CouponSystem couponSystem;
	@Autowired
	CustomerFacade customerFacade;
	
	public CustomerService() 
	{
		super();
	}
	
	@RequestMapping(value = "/customer/coupons/bytype/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllPurchasedCouponsByType(@PathVariable("type") String type)
	{
		try 
		{
			CouponType couponType = CouponType.valueOf(type);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(customerFacade.getAllPurchasedCouponsByType(couponType));
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/customer/coupons/byprice/{price}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllPurchasedCouponsByPrice(@PathVariable("price") double price)
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(customerFacade.getAllPurchasedCouponsByPrice(price));
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/customer/coupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllPurchasedCoupons()
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(customerFacade.getAllPurchasedCoupons());
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/customer/coupon", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity purchaseCoupon(@RequestBody Coupon coupon)
	{
		try 
		{
			customerFacade.purchaseCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("coupon was purchased");
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/company/all.coupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllCoupons()
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(customerFacade.getAllCoupons());
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
}