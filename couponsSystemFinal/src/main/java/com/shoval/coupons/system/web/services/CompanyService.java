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
import com.shoval.coupons.system.facades.CompanyFacade;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

@RestController
@CrossOrigin("*")
public class CompanyService {

	@Autowired
	CouponSystem couponSystem;
	@Autowired
	CompanyFacade companyFacade;
	
	public CompanyService() 
	{
		super();
	}
	
	@RequestMapping(value = "/company/coupon/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCoupon(@PathVariable("id") long id)
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(companyFacade.getCoupon(id));
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/company/coupons/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCouponsByType(@PathVariable("type") String type)
	{ 
		try 
		{
			CouponType couponType = CouponType.valueOf(type);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(companyFacade.getCouponsByType(couponType));
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/company/coupons", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllCoupons()
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(companyFacade.getAllCoupons());
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/company/coupon", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createCoupon(@RequestBody Coupon coupon)
	{
		try 
		{
			companyFacade.createCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("coupon has been created");
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/company/coupon", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateCoupon(@RequestBody Coupon coupon)
	{	
		try 
		{
			companyFacade.updateCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("coupon has been updated");
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/company/coupon/remove/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity removeCoupon(@PathVariable("id") long id)
	{
		try 
		{
			Coupon coupon = companyFacade.getCoupon(id);
			companyFacade.removeCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("coupon has been removed");
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
}