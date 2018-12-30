package com.kartoffelkopf.waterBills.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reading {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private float units;
	private String filePath;
	
	public Reading() {}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getUnits() {
		return units;
	}
	public void setUnits(float units) {
		this.units = units;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}
