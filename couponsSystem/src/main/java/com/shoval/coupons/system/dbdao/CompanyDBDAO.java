package com.shoval.coupons.system.dbdao;


import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.connections.SyncObject;
import com.shoval.coupons.system.dao.CompanyDAO;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.CompanyRepo;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponRepo;
import com.shoval.coupons.system.tables.CouponType;

/**
 * This class implements CompanyDAO interface.
 * <br>It lies between CompanyFacade class and CompanyRepo class as a mediator for read and write operations to the database.</br>
 * This class use the CompanyRepo class for those operations.
 * <br>All the functions that want to communicate with the database, get a SyncObject from the Connection pool and return it when finished.</br>
 * @author Shoval_G
 * @version 1.0
 * @category CompanyDBDAO
 */
@Component
public class CompanyDBDAO implements CompanyDAO{

	private long connectedId;
	@Autowired
	CompanyRepo companyRepo;
	@Autowired
	CouponRepo couponRepo;
	
	/**
	 * Default constructor.
	 */
	public CompanyDBDAO() 
	{
		super();
	}
	
	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CompanyRepo class to save the given Company object to the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CompanyRepo class to delete the given Company object from the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CompanyRepo class to update (re-save) the given Company object to the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CompanyRepo class to get a Company object from the database by a given id.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
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

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CompanyRepo class to get all Companies objects from the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	@Override
	public Iterable<Company> getAllCompanies() 
	{
		SyncObject syncObject;
		Iterable<Company> allCompanies;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			allCompanies = companyRepo.findAll();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return allCompanies;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the getConnectedCompany function in order to receive the current connected company, and it's coupons.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished in an indirect manner (through getCompany function).
	 */
	@Override
	public Collection<Coupon> getCoupons() 
	{
		return this.getConnectedCompany().getCoupons();
	}
	
	/**
	 * This function saves the Company object that successfully logged in.
	 * @return Company object which is the connected Company.
	 */
	public Company getConnectedCompany()
	{
		Company connectedCompany = this.getCompany(this.connectedId);
		return connectedCompany;
	}
	
	/**
	 * This function uses the CompanyRepo class to get coupon by a given title.
	 * <br>The coupon belongs to the last company that successfully logged in.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 * @param title of the requested coupon. 
	 * @return Coupon object with the requested coupon title.
	 */
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
	
	/**
	 * This function uses the CompanyRepo class to get company by a given name.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished.</br>
	 * @param name of the requested company.
	 * @return Company object with the requested company name.
	 */
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
	
	/**
	 * This function uses the CompanyRepo class to get coupons by a given type.
	 * <br>The coupons belongs to the last company that successfully logged in.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 * @param type of the requested coupons.
	 * @return Collection of coupons objects with the requested coupon type.
	 */
	public Collection<Coupon> getCouponsByType(CouponType type) 
	{
		SyncObject syncObject;
		Collection<Coupon> couponsByType;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByType = companyRepo.getCouponsByType(this.connectedId, type);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByType;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function uses the CompanyRepo class to get coupons whose price is lower or equal to the given price.
	 * <br>The coupons belongs to the last company that successfully logged in.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 * @param price of the requested coupons.
	 * @return Collection of Coupons objects with the requested coupon price.
	 */
	public Collection<Coupon> getCouponsByPrice(double price) 
	{
		SyncObject syncObject;
		Collection<Coupon> couponsByPrice;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByPrice = companyRepo.getCouponsByPrice(this.connectedId, price);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByPrice;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function uses the CompanyRepo class to get coupons whose end date is before or equal to the given end date.
	 * <br>The coupons belongs to the last company that successfully logged in.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 * @param date, end date of the requested coupons.
	 * @return Collection of Coupons objects with the requested coupon end date.
	 */
	public Collection<Coupon> getCouponsByDate(Date date) 
	{
		SyncObject syncObject;
		Collection<Coupon> couponsByDate;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByDate = companyRepo.getCouponsByDate(connectedId, date);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByDate;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function uses the CompanyRepo class to check if company exists in the database by a given name.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished.</br>
	 * @param name of the requested company. 
	 * @return boolean which indicates whether the requested Company object exists in the database.
	 */
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
	
	/**
	 * <p>{@inheritDoc}</p>
	 * This function uses the CompanyRepo class (in an indirect manner) to check the given credentials (name and password) against the database records.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished in an indirect manner (through companyExistsByName and getCompanyByName functions).</br>
	 */
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
