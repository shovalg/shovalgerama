package com.shoval.coupons.system.entrance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.shoval.coupons.system.daily_thread.DailyCouponExpirationTask;
import com.shoval.coupons.system.facades.AdminFacade;
import com.shoval.coupons.system.facades.ClientType;
import com.shoval.coupons.system.facades.CompanyFacade;
import com.shoval.coupons.system.facades.CouponClientFacade;
import com.shoval.coupons.system.facades.CustomerFacade;

/**
 * This class is the main class of the coupon system application.
 * @author Shoval_G
 * @version 1.0
 * @category CouponSystem - entrance to application
 */
@Component
public class CouponSystem{

	@Autowired
	AdminFacade adminFacade;
	@Autowired
	CompanyFacade companyFacade;
	@Autowired
	CustomerFacade customerFacade;
	@Autowired
	ApplicationContext ctx;
	
	private boolean login = true;
	
	/**
	 * Default constructor.
	 */
	public CouponSystem() 
	{
		super();
	}
	
	/**
	 * This function is responsible for login by user type.
	 * <br>There are three types of users: administrator = "admin", company and customer.</br>
	 * If there is no match, the function will return null.
	 * <p>This function Constructs and initializes a daily thread.</br>
	 * A daily thread is used for clearing an expired coupons in the system database (also known as DB)</p>
	 * @param name user name
	 * @param password user password
	 * @param type user type
	 * @return CouponClientFacade
	 */
	public CouponClientFacade login(String name, String password, ClientType type)
	{
		if(this.login)
		{
			DailyCouponExpirationTask threadCode = new DailyCouponExpirationTask(ctx);
			Thread t = new Thread(threadCode);
			t.start();
			this.login = false;
		}
		switch (type) 
		{
		case ADMIN:
			return adminFacade.login(name, password);
		case COMPANY:
			return companyFacade.login(name, password); 
		case CUSTOMER:
			return customerFacade.login(name, password);
		}
		return null;
	}
	
}
