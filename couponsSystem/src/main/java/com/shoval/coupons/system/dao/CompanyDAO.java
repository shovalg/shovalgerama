package com.shoval.coupons.system.dao;

import java.util.ArrayList;

import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Coupon;

public interface CompanyDAO {

	void createCompany(Company company);
	void removeCompany(Company company);
	void updateCompany(Company company);
	Company getCompany(long id);
	ArrayList<Company> getAllCompanies();
	ArrayList<Coupon> getCoupons();
	boolean login(String compName, String password);
}
