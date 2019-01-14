package com.kartoffelkopf.waterBills.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartoffelkopf.waterBills.data.BillDao;
import com.kartoffelkopf.waterBills.model.Bill;
import com.kartoffelkopf.waterBills.model.Reading;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;
	
	@Autowired
	private ReadingService readingService;
	
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
	public void addReading(Long id, Reading reading) {
		Bill bill = findById(id);
		Long readingId = readingService.save(reading);
		bill.setReading(readingService.findById(readingId));
		save(bill);
	}
	
	public Bill getPreviousBill(Bill bill) {
		return billDao.getPreviousBill(bill);
	}

	@Override
	public void calculate(Long id) {
		Bill bill = findById(id);
		try {
		float downUnitsUsed = bill.getReading().getUnits() - readingService.getPreviousReading(bill.getReading()).getUnits();
		bill.setDownAmount(bill.getAmount() * (downUnitsUsed / bill.getTotalUnits()));
		bill.setUpAmount(bill.getAmount() - bill.getAmount() * (downUnitsUsed / bill.getTotalUnits()));
		save(bill);
		} catch (Exception e) {
			e.printStackTrace();
			bill.setDownAmount(0);
			bill.setUpAmount(0);
			save(bill);
		}
		
		
	}

	@Override
	public boolean togglePaid(Long id) {
		Bill bill = findById(id);
		if (bill.isPaid()) {
			bill.setPaid(false);
		} else {
			bill.setPaid(true);
		}
		save(bill);
		return bill.isPaid();
	}
}
