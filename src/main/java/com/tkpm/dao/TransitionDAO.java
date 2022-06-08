package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.Transition;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum TransitionDAO {

	private TransitionDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	private SessionFactory factory;
	
	//Find transition by id
		public Transition find(Integer id) {
			
			Session session = factory.getCurrentSession();
			Transition transition = null;
			
			try {
				session.beginTransaction();
				
				transition = session.get(Transition.class, id);
				
			} catch (Exception ex) {
				
				ex.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.getTransaction().commit();
				session.close();
			}
		
			return transition;
		}

		
		//Delete an transition by the given id
		public int delete(Integer id) {

			Session session = factory.getCurrentSession();
			int errorCode = 0;
			
			try {
				session.beginTransaction();
				
				Transition transition = session.get(Transition.class, id);
				
				if (null !=  transition) {
					session.delete(transition);
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
			
	
	//Update an transition
	public Transition update(Transition transition) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Update the transition
			session.update(transition);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return transition;
	}
	
	//Delete an transition by the given id
	public int delete(Integer id) {
		
		ex.printStackTrace();
		session.getTransaction().rollback();
		Session session = factory.getCurrentSession();
		int errorCode = 0;
		
		try {
			session.beginTransaction();
			
			Transition transition = session.get(Transition.class, id);
			
			if (null !=  transition) {
				session.delete(transition);
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
			errorCode = 1;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		try {
			session.beginTransaction();
			
			Transition transition = session.get(Transition.class, id);
			
			if (null !=  transition) {
				session.delete(transition);
			}
		return errorCode;
	}
	
	//Find all transitions in database
	public List<Transition> findAll() {
		
		Session session = factory.getCurrentSession();
		List<Transition> transitions = new ArrayList<>();
		
		try {
			session.beginTransaction();
			
			//Get the list of transitions
			transitions = session.createQuery("from " + Transition.class.getName()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return transitions;
		
	}
	
	//Create new transition
	public Transition create(Transition transition) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Save the transition to database
			Integer id = (Integer) session.save(transition);
			
			//Update the current id for the given transition
			transition.setId(id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return transition;
	}
	
	}
}
 