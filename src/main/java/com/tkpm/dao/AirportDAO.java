package com.tkpm.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.Airport;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum AirportDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private AirportDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	//Create new airport
	public Airport create(Airport airport) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Save the airport to database
			Integer id = (Integer) session.save(airport);
			
			//Update the current id for the given airport
			airport.setId(id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return airport;
	}
	
	//Update an airport
	public Airport update(Airport airport) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Update the airport
			session.update(airport);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return airport;
	}
	
	//Delete an airport by the given id
	public int delete(Integer id) {

		Session session = factory.getCurrentSession();
		int errorCode = 0;
		
		try {
			session.beginTransaction();
			
			Airport airport = session.get(Airport.class, id);
			
			if (null !=  airport) {
				session.delete(airport);
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
	
	//Find all airports in database
	public Set<Airport> findAll() {
		
		Session session = factory.getCurrentSession();
		Set<Airport> airports = null;
		
		try {
			session.beginTransaction();
			
			//Get the list of airports
			List<Airport> result = session.createQuery("from " + Airport.class.getName()).list();
			
			//Convert the list to set
			airports = new TreeSet<>(result);
		
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return airports;
		
	}
	
	//Find airport by id
	public Airport find(Integer id) {
		
		Session session = factory.getCurrentSession();
		Airport airport = null;
		
		try {
			session.beginTransaction();
			
			airport = session.get(Airport.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return airport;
	}
}
 