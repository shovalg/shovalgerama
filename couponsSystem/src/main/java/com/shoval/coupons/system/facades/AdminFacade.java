package com.shoval.coupons.system.facades;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dbdao.CompanyDBDAO;
import com.shoval.coupons.system.dbdao.CustomerDBDAO;
import com.shoval.coupons.system.exceptions.CompanyExistException;
import com.shoval.coupons.system.exceptions.CompanyNotExistException;
import com.shoval.coupons.system.exceptions.CustomerExistException;
import com.shoval.coupons.system.exceptions.CustomerNotExistException;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Customer;

/**
 * This class is one of three management classes.
 * <br>Each one of this classes is responsible for all the logic behind an end user type.</br>
 * This class is responsible for administrator user type.
 * @author Shoval_G
 * @category AdminFacade
 */
@Component
public class AdminFacade implements CouponClientFacade{

	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CustomerDBDAO customerDBDAO;
	
	/**
	 * Default constructor - AdminFacade
	 */
	public AdminFacade() 
	{
		super ();
	}
	
	/**
	 * <br>This function is responsible for creating a company and save it in the DB</br>
	 * The function blocks the option to create two companies with the same name description
	 * @param company company object which receive from user administrator and will be saved in the DB. 
	 * @throws InterruptedException 
	 * @throws CompanyExistException thrown in case of creating a company with name description that already exist in the DB.
	 */
	public void createCompany(Company company)
	{
		if(companyDBDAO.getCompanyByName(company.getCompName()) != null)
		{
			throw new CompanyExistException("Company name is already exist. Please change company's name!");
		}
		companyDBDAO.createCompany(company);
	}
	
	/**
	 * This function is responsible for deleting a company from the DB.
	 * <br>The function alerts if the user is trying to delete a company that doesn't exist.</br>
	 * A company that doesn't exist is such that it's name description doesn't exist in the DB.
	 * @param company company object which receive from user administrator and will be deleted from the DB.
	 * @throws CompanyNotExistException thrown in case of deleting a company with name description that don't exist in the DB.
	 */
	public void removeCompany(Company company)
	{
		if(companyDBDAO.getCompanyByName(company.getCompName()) != null)
		{
			companyDBDAO.removeCompany(company);
		}
		else
		{
			throw new CompanyNotExistException("Company don't exist!");
		}
		//Company companyFromDB = companyDBDAO.getCompanyByName(comq6` pany.getCompName());
		//companyFromDB.getCoupons().clear();
		//companyFromDB.setCoupons(companyFromDB.getCoupons());
	}

	/**
	 * This function is responsible for updating a company from the DB.
	 * <br>The function alerts if the user is trying to update a company that doesn't exist.</br>
	 * A company that doesn't exist is such that it's name description doesn't exist in the DB.
	 * @param company company object which receive from user administrator. the password and email which are the object's variables will be update.
	 * @throws CompanyNotExistException thrown in case of updating a company with name description that don't exist in the DB.
	 */
	public void updateCompany(Company company)
	{
		Company companyFromDB = companyDBDAO.getCompanyByName(company.getCompName());
		if(companyFromDB != null)
		{
			companyFromDB.setPassword(company.getPassword());
			companyFromDB.setEmail(company.getEmail());
			companyDBDAO.updateCompany(companyFromDB);
		}
		else
		{
			throw new CompanyNotExistException("Company don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting a company from the DB.
	 * @param id id parameter is the PK for the company object stored in the DB.
	 * @return the company object stored in the DB which match the specified id parameter.
	 * @throws CompanyNotExistException thrown in case of null value in company object that received from the DB.
	 */
	public Company getCompany(long id)
	{
		Company company = companyDBDAO.getCompany(id);
		if(company != null)
		{
			return company;
		}
		else
		{
			throw new CompanyNotExistException("Company don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting a all companies from the DB.
	 * @return Collection of companies objects stored in the DB.
	 */
	public Collection<Company> getAllCompanies()
	{
		return companyDBDAO.getAllCompanies();
	}
	
	/**
	 * <br>This function is responsible for creating a customer and save it in the DB</br>
	 * The function blocks the option to create two customers with the same name description
	 * @param customer customer object which receive from user administrator and will be saved in the DB. 
	 * @throws CustomerExistException thrown in case of creating a customer with name description that already exist in the DB.
	 */
	public void createCustomer(Customer customer)
	{
		if(customerDBDAO.getCustomerByName(customer.getCust_name()) != null)
		{
			throw new CustomerExistException("Customer name is already exist. Please change customer's name!");
		}
		customerDBDAO.createCustomer(customer);
	}
	
	/**
	 * This function is responsible for deleting a customer from the DB.
	 * <br>The function alerts if the user is trying to delete a customer that doesn't exist.</br>
	 * A customer that doesn't exist is such that it's name description doesn't exist in the DB.
	 * @param customer customer object which receive from user administrator and will be deleted from the DB.
	 * @throws CustomerNotExistException thrown in case of deleting a customer with name description that don't exist in the DB.
	 */
	public void removeCustomer(Customer customer)
	{
		if(customerDBDAO.getCustomerByName(customer.getCust_name()) != null)
		{
			customerDBDAO.removeCustomer(customer);
		}
		else
		{
			throw new CustomerNotExistException("Customer don't exist!");
		}
	}
	
	/**
	 * This function is responsible for updating a customer from the DB.
	 * <br>The function alerts if the user is trying to update a customer that doesn't exist.</br>
	 * A customer that doesn't exist is such that it's name description doesn't exist in the DB.
	 * @param customer customer object which receive from user administrator. the password which is the object's variable will be update.
	 * @throws CustomerNotExistException thrown in case of updating a customer with name description that don't exist in the DB.
	 */
	public void updateCustomer(Customer customer)
	{
		Customer customerFromDB = customerDBDAO.getCustomerByName(customer.getCust_name());
		if(customerFromDB != null)
		{
			customerFromDB.setPassword(customer.getPassword());
			customerDBDAO.updateCustomer(customerFromDB);
		}
		else
		{
			throw new CustomerNotExistException("Customer don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting a customer from the DB.
	 * @param id id parameter is the PK for the customer object stored in the DB.
	 * @return the customer object stored in the DB which match the specified id parameter.
	 * @throws CustomerNotExistException thrown in case of null value in customer object that received from the DB.
	 */
	public Customer getCustomer(long id)
	{
		Customer customer = customerDBDAO.getCustomer(id);
		if(customer != null)
		{
			return customer;
		}
		else
		{
			throw new CustomerNotExistException("Customer don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting a all customers from the DB.
	 * @return Collection of customers objects stored in the DB.
	 */
	public Collection<Customer> getAllCustomers()
	{
		return customerDBDAO.getAllCustomers();
	}
	
	/**{@inheritDoc}
	 * <p>This function is used for administrator user type login</p>
	 * 
	 */
	
	public Company getCompanyByName(String name)
	{
		Company company = companyDBDAO.getCompanyByName(name);
		if(company != null)
		{
			return company;
		}
		else
		{
			throw new CompanyNotExistException("Company don't exist!");
		}
	}
	
	public Customer getCustomerByName(String name)
	{
		Customer customer = customerDBDAO.getCustomerByName(name);
		if(customer != null)
		{
			return customer;
		}
		else
		{
			throw new CustomerNotExistException("Customer don't exist!");
		}
	}

	@Override
	public CouponClientFacade login(String name, String password)
	{
		if(name.equals("admin") && password.equals("1234"))
		{
			return this;
		}
		throw new LoginException("Wrong name or password!");
	}
}
