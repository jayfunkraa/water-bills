package com.kartoffelkopf.waterBills.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kartoffelkopf.waterBills.model.Bill;

public interface BillService {
	List<Bill> findAll();
	Bill findById(Long id);
	void save(Bill bill);
	void delete(Bill bill);
	void addReading(Long billId, Date readingDate, float readingUnits, String readingFilePath);
	Map<Date, Float> getAllReadings();
	void calculate(Bill bill);
}
