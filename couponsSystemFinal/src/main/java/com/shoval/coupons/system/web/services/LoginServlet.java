package com.shoval.coupons.system.web.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shoval.coupons.system.entrance.CouponSystem;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.facades.AdminFacade;
import com.shoval.coupons.system.facades.ClientType;
import com.shoval.coupons.system.facades.CompanyFacade;
import com.shoval.coupons.system.facades.CustomerFacade;


@Controller
public class LoginServlet {

	@Autowired
	CouponSystem couponSystem;
	
	@RequestMapping( value = "/loginservlet", 
			method = RequestMethod.POST)
	public void loginGet( @RequestParam String nametxt,
			@RequestParam String passwordtxt, @RequestParam String clientType,
			HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			ClientType type = ClientType.valueOf(clientType);
			switch (type) 
			{
			case ADMIN:
				AdminFacade adminFacade = (AdminFacade) couponSystem.login(nametxt, passwordtxt, type);
				if(adminFacade != null)
				{
					request.getSession().setAttribute(nametxt, adminFacade);
					response.sendRedirect("http://localhost:8080/admin/index.html");
					
					break;
				}
			case COMPANY:
				CompanyFacade companyFacade = (CompanyFacade) couponSystem.login(nametxt, passwordtxt,type);
				if(companyFacade != null)
				{
					request.getSession().setAttribute(nametxt, companyFacade);
					response.sendRedirect("http://localhost:8080/company/index.html");
						
					break;
				}
			case CUSTOMER:
				CustomerFacade customerFacade = (CustomerFacade) couponSystem.login(nametxt, passwordtxt, type);
				if(customerFacade != null)
				{
					request.getSession().setAttribute(nametxt, customerFacade);
					response.sendRedirect("http://localhost:8080/customer/index.html");
					
					break;
				}
			}
			
		}
		
		catch (LoginException | IOException e)
		{
			try 
			{
				response.sendRedirect("http://localhost:8080/index.html?error=wrongDataEntered");
			}
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
	}
}