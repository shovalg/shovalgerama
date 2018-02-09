package com.shoval.coupons.system.dbdao;


import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dao.CompanyDAO;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.CompanyRepo;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponRepo;
import com.shoval.coupons.system.tables.CouponType;

@Component
public class CompanyDBDAO implements CompanyDAO{

	private long connectedId;
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	CouponRepo couponRepo;
	
	public CompanyDBDAO() 
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void createCompany(Company company) 
	{
		companyRepo.save(company);	
	}

	@Override
	public void removeCompany(Company company) 
	{
		companyRepo.delete(company.getId());	
	}

	@Override
	public void updateCompany(Company company)
	{
		companyRepo.save(company);	
	}

	@Override
	public Company getCompany(long id)
	{
		return companyRepo.findOne(id);
	}

	@Override
	public ArrayList<Company> getAllCompanies() 
	{
		return (ArrayList<Company>) companyRepo.findAll();
	}

	@Override
	public ArrayList<Coupon> getCoupons() 
	{
		Company company = this.getCompany(this.connectedId);
		return (ArrayList<Coupon>) company.getCoupons();
	}
	
	public Coupon getCouponByTitle(String title)
	{
//		Company company = this.getCompany(this.connectedId);
//		for (Coupon companyCoupon : company.getCoupons()) 
//		{
//			if(companyCoupon.getTitle().equals(couponRepo.getCouponByTitle(title)))
//			{
//				return couponRepo.getCouponByTitle(title);
//			}
//		}
//		return null;
//		Coupon companyCoupon = couponRepo.findCouponByTitleAndCompany_id(title, this.connectedId);
//		if(companyCoupon == null)
//		{
//			throw new CouponNotExistException("Coupon not exist!");
//		}
		return null;//companyCoupon;
	}
	
	public ArrayList<Coupon> getCouponsByType(CouponType type) 
	{
		return null;//(ArrayList<Coupon>) couponRepo.findCouponsByTypeAndCompany_id(type, this.connectedId);
	}

	public ArrayList<Coupon> getCouponsByPrice(Double price) 
	{
		return null;//(ArrayList<Coupon>) couponRepo.getCompanyCouponsByMaxPrice(connectedId, price);
	}
	
	public ArrayList<Coupon> getCouponsByDate(Calendar date) 
	{
		return null;//(ArrayList<Coupon>) couponRepo.findCouponsIsLessThanEqualDateAndCompany_id(date, this.connectedId);
	}
	
	@Override
	public boolean login(String companyName, String password)
	{	
		if(!companyRepo.existsByName(companyName))
		{
			throw new LoginException("Company don't exist!");
		}
		if (companyRepo.findCompanyByName(companyName).getPassword().equals(password)) 
		{
			connectedId = companyRepo.findCompanyByName(companyName).getId();
			return true;
		}
		else
		{
			throw new LoginException("Wrong password!");
		}
	}


}
