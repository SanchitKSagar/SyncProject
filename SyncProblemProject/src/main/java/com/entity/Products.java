package com.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products") // Table name in database
public class Products implements Serializable{

	// Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	
	private String pname;
	
	private String pmanufacturer;
	
	private double price;

	
	// Default Constructor
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized constructor
	public Products(int pid, String pname, String pmanufacturer, double price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pmanufacturer = pmanufacturer;
		this.price = price;
	}
	
	// Getters and Settters
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPmanufacturer() {
		return pmanufacturer;
	}

	public void setPmanufacturer(String pmanufacturer) {
		this.pmanufacturer = pmanufacturer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
