package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.Flight;
import com.tkpm.entities.FlightDetail;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum FlightDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private FlightDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	//Create new flight
	public Flight create(Flight flight) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Save the flight to database
			Integer id = (Integer) session.save(flight);
			
			//Update the current id for the given flight
			flight.setId(id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return flight;
	}
	
	//Update an flight
	public Flight update(Flight flight) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Update the flight
			session.update(flight);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return flight;
	}
	
	//Delete an flight by the given id
	public int delete(Integer id) {

		Session session = factory.getCurrentSession();
		int errorCode = 0;
		
		try {
			session.beginTransaction();
			
			Flight flight = session.get(Flight.class, id);
			
			if (null !=  flight) {
				session.delete(flight);
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
			errorCode = 1;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return errorCode;
	}
	
	//Find all flights in database
	public List<Flight> findAll() {
		
		Session session = factory.getCurrentSession();
		List<Flight> flights = new ArrayList<>();
		
		try {
			session.beginTransaction();
			
			//Get the list of flights
			flights = session.createQuery("from " + Flight.class.getName()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return flights;
		
	}
	
	//Find flight by id
	public Flight find(Integer id) {
		
		Session session = factory.getCurrentSession();
		Flight flight = null;
		
		try {
			session.beginTransaction();
			
			flight = session.get(Flight.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return flight;
	}

	public FlightDetail findDetailById(Integer id) {
		
		Session session = factory.getCurrentSession();
		FlightDetail detail = null;
		
		try {
			session.beginTransaction();
			
			detail = session.get(FlightDetail.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return detail;
	}
}
 