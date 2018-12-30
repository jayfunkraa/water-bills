package com.kartoffelkopf.waterBills.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartoffelkopf.waterBills.data.BillDao;
import com.kartoffelkopf.waterBills.model.Bill;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;
	
	@Override
	public List<Bill> findAll() {
		return billDao.findAll();
	}

	@Override
	public Bill findById(Long id) {
		return billDao.findById(id);
	}

	@Override
	public void save(Bill bill) {
		billDao.save(bill);
	}

	@Override
	public void delete(Bill bill) {
		billDao.delete(bill);
	}

	@Override
	public void addReading(Long billId, Date readingDate, float readingUnits, String readingFilePath) {
		Bill bill = billDao.findById(billId);
		bill.setReadingDate(readingDate);
		bill.setReadingUnits(readingUnits);
		bill.setReadingFilePath(readingFilePath);
		bill.setReadingAdded(true);
		billDao.save(bill);
	}
	
	@Override
	public Map<Date, Float> getAllReadings() {
		Map<Date, Float> readings = new HashMap<>();
		List<Bill> bills = billDao.findAll();
		for (Bill bill : bills) {
			readings.put(bill.getReadingDate(), bill.getReadingUnits());
		}
		Map<Date, Float> result = readings.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		return result;
	}
	
	@Override
	public void calculate(Bill bill) {
		Map<Date, Float> readings = getAllReadings();
		Date prevDate;
		float prevUnits = 0;
		float unitsUsed = 0;
		float percent = 0;
		
		for (Map.Entry<Date, Float> entry : readings.entrySet()) {
			prevDate = entry.getKey();
			prevUnits = entry.getValue();
			if (entry.getKey().equals(bill.getReadingDate())) {
				break;
			}
		}
		
		unitsUsed = bill.getReadingUnits() - prevUnits;
		percent = unitsUsed / bill.getTotalUnits();
		bill.setDownAmount(bill.getAmount() * percent);
		
		billDao.save(bill);
	}
}
