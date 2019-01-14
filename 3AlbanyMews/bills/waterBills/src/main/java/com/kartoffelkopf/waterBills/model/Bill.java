package com.kartoffelkopf.waterBills.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Bill implements Comparable<Bill> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Bill
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate billDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
	private float amount;
	private float totalUnits;
	@OneToOne
	private Reading reading;

	// Calculated
	private float upAmount;
	private float downAmount;
	private boolean paid;

	public Bill() {
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
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

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Reading getReading() {
		return reading;
	}

	public void setReading(Reading reading) {
		this.reading = reading;
	}

	@Override
	public int compareTo(Bill o) {
		if (getBillDate() == null || o.getBillDate() == null) {
			return 0;
		} else {
			return getBillDate().compareTo(o.getBillDate());
		}
	}

}
