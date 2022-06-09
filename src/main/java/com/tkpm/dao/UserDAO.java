package com.tkpm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tkpm.entities.User;
import com.tkpm.utils.HibernateUtil;

//Using enum for applying Singleton Pattern
public enum UserDAO {

	INSTANCE;
	
	private SessionFactory factory;
	
	private UserDAO() {
		factory = HibernateUtil.INSTANCE.getSessionFactory();
	}
	
	//Create new user
	public User create(User user) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Save the user to database
			Integer id = (Integer) session.save(user);
			
			//Update the current id for the given user
			user.setId(id);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return user;
	}
	
	//Update an user
	public User update(User user) {
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Update the user
			session.update(user);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return user;
	}
	
	//Delete an user by the given id
	public int delete(Integer id) {

		Session session = factory.getCurrentSession();
		int errorCode = 0;
		
		try {
			session.beginTransaction();
			
			User user = session.get(User.class, id);
			
			if (null !=  user) {
				session.delete(user);
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
	
	//Find all users in database
	public List<User> findAll() {
		
		Session session = factory.getCurrentSession();
		List<User> users = new ArrayList<>();
		
		try {
			session.beginTransaction();
			
			//Get the list of users
			users = session.createQuery("from " + User.class.getName()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		
		return users;
		
	}
	
	//Find user by id
	public User find(Integer id) {
		
		Session session = factory.getCurrentSession();
		User user = null;
		
		try {
			session.beginTransaction();
			
			user = session.get(User.class, id);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return user;
	}
	
	//Find user by username
	public User find(String username) {
	
		Session session = factory.getCurrentSession();
		User user = null;
		
		try {
			session.beginTransaction();
			
			//Parameterize the query
			String param = "username";
			
			//Make the query
			String query = "from " + User.class.getName() + " u where u.username = :" + param;
			
			user = (User) session.createQuery(query)
					.setParameter(param, username)
					.setMaxResults(1)
					.stream()
					.findFirst()
					.orElse(null);
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	
		return user;	
	}
}
 