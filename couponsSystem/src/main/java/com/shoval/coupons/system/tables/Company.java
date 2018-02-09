package com.shoval.coupons.system.tables;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="COMPANY")
public class Company {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	private Collection<Coupon> coupons;

	public Company(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompName() {
		return name;
	}

	public void setCompName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Collection<Coupon> getCoupons() 
	{
		return coupons;
	}

	public void setCoupons(Collection<Coupon> coupons) 
	{
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", comp_name=" + name + ", password=" + password + ", email=" + email + "]";
	}
	
}
