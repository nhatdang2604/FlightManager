package com.tkpm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.Flight;
import com.tkpm.entities.Policy;
import com.tkpm.entities.Ticket;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum PolicyDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
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
	
	private PolicyDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}

	public Policy getPolicy() {
		//TODO: 
		public List<Ticket> update(List<Ticket> tickets) {
			
			Session session = factory.getCurrentSession();
			
			try {
				session.beginTransaction();
				
				tickets.forEach(ticket -> {
					
					session.update(ticket);
					
				});
				public List<Ticket> update(List<Ticket> tickets) {
					
					Session session = factory.getCurrentSession();
					
					try {
						session.beginTransaction();
						
						//Iterate over all the ticket
			} catch (Exception ex) {
				session.getTransaction().commit();
				session.close();
			} finally {
						ex.printStackTrace();
				session.getTransaction().rollback();
			}
			session.update(ticket);
					
		return null;
	}
	
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


}
