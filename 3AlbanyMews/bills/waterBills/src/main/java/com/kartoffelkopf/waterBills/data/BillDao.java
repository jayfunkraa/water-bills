package com.kartoffelkopf.waterBills.data;

import java.util.List;

import com.kartoffelkopf.waterBills.model.Bill;

public interface BillDao {
	
	List<Bill> findAll();
	Bill findById(Long id);
	void save(Bill bill);
	void delete(Bill bill);

}
