package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.Ticket;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum TicketDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private TicketDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	//Create list of tickets
	public List<Ticket> create(List<Ticket> tickets) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Iterate over all the ticket
			tickets.forEach(ticket -> {
				
				//Save the tickets to database
				Integer id = (Integer) session.save(ticket);
				
				//Update the current id for the given ticket
				ticket.setId(id);
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return tickets;
	}
	
	//Update list of tickets
	public List<Ticket> update(List<Ticket> tickets) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Iterate over all the ticket
			tickets.forEach(ticket -> {
				
				//Update each ticket
				session.update(ticket);
				
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return tickets;
	}
	
	//Delete an ticket by the given id
	public int delete(List<Integer> ids) {

		Session session = factory.getCurrentSession();
		int errorCode = 0;
		
		try {
			session.beginTransaction();
			
			//Iterate over each id
			ids.forEach(id -> {
				
				//Try to find the ticket with the given id
				Ticket ticket = session.get(Ticket.class, id);
				
				//Delete the ticket if it was existed
				if (null !=  ticket) {
					session.delete(ticket);
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
	
	//Find all tickets in database
	public List<Ticket> findAll() {
		
		Session session = factory.getCurrentSession();
		List<Ticket> tickets = new ArrayList<>();
		
		try {
			session.beginTransaction();
			
			//Get the list of tickets
			tickets = session.createQuery("from " + Ticket.class.getName()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return tickets;
		
	}
	
	//Find ticket by id
	public Ticket find(Integer id) {
		
		Session session = factory.getCurrentSession();
		Ticket ticket = null;
		
		try {
			session.beginTransaction();
			
			ticket = session.get(Ticket.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return ticket;
	}
}
 