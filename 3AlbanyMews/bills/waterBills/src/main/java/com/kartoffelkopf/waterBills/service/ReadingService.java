package com.kartoffelkopf.waterBills.service;

import java.util.List;

import com.kartoffelkopf.waterBills.model.Reading;

public interface ReadingService {
	
	List<Reading> findAll();
	Reading findById(Long id);
	Long save(Reading reading);
	void delete(Reading reading);
	Reading getPreviousReading(Reading reading);
	
}
