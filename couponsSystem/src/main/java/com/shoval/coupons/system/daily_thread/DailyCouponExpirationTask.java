package com.shoval.coupons.system.daily_thread;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.dbdao.CouponDBDAO;

public class DailyCouponExpirationTask implements Runnable{
	
	private CouponDBDAO couponDB;
	private final long aDayTimeInMillis = 1000*60*60*24;
	private Date date = new Date();
	
	public DailyCouponExpirationTask(ApplicationContext ctx) 
	{
		super();
		this.couponDB = ctx.getBean(CouponDBDAO.class);
	}
	
	@Override
	public void run() 
	{
		while(!ConnectionPool.getInstance().isSHUTDOWN())
		{
			/*
			 * This code segment is for test00_thread
			 */
//-------------------------------------------------------
			try 
			{
				Thread.sleep(1000*10);
			}
			catch (InterruptedException e1) 
			{
				e1.printStackTrace();
			}
//-------------------------------------------------------			
			couponDB.deleteCouponByEndDate(date);
			System.out.println("Deleted");
			
			try 
			{
				Thread.sleep(aDayTimeInMillis);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}
}