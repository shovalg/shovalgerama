package com.shoval.coupons.system.dbdao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.connections.SyncObject;
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
		super();
	}
	
	@Override
	public void createCompany(Company company) 
	{
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			companyRepo.save(company);
			ConnectionPool.getInstance().returnConnection(syncObject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void removeCompany(Company company) 
	{
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			companyRepo.delete(company.getId());
			ConnectionPool.getInstance().returnConnection(syncObject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}

	@Override
	public void updateCompany(Company company)
	{
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			companyRepo.save(company);
			ConnectionPool.getInstance().returnConnection(syncObject);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public Company getCompany(long id)
	{
		SyncObject syncObject;
		Company company;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			company = companyRepo.findOne(id);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return company;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Company> getAllCompanies() 
	{
		SyncObject syncObject;
		ArrayList<Company> allCompanies;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			allCompanies = (ArrayList<Company>) companyRepo.findAll();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return allCompanies;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<Coupon> getCoupons() 
	{
		//Company company = this.getCompany(this.connectedId);
		return this.getConnectedCompany().getCoupons();
	}
	
	public Company getConnectedCompany()
	{
		Company connectedCompany = this.getCompany(this.connectedId);
		return connectedCompany;
	}
	
	public Coupon getCouponByTitle(String title)
	{
		SyncObject syncObject;
		Coupon coupon;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			coupon = companyRepo.getCouponByTitle(connectedId, title);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return coupon;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
//	public Coupon getCouponByTitle(String title)
//	{
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
//		return ;//companyCoupon;
//	}
	
	public Company getCompanyByName(String name)
	{
		SyncObject syncObject;
		Company company;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			company = companyRepo.findCompanyByName(name);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return company;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Coupon> getCouponsByType(CouponType type) 
	{
		SyncObject syncObject;
		ArrayList<Coupon> couponsByType;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByType = (ArrayList<Coupon>) companyRepo.getCouponsByType(this.connectedId, type);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByType;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Coupon> getCouponsByPrice(double price) 
	{
		SyncObject syncObject;
		ArrayList<Coupon> couponsByPrice;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByPrice = (ArrayList<Coupon>) companyRepo.getCouponsByPrice(this.connectedId, price);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByPrice;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Coupon> getCouponsByDate(Date date) 
	{
		SyncObject syncObject;
		ArrayList<Coupon> couponsByDate;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByDate = (ArrayList<Coupon>) companyRepo.getCouponsByDate(connectedId, date);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByDate;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean companyExistsByName(String name)
	{
		SyncObject syncObject;
		boolean companyExist;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			companyExist = companyRepo.existsByName(name);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return companyExist;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean login(String companyName, String password)
	{	
		if(!this.companyExistsByName(companyName))
		{
			return false;
		}
		if (this.getCompanyByName(companyName).getPassword().equals(password)) 
		{
			connectedId = this.getCompanyByName(companyName).getId();
			return true;
		}
		else
		{
			return false;
		}
	}
}
