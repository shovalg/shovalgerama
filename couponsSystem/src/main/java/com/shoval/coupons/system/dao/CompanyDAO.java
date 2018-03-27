package com.shoval.coupons.system.dao;

import java.util.Collection;

import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Coupon;

/**
 * 
 * @author Shoval_G
 *
 */
public interface CompanyDAO {

	/**
	 * 
	 * @param company
	 */
	void createCompany(Company company);
	/**
	 * The function is abstract and responsible for creating a required company.
	 * @param company to be created.
	 */
	void removeCompany(Company company);
	/**
	 * The function is abstract and responsible for updating a required company.
	 * @param company to be updated.
	 */
	void updateCompany(Company company);
	/**
	 * The function is abstract and responsible for getting a company from the DB by it's PK - id parameter.
	 * @param id id parameter is the PK for the customer object stored in the DB.
	 * @return Company
	 */
	Company getCompany(long id);
	/**
	 * The function is abstract and responsible for getting all companies from the DB.
	 * @return Collection of all companies to the company facade layer.
	 */
	Collection<Company> getAllCompanies();
	/**
	 * The function is abstract and responsible for getting all coupons from the DB.
	 * @return Collection of all coupons to the company facade layer.
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
