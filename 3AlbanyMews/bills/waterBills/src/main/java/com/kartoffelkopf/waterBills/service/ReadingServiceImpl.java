package com.kartoffelkopf.waterBills.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartoffelkopf.waterBills.data.ReadingDao;
import com.kartoffelkopf.waterBills.model.Reading;

@Service
public class ReadingServiceImpl implements ReadingService {
	
	@Autowired
	private ReadingDao readingDao;

	@Override
	public List<Reading> findAll() {
		return readingDao.findAll();
	}

	@Override
	public Reading findById(Long id) {
		return readingDao.findById(id);
	}

	@Override
	public Long save(Reading reading) {
		return readingDao.save(reading);
	}

	@Override
	public void delete(Reading reading) {
		readingDao.delete(reading);
	}

	@Override
	public Reading getPreviousReading(Reading reading) {
		return readingDao.getPreviousReading(reading);
	}

}
