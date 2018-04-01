package com.shoval.coupons.system.dbdao;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.connections.SyncObject;
import com.shoval.coupons.system.dao.CouponDAO;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponRepo;
import com.shoval.coupons.system.tables.CouponType;

/**
 * This class implements CouponDAO interface.
 * <br>Uses as a mediator for read and write operations to the database.</br>
 * This class use the CouponRepo class for those operations.
 * <br>All the functions that want to communicate with the database, get a SyncObject from the Connection pool and return it when finished.</br>
 * @author Shoval_G
 * @version 1.0
 * @category CouponDBDAO
 */
@Component
public class CouponDBDAO implements CouponDAO{

	@Autowired
	CouponRepo couponRepo;

	/**
	 * Default constructor.
	 */
	public CouponDBDAO() 
	{
		super();
	}

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CouponRepo class to save the given Coupon object to the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	@Override
	public void createCoupon(Coupon coupon) 
	{
		SyncObject syncObject;
		try
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponRepo.save(coupon);
			ConnectionPool.getInstance().returnConnection(syncObject);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CouponRepo class to delete the given Coupon object from the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	@Override
	public void removeCoupon(Coupon coupon) 
	{
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponRepo.delete(coupon.getId());
			ConnectionPool.getInstance().returnConnection(syncObject);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CouponRepo class to update (re-save) the given Coupon object to the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	@Override
	public void updateCoupon(Coupon coupon) 
	{
		SyncObject syncObject;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponRepo.save(coupon);
			ConnectionPool.getInstance().returnConnection(syncObject);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}

	/**
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CouponRepo class to get a Coupon object from the database by a given id.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	@Override
	public Coupon getCoupon(long id) 
	{
		SyncObject syncObject;
		Coupon coupon;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			coupon = couponRepo.findOne(id);
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
	 * <p>{@inheritDoc}</p>
	 * <br>This function uses the CouponRepo class to get all Coupons objects from the database.</br>
	 * This function get a SyncObject from the Connection pool and return it when finished.
	 */
	@Override
	public Iterable<Coupon> getAllCoupons() 
	{
		SyncObject syncObject;
		Iterable<Coupon> allCoupons;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			allCoupons = couponRepo.findAll();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return allCoupons;
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This function uses the CouponRepo class to get coupons by a given type.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished.</br>
	 */
	@Override
	public Collection<Coupon> getCouponsByType(CouponType type) 
	{
		SyncObject syncObject;
		Collection<Coupon> couponsByType;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByType = couponRepo.findCouponsByType(type);
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
	 * This function uses the CouponRepo class to get coupon by a given title.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished.</br>
	 * @param title of the requested coupon. 
	 * @return Coupon object with the requested coupon title.
	 */
	public Coupon getCouponByTitle(String title) 
	{
		SyncObject syncObject;
		Coupon couponByTitle;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponByTitle = couponRepo.getCouponByTitle(title);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponByTitle;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This function uses the CouponRepo class to check if coupon exists in the database by a given title.
	 * <br>This function get a SyncObject from the Connection pool and return it when finished.</br>
	 * @param title of the requested coupon. 
	 * @return boolean which indicates whether the requested Coupon object exists in the database.
	 */
	public boolean couponExistsByTitle(String title)
	{
		SyncObject syncObject;
		boolean couponExist;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponExist = couponRepo.existsByTitle(title);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponExist;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * This function uses the CouponRepo class to delete all expired Coupons objects from the database.
	 * <br>An expired coupon is considered such if it's end date is before or equal to the given end date.</br>
	 * This function bypass the get a SyncObject from the Connection pool mechanism mainly because it uses for the daily thread to delete expired coupons.
	 * @param endDate is the expiration date limit of the coupons.
	 */
	public void deleteCouponsByEndDate(Date endDate)
	{
		couponRepo.deleteCouponsByEndDate(endDate);
	}
}
