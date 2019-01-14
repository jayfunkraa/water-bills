package com.kartoffelkopf.waterBills.data;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kartoffelkopf.waterBills.model.Reading;

@Repository
public class ReadingDaoImpl implements ReadingDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Reading> findAll() {
		Session session = sessionFactory.openSession();
		CriteriaQuery<Reading> criteriaQuery = session.getCriteriaBuilder().createQuery(Reading.class);
		criteriaQuery.from(Reading.class);
		List<Reading> reading = session.createQuery(criteriaQuery).getResultList();
		session.close();
		return reading;
	}

	@Override
	public Reading findById(Long id) {
		Session session = sessionFactory.openSession();
		Reading reading = session.get(Reading.class, id);
		session.close();
		return reading;
	}

	@Override
	public Long save(Reading reading) {
		Long id;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		if (reading.getReadingId() == null) {
			id = (Long) session.save(reading);
		} else {
			id = reading.getReadingId();
			session.update(reading);
		}
		session.getTransaction().commit();
		session.close();
		return id;
	}

	@Override
	public void delete(Reading reading) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(reading);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Reading getPreviousReading(Reading reading) {
			Session session = sessionFactory.openSession();
			String hql = "FROM Reading WHERE date < :readingDate ORDER BY date DESC";
			Query query = session.createQuery(hql);
			query.setParameter("readingDate", reading.getDate());
			query.setMaxResults(1);
			List<Reading> readings = query.list();
			session.close();
			return readings.get(0);
	}
	
	

}
