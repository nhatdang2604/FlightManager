package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.tkpm.entities.Flight;
import com.tkpm.entities.Reservation;
import com.tkpm.entities.Transition;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum TransitionDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private TransitionDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
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

	public List<Transition> create(List<Transition> transitions) {
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Save the transition to database
			transitions.forEach(transition -> {
				Integer id = (Integer) session.save(transition);
				
				//Update the current id for the given transition
				transition.setId(id);
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return transitions;
	}

	public List<Transition> find(Flight flight) {
		
		List<Transition> transitions = new ArrayList<>();
		if (null == flight || null == flight.getId()) {
			return transitions;
		}
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			String param = "param";
			String query = 
					"from " + Transition.class.getName() + " " + 
					"where flight.id = :" + param;
			
			transitions = session
					.createQuery(query, Transition.class)
					.setParameter(param, flight.getId())
					.getResultList();
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return transitions;
	}

	public List<Transition> createOrUpdate(List<Transition> transitions) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			
			for (Transition transition: transitions) {
				session.saveOrUpdate(transition);
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return transitions;
		
	}
}
 