package com.shoval.coupons.system.daily_thread;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.shoval.coupons.system.connections.ConnectionPool;
import com.shoval.coupons.system.dbdao.CouponDBDAO;

/**
 * <br>This class is responsible for the limitation of DB entrances.</br>
 * This class serves as the code for the daily thread task. this thread runs when the system goes up and deletes all the expired coupons every 24 hours.
 * @author Shoval_G
 * @version 1.0
 * @category DailyCouponExpirationTask
 */
public class DailyCouponExpirationTask implements Runnable{
	
	private CouponDBDAO couponDB;
	private final long aDayTimeInMillis = 1000*60*60*24;
	private Date date = new Date();
	
	/**
	 * Default constructor.
	 * @param ctx ApplicationContext to bring the couponDBDAO.class 
	 */
	public DailyCouponExpirationTask(ApplicationContext ctx) 
	{
		super();
		this.couponDB = ctx.getBean(CouponDBDAO.class);
	}
	
	/**
	 * The thread main code.
	 * <br>This code runs with the first login connection, every 24 hours.</br>
	 * In shutdown mode the thread will go out from the loop and stop running
	 */
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
			couponDB.deleteCouponsByEndDate(date);
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