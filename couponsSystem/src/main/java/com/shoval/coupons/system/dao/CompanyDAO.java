package com.shoval.coupons.system.dao;

import java.util.Collection;

import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Coupon;

/**
 * This interface is responsible for reading and writing to the Company table in the database.
 * @author Shoval_G
 * @version 1.0
 * @category CompanyDAO interface
 */
public interface CompanyDAO {

	/**
	 * The function is abstract and responsible for creating a required company.
	 * @param company to be created.
	 */
	void createCompany(Company company);
	
	/**
	 * The function is abstract and responsible for removing a required company.
	 * @param company to be removed.
	 */
	void removeCompany(Company company);
	
	/**
	 * The function is abstract and responsible for updating a required company.
	 * @param company to be updated.
	 */
	void updateCompany(Company company);
	
	/**
	 * The function is abstract and responsible for getting a company from the DB by it's PK - id parameter.
	 * @param id parameter is the PK for the company object stored in the DB.
	 * @return Company
	 */
	Company getCompany(long id);
	
	/**
	 * The function is abstract and responsible for getting all companies from the DB.
	 * @return Iterable of all companies.
	 */
	Iterable<Company> getAllCompanies();
	
	/**
	 * The function is abstract and responsible for getting all coupons of the current connected company from the DB.
	 * @return Collection of all coupons of the current connected company.
	 */
	Collection<Coupon> getCoupons();
	
	/**
	 * The function is abstract and its purpose is for login by user type company.
	 * @param compName company's name
	 * @param password company's password
	 * @return boolean - true if login succeeded and false otherwise.
	 */
	boolean login(String compName, String password);
}
