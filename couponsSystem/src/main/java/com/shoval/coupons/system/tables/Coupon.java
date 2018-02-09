package com.shoval.coupons.system.tables;


import java.util.Calendar;
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

@Entity(name="COUPON")
public class Coupon {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	@Column(name="start_date")
	private Date start_date ;
	@Column(name="end_date")
	private Calendar end_date;
	@Column(name="amount")
	private Integer amount;
	@Column(name="type")
	@Enumerated (EnumType.STRING)
	private CouponType type;
	@Column(name="message")
	private String message;
	@Column(name="price")
	private double price;
	@Column(name="image")
	private String image;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "customer_coupon",
			   joinColumns = @JoinColumn(name = "coupon_id"),
			   inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Collection<Customer> customers;
	
	public Coupon(String title, Date start_date, Calendar end_date, Integer amount,
				  CouponType type, String message,double price, String image) 
	{
		super();
		this.title = title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Calendar getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Calendar end_date) {
		this.end_date = end_date;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public CouponType getType() {
		return type;
	}

	public void setType(CouponType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", amount=" + amount + ", message=" + message + ", price=" + price + ", image=" + image + "]";
	}

	
}
