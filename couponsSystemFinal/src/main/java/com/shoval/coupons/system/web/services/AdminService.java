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
import com.shoval.coupons.system.facades.AdminFacade;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Customer;


@RestController
@CrossOrigin("*")
public class AdminService {

	@Autowired
	CouponSystem couponSystem;
	@Autowired
	AdminFacade adminFacade;
	public AdminService() 
	{
		super();
	}
		
	@RequestMapping(value = "/admin/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCompany(@PathVariable("id") long id)
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(adminFacade.getCompany(id));
		}
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/companies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllCompanies()
	{
		try
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(adminFacade.getAllCompanies());
		} 
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage()); 
		}
	}
	
	@RequestMapping(value = "/admin/company", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createCompany(@RequestBody Company company)
	{
		try 
		{
			adminFacade.createCompany(company);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("company has been created");
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/company", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateCompany(@RequestBody Company company)
	{
		try 
		{
			adminFacade.updateCompany(company);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("company has been updated");
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/company/remove/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity removeCompany(@PathVariable("id") long id)
	{
		try 
		{
			Company company = adminFacade.getCompany(id);
			adminFacade.removeCompany(company);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("company has been removed");
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getCustomer(@PathVariable("id") long id)
	{
		try 
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(adminFacade.getCustomer(id));
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity getAllCustomers()
	{
		try
		{
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body(adminFacade.getAllCustomers());
		} 
		catch (Exception e) 
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage()); 
		}
	}
	
	@RequestMapping(value = "/admin/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity createCustomer(@RequestBody Customer customer)
	{
		try 
		{
			adminFacade.createCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("customer has been created");
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/customer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity updateCustomer(@RequestBody Customer customer)
	{
		try 
		{
			adminFacade.updateCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("customer has been updated");
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/admin/customer/remove/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity removeCustomer(@PathVariable("id") long id)
	{
		try 
		{
			Customer customer = adminFacade.getCustomer(id);
			adminFacade.removeCustomer(customer);
			return ResponseEntity.status(HttpStatus.OK).contentType(
					MediaType.TEXT_MARKDOWN).body("customer has been removed");
		}
		catch (Exception e)
		{
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).contentType(
					MediaType.TEXT_PLAIN).body(e.getMessage());
		}
	}
	
}