package com.shoval.coupons.system.facades;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.dbdao.CompanyDBDAO;
import com.shoval.coupons.system.dbdao.CouponDBDAO;
import com.shoval.coupons.system.exceptions.CompanyNotExistException;
import com.shoval.coupons.system.exceptions.CouponExistException;
import com.shoval.coupons.system.exceptions.CouponNotExistException;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;

/**
 * This class is one of three management classes.
 * <br>Each one of this classes is responsible for all the logic behind an end user type.</br>
 * This class is responsible for company user type.
 * @author Shoval_G
 * @version 1.0
 * @category CompanyFacade
 */
@Component
public class CompanyFacade implements CouponClientFacade{

	@Autowired
	CompanyDBDAO companyDB;
	@Autowired
	CouponDBDAO couponDB;
	
	
	/**
	 * Default constructor.
	 */
	public CompanyFacade() 
	{
		super();
	}
	
	/**
	 * This function is responsible for creating a coupon and save it in the database according to the current connected company.
	 * <br>The function blocks the option to create two coupons with the same title description.</br>
	 * @param coupon object received from user company and will be saved in the database. 
	 * @throws CouponExistException thrown in case of creating a coupon with title description that already exist in the database.
	 */
	public void createCoupon(Coupon coupon)
	{	
		if(couponDB.couponExistsByTitle(coupon.getTitle()))
		{
			throw new CouponExistException("A coupon with the same title, " + coupon.getTitle() + ", is already exist!");
		}
		Company connectedCompany = companyDB.getConnectedCompany();
		couponDB.createCoupon(coupon);
		Collection<Coupon> coupons = companyDB.getCoupons();
		coupons.add(coupon);
		connectedCompany.setCoupons(coupons);
		companyDB.updateCompany(connectedCompany);
	}
	
	/**
	 * This function is responsible for deleting a coupon from the database according to the current connected company.
	 * @param coupon object received from user company and will be deleted from the database.
	 */
	public void removeCoupon(Coupon coupon)
	{
//		Company connectedCompany = companyDB.getConnectedCompany();
		couponDB.removeCoupon(coupon);
//		Collection<Coupon> coupons = connectedCompany.getCoupons();
		//Collection<Coupon> coupons = companyDB.getCoupons();
//		coupons.remove(coupon);
		//connectedCompany.setCoupons(coupons);
//		companyDB.updateCompany(connectedCompany);
//		couponDB.updateCoupon(coupon);
	}
	
	/**
	 * This function is responsible for updating a coupon from the database according to the current connected company.
	 * @param coupon object which receive from user company. the price and end date which are the object's variables will be update.
	 */
	public void updateCoupon(Coupon coupon)
	{
		Coupon companyCoupon = companyDB.getCouponByTitle(coupon.getTitle());
		companyCoupon.setPrice(coupon.getPrice());
		companyCoupon.setEnd_date(coupon.getEnd_date());
		couponDB.updateCoupon(companyCoupon);
	}
	
	/**
	 * This function is responsible for getting a coupon from the database.
	 * @param id parameter is the PK for the Coupon object stored in the database.
	 * @return the Coupon object stored in the database which match the specified id parameter.
	 */
	public Coupon getCoupon(long id)
	{
		Coupon coupon = couponDB.getCoupon(id);
		if(coupon != null)
		{
			return coupon;
		}
		else
		{
			throw new CouponNotExistException("Coupon don't exist!");
		}
	}
	
	/**
	 * This function is responsible for getting all coupons from the database according to the current connected company.
	 * @return Iterable of all company's coupons objects stored in the database.
	 */
	public Collection<Coupon> getAllCoupons()
	{
		return companyDB.getCoupons();
	}
	
	/**
	 * This function is responsible for getting all coupons from the database according to the current connected company by a given coupon type.
	 * @param type is the coupon type.
	 * @return Collection of all company's coupons objects stored in the database according to the given coupon type.
	 */
	public Collection<Coupon> getCouponsByType(CouponType type)
	{
		return companyDB.getCouponsByType(type);
	}
	
	/**
	 * This function is responsible for getting all coupons from the database according to the current connected company by a given coupons top price.
	 * @param price is the top price which is all the coupons whose price is lower or equal to the given price.
	 * @return Collection of all company's coupons objects stored in the database according to the given coupons top type.
	 */
	public Collection<Coupon> getCouponsByPrice(double price)
	{
		return companyDB.getCouponsByPrice(price);
	}
	
	/**
	 * This function is responsible for getting all coupons from the database according to the current connected company by a given coupons end date.
	 * @param date is the end date which is all the coupons whose date is before or equal to the given date.
	 * @return Collection of all company's coupons objects stored in the database according to the given coupons end date.
	 */
	public Collection<Coupon> getCouponsByDate(Date date)
	{
		return companyDB.getCouponsByDate(date);
	}
	
	/**
	 * This function is responsible for getting a coupon from the database according to the current connected company by a given coupon title.
	 * @param title is the coupon title.
	 * @return the Coupon object stored in the database which match the specified title parameter.
	 * @throws CouponNotExistException thrown in case of null value in Coupon object that received from the database.
	 */
	public Coupon getCouponByTitle(String title)
	{
		if(companyDB.getCouponByTitle(title) == null)
		{
			throw new CouponNotExistException("Coupon " + title + " is not exist!");
		}
		return companyDB.getCouponByTitle(title);
	}
	
	/**{@inheritDoc}
	 * <p>This function is used for company user type login.</p>
	 * @throws LoginException indicates about wrong name or password typo.
	 */
	@Override
	public CouponClientFacade login(String name, String password)
	{	
		if(this.companyDB.login(name, password))
		{
			return this;
		}
		throw new LoginException("Company " + name + " don't exist or Wrong password!");
	}

}
