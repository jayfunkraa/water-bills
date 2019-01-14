package com.kartoffelkopf.waterBills.service;

import java.util.List;

import com.kartoffelkopf.waterBills.model.Bill;
import com.kartoffelkopf.waterBills.model.Reading;

public interface BillService {
	List<Bill> findAll();
	Bill findById(Long id);
	void save(Bill bill);
	void delete(Bill bill);
	void addReading(Long id, Reading reading);
	Bill getPreviousBill(Bill bill);
	void calculate(Long id);
	boolean togglePaid(Long id);
}
