package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.Reservation;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum ReservationDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private ReservationDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	//Create list of reservations
	public List<Reservation> create(List<Reservation> reservations) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Iterate over all the reservation
			reservations.forEach(reservation -> {
				
				//Save the reservations to database
				Integer id = (Integer) session.save(reservation);
				
				//Update the current id for the given reservation
				reservation.setId(id);
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return reservations;
	}
	
	//Update list of reservations
	public List<Reservation> update(List<Reservation> reservations) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Iterate over all the reservation
			reservations.forEach(reservation -> {
				
				//Update each reservation
				session.update(reservation);
				
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return reservations;
	}
	
	//Delete an reservation by the given id
	public int delete(List<Integer> ids) {

		Session session = factory.getCurrentSession();
		int errorCode = 0;
		
		try {
			session.beginTransaction();
			
			//Iterate over each id
			ids.forEach(id -> {
				
				//Try to find the reservation with the given id
				Reservation reservation = session.get(Reservation.class, id);
				
				//Delete the reservation if it was existed
				if (null !=  reservation) {
					session.delete(reservation);
				}
			});
			
			
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
	
	//Find all reservations in database
	public List<Reservation> findAll() {
		
		Session session = factory.getCurrentSession();
		List<Reservation> reservations = new ArrayList<>();
		
		try {
			session.beginTransaction();
			
			//Get the list of reservations
			reservations = session.createQuery("from " + Reservation.class.getName()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return reservations;
		
	}
	
	//Find reservation by id
	public Reservation find(Integer id) {
		
		Session session = factory.getCurrentSession();
		Reservation reservation = null;
		
		try {
			session.beginTransaction();
			
			reservation = session.get(Reservation.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return reservation;
	}
}
 