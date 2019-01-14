package com.kartoffelkopf.waterBills.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reading implements Comparable<Reading> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	private float units;
	private String filePath;

	public Reading() {
	}

	public Long getReadingId() {
		return id;
	}

	public void setReadingId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	@Override
	public int compareTo(Reading o) {
		if (getDate() == null || o.getDate() == null) {
			return 0;
		} else {
			return getDate().compareTo(o.getDate());
		}
	}

}
