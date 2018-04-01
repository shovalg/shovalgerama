package com.shoval.coupons.system;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoval.coupons.system.entrance.CouponSystem;
import com.shoval.coupons.system.exceptions.CompanyExistException;
import com.shoval.coupons.system.exceptions.CompanyNotExistException;
import com.shoval.coupons.system.exceptions.CouponExistException;
import com.shoval.coupons.system.exceptions.CouponNotExistException;
import com.shoval.coupons.system.exceptions.CustomerExistException;
import com.shoval.coupons.system.exceptions.CustomerNotExistException;
import com.shoval.coupons.system.exceptions.LoginException;
import com.shoval.coupons.system.exceptions.PurchaseCouponException;
import com.shoval.coupons.system.facades.AdminFacade;
import com.shoval.coupons.system.facades.ClientType;
import com.shoval.coupons.system.facades.CompanyFacade;
import com.shoval.coupons.system.facades.CustomerFacade;
import com.shoval.coupons.system.tables.Company;
import com.shoval.coupons.system.tables.Coupon;
import com.shoval.coupons.system.tables.CouponType;
import com.shoval.coupons.system.tables.Customer;

@DirtiesContext (classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest

/**
 * This class tests all the facades methods including the exceptions in every method.
 * @author Shoval_G
 * @version 1.0
 * @category CouponsSystemApplicationTests class
 */
public class CouponsSystemApplicationTests {
	@Autowired
	CouponSystem couponSystem;
	SimpleDateFormat endDate = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * This function compares two companies objects and forms an assert on each field of the company object.
	 * @param first The first company object to compare.
	 * @param second The second company object to compare.
	 * @return true only if all the asserts passed successfully.
	 */
	public boolean compareCompany (Company first, Company second)
	{
		assertTrue(first.getCompName().equals(second.getCompName()));
		assertTrue(first.getPassword().equals(second.getPassword()));
		assertTrue(first.getEmail().equals(second.getEmail()));
		return true;
	}
	
	/**
	 * This function compares two customers objects and forms an assert on each field of the customer object.
	 * @param first The first customer object to compare.
	 * @param second The second customer object to compare.
	 * @return true only if all the asserts passed successfully.
	 */
	public boolean compareCustomer (Customer first, Customer second)
	{
		assertTrue(first.getCust_name().equals(second.getCust_name()));
		assertTrue(first.getPassword().equals(second.getPassword()));
		return true;
	}
	
	/**
	 * This function compares two coupons objects and forms an assert on each field of the coupon object.
	 * @param first The first coupon object to compare.
	 * @param second The second coupon object to compare.
	 * @return true only if all the asserts passed successfully.
	 */
	
	/*
	 * The start date and end date fields are not checked since their database formats are different from simple date format object
	 * The code for testing this fields is:
	 * assertTrue(first.getStart_date().equals(second.getStart_date()));
	 * assertTrue(first.getEnd_date().equals(second.getEnd_date()));
	 */
	public boolean compareCoupon (Coupon first, Coupon second)
	{
		assertTrue(first.getTitle().equals(second.getTitle()));
		assertTrue(first.getAmount().equals(second.getAmount()));
		assertTrue(first.getType().equals(second.getType()));
		assertTrue(first.getMessage().equals(second.getMessage()));
		assertTrue(first.getImage().equals(second.getImage()));
		return true;
	}
	
	/**
	 * Default test
	 */
	@Test
	public void contextLoads()
	{
		
	}
	
	/**
	 * <br>This test is for the daily thread. this thread is responsible for deleting an expired coupon every 24 hours.</br>
	 * The test is in Ignore state. if you wish to check the thread you need to undo the Ignore annotation.
	 * @throws ParseException
	 */
	@Test
	@Ignore
	public void test00_thread() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Coupon shoes = new Coupon("shoes", endDate.parse("25/08/2017"), 19, CouponType.SPORTS, "discount", 25, "photo");
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		companyFacade.createCoupon(shoes);
		Coupon couponFromDB = companyFacade.getCouponByTitle(shoes.getTitle());
		System.out.println(couponFromDB);
		try 
		{
			Thread.sleep(1000*10);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		Assert.assertEquals(this.compareCoupon(shoes,couponFromDB),true);
	}
	
	/**
	 * This test tests correct login for admin and getting adminFacade in return.
	 */
	@Test
	public void test01_adminFacade_login()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		assertTrue(adminFacade != null);
	}
	
	/**
	 * This test tests LoginException. in a case of wrong admin password login details the test will succeed.
	 */
	@Test (expected = LoginException.class)
	public void test02_adminFacade_loginFailedException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "wrong password", ClientType.ADMIN);
	}
	
	/**
	 * This test tests creation of a company object and saving it in the database.
	 * It check it by comparing the company from the database to the created one.
	 */
	@Test
	public void test03_adminFacade_createCompany()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Company companyFromDB = adminFacade.getCompanyByName(CoffeCoffe.getCompName());
		Assert.assertEquals(this.compareCompany(CoffeCoffe,companyFromDB),true);
	}
	
	/**
	 * This test tests CompanyExistException by trying to add the same company twice.
	 */
	@Test (expected = CompanyExistException.class)
	public void test04_adminFacade_companyExistException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		adminFacade.createCompany(CoffeCoffe);
	}
	
	/**
	 * <br>This test tests the remove company function.</br> 
	 * The company object that was removed is withdrawn from the database and if it's null then an expected exception occur.
	 */
	@Test (expected = CompanyNotExistException.class)
	public void test05_adminFacade_removeCompany()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		adminFacade.removeCompany(CoffeCoffe);
		Company companyFromDB = adminFacade.getCompanyByName(CoffeCoffe.getCompName());
	}
	
	/**
	 * This test tests correct login for company and getting companyFacade in return.
	 */
	@Test
	public void test06_companyFacade_Login()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		assertTrue(companyFacade != null);
	}
	
	/**
	 * This test tests LoginException. in a case of wrong company name login details the test will succeed.
	 */
	@Test (expected = LoginException.class)
	public void test07_companyFacade_loginFailedException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("wrong name","password" , ClientType.COMPANY);
	}
	
	/**
	 * <br>This test tests two methods of getting company from the database.</br>
	 * getCompanyByName and getCompany (by id) and compare between them.
	 */
	@Test
	public void test08_adminFacade_getCompany()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Company companyByName = adminFacade.getCompanyByName(CoffeCoffe.getCompName());
		Company companyById = adminFacade.getCompany(companyByName.getId());
		Assert.assertEquals(this.compareCompany(companyById,companyByName),true);	
	}
	
	/**
	 * This test tests CompanyNotExistException by trying to get a company that doesn't exists (empty collection).
	 */
	@Test (expected = CompanyNotExistException.class)
	public void test09_adminFacade_companyNotExistException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company companyById = adminFacade.getCompany(0);
	}
	
	/**
	 * This test tests CompanyNotExistException by trying to get a company that doesn't exists (wrong name).
	 */
	@Test (expected = CompanyNotExistException.class)
	public void test10_adminFacade_companyNotExistsException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company companyById = adminFacade.getCompanyByName("wrong name");
	}
	
	/**
	 * This test tests the get all companies function by comparing the companies from the database to the companies that has been created.
	 * 
	 */
	@Test
	public void test11_adminFacade_getAllCompanies()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company megaSport = new Company("megaSport" , "T-shirt", "megaSport@yahoo.com");
		adminFacade.createCompany(megaSport);
		Company adidas = new Company("adidas" , "shoes", "adidas@yahoo.com");
		adminFacade.createCompany(adidas);
		List<Company> companiesFromDB = (List<Company>) adminFacade.getAllCompanies();
		assertTrue(companiesFromDB.size() == 2);
		Company firstCompany = companiesFromDB.get(0);
		Assert.assertEquals(this.compareCompany(megaSport ,firstCompany),true);
		Company secondCompany = companiesFromDB.get(1);
		Assert.assertEquals(this.compareCompany(adidas,secondCompany),true);
	}
	
	/**
	 * This test tests the update company function by updating the database and checking that the details changed in the database.
	 */
	@Test
	public void test12_adminFacade_updateCompany()
	{
		AdminFacade adminFacade = (	AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		CoffeCoffe.setPassword("different password");
		CoffeCoffe.setEmail("Aroma@gmail.com");
		adminFacade.updateCompany(CoffeCoffe);
		Company companyFromDB = adminFacade.getCompanyByName(CoffeCoffe.getCompName());
		Assert.assertEquals(this.compareCompany(CoffeCoffe,companyFromDB),true);
	}
	
	/**
	 * This test tests the create customer function by comparing the customer object from the database to the created one.
	 */
	@Test
	public void test13_adminFacade_createCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		Customer customerFromDB = adminFacade.getCustomerByName(shoval.getCust_name());
		Assert.assertEquals(this.compareCustomer(shoval,customerFromDB),true);
	}
	
	/**
	 * This test tests CustomerExistException by trying to add the same customer twice.
	 */
	@Test (expected = CustomerExistException.class)
	public void test14_adminFacade_customerExistException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		adminFacade.createCustomer(shoval);
	}
	
	/**
	 * <br>This test tests the remove customer function.</br> 
	 * The customer object that was removed is withdrawn from the database and if it's null then an expected exception occur.
	 */
	@Test (expected = CustomerNotExistException.class)
	public void test15_adminFacade_removeCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		adminFacade.removeCustomer(shoval);
		Customer customerFromDB = adminFacade.getCustomerByName(shoval.getCust_name());
	}
	
	/**
	 * This test tests correct customer login and getting customerFacade in return.
	 */
	@Test
	public void test16_customerFacade_Login()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		assertTrue(customerFacade != null);
	}
	
	/**
	 * This test tests LoginException. in a case of wrong customer password login details the test will succeed.
	 */
	@Test (expected = LoginException.class)
	public void test17_customerFacade_loginFailedException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "wrong password", ClientType.CUSTOMER);
	}
	
	/**
	 * <br>This test tests two methods of getting customer from the database.</br>
	 * getCustomerByName and getCustomer (by id) and compare between them.
	 */
	@Test 
	public void test18_adminFacade_getCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		Customer customerByName = adminFacade.getCustomerByName(shoval.getCust_name());
		Customer customerById = adminFacade.getCustomer(customerByName.getId());
		Assert.assertEquals(this.compareCustomer(customerById,customerByName),true);	
	}
	
	/**
	 * This test tests CustomerNotExistException by trying to get a customer that doesn't exists (empty collection).
	 */
	@Test (expected = CustomerNotExistException.class)
	public void test19_adminFacade_customerNotExistException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer customerById = adminFacade.getCustomer(0);
	}
	
	/**
	 * This test tests CustomerNotExistException by trying to get a customer that doesn't exists (wrong name).
	 */
	@Test (expected = CustomerNotExistException.class)
	public void test20_adminFacade_customerNotExistException()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer customerById = adminFacade.getCustomerByName("wrong name");
	}
	
	/**
	 * This test tests the get all customers function by comparing the customers from the database to the customers that has been created.
	 */
	@Test
	public void test21_adminFacade_getAllCustomers()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		Customer hadar = new Customer("hadar" , "wife");
		adminFacade.createCustomer(hadar);
		List<Customer> customersFromDB = (List<Customer>) adminFacade.getAllCustomers();
		assertTrue(customersFromDB.size() == 2);
		Customer firstCustomer = customersFromDB.get(0);
		Assert.assertEquals(this.compareCustomer(shoval , firstCustomer),true);
		Customer secondCustomer = customersFromDB.get(1);
		Assert.assertEquals(this.compareCustomer(hadar , secondCustomer),true);	
	}
	
	/**
	 * This test tests the update customer function by updating the database and checking that the details changed in the database.
	 */
	@Test
	public void test22_adminFacade_updateCustomer()
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		shoval.setPassword("different password");
		adminFacade.updateCustomer(shoval);
		Customer customerFromDB = adminFacade.getCustomerByName(shoval.getCust_name());
		Assert.assertEquals(this.compareCustomer(shoval,customerFromDB),true);
	}
	
	/**
	 * This test tests the create coupon function by comparing the coupon object from the database to the created one.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test23_companyFacade_createCoupon() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		companyFacade.createCoupon(shoes);
		Coupon couponFromDB = companyFacade.getCouponByTitle(shoes.getTitle());	
		Assert.assertEquals(this.compareCoupon(shoes,couponFromDB),true);
	}
	
	/**
	 * This test tests CouponExistException by trying to add the same coupon twice.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test (expected = CouponExistException.class)
	public void test24_companyFacade_CouponExistException() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		companyFacade.createCoupon(shoes);
		companyFacade.createCoupon(shoes);
	}
	
	/**
	 * <br>This test tests the remove coupon function.</br> 
	 * The coupon object that was removed is withdrawn from the database and if it's null then an expected exception occur.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test (expected = CouponNotExistException.class)
	public void test25_companyFacade_removeCoupon() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		companyFacade.createCoupon(shoes);
		companyFacade.removeCoupon(shoes);
		Coupon couponFromDB = companyFacade.getCouponByTitle(shoes.getTitle());
	}
	
	/**
	 * <br>This test tests two methods of getting coupon from the database.</br>
	 * getCouponByTitle and getCoupon (by id) and compare between them.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test26_companyFacade_getCoupon () throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon couponByTitle = companyFacade.getCouponByTitle(shoes.getTitle());
		Coupon couponById = companyFacade.getCoupon(couponByTitle.getId());
		Assert.assertEquals(this.compareCoupon(couponById,couponByTitle),true);	
	}
	
	/**
	 * This test tests the update coupon function by updating the database and checking that the details changed in the database.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test27_companyFacade_updateCoupon() throws ParseException
	{	
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		shoes.setEnd_date(endDate.parse("20/07/2019"));
		shoes.setPrice(103);
		companyFacade.updateCoupon(shoes);
		Coupon couponFromDB = companyFacade.getCouponByTitle(shoes.getTitle());
		Assert.assertEquals(this.compareCoupon(shoes,couponFromDB),true);	
	}
	
	/**
	 * This test tests the get all coupons function by comparing the coupons from the database to the coupons that has been created.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test28_companyFacade_getAllCoupons() throws ParseException
	{	
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon("bbb",endDate.parse("30/08/2017"), 30, CouponType.RESTURANTE, "discount", 30, "photo");
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon("shirts", endDate.parse("22/04/2019"), 0, CouponType.SPORTS, "discount", 19, "photo");
		companyFacade.createCoupon(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getAllCoupons();
		assertTrue(couponsFromDB.size() == 3);
		Coupon firstCoupon = couponsFromDB.get(0);
		Coupon secondCoupon = couponsFromDB.get(1);
		Coupon thirdCoupon = couponsFromDB.get(2);
		Assert.assertEquals(this.compareCoupon(firstCoupon,companyFacade.getCouponByTitle(bbb.getTitle())),true);
		Assert.assertEquals(this.compareCoupon(secondCoupon,companyFacade.getCouponByTitle(shoes.getTitle())),true);
		Assert.assertEquals(this.compareCoupon(thirdCoupon,companyFacade.getCouponByTitle(shirts.getTitle())),true);
	}
	
	/**
	 * <br>This test tests the get all coupons by type function by comparing the coupons from the database to the coupons that has been created.</br>
	 * The coupons withdraw is by a getCouponsByType query.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test29_companyFacade_getAllCouponsByType() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon("bbb",endDate.parse("30/08/2017"), 30, CouponType.RESTURANTE, "discount", 30, "photo");
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon("shirts", endDate.parse("22/04/2019"), 0, CouponType.SPORTS, "discount", 19, "photo");
		companyFacade.createCoupon(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getCouponsByType(CouponType.SPORTS);
		assertTrue(couponsFromDB.size() == 2);
		assertTrue(couponsFromDB.get(0).getType().equals(CouponType.SPORTS));
		assertTrue(couponsFromDB.get(1).getType().equals(CouponType.SPORTS));
	}
	
	/**
	 * <br>This test tests the purchase coupon function by a customer.</br>
	 * This test also tests getCouponByTitle method and compares the coupon from the database to the one that has been purchased.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test30_customerFacade_purchaseCoupon() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		Coupon purchasedCoupon = customerFacade.getCouponByTitle(shoes.getTitle());
		shoes.setAmount(shoes.getAmount()-1);
		Assert.assertEquals(this.compareCoupon(shoes,purchasedCoupon),true);
	}
	
	/**
	 * This test tests PurchaseCouponException by trying to purchase the same coupon twice.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test (expected = PurchaseCouponException.class)
	public void test31_customerFacade_purchaseCouponExeption() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(shoes);
	}
	
	/**
	 * This test tests PurchaseCouponException by trying to purchase an expired coupon.  
	 * @throws ParseException Required when setting a new date.
	 */
	@Test (expected = PurchaseCouponException.class)
	public void test32_customerFacade_purchaseCouponExeption() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		Coupon bbb = new Coupon("bbb",endDate.parse("30/08/2017"), 30, CouponType.RESTURANTE, "discount", 30, "photo");
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		companyFacade.createCoupon(bbb);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(bbb);
	}
	
	/**
	 * <br>This test tests PurchaseCouponException by trying to purchase a out of stock coupon.</br>
	 * This is done by setting the coupon amount filed to zero.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test (expected = PurchaseCouponException.class)
	public void test33_customerFacade_purchaseCouponExeption() throws ParseException
	{	
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		Coupon shirts = new Coupon("shirts", endDate.parse("22/04/2019"), 0, CouponType.SPORTS, "discount", 19, "photo");
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		companyFacade.createCoupon(shirts);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shirts);
	}
	
	/**
	 * This test tests the get all purchased coupons function by comparing the purchased coupons from the database to the coupons that has been created.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test34_customerFacade_getAllPurchasedCoupons() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon tent = new Coupon("tent",endDate.parse("30/12/2018"), 25 , CouponType.CAMPING, "discount", 110, "photo");
		companyFacade.createCoupon(tent);
		Coupon racket = new Coupon("racket",endDate.parse("2/4/2018"), 50 , CouponType.SPORTS, "discount", 30, "photo");
		companyFacade.createCoupon(racket);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(tent);
		customerFacade.purchaseCoupon(racket);
		List<Coupon> couponsFromDB = (List<Coupon>) customerFacade.getAllPurchasedCoupons();
		assertTrue(couponsFromDB.size() == 3);
		Coupon firstCoupon = couponsFromDB.get(0);
		Coupon secondCoupon = couponsFromDB.get(1);
		Coupon thirdCoupon = couponsFromDB.get(2);
		Assert.assertEquals(this.compareCoupon(firstCoupon,companyFacade.getCouponByTitle(shoes.getTitle())),true);
		Assert.assertEquals(this.compareCoupon(secondCoupon,companyFacade.getCouponByTitle(tent.getTitle())),true);
		Assert.assertEquals(this.compareCoupon(thirdCoupon,companyFacade.getCouponByTitle(racket.getTitle())),true);
	}
	
	/**
	 * <br>This test tests the get all purchased coupons by type function of certain customer with the given type from the database</br> 
	 * by comparing the purchased coupons from the database to the given coupon type.
	 * @throws ParseException Required when setting a new date. 
	 */
	@Test
	public void test35_customerFacade_getAllPurchasedCouponsByType() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon tent = new Coupon("tent",endDate.parse("30/12/2018"), 25 , CouponType.CAMPING, "discount", 120, "photo");
		companyFacade.createCoupon(tent);
		Coupon racket = new Coupon("racket",endDate.parse("2/4/2018"), 50 , CouponType.SPORTS, "discount", 40, "photo");
		companyFacade.createCoupon(racket);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(racket);
		customerFacade.purchaseCoupon(tent);
		List<Coupon> couponsFromDB = (List<Coupon>) customerFacade.getAllPurchasedCouponsByType(CouponType.SPORTS);
		assertTrue(couponsFromDB.size() == 2);
		assertTrue(couponsFromDB.get(0).getType().equals(CouponType.SPORTS));
		assertTrue(couponsFromDB.get(1).getType().equals(CouponType.SPORTS));
	}
	
	/**
	 * <br>This test tests the get all purchased coupons by price function of a certain customer.</br> 
	 * It checks the coupons price whether the price is lower or equals to the given price by comparing the purchased coupons price to the given price. 
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test36_customerFacade_getAllPurchasedCouponsByPrice() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon tent = new Coupon("tent",endDate.parse("30/12/2018"), 25 , CouponType.CAMPING, "discount", 120, "photo");
		companyFacade.createCoupon(tent);
		Coupon racket = new Coupon("racket",endDate.parse("2/4/2018"), 50 , CouponType.SPORTS, "discount", 40, "photo");
		companyFacade.createCoupon(racket);
		CustomerFacade customerFacade = (CustomerFacade) couponSystem.login("shoval", "shoval's project", ClientType.CUSTOMER);
		customerFacade.purchaseCoupon(shoes);
		customerFacade.purchaseCoupon(racket);
		customerFacade.purchaseCoupon(tent);
		List<Coupon> couponsFromDB = (List<Coupon>) customerFacade.getAllPurchasedCouponsByPrice(100);
		assertTrue(couponsFromDB.size() == 2);
		assertTrue(couponsFromDB.get(0).getPrice() <= 100);
		assertTrue(couponsFromDB.get(1).getPrice() <= 100);
	}
	
	/**
	 * <br>This test tests the get coupons by price function by getting all the coupons from the database that their price is lower</br>
	 * or equals to the given price and comparing the coupons price to the given price. 
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test37_companyFacade_getCouponsByPrice() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon("bbb",endDate.parse("30/08/2017"), 30, CouponType.RESTURANTE, "discount", 30, "photo");
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon("shirts", endDate.parse("22/04/2019"), 0, CouponType.SPORTS, "discount", 19, "photo");
		companyFacade.createCoupon(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getCouponsByPrice(26);
		assertTrue(couponsFromDB.size() == 2);
		assertTrue(couponsFromDB.get(0).getPrice() <= 30);
		assertTrue(couponsFromDB.get(1).getPrice() <= 30);
	}
	
	/**
	 * <br>This test tests the get coupons by end date function by getting all the coupons from the database that</br>
	 * their end date is before or equals to the given end date and comparing the coupon's end date to the current date.
	 * @throws ParseException Required when setting a new date.
	 */
	@Test
	public void test38_companyFacade_getCouponsByEndDate() throws ParseException
	{
		AdminFacade adminFacade = (AdminFacade) couponSystem.login("admin", "1234", ClientType.ADMIN);
		Company CoffeCoffe = new Company("CoffeCoffe" , "password", "CoffeCoffe@gmail.com");
		adminFacade.createCompany(CoffeCoffe);
		Customer shoval = new Customer("shoval" , "shoval's project");
		adminFacade.createCustomer(shoval);
		CompanyFacade companyFacade = (CompanyFacade) couponSystem.login("CoffeCoffe","password" , ClientType.COMPANY);
		Coupon shoes = new Coupon("shoes", endDate.parse("15/10/2018"), 19, CouponType.SPORTS, "discount", 25, "photo");
		companyFacade.createCoupon(shoes);
		Coupon bbb = new Coupon("bbb",endDate.parse("30/08/2017"), 30, CouponType.RESTURANTE, "discount", 30, "photo");
		companyFacade.createCoupon(bbb);
		Coupon shirts = new Coupon("shirts", endDate.parse("22/04/2019"), 0, CouponType.SPORTS, "discount", 19, "photo");
		companyFacade.createCoupon(shirts);
		List<Coupon> couponsFromDB = (List<Coupon>) companyFacade.getCouponsByDate(new Date());
		assertTrue(couponsFromDB.size() == 1);
		assertTrue(couponsFromDB.get(0).getEnd_date().before(new Date()));
	}
}