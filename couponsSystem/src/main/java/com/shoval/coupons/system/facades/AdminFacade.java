package com.shoval.coupons.system.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dbdao.CompanyDBDAO;
import com.shoval.coupons.system.dbdao.CustomerDBDAO;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Customer;

@Component
public class AdminFacade implements CouponClientFacade{

	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CustomerDBDAO customerDBDAO;
	
	public AdminFacade() 
	{
		super ();
	}
	
	@Override
	public CouponClientFacade login(String name, String password)
	{
		if(name.equals("admin") && password.equals("1234"))
		{
			return this;
		}
		return null;
	}

	
	public void createCompany(Company company)
	{
		companyDBDAO.createCompany(company);
	}

	public void createCustomer(Customer customer)
	{
		System.out.println("create customer");
		customerDBDAO.createCustomer(customer);
		System.out.println("go to DBDAO");
	}
}
