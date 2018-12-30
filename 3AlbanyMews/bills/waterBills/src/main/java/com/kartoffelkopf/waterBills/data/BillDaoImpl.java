package com.kartoffelkopf.waterBills.data;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kartoffelkopf.waterBills.model.Bill;

@Repository
public class BillDaoImpl implements BillDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Bill> findAll() {
		Session session = sessionFactory.openSession();
		CriteriaQuery<Bill> criteriaQuery = session.getCriteriaBuilder().createQuery(Bill.class);
		criteriaQuery.from(Bill.class);
		List<Bill> bills = session.createQuery(criteriaQuery).getResultList();
		session.close();
		return bills;
	}

	@Override
	public Bill findById(Long id) {
		Session session = sessionFactory.openSession();
		Bill bill = session.get(Bill.class, id);
		session.close();
		return bill;
	}

	@Override
	public void save(Bill bill) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(bill);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(Bill bill) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(bill);
		session.getTransaction().commit();
		session.close();
	}
	

}
