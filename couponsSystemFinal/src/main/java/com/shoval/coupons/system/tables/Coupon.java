package com.shoval.coupons.system.tables;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * This table entity (COUPON) represent the fields of object Coupon in the database.
 * <br>The Coupon table is connected to the Customer table by many to many relation.</br>
 * @author Shoval_G
 * @version 1.0
 * @category Coupon table
 */
@Entity(name="COUPON")
public class Coupon {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	@Column(name="start_date")
	private Date start_date ;
	@Column(name="end_date")
	private Date end_date;
	@Column(name="amount")
	private int amount;
	@Column(name="type")
	@Enumerated (EnumType.STRING)
	private CouponType type;
	@Column(name="message")
	private String message;
	@Column(name="price")
	private double price;
	@Column(name="image")
	private String image;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,  CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "customer_coupon",
			   joinColumns = @JoinColumn(name = "coupon_id"),
			   inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Collection<Customer> customers;
	
	/**
	 * Default constructor
	 */
	public Coupon() 
	{
		super();
		this.start_date = new Date();
	}
	
	/**
	 * Main constructor
	 * @param title coupon title
	 * @param end_date coupon end date
	 * @param amount coupon amount
	 * @param type coupon type
	 * @param message coupon message
	 * @param price coupon price
	 * @param image coupon image
	 */
	public Coupon(String title, Date end_date, int amount,
				  CouponType type, String message,double price, String image) 
	{
		super();
		this.title = title;
		this.start_date = new Date();
		this.end_date = end_date;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	/**
	 * Get function that return the coupon's id.
	 * @return long identifier of coupon's id.
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * Set function for changing coupon's id.
	 * @param id the new coupon's id.
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * Get function that return the coupon's title.
	 * @return String identifier of coupon's title.
	 */
	public String getTitle() 
	{
		return title;
	}

	/**
	 * Set function for changing coupon's title.
	 * @param title the new coupon's title.
	 */
	public void setTitle(String title) 
	{
		this.title = title;
	}

	/**
	 * Get function that return the coupon's start date.
	 * @return Date identifier of coupon's start date.
	 */
	public Date getStart_date() 
	{
		return start_date;
	}

	/**
	 * Set function for changing coupon's start date.
	 * @param start_date the new coupon's start date.
	 */
	public void setStart_date(Date start_date) 
	{
		this.start_date = start_date;
	}

	/**
	 * Get function that return the coupon's end date.
	 * @return Date identifier of coupon's end date.
	 */
	public Date getEnd_date() 
	{
		return end_date;
	}

	/**
	 * Set function for changing coupon's end date.
	 * @param end_date the new coupon's end date.
	 */
	public void setEnd_date(Date end_date) 
	{
		this.end_date = end_date;
	}

	/**
	 * Get function that return the coupon's amount.
	 * @return Integer identifier of coupon's amount.
	 */
	public Integer getAmount() 
	{
		return amount;
	}

	/**
	 * Set function for changing coupon's amount.
	 * @param amount the new coupon's amount.
	 */
	public void setAmount(Integer amount) 
	{
		this.amount = amount;
	}

	/**
	 * Get function that return the coupon's type.
	 * @return CouponType identifier (Enum) of coupon's type.
	 */
	public CouponType getType()
	{
		return type;
	}

	/**
	 * Set function for changing coupon's type.
	 * @param type the new coupon's type.
	 */
	public void setType(CouponType type) 
	{
		this.type = type;
	}

	/**
	 * Get function that return the coupon's message.
	 * @return String identifier of coupon's message.
	 */
	public String getMessage() 
	{
		return message;
	}

	/**
	 * Set function for changing coupon's message.
	 * @param message the new coupon's message.
	 */
	public void setMessage(String message) 
	{
		this.message = message;
	}

	/**
	 * Get function that return the coupon's price.
	 * @return double identifier of coupon's price.
	 */
	public double getPrice() 
	{
		return price;
	}

	/**
	 * Set function for changing coupon's price.
	 * @param price the new coupon's price.
	 */
	public void setPrice(double price)
	{
		this.price = price;
	}

	/**
	 * Get function that return the coupon's image.
	 * @return String identifier of coupon's image.
	 */
	public String getImage() 
	{
		return image;
	}

	/**
	 * Set function for changing coupon's image.
	 * @param image the new coupon's image.
	 */
	public void setImage(String image) 
	{
		this.image = image;
	}

	/**
	 * This function presents all the coupon's details.
	 * @return String with all the coupon's details.
	 */
	@Override
	public String toString()
	{
		return "Coupon [id=" + id + ", title=" + title + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", amount=" + amount + ", message=" + message + ", price=" + price + ", image=" + image + "]";
	}	
}
