package com.shoval.coupons.system.entrance;

import org.springframework.stereotype.Component;

import com.shoval.coupons.system.facades.AdminFacade;
import com.shoval.coupons.system.facades.ClientType;
import com.shoval.coupons.system.facades.CompanyFacade;
import com.shoval.coupons.system.facades.CouponClientFacade;
import com.shoval.coupons.system.facades.CustomerFacade;

@Component
public class CouponSystem{

	private static CouponSystem _INSTANCE = null;
	
	private CouponSystem() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static CouponSystem getInstance()
	{
		if(_INSTANCE == null)
		{
			_INSTANCE = new CouponSystem();
		}
		return _INSTANCE;
	}

	
	public CouponClientFacade login(String name, String password, ClientType type)
	{
		switch (type) 
		{
		case ADMIN:
			AdminFacade userAdmin = new AdminFacade();
			return userAdmin.login(name, password);
		case COMPANY:
			CompanyFacade userCompany = new CompanyFacade(); 
			return userCompany.login(name, password); 
		case CUSTOMER:
			CustomerFacade userCustomer = new CustomerFacade();
			return userCustomer.login(name, password);
		}
		return null;
	}
	
}
