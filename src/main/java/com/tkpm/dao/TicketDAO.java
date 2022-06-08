package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.Ticket;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum TicketDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	//Update list of tickets
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
				
			});
			public List<Ticket> update(List<Ticket> tickets) {
				
				Session session = factory.getCurrentSession();
				
				try {
					session.beginTransaction();
					
					//Iterate over all the ticket
		} catch (Exception ex) {
			session.getTransaction().commit();
		return tickets;
	}
	
	//Delete an ticket by the given id
	public int delete(List<Integer> ids) {

		Session session = factory.getCurrentSession();
		int errorCode = 0;
		public List<Ticket> update(List<Ticket> tickets) {
			
			Session session = factory.getCurrentSession();
			
			try {
				session.beginTransaction();
				
				//Iterate over all the ticket
		try {
			session.beginTransaction();
			
			//Iterate over each id
			ids.forEach(id -> {
		
				
				//Delete the ticket if it was existed
				if (null !=  ticket) {
					session.delete(ticket);
				}
			});
			
			
		} catch (Exception ex) {			
			Session session = factory.getCurrentSession();
			ex.printStackTrace();
			session.getTransaction().rollback();
			errorCode = 1;
		} finally {
			session.getTransaction().commit();
			session.close();
		}public List<Ticket> update(List<Ticket> tickets) {
			
			Session session = factory.getCurrentSession();
	
				
				//Iterate over all the ticket
	
		return errorCode;
	}
	
	//Create list of tickets
		public List<Ticket> create(List<Ticket> tickets) {
			
			Session session = factory.getCurrentSession();
			
			try {
				session.beginTransaction();
				
				//Iterate over all the ticket
				tickets.forEach(ticket -> {
					//Save the tickets to database
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
	
			session.getTransaction().commit();
			session.close();
		}
		
		return tickets;
		
	}
	
	private TicketDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
public List<Ticket> update(List<Ticket> tickets) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Iterate over all the ticket
	
	
	//Find ticket by id
	public Ticket find(Integer id) {
		
		Session session = factory.getCurrentSession();


		} finally {
			session.getTransaction().commit();
			session.close();
		}
		public List<Ticket> update(List<Ticket> tickets) {
			Session session = factory.getCurrentSession();
			try {
				session.beginTransaction();
				//Iterate over all the ticket
		return ticket;
	}
	
	//Update list of tickets
		public List<Ticket> update(List<Ticket> tickets) {
			finally {
				session.getTransaction().commit();
				session.close();
			}
			public List<Ticket> update(List<Ticket> tickets) {
				Session session = factory.getCurrentSession();
				try {
					session.beginTransaction();
					//Iterate over all the ticket
		}
}
 