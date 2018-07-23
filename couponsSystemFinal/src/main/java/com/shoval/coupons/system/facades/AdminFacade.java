package com.shoval.coupons.system.facades;

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
 * @version 1.0
 * @category AdminFacade
 */
@Component
public class AdminFacade implements CouponClientFacade{

	@Autowired
	CompanyDBDAO companyDBDAO;
	@Autowired
	CustomerDBDAO customerDBDAO;
	
	/**
	 * Default constructor.
	 */
	public AdminFacade() 
	{
		super ();
	}
	
	/**
	 * This function is responsible for creating a company and save it in the database.
	 * <br>The function blocks the option to create two companies with the same name description.</br>
	 * @param company object received from user administrator and will be saved in the database. 
	 * @throws CompanyExistException thrown in case of creating a company with name description that already exist in the database.
	 */
	public void createCompany(Company company)
	{
		if(companyDBDAO.getCompanyByName(company.getName()) != null)
		{
			throw new CompanyExistException("Company name, " + company.getName() + " is already exist. Please change company's name!");
		}
		companyDBDAO.createCompany(company);
		System.out.println(company);
	}
	
	/**
	 * This function is responsible for deleting a company from the database.
	 * <br>The function alerts if the user is trying to delete a company that doesn't exist.</br>
	 * A company that doesn't exist is such that it's name description doesn't exist in the database.
	 * @param company object received from user administrator and will be deleted from the database.
	 * @throws CompanyNotExistException thrown in case of deleting a company with name description that don't exist in the database.
	 */
	public void removeCompany(Company company)
	{
		if(companyDBDAO.getCompanyByName(company.getName()) != null)
		{
			companyDBDAO.removeCompany(company);
		}
		else
		{
			throw new CompanyNotExistException("Company " + company.getName() + " don't exist!");
		}
	}

	/**
	 * This function is responsible for updating a company from the database.
	 * <br>The function alerts if the user is trying to update a company that doesn't exist.</br>
	 * A company that doesn't exist is such that it's name description doesn't exist in the database.
	 * @param company object which receive from user administrator. the password and email which are the object's variables will be update.
	 * @throws CompanyNotExistException thrown in case of updating a company with name description that don't exist in the database.
	 */
	public void updateCompany(Company company)
	{
		Company companyFromDB = companyDBDAO.getCompanyByName(company.getName());
		if(companyFromDB != null)
		{
			companyFromDB.setPassword(company.getPassword());
			companyFromDB.setEmail(company.getEmail());
			companyDBDAO.updateCompany(companyFromDB);
		}
		else
		{
			throw new CompanyNotExistException("Company "  + company.getName() + " don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting a company from the database.
	 * @param id parameter is the PK for the Company object stored in the database.
	 * @return the Company object stored in the database which match the specified id parameter.
	 * @throws CompanyNotExistException thrown in case of null value in Company object that received from the database.
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
	 * This function is responsible for getting a all companies from the database.
	 * @return Iterable of companies objects stored in the database.
	 */
	public Iterable<Company> getAllCompanies()
	{
		return companyDBDAO.getAllCompanies();
	}
	
	/**
	 * This function is responsible for creating a customer and save it in the database.
	 * <br>The function blocks the option to create two customers with the same name description.</br>
	 * @param customer object which receive from user administrator and will be saved in the database. 
	 * @throws CustomerExistException thrown in case of creating a customer with name description that already exist in the database.
	 */
	public void createCustomer(Customer customer)
	{
		if(customerDBDAO.getCustomerByName(customer.getName()) != null)
		{
			throw new CustomerExistException("Customer name, " + customer.getName() + " is already exist. Please change customer's name!");
		}
		customerDBDAO.createCustomer(customer);
	}
	
	/**
	 * This function is responsible for deleting a customer from the database.
	 * <br>The function alerts if the user is trying to delete a customer that doesn't exist.</br>
	 * A customer that doesn't exist is such that it's name description doesn't exist in the database.
	 * @param customer object which receive from user administrator and will be deleted from the database.
	 * @throws CustomerNotExistException thrown in case of deleting a customer with name description that don't exist in the database.
	 */
	public void removeCustomer(Customer customer)
	{
		if(customerDBDAO.getCustomerByName(customer.getName()) != null)
		{
			customerDBDAO.removeCustomer(customer);
		}
		else
		{
			throw new CustomerNotExistException("Customer " + customer.getName() + " don't exist!");
		}
	}
	
	/**
	 * This function is responsible for updating a customer from the database.
	 * <br>The function alerts if the user is trying to update a customer that doesn't exist.</br>
	 * A customer that doesn't exist is such that it's name description doesn't exist in the database.
	 * @param customer object which receive from user administrator. the password which is the object's variable will be update.
	 * @throws CustomerNotExistException thrown in case of updating a customer with name description that don't exist in the database.
	 */
	public void updateCustomer(Customer customer)
	{
		Customer customerFromDB = customerDBDAO.getCustomerByName(customer.getName());
		if(customerFromDB != null)
		{
			customerFromDB.setPassword(customer.getPassword());
			customerDBDAO.updateCustomer(customerFromDB);
		}
		else
		{
			throw new CustomerNotExistException("Customer " + customer.getName() + " don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting a customer from the database.
	 * @param id parameter is the PK for the Customer object stored in the database.
	 * @return the Customer object stored in the database which match the specified id parameter.
	 * @throws CustomerNotExistException thrown in case of null value in Customer object that received from the database.
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
	 * This function is responsible for getting a all customers from the database.
	 * @return Iterable of customers objects stored in the database.
	 */
	public Iterable<Customer> getAllCustomers()
	{
		return customerDBDAO.getAllCustomers();
	}
	
	/**
	 * This function is responsible for getting a company from the database by a given name.
	 * @param name of the requested Company object.
	 * @return the Company object stored in the database which match the specified name parameter.
	 * @throws CompanyNotExistException thrown in case of null value in Customer object that received from the database.
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
			throw new CompanyNotExistException("Company " + name + " don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting a customer from the database by a given name.
	 * @param name of the requested Customer object.
	 * @return the Customer object stored in the database which match the specified name parameter.
	 * @throws CustomerNotExistException thrown in case of null value in Customer object that received from the database.
	 */
	public Customer getCustomerByName(String name)
	{
		Customer customer = customerDBDAO.getCustomerByName(name);
		if(customer != null)
		{
			return customer;
		}
		else
		{
			throw new CustomerNotExistException("Customer " + name + " don't exist!");
		}
	}

	/**{@inheritDoc}
	 * <p>This function is used for administrator user type login.</p>
	 * @throws LoginException indicates about wrong name or password typo.
	 */
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