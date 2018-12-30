package com.kartoffelkopf.waterBills.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Bill
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date billDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
	private float amount;
	private float totalUnits;

	
	//Reading
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date readingDate;
	private float readingUnits;
	private String readingFilePath;
	
	//Calculated
	private float upAmount;
	private float downAmount;
	private boolean readingAdded = false;
	private boolean paid;
	
	public Bill() {}
	
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getTotalUnits() {
		return totalUnits;
	}
	public void setTotalUnits(float totalUnits) {
		this.totalUnits = totalUnits;
	}
	public Date getReadingDate() {
		return readingDate;
	}
	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}
	public float getReadingUnits() {
		return readingUnits;
	}
	public void setReadingUnits(float readingUnits) {
		this.readingUnits = readingUnits;
	}
	public String getReadingFilePath() {
		return readingFilePath;
	}
	public void setReadingFilePath(String readingFilePath) {
		this.readingFilePath = readingFilePath;
	}
	public float getUpAmount() {
		return upAmount;
	}
	public void setUpAmount(float upAmount) {
		this.upAmount = upAmount;
	}
	public float getDownAmount() {
		return downAmount;
	}
	public void setDownAmount(float downAmount) {
		this.downAmount = downAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isReadingAdded() {
		return readingAdded;
	}

	public void setReadingAdded(boolean readingAdded) {
		this.readingAdded = readingAdded;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	

}
