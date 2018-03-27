package com.shoval.coupons.system.dbdao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.connections.SyncObject;
import com.shoval.coupons.system.dao.CouponDAO;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponRepo;
import com.shoval.coupons.system.tables.CouponType;

@Component
public class CouponDBDAO implements CouponDAO{

	@Autowired
	CouponRepo couponRepo;
	
	public CouponDBDAO() 
	{
		super();
	}

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

	@Override
	public ArrayList<Coupon> getAllCoupons() 
	{
		SyncObject syncObject;
		ArrayList<Coupon> allCoupons;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			allCoupons = (ArrayList<Coupon>) couponRepo.findAll();
			ConnectionPool.getInstance().returnConnection(syncObject);
			return allCoupons;
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Coupon> getCouponsByType(CouponType type) 
	{
		SyncObject syncObject;
		ArrayList<Coupon> couponsByType;
		try 
		{
			syncObject = ConnectionPool.getInstance().getConnection();
			couponsByType = (ArrayList<Coupon>) couponRepo.findCouponByType(type);
			ConnectionPool.getInstance().returnConnection(syncObject);
			return couponsByType;
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
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
	
	public void deleteCouponByEndDate(Date endDate)
	{
		couponRepo.deleteCouponByEndDate(endDate);
	}
}
