package com.shoval.coupons.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoval.coupons.system.entrance.CouponSystem;
import com.shoval.coupons.system.facades.AdminFacade;
import com.shoval.coupons.system.facades.ClientType;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponsSystemApplicationTests {

	@Autowired
	CouponSystem couponSystem;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void test1_createDB() {
		// Login via CouponSystem (AdminFacde)
		
			AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
			
			/*
			 * Load Customers to DB
			 */
			Company teva = new Company("teva", "111", "nunu@n");
			adminFacade.createCompany(teva);
//			Customer shoval = new Customer("shoval", "12121212");
//			adminFacade.createCustomer(shoval);
//
//			Customer karin = new Customer("karin", "2154459");
//			adminFacade.createCustomer(karin);
//
//			Customer avigail = new Customer("avigail", "4567897");
//			adminFacade.createCustomer(avigail);
//
//			Customer ariel = new Customer("ariel", "784595");
//			adminFacade.createCustomer(ariel);
//
//			Customer odel = new Customer("odel", "1200034");
//			adminFacade.createCustomer(odel);
//
//			Customer avraham = new Customer("avraham", "12145362");
//			adminFacade.createCustomer(avraham);
//
//			Customer itshak = new Customer("itshak", "2112354");
//			adminFacade.createCustomer(itshak);
//
//			Customer jacob = new Customer("jacob", "412567");
//			adminFacade.createCustomer(jacob);
//
//			Customer sara = new Customer("sara", "7843295");
//			adminFacade.createCustomer(sara);
//
//			Customer rivka = new Customer("rivka", "12347890");
//			adminFacade.createCustomer(rivka);
//
//			Customer rachel = new Customer("rachel", "7543895");
//			adminFacade.createCustomer(rachel);
//
//			Customer lea = new Customer("lea", "126434");
//			adminFacade.createCustomer(lea);
//
//			Customer reuven = new Customer("reuven", "1717954");
//			adminFacade.createCustomer(reuven);
//
//			Customer shimon = new Customer("shimon", "7896323");
//			adminFacade.createCustomer(shimon);

		}
}
